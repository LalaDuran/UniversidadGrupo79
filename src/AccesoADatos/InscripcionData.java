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
import javax.swing.JOptionPane;

public class InscripcionData {

    private Connection con = null;
    private AlumnoData alumnodata;
    private MateriaData materiadata;

    public InscripcionData() {
        this.con = Conexion.getConexion();
        alumnodata = new AlumnoData();
        materiadata = new MateriaData();
    }

    public void guardarInscripcion(Inscripcion ins) {
        String sql = "INSERT INTO inscripcion (nota, id_alumno, id_materia)"
                + "VALUES (?, ?, ?)";

        try {
            //Prepara el comando SQL con RETURN GENERATED KEYS para que devuelva el 
            //id_inscripcion que es generado autoincremental
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //Asignamos los valores a los parámetros dinámicos 
            ps.setDouble(1, ins.getNota());
            ps.setInt(2, ins.getAlumno().getIdAlumno());
            ps.setInt(3, ins.getMateria().getIdMateria());

            //Ejecutamos el comando SQL
            ps.executeUpdate();

            //Recuperamos el id_alumno generado autoincremental
            ResultSet rs = ps.getGeneratedKeys();

            if (rs.next()) {
                ins.setIdInscripcion(rs.getInt(1));
                JOptionPane.showMessageDialog(null, "Inscripcion agregada exitosamente");
            }

            //Liberamos recursos
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla inscripcion");
        }
    }

    public List<Inscripcion> obtenerInscripciones() {
        String sql = "SELECT id_inscripto, nota, id_alumno, id_materia FROM inscripcion";

        //Instanciamos el arraylist que usaremos luego
        ArrayList<Inscripcion> inscripciones = new ArrayList<>();

        try {
            //Prepara el comando SQL
            PreparedStatement ps = con.prepareStatement(sql);

            //Ejecutamos el comando SQL que devuelve un ResulSet; creamos variable
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                //Instanciamos inscripción y seteamos                
                Inscripcion inscripcion = new Inscripcion();
                inscripcion.setIdInscripcion(rs.getInt("id_inscripto"));
                inscripcion.setNota(rs.getDouble("nota"));
                //accedemos a BD de alumno y materia y buscamos con método buscar
                inscripcion.setAlumno(alumnodata.buscarAlumno(rs.getInt("id_alumno")));
                inscripcion.setMateria(materiadata.buscarMateria(rs.getInt("id_materia")));

                //Agregamos la inscripcion al arraylist
                inscripciones.add(inscripcion);
            }

            //Liberamos recursos
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla");
        }
        return inscripciones;
    }

    public List<Inscripcion> obtenerInscripcionesPorAlumno(int id) {
        String sql = "SELECT id_inscripto, nota, id_alumno, id_materia FROM inscripcion WHERE id_alumno = ?";

        //Instanciamos el arraylist que usaremos luego
        ArrayList<Inscripcion> inscripciones = new ArrayList<>();

        try {
            //Prepara el comando SQL
            PreparedStatement ps = con.prepareStatement(sql);

            //Asignamos el valor al parámetro dinámico
            ps.setInt(1, id);

            //Ejecutamos el comando SQL que devuelve un ResulSet; creamos variable
            ResultSet rs = ps.executeQuery();

            //ACCEDER A LA BASE DE DATOS DE ALUMNO Y MATERIA Y BUSCARLOS CON EL METODO BUSCAR
            while (rs.next()) {
                //Instanciamos inscripción y seteamos                 
                Inscripcion inscripcion = new Inscripcion();
                inscripcion.setIdInscripcion(rs.getInt("id_inscripto"));
                inscripcion.setNota(rs.getDouble("nota"));
                //accedemos a BD de alumno y materia y buscamos con método buscar
                inscripcion.setAlumno(alumnodata.buscarAlumno(rs.getInt("id_alumno")));
                inscripcion.setMateria(materiadata.buscarMateria(rs.getInt("id_materia")));

                //Agregamos la inscripcion al arraylist
                inscripciones.add(inscripcion);
            }

            //Liberamos recursos
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla");
        }
        return inscripciones;
    }

    public List<Materia> obtenerMateriasCursadas(int id) {
        String sql = "SELECT inscripcion.id_materia, nombre, anio FROM inscripcion, materia "
                + "WHERE inscripcion.id_materia = materia.id_materia AND inscripcion.id_alumno = ?";

        //Instanciamos el arraylist que usaremos luego
        ArrayList<Materia> materias = new ArrayList<>();

        try {
            //Prepara el comando SQL
            PreparedStatement ps = con.prepareStatement(sql);

            //Asignamos el valor al parámetro dinámico
            ps.setInt(1, id);

            //Ejecutamos el comando SQL que devuelve un ResulSet; creamos variable
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                //Instanciamos materia y seteamos   
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("id_materia"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("anio"));

                //Agregamos la materia al arraylist
                materias.add(materia);
            }

            //Liberamos recursos
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla");
        }
        return materias;
    }

    public List<Materia> obtenerMateriasNoCursadas(int id) {
        String sql = "SELECT * FROM materia WHERE estado = 1 AND id_materia not in"
                + "(SELECT id_materia FROM inscripcion WHERE id_alumno = ?)";

        //Instanciamos el arraylist que usaremos luego
        ArrayList<Materia> materias = new ArrayList<>();

        try {
            //Prepara el comando SQL
            PreparedStatement ps = con.prepareStatement(sql);

            //Asignamos el valor al parámetro dinámico
            ps.setInt(1, id);

            //Ejecutamos el comando SQL que devuelve un ResulSet; creamos variable
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                //Instanciamos materia y seteamos   
                Materia materia = new Materia();
                materia.setIdMateria(rs.getInt("id_materia"));
                materia.setNombre(rs.getString("nombre"));
                materia.setAnio(rs.getInt("anio"));

                //Agregamos la materia al arraylist
                materias.add(materia);
            }

            //Liberamos recursos
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla");
        }
        return materias;
    }

    public void borrarInscripcionMateriaAlumno(int id_alumno, int id_materia) {
        String sql = "DELETE FROM inscripcion WHERE id_alumno = ? AND id_materia = ?";

        try {
            //Prepara el comando SQL
            PreparedStatement ps = con.prepareStatement(sql);

            //Asignamos el valor a los parámetros dinámicos
            ps.setInt(1, id_alumno);
            ps.setInt(2, id_materia);

            //Ejecutamos el comando SQL que devuelve un entero; creamos variable
            int filas = ps.executeUpdate();

            if (filas == 1) {
                JOptionPane.showMessageDialog(null, "Inscripcion borrada");
            }

            //Liberamos recursos
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla");
        }
    }

    public void actualizarNota(int id_alumno, int id_materia, double nota) {
        String sql = "UPDATE inscripcion SET nota = ? WHERE id_alumno = ? AND id_materia = ?";

        try {
            //Prepara el comando SQL
            PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            //Asignamos el valor a los parámetros dinámicos
            ps.setDouble(1, nota);
            ps.setInt(2, id_alumno);
            ps.setInt(3, id_materia);

            //Ejecutamos el comando SQL que devuelve un entero; creamos variable
            int filas = ps.executeUpdate();
            if (filas == 1) {
                JOptionPane.showMessageDialog(null, "nota actualizada");
            }

            //Liberamos recursos
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla");
        }
    }

    public List<Alumno> obtenerAlumnosPorMateria(int id_materia) {
        String sql = "SELECT a.id_alumno, dni, nombre, apellido, fecha_nac, estado "
                + "FROM inscripcion i, alumno a WHERE i.id_alumno = a.id_alumno AND id_materia=? AND a.estado = 1";

        //Instanciamos el arraylist que usaremos luego
        ArrayList<Alumno> alumnos = new ArrayList<>();

        try {
            //Prepara el comando SQL
            PreparedStatement ps = con.prepareStatement(sql);

            //Asignamos el valor al parámetro dinámico
            ps.setInt(1, id_materia);

            //Ejecutamos el comando SQL que devuelve un resultSet; creamos variable
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                //Instanciamos materia y seteamos   
                Alumno alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("id_alumno"));
                alumno.setDni(rs.getInt("dni"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setFechaNacim(rs.getDate("fecha_nac").toLocalDate());
                alumno.setActivo(rs.getBoolean("estado"));

                //Agregamos el alumno al arraylist
                alumnos.add(alumno);
            }

            //Liberamos recursos
            ps.close();

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error al acceder a la tabla");
        }
        return alumnos;
    }
}
