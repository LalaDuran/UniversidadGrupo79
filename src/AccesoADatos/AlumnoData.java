
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
import javax.swing.JOptionPane;


public class AlumnoData {
    
    //atributo común a todos los Data
    private Connection con = null;
    
    public AlumnoData(){
        //inicializa la variable con
        con = Conexion.getConexion();
    }
    
    public void guardarAlumno(Alumno alumno){
        
        String sql = "INSERT INTO alumno (dni,apellido,nombre,fecha_nac,estado)"
                + "VALUES (?,?,?,?,?)";
        
        try {
            //Prepara el comando SQL con RETURN GENERATED KEYS para que devuelva el 
            //id_alumno que es generado autoincremental
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //Asignamos los valores a los parámetros dinámicos 
            ps.setInt(1, alumno.getDni());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getNombre());
            ps.setDate(4,Date.valueOf(alumno.getFechaNacim()));
            ps.setBoolean(5, alumno.isActivo());
            
            //Ejecutamos el comando SQL
            ps.executeUpdate();
            
            //Recuperamos el id_alumno generado autoincremental
            ResultSet rs = ps.getGeneratedKeys();
            
            //Asignamos el id generado 
            if(rs.next()){
                alumno.setIdAlumno(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Alumno Guardado");
            }
            
            //Liberamos recursos
            ps.close();
    
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla alumno");
            
        } 
        
    }
    
    public void modificarAlumno(Alumno alumno) {
        String sql = "UPDATE alumno SET apellido = ?, nombre = ?, fecha_nac= ? WHERE id_alumno = ?";
        
        try {
            //Prepara el comando SQL
            PreparedStatement ps = con.prepareStatement(sql);
            
            //Asignamos los valores a los parámetros dinámicos
            ps.setString(1, alumno.getApellido());
            ps.setString(2, alumno.getNombre());
            ps.setDate(3,Date.valueOf(alumno.getFechaNacim()));   
            ps.setInt(4, alumno.getIdAlumno());
            ps.setInt(5, alumno.getDni());
           
            //Ejecutamos el comando SQL que devuelve un entero; creamos variable
            int exito = ps.executeUpdate();
            
            if(exito ==1){
                System.out.println(" da");
                JOptionPane.showMessageDialog(null, "Alumno modificado");
            }
            
            //Liberamos recursos
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno");
        }
    }
    
    public void eliminarAlumno(int id){
        String sql = "UPDATE alumno SET estado = 0 "
                + "WHERE id_alumno = ? ";
        
        try {
            //Prepara el comando SQL
            PreparedStatement ps = con.prepareStatement(sql);
            
            //Asignamos el valor al parámetro dinámico
            ps.setInt(1, id);
            
            //Ejecutamos el comando SQL que devuelve un entero; creamos variable
            int exito = ps.executeUpdate();
            
            if(exito==1){
                JOptionPane.showMessageDialog(null,"Alumno eliminado");
            }
            
            //Liberamos recursos
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno");
            
        }
    }
    
    public void actualizarAlumno(int id){
        
        String sql = "UPDATE alumno SET estado = 1 "
                + "WHERE id_alumno = ? ";
        
        try {
            //Prepara el comando SQL
            PreparedStatement ps = con.prepareStatement(sql);
            
            //Asignamos el valor al parámetro dinámico
            ps.setInt(1, id);
            
            //Ejecutamos el comando SQL que devuelve un entero; creamos variable
            int exito = ps.executeUpdate();
            
            if(exito==1){
                JOptionPane.showMessageDialog(null,"Alumno actualizado");
            }
            
            //Liberamos recursos
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla alumno");
            
        }
    }
    
    public Alumno buscarAlumno(int id){
        String sql = "SELECT dni, apellido, nombre, fecha_nac FROM alumno WHERE id_alumno = ?  ";
        //Creamos un alumno en null para setearlo luego
        Alumno alumnoABuscar = null;
        
        try {
            //Prepara el comando SQL
            PreparedStatement ps = con.prepareStatement(sql);
            
            //Asignamos el valor al parámetro dinámico
            ps.setInt(1, id);
            
            //Ejecutamos el comando SQL que devuelve un ResulSet; creamos variable
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                //Instanciamos alumnoABuscar y seteamos
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
            
            //Liberamos recursos
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla");
        }
        
        return alumnoABuscar;
    }
    
    public Alumno buscarAlumnoPorDni(int dni){
        //Sacamos 'estado' de WHERE para poder visualizar alumnos activos y no activos
        String sql = "SELECT id_alumno, dni, apellido, nombre, fecha_nac, estado FROM alumno WHERE dni = ? ";
        
        //Creamos un alumno en null para setearlo luego
        Alumno alumnoABuscar = null;
        
        try {
            //Prepara el comando SQL
            PreparedStatement ps = con.prepareStatement(sql);
            
            //Asignamos el valor al parámetro dinámico
            ps.setInt(1, dni);
            
            //Ejecutamos el comando SQL que devuelve un ResulSet; creamos variable
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                //Instanciamos alumnoABuscar y seteamos
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
            
            //Liberamos recursos
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla");
        }
        
        return alumnoABuscar;
    }
    
    public List<Alumno> listarAlumno(){
        //Sacamos 'estado' del WHERE igual que método anterior
        String sql = "SELECT id_alumno, dni, apellido, nombre, fecha_nac FROM alumno ";
        
        //Instanciamos el arraylist que usaremos luego
        ArrayList<Alumno> alumnos = new ArrayList<>();
        
        try {
            //Prepara el comando SQL
            PreparedStatement ps = con.prepareStatement(sql);
            
            //Ejecutamos el comando SQL que devuelve un ResulSet; creamos variable
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                //Instanciamos alumnoABuscar y seteamos
                Alumno alumnoABuscar = new Alumno();
                alumnoABuscar.setIdAlumno(rs.getInt("id_alumno"));        
                alumnoABuscar.setDni(rs.getInt("dni"));
                alumnoABuscar.setApellido(rs.getString("apellido"));
                alumnoABuscar.setNombre(rs.getString("nombre"));
                alumnoABuscar.setFechaNacim(rs.getDate("fecha_nac").toLocalDate());
                alumnoABuscar.setActivo(true);
                
                //Agregamos el alumno al arraylist
                alumnos.add(alumnoABuscar);
       
            } 
            
            //Liberamos recursos
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null,"Error al acceder a la tabla");
        }
        
        return alumnos;
        
    }
    
    
}
