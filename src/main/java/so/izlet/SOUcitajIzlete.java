package so.izlet;
import db.DBBroker;
import domen.Izlet;
import domen.OpstiDomenskiObjekat;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;

/**
 * Predstavlja sistemsku operaciju za ucitavanje svih izleta iz baze podataka.
 * Implementira apstraktne metode iz apstraktne klase OpstaSistemskaOperacija
 * 
 * @author Jovana
 */

public class SOUcitajIzlete extends OpstaSistemskaOperacija {
	
	
	/**
 	 * Lista svih izleta
 	 */

     private ArrayList<Izlet> izleti;

     
     
     /**
      * @throws Exception ako prosledjeni objekat nije instanca klase Izlet
      */

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if(! (odo instanceof Izlet)){
           throw new Exception("Prosledjeni objekat nije instanca klase Izlet!");
        }
    }

    
    /**
	 * Poziva brokera baze podataka da izvrsi SELECT upit i rezultat upita postavlja u
	 * listu izleti.
	 */
    @Override
    protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
    ArrayList<OpstiDomenskiObjekat>  lista=DBBroker.getInstance().select(odo);
    izleti= (ArrayList<Izlet>) (ArrayList<?> )lista;
   }
    
    
    
    /**
     * Vraca listu izleta
     * 
     * @return izleti 
     */

    public ArrayList<Izlet> getLista(){
        return izleti;
    }

}
