package ma.projet.service;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ma.projet.beans.Employe;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;
import org.hibernate.Session;


/**
 *
 * @author YASSINE
 */
public class EmployeService implements IDao<Employe> {

    @Override
    public boolean create(Employe Employe) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(Employe);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(Employe Employe) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(Employe);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(Employe Employe) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(Employe);
        session.flush();
        session.getTransaction().commit();
        return true;
    }

    @Override
    public Employe getById(int id) {
        Employe Employe = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Employe = (Employe) session.get(Employe.class, id);
        session.getTransaction().commit();
        return Employe;
    }

    @Override
    public List<Employe> getAll() {
        List<Employe> Employes = null;

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Employes = session.createQuery("from Employe").list();
        session.getTransaction().commit();
        return Employes;
    }
public List<Object[]> nbEmploye() {
    List<Object[]> Employes = null;
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    
    Employes = session.createQuery("select count(e.id), e.nom, e.chef.id from Employe e group by e.nom, e.chef.id").list();
    
    session.getTransaction().commit();
    return Employes;
}
public List<Object[]> nbEmployeByChef() {
    List<Object[]> Employes = null;
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    Employes = session.createQuery("select count(e.nom), e.chef.nom from Employe e group by e.chef.nom").list();
    session.getTransaction().commit();
    return Employes;
}


    public List<Employe> getbydates(Date d1, Date d2) {
        List<Employe> Employes = new ArrayList<>();
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        Employes = session.createQuery("from Employe e where e.dateDeNaisssance between :d1 and :d2")
                .setParameter("d1", d1)
                .setParameter("d2", d2)
                .list();
        session.getTransaction().commit();
        return Employes;
    }
// Inside EmployeService class

public List<Employe> getChefs() {
    List<Employe> chefs = null;
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    chefs = session.createQuery("from Employe e where e.chef_id is null").list();
    session.getTransaction().commit();
    return chefs;
}

public List<Employe> getSubordinates(Employe chef) {
    List<Employe> subordinates = null;
    Session session = HibernateUtil.getSessionFactory().openSession();
    session.beginTransaction();
    subordinates = session.createQuery("from Employe e where e.chef_id = :chef_id")
                          .setParameter("chef_id", chef)
                          .list();
    session.getTransaction().commit();
    return subordinates;
}

    
}