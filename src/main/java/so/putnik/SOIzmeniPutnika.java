package so.putnik;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import db.DBBroker;
import domen.OpstiDomenskiObjekat;
import domen.Putnik;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;

/**
 * Predstavlja sistemsku operaciju za menjanje putnika iz baze podataka.
 * Implementira apstraktne metode iz apstraktne klase OpstaSistemskaOperacija.
 * 
 * @author Jovana
 */
public class SOIzmeniPutnika extends OpstaSistemskaOperacija {

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
	 * Poziva brokera baze podataka da izvrsi UPDATE upit kojim se menja putnik u
	 * bazi podataka.
	 */
	@Override
	protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
		DBBroker.getInstance().update(odo);
	}

}
