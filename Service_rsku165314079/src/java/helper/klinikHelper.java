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
import pojos.Klinik;
import util.TobbyHibernateUtil;

/**
 *
 * @author user
 */
public class klinikHelper {

    public klinikHelper() {
    }

    public List<Klinik> getKlinik() {
        Session session = TobbyHibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        List<Klinik> list = null;
        Query q = session.createQuery("from Klinik k");
        list = q.list();
        tx.commit();
        session.close();
        return list;

    }

    public void addNewKlinik(String id, String nama, String spesialis) {
        Session session = TobbyHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Klinik klinik = new Klinik(id, nama, spesialis);
        session.saveOrUpdate(klinik);
        transaction.commit();
        session.close();
    }

    public Klinik cariKlinik(String nama) {
        Session session = TobbyHibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Klinik k where k.nama=nm");
        q.setParameter("nm", q);
        List<Klinik> list = null;
        list = q.list();
        tx.commit();
        session.close();

        if (list.size() > 0) {
            return list.get(0);

        } else {
            return null;
        }
    }

    public static String toJson() {
        klinikHelper helper = new klinikHelper();
        List<Klinik> list = helper.getKlinik();
        String result = "[";
        for (int i = 0; i < list.size(); i++) {
            if (i < (list.size() - 1)) {
                result += list.get(i).toJson() + ", \n";
            } else {
                result += list.get(i).toJson();
            }

        }
        result += "]";
        return result;
    }
}
