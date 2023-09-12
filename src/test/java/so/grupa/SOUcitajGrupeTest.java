package so.grupa;

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

import domen.Clan;
import domen.Grupa;
import domen.Izlet;
import domen.Mesto;
import domen.Putnik;

class SOUcitajGrupeTest {

	SOUcitajGrupe soUcitajGrupe;
	ArrayList<Grupa> grupe;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		soUcitajGrupe= new SOUcitajGrupe();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		soUcitajGrupe=null;
	}

	@Test
	public void testNeuspesnaValidacijaDrugaKlasa() {
		assertThrows(Exception.class, () -> soUcitajGrupe.execute(new Clan()));
	}

	@Test
	public void testNeuspesnaValidacijaNull() {
		assertThrows(Exception.class, () -> soUcitajGrupe.execute(null));
	}
 
	
	@Test
	public void testUspesnoUcitanaListaGrupa() {
		try {
			soUcitajGrupe.execute(new Grupa());
			 grupe = soUcitajGrupe.getLista();
            assertNotNull(grupe.size());
			assertEquals(2,grupe.size());

        System.out.println(grupe.get(1).getKapacitetGrupe());
        assertTrue(
                grupe.get(1).getIzlet().getIzletID().equals(2L) && 
     			grupe.get(1).getPrevoz().equals("Bus2") && 
     			grupe.get(1).getKapacitetGrupe()==30  &&
     			grupe.get(1).getVodic().getVodicID().equals(2L)  && 
     			grupe.get(1).getGrupaID().equals(1L));
		
	
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}
