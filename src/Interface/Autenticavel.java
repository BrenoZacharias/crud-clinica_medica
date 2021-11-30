package Interface;

import java.sql.SQLException;

public interface Autenticavel {

    void autenticar (String usename, String senha) throws SQLException;
}
