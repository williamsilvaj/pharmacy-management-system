package tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TesteConexaoBD {

  public static void main(String[] args) {

    String urlLocal = "jdbc:mysql://localhost:3306/pharmamanager";
    String userLocal = "root";
    String passwordLocal = "1234";

    String url =
        "jdbc:mysql://db-pharmamanager.cb0mek0yqv3r.us-east-2.rds.amazonaws.com:3306/pharmaManager";
    String user = "admin";
    String password = "!123456#";

    try {
      Connection conn = DriverManager.getConnection(urlLocal, userLocal, passwordLocal);
      System.out.println("✅ Conexão bem-sucedida!");
      conn.close();
    } catch (SQLException e) {
      System.out.println("❌ Falha na conexão: " + e.getMessage());
    }
  }
}
