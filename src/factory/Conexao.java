package factory;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private final static String URL = "jdbc:mysql://localhost:3306/EAMT";
    private final static String USER = "root";
    private final static String PASS = "";
    private static Connection conexao;

    public static Connection getConexao() {
        if (conexao == null) {
            try {
                conexao = (Connection) DriverManager.getConnection(URL, USER, PASS);
                System.out.println("Conexão com banco de Dados Estabelecida!");

            } catch (SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
        return conexao;
    }

}