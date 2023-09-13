package so.putnik;

import db.DBBroker;
import domen.OpstiDomenskiObjekat;
import domen.Putnik;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;

/**
 * Predstavlja sistemsku operaciju za dodavanje novog putnika u bazu podataka.
 * Implementira apstraktne metode iz apstraktne klase OpstaSistemskaOperacija.
 * 
 * @author Jovana
 */
public class SODodajPutnika extends OpstaSistemskaOperacija {

	/**
	 * @throws Exception ako prosledjeni objekat nije instanca klase Putnik
	 * 
	 */
	@Override
	protected void validate(OpstiDomenskiObjekat odo) throws Exception {
		if (!(odo instanceof Putnik)) {
			throw new Exception("Prosledjeni objekat nije instanca klase Putnik");
		}

		Putnik p = (Putnik) odo;

	}

	/**
	 * Poziva brokera baze podataka da izvrsi INSERT upit kojim se dodaje novi
	 * putnik u bazu podataka.
	 */
	@Override
	protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
		DBBroker.getInstance().insert(odo);
	}

}
