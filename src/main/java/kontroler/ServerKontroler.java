package kontroler;

import domen.Administrator;
import domen.Clan;
import domen.Grupa;
import domen.Izlet;
import domen.Mesto;
import domen.Putnik;
import domen.Vodic;
import java.util.ArrayList;
import so.administrator.SOLogovanje;
import so.clan.SOUcitajClanove;
import so.grupa.SODodajGrupu;
import so.grupa.SOIzmeniGrupu;
import so.grupa.SOObrisiGrupu;
import so.grupa.SOUcitajGrupe;
import so.izlet.SOUcitajIzlete;
import so.mesto.SOUcitajMesta;
import so.putnik.SODodajPutnika;
import so.putnik.SOIzmeniPutnika;
import so.putnik.SOObrisiPutnika;
import so.putnik.SOUcitajPutnike;
import so.vodic.SOUcitajVodice;

/**
 *
 * @author Jovana
 */
public class ServerKontroler {
     private static ServerKontroler instance;

    private ServerKontroler() {
    }

    

    public static ServerKontroler getInstance() {
        if (instance == null) {
            instance = new ServerKontroler();
        }
        return instance;
    }

    public Administrator login(Administrator administrator) throws Exception {
        SOLogovanje so = new SOLogovanje();
        //
        so.execute(administrator);
        return so.getUlogovani();
    }

    public void dodajPutnik(Putnik putnik) throws Exception {
        SODodajPutnika so = new SODodajPutnika();
        so.execute(putnik);
    }

    public void dodajGrupa(Grupa grupa) throws Exception {
         SODodajGrupu so = new SODodajGrupu();
        so.execute(grupa);
    }
        

    public void deletePutnik(Putnik putnik) throws Exception {
        SOObrisiPutnika so= new SOObrisiPutnika();
        so.execute(putnik);
    }

    public void deleteGrupa(Grupa grupa) throws Exception {
        SOObrisiGrupu so = new SOObrisiGrupu();
        so.execute(grupa);
    }
    

    public void updatePutnik(Putnik putnik) throws Exception {
        SOIzmeniPutnika  so= new SOIzmeniPutnika();
        so.execute(putnik);
        
    }

    public void updateGrupa(Grupa grupa) throws Exception {
       SOIzmeniGrupu so= new SOIzmeniGrupu();
       so.execute(grupa);
    }
     

    public ArrayList<Putnik> getSvePutnike() throws Exception {
        SOUcitajPutnike so= new SOUcitajPutnike();
        so.execute(new Putnik());
      return so.getLista();
    }

    public ArrayList<Grupa> getSveGrupe() throws Exception {
        SOUcitajGrupe so =new SOUcitajGrupe();
        so.execute(new Grupa());
        return so.getLista();
    }

    public ArrayList<Mesto> getSvaMesta() throws Exception {
        SOUcitajMesta so = new SOUcitajMesta();
        so.execute(new Mesto());
        return so.getLista();
    }
    

    public ArrayList<Izlet> getSveIzlete() throws Exception {
        SOUcitajIzlete so= new SOUcitajIzlete();
        so.execute(new Izlet());
        return so.getLista();
    }

    public ArrayList<Vodic> getSveVodice() throws Exception {
        SOUcitajVodice so = new SOUcitajVodice();
        so.execute(new Vodic());
        return so.getLista();
    }

    public ArrayList<Clan> getSveClanove(Grupa grupa) throws Exception {
        SOUcitajClanove so = new SOUcitajClanove();
       Clan c= new Clan();
        c.setGrupa(grupa);
        
        so.execute(c);
        return so.getLista();
    }
}

