package so.putnik;

import db.DBBroker;
import domen.OpstiDomenskiObjekat;
import domen.Putnik;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author Jovana
 */
public class SOUcitajPutnike  extends OpstaSistemskaOperacija{

    private ArrayList<Putnik> putnici;
    
    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
    if (!(odo instanceof Putnik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Putnik!");
        }
    }

    @Override
    protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
      ArrayList<OpstiDomenskiObjekat> lista = DBBroker.getInstance().select(odo);
       putnici = (ArrayList<Putnik>) (ArrayList<?>) lista;
    }
     public ArrayList<Putnik> getLista() {
        return putnici;
    }
}
