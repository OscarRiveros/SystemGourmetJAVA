/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModulosProduccion;

//import Jcombox.ingredientes;
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
import productos.AgregarProducto;
import reportes.reporte;

/**
 *
 * @author USER
 */
public class Recetas extends javax.swing.JDialog {

    /**
     * Creates new form InventarioConsumos
     */
    private reporte jasper;
    conexionBD cn;
    private abm abm;
    private ResultSet rs,rscbo;
    private static char opcion;
    private boolean vacio;
    private boolean v_control;//si guardo bien o mal
    DefaultTableModel modelo;
    private Object[] filas;
    AgregarProducto JDialog6;
    public Recetas() {
        initComponents();
        
        cn= new conexionBD();
       
        
        abm= new abm();
        rs=abm.consulta("*", "productos");
//        MostrarRegistro();
//        verreceta();
//        cargarreceta("");
       
        verproducto();
        cargarproducto("");
        cargaringredientes("");
        veringredientes();
        codigo();
        txtCodigoUno.setVisible(false); 
        jasper=new reporte();
    }
    
      public void cargaringredientesdos(String valor ){
        String [] titulos  = {"Codigo","Insumo","Existencia","Medida"};
        String [] registros  = new String [4];
        String sql=null;
         sql = "select p.idproducto,p.descripcion,cantidad, m.descripcion from productos as p ";
         sql+=" inner join recetas as r on(r.idinsumo=p.idproducto) ";
         sql+="inner join medida as m on(m.idmedida=p.idmedida) ";
         sql+= " where tipo='insumo' and r.idproducto="+txtCodigoUno.getText();
         
        // sql+=" where idproducto="+txtIdProducto.getText();
        
        modelo = new DefaultTableModel(null, titulos);
    
        conexionBD cn = new conexionBD();
        Connection cnn = (Connection) cn.ConectarBD();
        Statement  st;
        try {
            
            st = (Statement) cnn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
//                registros[0] = rs.getString("ingredientes");
//                registros[1] = rs.getString("cantidad");
//                registros[2] = rs.getString("preciodeventa");
//                registros[3] = rs.getString("gananciaproducto");
                
//                registros[6] = rs.getString("recetas");
//                registros[7] = rs.getString("proveedor");
//                registros[7] = rs.getString("categoria");
//                registros[8] = rs.getString("iva");
                 registros[0] = rs.getString("idproducto");
                //registros[2] = rs.getString("preciodeventa");
//                registros[3] = rs.getString("gananciaproducto");
                registros[1] = rs.getString("descripcion");
                registros[2] = rs.getString("cantidad");
                registros[3] = rs.getString("m.descripcion");
                modelo.addRow(registros);
                }
            tablaIngredientes.setModel(modelo);
            
            } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex);
                            }   
    }
    
     void veringredientesDos(){
        try{
            Statement consultaingre = (Statement) conexionBD.ConectarBD().createStatement();
            ResultSet rs = consultaingre.executeQuery("select p.idproducto,p.descripcion as ingredientes,cantidad from productos as p " +
                       "inner join recetas as r on(r.idinsumo=p.idproducto) where tipo='insumo' and r.idproducto="+txtCodigoUno.getText()+"");
            
            modelo = new DefaultTableModel();
            tablaIngredientes.setModel(modelo);
            
            
                 
            modelo.addColumn("Codigo");
            modelo.addColumn("Insumo");
            modelo.addColumn("Existencia");
            modelo.addColumn("Medida");
               
            
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
            
           tablaIngredientes.setModel(modelo);
    
        }catch(Exception e){
            System.out.println("Error al mostrar datos en la tabla"+e.getMessage());
        }
    }
    
    void veringredientes(){
        try{
            Statement consultamarca = (Statement) conexionBD.ConectarBD().createStatement();
            ResultSet rs = consultamarca.executeQuery("select idproducto,p.descripcion,stock, m.descripcion from productos as p inner join medida as m on(m.idmedida=p.idmedida) where tipo='insumo'");
            
            modelo = new DefaultTableModel();
            tablaIngredientesDos.setModel(modelo);
            
            
            modelo.addColumn("Codigo");
            modelo.addColumn("Insumo");
            modelo.addColumn("Existencia");
             modelo.addColumn("Medida");
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
            
            tablaIngredientesDos.setModel(modelo);
    
        }catch(Exception e){
            System.out.println("Error al mostrar datos en la tabla"+e.getMessage());
        }
    }
     public void cargaringredientes(String valor ){
        String [] titulos  = {"Codigo","Insumo","Existencia","Medida"};
        String [] registros  = new String [4];
        String sql=null;
         sql = "select idproducto,p.descripcion,stock, m.descripcion from productos as p inner join medida as m on(m.idmedida=p.idmedida) where tipo='insumo'";
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
                //registros[2] = rs.getString("preciodeventa");
//                registros[3] = rs.getString("gananciaproducto");
                registros[1] = rs.getString("descripcion");
                registros[2] = rs.getString("stock");
                registros[3] = rs.getString("descripcion");
                
                
//                registros[6] = rs.getString("recetas");
//                registros[7] = rs.getString("proveedor");
//                registros[7] = rs.getString("categoria");
//                registros[8] = rs.getString("iva");
                modelo.addRow(registros);
                }
            tablaIngredientesDos.setModel(modelo);
            
            } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex);
                            }   
    }
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
    
    
    
    public void seleccionartablingredientes(){
        
        
       //DefaultTableModel tabla = (DefaultTableModel) this.tablacliente.getModel();
       
       
       int c= tablaIngredientesDos.getSelectedRow();
       
       if(c==-1){
           JOptionPane.showMessageDialog(null, "Seleccione un registro");
            }
       else{
          // String id = (String) tablaProducto.getValueAt(c, 0);
           Integer id = Integer.parseInt(tablaIngredientesDos.getValueAt(c, 0).toString());
//           Integer descripcion = Integer.parseInt(tablaIngredientesDos.getValueAt(c, 1).toString());
           String descripcion = (String) tablaIngredientesDos.getValueAt(c, 1);
           //String precioventa = (String) tablaProducto.getValueAt(c, 1);
           Integer existencia = Integer.parseInt(tablaIngredientesDos.getValueAt(c, 2).toString());
          // String marca = (String) tablaProducto.getValueAt(c, 3);
           String medida = (String) tablaIngredientesDos.getValueAt(c, 3);
           // txtStock.setText(Integer.toString(stock));
           //txtCodigoIngre.setText(Integer.toString(id));
           this.txtCodigoIngre.setText(Integer.toString(id));
           this.txtDescriIngredientes.setText(descripcion);
       //    txtPrecioVenta.setText(Integer.toString(precioventa));
           this.txtMedida.setText(medida);
          // this.txtExistencia.setText(Integer.toString(existencia));
       //    txtIVA.setText(Integer.toString(iva));
           }       
    }
    
     public void cargarproductobuscar(){
        String [] titulos  = {"Codigo","Producto"};
        String [] registros  = new String [2];
        String sql=null;
         sql = "select idproducto,descripcion from productos where tipo='producto'";
         sql+=" from productos ";
         sql+=" where descripcion LIKE '%"+txtBuscarProdu.getText()+"%'";
         //System.out.println(sql);
         
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
//                registros[2] = rs.getString("descripcion");
//                registros[3] = rs.getString("stock");
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
    
    
    public  boolean validardatos(){
   vacio=false;
   
    
//   if(txtNombre.getText().isEmpty()){
//       vacio=true;
//        }
//   if(txtIdReceta.getText().isEmpty()){
//       vacio=true;
//   }
//   if(txtCantidad.getText().isEmpty()){
//       vacio=true;
//   }
   
   return vacio;
}
    public void habilitarbotones(boolean j){
        btnBuscarIngredientes.setEnabled(j);
       // btnAgregarDos.setEnabled(j);
        //btnGuardarDos.setEnabled(j);
        
        
    }
    public void habilitarcampos(boolean h){
        txtCodigoIngre.setEnabled(h);
        txtDescriIngredientes.setEnabled(h);
        txtCantidad.setEnabled(h);
        
    }
      
    
    
    
     public void codigo(){
        try {
            rs = abm.nuevo("idreceta", "recetas");
            rs.first();
            txtIdReceta.setText(String.valueOf(rs.getInt("codigo")+1));
            txtIdReceta.requestFocus();//mantiene el enfoque en un objeto
        } catch (SQLException ex) {
            Logger.getLogger(Recetas.class.getName()).log(Level.SEVERE, null, ex);
        }
      }
    
      
     
   
     
    
     
      public void limpiardos(){
        txtCodigoIngre.setText("");
        txtDescriIngredientes.setText("");
        txtCantidad.setText("");
        txtCantidad.setText("");
        txtMedida.setText("");
    }
    void verreceta(){
        try{
            Statement consultamarca = (Statement) conexionBD.ConectarBD().createStatement();
            ResultSet rs = consultamarca.executeQuery("select idreceta, descripcion, cantidad,  from recetas");
            
            modelo = new DefaultTableModel();
            tablaProducto.setModel(modelo);
            
            
            modelo.addColumn("Codigo");
            modelo.addColumn("Receta");
            modelo.addColumn("Cantidad");
            
            
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
    public void cargarreceta(String valor ){
        String [] titulos  = {"Codigo","Receta","Cantidad"};
        String [] registros  = new String [3];
        
        String sql = "select idreceta, descripcion, cantidad from recetas where CONCAT(idreceta,' ',descripcion) LIKE '%"+valor+"%'";  
        modelo = new DefaultTableModel(null, titulos);
    
        conexionBD cn = new conexionBD();
        Connection cnn = (Connection) cn.ConectarBD();
        Statement  st;
        try {
            
            st = (Statement) cnn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                registros[0] = rs.getString("idreceta");
                registros[1] = rs.getString("descripcion");
                registros[2] = rs.getString("cantidad");
               
                
                modelo.addRow(registros);
                }
            tablaProducto.setModel(modelo);
            
            } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex);
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
                 
          
           
//           this.txtIdReceta.setText(id);
//           this.txtNombre.setText(receta);
//           this.txtCantidad.setText(cantidad);
           this.txtIdProductoDos.setText(id);
           this.txtNombreDos.setText(producto);
           this.txtCodigoUno.setText(id);
           
            
           }       
    }
   
       void agregartablafactura(){
        
       DefaultTableModel tabla = (DefaultTableModel) this.tablaIngredientes.getModel();
        
       //int iva= tablafactura.getSelectedRow();
       
       if(txtCantidad.getText().isEmpty()){
           JOptionPane.showMessageDialog(null,"Completar cantidad");
           txtCantidad.requestFocus();
       }
       else{
           
           Object[] valor = new Object[4];
           valor[0] = (String) txtCodigoIngre.getText();
           valor[1] = (String) txtDescriIngredientes.getText();
           valor[2] = (String) txtCantidad.getText();
           valor[3] = (String) txtMedida.getText();
          
           
           tabla.addRow(valor);
           
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

        ingredientes = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaIngredientes = new javax.swing.JTable();
        txtNombreDos = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtIdProductoDos = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        btnGuardarDos = new javax.swing.JButton();
        btnAgregarDos = new javax.swing.JButton();
        btnBuscarIngredientes = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        txtCodigoIngre = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtDescriIngredientes = new javax.swing.JTextField();
        txtMedida = new javax.swing.JLabel();
        txtExistencia = new javax.swing.JLabel();
        txtIdReceta = new javax.swing.JTextField();
        btnSalir1 = new javax.swing.JToggleButton();
        buscaringredientes = new javax.swing.JDialog();
        txtBuscarProdu = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        btnAceotarProdu = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaIngredientesDos = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        btnSalir = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProducto = new javax.swing.JTable();
        btnIngredientes = new javax.swing.JButton();
        txtCodigoUno = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        ingredientes.setModalExclusionType(null);
        ingredientes.setModalityType(null);
        ingredientes.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaIngredientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Ingrediente", "Cantidad", "Unidad de Medida"
            }
        ));
        tablaIngredientes.setEnabled(false);
        tablaIngredientes.getTableHeader().setReorderingAllowed(false);
        tablaIngredientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaIngredientesMouseClicked(evt);
            }
        });
        tablaIngredientes.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaIngredientesKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaIngredientesKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tablaIngredientesKeyTyped(evt);
            }
        });
        jScrollPane2.setViewportView(tablaIngredientes);

        ingredientes.getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 560, 340));

        txtNombreDos.setEnabled(false);
        ingredientes.getContentPane().add(txtNombreDos, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 153, -1));

        jLabel11.setText("Nombre:");
        ingredientes.getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, -1, -1));

        txtIdProductoDos.setEnabled(false);
        ingredientes.getContentPane().add(txtIdProductoDos, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 100, -1));

        jLabel14.setText("Codigo:");
        ingredientes.getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, -1, -1));

        jLabel15.setFont(new java.awt.Font("Sitka Text", 1, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(0, 153, 0));
        jLabel15.setText("Productos");
        ingredientes.getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 10, 140, 30));

        jLabel17.setFont(new java.awt.Font("Sitka Text", 1, 24)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(0, 153, 0));
        jLabel17.setText("Receta");
        ingredientes.getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, 80, 30));

        jLabel18.setText("Insumos para ingredientes:");
        ingredientes.getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 140, 138, -1));

        jLabel19.setText("Cantidad:");
        ingredientes.getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 180, -1, -1));
        ingredientes.getContentPane().add(txtCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 180, 70, -1));

        btnGuardarDos.setText("Guardar");
        btnGuardarDos.setEnabled(false);
        btnGuardarDos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarDosActionPerformed(evt);
            }
        });
        ingredientes.getContentPane().add(btnGuardarDos, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 300, -1, -1));

        btnAgregarDos.setText("Agregar");
        btnAgregarDos.setEnabled(false);
        btnAgregarDos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarDosActionPerformed(evt);
            }
        });
        ingredientes.getContentPane().add(btnAgregarDos, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 230, -1, -1));

        btnBuscarIngredientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/add.png"))); // NOI18N
        btnBuscarIngredientes.setText("Buscar");
        btnBuscarIngredientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarIngredientesActionPerformed(evt);
            }
        });
        ingredientes.getContentPane().add(btnBuscarIngredientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 160, -1, -1));

        jLabel1.setText("Codigo:");
        ingredientes.getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 180, -1, -1));
        ingredientes.getContentPane().add(txtCodigoIngre, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, 50, -1));

        jLabel2.setText("Ingrediente:");
        ingredientes.getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 180, -1, -1));
        ingredientes.getContentPane().add(txtDescriIngredientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, 90, -1));
        ingredientes.getContentPane().add(txtMedida, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 180, 50, 20));
        ingredientes.getContentPane().add(txtExistencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 340, 40, 20));
        ingredientes.getContentPane().add(txtIdReceta, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, 40, -1));

        btnSalir1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cancel.png"))); // NOI18N
        btnSalir1.setText("Salir");
        btnSalir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalir1ActionPerformed(evt);
            }
        });
        ingredientes.getContentPane().add(btnSalir1, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 340, 90, 30));

        txtBuscarProdu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtBuscarProdu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarProduActionPerformed(evt);
            }
        });
        txtBuscarProdu.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarProduKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtBuscarProduKeyTyped(evt);
            }
        });

        jLabel42.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabel42.setForeground(new java.awt.Color(0, 153, 255));
        jLabel42.setText("Introducir Nombre de Ingrediente");

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buscar.png"))); // NOI18N

        btnAceotarProdu.setText("Aceptar");
        btnAceotarProdu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceotarProduActionPerformed(evt);
            }
        });

        tablaIngredientesDos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Descripcion", "Cantidad", "Existencia"
            }
        ));
        tablaIngredientesDos.getTableHeader().setReorderingAllowed(false);
        tablaIngredientesDos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaIngredientesDosMouseClicked(evt);
            }
        });
        tablaIngredientesDos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tablaIngredientesDosKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaIngredientesDosKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(tablaIngredientesDos);

        javax.swing.GroupLayout buscaringredientesLayout = new javax.swing.GroupLayout(buscaringredientes.getContentPane());
        buscaringredientes.getContentPane().setLayout(buscaringredientesLayout);
        buscaringredientesLayout.setHorizontalGroup(
            buscaringredientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buscaringredientesLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(buscaringredientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(buscaringredientesLayout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(4, 4, 4)
                        .addGroup(buscaringredientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(buscaringredientesLayout.createSequentialGroup()
                                .addGap(24, 24, 24)
                                .addComponent(jLabel42))
                            .addComponent(txtBuscarProdu, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addComponent(btnAceotarProdu))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        buscaringredientesLayout.setVerticalGroup(
            buscaringredientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buscaringredientesLayout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(buscaringredientesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(buscaringredientesLayout.createSequentialGroup()
                        .addGap(8, 8, 8)
                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(buscaringredientesLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(txtBuscarProdu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(buscaringredientesLayout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(btnAceotarProdu)))
                .addGap(11, 11, 11)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Sitka Text", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 153, 0));
        jLabel6.setText("Lista de Recetas");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 10, 220, 30));

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cancel.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        getContentPane().add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 200, 110, 50));

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
        jScrollPane1.setViewportView(tablaProducto);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 370, 110));

        btnIngredientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/info2_1.png"))); // NOI18N
        btnIngredientes.setText("Insertar ingredientes");
        btnIngredientes.setEnabled(false);
        btnIngredientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngredientesActionPerformed(evt);
            }
        });
        getContentPane().add(btnIngredientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 100, 160, 20));
        getContentPane().add(txtCodigoUno, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 40, -1));

        jButton1.setText("Agregar Productos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 60, -1, -1));

        jButton2.setText("Imprimir Receta");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 160, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void tablaProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductoMouseClicked
       seleccionartabla();
       
       btnIngredientes.setEnabled(true);
       cargaringredientesdos("");
    }//GEN-LAST:event_tablaProductoMouseClicked

    private void tablaProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaProductoKeyPressed

    }//GEN-LAST:event_tablaProductoKeyPressed

    private void tablaProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaProductoKeyReleased
        seleccionartabla();
    }//GEN-LAST:event_tablaProductoKeyReleased

    private void tablaProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaProductoKeyTyped

    }//GEN-LAST:event_tablaProductoKeyTyped

    private void btnIngredientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngredientesActionPerformed
        // TODO add your handling code here:
  
      // cargaringredientesdos("");
        //veringredientesDos();
        if (this.tablaIngredientes.getRowCount() == 0 ) {
               //JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
            habilitarbotones(true);
            habilitarcampos(true);
          System.out.println("hola");
            }else{
                        habilitarbotones(false);
                        habilitarcampos(false);

                }
        ingredientes.setModal(false);
        ingredientes.setSize(722, 504);
        ingredientes.setLocationRelativeTo(this);
        ingredientes.setVisible(true);
        
         
        

    }//GEN-LAST:event_btnIngredientesActionPerformed

    private void tablaIngredientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaIngredientesMouseClicked
        seleccionartabla();
    }//GEN-LAST:event_tablaIngredientesMouseClicked

    private void tablaIngredientesKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaIngredientesKeyPressed

    }//GEN-LAST:event_tablaIngredientesKeyPressed

    private void tablaIngredientesKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaIngredientesKeyReleased
        seleccionartabla();
    }//GEN-LAST:event_tablaIngredientesKeyReleased

    private void tablaIngredientesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaIngredientesKeyTyped

    }//GEN-LAST:event_tablaIngredientesKeyTyped

    private void btnGuardarDosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarDosActionPerformed
        // TODO add your handling code here:
        vacio=validardatos();
        if(vacio==true){
            JOptionPane.showMessageDialog(null,"Completar los datos marcados en aterisco");
        }else{
            //                conexionBD cn = new conexionBD();// se crea la conexion
            //                Connection cnn = (Connection) cn.ConectarBD();
            //                Statement  st;
            try {
                String hola="hola";
                for (int i = 0; i < tablaIngredientes.getRowCount(); i++) {
                    v_control=abm.insertar("recetas",txtIdReceta.getText()+",'"+txtNombreDos.getText()+"',"+tablaIngredientes.getValueAt(i, 2)+","+1+","+txtIdProductoDos.getText()+","+tablaIngredientes.getValueAt(i, 0));

                }
                if (v_control==true){
                    JOptionPane.showMessageDialog(null,"Se ha guardado los datos");
                }

            }catch(Exception e){
                System.out.println("Error al mostrar datos en la tabla"+e.getMessage());
            }
        }
    }//GEN-LAST:event_btnGuardarDosActionPerformed

    private void btnAgregarDosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarDosActionPerformed
        // TODO add your handling code here:
        
        
        
        agregartablafactura();
        limpiardos();
        btnGuardarDos.setEnabled(true);
        
    
        
        
    }//GEN-LAST:event_btnAgregarDosActionPerformed

    private void btnBuscarIngredientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarIngredientesActionPerformed
        // TODO add your handling code here:
        buscaringredientes.setModal(true);
        buscaringredientes.setSize(722, 504);
        buscaringredientes.setLocationRelativeTo(this);
        buscaringredientes.setVisible(true);

    }//GEN-LAST:event_btnBuscarIngredientesActionPerformed

    private void txtBuscarProduActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarProduActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarProduActionPerformed

    private void txtBuscarProduKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProduKeyReleased
        if(txtBuscarProdu.getText().isEmpty()){
            cargarproducto("");
        }
        else{
            cargarproductobuscar();
        }
    }//GEN-LAST:event_txtBuscarProduKeyReleased

    private void txtBuscarProduKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarProduKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarProduKeyTyped

    private void btnAceotarProduActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceotarProduActionPerformed
        this.buscaringredientes.dispose();
        txtCantidad.setText("1");
        int precioventa, cantidad, total;

        cantidad = Integer.parseInt(txtCantidad.getText().toString());
        btnAgregarDos.setEnabled(true);
        //precioventa = Integer.parseInt(txtPrecioVenta.getText().toString());
        // total = cantidad*precioventa;
        // txtTotal1.setText(String.valueOf(total));
    }//GEN-LAST:event_btnAceotarProduActionPerformed

    private void tablaIngredientesDosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaIngredientesDosMouseClicked
        seleccionartablingredientes();
    }//GEN-LAST:event_tablaIngredientesDosMouseClicked

    private void tablaIngredientesDosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaIngredientesDosKeyPressed

    }//GEN-LAST:event_tablaIngredientesDosKeyPressed

    private void tablaIngredientesDosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaIngredientesDosKeyReleased
        //    seleccionartablaproducto();
    }//GEN-LAST:event_tablaIngredientesDosKeyReleased

    private void btnSalir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalir1ActionPerformed
        // TODO add your handling code here:
        this.ingredientes.dispose();
    }//GEN-LAST:event_btnSalir1ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        JDialog6 = new AgregarProducto();
        JDialog6.setLocationRelativeTo(this);
        JDialog6.setModal(true);
        JDialog6.setVisible(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
         String ubicacion="/reportes/Recetas.jasper";
         String parametrop=(txtNombreDos.getText());
         System.out.println(parametrop);
         jasper.runReporte_parametroSting(ubicacion,"producto" ,parametrop);
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(Recetas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Recetas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Recetas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Recetas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Recetas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceotarProdu;
    private javax.swing.JButton btnAgregarDos;
    private javax.swing.JButton btnBuscarIngredientes;
    private javax.swing.JButton btnGuardarDos;
    private javax.swing.JButton btnIngredientes;
    private javax.swing.JToggleButton btnSalir;
    private javax.swing.JToggleButton btnSalir1;
    private javax.swing.JDialog buscaringredientes;
    private javax.swing.JDialog ingredientes;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tablaIngredientes;
    private javax.swing.JTable tablaIngredientesDos;
    private javax.swing.JTable tablaProducto;
    private javax.swing.JTextField txtBuscarProdu;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCodigoIngre;
    private javax.swing.JTextField txtCodigoUno;
    private javax.swing.JTextField txtDescriIngredientes;
    private javax.swing.JLabel txtExistencia;
    private javax.swing.JTextField txtIdProductoDos;
    private javax.swing.JTextField txtIdReceta;
    private javax.swing.JLabel txtMedida;
    private javax.swing.JTextField txtNombreDos;
    // End of variables declaration//GEN-END:variables
}
