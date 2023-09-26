package Vistas;

import AccesoADatos.AlumnoData;
import AccesoADatos.InscripcionData;
import Entidades.Alumno;
import Entidades.Inscripcion;
import Entidades.Materia;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class CargaDeNotas extends javax.swing.JInternalFrame {

    //Cargamos el modelo de tabla
    private final DefaultTableModel modelo = new DefaultTableModel() {
        //Hacemos editable la columna 2
        public boolean isCellEditable(int f, int c) {
            if (c == 2) {
                return true;
            } else {
                return false;
            }
        }
    };

    public CargaDeNotas() {
        initComponents();

        //Carga los alumnos al jComboBox
        cargarAlumnos();

        //Carga la estructura de la tabla
        armarTabla();
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
        jLabel2 = new javax.swing.JLabel();
        jcbSeleccionarAlumno = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtTablaNotas = new javax.swing.JTable();
        jbGuardar = new javax.swing.JButton();
        jbSalir = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(600, 500));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Carga de Notas");

        jLabel2.setText("Seleccione un Alumno");

        jcbSeleccionarAlumno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbSeleccionarAlumnoActionPerformed(evt);
            }
        });

        jtTablaNotas.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtTablaNotas);

        jbGuardar.setText("Guardar");
        jbGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jbGuardarActionPerformed(evt);
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
            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jcbSeleccionarAlumno, javax.swing.GroupLayout.PREFERRED_SIZE, 282, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 554, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jbGuardar)
                        .addGap(158, 158, 158)
                        .addComponent(jbSalir, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(21, 21, 21))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jcbSeleccionarAlumno, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(59, 59, 59)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jbGuardar)
                    .addComponent(jbSalir))
                .addGap(51, 51, 51))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirActionPerformed
        //Deselecciona, invisibiliza y cierra la ventana
        this.dispose();
    }//GEN-LAST:event_jbSalirActionPerformed

    private void jcbSeleccionarAlumnoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbSeleccionarAlumnoActionPerformed
        //Instanciamos inscripcionData para usar luego
        InscripcionData inscData = new InscripcionData();

        //Creamos un alumno y le asignamos el alumno seleccionado en la vista
        Alumno a = (Alumno) jcbSeleccionarAlumno.getSelectedItem();

        //Borramos las filas evitando repeticiones
        borrarFilas();

        //Si es un alumno activo, carga las materias en las que está inscripto a la tabla
        if (a.isActivo()) {
            for (Inscripcion insc : inscData.obtenerInscripcionesPorAlumno(a.getIdAlumno())) {
                modelo.addRow(new Object[]{insc.getMateria().getIdMateria(), insc.getMateria().getNombre(), insc.getNota()});
            }
        }
    }//GEN-LAST:event_jcbSeleccionarAlumnoActionPerformed

    private void jbGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbGuardarActionPerformed
        //Instanciamos para usar luego
        InscripcionData inscData = new InscripcionData();
        Alumno a = new Alumno();
        Materia m = new Materia();
        
        //Asignamos al alumno instanciado, el seleccionado en la vista
        a = (Alumno) jcbSeleccionarAlumno.getSelectedItem();
        
        //Creamos una variable con la materia seleccionada en la vista
        int filaSeleccionada = jtTablaNotas.getSelectedRow();

        //Seteamos los atributos a la materia antes instanciada
        m.setIdMateria((int) jtTablaNotas.getValueAt(filaSeleccionada, 0));

        //Asignamos el valor de la vista en String
        String valor = jtTablaNotas.getValueAt(filaSeleccionada, 2).toString();
        
        //a.toString();
        
        try {
            //Pasamos valor de String a double
            double d = Double.parseDouble(valor);
            
            //Llamamos al método que actualizará la nota
            inscData.actualizarNota(a.getIdAlumno(), m.getIdMateria(), d);
            
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "La celda debe contener un número");
        }
    }//GEN-LAST:event_jbGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbGuardar;
    private javax.swing.JButton jbSalir;
    private javax.swing.JComboBox<Alumno> jcbSeleccionarAlumno;
    private javax.swing.JTable jtTablaNotas;
    // End of variables declaration//GEN-END:variables

    private void armarTabla() {
        //Agregamos las cabeceras a la tabla
        modelo.addColumn("Id");
        modelo.addColumn("nombre");
        modelo.addColumn("nota");

        //Seteamos el modelo a la tabla
        jtTablaNotas.setModel(modelo);

        //Impedimos el reordenamiento de la cabecera
        jtTablaNotas.getTableHeader().setReorderingAllowed(false);

        //para centrar las celdas del encabezado
        DefaultTableCellRenderer header = (DefaultTableCellRenderer) jtTablaNotas.getTableHeader().getDefaultRenderer();
        header.setHorizontalAlignment(SwingConstants.CENTER);

        //para centrar los datos de la primera columna
        DefaultTableCellRenderer tcr0 = new DefaultTableCellRenderer();
        tcr0.setHorizontalAlignment(SwingConstants.CENTER);
        jtTablaNotas.getColumnModel().getColumn(0).setCellRenderer(tcr0);

        //Para centrar los datos de la tercera columna
        tcr0.setHorizontalAlignment(SwingConstants.CENTER);
        jtTablaNotas.getColumnModel().getColumn(2).setCellRenderer(tcr0);
    }

    private void cargarAlumnos() {
        //Cargamos los alumnos al jComboBox
        AlumnoData ad = new AlumnoData();

        for (Alumno item : ad.listarAlumno()) {
            jcbSeleccionarAlumno.addItem(item);
        }
    }

    private void borrarFilas() {
        //Evita la repetición de las filas en la tabla
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }
}
