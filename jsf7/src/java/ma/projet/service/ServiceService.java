/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ma.projet.service;

/**
 *
 * @author HP
 */
import org.hibernate.Session;
import java.util.List;
import ma.projet.beans.Service;
import ma.projet.dao.IDao;
import ma.projet.util.HibernateUtil;

public class ServiceService implements IDao<Service> {

    @Override
    public boolean create(Service service) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(service);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean update(Service service) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.update(service);
        session.getTransaction().commit();
        return true;
    }

    @Override
    public boolean delete(Service service) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.delete(service);
        session.flush();
        session.getTransaction().commit();
        return true;
    }

    @Override
    public Service getById(int id) {
        Service service = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        service = (Service) session.get(Service.class, id);
        session.getTransaction().commit();
        return service;
    }

    @Override
    public List<Service> getAll() {
        List<Service> services = null;

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        services = session.createQuery("from Service").list();
        session.getTransaction().commit();
        return services;
    }
}
