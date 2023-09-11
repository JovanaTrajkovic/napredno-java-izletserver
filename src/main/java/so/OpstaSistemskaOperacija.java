package so;

import domen.Administrator;

import db.DBBroker;
import domen.OpstiDomenskiObjekat;
import java.sql.SQLException;
/**
 *
 * @author Jovana
 */
public abstract class OpstaSistemskaOperacija {
    protected abstract void validate(OpstiDomenskiObjekat odo) throws Exception;
    protected abstract void executeOperation(OpstiDomenskiObjekat odo) throws Exception;

    public void execute(OpstiDomenskiObjekat odo) throws Exception {
        try {
            validate(odo);
            executeOperation(odo);
            commit();
        } catch (Exception e) {
            rollback();
            throw e;
        }
    }

    public void commit() throws SQLException {
       DBBroker.getInstance().getConnection().commit();
    }

    public void rollback() throws SQLException {
        DBBroker.getInstance().getConnection().rollback();
    }
    
}
