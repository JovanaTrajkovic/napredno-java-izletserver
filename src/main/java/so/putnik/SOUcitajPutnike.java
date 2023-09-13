package so.putnik;

import db.DBBroker;
import domen.OpstiDomenskiObjekat;
import domen.Putnik;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;

/**
 * Predstavlja sistemsku operaciju za ucitavanje svih putnika iz baze podataka.
 * Implementira apstraktne metode iz apstraktne klase OpstaSistemskaOperacija.
 * 
 * @author Jovana
 */

public class SOUcitajPutnike extends OpstaSistemskaOperacija {

	/**
	 * Lista svih putnika
	 */
	private ArrayList<Putnik> putnici;

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
	 * Poziva brokera baze podataka da izvrsi SELECT upit i rezultat upita postavlja
	 * u listu putnici.
	 */
	@Override
	protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
		ArrayList<OpstiDomenskiObjekat> lista = DBBroker.getInstance().select(odo);
		putnici = (ArrayList<Putnik>) (ArrayList<?>) lista;
	}

	/**
	 * Vraca listu putnika
	 * 
	 * @return putnici kao lista
	 */
	public ArrayList<Putnik> getLista() {
		return putnici;
	}

}

