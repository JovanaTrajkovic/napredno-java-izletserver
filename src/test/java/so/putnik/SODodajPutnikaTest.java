package so.putnik;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import db.DBBroker;
import domen.Administrator;
import domen.Putnik;

public class SODodajPutnikaTest {
	   private SODodajPutnika sododajputnika;
		@BeforeAll
		static void setUpBeforeClass() throws Exception {
		}

		@AfterAll
		static void tearDownAfterClass() throws Exception {
		}

		@BeforeEach
		void setUp() throws Exception {
			sododajputnika=new SODodajPutnika();
		}

		@AfterEach
		void tearDown() throws Exception {
			sododajputnika=null;
		}


		@Test
		public void testNeuspesnaValidacijaDrugaKlasa() {
			assertThrows(Exception.class, () -> sododajputnika.execute(new Administrator()));
		}
		@Test
		public void testNeuspesnaValidacijaNull() {
			assertThrows(Exception.class, () -> sododajputnika.execute(null));
		}
		
		
		
		////
		
		@Test
		void testUspesnoDodatPutnik() {
			
	      Putnik p= new Putnik(null,"Marko","Markovic","marko@gmail.com","069677928");
	      p.setBrojTelefona("069677928");
		try {
			
				sododajputnika.execute(p);
			} catch (Exception e1) {
				System.out.println("Greska");
				e1.printStackTrace();
			}
	ArrayList<Putnik> putnici=vratiSvePutnikeIzBaze();

	for (Putnik putnik : putnici) {
		if(p.getEmail().equals(putnik.getEmail()) &&
			p.getBrojTelefona().equals(putnik.getBrojTelefona())){
			p.setPutnikID(putnik.getPutnikID());
		}
	}
	System.out.println(p.getPutnikID());

	assertNotEquals(p.getPutnikID(),null);
	assertEquals(p.getEmail(),"marko@gmail.com");
	obrisiDodatogPutnikaIzBaze(p);
		
		}

		private void obrisiDodatogPutnikaIzBaze(Putnik p) {
			ArrayList<Putnik> putnici= vratiSvePutnikeIzBaze();
			System.out.println("Uslo je u obrisi");
			for (Putnik putnik : putnici) {
				if(p.getEmail().equals(putnik.getEmail()) &&
						p.getBrojTelefona().equals(putnik.getBrojTelefona())){
					System.out.println("Naslo je "+p.getImePutnika());
					p.setPutnikID(putnik.getPutnikID());
				}
			}
			String upit = "Delete from putnik where putnikID=?";
			PreparedStatement ps;
			try {
				ps = DBBroker.getInstance().getConnection().prepareStatement(upit);
				ps.setLong(1, p.getPutnikID());
				ps.execute(); 
				System.out.println("Obrisalo je");
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

		private ArrayList<Putnik> vratiSvePutnikeIzBaze() {
			try {
				SOUcitajPutnike so = new SOUcitajPutnike();
				  so.execute(new Putnik());
				return so.getLista();
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}

	}
