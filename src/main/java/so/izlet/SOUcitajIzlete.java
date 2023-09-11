package so.izlet;
import db.DBBroker;
import domen.Izlet;
import domen.OpstiDomenskiObjekat;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author Jovana
 */
public class SOUcitajIzlete extends OpstaSistemskaOperacija {
     private ArrayList<Izlet> izleti;

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if(! (odo instanceof Izlet)){
           throw new Exception("Prosledjeni objekat nije instanca klase Izlet!");
        }
    }

    @Override
    protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
    ArrayList<OpstiDomenskiObjekat>  lista=DBBroker.getInstance().select(odo);
    izleti= (ArrayList<Izlet>) (ArrayList<?> )lista;
   }
    
    public ArrayList<Izlet> getLista(){
        return izleti;
    }

}
