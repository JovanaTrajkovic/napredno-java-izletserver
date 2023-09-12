package so.putnik;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domen.Administrator;
import domen.Putnik;




class SOIzmeniPutnikaTest {
SOIzmeniPutnika soizmeniPutnika;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		soizmeniPutnika=new SOIzmeniPutnika();
	}

	@AfterEach
	void tearDown() throws Exception {
		soizmeniPutnika=null;
	}


	@Test
	public void testNeuspesnaValidacijaDrugaKlasa() {
		assertThrows(Exception.class, () -> soizmeniPutnika.execute(new Administrator()));
	}
	@Test
	public void testNeuspesnaValidacijaNull() {
		assertThrows(Exception.class, () -> soizmeniPutnika.execute(null));
	}
	
	@Test
	public void testNeuspesnaValidacijaBrojTelefonaNull() {
		
				Putnik p= new Putnik(50L,"Marko","Markovic","marko@gmail.com",null);
				assertThrows(Exception.class, () ->soizmeniPutnika.validate(p));
	}
	
	@Test
	public void testNeuspesnaValidacijaBrojTelefonaViseod10Cifara() {
		//11 cifara
				Putnik p= new Putnik(50L,"Marko","Markovic","marko@gmail.com","06967792100686755");
				assertThrows(Exception.class, () -> soizmeniPutnika.validate(p));
	}
	@Test
	public void testNeuspesnaValidacijaBrojTelefonaManjeOd9Cifara() {
		//8 cifara
				Putnik p1= new Putnik(50L,"Marko","Markovic","marko@gmail.com","0696");
				assertThrows(Exception.class, () ->soizmeniPutnika.validate(p1));
	}
	
	@Test
	public void testNeuspesnaValidacijaBrojTelefonaImaSlova() {
		//cifre i slova
				Putnik p3= new Putnik(50L,"Marko","Markovic","marko@gmail.com","06967792a1");
				assertThrows(Exception.class, () -> soizmeniPutnika.validate(p3));
	}
	
	@Test
	public void testNeuspesnaValidacijaImeNull() {
		//2 slova
		Putnik p= new Putnik(50L,null,"Markovic","marko@gmail.com","0696779210");
		assertThrows(Exception.class, () -> soizmeniPutnika.validate(p));
	}
	
	@Test
	public void testNeuspesnaValidacijaImeManjeod3Karaktera() {
		//2 slova
		Putnik p= new Putnik(50L,"Ma","Markovic","marko@gmail.com","0696779210");
		assertThrows(Exception.class, () -> soizmeniPutnika.validate(p));
	}
	
	@Test
	public void testNeuspesnaValidacijaImeImaBrojUSebi() {
		//cifre i slova
		Putnik p1= new Putnik(50L,"Ma3","Markovic","marko@gmail.com","069677920");
		assertThrows(Exception.class, () -> soizmeniPutnika.validate(p1));
	}
	
	@Test
	public void testNeuspesnaValidacijaPrezimeNull() {
		//2 slova
				Putnik p= new Putnik(50L,"Marko",null,"marko@gmail.com","0696779210");
				assertThrows(Exception.class, () -> soizmeniPutnika.validate(p));
	}
	
	@Test
	public void testNeuspesnaValidacijaPrezimeManjeod3Karaktera() {
		//2 slova
				Putnik p= new Putnik(50L,"Marko","Ma","marko@gmail.com","0696779210");
				assertThrows(Exception.class, () -> soizmeniPutnika.validate(p));
	}
	
	@Test
	public void testNeuspesnaValidacijaPrezimeImaBrojUSebi() {
		//cifre i slova
				Putnik p1= new Putnik(50L,"Marko","Ma3","marko@gmail.com","069677920");
				assertThrows(Exception.class, () -> soizmeniPutnika.validate(p1));
	}
	
	@Test
	public void testNeuspesnaValidacijaEmailNull() {
		
				Putnik p= new Putnik(50L,"Marko","Markic",null,"0696779210");
				assertThrows(Exception.class, () -> soizmeniPutnika.validate(p));
	}
	
	@Test
	public void testNeuspesnaValidacijaEmailManjeod3Karaktera() {
		
				Putnik p= new Putnik(50L,"Marko","Markic","om","0696779210");
				assertThrows(Exception.class, () -> soizmeniPutnika.validate(p));
	}
	
	

/////////
	
	@Test
	void testUspesnoIzmenjenPutnik() {
		

	      Putnik p= new Putnik(null,"Lav","Zivojinovic","zikica@gmail.com","0676775690");
	      
	     	dodajPutnika(p);
	     	ArrayList<Putnik> putnici=vratiSvePutnikeIzBaze();
	     	for (Putnik putnik : putnici) {
	     		if(p.getEmail().equals(putnik.getEmail()) &&
						p.getBrojTelefona().equals(putnik.getBrojTelefona())){
					p.setPutnikID(putnik.getPutnikID());
				}
			}
			System.out.println(p.getPutnikID());
			
			p.setEmail("lav@gmail.com");
			p.setBrojTelefona("069997928");
			p.setPrezimePutnika("Lavic");
			
			
			
			try {
				soizmeniPutnika.execute(p);
			} catch (Exception e) {
				e.printStackTrace();
			}
			putnici=vratiSvePutnikeIzBaze();
	
		
			for (Putnik putnik:putnici) {
				if (putnik.getPutnikID()==p.getPutnikID()) {
				
					assertTrue(putnik.getEmail().equals(p.getEmail()));
					assertTrue(putnik.getBrojTelefona().equals(p.getBrojTelefona()));
					assertTrue(putnik.getPrezimePutnika().equals(p.getPrezimePutnika()));
					break;
				}
			}
         obrisiDodatogPutnikaIzBaze(p);
	
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
	
	private void obrisiDodatogPutnikaIzBaze(Putnik p) {
		SOObrisiPutnika so= new SOObrisiPutnika();
		try {
			so.execute(p);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void dodajPutnika(Putnik p) {
		try {
			new SODodajPutnika().execute(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
