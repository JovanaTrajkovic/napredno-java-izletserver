package so.mesto;

import db.DBBroker;
import domen.Mesto;
import domen.OpstiDomenskiObjekat;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;

/**
 * Predstavlja sistemsku operaciju za ucitavanje svih mesta iz baze podataka.
 * Implementira apstraktne metode iz apstraktne klase OpstaSistemskaOperacija
 *
 * @author Jovana
 */

public class SOUcitajMesta extends OpstaSistemskaOperacija {

	/**
	 * Lista svih mesta
	 */

	private ArrayList<Mesto> mesta;

	/**
	 * @throws Exception ako prosledjeni objekat nije instanca klase Mesto
	 */

	@Override
	protected void validate(OpstiDomenskiObjekat odo) throws Exception {
		if (!(odo instanceof Mesto))
			throw new Exception("Prosledjeni objekat nije instanca klase Mesto!");
	}

	/**
	 * Poziva brokera baze podataka da izvrsi SELECT upit i rezultat upita postavlja
	 * u listu mesta.
	 */

	@Override
	protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
		ArrayList<OpstiDomenskiObjekat> lista = DBBroker.getInstance().select(odo);
		mesta = (ArrayList<Mesto>) (ArrayList<?>) lista;
	}

	/**
	 * Vraca listu mesta
	 * 
	 * @return mesta
	 */

	public ArrayList<Mesto> getLista() {
		return mesta;
	}
}
