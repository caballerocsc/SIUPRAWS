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
 * @author Usuario
 */
public class Conexion {

    public Conexion() {
    }
    
    String driver = "org.postgresql.Driver";
    String connectString = "jdbc:postgresql://10.10.30.11:5432/siupra";
    String user = "upramisional";
    String password = "upr4m1s10n4l";
    
    public Connection getConexion(){
        Connection con=null;
        try{
            Class.forName(driver);
            con = DriverManager.getConnection(connectString, user , password);
        }catch(SQLException e){
            System.out.println("sqlexcepcion");
        }catch(ClassNotFoundException e){
            System.out.println("classnofoundexception");
        }
        return con;
    }
    
    public void cerrar(Connection cn){
		if(cn!=null){
			try {
			//	System.out.println("cierra conexion");
				cn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void cerrar(PreparedStatement pst){
		if(pst!=null){
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	public void cerrar(ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();;
			}
		}
	}
}
