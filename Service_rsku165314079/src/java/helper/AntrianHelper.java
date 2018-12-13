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
import pojos.Antrian;
import util.TobbyHibernateUtil;
import java.util.Date;

/**
 *
 * @author user
 */
public class AntrianHelper {

    public AntrianHelper() {

    }

    public List<Antrian> bacaSemuaAntrian() {
        List<Antrian> list = null;
        Session session = TobbyHibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Antrian a");
        list = q.list();
        tx.commit();
        session.close();
        return list;

    }

    public static String toJson() {
        AntrianHelper helper = new AntrianHelper();
        List<Antrian> list = helper.bacaSemuaAntrian();
        String result = "[";
        for (int i = 0; i < list.size(); i++) {
            if (i < list.size() - 1) {
                result = result + list.get(i).toJson() + ",\n";
            } else {
                result = result + list.get(i).toJson() + "\n";
            }
        }
        result = result + "]";
        return result;
    }

    public void addNewAntrian(Long id, Date tanggal, String noRm, String nama, String alamat, String namaKlinik) {
        Session session = TobbyHibernateUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();

        Antrian antrian = new Antrian(id, tanggal, noRm, nama, alamat, namaKlinik);
        session.saveOrUpdate(antrian);
        transaction.commit();
        session.close();

    }
}
