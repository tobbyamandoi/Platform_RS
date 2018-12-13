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
import pojos.Pasien;
import util.TobbyHibernateUtil;
import java.util.Date;

/**
 *
 * @author user
 */
public class pasienHelper {

    public pasienHelper() {

    }

    public List<Pasien> bacaSemuaPasien() {
        List<Pasien> list = null;
        Session session = TobbyHibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Query q = session.createQuery("from Pasien l");
        list = q.list();
        tx.commit();
        session.close();
        return list;

    }

    public Pasien cariPasien(String nik) {
        // Create session
        Session session = TobbyHibernateUtil.getSessionFactory().openSession();
        // Create String query
        String query = "from Pasien u where u.nik=:nik";
        Query q = session.createQuery(query);
        q.setParameter("nik", nik);
        // siapkan list,hasil pencarian dan panggil pencarian
        List<Pasien> list = q.list();
        // tutuup session
        session.close();

        if (list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }

    public void addNewPasien(
            String noRm,
            String nama,
            String alamat,
            String nik,
            Date tanggalLahir,
            String kelamin
    ) {
        Session session = TobbyHibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        Pasien pasien = new Pasien(noRm, nama, alamat, noRm, tanggalLahir, kelamin);
        session.saveOrUpdate(pasien);
        tx.commit();
        session.close();
    }
}
