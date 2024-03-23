package terabu.config;


import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;


public class HibernateConnection {

    private static final EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("Unit");


    public static EntityManager getEntityManager() {
        //Необходимо создать EntityManagerFactory, откуда мы возьмём entityManager
        // и тогда уже сможем сохранить нашего студента. Аргумент берётся из persistenceunitName
//        файл persistence.xml
//        EntityManagerFactory создаётся один раз для persistenseunit

        return emf.createEntityManager();
    }
}
