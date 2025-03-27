package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Conexao {


     private static final String url = "jdbc:mysql://localhost:3306/pharmabd";

    public static void main (String[] args){


        String user = "root";
        String password = "Aa1234@!";
        Connection conn;
        conn = null;

        try {
            if(conn == null){
                conn = DriverManager.getConnection(url, user, password);
                System.out.println("conectou");
                //return conn;
            }else{
                //return conn;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            //return null;
        }

    }

}
