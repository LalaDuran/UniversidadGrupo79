
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


public class MateriaData {
    
    private Connection con = null;
    
    public MateriaData(){
        con = Conexion.getConexion(); 
    }
    
    public void guardarMateria(Materia materia){
        String sql = "INSERT INTO materia (nombre, anio, estado) VALUES (?,?,?)";
        
        try {
            //Prepara el comando SQL con RETURN GENERATED KEYS para que devuelva el 
            //id_materia que es generado autoincremental
            PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            //Asignamos los valores a los parámetros dinámicos
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAnio());
            ps.setBoolean(3, materia.isActivo());
            
            //Ejecutamos el comando SQL
            ps.executeUpdate();
            
            //Recuperamos el id_materia generado autoincremental
            ResultSet rs = ps.getGeneratedKeys();
            
            //Asignamos el id generado 
            if(rs.next()){
                materia.setIdMateria(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Materia Guardada");
            }
            
            //Liberamos recursos
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla");
        } 
    }
    
    
    public void modificarMateria(Materia materia){
        String sql = "UPDATE materia SET nombre = ?, anio = ?, estado = ? WHERE id_materia = ?";
        
        try {
            //Prepara el comando SQL
            PreparedStatement ps = con.prepareStatement(sql);
            
            //Asignamos los valores a los parámetros dinámicos
            ps.setString(1, materia.getNombre());
            ps.setInt(2, materia.getAnio());
            ps.setBoolean(3, materia.isActivo());
            ps.setInt(4, materia.getIdMateria());
            
            //Ejecutamos el comando SQL que devuelve un entero; creamos variable
            int exito = ps.executeUpdate();
            
            if(exito==1){
                JOptionPane.showMessageDialog(null, "Materia modificada exitosamente");  
            }
            
            //Liberamos recursos     
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla");
        }
    }
    
    
    public void eliminarMateria(int id){
        String sql = "UPDATE materia SET estado = 0 WHERE id_materia = ?";
        
        try {
            //Prepara el comando SQL
            PreparedStatement ps = con.prepareStatement(sql);
            
            //Asignamos el valor al parámetro dinámico
            ps.setInt(1, id);
            
            //Ejecutamos el comando SQL que devuelve un entero; creamos variable
            int exito = ps.executeUpdate();
            
            if(exito==1){
                JOptionPane.showMessageDialog(null, "Materia Eliminada Exitosamente");
            }
            
            //Liberamos recursos
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla");
        }    
    }
    
    
     public void actualizarMateria(int id){
        String sql = "UPDATE materia SET estado = 1 WHERE id_materia = ?";
        
        try {
            //Prepara el comando SQL
            PreparedStatement ps = con.prepareStatement(sql);
            
            //Asignamos el valor al parámetro dinámico
            ps.setInt(1, id);
            
            //Ejecutamos el comando SQL que devuelve un entero; creamos variable
            int exito = ps.executeUpdate();
            
            if(exito==1){
                JOptionPane.showMessageDialog(null, "Materia Actualizada Exitosamente");
            }
            
            //Liberamos recursos
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla");
        }
    }
    
     
    public Materia buscarMateria(int id){
        //Decidimos sacar 'estado' del WHERE para poder acceder a materias activas e inactivas
        String sql = "SELECT nombre, anio FROM materia WHERE id_materia = ? ";
        
        //Creamos una materia en null para setearla luego
        Materia materiaABuscar = null;
        
        try {
            //Prepara el comando SQL
            PreparedStatement ps = con.prepareStatement(sql);
            
            //Asignamos el valor al parámetro dinámico
            ps.setInt(1, id);
            
            //Ejecutamos el comando SQL que devuelve un ResulSet; creamos variable
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()){
                //Instanciamos materiaABuscar y seteamos
                materiaABuscar = new Materia();
                materiaABuscar.setIdMateria(id);
                materiaABuscar.setNombre(rs.getString("nombre"));
                materiaABuscar.setAnio(rs.getInt("anio"));
                materiaABuscar.setActivo(true);
                
            } else {
                JOptionPane.showMessageDialog(null, "No existe materia con ese ID");
            }
            
            //Liberamos recursos
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla");
        }
        return materiaABuscar; 
    }
    
    
    public List<Materia> listarMateria(){
        String sql = "SELECT id_materia, nombre, anio FROM materia ";
        
        //Instanciamos el arraylist que usaremos luego
        ArrayList<Materia> materias = new ArrayList<>();
        
        try {
            //Prepara el comando SQL
            PreparedStatement ps = con.prepareStatement(sql);
            
            //Ejecutamos el comando SQL que devuelve un ResulSet; creamos variable
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                //Instanciamos materiaABuscar y seteamos
                Materia materiaABuscar = new Materia();
                materiaABuscar.setIdMateria(rs.getInt("id_materia"));
                materiaABuscar.setNombre(rs.getString("nombre"));
                materiaABuscar.setAnio(rs.getInt("anio"));
                materiaABuscar.setActivo(true);
                
                //Agregamos la materia al arraylist
                materias.add(materiaABuscar);
            }
            
            //Liberamos recursos
            ps.close();
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla");
        }
        return materias;     
    }
}
