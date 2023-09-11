package so.mesto;

import db.DBBroker;
import domen.Mesto;
import domen.OpstiDomenskiObjekat;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author Jovana
 */
public class SOUcitajMesta extends OpstaSistemskaOperacija {
    private ArrayList<Mesto> mesta;

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if(!(odo instanceof Mesto))
        throw new Exception("Prosledjeni objekat nije instanca klase Mesto!");
    }

    @Override
    protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
    ArrayList<OpstiDomenskiObjekat>  lista=DBBroker.getInstance().select(odo);
    mesta= (ArrayList<Mesto>) (ArrayList<?> ) lista;
   }
    
    public ArrayList<Mesto> getLista(){
        return mesta;
    }
}
