package br.fipp.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexao {

    private static final String URL = 
            "jdbc:postgresql://127.0.0.1:5432/postgres";
    private static final String usuario = "postgres";
    private static final String senha = "6844866";

    public static Connection abre() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(URL,
                    usuario, senha);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexao.class.getName())
                    .log(Level.SEVERE, null, ex);
        }
        return null;
    }

}



