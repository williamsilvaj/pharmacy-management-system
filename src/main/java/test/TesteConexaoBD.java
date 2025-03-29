package test;

import io.github.cdimascio.dotenv.Dotenv;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TesteConexaoBD {

  public static void main(String[] args) {
    Dotenv dotenv = Dotenv.load();

    String url = dotenv.get("DATABASE_URL");
    String user = dotenv.get("DATABASE_USER");
    String password = dotenv.get("DATABASE_PASSWORD");

    try {
      Connection conn = DriverManager.getConnection(url, user, password);
      System.out.println("✅ Conexão bem-sucedida!");
      conn.close();
    } catch (SQLException e) {
      System.out.println("❌ Falha na conexão: " + e.getMessage());
    }
  }
}
