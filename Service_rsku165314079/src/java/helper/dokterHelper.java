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
import pojos.Dokter;
import util.TobbyHibernateUtil;

/**
 *
 * @author user
 */
public class dokterHelper {

    public dokterHelper() {

    }

    public List<Dokter> bacaSemuaLokasi() {
        List<Dokter> list = null;
        Session session = TobbyHibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Dokter l");
        list = q.list();
        tx.commit();
        session.close();
        return list;

    }

    public void addNewDokter(String nama, String spesialis) {
        Session session = TobbyHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        Dokter dokter = new Dokter(nama, spesialis);
        session.saveOrUpdate(dokter);
        transaction.commit();
        session.close();
    }
}
