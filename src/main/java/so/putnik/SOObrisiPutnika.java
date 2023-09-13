package so.putnik;

import db.DBBroker;

import db.DBBroker;
import domen.OpstiDomenskiObjekat;
import domen.Putnik;
import so.OpstaSistemskaOperacija;

/**
 * Predstavlja sistemsku operaciju za brisanje postojeceg putnika iz baze
 * podataka. Implementira apstraktne metode iz apstraktne klase
 * OpstaSistemskaOperacija.
 * 
 * @author Jovana
 */
public class SOObrisiPutnika extends OpstaSistemskaOperacija {

	/**
	 * @throws Exception ako prosledjeni objekat nije instanca klase Putnik
	 */

	@Override
	protected void validate(OpstiDomenskiObjekat odo) throws Exception {
		if (!(odo instanceof Putnik)) {
			throw new Exception("Prosledjeni objekat nije instanca klase Putnik!");
		}
	}

	/**
	 * Poziva brokera baze podataka da izvrsi DELETE upit kojim se brise postojeci
	 * putnik iz baze podataka.
	 */
	@Override
	protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
		DBBroker.getInstance().delete(odo);
	}
}

