/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoADatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Faustino
 */
public class Conexion {
    
    private static final String url = "jdbc:mariadb://localhost/";
    private static final String bdd = "universidadulp";
    private static final String usuario = "root";
    private static final String clave = "";
    private static Connection connection;
    
    private Conexion(){}
    
    public static Connection getConexion(){
        
        if(connection == null){
            try {
                Class.forName("org.mariadb.jdbc.Driver");
                connection = DriverManager.getConnection(url + bdd,usuario,clave);
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Error al cargar el driver");
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error al conectarse a la base de datos");
            }
        }
        return connection;
        
    }
    
}
