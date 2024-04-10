package terabu.repository.impl;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import terabu.config.HibernateConnection;
import terabu.config.HibernateJavaConfig;
import terabu.entity.User;
import terabu.repository.UserRepositoryInterface;

import java.util.List;

public class UserRepositoryImpl implements UserRepositoryInterface {
    private final SessionFactory sessionFactory;

    public UserRepositoryImpl(){
        sessionFactory = HibernateJavaConfig.getSessionFactory();
    }
    @Override
    public void add(User user) {
        EntityManager entityManager = HibernateConnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }

    @Override
    public void deleteById(Long id) {
        Session session = sessionFactory.openSession();
        User user = session.get(User.class, id);
        session.beginTransaction();
        session.remove(user);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public List<User> viewAllUsers() {
        Session session = sessionFactory.openSession();
        Query<User> userQuery = session.createQuery("select u from User u", User.class);
        List<User> resultList = userQuery.getResultList();
        return resultList;
    }
}
