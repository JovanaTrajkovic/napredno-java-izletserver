package so.putnik;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domen.Putnik;
import domen.Vodic;

class SOUcitajPutnikeTest {
	SOUcitajPutnike socuitajputnike;
	ArrayList<Putnik> putnici;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		socuitajputnike= new SOUcitajPutnike();
	}

	@AfterEach
	void tearDown() throws Exception {
		socuitajputnike=null;
	}


	@Test
	public void testNeuspesnaValidacijaDrugaKlasa() {
		assertThrows(Exception.class, () -> socuitajputnike.execute(new domen.Clan()));
	}

	@Test
	public void testNeuspesnaValidacijaNull() {
		assertThrows(Exception.class, () -> socuitajputnike.execute(null));
	}

	@Test
	public void testUspesnoUcitanaListaPutnika() {
		try {
			socuitajputnike.execute(new Putnik());
			 putnici = socuitajputnike.getLista();
            assertNotNull(putnici.size());
			assertEquals(5,putnici.size());
			
			
			
			assertTrue(putnici.get(0).getEmail().equals("milan@gmail.com") && 
					putnici.get(0).getBrojTelefona().equals("0698023243") );
			
			assertTrue(putnici.get(1).getEmail().equals("anka@gmail.com") && 
					putnici.get(1).getBrojTelefona().equals("063858678") );

			assertTrue(putnici.get(3).getEmail().equals("aleksandra@gmail.com") && 
					putnici.get(3).getBrojTelefona().equals("063858671") );
			
			assertTrue(putnici.get(2).getEmail().equals("tamara@gmail.com") && 
					putnici.get(2).getBrojTelefona().equals("063858670") );
			
		assertTrue(putnici.get(4).getEmail().equals("katarina@gmail.com") && 
					putnici.get(4).getBrojTelefona().equals("063858679") );
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
