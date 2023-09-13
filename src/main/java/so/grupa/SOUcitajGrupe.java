package so.grupa;

import db.DBBroker;
import domen.Grupa;
import domen.OpstiDomenskiObjekat;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;

/**
 * Predstavlja sistemsku operaciju za ucitavanje svih grupa iz baze podataka.
 * Implementira apstraktne metode iz apstraktne klase OpstaSistemskaOperacija.
 * 
 * @author Jovana
 */

public class SOUcitajGrupe extends OpstaSistemskaOperacija {
	/**
	 * Lista svih grupa
	 */
	private ArrayList<Grupa> grupe;

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
	 * Poziva brokera baze podataka da izvrsi SELECT upit i rezultat upita postavlja
	 * u listu grupa.
	 */
	@Override
	protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
		ArrayList<OpstiDomenskiObjekat> lista = DBBroker.getInstance().select(odo);
		grupe = (ArrayList<Grupa>) (ArrayList<?>) lista;
	}

	/**
	 * Vraca listu grupa
	 * 
	 * @return grupe kao lista
	 */

	public ArrayList<Grupa> getLista() {
		return grupe;
	}
}