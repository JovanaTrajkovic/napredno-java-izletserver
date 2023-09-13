package so.grupa;


import db.DBBroker;
import domen.Grupa;
import domen.OpstiDomenskiObjekat;
import so.OpstaSistemskaOperacija;

/**
 * Predstavlja sistemsku operaciju za brisanje postojece grupe iz baze
 * podataka. Implementira apstraktne metode iz apstraktne klase
 * OpstaSistemskaOperacija.
 * 
 * @author Jovana
 */

public class SOObrisiGrupu  extends OpstaSistemskaOperacija{

	
	/**
	 * @throws Exception ako prosledjeni objekat nije instanca klase Grupa
	 */

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
     if (!(odo instanceof Grupa)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Grupa!");
        }
    }

    
    
    /**
	 * Poziva brokera baze podataka da izvrsi DELETE upit kojim se brise postojeca
	 * grupa iz baze podataka.
	 */

    @Override
    protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
        DBBroker.getInstance().delete(odo);
    }
    
    
}

