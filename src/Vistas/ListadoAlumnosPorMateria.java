package Vistas;

import AccesoADatos.InscripcionData;
import AccesoADatos.MateriaData;
import Entidades.Alumno;
import Entidades.Materia;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ListadoAlumnosPorMateria extends javax.swing.JInternalFrame {

    //Cargamos el modelo de tabla
    private final DefaultTableModel modelo = new DefaultTableModel() {
        //Hacemos la tabla no-editable en todas sus celdas
        public boolean isCellEditable(int f, int c) {
            return false;
        }
    };

    public ListadoAlumnosPorMateria() {
        initComponents();

        //Carga las materias al jComboBox
        cargarMaterias();

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
        jcbSeleccionarMateria = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtTablaAlumnosPorMat = new javax.swing.JTable();
        jbSalir = new javax.swing.JButton();

        setPreferredSize(new java.awt.Dimension(600, 500));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Listado de Alumnos por Materia");

        jLabel2.setText("Seleccione una Materia");

        jcbSeleccionarMateria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbSeleccionarMateriaActionPerformed(evt);
            }
        });

        jtTablaAlumnosPorMat.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jtTablaAlumnosPorMat);

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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jbSalir))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                        .addComponent(jcbSeleccionarMateria, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 93, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jcbSeleccionarMateria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(jbSalir)
                .addGap(85, 85, 85))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jbSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jbSalirActionPerformed
        //Deselecciona, invisibiliza y cierra la ventana
        this.dispose();
    }//GEN-LAST:event_jbSalirActionPerformed

    private void jcbSeleccionarMateriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbSeleccionarMateriaActionPerformed
        //Instanciamos inscripcionData para usar luego
        InscripcionData inscData = new InscripcionData();

        //Creamos una materia y le asignamos la seleccionada en la vista
        Materia m = (Materia) jcbSeleccionarMateria.getSelectedItem();

        //Borramos las filas evitando repeticiones
        borrarFilas();

        //Si una materia esta activa, carga sus alumnos en la tabla
        if (m.isActivo()) {
            for (Alumno alu : inscData.obtenerAlumnosPorMateria(m.getIdMateria())) {
                modelo.addRow(new Object[]{alu.getIdAlumno(), alu.getDni(), alu.getApellido(), alu.getNombre()});
            }
        }
    }//GEN-LAST:event_jcbSeleccionarMateriaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jbSalir;
    private javax.swing.JComboBox<Materia> jcbSeleccionarMateria;
    private javax.swing.JTable jtTablaAlumnosPorMat;
    // End of variables declaration//GEN-END:variables

    private void armarTabla() {
        //Agregamos las cabeceras a la tabla
        modelo.addColumn("Id Alumno");
        modelo.addColumn("DNI");
        modelo.addColumn("Apellido");
        modelo.addColumn("Nombre");

        //Seteamos el modelo a la tabla
        jtTablaAlumnosPorMat.setModel(modelo);

        //Impedimos el reordenamiento de la cabecera
        jtTablaAlumnosPorMat.getTableHeader().setReorderingAllowed(false);

        //para centrar las celdas del encabezado
        DefaultTableCellRenderer header = (DefaultTableCellRenderer) jtTablaAlumnosPorMat.getTableHeader().getDefaultRenderer();
        header.setHorizontalAlignment(SwingConstants.CENTER);

        //para centrar los datos de la primera columna
        DefaultTableCellRenderer tcr0 = new DefaultTableCellRenderer();
        tcr0.setHorizontalAlignment(SwingConstants.CENTER);
        jtTablaAlumnosPorMat.getColumnModel().getColumn(0).setCellRenderer(tcr0);

        //Para centrar los datos de la segunda columna
        tcr0.setHorizontalAlignment(SwingConstants.CENTER);
        jtTablaAlumnosPorMat.getColumnModel().getColumn(1).setCellRenderer(tcr0);
    }

    private void cargarMaterias() {
        //Cargamos las materias al jComboBox
        MateriaData md = new MateriaData();

        for (Materia item : md.listarMateria()) {
            jcbSeleccionarMateria.addItem(item);
        }
    }

    private void borrarFilas() {
        //Evita la repetición de las filas en la tabla
        while (modelo.getRowCount() > 0) {
            modelo.removeRow(0);
        }
    }
}
