package so.mesto;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domen.Mesto;
import domen.Putnik;
import domen.Vodic;

class SOUcitajMestaTest {
	private ArrayList<Mesto> mesta;
    private  SOUcitajMesta soUcitajMesta;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		soUcitajMesta=new SOUcitajMesta();
	}

	@AfterEach
	void tearDown() throws Exception {
		soUcitajMesta=null;
	}

	@Test
	public void testNeuspesnaValidacijaDrugaKlasa() {
		assertThrows(Exception.class, () -> soUcitajMesta.execute(new Putnik()));  ;
	}

	@Test
	public void testNeuspesnaValidacijaNull() {
		assertThrows(Exception.class, () -> soUcitajMesta.execute(null));
	}

	@Test
	public void testUspesnoUcitanaListaMesta() {
		try {
			soUcitajMesta.execute(new Mesto());
			mesta = soUcitajMesta.getLista();

			assertEquals(5, mesta.size());
			
			
			assertTrue(mesta.get(0).getMestoID().equals(1L)&&
					mesta.get(0).getNazivMesta().equals("Tara"));
			
			assertTrue(mesta.get(1).getMestoID().equals(2L)&&
					mesta.get(1).getNazivMesta().equals("Golubacka tvrdjava"));
			
			assertTrue(mesta.get(2).getMestoID().equals(3L)&&
					mesta.get(2).getNazivMesta().equals("Banja zdrelo"));
			
			assertTrue(mesta.get(3).getMestoID().equals(4L)&&
					mesta.get(3).getNazivMesta().equals("Felix Romuliana"));
			
			assertTrue(mesta.get(4).getMestoID().equals(5L)&&
					mesta.get(4).getNazivMesta().equals("Manastir Ravanica"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
