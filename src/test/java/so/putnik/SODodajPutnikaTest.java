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
		
		@Test
		public void testNeuspesnaValidacijaBrojTelefonaNull() {
			
					Putnik p= new Putnik(50L,"Marko","Markovic","marko@gmail.com",null);
					assertThrows(Exception.class, () -> sododajputnika.validate(p));
		}
		
		@Test
		public void testNeuspesnaValidacijaBrojTelefonaViseod10Cifara() {
			//11 cifara
					Putnik p= new Putnik(50L,"Marko","Markovic","marko@gmail.com","06967792100686755");
					assertThrows(Exception.class, () -> sododajputnika.validate(p));
		}
		@Test
		public void testNeuspesnaValidacijaBrojTelefonaManjeOd9Cifara() {
			//8 cifara
					Putnik p1= new Putnik(50L,"Marko","Markovic","marko@gmail.com","0696");
					assertThrows(Exception.class, () -> sododajputnika.validate(p1));
		}
		
		@Test
		public void testNeuspesnaValidacijaBrojTelefonaImaSlova() {
			//cifre i slova
					Putnik p3= new Putnik(50L,"Marko","Markovic","marko@gmail.com","06967792a1");
					assertThrows(Exception.class, () -> sododajputnika.validate(p3));
		}
		
		@Test
		public void testNeuspesnaValidacijaImeNull() {
			//2 slova
			Putnik p= new Putnik(50L,null,"Markovic","marko@gmail.com","0696779210");
			assertThrows(Exception.class, () -> sododajputnika.validate(p));
		}
		
		@Test
		public void testNeuspesnaValidacijaImeManjeod3Karaktera() {
			//2 slova
			Putnik p= new Putnik(50L,"Ma","Markovic","marko@gmail.com","0696779210");
			assertThrows(Exception.class, () -> sododajputnika.validate(p));
		}
		
		@Test
		public void testNeuspesnaValidacijaImeImaBrojUSebi() {
			//cifre i slova
			Putnik p1= new Putnik(50L,"Ma3","Markovic","marko@gmail.com","069677920");
			assertThrows(Exception.class, () -> sododajputnika.validate(p1));
		}
		
		@Test
		public void testNeuspesnaValidacijaPrezimeNull() {
			//2 slova
					Putnik p= new Putnik(50L,"Marko",null,"marko@gmail.com","0696779210");
					assertThrows(Exception.class, () -> sododajputnika.validate(p));
		}
		
		@Test
		public void testNeuspesnaValidacijaPrezimeManjeod3Karaktera() {
			//2 slova
					Putnik p= new Putnik(50L,"Marko","Ma","marko@gmail.com","0696779210");
					assertThrows(Exception.class, () -> sododajputnika.validate(p));
		}
		
		@Test
		public void testNeuspesnaValidacijaPrezimeImaBrojUSebi() {
			//cifre i slova
					Putnik p1= new Putnik(50L,"Marko","Ma3","marko@gmail.com","069677920");
					assertThrows(Exception.class, () -> sododajputnika.validate(p1));
		}
		
		@Test
		public void testNeuspesnaValidacijaEmailNull() {
			
					Putnik p= new Putnik(50L,"Marko","Markic",null,"0696779210");
					assertThrows(Exception.class, () -> sododajputnika.validate(p));
		}
		
		@Test
		public void testNeuspesnaValidacijaEmailManjeod3Karaktera() {
			
					Putnik p= new Putnik(50L,"Marko","Markic","om","0696779210");
					assertThrows(Exception.class, () -> sododajputnika.validate(p));
		}
		


		
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
			
			/*try {
				new SOObrisiPutnika().execute(p);
			} catch (Exception e) {
				e.printStackTrace();
			}*/
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
