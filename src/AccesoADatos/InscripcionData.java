/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoADatos;

import Entidades.Alumno;
import Entidades.Inscripcion;
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
public class InscripcionData {
    
    private Connection con = null;
    private AlumnoData alumnodata;
    private MateriaData materiadata;
    
    public InscripcionData(){
        this.con = Conexion.getConexion();
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
    
    public List<Inscripcion> obtenerInscripcionesPorAlumno(int id){
        String sql = "SELECT id_inscripto, nota, id_alumno, id_materia FROM inscripcion WHERE id_alumno = ?";
        ArrayList<Inscripcion> inscripciones = new ArrayList<>();      
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1,id);
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
    
    public List<Materia> obtenerMateriasCursadas(int id){
        String sql = "SELECT inscripcion.id_materia, nombre, anio FROM inscripcion, materia "
                + "WHERE inscripcion.id_materia = materia.id_materia AND inscripcion.id_alumno = ?";
        ArrayList<Materia> materias = new ArrayList<>();
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("id_materia"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("anio"));
                
                materias.add(materia);
            }
            
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla");
        }
        return materias;
    }
    
    public List<Materia> obtenerMateriasNoCursadas(int id){
        String sql = "SELECT * FROM materia WHERE estado = 1 AND id_materia not in"
                + "(SELECT id_materia FROM inscripcion WHERE id_alumno = ?)";
        ArrayList<Materia> materias = new ArrayList<>();
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("id_materia"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("anio"));
                
                materias.add(materia);
            }
            
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla");
        }
        return materias;
    }
    
    public void borrarInscripcionMateriaAlumno(int id_alumno, int id_materia){
    
        String sql = "DELETE FROM inscripcion WHERE id_alumno = ? AND id_materia = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_alumno);
            ps.setInt(2, id_materia);
            
            int filas = ps.executeUpdate();
            if(filas ==1){
                JOptionPane.showMessageDialog(null, "Inscripcion borrada");
            }
            
            ps.close();
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Error al acceder a la tabla");
        }
    }
    
    public void actualizarNota(int id_alumno, int id_materia, double nota){
        String sql = "UPDATE inscripcion SET nota = ? WHERE id_alumno=? AND id_materia = ?";
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setDouble(1, nota);
            ps.setInt(2, id_alumno);
            ps.setInt(3, id_materia);
            
            int filas = ps.executeUpdate();
            if(filas == 1){
                JOptionPane.showMessageDialog(null, "nota actualizada");
            }
            
            ps.close();
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Error al acceder a la tabla");
        }
   
    }
    
    public List<Alumno> obtenerAlumnosPorMateria(int id_materia){
        String sql = "SELECT a.id_alumno, dni, nombre, apellido, fecha_nac, estado "
                + "FROM inscripcion i, alumno a WHERE i.id_alumno = a.id_alumno AND id_materia=? AND a.estado = 1";
        
        ArrayList<Alumno> alumnos = new ArrayList<>();
        
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_materia);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Alumno alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("id_alumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacim(rs.getDate("fecha_nac").toLocalDate());
                alumno.setActivo(rs.getBoolean("estado"));
                
                alumnos.add(alumno);
            }
            ps.close();
            
        } catch (SQLException ex) {
            
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla");        }
        
        return alumnos;
    }
    
}
