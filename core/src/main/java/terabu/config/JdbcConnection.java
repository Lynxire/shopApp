package terabu.config;

import java.sql.Connection;

public interface JdbcConnection {

    Connection getConnection();
}
