package so.putnik;
import db.DBBroker;

import db.DBBroker;
import domen.OpstiDomenskiObjekat;
import domen.Putnik;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author Jovana
 */
public class SOObrisiPutnika extends OpstaSistemskaOperacija{

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
     if (!(odo instanceof Putnik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Putnik!");
        }
    }

    @Override
    protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
        DBBroker.getInstance().delete(odo);
    }
}

