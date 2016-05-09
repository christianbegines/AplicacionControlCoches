/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Controller.GestionCoches;
import Modelo.Coche;
import java.io.File;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 * Clase que extiende de JFrame y implemente la aplicacion para
 * cargar coches en la tabla.
 * @author christian begines
 */
public class AplicacionCoches extends javax.swing.JFrame {
    
    
   
    /**
     * Constructor el cual crea una Gestion coche y se conecta a la base de datos
     * a traves del metodo <code>crearConexion()</code>.
     */    
    public AplicacionCoches() {
        initComponents();
        gc= new GestionCoches();
        try {
            if(gc.crearConexion()==true){
                this.textoConexion.setText("Conexion establecida");
            }            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "!Error " + ex.getMessage() + ex.getErrorCode(), "Ventana check", JOptionPane.INFORMATION_MESSAGE);
        
        } catch (Exception e) {
            System.out.println(e);
       }
    }

    /**
     * Codigo generado automaticamente por netbeans
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonCargarTabla = new javax.swing.JButton();
        textoRuta = new javax.swing.JTextField();
        labelText = new javax.swing.JLabel();
        botonCargarDatos = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaCoches = new javax.swing.JTable();
        textoConexion = new javax.swing.JLabel();
        botonBorrar = new javax.swing.JButton();
        botonMostrar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        botonCargarTabla.setText("cargar tabla coches");
        botonCargarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCargarTablaActionPerformed(evt);
            }
        });

        textoRuta.setText("Haz click para insertar la ruta del archivo");
        textoRuta.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        textoRuta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textoRutaMouseClicked(evt);
            }
        });

        labelText.setText("archivo origen de datos");

        botonCargarDatos.setText("Cargar datos");
        botonCargarDatos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCargarDatosActionPerformed(evt);
            }
        });

        tablaCoches.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "matricula", "modelo", "marca", "color", "año", "precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tablaCoches);

        textoConexion.setToolTipText("");

        botonBorrar.setText("Borrar tabla");
        botonBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBorrarActionPerformed(evt);
            }
        });

        botonMostrar.setText("Mostrar Tabla Coches");
        botonMostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMostrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelText, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textoRuta, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonCargarDatos)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(textoConexion, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonBorrar))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(40, 40, 40)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 495, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addGap(191, 191, 191)
                            .addComponent(botonCargarTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(32, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addComponent(botonMostrar, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonCargarTabla)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoRuta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelText)
                    .addComponent(botonCargarDatos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonMostrar)
                .addGap(38, 38, 38)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(botonBorrar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(textoConexion, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Control de ActionPerformed del boton cargarTabla
     * @param evt evento producido por el boton
     */
    private void botonCargarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCargarTablaActionPerformed
      boolean resultado;
        try {
            resultado=gc.crearTablaCoches();
            if(resultado==false){
                JOptionPane.showMessageDialog(rootPane, "Tabla Creada");
            } else {
                JOptionPane.showMessageDialog(rootPane, "Tabla no creada", null, JOptionPane.WARNING_MESSAGE);
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "!Error " + ex.getMessage() + ex.getErrorCode(), "Ventana check", JOptionPane.INFORMATION_MESSAGE);
        }
      
    }//GEN-LAST:event_botonCargarTablaActionPerformed

    private void textoRutaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textoRutaMouseClicked
       final JFileChooser fc = new JFileChooser();
       int indice = fc.showOpenDialog(null);
       if (indice == JFileChooser.APPROVE_OPTION){
           fichero =fc.getSelectedFile();
           textoRuta.setText(fichero.getAbsolutePath());
       }
       
    }//GEN-LAST:event_textoRutaMouseClicked

    private void botonCargarDatosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCargarDatosActionPerformed
        int registros =gc.cargarTablaCoches(fichero);
        if(registros!=0){
            JOptionPane.showMessageDialog(rootPane, "Datos Cargados");
        }else {
                JOptionPane.showMessageDialog(rootPane, "Datos NO cargados", null, JOptionPane.WARNING_MESSAGE);
            }
        
        
    }//GEN-LAST:event_botonCargarDatosActionPerformed

    private void botonBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBorrarActionPerformed
        try {
            boolean respuesta;
            respuesta =gc.borrarTabla();
            tablaCoches.setModel(new DefaultTableModel());
            if(respuesta==false){
            JOptionPane.showMessageDialog(rootPane, "Tabla borrada");
        }else {
                JOptionPane.showMessageDialog(rootPane, "Tabla Borrada", null, JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException ex) {
           
        }
    }//GEN-LAST:event_botonBorrarActionPerformed

    private void botonMostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonMostrarActionPerformed
       String consulta= "select * from datos_coche";
       String [] filas = new String[6];
       String [] titulos = { "Matricula", "Marca","Modelo","Color","Año","Precio"};
       DefaultTableModel tabla=new DefaultTableModel(null,titulos);
       List<Coche> listaCoches = gc.mostrarCoches();
       for (Coche c: listaCoches){
           filas[0]=c.getMatricula();
           filas[1]=c.getMarca();
           filas[2]=c.getModelo();
           filas[3]=c.getColor();
           filas[4]=c.getAño().toString();
           filas[5]=c.getPrecio().toString();
           tabla.addRow(filas);
       }
       this.tablaCoches.setModel(tabla);
         
       
    }//GEN-LAST:event_botonMostrarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AplicacionCoches.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AplicacionCoches.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AplicacionCoches.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AplicacionCoches.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AplicacionCoches().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonBorrar;
    private javax.swing.JButton botonCargarDatos;
    private javax.swing.JButton botonCargarTabla;
    private javax.swing.JButton botonMostrar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelText;
    private javax.swing.JTable tablaCoches;
    private javax.swing.JLabel textoConexion;
    private javax.swing.JTextField textoRuta;
    // End of variables declaration//GEN-END:variables
    File fichero;
    private GestionCoches gc;
}
