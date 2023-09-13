package so.clan;

import db.DBBroker;
import domen.Clan;
import domen.OpstiDomenskiObjekat;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;

/**
 * Predstavlja sistemsku operaciju za ucitavanje svih clanova izabrane grupe iz baze podataka.
 * Implementira apstraktne metode iz apstraktne klase OpstaSistemskaOperacija.
 * 
 * @author Jovana
 */

public class SOUcitajClanove extends OpstaSistemskaOperacija{
	/**
	 * Lista svih clanova izabrane grupe
	 */
    private ArrayList<Clan> clanovi;
    
    
    /**
     * @throws Exception ako prosledjeni objekat nije instanca klase Clan
     */
  @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
    if (!(odo instanceof Clan)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Clan!");
        }
    }

  
  /**
	 * Poziva brokera baze podataka da izvrsi SELECT upit iz baze izvuce sve clanove proslednjene grupe.
	 * Rezultat upita postavlja u  listu svih clanova.
	 *  
	 */
    @Override
    protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
      ArrayList<OpstiDomenskiObjekat> lista = DBBroker.getInstance().select(odo);
       clanovi = (ArrayList<Clan>) (ArrayList<?>) lista;
    }
    
    
    
    /**
     * Vraca listu svih clanova date grupe
     * 
     * @return clanovi kao listu svih clanova date grupe 
     */

     public ArrayList<Clan> getLista() {
        return clanovi;
    }

	
}
