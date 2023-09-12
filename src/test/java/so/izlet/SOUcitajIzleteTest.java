package so.izlet;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domen.Izlet;
import domen.Kategorija;
import domen.Mesto;
import domen.Putnik;

class SOUcitajIzleteTest {
private SOUcitajIzlete soUcitajIzlete;
private ArrayList<Izlet> izleti;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		soUcitajIzlete= new SOUcitajIzlete();
	}

	@AfterEach
	void tearDown() throws Exception {
		soUcitajIzlete=null;
	}

	@Test
	public void testNeuspesnaValidacijaDrugaKlasa() {
		assertThrows(Exception.class, () -> soUcitajIzlete.execute(new Putnik()));  ;
	}

	@Test
	public void testNeuspesnaValidacijaNull() {
		assertThrows(Exception.class, () -> soUcitajIzlete.execute(null));
	}

	@Test
	public void testUspesnoUcitanaListaIzleta() {
		try {
			soUcitajIzlete.execute(new Izlet());
			izleti = soUcitajIzlete.getLista();
			

			assertEquals(6,izleti.size());
			
			
					

	        assertTrue(izleti.get(5).getIzletID().equals(6L) &&
					izleti.get(5).getKategorija().equals(Kategorija.VanSezone) &&
					izleti.get(5).getMesto().getNazivMesta().equals("Manastir Ravanica")  ); 
			
			assertTrue(izleti.get(4).getIzletID().equals(5L) &&
					izleti.get(4).getKategorija().equals(Kategorija.Sezona) && 
                    izleti.get(4).getMesto().getNazivMesta().equals("Felix Romuliana") ); 
			
	
			
			assertTrue(izleti.get(0).getIzletID().equals(1L) &&
					izleti.get(0).getKategorija().equals(Kategorija.Sezona) && 
                    izleti.get(0).getMesto().getNazivMesta().equals("Tara") ); 
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

