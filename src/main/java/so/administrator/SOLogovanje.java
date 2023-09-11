package so.administrator;
import db.DBBroker;
import domen.Administrator;
import domen.OpstiDomenskiObjekat;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author Jovana
 */
public class SOLogovanje extends OpstaSistemskaOperacija {
    Administrator ulogovani;

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof Administrator)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Administrator!");
        }
        
    }

    @Override
    protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
         Administrator a = (Administrator) odo;

        ArrayList<Administrator> administratori  = (ArrayList<Administrator>) (ArrayList<?>)
                DBBroker.getInstance().select(odo);

        for (Administrator administrator : administratori) {
            if (administrator.getUsername().equals(a.getUsername())
                    && administrator.getPassword().equals(a.getPassword())) {
                ulogovani = administrator;
                return;
            }
        }

        throw new Exception("Ne postoji administrator sa tim kredencijalima.");
    }
    
    public Administrator getUlogovani() {
        return ulogovani;
    }

 
}
