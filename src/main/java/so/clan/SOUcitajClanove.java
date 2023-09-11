package so.clan;
import db.DBBroker;
import domen.Clan;
import domen.OpstiDomenskiObjekat;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author Jovana
 */
public class SOUcitajClanove extends OpstaSistemskaOperacija{

    private ArrayList<Clan> clanovi;
    
    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
    if (!(odo instanceof Clan)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Clan!");
        }
    }

    @Override
    protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
      ArrayList<OpstiDomenskiObjekat> lista = DBBroker.getInstance().select(odo);
       clanovi = (ArrayList<Clan>) (ArrayList<?>) lista;
    }
     public ArrayList<Clan> getLista() {
        return clanovi;
    }
}
