package so.grupa;


import db.DBBroker;
import domen.Grupa;
import domen.OpstiDomenskiObjekat;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author Jovana
 */
public class SOObrisiGrupu  extends OpstaSistemskaOperacija{

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
     if (!(odo instanceof Grupa)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Grupa!");
        }
    }

    @Override
    protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
        DBBroker.getInstance().delete(odo);
    }
    
    
}

