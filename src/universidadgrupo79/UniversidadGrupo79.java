/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package universidadgrupo79;

import AccesoADatos.AlumnoData;
import AccesoADatos.Conexion;
import AccesoADatos.InscripcionData;
import AccesoADatos.MateriaData;
import Entidades.Alumno;
import Entidades.Inscripcion;
import Entidades.Materia;
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
//
 // Alumno juan = new Alumno(12312321,"Bascones", "Jaun",LocalDate.of(2002, Month.SEPTEMBER, 23),true);
    //     Alumno esteban = new Alumno(12312322,"Lopez", "Esteban",LocalDate.of(2002, Month.SEPTEMBER, 23),true);
        AlumnoData a = new AlumnoData();
 //      a.guardarAlumno(esteban);
        
   //     a.modificarAlumno(juan);
   
  //      a.eliminarAlumno(2);
        
        
  //      Alumno alumnoEncontrado = a.buscarAlumnoPorDni(22222222);
  //      System.out.println(alumnoEncontrado.toString());
    
//          for(Alumno aux: a.listarAlumno()){
//              System.out.println(aux.toString());
//          }
//  
//  
        
    //  Materia fisica = new Materia(5,"fisica 1",1,true);
        MateriaData m = new MateriaData();
   //     m.guardarMateria(fisica);
        //RECORDAR AGREGAR EL ID PARA MODIFICAR
   //    m.modificarMateria(fisica);
  //      m.actualizarMateria(5);
  
//       Materia materiaEncontrada = m.buscarMateria(5);
//       System.out.println(materiaEncontrada.toString());
//  
//        for(Materia aux: m.listarMateria()){
//            System.out.println(aux.toString());
//        }


        //INSCRIPCION PARA ALUMNO YA EXISTENTE
 //       Inscripcion i = new Inscripcion();
       InscripcionData insc = new InscripcionData();
//        i.setAlumno(a.buscarAlumno(10));
//        i.setMateria(m.buscarMateria(4));
//        i.setNota(8.5);
//        
//        insc.guardarInscripcion(i);
//        insc.obtenerInscripciones();
//        for(Inscripcion aux: insc.obtenerInscripciones()){
//            System.out.println(aux.toString());
//        }


//        insc.actualizarNota(10, 5, 4.2);
//        insc.borrarInscripcionMateriaAlumno(10, 5);
        
//            
//          for(Materia aux: insc.obtenerMateriasCursadas(1)){
//              System.out.println(aux.getNombre());
//          }
//          
//          for(Materia aux: insc.obtenerMateriasNoCursadas(1)){
//              System.out.println(aux.getNombre());
//          }
          
          for(Alumno aux: insc.obtenerAlumnosPorMateria(3)){
              System.out.println(aux.toString());
          }
    }
    
}
