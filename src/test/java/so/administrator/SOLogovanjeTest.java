package so.administrator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domen.Administrator;

import so.administrator.*;

public class SOLogovanjeTest {
	
	 SOLogovanje sologovanje;
	private Administrator admin;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		
	}

	@BeforeEach
	void setUp() throws Exception {
		sologovanje=new SOLogovanje();
	}

	@AfterEach
	void tearDown() throws Exception {
		sologovanje=null;
	}

	@Test
	public void testNeuspesnaValidacijaDrugaKlasa() {
		assertThrows(Exception.class, () -> sologovanje.execute(new domen.Clan()));
	}
	
	@Test
	public void testNeuspesnaValidacijaNull() {
		assertThrows(Exception.class, () ->sologovanje.execute(null));
	}
	
	@Test
	public void testLoginNePostojiAdministrator() {
		Administrator a = new Administrator();
		a.setUsername("joca1");
		a.setPassword("joca1");
	
		assertThrows(Exception.class, () -> sologovanje.execute(a));
	}
	
	
	@Test
	public void testLoginUspesnaRegistracija() {
		Administrator a = new Administrator();
		a.setUsername("joca");
		a.setPassword("joca12345");
		
		try {
			sologovanje.execute(a);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Administrator admin1=sologovanje.getUlogovani();
		
		
		assertEquals(a.getUsername(), admin1.getUsername());
		assertEquals(a.getPassword(),admin1.getPassword());
		
		
	
	}
	
	

}
