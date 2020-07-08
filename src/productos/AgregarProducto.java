/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package productos;

import Jcombox.categoria;
import Jcombox.medida;
import Jcombox.procedencia;
import Jcombox.proveedor;
import Metodos.abm;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import gourmet.conexionBD;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oscar
 */
public class AgregarProducto extends javax.swing.JDialog {

    conexionBD cn;
    private abm abm;
    private ResultSet rs,rscbo;
    private boolean v_control;
    DefaultTableModel modelo;
    private Object[] filas;
    private static char opcion;
    private boolean vacio;
     
    
    
    public AgregarProducto() {
        
        initComponents();
        
         cn= new conexionBD();        
         
                
        abm= new abm();
        rs=abm.consulta("*", "productos");
        MostrarRegistro();
        
        comboxCategoria();
        jcboxMedida();
        
        // TodaLaPantalla();
       
        
        tablaProducto.setSize(2500, 2800);
        
        verproducto();
        cargarproducto("");
        cargaringredientes("");
        veringredientes();
        visibles(false);
        
         codigo();
         
         btnIngredientes.setVisible(false);
         txtPruebaTipo.setVisible(false);
         txtStock.setVisible(false);
       // cargarproductobuscar();       
       // buscarProducto("");
    }
    public  void TodaLaPantalla(){
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(d);
		this.setResizable(false);
	}
    public void visibles(boolean v){
        cboTipo.setVisible(v);
        comboCategoria.setVisible(v);
        jcboMedida.setVisible(v);
        comboIva.setVisible(v);
    }

       
    
    public void habilitarcampos(boolean h){ //metodo encargado de habilitar o deshabilitar botonoes
              txtPrecioCompra.setEnabled(h);
              txtStock.setEnabled(h);
              txtDescriProduc.setEnabled(h);
              comboCategoria.setEnabled(h);
              comboIva.setEnabled(h);
              //txtIdProducto.setEnabled(h);
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
    public void limpiar(){
            txtPrecioCompra.setText("");
            txtStock.setText("");
            txtDescriProduc.setText("");
        }
    public  boolean validardatos(){
            vacio=false;
            if(txtPrecioCompra.getText().isEmpty()){
                vacio=true;
                 }
                return vacio;
            }
    public  boolean validardatosdos(){
            vacio=false;
            if(txtIdProductoDos.getText().isEmpty()){
                vacio=true;
                 }
                return vacio;
            }
    
    
   
    
    private void comboxCategoria(){
         try{
            rscbo = abm.consultasql("select * from categoria order by idcategoria");
            while(rscbo.next()){
                categoria cat= new categoria();
                cat.setCodigo(Integer.valueOf(rscbo.getString("idcategoria")));
                cat.setDescripcion(rscbo.getString("descripcion"));
                comboCategoria.addItem(cat);
                }
            }
        catch(Exception e){
            e.printStackTrace();
        } 
}
    
    private void jcboxMedida(){
         try{
            rscbo = abm.consultasql("select * from medida order by idmedida");
            while(rscbo.next()){
                medida medida= new medida();
                medida.setCodigo(Integer.valueOf(rscbo.getString("idmedida")));
                medida.setDescripcion(rscbo.getString("descripcion"));
                jcboMedida.addItem(medida);
                }
            }
        catch(Exception e){
            e.printStackTrace();
        } 
}
    
    
   
//      private void comboxProveedor(){
//         try{
//            rscbo = abm.consultasql("select * from proveedor order by idproveedor");
//            while(rscbo.next()){
//                proveedor pro= new proveedor();
//                pro.setCodigo(Integer.valueOf(rscbo.getString("idproveedor")));
//                pro.setNombre(rscbo.getString("nombre"));
//                comboProveedor.addItem(pro);
//                }
//            }
//        catch(Exception e){
//            e.printStackTrace();
//        } 
//}    
    
    public void MostrarRegistro(){//metodo creado poara mostrar datos
    try{
       
        if(rs.getRow()!=0){ //devuelve numero de filas de una objeto de tipo resultset
            txtIdProducto.setText(rs.getString(1));
            txtPrecioCompra.setText(rs.getString(2));
//            txtPrecioVenta.setText(rs.getString(3));
            txtStock.setText(rs.getString(4));
            txtDescriProduc.setText(rs.getString(5));             
//            txtGanancia.setText(rs.getString(6));  
            // combo recetas•••••
//            recetas m=new recetas();
//            m.setCodigo(Integer.valueOf(rs.getString("idreceta")));
//            comboMarca.setSelectedItem(m);
            //combo categoria
            categoria c = new categoria();
            c.setCodigo(Integer.valueOf(rs.getString("idcategoria")));
           // System.out.println("que paso?1-3");
            comboCategoria.setSelectedItem(c);
            //combor Proveedor
//            proveedor pro = new proveedor();
//            pro.setCodigo(Integer.valueOf(rs.getString("idproveedor")));
//            comboProveedor.setSelectedItem(pro);  
        }    
        }catch(Exception e){
            System.out.println("error al mostrar resultados este es "+e.getMessage());
            }
  }
    
void verproducto(){
        try{
            Statement consultamarca = (Statement) conexionBD.ConectarBD().createStatement();
            ResultSet rs = consultamarca.executeQuery("select * from productos where tipo='producto'");
            
            modelo = new DefaultTableModel();
            tablaProducto.setModel(modelo);
            
            
            modelo.addColumn("Codigo");
            modelo.addColumn("Precio Compra");
//            modelo.addColumn("Ganancia");
//            modelo.addColumn("Precio Venta");
            modelo.addColumn("Descripcion");
           modelo.addColumn("Stock");
           modelo.addColumn("Tipo");
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
    
     public void cargarproducto(String valor ){
        String [] titulos  = {"Codigo","Precio Compra","Descripcion","Stock","Tipo"};
        String [] registros  = new String [5];
        String sql=null;
         sql = "select idproducto,preciodecompra,stock,descripcion,tipo";
         sql+=" from productos ";
         sql+=" where tipo='producto' order by idproducto";
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
                registros[1] = rs.getString("preciodecompra");
//                registros[2] = rs.getString("preciodeventa");
//                registros[3] = rs.getString("gananciaproducto");
                registros[2] = rs.getString("descripcion");
                registros[3] = rs.getString("stock");
                registros[4] = rs.getString("tipo");
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
     public void cargarproductobuscar(){
        String [] titulos  = {"Codigo","Precio Compra","Descripcion","Stock"};
        String [] registros  = new String [4];
        String sql=null;
         sql = "select idproducto,preciodecompra,stock,descripcion";
         sql+=" from productos ";
         sql+=" where p.descripcion LIKE '%"+txtBuscar.getText()+"%'";
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
                registros[1] = rs.getString("preciodecompra");
//                registros[2] = rs.getString("preciodeventa");
//                registros[3] = rs.getString("gananciaproducto");
                registros[2] = rs.getString("descripcion");
                registros[3] = rs.getString("stock");
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
      public void seleccionartabla(){
        
        
      // DefaultTableModel tabla = (DefaultTableModel) this.tablaProducto.getModel();
       
       
       int c= tablaProducto.getSelectedRow();
       
       if(c==-1){
           System.out.println("Seleccione un registro");
            }
       else{
           String id = (String) tablaProducto.getValueAt(c, 0);
           String prcompra = (String) tablaProducto.getValueAt(c, 1);
//           String preventa = (String) tablaProducto.getValueAt(c, 2);
//           String ganancia = (String) tablaProducto.getValueAt(c, 3);
           String descripcion = (String) tablaProducto.getValueAt(c, 2);
           String stock = (String) tablaProducto.getValueAt(c, 3);
           String tipo = (String) tablaProducto.getValueAt(c, 4);
//           String recetas = (String) tablaProducto.getValueAt(c, 6);
       //    String proveedor = (String) tablaProducto.getValueAt(c, 7);
//           String categoria = (String) tablaProducto.getValueAt(c, 7);
//           String iva = (String) tablaProducto.getValueAt(c, 9);
           
     
           this.txtIdProducto.setText(id);
           this.txtPrecioCompra.setText(prcompra);
//           this.txtPrecioVenta.setText(preventa);
//           this.txtGanancia.setText(ganancia);           
           this.txtStock.setText(stock);
           this.txtDescriProduc.setText(descripcion); 
           this.txtIdProductoDos.setText(id);
           this.txtNombreDos.setText(descripcion); 
           this.txtPruebaTipo.setText(tipo);
           
           if(txtPruebaTipo.getText().equals("producto")){
               btnIngredientes.setEnabled(true);
           }else{
               btnIngredientes.setEnabled(false);
           }
           
        
           
//           for(int i=0; i<this.cboTipo.getItemCount();i++){
//               if(((medida)(this.cboTipo.getItemAt(i))).getDescripcion().equals(tipo)){
//                   this.cboTipo.setSelectedIndex(i);
//                   this.cboTipo.repaint();
//                   break;
//               }
//           }
           
//           for(int i=0; i<this.comboProveedor.getItemCount();i++){
//               if(((proveedor)(this.comboProveedor.getItemAt(i))).getNombre().equals(proveedor)){
//                   this.comboProveedor.setSelectedIndex(i);
//                   this.comboProveedor.repaint();
//                   break;
//                   }
//                }  
           
           
           
//           for(int i=0; i<this.comboCategoria.getItemCount();i++){
//               if(((categoria)(this.comboCategoria.getItemAt(i))).getDescripcion().equals(categoria)){
//                   this.comboCategoria.setSelectedIndex(i);
//                   this.comboCategoria.repaint();
//                   break;
//                   }
//                }           
           
    }
}
      
//       public void seleccionartablaproducto(){
//        
//        
//       //DefaultTableModel tabla = (DefaultTableModel) this.tablacliente.getModel();
//       
//       
//       int c= tablaProducto.getSelectedRow();
//       
//       if(c==-1){
//           JOptionPane.showMessageDialog(null, "Seleccione un registro");
//            }
//       else{
//          // String id = (String) tablaProducto.getValueAt(c, 0);
//           Integer id = Integer.parseInt(tablaProducto.getValueAt(c, 0).toString());
//           Integer precioventa = Integer.parseInt(tablaProducto.getValueAt(c, 1).toString());
//           //String precioventa = (String) tablaProducto.getValueAt(c, 1);
//           String descripcion = (String) tablaProducto.getValueAt(c, 2);
//          // String marca = (String) tablaProducto.getValueAt(c, 3);
//           Integer iva = Integer.parseInt(tablaProducto.getValueAt(c, 3).toString());
//           Integer stock = Integer.parseInt(tablaProducto.getValueAt(c, 4).toString());
//           txtStock.setText(Integer.toString(stock));
//           txtCodigo.setText(Integer.toString(id));
//          // this.txtCodigo.setText(id);
//           //this.txtPrecioVenta.setText(precioventa);
//           txtPrecioVenta.setText(Integer.toString(precioventa));
//           this.txtDescripcion.setText(descripcion);
//         //  this.txtTotal1.setText(marca);
//           txtIVA.setText(Integer.toString(iva));
//           }       
//    }
       
//        public void cargaringredientes(String valor){
//        String [] titulos  = {"Codigo","Descripcion","Existencia"};
//        String [] registros  = new String [3];
//        String sql=null;
//         sql = "select * from productos where tipo='insumo' order by idproducto";
//                
//         
//        DefaultTableModel m = new DefaultTableModel(null, titulos);
//    
//        conexionBD cn = new conexionBD();
//        Connection cnn = (Connection) cn.ConectarBD();
//        Statement  st;
//        try {
//            
//            st = (Statement) cnn.createStatement();
//            ResultSet rs = st.executeQuery(sql);
//            
//            while(rs.next()){
//                registros[0] = rs.getString("idproducto");
//                registros[1] = rs.getString("descripcion");
//                registros[2] = rs.getString("stock");
//                m.addRow(registros);
//                }
//            tablaIngredientesDos.setModel(m);
//            
//            }catch (SQLException ex) {
//                            JOptionPane.showMessageDialog(null, ex);
//                            }   
//    }
      
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
         public void limpiardos(){
        txtCodigoIngre.setText("");
        txtDescriIngredientes.setText("");
        txtCantidad.setText("");
        txtCantidad.setText("");
        txtMedida.setText("");
    }
         public void codigo(){
        try {
            rs = abm.nuevo("idreceta", "recetas");
            rs.first();
            txtIdReceta.setText(String.valueOf(rs.getInt("codigo")+1));
            txtIdReceta.requestFocus();//mantiene el enfoque en un objeto
        } catch (SQLException ex) {
            Logger.getLogger(AgregarProducto.class.getName()).log(Level.SEVERE, null, ex);
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
        buscaringredientes = new javax.swing.JDialog();
        txtBuscarProdu = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        btnAceotarProdu = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaIngredientesDos = new javax.swing.JTable();
        jSplitPane1 = new javax.swing.JSplitPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtDescriProduc = new javax.swing.JTextField();
        txtPrecioCompra = new javax.swing.JTextField();
        txtStock = new javax.swing.JTextField();
        txtIdProducto = new javax.swing.JTextField();
        comboCategoria = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaProducto = new javax.swing.JTable();
        btnSalir = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        txtBuscar = new javax.swing.JTextField();
        btnBuscar = new javax.swing.JButton();
        comboIva = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jcboMedida = new javax.swing.JComboBox();
        cboTipo = new javax.swing.JComboBox();
        btnIngredientes = new javax.swing.JButton();
        txtPruebaTipo = new javax.swing.JTextField();

        ingredientes.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaIngredientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Ingrediente", "Cantidad", "Unidad de Medida"
            }
        ));
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
                .addContainerGap(39, Short.MAX_VALUE))
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
                .addContainerGap(21, Short.MAX_VALUE))
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jSplitPane1.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jLabel3.setFont(new java.awt.Font("Bell MT", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 204, 0));
        jLabel3.setText("Produtos");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(286, 286, 286)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(429, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3))
        );

        jSplitPane1.setTopComponent(jPanel1);

        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel9.setText("Codigo:");
        jPanel2.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 260, -1, -1));

        jLabel10.setText("Precio:");
        jPanel2.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, -1, -1));

        jLabel12.setText("Cantidad:");
        jPanel2.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 280, -1, -1));

        jLabel13.setText("Descripcion:");
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 310, -1, -1));

        txtDescriProduc.setEnabled(false);
        jPanel2.add(txtDescriProduc, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 320, 150, 20));

        txtPrecioCompra.setEnabled(false);
        txtPrecioCompra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtPrecioCompraKeyTyped(evt);
            }
        });
        jPanel2.add(txtPrecioCompra, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 350, 110, -1));

        txtStock.setText("1");
        txtStock.setEnabled(false);
        txtStock.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtStockKeyTyped(evt);
            }
        });
        jPanel2.add(txtStock, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 270, 100, -1));

        txtIdProducto.setEnabled(false);
        jPanel2.add(txtIdProducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 250, 100, -1));

        comboCategoria.setEnabled(false);
        comboCategoria.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboCategoriaActionPerformed(evt);
            }
        });
        jPanel2.add(comboCategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 400, 130, -1));

        tablaProducto.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Producto", "Precio Compra", "Descripcion", "Stock", "Tipo"
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

        jPanel2.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 960, 200));

        btnSalir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/equis.png"))); // NOI18N
        btnSalir.setText("Salir");
        btnSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalirActionPerformed(evt);
            }
        });
        jPanel2.add(btnSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 400, 100, -1));

        btnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/add.png"))); // NOI18N
        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });
        jPanel2.add(btnAgregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 280, -1, -1));

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/articulos.png"))); // NOI18N
        btnGuardar.setText("Guardar");
        btnGuardar.setEnabled(false);
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });
        jPanel2.add(btnGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 340, -1, -1));

        btnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/actualizar.png"))); // NOI18N
        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        jPanel2.add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 400, 110, -1));

        btnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/basureronegro.png"))); // NOI18N
        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });
        jPanel2.add(btnEliminar, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 280, -1, -1));

        btnCancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/cancel.png"))); // NOI18N
        btnCancelar.setText("Cancelar");
        btnCancelar.setEnabled(false);
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        jPanel2.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 340, -1, -1));

        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtBuscarKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        jPanel2.add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 10, 200, 30));

        btnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buscar.png"))); // NOI18N
        btnBuscar.setText("Buscar");
        btnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarActionPerformed(evt);
            }
        });
        jPanel2.add(btnBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 10, 110, 30));

        comboIva.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Agregar", "10", "5", "0" }));
        comboIva.setEnabled(false);
        jPanel2.add(comboIva, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 410, 90, -1));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 204, 0));
        jLabel5.setText("Introduzca Descripcion del Producto");
        jPanel2.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 210, -1));

        jPanel2.add(jcboMedida, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 440, 130, -1));

        cboTipo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "producto" }));
        jPanel2.add(cboTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 280, 130, -1));

        btnIngredientes.setText("Insertar ingredientes");
        btnIngredientes.setEnabled(false);
        btnIngredientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngredientesActionPerformed(evt);
            }
        });
        jPanel2.add(btnIngredientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 330, 140, 20));
        jPanel2.add(txtPruebaTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 380, 150, 30));

        jSplitPane1.setRightComponent(jPanel2);

        getContentPane().add(jSplitPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 970, 530));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        try {
                opcion='n';
                limpiar();
                habilitarbotones(false);
                habilitarcampos(true);
                habilitarbotones2(true);
                rs = abm.nuevo("idproducto", "productos");
                rs.first();
                txtIdProducto.setText(String.valueOf(rs.getInt("codigo")+1));
                txtPrecioCompra.requestFocus();//mantiene el enfoque en un objeto
                rs.close();
                }catch(Exception e){
                JOptionPane.showMessageDialog(null,"error al generar el codigo "+e);          
                }
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
       vacio=validardatos();
        if(vacio==true){
            JOptionPane.showMessageDialog(null,"Completar los datos , no pueden quedar en blanco");
        }else{
            switch(opcion){
                case 'n':
                        conexionBD cn = new conexionBD();// se crea la conexion 
                        Connection cnn = (Connection) cn.ConectarBD();
                        Statement  st; 
                        try{
                            String consulta="";
                            consulta=("Select * from productos where descripcion='"+txtDescriProduc.getText()+"'"); //se traen todos los registros
                            st = (Statement) cnn.createStatement();
                            ResultSet rs = st.executeQuery(consulta);
                            rs.first();
                            if (rs.getRow() != 0){ // si es distinto a 0 ya existe y no agrega
                                            JOptionPane.showMessageDialog(null,"Este producto ya existe");                                           
                                            txtPrecioCompra.setText("");
                                            txtPrecioCompra.requestFocus();
                                            }
                            else{
//                                    recetas mar =  (recetas) comboMarca.getSelectedItem();
                                    categoria cat =  (categoria) comboCategoria.getSelectedItem();
                                    medida mad = (medida) jcboMedida.getSelectedItem();
//                                    proveedor prove =  (proveedor) comboProveedor.getSelectedItem();
                                    String iva="";
                                    String tipo="producto";
                                    int c=0;
                                    
                                   // iva = comboIva.getSelectedItem().toString();
                                    //tipo = cboTipo.getSelectedItem().toString();
                                    v_control=abm.insertar("productos",txtIdProducto.getText()+","+txtPrecioCompra.getText()+","+0+","+txtStock.getText()+",'"+txtDescriProduc.getText()+"',"+0+","+cat.getCodigo()+","+1+",'"+tipo+"',"+1+","+mad.getCodigo());
                                    if (v_control==true){
                                                JOptionPane.showMessageDialog(null,"Se ha guardado los datos");              
                                                }
                                }
                        }catch(Exception e){
                                    System.out.println("Error al mostrar datos en la tabla"+e.getMessage());  
                                    }           
                break;
                case 'm':
//                        recetas mar =  (recetas) comboMarca.getSelectedItem();
                        categoria cat =  (categoria) comboCategoria.getSelectedItem();
//                        proveedor prove =  (proveedor) comboProveedor.getSelectedItem();
                        String iva="";
                        iva = comboIva.getSelectedItem().toString();
                        v_control= abm.modificar("productos", " preciodecompra="+txtPrecioCompra.getText()+", "+" preciodeventa="+0+", "
                                +" stock="+txtStock.getText()+", "+" descripcion='"+txtDescriProduc.getText()+"', "+" gananciaproducto="+0
                                +", idcategoria="+cat.getCodigo()
                                , "idproducto="+txtIdProducto.getText());
                        if(v_control==true){
                          JOptionPane.showMessageDialog(null,"Datos actualizados congratuleishon");
                            }
                        break;
               }
        habilitarcampos(false);
        habilitarbotones(true);
        habilitarbotones2(false);
        rs=abm.consulta("*", "productos");        
        MostrarRegistro();
        verproducto();
        cargarproducto("");
        //cargarproducto("");        
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void comboCategoriaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboCategoriaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboCategoriaActionPerformed

    private void btnSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalirActionPerformed
    this.dispose();
    }//GEN-LAST:event_btnSalirActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        opcion='m';
        habilitarcampos(true);
        habilitarbotones(false);
        habilitarbotones2(true);
        txtPrecioCompra.requestFocus();
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        habilitarbotones2(false);
        habilitarbotones(true);
        habilitarcampos(false);
        rs=abm.consulta("*", "productos");
        MostrarRegistro();
        verproducto();
        cargarproducto("");
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void tablaProductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaProductoMouseClicked
       seleccionartabla();
       
    }//GEN-LAST:event_tablaProductoMouseClicked

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        v_control = abm.eliminar("productos", "idproducto="+txtIdProducto.getText());
        if(v_control==true){
            rs= abm.consulta("*", "productos");
            MostrarRegistro();
            JOptionPane.showMessageDialog(null,"Datos Eliminados congratuleishon");
            }
        verproducto();
        cargarproducto("");
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void tablaProductoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaProductoKeyPressed
      
    }//GEN-LAST:event_tablaProductoKeyPressed

    private void txtBuscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyPressed
        
    }//GEN-LAST:event_txtBuscarKeyPressed

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void btnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarActionPerformed

    }//GEN-LAST:event_btnBuscarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        // TODO add your handling code here:
        
       if(txtBuscar.getText().isEmpty()){
        cargarproducto("");
            }
       else{cargarproductobuscar();
            }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void tablaProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaProductoKeyReleased
         seleccionartabla();
    }//GEN-LAST:event_tablaProductoKeyReleased

    private void tablaProductoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaProductoKeyTyped
       
    }//GEN-LAST:event_tablaProductoKeyTyped

private void txtPrecioCompraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPrecioCompraKeyTyped
        char caracter=evt.getKeyChar();
        if((caracter<'0'||(caracter>'9'))&&(caracter!='\b')){
            evt.consume();
        }
}//GEN-LAST:event_txtPrecioCompraKeyTyped

private void txtStockKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStockKeyTyped
char caracter=evt.getKeyChar();
        if((caracter<'0'||(caracter>'9'))&&(caracter!='\b')){
            evt.consume();
        }
}//GEN-LAST:event_txtStockKeyTyped

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

    private void btnAgregarDosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarDosActionPerformed
        // TODO add your handling code here:
        agregartablafactura();
        limpiardos();
        btnGuardarDos.setEnabled(true);

    }//GEN-LAST:event_btnAgregarDosActionPerformed

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

    private void btnBuscarIngredientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarIngredientesActionPerformed
        // TODO add your handling code here:
        buscaringredientes.setModal(true);
        buscaringredientes.setSize(722, 504);
        buscaringredientes.setLocationRelativeTo(this);
        buscaringredientes.setVisible(true);
        
        
    }//GEN-LAST:event_btnBuscarIngredientesActionPerformed

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
                        v_control=abm.insertar("recetas",txtIdReceta.getText()+",'"+hola+"',"+tablaIngredientes.getValueAt(i, 2)+","+1+","+txtIdProductoDos.getText()+","+tablaIngredientes.getValueAt(i, 0));
                        
                    }
                    if (v_control==true){
                            JOptionPane.showMessageDialog(null,"Se ha guardado los datos");
                        }

                }catch(Exception e){
                    System.out.println("Error al mostrar datos en la tabla"+e.getMessage());
                }
        }
    }//GEN-LAST:event_btnGuardarDosActionPerformed

    private void btnIngredientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngredientesActionPerformed
        // TODO add your handling code here:

        ingredientes.setModal(true);
        ingredientes.setSize(722, 504);
        ingredientes.setLocationRelativeTo(this);
        ingredientes.setVisible(true);

    }//GEN-LAST:event_btnIngredientesActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AgregarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgregarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgregarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgregarProducto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /*
         * Create and display the dialog
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new AgregarProducto().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceotarProdu;
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnAgregarDos;
    private javax.swing.JButton btnBuscar;
    private javax.swing.JButton btnBuscarIngredientes;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardarDos;
    private javax.swing.JButton btnIngredientes;
    private javax.swing.JButton btnSalir;
    private javax.swing.JDialog buscaringredientes;
    private javax.swing.JComboBox cboTipo;
    private javax.swing.JComboBox comboCategoria;
    private javax.swing.JComboBox comboIva;
    private javax.swing.JDialog ingredientes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JComboBox jcboMedida;
    private javax.swing.JTable tablaIngredientes;
    private javax.swing.JTable tablaIngredientesDos;
    private javax.swing.JTable tablaProducto;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JTextField txtBuscarProdu;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCodigoIngre;
    private javax.swing.JTextField txtDescriIngredientes;
    private javax.swing.JTextField txtDescriProduc;
    private javax.swing.JLabel txtExistencia;
    private javax.swing.JTextField txtIdProducto;
    private javax.swing.JTextField txtIdProductoDos;
    private javax.swing.JTextField txtIdReceta;
    private javax.swing.JLabel txtMedida;
    private javax.swing.JTextField txtNombreDos;
    private javax.swing.JTextField txtPrecioCompra;
    private javax.swing.JTextField txtPruebaTipo;
    private javax.swing.JTextField txtStock;
    // End of variables declaration//GEN-END:variables
}
