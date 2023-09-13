package so.vodic;

import db.DBBroker;
import domen.OpstiDomenskiObjekat;
import domen.Vodic;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;

/**
 * Predstavlja sistemsku operaciju za ucitavanje svih vodica iz baze podataka.
 * Implementira apstraktne metode iz apstraktne klase OpstaSistemskaOperacija
 *
 * @author Jovana
 */

public class SOUcitajVodice extends OpstaSistemskaOperacija {

	/**
	 * Lista svih vidica
	 */
	private ArrayList<Vodic> vodici;

	/**
	 * @throws Exception ako prosledjeni objekat nije instanca klase Vodic
	 */

	@Override
	protected void validate(OpstiDomenskiObjekat odo) throws Exception {
		if (!(odo instanceof Vodic)) {
			throw new Exception("Prosledjeni objekat nije instanca klase Vodic!");
		}
	}

	/**
	 * Poziva brokera baze podataka da izvrsi SELECT upit i rezultat upita postavlja
	 * u listu vodica.
	 */

	@Override
	protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
		ArrayList<OpstiDomenskiObjekat> lista = DBBroker.getInstance().select(odo);
		vodici = (ArrayList<Vodic>) (ArrayList<?>) lista;
	}

	/**
	 * Vraca listu vodica
	 * 
	 * @return vodici
	 */

	public ArrayList<Vodic> getLista() {
		return vodici;
	}
}
