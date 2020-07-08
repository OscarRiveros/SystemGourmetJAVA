/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package productos;

import Jcombox.region;
import Metodos.abm;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import gourmet.conexionBD;

/**
 *
 * @author USER
 */
public class Departamento extends javax.swing.JDialog {

    /**
     * Creates new form Departamento
     */
     conexionBD cn;
     private abm abm;
     private ResultSet rs,  rscbo;
     DefaultTableModel modelo;
     private Object[] filas;
     private static char opcion;
     private boolean vacio;
     private boolean v_control;//si guardo bien o mal
     
    public Departamento() {
       
        initComponents();
        
        cn= new conexionBD();
        
        abm= new abm();
        rs=abm.consulta("*", "departamento");
        MostrarRegistro();
        verdepartamento();
        cargardepartamento("");
        comboxRegion();
    }
    
    private void comboxRegion(){
         try{
            rscbo = abm.consultasql("select * from region where estado=1");
            while(rscbo.next()){
                region dep= new region();
                dep.setCodigo(Integer.valueOf(rscbo.getString("idregion")));
                dep.setDescripcion(rscbo.getString("descripcion"));
                comboRegion.addItem(dep);
                }
            }
        catch(Exception e){
            e.printStackTrace();
        } 
}

      
    
    public void cargarmarcabuscardos(){
        String sql = "select descripcion from ciudad where idmarca LIKE '"+txtIdDepartamento.getText()+"'";  
         

        conexionBD cnp = new conexionBD();
        Connection cnn = (Connection) cnp.ConectarBD();
        Statement  st;
        try {
            
            st = (com.mysql.jdbc.Statement) cnn.createStatement();
            ResultSet rst = st.executeQuery(sql);
           
                while(rst.next()){
               // registros[0] = rst.getString("idmarca");
               // this.txtDescriMarca.setText(rst.getString("descripcion")); 
                 
                     
                this.txtDescriDepar.setText(rst.getString("descripcion")); 
                
               // modelo.addRow(registros);
                
            }
            
           
            
            } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex);
                            }   
    }
    
     public void MostrarRegistro(){//metodo creado poara mostrar datos
    try{
        if(rs.getRow()!=0){ //devuelve numero de filas de una objeto de tipo resultset
            txtIdDepartamento.setText(rs.getString(1));
            txtDescriDepar.setText(rs.getString(2));
        }
        
    }catch(Exception e){
        System.out.println("error al mostrar resultados"+e.getMessage());
         }
    }
     
     void verdepartamento(){
        try{
            Statement consultamarca = (Statement) conexionBD.ConectarBD().createStatement();
            ResultSet rs = consultamarca.executeQuery("select iddepartamento, descripcion from departamento where estado=1");
            
            modelo = new DefaultTableModel();
            tablaDepartamento.setModel(modelo);
            
            
            modelo.addColumn("Codigo");
            modelo.addColumn("Departamento");
            
            filas = new Object[modelo.getColumnCount()];
            
            while(rs.next()){
                for(int i=0;i<modelo.getColumnCount();i++){
                    filas[i]=rs.getObject(i+1);
                }                
                modelo.addRow(filas);
            }           
            tablaDepartamento.setModel(modelo);   
        }catch(Exception e){
            System.out.println("Error al mostrar datos en la tabla"+e.getMessage());
        }
    }
     
     public void cargardepartamento(String valor ){
        String [] titulos  = {"Codigo","Departamento","Region"};
        String [] registros  = new String [3];
        
        String sql = "select iddepartamento, departamento.descripcion, region.descripcion as region from departamento "
                + "inner join region on(departamento.idregion=region.idregion )where departamento.estado=1";  
        modelo = new DefaultTableModel(null, titulos);
    
        conexionBD cn = new conexionBD();
        Connection cnn = (Connection) cn.ConectarBD();
        Statement  st;
        try {
            
            st = (Statement) cnn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                registros[0] = rs.getString("iddepartamento");
                registros[1] = rs.getString("descripcion");
                registros[2] = rs.getString("region");
                modelo.addRow(registros);
                }
            tablaDepartamento.setModel(modelo);
            
            } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex);
                            }   
    }

      public void limpiar(){
    txtIdDepartamento.setText("");
    txtDescriDepar.setText("");
    }
    
    
    public  boolean validardatos(){
   vacio=false;
   if(txtDescriDepar.getText().isEmpty()){
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
     txtDescriDepar.setEnabled(h);
}
     public void seleccionartabla(){
        
        
       DefaultTableModel tabla = (DefaultTableModel) this.tablaDepartamento.getModel();
       
       
       int c= tablaDepartamento.getSelectedRow();
       
       if(c==-1){
           System.out.println("Seleccione un registro");
            }
       else{
           String id = (String) tablaDepartamento.getValueAt(c, 0);       
           String marca = (String) tablaDepartamento.getValueAt(c, 1);
           this.txtIdDepartamento.setText(id);
           this.txtDescriDepar.setText(marca);
           
            
           }       
    }
     
      public void cargarciudadbuscar(){
        String [] titulos  = {"Codigo","Departamento"};
        String [] registros  = new String [2];
        String sql = "select iddepartamento, descripcion from departamento where CONCAT(iddepartamento,' ',descripcion) LIKE  '%"+txtBuscar.getText()+"%'";  
         
        
         
        modelo = new DefaultTableModel(null, titulos);
    
        conexionBD cnp = new conexionBD();
        Connection cnn = (Connection) cnp.ConectarBD();
        Statement  st;
        try {
            
            st = (com.mysql.jdbc.Statement) cnn.createStatement();
            ResultSet rst = st.executeQuery(sql);
            
            while(rst.next()){
                registros[0] = rst.getString("iddepartamento");
                registros[1] = rst.getString("descripcion");
                
                modelo.addRow(registros);
                }
            tablaDepartamento.setModel(modelo);
            
            } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex);
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
        txtIdDepartamento = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtDescriDepar = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        btnSalir = new javax.swing.JToggleButton();
        btnAgregar = new javax.swing.JToggleButton();
        btnCancelar = new javax.swing.JToggleButton();
        btnGuardar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDepartamento = new javax.swing.JTable();
        btnEditar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        btnEliminar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        comboRegion = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setFont(new java.awt.Font("Bell MT", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 204, 0));
        jLabel3.setText("Agregar Departamento");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 396, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel3)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtIdDepartamento.setEnabled(false);
        txtIdDepartamento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtIdDepartamentoActionPerformed(evt);
            }
        });
        txtIdDepartamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtIdDepartamentoKeyReleased(evt);
            }
        });
        jPanel2.add(txtIdDepartamento, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 10, 50, 30));

        jLabel1.setText("Departamento:");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, -1, 18));

        txtDescriDepar.setEnabled(false);
        jPanel2.add(txtDescriDepar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 10, 120, 30));

        jLabel2.setText("Id Departamento: ");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, -1, 18));

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cancel.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel2.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 280, 100, 50));

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/add.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btnAgregar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel2.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 290, 110, 50));

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/clear.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setEnabled(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel2.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 290, 110, 50));

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/articulos.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel2.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, -1, 50));

        tablaDepartamento.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Departamento", "Region"
            }
        ));
        tablaDepartamento.getTableHeader().setReorderingAllowed(false);
        tablaDepartamento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaDepartamentoMouseClicked(evt);
            }
        });
        tablaDepartamento.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaDepartamentoKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablaDepartamento);

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 360, 220));

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/actualizar.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel2.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 150, 120, -1));

        jLabel4.setText("*");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, 10, -1));

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/basureronegro.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel2.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 210, 120, -1));

        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        jPanel2.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 60, 120, 30));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buscar.png"))); // NOI18N
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 60, -1, -1));

        jLabel6.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 204, 0));
        jLabel6.setText("Introduzca Departamento o Codigo");
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 90, 180, -1));

        jPanel2.add(comboRegion, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, 130, -1));

        jLabel7.setText("Region:");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 106, 730, 352));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtIdDepartamentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtIdDepartamentoActionPerformed
        // TODO add your handling code here:
        cargarmarcabuscardos();
    }//GEN-LAST:event_txtIdDepartamentoActionPerformed

    private void txtIdDepartamentoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtIdDepartamentoKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_txtIdDepartamentoKeyReleased

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
            rs = abm.nuevo("idciudad", "ciudad");
            rs.first();
            txtIdDepartamento.setText(String.valueOf(rs.getInt("codigo")+1));
            txtDescriDepar.requestFocus();//mantiene el enfoque en un objeto
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"error al generar el codigo "+e);
        }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        habilitarbotones2(false);
        habilitarbotones(true);
        habilitarcampos(false);
        rs=abm.consulta("*", "departamento");
        MostrarRegistro();
        verdepartamento();
        cargardepartamento("");
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
                    consulta=("Select * from departamento where descripcion='"+txtDescriDepar.getText()+"'");
                    st= (Statement) cnn.createStatement();
                    ResultSet rs= st.executeQuery(consulta);
                    rs.first();
                    if(rs.getRow()!= 0){
                        JOptionPane.showMessageDialog(null, "Este departamento ya existe");
                        txtDescriDepar.setText("");
                        txtDescriDepar.requestFocus();
                    }else{
                        region depar =  (region) comboRegion.getSelectedItem();
                        v_control=abm.insertar("departamento",txtIdDepartamento.getText()+",'"+txtDescriDepar.getText()+"',"+1+","+depar.getCodigo());
                        if (v_control==true){
                            JOptionPane.showMessageDialog(null,"Se ha guardado los datos");
                        }
                    }

                }catch(Exception e){
                    System.out.println("Error al mostrar datos en la tabla"+e.getMessage());
                }

                break;
                case 'm':
                v_control= abm.modificar("departamento", "descripcion='"+txtDescriDepar.getText()+"'", "iddepartamento="+txtIdDepartamento.getText());
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
            verdepartamento();
            cargardepartamento("");
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void tablaDepartamentoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaDepartamentoMouseClicked
        seleccionartabla();
    }//GEN-LAST:event_tablaDepartamentoMouseClicked

    private void tablaDepartamentoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaDepartamentoKeyPressed
        seleccionartabla();
    }//GEN-LAST:event_tablaDepartamentoKeyPressed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        opcion='m';
        habilitarcampos(true);
        habilitarbotones(false);
        habilitarbotones2(true);
        txtDescriDepar.requestFocus();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        v_control= abm.modificar("departamento", "estado='"+0+"'", "iddepartamento="+txtIdDepartamento.getText());
                
//        v_control = abm.eliminar("departamento", "iddepartamento="+txtIdDepartamento.getText());
        if(v_control==true){
            rs= abm.consulta("*", "departamento");
            MostrarRegistro();
            JOptionPane.showMessageDialog(null,"Datos Eliminados congratuleishon");
        }
        /*rs=abm.consulta("*", "marca");
        MostrarRegistro();*/
        verdepartamento();
        cargardepartamento("");
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        // TODO add your handling code here:
        if(txtBuscar.getText().isEmpty()){
            cargardepartamento("");
        }
        else{cargarciudadbuscar();
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
            java.util.logging.Logger.getLogger(Departamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Departamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Departamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Departamento.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                /*Departamento dialog = new Departamento(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });*/
             //   dialog.setVisible(true);
                 new Departamento().setVisible(true);
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
    private javax.swing.JComboBox comboRegion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaDepartamento;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtDescriDepar;
    private javax.swing.JTextField txtIdDepartamento;
    // End of variables declaration//GEN-END:variables
}
