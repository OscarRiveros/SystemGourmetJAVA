/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModulosProduccion;

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
import reportes.reporte;

/**
 *
 * @author USER
 */
public class ListaPedidos extends javax.swing.JDialog {

    /**
     * Creates new form ListaPedidos
     */
    private boolean v_control;
     private reporte jasper;
    conexionBD cn;
    private ResultSet rs;
    DefaultTableModel modelo;
    private Object[] filas;
    private abm abm;
    public ListaPedidos(java.awt.Frame parent, boolean modal) {
        super(parent, false);
        initComponents();
         abm=new abm();
         cn= new conexionBD();
         jasper= new reporte();
        
        cargarclientes("");
        txtcodigopedido.setVisible(false);
        this.setLocationRelativeTo(null);
        
    }
    
     public void seleccionartablacliente(){
        
        
       DefaultTableModel tabla = (DefaultTableModel) this.tablacliente.getModel();
       
       
       int c= tablacliente.getSelectedRow();
       
       if(c==-1){
           System.out.println("Seleccione un registro");
            }
       else{
           String id = (String) tabla.getValueAt(c, 3);
           

         //  this.txtIdcliente.setText(id);
           
           
           this.txtcodigopedido.setText(id);
           
           }       
    }
    
      public void cargarproducto(String valor){
        String [] titulos  = {"Codigo","Descripcion","Precio Venta","Cantidad","Costo"};
        String [] registros  = new String [5];
        String sql=null;
         sql = "select p.idproducto, p.descripcion, p.preciodecompra, dp.cantidad, totalcadaproducto as costo from detallepedido as dp ";
         sql+="inner join pedido as pd on(pd.idpedido=dp.idpedido) inner join productos as p on (p.idproducto=dp.idproducto)";
         sql+="where pd.idpedido='"+txtcodigopedido.getText()+"'";
       
         
        //DefaultTableModel m = new DefaultTableModel();
         DefaultTableModel m = (DefaultTableModel) this.tablaproducto.getModel();
//               modelo = (DefaultTableModel) this.tablaproducto.getModel();

    
        conexionBD cn = new conexionBD();
        Connection cnn = (Connection) cn.ConectarBD();
        Statement  st;
        try {
            
            st = (Statement) cnn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                registros[0] = rs.getString("idproducto");
                registros[0] = rs.getString("descripcion");
                registros[2] = rs.getString("preciodecompra");
                registros[1] = rs.getString("cantidad");
                registros[4] = rs.getString("costo");
                m.addRow(registros);
                System.out.println(registros[1]+" mednie");
                }
            tablaproducto.setModel(m);
          //  tablaproducto.removeColumn(tablaproducto.getColumnModel().getColumn(0));
//            tablaproducto.removeColumn(tablaproducto.getColumnModel().getColumn(1));
//            tablaproducto.removeColumn(tablaproducto.getColumnModel().getColumn(2));
            }catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex);
                            }   
    }
    
     public void cargarclientes(String valor ){
        String [] titulos  = {"Codigo","Nombre","C.I.N°","Numero de Orden"};
        String [] registros  = new String [4];
        String sql = "select c.idcliente, nombre,apellido,cinro,pd.estado as estado,pd.idpedido as numero from cliente as c "
                      + "inner join pedido as pd on(pd.idcliente=c.idcliente) where pd.estado='pendiente' and pd.anulado='cliente'";   
         
        // sql+=" where idproducto="+txtIdProducto.getText();
         
        modelo = new DefaultTableModel(null, titulos);
    
        conexionBD cnx = new conexionBD();
        Connection cnn = (Connection) cnx.ConectarBD();
        com.mysql.jdbc.Statement  st;
        try {
            
            st = (com.mysql.jdbc.Statement) cnn.createStatement();
            ResultSet rss = st.executeQuery(sql);
            
            while(rss.next()){
                registros[0] = rss.getString("idcliente");
                registros[1] = rss.getString("nombre");
              //  registros[2] = rss.getString("apellido");
                registros[2] = rss.getString("cinro"); 
              //  registros[4] = rss.getString("estado");
                registros[3] = rss.getString("numero");
                modelo.addRow(registros);
                }
            tablacliente.setModel(modelo);
            tablacliente.removeColumn(tablacliente.getColumnModel().getColumn(0));
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tablacliente = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaproducto = new javax.swing.JTable();
        txtcodigopedido = new javax.swing.JTextField();
        btnImprimir = new javax.swing.JButton();
        btnAnular = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(51, 255, 51));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablacliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "codigo", "Nombre", "Ruc", "Orden N°"
            }
        ));
        tablacliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaclienteMouseClicked(evt);
            }
        });
        tablacliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaclienteKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tablacliente);
        if (tablacliente.getColumnModel().getColumnCount() > 0) {
            tablacliente.getColumnModel().getColumn(3).setHeaderValue("Orden N°");
        }

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 471, 94));

        jLabel6.setFont(new java.awt.Font("Sitka Text", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 153, 0));
        jLabel6.setText("Lista de Pedidos");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 220, 30));

        tablaproducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre"
            }
        ));
        jScrollPane2.setViewportView(tablaproducto);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 220, 230, 90));
        getContentPane().add(txtcodigopedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 180, 40, -1));

        btnImprimir.setText("Imprimir Pedidos");
        btnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirActionPerformed(evt);
            }
        });
        getContentPane().add(btnImprimir, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 180, -1, -1));

        btnAnular.setText("Anular");
        btnAnular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAnularActionPerformed(evt);
            }
        });
        getContentPane().add(btnAnular, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaclienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaclienteMouseClicked
        // TODO add your handling code here:
        seleccionartablacliente();
        cargarproducto("");
    }//GEN-LAST:event_tablaclienteMouseClicked

    private void tablaclienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaclienteKeyReleased
        // TODO add your handling code here:
        seleccionartablacliente();
        cargarproducto("");
    }//GEN-LAST:event_tablaclienteKeyReleased

    private void btnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirActionPerformed
        // TODO add your handling code here:
         String ubicacion="/reportes/ListaPedidos.jasper";
         int parametrop=Integer.valueOf(txtcodigopedido.getText());
         System.out.println(parametrop);
         jasper.runReporte_parametro(ubicacion,"orden" ,parametrop);
    }//GEN-LAST:event_btnImprimirActionPerformed

    private void btnAnularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAnularActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tabla = (DefaultTableModel) this.tablaproducto.getModel();
        
            for (int ii = 0; ii < tabla.getRowCount(); ii++) {
           
            try {
                                  Statement consulta = (Statement) conexionBD.ConectarBD().createStatement();
                consulta.execute("update productos set stock=stock+"+tabla.getValueAt(ii, 3) +
                        " where idproducto="+tabla.getValueAt(ii, 0));
                
                String cancelado="anulado";
                v_control= abm.modificar("pedido", "estado='"+cancelado+"'", "idpedido="+txtcodigopedido.getText());
               
                rs=abm.consulta("*", "auditoria");
                if(rs.getRow()!=0){ //devuelve numero de filas de una objeto de tipo resultset
                        
                        int id=Integer.valueOf(rs.getString(1));
                        System.out.println("es eñ id "+id);
                        String canceladoo="Anulado";
                        consulta.execute("update auditoria set evento='"+canceladoo+"'"+
                                           " where idauditoria="+id);
                    }
                
                
                
                } catch (SQLException ex) {
                    Logger.getLogger(FacturaVenta.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                    
                    
            
             }
    }//GEN-LAST:event_btnAnularActionPerformed

    
    public void MostrarRegistro(){//metodo creado poara mostrar datos
    try{
        if(rs.getRow()!=0){ //devuelve numero de filas de una objeto de tipo resultset
            rs.first();
            String id=(rs.getString(1));
             
        }
        
    }catch(Exception e){
        System.out.println("error al mostrar resultados"+e.getMessage());
    }
}
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
            java.util.logging.Logger.getLogger(ListaPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ListaPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ListaPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ListaPedidos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ListaPedidos dialog = new ListaPedidos(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAnular;
    private javax.swing.JButton btnImprimir;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tablacliente;
    private javax.swing.JTable tablaproducto;
    private javax.swing.JTextField txtcodigopedido;
    // End of variables declaration//GEN-END:variables
}
