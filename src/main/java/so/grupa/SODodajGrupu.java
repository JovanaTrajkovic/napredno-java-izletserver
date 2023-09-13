package so.grupa;

import db.DBBroker;
import domen.Clan;
import domen.Grupa;
import domen.OpstiDomenskiObjekat;
import so.OpstaSistemskaOperacija;
import java.sql.*;
import java.util.ArrayList;

/**
 * Predstavlja sistemsku operaciju za dodavanje nove grupe u bazu podataka, kao
 * i svih clanova date grupe. Implementira apstraktne metode iz apstraktne klase
 * OpstaSistemskaOperacija.
 * 
 * @author Jovana
 */

public class SODodajGrupu extends OpstaSistemskaOperacija {

	/**
	 * @throws Exception ako prosledjeni objekat nije instanca klase Grupa, ako je
	 *                   ako je broj clanova manji od 2 ili veci od unetog
	 *                   kapaciteta grupe,
	 * 
	 */

	@Override
	protected void validate(OpstiDomenskiObjekat odo) throws Exception {
		if (!(odo instanceof Grupa)) {
			throw new Exception("Prosledjeni objekat nije instanca klase Grupa!");
		}

		Grupa g = (Grupa) odo;
		if (g.getClanovi().size() < 2 || g.getClanovi().size() > g.getKapacitetGrupe()) {
			throw new Exception("Grupa mora minimum 2 clana, a maksimum " + g.getKapacitetGrupe() + "!");
		}

	}

	/**
	 * Poziva brokera baze podataka da izvrsi INSERT upit kojim se dodaje nova grupa
	 * u bazu podataka , a zatim se setuje id dobijene grupe,cime svi clanovi
	 * dobijaju taj id. Poziva se INSERT upit za sve clanove i oni se dodaju u bazu.
	 */

	@Override
	protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
		PreparedStatement ps = DBBroker.getInstance().insert(odo);

		ResultSet rsID = ps.getGeneratedKeys();
		rsID.next();
		Long grupaID = rsID.getLong(1);

		Grupa g = (Grupa) odo;
		g.setGrupaID(grupaID);

		for (Clan clan : g.getClanovi()) {
			clan.setGrupa(g);
			DBBroker.getInstance().insert(clan);
		}

	}

}