/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoADatos;

import Entidades.Materia;
import java.sql.Connection;
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
public class MateriaData {
    
    private Connection con = null;
    
    public MateriaData(){
        con = Conexion.getConexion();
        
    }
    
    public void guardarMateria(Materia materia){
        String sql = "INSERT INTO materia (nombre, anio, estado)"
                + "VALUES (?,?,?)";
        try {
            
            //RETURN GENERATED KEYS PARA DEVOLVER EL ID AUTOGENERADO
            
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAnio());
            ps.setBoolean(3, materia.isActivo());
            
            ps.executeUpdate();
            
             //ASIGNA EL ID A LA MATERIA
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                materia.setIdMateria(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Materia Guardada");
            }
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla");
        }
        
    }
    
    
    public void modificarMateria(Materia materia){
        String sql = "UPDATE materia SET nombre = ?, anio = ? WHERE id_materia = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAnio());
            ps.setInt(3, materia.getIdMateria());
            
            int exito = ps.executeUpdate();
            if(exito==1){
                JOptionPane.showMessageDialog(null, "Materia modificada exitosamente");  
            }
     
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla");
        }
    }
    
    public void eliminarMateria(int id){
        String sql = "UPDATE materia SET estado = 0 WHERE id_materia = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            
            int exito = ps.executeUpdate();
            if(exito==1){
                JOptionPane.showMessageDialog(null, "Materia Eliminada Exitosamente");
            }
            
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla");
        }
        
    }
     public void actualizarMateria(int id){
        String sql = "UPDATE materia SET estado = 1 WHERE id_materia = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            
            int exito = ps.executeUpdate();
            if(exito==1){
                JOptionPane.showMessageDialog(null, "Materia Actualizada Exitosamente");
            }
            
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla");
        }
        
    }
    
    public Materia buscarMateria(int id){
        String sql = "SELECT nombre, anio FROM materia WHERE id_materia = ? AND estado = 1";
        Materia materiaABuscar = null;
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                materiaABuscar = new Materia();
                materiaABuscar.setIdMateria(id);
                materiaABuscar.setNombre(rs.getString("nombre"));
                materiaABuscar.setAnio(rs.getInt("anio"));
                materiaABuscar.setActivo(true);
                
            } else {
                JOptionPane.showMessageDialog(null, "No existe materia con ese ID");
            }
            
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla");
        }
            
        return materiaABuscar;
         
    }
        public List<Materia> listarMateria(){
        String sql = "SELECT id_materia, nombre, anio FROM materia WHERE estado = 1";
        ArrayList<Materia> materias = new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                
                Materia materiaABuscar = new Materia();
                materiaABuscar.setIdMateria(rs.getInt("id_materia"));
                materiaABuscar.setNombre(rs.getString("nombre"));
                materiaABuscar.setAnio(rs.getInt("anio"));
                materiaABuscar.setActivo(true);
                
                materias.add(materiaABuscar);
            }
            
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla");
        }
            
        return materias;
         
    }
}
