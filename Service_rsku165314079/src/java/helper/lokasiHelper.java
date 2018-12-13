/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojos.Location;
import util.TobbyHibernateUtil;

/**
 *
 * @author user
 */
public class lokasiHelper {

    public lokasiHelper() {

    }

    public List<Location> bacaSemuaLokasi() {
        List<Location> list = null;
        Session session = TobbyHibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Location l");
        list = q.list();
        tx.commit();
        session.close();
        return list;
    }
    public List<Location> getLocation(){
        Session session = TobbyHibernateUtil.getSessionFactory().openSession();
        String query = "from Location";
        Query q = session.createQuery(query);
        List<Location> list = q.list();
        return list;
    }
    public void addNewLocation(double lat, double lng, String name) {
        Session session = TobbyHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Location location = new Location(lat, lng, name);
        session.saveOrUpdate(location);
        transaction.commit();
        session.close();
    }
}
