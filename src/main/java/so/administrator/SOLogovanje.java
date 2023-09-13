package so.administrator;

import db.DBBroker;
import domen.Administrator;
import domen.OpstiDomenskiObjekat;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;

/**
 * Predstavlja sistemsku operaciju koja omogucava administratoru da se uloguje
 * na sistem. Implementira apstraktne metode iz apstraktne klase
 * OpstaSistemskaOperacija
 *
 * @author Jovana
 */

public class SOLogovanje extends OpstaSistemskaOperacija {
	/**
	 * Objekat koji predstavlja ulogovanog administratora
	 */

	Administrator ulogovani;

	/**
	 * @throws Exception ako prosledjeni objekat nije instanca klase Administrator
	 */

	@Override
	protected void validate(OpstiDomenskiObjekat odo) throws Exception {
		if (!(odo instanceof Administrator)) {
			throw new Exception("Prosledjeni objekat nije instanca klase Administrator!");
		}

	}

	/**
	 * Poziva brokera baze podataka da izvrsi SELECT upit i vrati sve administratore
	 * iz baze podataka, a zatim proveri da li postoji medju njima onaj sa unetim
	 * podacima. Ako postoji setuje ulogovanog korisnika na pronadjenog
	 * administratora iz baze.
	 * 
	 * @throws Exception ako ne postoji administrator sa unetim podacima u bazi
	 *                   podataka
	 */

	@Override
	protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
		Administrator a = (Administrator) odo;

		ArrayList<Administrator> administratori = (ArrayList<Administrator>) (ArrayList<?>) DBBroker.getInstance()
				.select(odo);
		System.out.println(administratori.toArray());

		for (Administrator administrator : administratori) {
			if (administrator.getUsername().equals(a.getUsername())
					&& administrator.getPassword().equals(a.getPassword())) {
				ulogovani = administrator;
				return;
			}
		}

		throw new Exception("Ne postoji administrator sa tim kredencijalima.");
	}

	/**
	 * Vraca ulogovanog administratora.
	 * 
	 * @return ulogovani administrator Administrator
	 */

	public Administrator getUlogovani() {
		return ulogovani;
	}

}
