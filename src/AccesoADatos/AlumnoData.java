/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoADatos;

import Entidades.Alumno;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Faustino
 */
public class AlumnoData {
    
    private Connection con = null;
    
    public AlumnoData(){
        con = Conexion.getConexion();
    }
    
    public void guardarAlumno(Alumno alumno){
        String sql = "INSERT INTO alumno (dni,apellido,nombre,fecha_nac,estado)"
                + "VALUES (?,?,?,?,?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4,Date.valueOf(alumno.getFechaNacim()));
            ps.setBoolean(5, alumno.isActivo());
            
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                alumno.setIdAlumno(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Alumno Guardado");
            }
            
            ps.close();
    
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla alumno");
        }
        
    }
    
    public void modificarAlumno(Alumno alumno) {
        String sql = "UPDATE alumno set dni=?, apellido=?, nombre=?, fecha_nac=?"
                + "WHERE id_alumno = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4,Date.valueOf(alumno.getFechaNacim()));   
            ps.setInt(5, alumno.getIdAlumno());
           
            int exito = ps.executeUpdate();
            if(exito ==1){
                JOptionPane.showMessageDialog(null, "Alumno modificado");
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno");
        }
    }
    
    public void eliminarAlumno(int id){
        String sql = "UPDATE alumno SET estado = 0 "
                + "WHERE id_alumno = ? ";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            
            int exito = ps.executeUpdate();
            if(exito==1){
                JOptionPane.showMessageDialog(null,"Alumno eliminado");
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno");
            
        }
    }
    
    
}
