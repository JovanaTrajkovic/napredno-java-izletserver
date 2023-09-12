package so.grupa;


import db.DBBroker;
import domen.Clan;
import domen.Grupa;
import domen.OpstiDomenskiObjekat;
import so.OpstaSistemskaOperacija;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Jovana
 */
public class SODodajGrupu extends OpstaSistemskaOperacija{

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
      if (!(odo instanceof Grupa)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Grupa!");
        }
      
       Grupa g = (Grupa) odo;
       if (g.getClanovi().size() < 2 || g.getClanovi().size() > g.getKapacitetGrupe()) {
            throw new Exception("Grupa mora minimum 2 clana, a maksimum "
                    + g.getKapacitetGrupe() + "!");
        }
    
     
    }
      
    @Override
    protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
       PreparedStatement ps = DBBroker.getInstance().insert(odo);

       
        ResultSet rsID= ps.getGeneratedKeys();
        rsID.next();
        Long grupaID =rsID.getLong(1);

       
        Grupa g = (Grupa) odo;
        g.setGrupaID(grupaID);

        
        for (Clan  clan :g.getClanovi()) {
             clan.setGrupa(g);
              DBBroker.getInstance().insert(clan);
        }
 
    }
    
    
}