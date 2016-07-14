/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Cesar.Solano 
 * Clase encargada de generar la conexión con la base de datos
 */
public class Conexion {

    public Conexion() {
    }

    String driver = "org.postgresql.Driver";
    String connectString = "jdbc:postgresql://10.10.30.11:5432/siupra";
    String connectStringTESTProd = "jdbc:postgresql://10.10.150.10:5432/sipra_test";
    String user = "upramisional";
    String password = "upr4m1s10n4l";

    public Connection getConexion() {
        Connection con = null;
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(connectStringTESTProd, user, password);
        } catch (SQLException e) {
            System.out.println("sqlexcepcion");
        } catch (ClassNotFoundException e) {
            System.out.println("classnofoundexception");
        }
        return con;
    }

    /**
     * Método que se encarga de cerrar el objeto de conexión
     *
     * @param cn Objeto de tipo Connection instanciado
     */
    public void cerrar(Connection cn) {
        if (cn != null) {
            try {
                //	System.out.println("cierra conexion");
                cn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     * Método que se encarga de cerrar el objeto de PreparedStatement
     * @param pst Objeto de tipo PreparedStatement instanciado
     */
    public void cerrar(PreparedStatement pst) {
        if (pst != null) {
            try {
                pst.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    /**
     *  Método que se encarga de cerrar el objeto de ResultSet
     * @param rs Objeto de tipo ResultSet instanciado
     */
    @SuppressWarnings("empty-statement")
    public void cerrar(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();;
            }
        }
    }
}
