package so;

import domen.Administrator;

import db.DBBroker;
import domen.OpstiDomenskiObjekat;
import java.sql.SQLException;

/**
 * Apstraktna klasa koju nasledjuju sve klase sistemskih operacija.
 * 
 * Sadrzi apstraktne metode za validaciju i izvrsenje sistemske operacije, kao i
 * metode za potvrdjivanje, tj. ponistavanje transakcije.
 *
 * @author Jovana
 */

public abstract class OpstaSistemskaOperacija {
	/**
	 * Pokrece validaciju, odnosno proveru unetih podataka.
	 * Specificna je za svaku sistemsku operaciju, tako da zahteva implementaciju u
	 * klasi koja je definisana za to.
	 * 
	 * @param ado domenski objekat sa podacima nad kojima se vrsi validacija
	 * 
	 * @throws Exception ako dodje do greske prilikom validacije unetih podataka
	 */

	protected abstract void validate(OpstiDomenskiObjekat odo) throws Exception;

	/**
	 * Pokrece izvrsavanje sistemske operacije.
	 * 
	 * U okviru nje se poziva broker baze podataka da izvrsi odgovarajuci upit.
	 * 
	 * Specificna je za svaku sistemsku operaciju, tako da zahteva implementaciju u
	 * klasi koja je definisana za to.
	 * 
	 * @param ado domenski objekat sa podacima potrebnim za izvrsavanje sistemske
	 *            operacije
	 * 
	 * @throws Exception ako dodje do greske prilikom izvrsavanja sistemske
	 *                   operacije
	 */
	protected abstract void executeOperation(OpstiDomenskiObjekat odo) throws Exception;

	/**
	 * Poziva metode za validaciju, izvrsenje operacije i potvrdjivanje ili
	 * ponistavanje sistemske operacije.
	 * 
	 * @param ado domenski objekat sa podacima nad kojima se izvrsavaju metode
	 * 
	 * @throws Exception ako dodje do greske u bilo kojoj fazi izvrsenja metode
	 */

	public void execute(OpstiDomenskiObjekat odo) throws Exception {
		try {
			validate(odo);
			executeOperation(odo);
			commit();
		} catch (Exception e) {
			rollback();
			throw e;
		}
	}

	/**
	 * Potvrdjuje transakciju nastalu kao rezultat izvrsenja sistemske operacije.
	 * 
	 * @throws SQLException ako dodje do greske prilikom potvrdjivanja transakcije
	 */
	public void commit() throws SQLException {
		DBBroker.getInstance().getConnection().commit();
	}

	/**
	 * Ponistava transakciju nastalu kao rezultat izvrsenja sistemske operacije.
	 * 
	 * @throws SQLException ako dodje do greske prilikom ponistavanja transakcije
	 */

	public void rollback() throws SQLException {
		DBBroker.getInstance().getConnection().rollback();
	}

}
