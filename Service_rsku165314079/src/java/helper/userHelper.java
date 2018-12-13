/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import org.hibernate.Query;
import org.hibernate.Session;
import pojos.User;
import util.TobbyHibernateUtil;

/**
 *
 * @author user
 */
public class userHelper {

    public User login(String email, String password) {
        Session session = TobbyHibernateUtil.getSessionFactory().openSession();
        String q = "From User a where a.email=:email AND a.password =:password";

        Query query = session.createQuery(q);
        query.setParameter("email", email);
        query.setParameter("password", password);

        return (User) query.uniqueResult();
    }
}
