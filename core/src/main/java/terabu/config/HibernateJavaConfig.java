package terabu.config;


import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import terabu.entity.User;

import java.util.Properties;

//Конфигурация через java язык
public class HibernateJavaConfig {
    private final static StandardServiceRegistryBuilder serviceRegistryBuilder;

    private final static Configuration configuration;

    static {
        Properties properties = new Properties();
        properties.setProperty("hibernate.format_sql", "true");
        properties.setProperty("hibernate.use_sql_comments", "true");
//        Существуют следующие варианты для hibernate
        //1. validate (Проверяет текущую базу данных с сущностями
        //2. create-only Создаёт бд
        //3. drop удаляет бд
        //4. create Создаёт схему с удалением предыдущей схемы с данными
        //5. create-drop Удаляется схема, когда останавливается приложение
        //6. none ничего не делает
        //7. update обновляет бд
//        properties.setProperty("hibernate.hbm2ddl.auto", "create");
        //Не забыть поменять
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.setProperty("hibernate.connection.driver_class", "org.postgresql.Driver");
        properties.setProperty("hibernate.connection.url", "jdbc:postgresql://localhost:5432/shopapp");
        properties.setProperty("hibernate.connection.username", "postgres");
        properties.setProperty("hibernate.connection.password", "1111");

        //Показываь логи
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.format_sql", "true");
        configuration = new Configuration();
//        Сюда надо добавить наши сущности (помеченные аннтацией Entity)
        configuration.addAnnotatedClass(User.class);
        //        Сюда надо добавить наши данные по подключению
        configuration.setProperties(properties);
        serviceRegistryBuilder = new StandardServiceRegistryBuilder();
        serviceRegistryBuilder.applySettings(properties);
    }

    //Работаем уже непосредственно с объектами, которые предоставляет hibernate (SessionFactory)
    public static SessionFactory getSessionFactory() {
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistryBuilder.build());
        return sessionFactory;
    }
}
