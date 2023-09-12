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
public class SODodajPutnika extends OpstaSistemskaOperacija{

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof Putnik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Putnik");
        }

       Putnik p= (Putnik) odo;
      
      
    }

    @Override
    protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
         DBBroker.getInstance().insert(odo);
    }
    
}
