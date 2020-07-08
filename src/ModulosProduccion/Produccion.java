/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModulosProduccion;

import Jcombox.recetas;
import Metodos.abm;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import gourmet.conexionBD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class Produccion extends javax.swing.JDialog {

    /**
     * Creates new form Produccion
     */
     conexionBD cn;
     private abm abm;
     private ResultSet rs,rscbo,rst;
     DefaultTableModel modelo;
     private Object[] filas;
     private boolean v_control;

    public Produccion() {
        
        initComponents();
        
        cn= new conexionBD();
        
        abm= new abm();
        
        
        verproducto();
        cargarproducto("");
        
        txtCodigo.setVisible(false);
        
        
        
    }
    
     void agregartablafactura(){
        
       DefaultTableModel tabla = (DefaultTableModel) this.tablaProduccion.getModel();
        
       //int iva= tablafactura.getSelectedRow();
       
       if(txtCantidad.getText().isEmpty()){
           JOptionPane.showMessageDialog(null,"Completar cantidad");
           txtCantidad.requestFocus();
       }
       else{
           
           Object[] valor = new Object[3];
           valor[0] = (String) "";
           valor[1] = (String)"";
           valor[2] = (String) txtCantidad.getText();
          // valor[3] = (String) txtMedida.getText();
          
           
           tabla.addRow(valor);
           
        }
 }

     public void cargaringredientes(String valor ){
        String [] titulos  = {"Codigo","Ingredientes","Cantidad","Cantidad a Producir","Medida"};
        String [] registros  = new String [5];
        String sql=null;
         sql = "select p.idproducto,p.descripcion as ingredientes,cantidad,m.descripcion as medida from productos as p ";
         sql+=" inner join recetas as r on(r.idinsumo=p.idproducto) ";
         sql+="inner join medida as m on(p.idmedida=m.idmedida)";
         sql+= " where tipo='insumo' and r.idproducto="+txtCodigo.getText();
         
        // sql+=" where idproducto="+txtIdProducto.getText();
        
        modelo = new DefaultTableModel(null, titulos);
    
        conexionBD cn = new conexionBD();
        Connection cnn = (Connection) cn.ConectarBD();
        Statement  st;
        try {
            
            st = (Statement) cnn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                registros[0] = rs.getString("idproducto");
                registros[1] = rs.getString("ingredientes");
                registros[2] = rs.getString("cantidad");
                registros[4] = rs.getString("medida");
//                registros[2] = rs.getString("preciodeventa");
//                registros[3] = rs.getString("gananciaproducto");
                
//                registros[6] = rs.getString("recetas");
//                registros[7] = rs.getString("proveedor");
//                registros[7] = rs.getString("categoria");
//                registros[8] = rs.getString("iva");
                modelo.addRow(registros);
                }
            tablaProduccion.setModel(modelo);
            
            } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex);
                            }   
    }
    
     void veringredientes(){
        try{
            Statement consultaingre = (Statement) conexionBD.ConectarBD().createStatement();
            ResultSet rs = consultaingre.executeQuery("select p.idproducto,p.descripcion as ingredientes,cantidad,m.descripcion as medida from productos as p " +
                       "inner join recetas as r on(r.idinsumo=p.idproducto)"
                    + " inner join medida as m on(p.idmedida=m.idmedida)  where tipo='insumo' and r.idproducto="+txtCodigo.getText()+"");
            
            modelo = new DefaultTableModel();
            tablaProduccion.setModel(modelo);
            
                modelo.addColumn("Codigo");
                modelo.addColumn("Ingredientes");
                modelo.addColumn("Cantidad");
                modelo.addColumn("Medida");
                //modelo.addColumn("Cantidad a Producir");
            
//            modelo.addColumn("Marca");
//            modelo.addColumn("Categoria");
           
        //    modelo.addColumn("Proveedor");
            
            
            filas = new Object[modelo.getColumnCount()];
            
            while(rs.next()){
                for(int i=0;i<modelo.getColumnCount();i++){
                    filas[i]=rs.getObject(i+1);
                }
                
                modelo.addRow(filas);
                
            }
            
           tablaProduccion.setModel(modelo);
    
        }catch(Exception e){
            System.out.println("Error al mostrar datos en la tabla "+e.getMessage());
        }
    }
    
//    void listaingredientes() {
//        try {
//            Statement consulta = (Statement) conexionBD.ConectarBD().createStatement();
//            rst = consulta.executeQuery("select p.descripcion as ingredientes,cantidad from productos as p \n" +
//                       "inner join recetas as r on(r.idinsumo=p.idproducto) where tipo='insumo' and r.idproducto="+txtCodigo.getText()+"");
//            
//            
//            String ingredientes = rst.getString("ingredientes");
//            Integer cantidad = rst.getInt("cantidad");
//            
//            txtingrediente.setText(ingredientes);
//            txtExistencia.setText(Integer.toString(cantidad));
//                            
//             
//        } catch (SQLException ex) {
//                }
//}
    
      public void cargarproducto(String valor ){
        String [] titulos  = {"Codigo","Producto"};
        String [] registros  = new String [2];
        String sql=null;
         sql = "select idproducto,descripcion from productos where tipo='producto'";
         sql+="  order by idproducto";
        // sql+=" where idproducto="+txtIdProducto.getText();
        
        modelo = new DefaultTableModel(null, titulos);
    
        conexionBD cn = new conexionBD();
        Connection cnn = (Connection) cn.ConectarBD();
        Statement  st;
        try {
            
            st = (Statement) cnn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                registros[0] = rs.getString("idproducto");
                registros[1] = rs.getString("descripcion");
//                registros[2] = rs.getString("preciodeventa");
//                registros[3] = rs.getString("gananciaproducto");
                
//                registros[6] = rs.getString("recetas");
//                registros[7] = rs.getString("proveedor");
//                registros[7] = rs.getString("categoria");
//                registros[8] = rs.getString("iva");
                modelo.addRow(registros);
                }
            tablaProducto.setModel(modelo);
            
            } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex);
                            }   
    }
     void verproducto(){
        try{
            Statement consultamarca = (Statement) conexionBD.ConectarBD().createStatement();
            ResultSet rs = consultamarca.executeQuery("select idproducto,descripcion from productos where tipo='producto'");
            
            modelo = new DefaultTableModel();
            tablaProducto.setModel(modelo);
            
            
            modelo.addColumn("Codigo");
            modelo.addColumn("Producto");
            
//            modelo.addColumn("Marca");
//            modelo.addColumn("Categoria");
           
        //    modelo.addColumn("Proveedor");
            
            
            filas = new Object[modelo.getColumnCount()];
            
            while(rs.next()){
                for(int i=0;i<modelo.getColumnCount();i++){
                    filas[i]=rs.getObject(i+1);
                }
                
                modelo.addRow(filas);
                
            }
            
            tablaProducto.setModel(modelo);
    
        }catch(Exception e){
            System.out.println("Error al mostrar datos en la tabla"+e.getMessage());
        }
    }
     
      public void seleccionartabla(){
        
        
       DefaultTableModel tabla = (DefaultTableModel) this.tablaProducto.getModel();
       
       
       int c= tablaProducto.getSelectedRow();
       
       if(c==-1){
           System.out.println("Seleccione un registro");
            }
       else{
           String id = (String) tablaProducto.getValueAt(c, 0);       
           String producto = (String) tablaProducto.getValueAt(c, 1);
                 
           this.txtCodigo.setText(id);
           this.txtProducto.setText(producto);
           
            
           
           
           }       
    }
    
      void multiplicaciondetabla(){
        //double sumatoriaTotal = 0;
        int totalRow = tablaProduccion.getRowCount();
        totalRow -= 1;
        int z = 0;
        for (int i = 0; i <= (totalRow); i++) {
          
            int valortabla = Integer.parseInt(String.valueOf(tablaProduccion.getValueAt(i, 1)));
            z = valortabla*(Integer.parseInt(txtCantidad.getText()));
  
        }
    }
      void agregartabla(){          
                                    
        int totalRow = tablaProduccion.getRowCount();
        totalRow -= 1;
        int z = 0;
        for (int i = 0; i <= (totalRow); i++) {
          
            try {
                int valortabla = Integer.parseInt(String.valueOf(tablaProduccion.getValueAt(i, 2)));
                z = valortabla*(Integer.parseInt(txtCantidad.getText()));//24
                Statement consultaingre = (Statement) conexionBD.ConectarBD().createStatement();
                ResultSet rs = consultaingre.executeQuery("select stock from productos where tipo='insumo' and idproducto='"+tablaProduccion.getValueAt(i, 0)+"'");
                rs.first();
                int stock= rs.getInt("stock");//12
                if(stock>=z ){
                        tablaProduccion.setValueAt(z, i, 3);
                        btnGuardar.setEnabled(true);
                   }
                else{
                    JOptionPane.showMessageDialog(null, "Inusmo insuficiente para realizar la produccion");
                    btnGuardar.setEnabled(false);
                    return;
                   // tablaProduccion.setValueAt(stock, i, 3);
                }
                       
            } catch (SQLException ex) {
                Logger.getLogger(Produccion.class.getName()).log(Level.SEVERE, null, ex);
            }
            
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

        buscarProductos = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaProducto = new javax.swing.JTable();
        btnAceptar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnBuscar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProduccion = new javax.swing.JTable();
        btnGuardar = new javax.swing.JButton();
        txtProducto = new javax.swing.JTextField();
        txtCodigo = new javax.swing.JTextField();
        btnAgregar = new javax.swing.JButton();
        txtCantidadDos = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnSalir = new javax.swing.JButton();

        tablaProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Producto"
            }
        ));
        tablaProducto.getTableHeader().setReorderingAllowed(false);
        tablaProducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProductoMouseClicked(evt);
            }
        });
        tablaProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaProductoKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaProductoKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tablaProductoKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(tablaProducto);

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buscarProductosLayout = new javax.swing.GroupLayout(buscarProductos.getContentPane());
        buscarProductos.getContentPane().setLayout(buscarProductosLayout);
        buscarProductosLayout.setHorizontalGroup(
            buscarProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buscarProductosLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(299, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buscarProductosLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnAceptar)
                .addContainerGap())
        );
        buscarProductosLayout.setVerticalGroup(
            buscarProductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buscarProductosLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAceptar)
                .addContainerGap(384, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("Pizza:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(81, 89, -1, -1));

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buscar_1.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        getContentPane().add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 60, 30));

        jLabel2.setText("Cantidad:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(62, 124, -1, -1));
        getContentPane().add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 120, 110, -1));

        tablaProduccion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "codigo", "Descripcion", "Cantidad Receta", "cantidas", "unidad"
            }
        ));
        jScrollPane1.setViewportView(tablaProduccion);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 420, 170));

        btnGuardar.setText("Guardar");
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 370, -1, -1));

        txtProducto.setEnabled(false);
        getContentPane().add(txtProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 110, -1));
        getContentPane().add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 70, -1));

        btnAgregar.setText("Agregar Cantidad");
        btnAgregar.setEnabled(false);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        getContentPane().add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, -1, 20));

        txtCantidadDos.setEnabled(false);
        getContentPane().add(txtCantidadDos, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 340, 70, -1));

        jLabel4.setText("Total a preparar:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 340, -1, -1));

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/equis.png"))); // NOI18N
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 370, 70, 20));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductoMouseClicked
        seleccionartabla();
        
        veringredientes();
        cargaringredientes("");
       
    }//GEN-LAST:event_tablaProductoMouseClicked

    private void tablaProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaProductoKeyPressed

    }//GEN-LAST:event_tablaProductoKeyPressed

    private void tablaProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaProductoKeyReleased
        seleccionartabla();
    }//GEN-LAST:event_tablaProductoKeyReleased

    private void tablaProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaProductoKeyTyped

    }//GEN-LAST:event_tablaProductoKeyTyped

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        // TODO add your handling code here:
        buscarProductos.setModal(true);
        buscarProductos.setSize(450, 370);
        buscarProductos.setLocationRelativeTo(this);
        buscarProductos.setVisible(true);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        // TODO add your handling code here:
        veringredientes();
        cargaringredientes("");
        btnAgregar.setEnabled(true);
        this.buscarProductos.dispose();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
        //agregartablafactura();
       agregartabla();
       txtCantidadDos.setText(txtCantidad.getText());
       txtCantidad.setText("");
       
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
         try {

                    abm.start();

                   // int idempleado = abm.idUsuario;

//                    rs = abm.nuevo("idpedido", "pedido");
//                    rs.first();
//                    txtidfac.setText(String.valueOf(rs.getInt("codigo")+1));

                    
                        
                        v_control=abm.modificar("productos","stock=stock+'"+txtCantidadDos.getText()+"'", "tipo='producto' and idproducto="+txtCodigo.getText());
                        
                                    if (v_control==true){
                                                JOptionPane.showMessageDialog(null,"Se ha guardado los datos en productos");              
                                                }
                    

                    if (v_control == true) {

                        for (int i = 0; i < tablaProduccion.getRowCount(); i++) {
                            Statement consulta = (Statement) conexionBD.ConectarBD().createStatement();
                            consulta.execute("update productos set stock=stock-"+tablaProduccion.getValueAt(i, 3)+
                                " where tipo='insumo' and idproducto="+tablaProduccion.getValueAt(i, 0));
                            
                                
                            
                        }
                        if (v_control == false) {
                            abm.roolback();
                        }

                    }

                    //confirmo la transacion
                    if (v_control == true) {
                        abm.comit();
                    }

                    abm.end();
                    JOptionPane.showMessageDialog(null, "Los Datos se han actualizado satisfactoriamente");
                } catch (Exception ex) {
                    System.out.println("Error al realizar la transacion " + ex.getMessage());
                    JOptionPane.showMessageDialog(null, "Completar todos los campos para guardar");

                }
         
        btnGuardar.setEnabled(false);
        btnAgregar.setEnabled(false);
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

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
            java.util.logging.Logger.getLogger(Produccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Produccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Produccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Produccion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new Produccion().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JDialog buscarProductos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablaProduccion;
    private javax.swing.JTable tablaProducto;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCantidadDos;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtProducto;
    // End of variables declaration//GEN-END:variables
}
