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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USER
 */
public class PedidoProveedor extends javax.swing.JDialog {

    /**
     * Creates new form PedidoProveedor
     */
     DefaultTableModel modelo;
     private Object[] filas;
     private abm abm;
     private ResultSet rs;
     private boolean v_control;
     conexionBD cn;
    public PedidoProveedor(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        
         try {
             abm=new abm();
             cn= new conexionBD();
             initComponents();
             
             cargarproveedor("");
              obtenerfecha();
             verproveedor();
             cargarproducto("");
             verproducto();
             rs = abm.nuevo("numero", "pedido");
             rs.first();
             txtNumFac.setText(String.valueOf(rs.getInt("codigo") + 1));
             //idfactura();
         } catch (SQLException ex) {
             Logger.getLogger(PedidoProveedor.class.getName()).log(Level.SEVERE, null, ex);
         }
         btnContado.setSelected(true);
         btnContado.setVisible(false);
       //  txtNumFac.setVisible(false);
    }
    
    void obtenerfecha(){
        Date fecha = new Date();
        SimpleDateFormat fe = new SimpleDateFormat("yyyy/MM/dd");
        this.fecha.setText(fe.format(fecha));   
    }
    
    public void habilitarbotonestg(boolean h){
        AgregarProducto.setEnabled(h);
        txtCantidad.setEnabled(h);
        txtPrecioCompra.setEnabled(h);
    }
    
      void agregartablafactura(){
        
          DefaultTableModel tabla = (DefaultTableModel) this.tablafactura.getModel();
                                    Object[] valor = new Object[5];
                                    valor[0] = (String) txtCodigo.getText();
                                    valor[1] = (String) txtDescripcion.getText();
                                    valor[2] = (String) txtCantidad.getText();
                                    valor[3] = (String) txtPrecioCompra.getText();                   
                                    valor[4] = (String) txtTotal1.getText();
          
          if (this.tablafactura.getRowCount() == 0 ) {
               //JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
//                DefaultTableModel tabla = (DefaultTableModel) this.tablafactura.getModel();
//                Object[] valor = new Object[5];
//                valor[0] = (String) txtCodigo.getText();
//                valor[1] = (String) txtDescripcion.getText();
//                valor[2] = (String) txtCantidad.getText();
//                valor[3] = (String) txtPrecioCompra.getText();
//      //          valor[4] = (String) txtPrecioVenta.getText();          
//                valor[4] = (String) txtTotal1.getText();
     //           valor[6] = (String) "0";
     //           valor[7] = (String) "0";

                tabla.addRow(valor);
            }else{
                
          boolean dupli=false;
          dupli=duplicado();
          if(!dupli){
              tabla.addRow(valor);
          }
               
                      
        }
       
        
        
 } 
      void sumadetalbla(){
        //double sumatoriaTotal = 0;
        int totalRow = tablafactura.getRowCount();
        totalRow -= 1;
        double z = 0;
        for (int i = 0; i <= (totalRow); i++) {
          
            

            
          
            //SUMA TOTAL EXENTAS
            double sumatoria2 = Double.parseDouble(String.valueOf(tablafactura.getValueAt(i, 4)));
            z += sumatoria2;
//            txtExentas.setText(String.valueOf(z));
            //sumatoriaTotal += ( z);

            txtTotal.setText(String.valueOf(z));
            
            //SUMA PARA EL TOTAL DE IVA
            
//            double iva5p = Double.parseDouble(String.valueOf(txtIva5.getText()));
//            double iva10p = Double.parseDouble(String.valueOf(txtIva10.getText()));
//            double sumaivap = iva5p+iva10p;
//            txtTotalIva.setText(String.valueOf(sumaivap));
            
        }
    }
    
    public void seleccionartablaproducto(){
        
        
       //DefaultTableModel tabla = (DefaultTableModel) this.tablacliente.getModel();
       
       
       int c= tablaProducto.getSelectedRow();
       
       if(c==-1){
           JOptionPane.showMessageDialog(null, "Seleccione un registro");
            }
       else{
          // String id = (String) tablaProducto.getValueAt(c, 0);
           Integer id = Integer.parseInt(tablaProducto.getValueAt(c, 0).toString());
           Integer preciocompra = Integer.parseInt(tablaProducto.getValueAt(c, 1).toString());
           //String precioventa = (String) tablaProducto.getValueAt(c, 1);
           String descripcion = (String) tablaProducto.getValueAt(c, 2);
          // Integer precioventa = Integer.parseInt(tablaProducto.getValueAt(c, 2).toString());
          
           
           txtCodigo.setText(Integer.toString(id));
          // this.txtCodigo.setText(id);
           //this.txtPrecioVenta.setText(precioventa);
           txtPrecioCompra.setText(Integer.toString(preciocompra));
           this.txtDescripcion.setText(descripcion);
          // txtPrecioVenta.setText(Integer.toString(precioventa));
           
           }       
    }
    
      void verproducto(){
        try{
            Statement consultamarca = (Statement) conexionBD.ConectarBD().createStatement();
            ResultSet rs = consultamarca.executeQuery("select idproducto,preciodecompra,descripcion  from productos where tipo='insumo' order by idproducto");
            
            modelo = new DefaultTableModel();
            tablaProducto.setModel(modelo);
            
            
            modelo.addColumn("Codigo");
            modelo.addColumn("Precio CompraVenta");
            modelo.addColumn("Descripcion");
            
            
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
    public void cargarproducto(String valor){
        String [] titulos  = {"Codigo","Precio Compra","Descripcion"};
        String [] registros  = new String [3];
        String sql=null;
         sql = "select idproducto,preciodecompra,descripcion";
         sql+=" from productos";
         sql+=" where tipo='insumo'";
       
         
        DefaultTableModel m = new DefaultTableModel(null, titulos);
    
        conexionBD cn = new conexionBD();
        Connection cnn = (Connection) cn.ConectarBD();
        Statement  st;
        try {
            
            st = (Statement) cnn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                registros[0] = rs.getString("idproducto");
                registros[1] = rs.getString("preciodecompra");
                registros[2] = rs.getString("descripcion");
               
                m.addRow(registros);
                }
            tablaProducto.setModel(m);
            
            }catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex);
                            }   
    }
    
    void verproveedor(){
        try{
            com.mysql.jdbc.Statement consultamarca = (com.mysql.jdbc.Statement) conexionBD.ConectarBD().createStatement();
            ResultSet rs = consultamarca.executeQuery("select idproveedor, nombre, telefono, direccion, RUC from proveedor");
            
            modelo = new DefaultTableModel();
            tablaproveedor.setModel(modelo);
            
            modelo.addColumn("Codigo");
            modelo.addColumn("Nombre");
            modelo.addColumn("Telefono");
            modelo.addColumn("Direccion");
            modelo.addColumn("R.U.C");
            
            filas = new Object[modelo.getColumnCount()];
            
            while(rs.next()){
                for(int i=0;i<modelo.getColumnCount();i++){
                    filas[i]=rs.getObject(i+1);
                }
                
                modelo.addRow(filas);
                
            }
            
            tablaproveedor.setModel(modelo);
    
        }catch(Exception e){
            System.out.println("Error al mostrar datos en la tabla"+e.getMessage());
        }
    }   
     public void cargarproveedor(String valor ){
        String [] titulos  = {"Codigo","Nombre","R.U.C.","Telefono","Direccion"};
        String [] registros  = new String [5];
        String sql = "select idproveedor, nombre, telefono, direccion, RUC from proveedor where CONCAT(idproveedor,' ',nombre,' ',telefono,' ',direccion,' ',RUC) LIKE '%"+valor+"%'";   
         
        // sql+=" where idproducto="+txtIdProducto.getText();
         
        modelo = new DefaultTableModel(null, titulos);
    
        conexionBD cnx = new conexionBD();
        Connection cnn = (Connection) cnx.ConectarBD();
        com.mysql.jdbc.Statement  st;
        try {
            
            st = (com.mysql.jdbc.Statement) cnn.createStatement();
            ResultSet rss = st.executeQuery(sql);
            
            while(rss.next()){
                registros[0] = rss.getString("idproveedor");
                registros[1] = rss.getString("nombre");
                registros[2] = rss.getString("telefono");
                registros[3] = rss.getString("direccion");
                registros[4] = rss.getString("RUC");
                modelo.addRow(registros);
                }
            tablaproveedor.setModel(modelo);
            
            } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex);
                            }   
    }
     
     public void cargarproductoDos(){
        
        String sql=null;
         sql = "select idproducto,preciodecompra,descripcion";
             sql+=" from productos";
          sql+=" where tipo='insumo' and idproducto LIKE '"+txtCodigo.getText()+"'";
       
         
        
        conexionBD cn = new conexionBD();
        Connection cnn = (Connection) cn.ConectarBD();
        Statement  st;
        try {
            
            st = (Statement) cnn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){                
                this.txtPrecioCompra.setText(rs.getString("preciodecompra"));
                this.txtDescripcion.setText(rs.getString("descripcion"));
                
                
                }
            
            
            }catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex);
                            }   
    }
     
      public void seleccionartablaproveedor(){
        
        
      // DefaultTableModel tabla = (DefaultTableModel) this.tablaCliente.getModel();
       
       
       int c= tablaproveedor.getSelectedRow();
       
       if(c==-1){
           System.out.println("Seleccione un registro");
            }
       else{
           Integer id = Integer.parseInt(tablaproveedor.getValueAt(c, 0).toString());
           String nombre = (String) tablaproveedor.getValueAt(c, 1);
           String telefono= (String) tablaproveedor.getValueAt(c, 2);;
           String direccion= (String) tablaproveedor.getValueAt(c, 3);
           String ruc = (String) tablaproveedor.getValueAt(c, 4);
           
           

         //  this.txtIdcliente.setText(id);
           txtIdproveedor.setText(Integer.toString(id));
           
           this.txtnombre.setText(nombre);
           this.txtCin.setText(ruc);        
           this.txtTelef.setText(telefono);
           this.txtDireccion.setText(direccion);
           
           }       
    }
      public void habilitarbotones(boolean j){
        buscarProdu.setEnabled(j);
        
    }
         public void limpiar(){
        txtCodigo.setText("");
        txtDescripcion.setText("");
        txtCantidad.setText("");
        txtPrecioCompra.setText("");
        txtTotal1.setText("");
       
    }
      public void habilitarbotones2(boolean k){
        btnGuardar.setEnabled(k);
        btnCancelar.setEnabled(k);
        btnQuitar.setEnabled(k);
        //btnCredito.setEnabled(k);
    }
      public void habilitarguardar(){
        int x = tablafactura.getRowCount();
        
        if(tablafactura.getRowCount()!=0){

            btnGuardar.setEnabled(true);
        }
        else{
                btnGuardar.setEnabled(false);}
    }
    
      public void limpiarcamposcabezara(){
        txtnombre.setText("");
        txtApellido.setText("");
        txtDireccion.setText("");
        txtCin.setText("");
        txtTelef.setText("");
        txtCodigo.setText("");
        txtDescripcion.setText("");
        txtCantidad.setText("");
        txtPrecioCompra.setText("");
        txtTotal1.setText("");        
        txtTotal.setText("");
        
        
        DefaultTableModel tablaborrar = (DefaultTableModel) this.tablafactura.getModel();
        while(tablaborrar.getRowCount()>0)tablaborrar.removeRow(0);
    }
      public void idfactura(){
        try {
            rs = abm.nuevo("idpedido","pedido");
            try {
                rs.first();
            } catch (SQLException ex) {
                Logger.getLogger(FacturaCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
            txtidfac.setText(String.valueOf(rs.getInt("codigo")+1));
        } catch (SQLException ex) {
            Logger.getLogger(FacturaCompra.class.getName()).log(Level.SEVERE, null, ex);
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

        buscarproveedor = new javax.swing.JDialog();
        txtBuscarCliente = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaproveedor = new javax.swing.JTable();
        jLabel41 = new javax.swing.JLabel();
        buscarproductos = new javax.swing.JDialog();
        txtBuscarProdu = new javax.swing.JTextField();
        btnAceotarProdu = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaProducto = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtnombre = new javax.swing.JTextField();
        txtTelef = new javax.swing.JTextField();
        txtCin = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        txtIdproveedor = new javax.swing.JTextField();
        btnContado = new javax.swing.JRadioButton();
        txtCodigo = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        txtPrecioCompra = new javax.swing.JTextField();
        txtTotal1 = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        buscarProdu = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablafactura = new javax.swing.JTable();
        AgregarProducto = new javax.swing.JButton();
        txtTotal = new javax.swing.JTextField();
        btnQuitar = new javax.swing.JButton();
        txtidfac = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();
        fecha = new javax.swing.JLabel();
        txtNumFac = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        txtBuscarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarClienteActionPerformed(evt);
            }
        });
        txtBuscarCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarClienteKeyReleased(evt);
            }
        });

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        tablaproveedor.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "Apellido", "C.I.N.", "Telefono", "Direccion"
            }
        ));
        tablaproveedor.getTableHeader().setReorderingAllowed(false);
        tablaproveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaproveedorMouseClicked(evt);
            }
        });
        tablaproveedor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaproveedorKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tablaproveedor);

        jLabel41.setText("Introduzca Nombre del Proveedor");

        javax.swing.GroupLayout buscarproveedorLayout = new javax.swing.GroupLayout(buscarproveedor.getContentPane());
        buscarproveedor.getContentPane().setLayout(buscarproveedorLayout);
        buscarproveedorLayout.setHorizontalGroup(
            buscarproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buscarproveedorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buscarproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(buscarproveedorLayout.createSequentialGroup()
                        .addComponent(txtBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAceptar)
                        .addContainerGap(159, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
            .addGroup(buscarproveedorLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel41)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        buscarproveedorLayout.setVerticalGroup(
            buscarproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buscarproveedorLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel41)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(buscarproveedorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAceptar))
                .addGap(33, 33, 33)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        buscarproductos.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        txtBuscarProdu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarProduActionPerformed(evt);
            }
        });
        txtBuscarProdu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarProduKeyReleased(evt);
            }
        });

        btnAceotarProdu.setText("Aceptar");
        btnAceotarProdu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceotarProduActionPerformed(evt);
            }
        });

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buscar.png"))); // NOI18N

        tablaProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripcion", "PrecioVenta", "IVA%"
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
        });
        jScrollPane3.setViewportView(tablaProducto);

        javax.swing.GroupLayout buscarproductosLayout = new javax.swing.GroupLayout(buscarproductos.getContentPane());
        buscarproductos.getContentPane().setLayout(buscarproductosLayout);
        buscarproductosLayout.setHorizontalGroup(
            buscarproductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buscarproductosLayout.createSequentialGroup()
                .addComponent(jLabel23)
                .addGap(4, 4, 4)
                .addComponent(txtBuscarProdu, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnAceotarProdu)
                .addGap(173, 173, 173))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, buscarproductosLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 452, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        buscarproductosLayout.setVerticalGroup(
            buscarproductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buscarproductosLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(buscarproductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(buscarproductosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtBuscarProdu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnAceotarProdu)))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(77, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setText("RUC o C.I.N.:");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jLabel15.setText("Proveedor:");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel8.setText("Telef:");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, -1, -1));

        txtnombre.setEnabled(false);
        jPanel3.add(txtnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 100, -1));

        txtTelef.setEnabled(false);
        jPanel3.add(txtTelef, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, 120, -1));

        txtCin.setEnabled(false);
        jPanel3.add(txtCin, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 110, -1));

        txtApellido.setEnabled(false);
        jPanel3.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, 360, -1));

        jLabel17.setText("Direccion:");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        txtDireccion.setEnabled(false);
        jPanel3.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 470, -1));

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/clientes.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel3.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 10, 80, 60));
        jPanel3.add(txtIdproveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 120, 30, -1));

        btnContado.setText("Contado");
        jPanel3.add(btnContado, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 80, -1, -1));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 670, 110));

        txtCodigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCodigoActionPerformed(evt);
            }
        });
        txtCodigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCodigoKeyReleased(evt);
            }
        });
        getContentPane().add(txtCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 60, -1));

        txtDescripcion.setEnabled(false);
        getContentPane().add(txtDescripcion, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 200, 120, -1));

        txtCantidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCantidadFocusLost(evt);
            }
        });
        txtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCantidadActionPerformed(evt);
            }
        });
        txtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCantidadKeyTyped(evt);
            }
        });
        getContentPane().add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 200, 120, -1));

        txtPrecioCompra.setEnabled(false);
        txtPrecioCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtPrecioCompraKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioCompraKeyTyped(evt);
            }
        });
        getContentPane().add(txtPrecioCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 200, 120, -1));

        txtTotal1.setEnabled(false);
        getContentPane().add(txtTotal1, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 200, 120, -1));

        jLabel24.setText("Total");
        getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 180, -1, -1));

        jLabel28.setText("Precio Compra");
        getContentPane().add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 180, -1, -1));

        jLabel27.setText("Cantidad");
        getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 180, -1, -1));

        jLabel26.setText("Descripcion");
        getContentPane().add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 180, -1, -1));

        jLabel25.setText("Codigo");
        getContentPane().add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 180, -1, -1));

        buscarProdu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/nuevacoompra.png"))); // NOI18N
        buscarProdu.setEnabled(false);
        buscarProdu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarProduActionPerformed(evt);
            }
        });
        getContentPane().add(buscarProdu, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 170, 80, 60));

        tablafactura.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripcion", "Cantidad", "Precio Compra", "total"
            }
        ));
        jScrollPane1.setViewportView(tablafactura);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 240, 710, 100));

        AgregarProducto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/entrada.png"))); // NOI18N
        AgregarProducto.setEnabled(false);
        AgregarProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarProductoActionPerformed(evt);
            }
        });
        getContentPane().add(AgregarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 240, 80, 60));

        txtTotal.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtTotal.setEnabled(false);
        getContentPane().add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 350, 210, 30));

        btnQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/basurero.png"))); // NOI18N
        btnQuitar.setEnabled(false);
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });
        getContentPane().add(btnQuitar, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 310, 80, 40));

        txtidfac.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtidfacFocusLost(evt);
            }
        });
        getContentPane().add(txtidfac, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 30, 50, 30));

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/articulos.png"))); // NOI18N
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 360, 80, 50));

        fecha.setText("dd/mm/yy");
        getContentPane().add(fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 80, 60, 20));

        txtNumFac.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtNumFac.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumFacKeyTyped(evt);
            }
        });
        getContentPane().add(txtNumFac, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 30, 30, 30));

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/clear.png"))); // NOI18N
        btnCancelar.setEnabled(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 420, 80, 40));

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/equis.png"))); // NOI18N
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 420, 80, -1));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Total Costo");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 350, 80, 30));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarproveedor.setModal(true);
        buscarproveedor.setSize(450, 300);
        buscarproveedor.setLocationRelativeTo(this);
        buscarproveedor.setVisible(true);
        habilitarbotones(true);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarClienteActionPerformed

    private void txtBuscarClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarClienteKeyReleased
        if(txtBuscarCliente.getText().isEmpty()){
            cargarproveedor("");
        }
        else{//cargarclinetesbuscar("");
        }
    }//GEN-LAST:event_txtBuscarClienteKeyReleased

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        this.buscarproveedor.dispose();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void tablaproveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaproveedorMouseClicked

        seleccionartablaproveedor();
    }//GEN-LAST:event_tablaproveedorMouseClicked

    private void tablaproveedorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaproveedorKeyReleased
        seleccionartablaproveedor();
    }//GEN-LAST:event_tablaproveedorKeyReleased

    private void txtCodigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCodigoActionPerformed
        // TODO add your handling code here:
        cargarproductoDos();
        txtCantidad.requestFocus();
    }//GEN-LAST:event_txtCodigoActionPerformed

    private void txtCodigoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCodigoKeyReleased
        // TODO add your handling code here:
        cargarproductoDos();
    }//GEN-LAST:event_txtCodigoKeyReleased

    private void txtCantidadFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCantidadFocusLost

    }//GEN-LAST:event_txtCantidadFocusLost

    private void txtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCantidadActionPerformed
        // TODO add your handling code here:
//        agregartablafactura();
  //      sumadetalbla();
  //      limpiar();
   //     habilitarbotones2(true);
     //   habilitarbotonestg(false);
    }//GEN-LAST:event_txtCantidadActionPerformed

    private void txtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyReleased

        if(txtCantidad.getText().isEmpty()){
            txtCantidad.setText("1");
        }else{
            int precioventa, cantidad, total;
            cantidad = Integer.parseInt(txtCantidad.getText().toString());
            precioventa = Integer.parseInt(txtPrecioCompra.getText().toString());
            total = cantidad*precioventa;
            txtTotal1.setText(String.valueOf(total));
        }

    }//GEN-LAST:event_txtCantidadKeyReleased

    private void txtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidadKeyTyped
        char caracter=evt.getKeyChar();
        if((caracter<'0'||(caracter>'9'))&&(caracter!='\b')){
            evt.consume();
        }
    }//GEN-LAST:event_txtCantidadKeyTyped

    private void txtPrecioCompraKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioCompraKeyReleased
        // TODO add your handling code here:
        int precioventa, cantidad, total;
        cantidad = Integer.parseInt(txtCantidad.getText().toString());
        precioventa = Integer.parseInt(txtPrecioCompra.getText().toString());
        total = cantidad*precioventa;
        txtTotal1.setText(String.valueOf(total));
    }//GEN-LAST:event_txtPrecioCompraKeyReleased

    private void txtPrecioCompraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioCompraKeyTyped
        // TODO add your handling code here:
        char caracter=evt.getKeyChar();
        if((caracter<'0'||(caracter>'9'))&&(caracter!='\b')){
            evt.consume();
        }
    }//GEN-LAST:event_txtPrecioCompraKeyTyped

    private void buscarProduActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarProduActionPerformed
        buscarproductos.setModal(true);
        buscarproductos.setSize(500, 300);
        buscarproductos.setLocationRelativeTo(this);
        buscarproductos.setVisible(true);

        habilitarbotonestg(true);
    }//GEN-LAST:event_buscarProduActionPerformed

    private void txtBuscarProduActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarProduActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarProduActionPerformed

    private void txtBuscarProduKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProduKeyReleased
        if(txtBuscarProdu.getText().isEmpty()){
            cargarproducto("");
        }
        else{
//            cargarproductobuscar();
        }
    }//GEN-LAST:event_txtBuscarProduKeyReleased

    private void btnAceotarProduActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceotarProduActionPerformed
        this.buscarproductos.dispose();
        txtCantidad.setText("1");
        int precioventa, cantidad, total;

        cantidad = Integer.parseInt(txtCantidad.getText().toString());
        precioventa = Integer.parseInt(txtPrecioCompra.getText().toString());
        total = cantidad*precioventa;
        txtTotal1.setText(String.valueOf(total));

    }//GEN-LAST:event_btnAceotarProduActionPerformed

    private void tablaProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductoMouseClicked
        seleccionartablaproducto();
    }//GEN-LAST:event_tablaProductoMouseClicked

    private void tablaProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaProductoKeyPressed

    }//GEN-LAST:event_tablaProductoKeyPressed

    private void tablaProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaProductoKeyReleased
//        seleccionartablaproducto();
    }//GEN-LAST:event_tablaProductoKeyReleased

    private void AgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarProductoActionPerformed
        agregartablafactura();
        txtTotal.setText(txtTotal1.getText());
         sumadetalbla();
        limpiar();
        habilitarbotones2(true);
 //       habilitarbotonestg(false);

    }//GEN-LAST:event_AgregarProductoActionPerformed

    private void btnQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuitarActionPerformed
        DefaultTableModel dtm = (DefaultTableModel) tablafactura.getModel();
        int i = tablafactura.getSelectedRow();

        //hacemos una condicion de que si la varialbe i es igual a - es que no se ha seleccionado ninguna fila
        if (i == -1) {
            JOptionPane.showMessageDialog(null, "Favor seleccione una fila");

        } else {
            
            txtTotal.setText("");

            dtm.removeRow(tablafactura.getSelectedRow());
            sumadetalbla();
           // habilitarguardar();
        }
    }//GEN-LAST:event_btnQuitarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        try {
            if (btnContado.isSelected()) {

                try {

                    abm.start();

                    int idempleado = abm.idUsuario;

                    rs = abm.nuevo("idpedido", "pedido");
                    rs.first();
                    txtidfac.setText(String.valueOf(rs.getInt("codigo")+1));

                    int iva=10;
                    // iva = Integer.parseInt(txtIVA.getText().toString());
                    if((iva==10)||(iva==5)||(iva==0)){
                        String anulado = "proveedor", estado = "pendiente";
                        int  pagado=0 ,condicion=0;
                        v_control = abm.insertar("pedido", txtidfac.getText()+","+txtNumFac.getText() +",'"
                            + fecha.getText() + "','" + estado + "','" + anulado + "'," + txtIdproveedor.getText() + "," + 0+
                            "," + pagado + "," +condicion+","+idempleado+","+ 0 +"," + 0 + "," + 0 + "," + txtTotal.getText()+",'"+fecha.getText()+"',"
                        +txtIdproveedor.getText());
                        System.out.println("despues del print");
                    }

                    if (v_control == false) {
                        abm.roolback();
                    }

                    if (v_control == true) {

                        for (int i = 0; i < tablafactura.getRowCount(); i++) {
//                            Statement consulta = (Statement) conexionBD.ConectarBD().createStatement();
//                            consulta.execute("update productos set stock=stock+"+tablafactura.getValueAt(i, 2) +", preciodecompra="+tablafactura.getValueAt(i, 3)+
//                                " where idproducto="+tablafactura.getValueAt(i, 0));
                            if((iva==10)||(iva==5)||(iva==0)){
                                int estado=0;
                                v_control = abm.insertar("detallepedido", txtidfac.getText() + ",'"
                                    +  tablafactura.getValueAt(i, 0) + "','" +tablafactura.getValueAt(i, 3) + "','"
                                    +  tablafactura.getValueAt(i, 2)+"',"+estado+",'"+tablafactura.getValueAt(i, 4)+"'");
                                System.out.println("despues del print2 ");
                            }
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
                    JOptionPane.showMessageDialog(null, "Los Datos se han guardado satisfactoriamente");
                } catch (Exception ex) {
                    System.out.println("Error al realizar la transacion " + ex.getMessage());
                    JOptionPane.showMessageDialog(null, "Completar todos los campos para guardar");

                }

            }
            habilitarbotones(false);
            habilitarbotonestg(false);
            habilitarbotones2(false);
            limpiarcamposcabezara();
            idfactura();
            rs = abm.nuevo("numero", "pedido");
            rs.first();
            txtNumFac.setText(String.valueOf(rs.getInt("codigo") + 1));
        } catch (SQLException ex) {
            Logger.getLogger(FacturaCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void txtNumFacKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumFacKeyTyped
        char caracter=evt.getKeyChar();
        if((caracter<'0'||(caracter>'9'))&&(caracter!='\b')){
            evt.consume();
        }
    }//GEN-LAST:event_txtNumFacKeyTyped

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiarcamposcabezara();
        habilitarguardar();
        habilitarbotones2(false);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void txtidfacFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtidfacFocusLost
        // TODO add your handling code here:
         try {
                // TODO add your handling code here:
                conexionBD cn = new conexionBD();// se crea la conexion
                java.sql.Connection cnn = (java.sql.Connection) cn.ConectarBD();
                java.sql.Statement  st;
                String consulta="";
                consulta=("Select * from pedido where idpedido='"+txtidfac.getText()+"'");
                st= (com.mysql.jdbc.Statement) cnn.createStatement();
                ResultSet rs= st.executeQuery(consulta);
                rs.first();
                if(rs.getRow()!= 0){
                    JOptionPane.showMessageDialog(null, "Este Codigo ya existe");
                    txtidfac.requestFocus();
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(PedidoProveedor.class.getName()).log(Level.SEVERE, null, ex);
            }
    }//GEN-LAST:event_txtidfacFocusLost

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
            java.util.logging.Logger.getLogger(PedidoProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PedidoProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PedidoProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PedidoProveedor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PedidoProveedor dialog = new PedidoProveedor(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton AgregarProducto;
    private javax.swing.JButton btnAceotarProdu;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JRadioButton btnContado;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton buscarProdu;
    private javax.swing.JDialog buscarproductos;
    private javax.swing.JDialog buscarproveedor;
    private javax.swing.JLabel fecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tablaProducto;
    private javax.swing.JTable tablafactura;
    private javax.swing.JTable tablaproveedor;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtBuscarCliente;
    private javax.swing.JTextField txtBuscarProdu;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCin;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtIdproveedor;
    private javax.swing.JTextField txtNumFac;
    private javax.swing.JTextField txtPrecioCompra;
    private javax.swing.JTextField txtTelef;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTotal1;
    private javax.swing.JTextField txtidfac;
    private javax.swing.JTextField txtnombre;
    // End of variables declaration//GEN-END:variables

    private boolean duplicado() {
             boolean duplicado=false;
                int codigotxt= Integer.parseInt(txtCodigo.getText());//trae el codigo del txt
                //System.out.println(codigotxt);
                System.out.println("hizo este");
                int cantfila= tablafactura.getRowCount();
                for (int i = 0; i < cantfila; i++) {//recorre la tabla factura
                     
                     int idtabla = Integer.parseInt(tablafactura.getValueAt(i, 0).toString());//trae el codigo de la primera columna
                     
                    if(codigotxt==idtabla){//compra el valor de los dos codigo
                        
                         JOptionPane.showMessageDialog(null, "Ya existe el producto en la tabla");//si es igual me tira el mensaje y cerrar
                         duplicado=true;
                    }//si no es igual me agrega el producto
      
                        
                    }
                return duplicado;
    }
}
