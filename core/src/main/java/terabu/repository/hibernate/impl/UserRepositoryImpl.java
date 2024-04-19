package terabu.repository.hibernate.impl;

import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import terabu.entity.User;
import terabu.repository.hibernate.UserRepositoryInterface;

import java.util.List;
@Repository
@RequiredArgsConstructor
public class UserRepositoryImpl implements UserRepositoryInterface {

    private final SessionFactory sessionFactory;

    @Override
    public void add(User user) {
//        EntityManager entityManager = HibernateConnection.getEntityManager();
//        entityManager.getTransaction().begin();
//        entityManager.persist(user);
//        entityManager.getTransaction().commit();
//        entityManager.close();
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
