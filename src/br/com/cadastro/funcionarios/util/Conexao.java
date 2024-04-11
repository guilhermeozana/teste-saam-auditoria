package br.com.cadastro.funcionarios.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class Conexao {

    private static Connection conexao;

    public static Connection getConexao() {

        try {
            if (conexao == null || conexao.isClosed()) {

                conecta();
            }
        } catch (SQLException ex) {

            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return conexao;
    }

    protected static void conecta() {

        try {

            String url = "jdbc:postgresql://localhost:5432/teste-saam";
            conexao = (Connection) DriverManager.getConnection(url, "postgres", "admin123");
        } catch (SQLException ex) {

            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void desconecta() {

        try {
            if (conexao != null) {

                conexao.close();
            }
        } catch (SQLException ex) {

            Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
