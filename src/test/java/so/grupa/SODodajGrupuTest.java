package so.grupa;

import static org.junit.jupiter.api.Assertions.*;

import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import db.DBBroker;
import domen.Administrator;
import domen.Clan;
import domen.Grupa;
import domen.Izlet;
import domen.Mesto;
import domen.Putnik;
import domen.Vodic;
import so.clan.SOUcitajClanove;
import so.putnik.SODodajPutnika;
import so.putnik.SOUcitajPutnike;



class SODodajGrupuTest {
SODodajGrupu sododajGrupu;
ArrayList<Grupa> grupe;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		sododajGrupu= new SODodajGrupu();
	}

	@AfterEach
	void tearDown() throws Exception {
		sododajGrupu= null;
	}

	@Test
	public void testUspesnoDodataGrupa() {
		
			 grupe = vratiSveGrupeIzBaze();
		System.out.println("grupe su"+grupe.toArray());
		
		int brojGrupaPreDodavanja = grupe.size();

		Grupa g=new Grupa();

		SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
		Date d = null;
		try {
			d = sdf.parse("3.10.2023");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		g.setDatumPolaska(d);
		g.setKapacitetGrupe(25);
		g.setPrevoz("Bus1");

		Mesto m= new Mesto();
		m.setMestoID(1L);
		
        Izlet i = new Izlet();
        i.setMesto(m);
        i.setIzletID(1L);
		g.setIzlet(i);

	    Vodic v= new Vodic();
        v.setVodicID(1L);
        g.setVodic(v);
        
        Administrator a=new Administrator();
		a.setAdministratorID(1l);
		g.setAdministrator(a);
        
		ArrayList<domen.Clan> clanovi = new ArrayList<>();

		Putnik p1 = new Putnik(null,"Jovana","Trajkovic","jovana@gmail.com","069677920");

		Putnik p2 = new Putnik(null,"Pera","Peric","pera@gmail.com","0696891021");
		
		// doda u bazu putnika,id je autoinkrement
				dodajPutnika(p1);
				//sad nalazimo tog dodatog putnika u bazi i uzmemo mu id i postavljamo ga ovde 
				p1.setPutnikID(vratiIDPutnikaIzBaze(p1));
				
				dodajPutnika(p2);
				p2.setPutnikID(vratiIDPutnikaIzBaze(p2));

				domen.Clan clan1= new domen.Clan(g,1,"Spojiti sa Perom u busu",true,p1);
				clanovi.add(clan1);

				domen.Clan clan2= new domen.Clan(g,2,"",true,p2);
				clanovi.add(clan2);

				g.setClanovi(clanovi);

				//prvo dodajemo grupu
		        
		    	try {
					sododajGrupu.execute(g);
					System.out.println(g.getGrupaID());
					clan1.setGrupa(g);
					clan2.setGrupa(g);
				} catch (Exception e) {
					e.printStackTrace();
				}
				grupe = vratiSveGrupeIzBaze();
			
				System.out.println("Novi clanovi: "+grupe.toArray());
				
				ArrayList<Clan> sviClanoviGrupe= vratiSveClanoveGrupeIzBaze(clan1);
				System.out.println("Novei clanovi:" +sviClanoviGrupe.toArray());

				assertEquals(brojGrupaPreDodavanja+1,grupe.size());
				assertEquals(2,sviClanoviGrupe.size());
				assertTrue(clan1.getGrupa().getGrupaID().equals(sviClanoviGrupe.get(0).getGrupa().getGrupaID())
						&& clan1.getPutnik().getPutnikID().equals(sviClanoviGrupe.get(0).getPutnik().getPutnikID()));
				assertTrue(clan2.getGrupa().getGrupaID().equals(sviClanoviGrupe.get(1).getGrupa().getGrupaID())
						&& clan2.getPutnik().getPutnikID().equals(sviClanoviGrupe.get(1).getPutnik().getPutnikID()));

				obrisiDodatuGrupuIzBaze(g);
				obrisiDodatogPutnikaIzBaze(p1);
				obrisiDodatogPutnikaIzBaze(p2);
	}

	private void obrisiDodatogPutnikaIzBaze(Putnik pu) {
		ArrayList<Putnik> putnici= vratiSvePutnikeIzBaze();
		System.out.println("Uslo je u obrisi");
		for (Putnik putnik : putnici) {
			if(pu.getEmail().equals(putnik.getEmail()) &&
					pu.getBrojTelefona().equals(putnik.getBrojTelefona())){
				System.out.println("Naslo je "+pu.getImePutnika());
				pu.setPutnikID(putnik.getPutnikID());
			}
		}
		
	
		String upit = "Delete from putnik where putnikID=?";
		PreparedStatement ps;
		try {
			ps = DBBroker.getInstance().getConnection().prepareStatement(upit);
			ps.setLong(1, pu.getPutnikID());
			ps.execute(); 
			System.out.println("Obrisalo je");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private ArrayList<Putnik> vratiSvePutnikeIzBaze() {
		SOUcitajPutnike socuitajputnike=new SOUcitajPutnike();
		try {
			socuitajputnike.execute(new Putnik());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Putnik> putnici = socuitajputnike.getLista();
		return putnici;
	}

	private void obrisiDodatuGrupuIzBaze(Grupa g) {
	   String upit = "Delete from grupa where grupaID=?";
		PreparedStatement ps;
		try {
			ps = DBBroker.getInstance().getConnection().prepareStatement(upit);
			ps.setLong(1, g.getGrupaID());
			ps.execute(); 
			System.out.println("Obrisalo je");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private ArrayList<Clan> vratiSveClanoveGrupeIzBaze(Clan clan) {
		try {
			SOUcitajClanove so = new SOUcitajClanove();
			  so.execute(clan);
			return so.getLista();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private ArrayList<Grupa> vratiSveGrupeIzBaze() {
		try {
			SOUcitajGrupe so = new SOUcitajGrupe();
			  so.execute(new Grupa());
			return so.getLista();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	

	private Long vratiIDPutnikaIzBaze(Putnik p1) {
		ArrayList<Putnik> putnici= vratiSvePutnikeIzBaze();
		System.out.println("Uslo je u obrisi");
		for (Putnik putnik : putnici) {
			if(p1.getEmail().equals(putnik.getEmail()) &&
					p1.getBrojTelefona().equals(putnik.getBrojTelefona())){
				System.out.println("Naslo je "+p1.getImePutnika());
				return putnik.getPutnikID();
			}
		}
		return null;
	}

	private void dodajPutnika(Putnik p1) {
		SODodajPutnika so= new SODodajPutnika();
		try {
			so.execute(p1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
