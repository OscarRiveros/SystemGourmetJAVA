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
public class PedidoClienteDos extends javax.swing.JDialog {

    /**
     * Creates new form PedidoClienteDos
     */
     DefaultTableModel modelo;
     private Object[] filas;
     private abm abm;
     private ResultSet rs;
     private boolean v_control;
     conexionBD cn;
     Produccion JDialog8;
    public PedidoClienteDos(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
         try {
             initComponents();
             abm=new abm();
             cn= new conexionBD();
             
               abm= new abm();
               rs=abm.consulta("*", "cliente");
               MostrarRegistro();
             
            btnContado.setSelected(true);
            btnContado.setVisible(false);
            AgregarProducto.setVisible(false);
            txtCantidadDos.setVisible(false);
            btnQuitar.setVisible(false);
            txtIdcliente.setVisible(false);
             cargarclientes(""); 
             obtenerfecha();
           
            //cargarproducto("");
             
             // verproducto();
              //cargarproductodos("");
        
        txtCodigo1.setVisible(false);
             txtStock.setVisible(false);
             rs = abm.nuevo("idpedido", "pedido");
             rs.first();
             txtNumFac.setText(String.valueOf(rs.getInt("codigo") + 1));
             txtidfac.setText(String.valueOf(rs.getInt("codigo") + 1));
         } catch (SQLException ex) {
             Logger.getLogger(PedidoClienteDos.class.getName()).log(Level.SEVERE, null, ex);
         }
         txtNumFac.setVisible(false);
    }
    public void habilitarbotones(boolean j){
        buscarProdu.setEnabled(j);
        }
    public void MostrarRegistro(){//metodo creado poara mostrar datos
    try{
        if(rs.getRow()!=0){ //devuelve numero de filas de una objeto de tipo resultset
            rs.first();
            txtIdcliente.setText(rs.getString(1));
            txtnombre.setText(rs.getString(2));
            txtApellido.setText(rs.getString(3));
            
            txtCin.setText(rs.getString(5));  
             
        }
        
    }catch(Exception e){
        System.out.println("error al mostrar resultados"+e.getMessage());
    }
}
    
    void obtenerfecha(){
        Date fecha = new Date();
        SimpleDateFormat fe = new SimpleDateFormat("yyyy/MM/dd");
        this.fecha.setText(fe.format(fecha));   
    }
    public void habilitarbotonestg(boolean h){
        AgregarProducto.setEnabled(h);
       // txtCantidad.setEnabled(h);
        //txtPrecioCompra.setEnabled(h);
    }
     public void limpiar(){
        txtCodigo.setText("");
        txtDescripcion.setText("");
       // txtCantidad.setText("");
        txtPrecioCompra.setText("");
        txtTotal1.setText("");
        
        
       
    }
      public void habilitarbotones2(boolean k){
       // btnGuardar.setEnabled(k);
        btnCancelar.setEnabled(k);
         btnAgregar.setEnabled(k);
        btnQuitar.setEnabled(k);
        btnGuardar1.setEnabled(k);
        txtCantidad1.setEnabled(k);
        //btnCredito.setEnabled(k);
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
        public void habilitarguardar(){
        int x = tablafactura.getRowCount();
        
        if(tablafactura.getRowCount()!=0){

            btnGuardar.setEnabled(true);
        }
        else{
                btnGuardar.setEnabled(false);}
    }
    public void seleccionartablacliente(){
        
        
       DefaultTableModel tabla = (DefaultTableModel) this.tablacliente.getModel();
       
       
       int c= tablacliente.getSelectedRow();
       
       if(c==-1){
           System.out.println("Seleccione un registro");
            }
       else{
           Integer id = Integer.parseInt(tabla.getValueAt(c, 0).toString());
           String nombre = (String) tabla.getValueAt(c, 1);
           String apellido= (String) tabla.getValueAt(c, 2);;
//           String direccion= (String) tablacliente.getValueAt(c, 3);
           String ruc = (String) tabla.getValueAt(c, 3);
           
           

         //  this.txtIdcliente.setText(id);
           txtIdcliente.setText(Integer.toString(id));
           
           this.txtnombre.setText(nombre);
           this.txtCin.setText(ruc);        
           this.txtApellido.setText(apellido);
          // this.txtDireccion.setText(direccion);
           
           }       
    }
     public void cargarclientes(String valor ){
        String [] titulos  = {"Codigo","Nombre","Apellido","C.I/RUC"};
        String [] registros  = new String [4];
        String sql = "select idcliente, nombre, apellido, cinro from cliente where estado='1'";   
         
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
                registros[2] = rss.getString("apellido");
                registros[3] = rss.getString("cinro"); 
                modelo.addRow(registros);
                }
            tablacliente.setModel(modelo);
            tablacliente.removeColumn(tablacliente.getColumnModel().getColumn(0));
            } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex);
                            }   
    }
     
     //********************************************************************************************************************
       public void cargarproductodos(String valor ){
        String [] titulos  = {"Codigo","Producto","Precio","Stock"};
        String [] registros  = new String [4];
        String sql=null;
         sql = "select idproducto,descripcion,preciodecompra,stock from productos where tipo='producto'";
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
                registros[2] = rs.getString("preciodecompra");
                registros[3] = rs.getString("stock");
//              registros[2] = rs.getString("preciodeventa");
//              registros[3] = rs.getString("gananciaproducto");
                
//              registros[6] = rs.getString("recetas");
//              registros[7] = rs.getString("proveedor");
//              registros[7] = rs.getString("categoria");
//              registros[8] = rs.getString("iva");
                modelo.addRow(registros);
                
                }
            
            tablaProducto.setModel(modelo);
            tablaProducto.removeColumn(tablaProducto.getColumnModel().getColumn(0));
            tablaProducto.removeColumn(tablaProducto.getColumnModel().getColumn(2));
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
     
     
     //***************************************************************************************************************
      public void cargarproducto(String valor){
        String [] titulos  = {"Codigo","Precio Compra","Descripcion","Stock"};
        String [] registros  = new String [4];
        String sql=null;
         sql = "select idproducto,preciodecompra,descripcion,stock";
         sql+=" from productos";
         sql+=" where tipo='producto'";
       
         
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
                registros[3] = rs.getString("stock");
               
                m.addRow(registros);
                
                }
            tablaProductoo.setModel(m);
            
            }catch (SQLException ex) {
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
        public void seleccionartablaproducto(){
        
        
       //DefaultTableModel tabla = (DefaultTableModel) this.tablacliente.getModel();
       
       
       int c= tablaProductoo.getSelectedRow();
       
       if(c==-1){
           JOptionPane.showMessageDialog(null, "Seleccione un registro");
            }
       else{
          // String id = (String) tablaProducto.getValueAt(c, 0);
           Integer id = Integer.parseInt(tablaProductoo.getValueAt(c, 0).toString());
           Integer preciocompra = Integer.parseInt(tablaProductoo.getValueAt(c, 1).toString());
           //String precioventa = (String) tablaProducto.getValueAt(c, 1);
           String descripcion = (String) tablaProductoo.getValueAt(c, 2);
           Integer stock = Integer.parseInt(tablaProductoo.getValueAt(c, 3).toString());
          // Integer precioventa = Integer.parseInt(tablaProducto.getValueAt(c, 2).toString());
          
           
           txtCodigo.setText(Integer.toString(id));
          // this.txtCodigo.setText(id);
           //this.txtPrecioVenta.setText(precioventa);
           txtPrecioCompra.setText(Integer.toString(preciocompra));
           this.txtDescripcion.setText(descripcion);
           txtStock.setText(Integer.toString(stock));
          // txtPrecioVenta.setText(Integer.toString(precioventa));
           
           }       
    }
          void agregartablafactura(){
        
          DefaultTableModel tabla = (DefaultTableModel) this.tablafactura.getModel();
                                    Object[] valor = new Object[5];
                                    valor[0] = (String) txtCodigo.getText();
                                    valor[1] = (String) txtDescripcion.getText();
                                    valor[2] = (String) txtCantidad1.getText();
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
            void agregartabla(){  
                
            DefaultTableModel tabla = (DefaultTableModel) this.tablaProduccion.getModel();
                                    
        int totalRow = tablaProduccion.getRowCount();
        totalRow -= 1;
        int z = 0;
        for (int i = 0; i <= (totalRow); i++) {
          
            try {
                int valortabla = Integer.parseInt(String.valueOf(tabla.getValueAt(i, 2)));
                z = valortabla*(Integer.parseInt(txtCantidad1.getText()));//24
                Statement consultaingre = (Statement) conexionBD.ConectarBD().createStatement();
                ResultSet rs = consultaingre.executeQuery("select stock from productos where tipo='insumo' and idproducto='"+tabla.getValueAt(i, 0)+"'");
                rs.first();
                int stock= rs.getInt("stock");//12
                if(stock>=z ){
                    
                                               
                        tabla.setValueAt(z, i, 3);
                        btnGuardar1.setEnabled(true);
//                       
                       
                        
                        
                   }
                else{
                    JOptionPane.showMessageDialog(null, "Inusmo insuficiente para realizar la produccion");
                    btnGuardar1.setEnabled(false);
                    
//                DefaultTableModel tablaborrar = (DefaultTableModel) this.tablafactura.getModel();
//                while(tablaborrar.getRowCount()>0)tablaborrar.removeRow(0);
                    return;
                   // tablaProduccion.setValueAt(stock, i, 3);
                }
                
                      
            } catch (SQLException ex) {
                Logger.getLogger(Produccion.class.getName()).log(Level.SEVERE, null, ex);
            }
            
          }
         agregartablafactura();
                        
                        limpiar();
                        habilitarbotones2(true);
        txtTotal.setText(txtTotal1.getText());
                        sumadetalbla();
         //txtTotal.setText(txtTotal1.getText());
                        
                      
                        
                                   
      }
            
             public void seleccionartabla(){
        
        
       DefaultTableModel tabla = (DefaultTableModel) this.tablaProducto.getModel();
       
       
       int c= tablaProducto.getSelectedRow();
       
       if(c==-1){
           System.out.println("Seleccione un registro");
            }
       else{
           String id = (String) tabla.getValueAt(c, 0);       
           String producto = (String) tabla.getValueAt(c, 1);
           String precio = (String) tabla.getValueAt(c, 2);
           String stock = (String) tabla.getValueAt(c, 3);
           
           this.txtCodigo1.setText(id);
           this.txtProducto.setText(producto);
           txtCodigo.setText(id);
           txtDescripcion.setText(producto);
           txtPrecioCompra.setText(precio);
           txtStock.setText(stock);
            
           
           
           }       
    }
             public void cargaringredientes(String valor ){
        String [] titulos  = {"Codigo","Ingredientes","Cantidad","Cantidad a Producir","Medida"};
        String [] registros  = new String [5];
        String sql=null;
         sql = "select p.idproducto,p.descripcion as ingredientes,cantidad,m.descripcion as medida from productos as p ";
         sql+=" inner join recetas as r on(r.idinsumo=p.idproducto) ";
         sql+="inner join medida as m on(p.idmedida=m.idmedida)";
         sql+= " where tipo='insumo' and r.idproducto="+txtCodigo1.getText();
         
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
            tablaProduccion.removeColumn(tablaProduccion.getColumnModel().getColumn(0));
            tablaProduccion.removeColumn(tablaProduccion.getColumnModel().getColumn(1));
            tablaProduccion.removeColumn(tablaProduccion.getColumnModel().getColumn(2));
            tablaProduccion.removeColumn(tablaProduccion.getColumnModel().getColumn(1));
            
            } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex);
                            }   
    }
    
     void veringredientes(){
        try{
            Statement consultaingre = (Statement) conexionBD.ConectarBD().createStatement();
            ResultSet rs = consultaingre.executeQuery("select p.idproducto,p.descripcion as ingredientes,cantidad,m.descripcion as medida from productos as p " +
                       "inner join recetas as r on(r.idinsumo=p.idproducto)"
                    + " inner join medida as m on(p.idmedida=m.idmedida)  where tipo='insumo' and r.idproducto="+txtCodigo1.getText()+"");
            
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
//            modelo.removeRow(0);
           tablaProduccion.setModel(modelo);
    
        }catch(Exception e){
            System.out.println("Error al mostrar datos en la tabla "+e.getMessage());
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

        buscarcliente = new javax.swing.JDialog();
        txtBuscarCliente = new javax.swing.JTextField();
        btnAceptar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablacliente = new javax.swing.JTable();
        jLabel41 = new javax.swing.JLabel();
        buscarproductos = new javax.swing.JDialog();
        txtBuscarProdu = new javax.swing.JTextField();
        btnAceotarProdu = new javax.swing.JButton();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaProductoo = new javax.swing.JTable();
        produccion = new javax.swing.JDialog();
        jLabel2 = new javax.swing.JLabel();
        btnBuscar1 = new javax.swing.JButton();
        txtProducto = new javax.swing.JTextField();
        txtCodigo1 = new javax.swing.JTextField();
        buscarProductos = new javax.swing.JDialog();
        jScrollPane5 = new javax.swing.JScrollPane();
        tablaProducto = new javax.swing.JTable();
        jScrollPane4 = new javax.swing.JScrollPane();
        tablaProduccion = new javax.swing.JTable();
        btnAgregar = new javax.swing.JButton();
        txtCantidad1 = new javax.swing.JTextField();
        txtCantidadDos = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btnGuardar1 = new javax.swing.JButton();
        btnSalir1 = new javax.swing.JButton();
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
        txtIdcliente = new javax.swing.JTextField();
        btnContado = new javax.swing.JRadioButton();
        txtidfac = new javax.swing.JTextField();
        fecha = new javax.swing.JLabel();
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
        btnQuitar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        btnSalir = new javax.swing.JButton();
        txtTotal = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtNumFac = new javax.swing.JTextField();
        txtStock = new javax.swing.JTextField();

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

        tablacliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "Apellido", "C.I.N.", "Telefono", "Direccion"
            }
        ));
        tablacliente.getTableHeader().setReorderingAllowed(false);
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
        jScrollPane2.setViewportView(tablacliente);

        jLabel41.setText("Introduzca Nombre del Proveedor");

        javax.swing.GroupLayout buscarclienteLayout = new javax.swing.GroupLayout(buscarcliente.getContentPane());
        buscarcliente.getContentPane().setLayout(buscarclienteLayout);
        buscarclienteLayout.setHorizontalGroup(
            buscarclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buscarclienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buscarclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(buscarclienteLayout.createSequentialGroup()
                        .addComponent(txtBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnAceptar)
                        .addContainerGap(159, Short.MAX_VALUE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
            .addGroup(buscarclienteLayout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jLabel41)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        buscarclienteLayout.setVerticalGroup(
            buscarclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buscarclienteLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel41)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(buscarclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
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

        tablaProductoo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripcion", "PrecioVenta", "IVA%"
            }
        ));
        tablaProductoo.getTableHeader().setReorderingAllowed(false);
        tablaProductoo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaProductooMouseClicked(evt);
            }
        });
        tablaProductoo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaProductooKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaProductooKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tablaProductoo);

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

        produccion.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        produccion.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Pizza:");
        produccion.getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(81, 89, -1, -1));

        btnBuscar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buscar_1.png"))); // NOI18N
        btnBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscar1ActionPerformed(evt);
            }
        });
        produccion.getContentPane().add(btnBuscar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, 60, 30));

        txtProducto.setEnabled(false);
        produccion.getContentPane().add(txtProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, 110, -1));
        produccion.getContentPane().add(txtCodigo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 70, -1));

        buscarProductos.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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
        jScrollPane5.setViewportView(tablaProducto);

        buscarProductos.getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 359, 115));

        tablaProduccion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "codigo"
            }
        ));
        jScrollPane4.setViewportView(tablaProduccion);

        buscarProductos.getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 220, 110));

        btnAgregar.setText("Agregar");
        btnAgregar.setEnabled(false);
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        buscarProductos.getContentPane().add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 150, -1, 20));

        txtCantidad1.setEnabled(false);
        txtCantidad1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCantidad1KeyReleased(evt);
            }
        });
        buscarProductos.getContentPane().add(txtCantidad1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 150, 110, -1));

        txtCantidadDos.setEnabled(false);
        buscarProductos.getContentPane().add(txtCantidadDos, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 230, 70, -1));

        jLabel3.setText("Cantidad:");
        buscarProductos.getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, -1, -1));

        btnGuardar1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/add.png"))); // NOI18N
        btnGuardar1.setText("Guardar");
        btnGuardar1.setEnabled(false);
        btnGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardar1ActionPerformed(evt);
            }
        });
        buscarProductos.getContentPane().add(btnGuardar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 340, 100, 30));

        btnSalir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/equis.png"))); // NOI18N
        btnSalir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir1ActionPerformed(evt);
            }
        });
        buscarProductos.getContentPane().add(btnSalir1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 340, 90, 30));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel14.setText("RUC o C.I.N.:");
        jPanel3.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, -1, -1));

        jLabel15.setText("Cliente:");
        jPanel3.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, -1));

        jLabel8.setText("Telef:");
        jPanel3.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 80, -1, -1));

        txtnombre.setEnabled(false);
        jPanel3.add(txtnombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 100, -1));

        txtTelef.setEnabled(false);
        jPanel3.add(txtTelef, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 80, 120, -1));

        txtCin.setEnabled(false);
        jPanel3.add(txtCin, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 80, 110, -1));

        txtApellido.setEnabled(false);
        jPanel3.add(txtApellido, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 20, 180, -1));

        jLabel17.setText("Direccion:");
        jPanel3.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, -1, -1));

        txtDireccion.setEnabled(false);
        jPanel3.add(txtDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 290, -1));

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/clientes.png"))); // NOI18N
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel3.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 80, 60));
        jPanel3.add(txtIdcliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 50, 50, 20));

        btnContado.setText("Contado");
        jPanel3.add(btnContado, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 80, -1, -1));

        txtidfac.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtidfacFocusLost(evt);
            }
        });
        jPanel3.add(txtidfac, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 10, 50, 30));

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 670, 110));

        fecha.setText("dd/mm/yy");
        getContentPane().add(fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 80, 60, 20));

        txtCodigo.setEnabled(false);
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

        txtCantidad.setEnabled(false);
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
        getContentPane().add(AgregarProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 110, 50, 20));

        btnQuitar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/basurero.png"))); // NOI18N
        btnQuitar.setEnabled(false);
        btnQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuitarActionPerformed(evt);
            }
        });
        getContentPane().add(btnQuitar, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 140, 50, 20));

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/articulos.png"))); // NOI18N
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        getContentPane().add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 360, 80, 50));

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

        txtTotal.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        txtTotal.setEnabled(false);
        getContentPane().add(txtTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 350, 210, 30));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setText("Total Costo");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 350, 80, 30));

        txtNumFac.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        txtNumFac.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtNumFacKeyTyped(evt);
            }
        });
        getContentPane().add(txtNumFac, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 30, 30, 30));
        getContentPane().add(txtStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 160, 30, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed
        buscarcliente.setModal(true);
        buscarcliente.setSize(450, 300);
        buscarcliente.setLocationRelativeTo(this);
        buscarcliente.setVisible(true);
        habilitarbotones(true);
    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBuscarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarClienteActionPerformed

    private void txtBuscarClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarClienteKeyReleased
        if(txtBuscarCliente.getText().isEmpty()){
      //      cargarcliente("");
        }
        else{//cargarclinetesbuscar("");
        }
    }//GEN-LAST:event_txtBuscarClienteKeyReleased

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        this.buscarcliente.dispose();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void tablaclienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaclienteMouseClicked

        seleccionartablacliente();
    }//GEN-LAST:event_tablaclienteMouseClicked

    private void tablaclienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaclienteKeyReleased
        seleccionartablacliente();
    }//GEN-LAST:event_tablaclienteKeyReleased

    private void txtBuscarProduActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarProduActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarProduActionPerformed

    private void txtBuscarProduKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProduKeyReleased
        if(txtBuscarProdu.getText().isEmpty()){
           // cargarproducto("");
        }
        else{
            //            cargarproductobuscar();
        }
    }//GEN-LAST:event_txtBuscarProduKeyReleased

    private void btnAceotarProduActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceotarProduActionPerformed
        this.buscarproductos.dispose();
       // txtCantidad.setText("1");
        int precioventa, cantidad, total;

       // cantidad = Integer.parseInt(txtCantidad.getText().toString());
        //precioventa = Integer.parseInt(txtPrecioCompra.getText().toString());
       // total = cantidad*precioventa;
       // txtTotal1.setText(String.valueOf(total));
    }//GEN-LAST:event_btnAceotarProduActionPerformed

    private void tablaProductooMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductooMouseClicked
        seleccionartablaproducto();
    }//GEN-LAST:event_tablaProductooMouseClicked

    private void tablaProductooKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaProductooKeyPressed

    }//GEN-LAST:event_tablaProductooKeyPressed

    private void tablaProductooKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaProductooKeyReleased
        //        seleccionartablaproducto();
    }//GEN-LAST:event_tablaProductooKeyReleased

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
//        produccion.setModal(true);
//        produccion.setSize(440, 398);
//        produccion.setLocationRelativeTo(this);
//        produccion.setVisible(true);
       // verproducto();
        cargarproductodos("");

       
//        JDialog8 = new Produccion();
//        JDialog8.setLocationRelativeTo(this);
//        JDialog8.setModal(true);
//        JDialog8.setVisible(true);
        
            buscarProductos.setModal(true);
            buscarProductos.setSize(500, 450);
            buscarProductos.setLocationRelativeTo(this);
            buscarProductos.setVisible(true);
        habilitarbotonestg(true);
        btnAgregar.setEnabled(false);

    }//GEN-LAST:event_buscarProduActionPerformed

    private void AgregarProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarProductoActionPerformed
   //      agregartabla();
        
        int stock, cantidad;
        stock = Integer.parseInt(txtStock.getText().toString());
        System.out.println(stock);
        cantidad= Integer.parseInt(txtCantidad.getText().toString());
        System.out.println(cantidad);
        if(cantidad<=stock){
            agregartablafactura();
            txtTotal.setText(txtTotal1.getText());
            sumadetalbla();
            limpiar();
            habilitarbotones2(true);
        }else{
            if(cantidad>stock){
           JOptionPane.showMessageDialog(null,"Baja existencia del Producto");
                }
        } 
        
        
               habilitarbotonestg(false);
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
                        String anulado = "cliente", estado = "pendiente";
                        int  pagado=0 ,condicion=0;
                        v_control = abm.insertar("pedido", txtidfac.getText()+","+txtNumFac.getText() +",'"
                            + fecha.getText() + "','" + estado + "','" + anulado + "'," +txtIdcliente.getText() + "," + 0+
                            "," + pagado + "," +condicion+","+idempleado+","+ 0 +"," + 0 + "," + 0 + "," + txtTotal.getText()+",'"+fecha.getText()+"',"
                                +txtIdcliente.getText());
                        System.out.println("despues del print");
                    }

                    if (v_control == false) {
                        abm.roolback();
                    }

                    if (v_control == true) {

                        for (int i = 0; i < tablafactura.getRowCount(); i++) {
//
                          Statement consulta = (Statement) conexionBD.ConectarBD().createStatement();
                                                        consulta.execute("update productos set stock=stock+"+tablafactura.getValueAt(i, 2) +
                                                               " where tipo='producto' and idproducto="+tablafactura.getValueAt(i, 0));
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
          //  habilitarbotonestg(false);
            habilitarbotones2(false);
            limpiarcamposcabezara();
          //  idfactura();
            rs = abm.nuevo("numero", "pedido");
            rs.first();
            txtNumFac.setText(String.valueOf(rs.getInt("codigo") + 1));
        } catch (SQLException ex) {
            Logger.getLogger(PedidoClienteDos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

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
            System.out.println("1029");
            if(rs.getRow()!= 0){
                JOptionPane.showMessageDialog(null, "Este Codigo ya existe");
                txtidfac.requestFocus();
                rs = abm.nuevo("numero", "pedido");
                rs.first();
                txtidfac.setText(String.valueOf(rs.getInt("codigo") + 1));
                

            }
        } catch (SQLException ex) {
            Logger.getLogger(PedidoProveedor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_txtidfacFocusLost

    private void txtNumFacKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumFacKeyTyped
        char caracter=evt.getKeyChar();
        if((caracter<'0'||(caracter>'9'))&&(caracter!='\b')){
            evt.consume();
        }
    }//GEN-LAST:event_txtNumFacKeyTyped

    private void btnBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscar1ActionPerformed
        // TODO add your handling code here:
        buscarProductos.setModal(true);
        buscarProductos.setSize(450, 370);
        buscarProductos.setLocationRelativeTo(this);
        buscarProductos.setVisible(true);
    }//GEN-LAST:event_btnBuscar1ActionPerformed

    private void btnGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardar1ActionPerformed
        // TODO add your handling code here:
        insertar();
        
    }//GEN-LAST:event_btnGuardar1ActionPerformed

    public void insertar(){
        try {

            abm.start();

            // int idempleado = abm.idUsuario;

            //                    rs = abm.nuevo("idpedido", "pedido");
            //                    rs.first();
            //                    txtidfac.setText(String.valueOf(rs.getInt("codigo")+1));

            v_control=abm.modificar("productos","stock=stock+'"+txtCantidadDos.getText()+"'", "tipo='producto' and idproducto="+txtCodigo1.getText());

            if (v_control==true){
                JOptionPane.showMessageDialog(null,"Se ha guardado los datos en productos");
            }

            if (v_control == true) {
                DefaultTableModel tabla = (DefaultTableModel) this.tablaProduccion.getModel();
                for (int i = 0; i < tabla.getRowCount(); i++) {
                    Statement consulta = (Statement) conexionBD.ConectarBD().createStatement();
                    consulta.execute("update productos set stock=stock-"+tabla.getValueAt(i, 3)+
                        " where tipo='insumo' and idproducto="+tabla.getValueAt(i, 0));

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

        btnGuardar.setEnabled(true);
        btnAgregar.setEnabled(false);
    }
    
    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
        //agregartablafactura();
        agregartabla();
        txtCantidadDos.setText(txtCantidad1.getText());
        txtCantidad1.setText("");
        btnAgregar.setEnabled(false);
         
       

    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnSalir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalir1ActionPerformed
        buscarProductos.dispose();
        txtCantidad1.setText("");
        txtCantidadDos.setText("");
        //txtTotal.setText("");
        
        DefaultTableModel tablaborrar = (DefaultTableModel) this.tablaProduccion.getModel();
        while(tablaborrar.getRowCount()>0)tablaborrar.removeRow(0);
//        DefaultTableModel tablaborrarr = (DefaultTableModel) this.tablafactura.getModel();
//        while(tablaborrarr.getRowCount()>0)tablaborrarr.removeRow(0);
         habilitarbotones2(false);
    }//GEN-LAST:event_btnSalir1ActionPerformed

    private void tablaProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductoMouseClicked
        seleccionartabla();
        veringredientes();
        
       
        txtCantidad1.setEnabled(true);
         
        
        
        cargaringredientes("");
    }//GEN-LAST:event_tablaProductoMouseClicked

    private void tablaProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaProductoKeyPressed

    }//GEN-LAST:event_tablaProductoKeyPressed

    private void tablaProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaProductoKeyReleased
        seleccionartabla();
         veringredientes();
        cargaringredientes("");
    }//GEN-LAST:event_tablaProductoKeyReleased

    private void tablaProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaProductoKeyTyped

    }//GEN-LAST:event_tablaProductoKeyTyped

    private void txtCantidad1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCantidad1KeyReleased
        // TODO add your handling code here:
        if(txtCantidad1.getText().isEmpty()){
            txtCantidad1.setText("1");
        }else{
            int precioventa, cantidad, total;
            cantidad = Integer.parseInt(txtCantidad1.getText().toString());
            precioventa = Integer.parseInt(txtPrecioCompra.getText().toString());
            total = cantidad*precioventa;
            txtTotal1.setText(String.valueOf(total));
        }
         btnAgregar.setEnabled(true);
    }//GEN-LAST:event_txtCantidad1KeyReleased

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
            java.util.logging.Logger.getLogger(PedidoClienteDos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PedidoClienteDos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PedidoClienteDos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PedidoClienteDos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PedidoClienteDos dialog = new PedidoClienteDos(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscar1;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JRadioButton btnContado;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardar1;
    private javax.swing.JButton btnQuitar;
    private javax.swing.JButton btnSalir;
    private javax.swing.JButton btnSalir1;
    private javax.swing.JButton buscarProdu;
    private javax.swing.JDialog buscarProductos;
    private javax.swing.JDialog buscarcliente;
    private javax.swing.JDialog buscarproductos;
    private javax.swing.JLabel fecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JDialog produccion;
    private javax.swing.JTable tablaProduccion;
    private javax.swing.JTable tablaProducto;
    private javax.swing.JTable tablaProductoo;
    private javax.swing.JTable tablacliente;
    private javax.swing.JTable tablafactura;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtBuscarCliente;
    private javax.swing.JTextField txtBuscarProdu;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCantidad1;
    private javax.swing.JTextField txtCantidadDos;
    private javax.swing.JTextField txtCin;
    private javax.swing.JTextField txtCodigo;
    private javax.swing.JTextField txtCodigo1;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtIdcliente;
    private javax.swing.JTextField txtNumFac;
    private javax.swing.JTextField txtPrecioCompra;
    private javax.swing.JTextField txtProducto;
    private javax.swing.JTextField txtStock;
    private javax.swing.JTextField txtTelef;
    private javax.swing.JTextField txtTotal;
    private javax.swing.JTextField txtTotal1;
    private javax.swing.JTextField txtidfac;
    private javax.swing.JTextField txtnombre;
    // End of variables declaration//GEN-END:variables
}
