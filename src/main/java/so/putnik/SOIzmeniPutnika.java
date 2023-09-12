package so.putnik;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import db.DBBroker;
import domen.OpstiDomenskiObjekat;
import domen.Putnik;
import java.util.ArrayList;
import so.OpstaSistemskaOperacija;

/**
 *
 * @author Jovana
 */
public class SOIzmeniPutnika extends OpstaSistemskaOperacija {

    @Override
    protected void validate(OpstiDomenskiObjekat odo) throws Exception {
    
    	if (!(odo instanceof Putnik)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Putnik");
        }

       Putnik p= (Putnik) odo;
      
       if (p.getImePutnika()==null) {
           throw new Exception("Ime ne sme biti null");
       }
       
         if(p.getImePutnika().length()<3) {
             throw new Exception("Ime mora imati makar 3 slova.");
         }
          char[] ime= p.getImePutnika().toCharArray();
           for(Character c:ime){
            if(!Character.isLetter(c)){
                throw new Exception("Ime mora sadrzati samo slova.");
            }
           }
          /////////////////////////////////////////////////////////
           if (p.getPrezimePutnika()==null) {
               throw new Exception("Prezime ne sme biti null");
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
           
           ///////////////////////////////////
           if (p.getBrojTelefona()==null) {
               throw new Exception("Broj telefona ne sme biti null");
           }
        if (p.getBrojTelefona().length() <9 || p.getBrojTelefona().length()>10 ) {
            throw new Exception("Broj telefona mora imati 9 ili 10 cifara.");
        }
        char[] brtel = p.getBrojTelefona().toCharArray();
        for (Character c : brtel) {
            if (!Character.isDigit(c)) {
                throw new Exception("Broj telefona mora sadrzati samo cifre.");
            }
        }

        
        ////////////////////////////////////////////////////////
        if (p.getEmail()==null) {
            throw new Exception("Email ne sme biti null");
        }
        if (p.getEmail().length()<3) {
            throw new Exception("Email ne sme biti kraci od 3 karaktera");
        }
        
        
      
        
        
    }

    @Override
    protected void executeOperation(OpstiDomenskiObjekat odo) throws Exception {
          DBBroker.getInstance().update(odo);
    }
    
}
