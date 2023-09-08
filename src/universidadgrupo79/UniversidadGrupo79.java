/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadgrupo79;

import AccesoADatos.AlumnoData;
import AccesoADatos.Conexion;
import Entidades.Alumno;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.Month;

/**
 *
 * @author Adriana
 */
public class UniversidadGrupo79 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        Connection con = Conexion.getConexion();

        Alumno juan = new Alumno(12312321,"Bascones", "Jaun",LocalDate.of(2002, Month.SEPTEMBER, 23),true);
        AlumnoData a = new AlumnoData();
   //     a.guardarAlumno(juan);
        
   //     a.modificarAlumno(juan);
   
        a.eliminarAlumno(2);
        
    }
    
}
