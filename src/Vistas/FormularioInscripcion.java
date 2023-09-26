package Vistas;

import AccesoADatos.AlumnoData;
import AccesoADatos.InscripcionData;
import AccesoADatos.MateriaData;
import Entidades.Alumno;
import Entidades.Inscripcion;
import Entidades.Materia;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class FormularioInscripcion extends javax.swing.JInternalFrame {

    private final DefaultTableModel modelo = new DefaultTableModel() {
        public boolean isCellEditable(int f, int c) {
            return false;
        }
    };

    public FormularioInscripcion() {
        initComponents();
        
        //Carga los alumnos al jComboBox
        cargarAlumnos();
        
        //Carga la estructura de la tabla
        armarTabla();
        
        //Inhabilita los botones 'Anular' e 'Inscribir'
        jbAnularInsc.setEnabled(false);
        jbInscribir.setEnabled(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jcbSeleccionarAlumno = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jrbMatInscriptas = new javax.swing.JRadioButton();
        jrbMatNOInscriptas = new javax.swing.JRadioButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTablaMaterias = new javax.swing.JTable();
        jbInscribir = new javax.swing.JButton();
        jbAnularInsc = new javax.swing.JButton();
        jbSalir = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(600, 500));

        jLabel1.setText("Seleccione un alumno");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Listado de Materias");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Formulario de Inscripcion");

        jLabel4.setText("Materias Inscriptas");

        jLabel5.setText("Materias No Inscriptas");

        jrbMatInscriptas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbMatInscriptasActionPerformed(evt);
            }
        });

        jrbMatNOInscriptas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jrbMatNOInscriptasActionPerformed(evt);
            }
        });

        jTablaMaterias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTablaMaterias);

        jbInscribir.setText("Inscribir");
        jbInscribir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbInscribirActionPerformed(evt);
            }
        });

        jbAnularInsc.setText("Anular Inscripcion");
        jbAnularInsc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbAnularInscActionPerformed(evt);
            }
        });

        jbSalir.setText("Salir");
        jbSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbSalirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(74, 74, 74)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jrbMatInscriptas)
                                .addGap(118, 118, 118)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jrbMatNOInscriptas))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jbInscribir)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jbAnularInsc)
                                        .addGap(104, 104, 104)
                                        .addComponent(jbSalir))
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(43, 43, 43)
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jcbSeleccionarAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 24, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
            .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel3)
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jcbSeleccionarAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jrbMatInscriptas)
                    .addComponent(jrbMatNOInscriptas)
                    .addComponent(jLabel5))
                .addGap(37, 37, 37)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbInscribir)
                    .addComponent(jbAnularInsc)
                    .addComponent(jbSalir))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirActionPerformed
        //Invisibiliza, deselecciona y cierra la ventana
        this.dispose();
    }//GEN-LAST:event_jbSalirActionPerformed

    private void jrbMatInscriptasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbMatInscriptasActionPerformed
        //Instanciamos inscripcionData para usar luego
        InscripcionData id = new InscripcionData();

        //Creamos un alumno y le asignamos el alumno seleccionado en la vista
        Alumno a = (Alumno) jcbSeleccionarAlumno.getSelectedItem();

        //Si está seleccionado el botón, habilitamos e inhabilitamos los otros
        if (jrbMatInscriptas.isSelected()) {
            jrbMatNOInscriptas.setSelected(false);
            jbAnularInsc.setEnabled(true);
            jbInscribir.setEnabled(false);
        }
        
        //Borramos las filas evitando repeticiones
        borrarFilas();
        
        //Listamos las materias en la tabla
        for (Materia aux : id.obtenerMateriasCursadas(a.getIdAlumno())) {
            modelo.addRow(new Object[]{aux.getIdMateria(), aux.getNombre(), aux.getAnio()});
        }
    }//GEN-LAST:event_jrbMatInscriptasActionPerformed

    private void jrbMatNOInscriptasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jrbMatNOInscriptasActionPerformed
        //Instanciamos inscripcionData para usar luego
        InscripcionData id = new InscripcionData();
        
        //Creamos un alumno y le asignamos el alumno seleccionado en la vista
        Alumno a = (Alumno) jcbSeleccionarAlumno.getSelectedItem();
        
        //Si está seleccionado el botón, habilitamos e inhabilitamos los otros
        if (jrbMatNOInscriptas.isSelected()) {
            jrbMatInscriptas.setSelected(false);
            jbInscribir.setEnabled(true);
            jbAnularInsc.setEnabled(false);
        }
        
        //Borramos las filas evitando repeticiones
        borrarFilas();
        
        //Listamos las materias en la tabla
        for (Materia aux : id.obtenerMateriasNoCursadas(a.getIdAlumno())) {
            modelo.addRow(new Object[]{aux.getIdMateria(), aux.getNombre(), aux.getAnio()});
        }

    }//GEN-LAST:event_jrbMatNOInscriptasActionPerformed

    private void jbInscribirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbInscribirActionPerformed
        //Instanciamos inscripción, inscripcionData y materia para usar luego
        InscripcionData inscData = new InscripcionData();
        Inscripcion insc = new Inscripcion();
        Materia m = new Materia();

        //Creamos una variable con la materia seleccionada en la vista
        int filaSeleccionada = jTablaMaterias.getSelectedRow();
        
        //Seteamos los atributos a la materia antes instanciada
        m.setIdMateria((int) jTablaMaterias.getValueAt(filaSeleccionada, 0));
        m.setNombre((String) jTablaMaterias.getValueAt(filaSeleccionada, 1));
        m.setAnio((int) jTablaMaterias.getValueAt(filaSeleccionada, 2));
        
        //Le seteamos a inscripción el alumno seleccionado y la materia antes instanciada
        insc.setAlumno((Alumno) jcbSeleccionarAlumno.getSelectedItem());
        insc.setMateria(m);
        
        //Guardamos la inscripción en BD
        inscData.guardarInscripcion(insc);
        
        //Quitamos la 'fila' de la materia recién inscripta
        modelo.removeRow(filaSeleccionada);
    }//GEN-LAST:event_jbInscribirActionPerformed

    private void jbAnularInscActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbAnularInscActionPerformed
        //Instanciamos inscripcionData, alumno y materia para usar luego
        InscripcionData inscData = new InscripcionData();
        Materia m = new Materia();
        Alumno a = new Alumno();
        
        //Creamos una variable con la materia seleccionada en la vista
        int filaSeleccionada = jTablaMaterias.getSelectedRow();
        
        //Le seteamos al alumno antes instanciado el alumno seleccionado en la vista
        a = (Alumno) jcbSeleccionarAlumno.getSelectedItem();
        
        //Le seteamos a la materia antes instanciada el id de la materia seleccionada en la vista
        m.setIdMateria((int) jTablaMaterias.getValueAt(filaSeleccionada, 0));
        
        //Borramos la inscripción en BD
        inscData.borrarInscripcionMateriaAlumno(a.getIdAlumno(), m.getIdMateria());
        
        //Quitamos la 'fila' de la materia cuya inscripción recién borramos
        modelo.removeRow(filaSeleccionada);
    }//GEN-LAST:event_jbAnularInscActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTablaMaterias;
    private javax.swing.JButton jbAnularInsc;
    private javax.swing.JButton jbInscribir;
    private javax.swing.JButton jbSalir;
    private javax.swing.JComboBox<Alumno> jcbSeleccionarAlumno;
    private javax.swing.JRadioButton jrbMatInscriptas;
    private javax.swing.JRadioButton jrbMatNOInscriptas;
    // End of variables declaration//GEN-END:variables

    private void armarTabla() {
        //Agregamos las cabeceras a la tabla
        modelo.addColumn("id");
        modelo.addColumn("nombre");
        modelo.addColumn("año");
        
        //Seteamos el modelo a la tabla
        jTablaMaterias.setModel(modelo);
        
        //Impedimos el reordenamiento de la cabecera
        jTablaMaterias.getTableHeader().setReorderingAllowed(false);
        
        //para centrar las celdas del encabezado
        DefaultTableCellRenderer header = (DefaultTableCellRenderer) jTablaMaterias.getTableHeader().getDefaultRenderer();
        header.setHorizontalAlignment(SwingConstants.CENTER);

        //para centrar los datos de la primera columna
        DefaultTableCellRenderer tcr0 = new DefaultTableCellRenderer();
        tcr0.setHorizontalAlignment(SwingConstants.CENTER);
        jTablaMaterias.getColumnModel().getColumn(0).setCellRenderer(tcr0);

        //Para centrar los datos de la tercera columna
        tcr0.setHorizontalAlignment(SwingConstants.CENTER);
        jTablaMaterias.getColumnModel().getColumn(2).setCellRenderer(tcr0);
    }

    private void borrarFilas() {
        //Evita la repetición de las filas en la tabla
        int f = jTablaMaterias.getRowCount() - 1;
        for (; f >= 0; f--) {
            modelo.removeRow(f);
        }
    }

    private void cargarAlumnos() {
        //Cargamos los alumnos al jComboBox
        AlumnoData ad = new AlumnoData();

        for (Alumno item : ad.listarAlumno()) {
            jcbSeleccionarAlumno.addItem(item);
        }
    }

    private void cargarMaterias() {
        //Cargamos las materias a la tabla
        MateriaData md = new MateriaData();

        for (Materia materia : md.listarMateria()) {
            modelo.addRow(new Object[]{materia.getIdMateria(), materia.getNombre(), materia.getAnio()});
        }
    }
}
