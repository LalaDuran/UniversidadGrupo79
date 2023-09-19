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
import java.util.ArrayList;
import java.util.List;
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
            
            //RETURN GENERATED KEYS PARA QUE DEVUELVA EL ID AUTO GENERADO
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4,Date.valueOf(alumno.getFechaNacim()));
            ps.setBoolean(5, alumno.isActivo());
            
            ps.executeUpdate();
            //ASIGNA EL ID AL ALUMNO
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
        String sql = "UPDATE alumno SET apellido = ?, nombre = ?, fecha_nac= ? WHERE id_alumno = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setString(1, alumno.getApellido());
            ps.setString(2, alumno.getNombre());
            ps.setDate(3,Date.valueOf(alumno.getFechaNacim()));   
            ps.setInt(4, alumno.getIdAlumno());
            ps.setInt(5, alumno.getDni());
           
           
            int exito = ps.executeUpdate();
            if(exito ==1){
                System.out.println(" da");
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
    
    public void actualizarAlumno(int id){
        String sql = "UPDATE alumno SET estado = 1 "
                + "WHERE id_alumno = ? ";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            
            int exito = ps.executeUpdate();
            if(exito==1){
                JOptionPane.showMessageDialog(null,"Alumno actualizado");
            }
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno");
            
        }
    }
    
    public Alumno buscarAlumno(int id){
        String sql = "SELECT dni, apellido, nombre, fecha_nac FROM alumno WHERE id_alumno = ? AND estado = 1 ";
        Alumno alumnoABuscar = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                alumnoABuscar = new Alumno();
                alumnoABuscar.setIdAlumno(id);
                alumnoABuscar.setDni(rs.getInt("dni"));
                alumnoABuscar.setApellido(rs.getString("apellido"));
                alumnoABuscar.setNombre(rs.getString("nombre"));
                alumnoABuscar.setFechaNacim(rs.getDate("fecha_nac").toLocalDate());
                alumnoABuscar.setActivo(true);
       
            } else {
                JOptionPane.showMessageDialog(null, "No existe alumno con ese ID");
            }
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla");
        }
        return alumnoABuscar;
    }
    
    public Alumno buscarAlumnoPorDni(int dni){
        //SACAR ESTADO DE WHERE PARA PODER VISUALZAR ALUMNOS ACTIVOS Y NO ACTIVOS
        String sql = "SELECT id_alumno, dni, apellido, nombre, fecha_nac, estado FROM alumno WHERE dni = ? ";
        Alumno alumnoABuscar = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ps.setInt(1, dni);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                alumnoABuscar = new Alumno();
                alumnoABuscar.setIdAlumno(rs.getInt("id_alumno"));
                
                //Tambien se puede poner alumnoABuscar.setDni(dni); porque lo tenemos por parametro.
                alumnoABuscar.setDni(rs.getInt("dni"));
                alumnoABuscar.setApellido(rs.getString("apellido"));
                alumnoABuscar.setNombre(rs.getString("nombre"));
                alumnoABuscar.setFechaNacim(rs.getDate("fecha_nac").toLocalDate());
                alumnoABuscar.setActivo(rs.getBoolean("estado"));
       
            } else {
                JOptionPane.showMessageDialog(null, "No existe alumno con ese DNI");
            }
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla");
        }
        return alumnoABuscar;
    }
    
    public List<Alumno> listarAlumno(){
        //Sacamos estado del WHERE
        String sql = "SELECT id_alumno, dni, apellido, nombre, fecha_nac FROM alumno ";
        ArrayList<Alumno> alumnos = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
      
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Alumno alumnoABuscar = new Alumno();
   
                alumnoABuscar.setIdAlumno(rs.getInt("id_alumno"));        
                alumnoABuscar.setDni(rs.getInt("dni"));
                alumnoABuscar.setApellido(rs.getString("apellido"));
                alumnoABuscar.setNombre(rs.getString("nombre"));
                alumnoABuscar.setFechaNacim(rs.getDate("fecha_nac").toLocalDate());
                alumnoABuscar.setActivo(true);
                
                alumnos.add(alumnoABuscar);
       
            } 
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla");
        }
        return alumnos;
    }
    
    
}
