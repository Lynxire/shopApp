package terabu.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnection implements JdbcConnection {

    //Для создания подключения необходимо указывать урл к бд, пользователя и пароль
    //При этом, для того подключения, необходимо зарегистрировать драйвер. Для этого
    // достаточно добавить соответствующую зависимость
    @Override
    public Connection getConnection() {
        try {
            String url = "jdbc:postgresql://localhost:5432/testdb";
            String username = "postgres";
            String password = "sa";
            //Это необязательно, если есть зависимость
//            Class.forName("org.postgresql.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        }
    }}
}
