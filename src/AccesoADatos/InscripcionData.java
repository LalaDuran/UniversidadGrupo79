/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoADatos;

import Entidades.Inscripcion;
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
public class InscripcionData {
    
    private Connection con = null;
    private AlumnoData alumnodata;
    private MateriaData materiadata;
    
    public InscripcionData(){
        con = Conexion.getConexion();
        alumnodata = new AlumnoData();
        materiadata = new MateriaData();
        
    }
    
    public void guardarInscripcion(Inscripcion ins){
        String sql = "INSERT INTO inscripcion (nota, id_alumno, id_materia)"
                + "VALUES (?, ?, ?)";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            ps.setDouble(1, ins.getNota());
            ps.setInt(2, ins.getAlumno().getIdAlumno());
            ps.setInt(3, ins.getMateria().getIdMateria());
            
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if(rs.next()){
                ins.setIdInscripcion(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Inscripcion agregada exitosamente");
            } 
            ps.close();
            
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion");
        }
        
        
    }
    
    public List<Inscripcion> obtenerInscripciones(){
        String sql = "SELECT id_inscripto, nota, id_alumno, id_materia FROM inscripcion";
        ArrayList<Inscripcion> inscripciones = new ArrayList<>();      
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            
            ResultSet rs = ps.executeQuery();          
            //ACCEDER A LA BASE DE DATOS DE ALUMNO Y MATERIA Y BUSCARLOS CON EL METODO BUSCAR
            while(rs.next()){
                
                
                Inscripcion inscripcion = new Inscripcion();
                
                inscripcion.setIdInscripcion(rs.getInt("id_inscripto"));
                inscripcion.setNota(rs.getDouble("nota"));
                inscripcion.setAlumno(alumnodata.buscarAlumno(rs.getInt("id_alumno")));
                inscripcion.setMateria(materiadata.buscarMateria(rs.getInt("id_materia")));
                
                inscripciones.add(inscripcion);       
            }
            ps.close();  
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla");
        } 
        return inscripciones;   
    }
//    
//    public List<Inscripcion> obtenerInscripcionesPorAlumno(int id){
//        
//    }
//    
//    public List<Materia> obtenerMateriasCursadas(int id){
//        
//    }
//    
//    public List<Materia> obtenerMateriasNoCursadas(int id){
//        
//    }
//    
//    public void borrarInscripcionMateriaAlumno(int id_alumno, int id_materia){
//    
//    }
//    
//    public void actualizarNota(int id_alumno, int id_materia, double nota){
//        
//    }
//    
//    public List<Alumno> obtenerAlumnosPorMateria(int id_materia){
//        
//    }
    
}
