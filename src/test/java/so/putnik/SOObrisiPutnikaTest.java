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



class SOObrisiPutnikaTest {
	 SOObrisiPutnika soObrisiPutnika;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		soObrisiPutnika= new SOObrisiPutnika();
	}

	@AfterEach
	void tearDown() throws Exception {
		soObrisiPutnika=null;
	}

	@Test
	public void testNeuspesnaValidacijaDrugaKlasa() {
		assertThrows(Exception.class, () ->  soObrisiPutnika.execute(new Administrator()));
	}
	@Test
	public void testNeuspesnaValidacijaNull() {
		assertThrows(Exception.class, () -> soObrisiPutnika.execute(null));
	}
	
	@Test
	void testUspesnoObrisanClan() {
		
		Putnik p= new Putnik(null,"Lea","Lekic","lea@gmail.com","0686543123");
	      
     	dodajPutnika(p);
     	ArrayList<Putnik> putnici=vratiSvePutnikeIzBaze();
     	int brojPreBrisanja=putnici.size();
     	for (Putnik putnik : putnici) {
			if(p.getEmail().equals(putnik.getEmail()) &&
					p.getBrojTelefona().equals(putnik.getBrojTelefona())){
				p.setPutnikID(putnik.getPutnikID());
			}
		}
		System.out.println(p.getPutnikID());
		
		try {
			soObrisiPutnika.execute(p);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		putnici=vratiSvePutnikeIzBaze();
		
		assertEquals(brojPreBrisanja - 1, putnici.size());
		assertFalse(putnici.contains(p));
	}
	
	private void dodajPutnika(Putnik p) {
		try {
			new SODodajPutnika().execute(p);
		} catch (Exception e) {
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
