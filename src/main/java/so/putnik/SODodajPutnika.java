package so.putnik;

import db.DBBroker;
import domen.OpstiDomenskiObjekat;
import domen.Putnik;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author Jovana
 */
public class SODodajPutnika extends OpstaSistemskaOperacija{

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
        if (!(odo instanceof Putnik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Putnik");
        }

       Putnik p= (Putnik) odo;
      
         if(p.getImePutnika().length()<3) {
             throw new Exception("Ime mora imati makar 3 slova.");
         }
          char[] ime= p.getImePutnika().toCharArray();
           for(Character c:ime){
            if(!Character.isLetter(c)){
                throw new Exception("Ime mora sadrzati samo slova.");
            }
           }
          
           
           if(p.getPrezimePutnika().length()<3) {
             throw new Exception("Prezime mora imati makar 3 slova.");
         }
           char[] prezime= p.getPrezimePutnika().toCharArray();
           for(Character c:prezime){
            if(!Character.isLetter(c)){
                throw new Exception("Prezime mora sadrzati samo slova.");
            }
           }
           
           
        if (p.getBrojTelefona().length() != 10
                || p.getBrojTelefona().length() != 9) {
            throw new Exception("Broj telefona mora imati 9 ili 10 cifara.");
        }
        char[] brtel = p.getBrojTelefona().toCharArray();
        for (Character c : brtel) {
            if (!Character.isDigit(c)) {
                throw new Exception("Broj telefona mora sadrzati samo cifre.");
            }
        }

        ArrayList<Putnik> putnici = (ArrayList<Putnik>) (ArrayList<?>) DBBroker.getInstance().select(odo);

        for (Putnik putnik : putnici) {
            if (putnik.getEmail().equals(p.getEmail())) {
                throw new Exception("Vec postoji putnik sa tim emailom!");
            }
            if (putnik.getBrojTelefona().equals(p.getBrojTelefona())) {
                throw new Exception("Vec postoji putnik sa tim brojem telefona!");
            }
        }
        
        
    }

    @Override
    protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
         DBBroker.getInstance().insert(odo);
    }
    
}
