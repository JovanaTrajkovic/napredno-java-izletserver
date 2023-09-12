package so.vodic;

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

import domen.Vodic;




class SOUcitajVodiceTest {

	
	private ArrayList<Vodic> vodici;
	private SOUcitajVodice soucitajVodice;
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		soucitajVodice= new SOUcitajVodice();
	}

	@AfterEach
	void tearDown() throws Exception {
		soucitajVodice=null;
	}


	@Test
	public void testNeuspesnaValidacijaDrugaKlasa() {
		assertThrows(Exception.class, () -> soucitajVodice.execute(new domen.Clan()));
	}

	@Test
	public void testNeuspesnaValidacijaNull() {
		assertThrows(Exception.class, () -> soucitajVodice.execute(null));
	}

	@Test
	public void testUspesnoUcitanaListaVodica() {
		try {
			soucitajVodice.execute(new Vodic());
			 vodici = soucitajVodice.getLista();

			assertEquals(3, vodici.size());
			assertTrue(vodici.get(0).getImeVodica().equalsIgnoreCase("marija") && 
					vodici.get(0).getPrezimeVodica().equalsIgnoreCase("maric") && 
					vodici.get(0).getBrojTelefona().equals("0611234595") && 
					vodici.get(0).getVodicID().equals(1L));
			
			assertTrue(vodici.get(1).getImeVodica().equalsIgnoreCase("zika") && 
					vodici.get(1).getPrezimeVodica().equalsIgnoreCase("zikic") && 
					vodici.get(1).getBrojTelefona().equals("0611234594")  && 
					vodici.get(1).getVodicID().equals(2L));
			
			assertTrue(vodici.get(2).getImeVodica().equalsIgnoreCase("darko") && 
					vodici.get(2).getPrezimeVodica().equalsIgnoreCase("daric") &&
					vodici.get(2).getBrojTelefona().equals("0611234593")  && 
					vodici.get(2).getVodicID().equals(3L));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
