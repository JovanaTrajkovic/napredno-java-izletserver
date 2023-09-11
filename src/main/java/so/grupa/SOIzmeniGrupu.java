package so.grupa;
import db.DBBroker;
import domen.Clan;
import domen.Grupa;
import domen.OpstiDomenskiObjekat;
import java.util.ArrayList;
import java.util.Date;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author Jovana
 */
public class SOIzmeniGrupu  extends OpstaSistemskaOperacija{
    
      

     

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
         if (!(odo instanceof Grupa)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Grupa!");
        }
              Grupa g = (Grupa) odo;

        if (g.getKapacitetGrupe() < 20 || g.getKapacitetGrupe() > 60) {
            throw new Exception("Kapacitet grupe mora biti izmedju 15 i 60!");
        }

        if (g.getClanovi().size() < 20 || g.getClanovi().size() > g.getKapacitetGrupe()) {
            throw new Exception("Grupa mora minimum 10 uclanova, a maksimum "
                    + g.getKapacitetGrupe() + "!");
        }

        if (!g.getDatumPolaska().after(new Date())) {
            throw new Exception("Datum polaska mora biti posle danasnjeg datuma!");
        }
 }

    @Override
    protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
    DBBroker.getInstance().update(odo);

       
        Grupa g = (Grupa) odo;
    
        DBBroker.getInstance().delete(g.getClanovi().get(0));
        
        for (Clan clan : g.getClanovi()) {
            DBBroker.getInstance().insert(clan);
        }
    }

}
