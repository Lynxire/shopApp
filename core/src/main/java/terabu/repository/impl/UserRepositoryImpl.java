package terabu.repository.impl;

import jakarta.persistence.EntityManager;
import terabu.config.HibernateConnection;
import terabu.entity.User;
import terabu.repository.UserRepository;

public class UserRepositoryImpl implements UserRepository {
    @Override
    public void add(User user) {
        EntityManager entityManager = HibernateConnection.getEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
        entityManager.close();
    }
}
