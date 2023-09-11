package so.grupa;

import db.DBBroker;
import domen.Grupa;
import domen.OpstiDomenskiObjekat;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author Jovana
 */
public class SOUcitajGrupe extends OpstaSistemskaOperacija {
private ArrayList<Grupa> grupe;

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
      if (!(odo instanceof Grupa)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Grupa!");
        }
    }

    @Override
    protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
       ArrayList<OpstiDomenskiObjekat> lista = DBBroker.getInstance().select(odo);
         grupe= (ArrayList<Grupa>) (ArrayList<?>)lista;
    }
 
    
     public ArrayList<Grupa> getLista() {
        return grupe;
    }
}