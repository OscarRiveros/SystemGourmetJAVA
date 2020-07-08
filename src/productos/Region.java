/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productos;

import Metodos.abm;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import gourmet.conexionBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class Region extends javax.swing.JDialog {

    /**
     * Creates new form Region
     */
    conexionBD cn;
     private abm abm;
     private ResultSet rs,rscbo;
     DefaultTableModel modelo;
     private Object[] filas;
     private static char opcion;
     private boolean vacio;
     private boolean v_control;//si guardo bien o mal
     
    public Region() {
        
        initComponents();
        
        cn= new conexionBD();
        
        abm= new abm();
        rs=abm.consulta("*", "region");
        MostrarRegistro();
        verregion();
        cargarregion("");
        
    }
    
    
     public void MostrarRegistro(){//metodo creado poara mostrar datos
    try{
        if(rs.getRow()!=0){ //devuelve numero de filas de una objeto de tipo resultset
            txtIdRegion.setText(rs.getString(1));
            txtDescriRegion.setText(rs.getString(2));
        }
        
    }catch(Exception e){
        System.out.println("error al mostrar resultados "+e.getMessage());
         }
    }
     
     void verregion(){
        try{
            Statement consultamarca = (Statement) conexionBD.ConectarBD().createStatement();
            ResultSet rs = consultamarca.executeQuery("select idregion, descripcion from region");
            
            modelo = new DefaultTableModel();
            tablaRegion.setModel(modelo);
            
            
            modelo.addColumn("Codigo");
            modelo.addColumn("Region");
            
            filas = new Object[modelo.getColumnCount()];
            
            while(rs.next()){
                for(int i=0;i<modelo.getColumnCount();i++){
                    filas[i]=rs.getObject(i+1);
                }                
                modelo.addRow(filas);
            }           
            tablaRegion.setModel(modelo);   
        }catch(Exception e){
            System.out.println("Error al mostrar datos en la tabla"+e.getMessage());
        }
    }
     
     public void cargarregion(String valor ){
        String [] titulos  = {"Codigo","Region"};
        String [] registros  = new String [2];
        
        String sql = "select * from region where estado=1";
               // + "where CONCAT(idciudad,' ',ciudad.descripcion) LIKE '%"+valor+"%'";  
        modelo = new DefaultTableModel(null, titulos);
    
        conexionBD cn = new conexionBD();
        Connection cnn = (Connection) cn.ConectarBD();
        Statement  st;
        try {
            
            st = (Statement) cnn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                registros[0] = rs.getString("idregion");
                registros[1] = rs.getString("descripcion");
                modelo.addRow(registros);
                }
            tablaRegion.setModel(modelo);
            
            } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex);
                            }   
    }
     public void cargarregionbuscar(){
        String [] titulos  = {"Codigo","Region"};
        String [] registros  = new String [2];
        String sql = "select idregion, descripcion from region where estado=1 CONCAT(idregion,descripcion) LIKE  '%"+txtBuscar.getText()+"%'";  
         
        
         
        modelo = new DefaultTableModel(null, titulos);
    
        conexionBD cnp = new conexionBD();
        Connection cnn = (Connection) cnp.ConectarBD();
        Statement  st;
        try {
            
            st = (com.mysql.jdbc.Statement) cnn.createStatement();
            ResultSet rst = st.executeQuery(sql);
            
            while(rst.next()){
                registros[0] = rst.getString("idregion");
                registros[1] = rst.getString("descripcion");
                
                modelo.addRow(registros);
                }
            tablaRegion.setModel(modelo);
            
            } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex);
                            }   
    }
      public void cargarregionbuscardos(){
        String sql = "select descripcion from region where idregion LIKE '"+txtIdRegion.getText()+"'";  
         

        conexionBD cnp = new conexionBD();
        Connection cnn = (Connection) cnp.ConectarBD();
        Statement  st;
        try {
            
            st = (com.mysql.jdbc.Statement) cnn.createStatement();
            ResultSet rst = st.executeQuery(sql);
           
                while(rst.next()){
               // registros[0] = rst.getString("idmarca");
               // this.txtDescriMarca.setText(rst.getString("descripcion")); 
                 
                     
                this.txtDescriRegion.setText(rst.getString("descripcion")); 
                
               // modelo.addRow(registros);
                
            }
            
           
            
            } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex);
                            }   
    }
      
      public void limpiar(){
    txtIdRegion.setText("");
    txtDescriRegion.setText("");
    }
    
    
    public  boolean validardatos(){
   vacio=false;
   if(txtDescriRegion.getText().isEmpty()){
       vacio=true;
        }
   return vacio;
}
    
     public void habilitarbotones(boolean h){ //metodo encargado de habilitar o deshabilitar botonoes
    btnEditar.setEnabled(h);
    btnEliminar.setEnabled(h);
    btnAgregar.setEnabled(h);
        }
     public void habilitarbotones2(boolean j){ //metodo encargado de habilitar o deshabilitar botonoes
    btnCancelar.setEnabled(j);
    btnGuardar.setEnabled(j);
        }
     public void habilitarcampos(boolean h){ //metodo encargado de habilitar o deshabilitar botonoes
     txtDescriRegion.setEnabled(h);
     txtIdRegion.setEnabled(h);
}
     
     public void seleccionartabla(){
        
        
       DefaultTableModel tabla = (DefaultTableModel) this.tablaRegion.getModel();
       
       
       int c= tablaRegion.getSelectedRow();
       
       if(c==-1){
           System.out.println("Seleccione un registro");
            }
       else{
           String id = (String) tablaRegion.getValueAt(c, 0);       
           String marca = (String) tablaRegion.getValueAt(c, 1);
           this.txtIdRegion.setText(id);
           this.txtDescriRegion.setText(marca);
           
            
           }       
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        txtIdRegion = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtDescriRegion = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnSalir = new javax.swing.JToggleButton();
        btnAgregar = new javax.swing.JToggleButton();
        btnCancelar = new javax.swing.JToggleButton();
        btnGuardar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaRegion = new javax.swing.JTable();
        btnEditar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel3.setFont(new java.awt.Font("Bell MT", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 204, 0));
        jLabel3.setText("Agregar Region");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 273, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(167, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel3)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtIdRegion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdRegionActionPerformed(evt);
            }
        });
        txtIdRegion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdRegionKeyReleased(evt);
            }
        });
        jPanel2.add(txtIdRegion, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 50, 30));

        jLabel1.setText("Region:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, -1, 18));
        jPanel2.add(txtDescriRegion, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 120, 30));

        jLabel2.setText("Id Ciudad: ");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, -1, 18));

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cancel.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel2.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 270, 100, 50));

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/add.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAgregar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel2.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 270, 110, 50));

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/clear.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setEnabled(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel2.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 270, 110, 50));

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/articulos.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel2.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, -1, 50));

        tablaRegion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Region"
            }
        ));
        tablaRegion.getTableHeader().setReorderingAllowed(false);
        tablaRegion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaRegionMouseClicked(evt);
            }
        });
        tablaRegion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaRegionKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablaRegion);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 410, 200));

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/actualizar.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel2.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 140, 120, -1));

        jLabel4.setText("*");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 10, 20, -1));

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/basureronegro.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel2.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 200, 120, -1));

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        jPanel2.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 70, 120, 30));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buscar.png"))); // NOI18N
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 70, -1, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 204, 0));
        jLabel6.setText("Introduzca Region o Codigo");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 100, 150, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 589, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 352, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdRegionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdRegionActionPerformed
        // TODO add your handling code here:
        cargarregionbuscardos();
    }//GEN-LAST:event_txtIdRegionActionPerformed

    private void txtIdRegionKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdRegionKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdRegionKeyReleased

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try {
            opcion='n';
            limpiar();
            habilitarbotones(false);
            habilitarbotones2(true);
            habilitarcampos(true);
            //rs = abm.nuevo("idregion", "region");
           // rs.first();
            //txtIdRegion.setText(String.valueOf(rs.getInt("codigo")+1));
            txtIdRegion.requestFocus();//mantiene el enfoque en un objeto
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"error al generar el codigo "+e);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        habilitarbotones2(false);
        habilitarbotones(true);
        habilitarcampos(false);
        rs=abm.consulta("*", "region");
        MostrarRegistro();
        verregion();
        cargarregion("");
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        vacio=validardatos();
        if(vacio==true){
            JOptionPane.showMessageDialog(null,"Completar los datos marcados en aterisco");

        }else{
            switch(opcion){
                case 'n':
                conexionBD cn = new conexionBD();// se crea la conexion
                Connection cnn = (Connection) cn.ConectarBD();
                Statement  st;
                try {
                    String consulta="";
                    consulta=("Select * from region where descripcion='"+txtDescriRegion.getText()+"'");
                    st= (Statement) cnn.createStatement();
                    ResultSet rs= st.executeQuery(consulta);
                    rs.first();
                    if(rs.getRow()!= 0){
                        JOptionPane.showMessageDialog(null, "Esta region ya existe");
                        txtDescriRegion.setText("");
                        txtDescriRegion.requestFocus();
                    }else{
                        
                        
                        v_control=abm.insertar("region",txtIdRegion.getText()+",'"+txtDescriRegion.getText()+"',"+1);
                        if (v_control==true){
                            JOptionPane.showMessageDialog(null,"Se ha guardado los datos");
                        }
                    }

                }catch(Exception e){
                    System.out.println("Error al mostrar datos en la tabla"+e.getMessage());
                }

                break;
                case 'm':
                v_control= abm.modificar("region", "descripcion='"+txtDescriRegion.getText()+"'", "idregion="+txtIdRegion.getText());
                if(v_control==true){
                    JOptionPane.showMessageDialog(null,"Datos actualizados congratuleishon");
                }
                break;
            }
            habilitarcampos(false);
            habilitarbotones(true);
            habilitarbotones2(false);
            /* rs=abm.consulta("*", "marca");
            MostrarRegistro();*/
            verregion();
            cargarregion("");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void tablaRegionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaRegionMouseClicked
        seleccionartabla();
    }//GEN-LAST:event_tablaRegionMouseClicked

    private void tablaRegionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaRegionKeyPressed
        seleccionartabla();
    }//GEN-LAST:event_tablaRegionKeyPressed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        opcion='m';
        habilitarcampos(true);
        habilitarbotones(false);
        habilitarbotones2(true);
        txtDescriRegion.requestFocus();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
         v_control= abm.modificar("region", "estado='"+0+"'", "idregion="+txtIdRegion.getText());
//        v_control = abm.eliminar("region", "idregion="+txtIdRegion.getText());
        if(v_control==true){
            rs= abm.consulta("*", "region");
            MostrarRegistro();
            JOptionPane.showMessageDialog(null,"Datos Eliminados congratuleishon");
        }
        /*rs=abm.consulta("*", "marca");
        MostrarRegistro();*/
        verregion();
        cargarregion("");
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        // TODO add your handling code here:
        if(txtBuscar.getText().isEmpty()){
            cargarregion("");
        }
        else{cargarregionbuscar();
        }
    }//GEN-LAST:event_txtBuscarKeyReleased

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
            java.util.logging.Logger.getLogger(Region.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Region.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Region.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Region.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Region().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnAgregar;
    private javax.swing.JToggleButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JToggleButton btnSalir;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaRegion;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtDescriRegion;
    private javax.swing.JTextField txtIdRegion;
    // End of variables declaration//GEN-END:variables
}
