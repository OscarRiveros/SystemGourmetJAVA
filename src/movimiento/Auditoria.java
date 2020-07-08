/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movimiento;

import Jcombox.evento;
import Metodos.abm;
import com.mysql.jdbc.Connection;
import gourmet.conexionBD;
import java.awt.print.PrinterException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import reportes.reporte;

/**
 *
 * @author USER
 */
public class Auditoria extends javax.swing.JDialog {

    /**
     * Creates new form Auditoria
     */
    DefaultTableModel modelo;
    private abm abm;
    conexionBD cn;
    Calendar Fecha;
    Calendar Fechia;
    Calendar Fechap;
    private reporte jasper;
    private conexionBD com;
    private ResultSet rscbo;
    
    public Auditoria(java.awt.Frame parent, boolean modal) {
        super(parent, false);
        initComponents();
        
         cn = new conexionBD();
         jasper=new reporte();
         
         jButton4.setVisible(false);

         abm = new abm();
         cargarempleados("");
         setLocationRelativeTo(null);
         txtIdEmpleado.setVisible(false);
         //comboxevento();
    }
    
    private void comboxevento(){
         try{
            rscbo = abm.consultasql("select evento from auditoria order by evento");
            while(rscbo.next()){
                evento event= new evento();
               // event.setIdevento(Integer.valueOf(rscbo.getString("idevento")));
                event.setEvento(rscbo.getString("evento"));
                jcomboevento.addItem(event);
                }
            }
        catch(Exception e){
            e.printStackTrace();
        } 
}
    
    public void cargarempleados(String valor) {
        String[] titulos = {"Codigo", "Nombre", "Apellido", "Cargo", "CIN°"};
        String[] registros = new String[5];
        String sql = "select idempleado, nombre, apellido, cargo, cinro from empleado where estado=1";

        // sql+=" where idproducto="+txtIdProducto.getText();

        modelo = new DefaultTableModel(null, titulos);

        conexionBD cn = new conexionBD();
        Connection cnn = (Connection) cn.ConectarBD();
        Statement st;
        try {

            st = (com.mysql.jdbc.Statement) cnn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                registros[0] = rs.getString("idempleado");
                registros[1] = rs.getString("nombre");
                registros[2] = rs.getString("apellido");
                registros[3] = rs.getString("cargo");                
                registros[4] = rs.getString("cinro");
             

                modelo.addRow(registros);
            }
            tablaEmpleado.setModel(modelo);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }
     void seleccionartabla() {
        //creamos una variable que va a contener la fila seleccionada de la tabla
        int i = tablaEmpleado.getSelectedRow();
        //hacemos una condicion de que si la varialbe i es igual a - es que no se ha seleccionado ninguna fila
        if (i == -1) {

            JOptionPane.showMessageDialog(null, "Favor seleccione una fila");

        } else { //de lo contrario si se selecciono la fila     
//creamos nuestras varialbes que va a contener los datos de la fila seleccionado en este caso
//nom va a tener el dato que se encuentra en la fila (i),recuerda que esa variable es la fila 
//seleccionada,columna 0,que tiene el campo nombre me imagino,y hacemos lo mismo con los otros.getValueAt
            //Obetemos el valor que se encuentra en la tabla indicando la fila y columna.
//haciendo un Casting a String ese valor.
            String id = (String) tablaEmpleado.getValueAt(i, 0);
            String nombre = (String) tablaEmpleado.getValueAt(i, 1);
           
            
            
            txtIdEmpleado.setText((id));
             this.txtempleado.setText(nombre);
            
        }

    }
     
     public void cargarauditoriaempleadobuscar(String valor ){
        String [] titulos  = { "Nombre Usuario", "Comando Eejecutado", "Evento Realizado", "Fecha y Hora", "tabla"};
        String [] registros  = new String [5];
        String sql = "select e.usuario as usuario,comandoejecutado,evento,concat(fecha,' | ',hora) as FechaHora,tabla,tabla from auditoria as a "
                + "inner join empleado as e on (a.idusuario=e.idempleado)"
                + " where idusuario='"+txtIdEmpleado.getText()+"'";  
         
        
         
        modelo = new DefaultTableModel(null, titulos);
    
        conexionBD cnp = new conexionBD();
        Connection cnn = (Connection) cnp.ConectarBD();
        com.mysql.jdbc.Statement  st;
        try {
            
            st = (com.mysql.jdbc.Statement) cnn.createStatement();
            ResultSet rst = st.executeQuery(sql);
            
            while(rst.next()){
                registros[0] = rst.getString("usuario");
                registros[1] = rst.getString("comandoejecutado");
                registros[2] = rst.getString("evento");
                registros[3] = rst.getString("FechaHora");
                registros[4] = rst.getString("tabla");
                
                
                modelo.addRow(registros);
                }
            tablaauditoria.setModel(modelo);
            tablaauditoria.removeColumn(tablaauditoria.getColumnModel().getColumn(1));
            } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex);
                            }   
    }
     
     
     public void cargarauditoriaparametroevento(String valor ){
          String evento= (String) jcomboevento.getSelectedItem();
        String [] titulos  = { "Nombre Usuario", "Comando Eejecutado", "Evento Realizado", "Fecha y Hora", "tabla"};
        String [] registros  = new String [5];
        String sql = "select e.usuario as usuario,comandoejecutado,evento,concat(fecha,' | ',hora) as FechaHora,tabla from auditoria as a\n" +
                     "inner join empleado as e on (a.idusuario=e.idempleado) where idusuario='"+txtIdEmpleado.getText()+"' and evento='"+evento+"'";  
         
        
         
        modelo = new DefaultTableModel(null, titulos);
    
        conexionBD cnp = new conexionBD();
        Connection cnn = (Connection) cnp.ConectarBD();
        com.mysql.jdbc.Statement  st;
        try {
            
            st = (com.mysql.jdbc.Statement) cnn.createStatement();
            ResultSet rst = st.executeQuery(sql);
            
            while(rst.next()){
                registros[0] = rst.getString("usuario");
                registros[1] = rst.getString("comandoejecutado");
                registros[2] = rst.getString("evento");
                registros[3] = rst.getString("FechaHora");
                registros[4] = rst.getString("tabla");
                
                
                modelo.addRow(registros);
                }
            tablaauditoria.setModel(modelo);
            tablaauditoria.removeColumn(tablaauditoria.getColumnModel().getColumn(1));
            } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex);
                            }   
    }
     
      public void cargarauditoriaparametrotres(String valor ){
          String evento= (String) jcomboevento.getSelectedItem();
        String [] titulos  = { "Nombre Usuario", "Comando Eejecutado", "Evento Realizado", "Fecha y Hora", "tabla"};
        String [] registros  = new String [5];
        String sql = "select e.usuario as usuario,comandoejecutado,evento,concat(fecha,' | ',hora) as FechaHora,tabla from auditoria as a\n" +
                     "inner join empleado as e on (a.idusuario=e.idempleado) where idusuario='"+txtIdEmpleado.getText()+"' and evento='"+evento+"' and tabla='"+txttabla.getText()+"'";
         
        
         
        modelo = new DefaultTableModel(null, titulos);
    
        conexionBD cnp = new conexionBD();
        Connection cnn = (Connection) cnp.ConectarBD();
        com.mysql.jdbc.Statement  st;
        try {
            
            st = (com.mysql.jdbc.Statement) cnn.createStatement();
            ResultSet rst = st.executeQuery(sql);
            
            while(rst.next()){
                registros[0] = rst.getString("usuario");
                registros[1] = rst.getString("comandoejecutado");
                registros[2] = rst.getString("evento");
                registros[3] = rst.getString("FechaHora");
                registros[4] = rst.getString("tabla");
                
                
                modelo.addRow(registros);
                }
            tablaauditoria.setModel(modelo);
            tablaauditoria.removeColumn(tablaauditoria.getColumnModel().getColumn(1));
            } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex);
                            }   
    }
     
      public void cargarauditoriageneral(String valor ){
        String [] titulos  = { "Nombre Usuario", "Comando Eejecutado", "Evento Realizado", "Fecha y Hora", "tabla"};
        String [] registros  = new String [5];
        String sql = "select e.usuario as usuario,comandoejecutado,evento,concat(fecha,' | ',hora) as FechaHora,tabla from auditoria as a\n" +
                    "inner join empleado as e on (a.idusuario=e.idempleado)";  
         
        
         
        modelo = new DefaultTableModel(null, titulos);
    
        conexionBD cnp = new conexionBD();
        Connection cnn = (Connection) cnp.ConectarBD();
        com.mysql.jdbc.Statement  st;
        try {
            
            st = (com.mysql.jdbc.Statement) cnn.createStatement();
            ResultSet rst = st.executeQuery(sql);
            
            while(rst.next()){
                registros[0] = rst.getString("usuario");
                registros[1] = rst.getString("comandoejecutado");
                registros[2] = rst.getString("evento");
                registros[3] = rst.getString("FechaHora");
                registros[4] = rst.getString("tabla");
                
                
                modelo.addRow(registros);
                }
            tablaauditoria.setModel(modelo);
            tablaauditoria.removeColumn(tablaauditoria.getColumnModel().getColumn(1));
            } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex);
                            }   
    }
      public void cargarauditoriafechabuscar(String valor ){
          
        Fecha = txtfecha.getCalendar();
        Fecha.setTime(txtfecha.getDate());
        String dia = String.valueOf(Fecha.get(Calendar.DATE));
        String mes = String.valueOf(Fecha.get(Calendar.MONTH) + 1);
        String anio = String.valueOf(Fecha.get(Calendar.YEAR));
        String date1 = anio + "-" + mes + "-" + dia;
        
        Fechap = txtfecha2.getCalendar();
        Fechap.setTime(txtfecha2.getDate());
        String diaa = String.valueOf(Fechap.get(Calendar.DATE));
        String mess = String.valueOf(Fechap.get(Calendar.MONTH) + 1);
        String anioo = String.valueOf(Fechap.get(Calendar.YEAR));
        String date2 = anioo + "-" + mess + "-" + diaa;
        
        String [] titulos  = { "Nombre Usuario", "Comando Eejecutado", "Evento Realizado", "Fecha y Hora", "tabla"};
        String [] registros  = new String [5];
        String sql = "select e.usuario as usuario,comandoejecutado,evento,concat(fecha,' | ',hora) as FechaHora,tabla,tabla from auditoria as a "
                + "inner join empleado as e on (a.idusuario=e.idempleado)"
                + " where fecha between '"+date1+"' and '"+date2+"'";  
         
        System.out.println(date1);
        System.out.println(date2);
         
        modelo = new DefaultTableModel(null, titulos);
    
        conexionBD cnp = new conexionBD();
        Connection cnn = (Connection) cnp.ConectarBD();
        com.mysql.jdbc.Statement  st;
        try {
            
            st = (com.mysql.jdbc.Statement) cnn.createStatement();
            ResultSet rst = st.executeQuery(sql);
            
            while(rst.next()){
                registros[0] = rst.getString("usuario");
                registros[1] = rst.getString("comandoejecutado");
                registros[2] = rst.getString("evento");
                registros[3] = rst.getString("FechaHora");
                registros[4] = rst.getString("tabla");
                
                
                modelo.addRow(registros);
                }
            tablaauditoria.setModel(modelo);
            tablaauditoria.removeColumn(tablaauditoria.getColumnModel().getColumn(1));
            
            } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex);
                            }   
    }

      
      
       public void cargarauditoriaFechaEmpleadobuscar(String valor ){
          
        Fecha = txtfecha.getCalendar();
        Fecha.setTime(txtfecha.getDate());
        String dia = String.valueOf(Fecha.get(Calendar.DATE));
        String mes = String.valueOf(Fecha.get(Calendar.MONTH) + 1);
        String anio = String.valueOf(Fecha.get(Calendar.YEAR));
        String date1 = anio + "-" + mes + "-" + dia;
        
        Fechap = txtfecha2.getCalendar();
        Fechap.setTime(txtfecha2.getDate());
        String diaa = String.valueOf(Fechap.get(Calendar.DATE));
        String mess = String.valueOf(Fechap.get(Calendar.MONTH) + 1);
        String anioo = String.valueOf(Fechap.get(Calendar.YEAR));
        String date2 = anioo + "-" + mess + "-" + diaa;
        
        String [] titulos  = { "Nombre Usuario", "Comando Eejecutado", "Evento Realizado", "Fecha y Hora", "tabla"};
        String [] registros  = new String [5];
        String sql = "select e.usuario as usuario,comandoejecutado,evento,concat(fecha,' | ',hora) as FechaHora,tabla,tabla from auditoria as a "
                + "inner join empleado as e on (a.idusuario=e.idempleado)"
                + " where fecha between '"+date1+"' and '"+date2+"' and  idusuario='"+txtIdEmpleado.getText()+"'";  
         
        System.out.println(date1);
        System.out.println(date2);
         
        modelo = new DefaultTableModel(null, titulos);
    
        conexionBD cnp = new conexionBD();
        Connection cnn = (Connection) cnp.ConectarBD();
        com.mysql.jdbc.Statement  st;
        try {
            
            st = (com.mysql.jdbc.Statement) cnn.createStatement();
            ResultSet rst = st.executeQuery(sql);
            
            while(rst.next()){
                registros[0] = rst.getString("usuario");
                registros[1] = rst.getString("comandoejecutado");
                registros[2] = rst.getString("evento");
                registros[3] = rst.getString("FechaHora");
                registros[4] = rst.getString("tabla");
                
                
                modelo.addRow(registros);
                }
            tablaauditoria.setModel(modelo);
            tablaauditoria.removeColumn(tablaauditoria.getColumnModel().getColumn(1));
            
            } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex);
                            }   
    }
      
      
      
       public void cargarauditoriaFechaBuscareventoUsuario(String valor ){
          
        Fecha = txtfecha.getCalendar();
        Fecha.setTime(txtfecha.getDate());
        String dia = String.valueOf(Fecha.get(Calendar.DATE));
        String mes = String.valueOf(Fecha.get(Calendar.MONTH) + 1);
        String anio = String.valueOf(Fecha.get(Calendar.YEAR));
        String date1 = anio + "-" + mes + "-" + dia;
        
        Fechap = txtfecha2.getCalendar();
        Fechap.setTime(txtfecha2.getDate());
        String diaa = String.valueOf(Fechap.get(Calendar.DATE));
        String mess = String.valueOf(Fechap.get(Calendar.MONTH) + 1);
        String anioo = String.valueOf(Fechap.get(Calendar.YEAR));
        String date2 = anioo + "-" + mess + "-" + diaa;
        
         String evento= (String) jcomboevento.getSelectedItem();
        String [] titulos  = { "Nombre Usuario", "Comando Eejecutado", "Evento Realizado", "Fecha y Hora", "tabla"};
        String [] registros  = new String [5];
        String sql = "select e.usuario as usuario,comandoejecutado,evento,concat(fecha,' | ',hora) as FechaHora,tabla,tabla from auditoria as a "
                + "inner join empleado as e on (a.idusuario=e.idempleado)"
                + " where fecha between '"+date1+"' and '"+date2+"'"+" and idusuario='"+txtIdEmpleado.getText()+"' and evento='"+evento+"'";  
             //   and tabla='"+txttabla.getText()+"'";
         
        System.out.println(date1);
        System.out.println(date2);
         
        modelo = new DefaultTableModel(null, titulos);
    
        conexionBD cnp = new conexionBD();
        Connection cnn = (Connection) cnp.ConectarBD();
        com.mysql.jdbc.Statement  st;
        try {
            
            st = (com.mysql.jdbc.Statement) cnn.createStatement();
            ResultSet rst = st.executeQuery(sql);
            
            while(rst.next()){
                registros[0] = rst.getString("usuario");
                registros[1] = rst.getString("comandoejecutado");
                registros[2] = rst.getString("evento");
                registros[3] = rst.getString("FechaHora");
                registros[4] = rst.getString("tabla");
                
                
                modelo.addRow(registros);
                }
            tablaauditoria.setModel(modelo);
            tablaauditoria.removeColumn(tablaauditoria.getColumnModel().getColumn(1));
            
            } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex);
                            }   
    }
       public void cargarauditoriaeventobuscar(String valor ){
           
        String evento= (String) jcomboevento.getSelectedItem();
        String [] titulos  = { "Nombre Usuario", "Comando Eejecutado", "Evento Realizado", "Fecha y Hora", "tabla"};
        String [] registros  = new String [5];
        String sql = "select e.usuario as usuario,comandoejecutado,evento,concat(fecha,' | ',hora) as FechaHora,tabla,tabla from auditoria as a "
                + "inner join empleado as e on (a.idusuario=e.idempleado)"
                + " where evento='"+evento+"'";  
         
        
         
        modelo = new DefaultTableModel(null, titulos);
    
        conexionBD cnp = new conexionBD();
        Connection cnn = (Connection) cnp.ConectarBD();
        com.mysql.jdbc.Statement  st;
        try {
            
            st = (com.mysql.jdbc.Statement) cnn.createStatement();
            ResultSet rst = st.executeQuery(sql);
            
            while(rst.next()){
                registros[0] = rst.getString("usuario");
                registros[1] = rst.getString("comandoejecutado");
                registros[2] = rst.getString("evento");
                registros[3] = rst.getString("FechaHora");
                registros[4] = rst.getString("tabla");
                
                
                modelo.addRow(registros);
                }
            tablaauditoria.setModel(modelo);
            tablaauditoria.removeColumn(tablaauditoria.getColumnModel().getColumn(1));
            
            } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, ex);
                            }   
    }
       public void cargarauditoriatablabuscar(String valor ){
        String [] titulos  = { "Nombre Usuario", "Comando Eejecutado", "Evento Realizado", "Fecha y Hora", "tabla"};
        String [] registros  = new String [5];
        String sql = "select e.usuario as usuario,comandoejecutado,evento,concat(fecha,' | ',hora) as FechaHora,tabla,tabla from auditoria as a "
                + "inner join empleado as e on (a.idusuario=e.idempleado)"
                + " where  tabla='"+txttabla.getText()+"'";  
         
        
         
        modelo = new DefaultTableModel(null, titulos);
    
        conexionBD cnp = new conexionBD();
        Connection cnn = (Connection) cnp.ConectarBD();
        com.mysql.jdbc.Statement  st;
        try {
            
            st = (com.mysql.jdbc.Statement) cnn.createStatement();
            ResultSet rst = st.executeQuery(sql);
            
            while(rst.next()){
                registros[0] = rst.getString("usuario");
                registros[1] = rst.getString("comandoejecutado");
                registros[2] = rst.getString("evento");
                registros[3] = rst.getString("FechaHora");
                registros[4] = rst.getString("tabla");
                
                
                modelo.addRow(registros);
                }
            tablaauditoria.setModel(modelo);
            
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

        vistaempleado = new javax.swing.JDialog();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaEmpleado = new javax.swing.JTable();
        txtBuscar = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtempleado = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaauditoria = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtIdEmpleado = new javax.swing.JLabel();
        txttabla = new javax.swing.JTextField();
        btnEmpleado = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        txtfecha = new com.toedter.calendar.JDateChooser();
        txtfecha2 = new com.toedter.calendar.JDateChooser();
        btnFecha = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btnImprimirDos = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        jcomboevento = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        btnEvento = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        checEmpleado = new javax.swing.JCheckBox();
        checFecha = new javax.swing.JCheckBox();
        checEvento = new javax.swing.JCheckBox();
        checTabla = new javax.swing.JCheckBox();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        btnImprimirEmpleado = new javax.swing.JLabel();

        vistaempleado.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaEmpleado.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Codigo", "Nombre", "Apellido", "Usuario", "CIN"
            }
        ));
        tablaEmpleado.getTableHeader().setReorderingAllowed(false);
        tablaEmpleado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaEmpleadoMouseClicked(evt);
            }
        });
        tablaEmpleado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tablaEmpleadoKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(tablaEmpleado);

        vistaempleado.getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 470, 100));

        txtBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBuscarActionPerformed(evt);
            }
        });
        txtBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtBuscarKeyReleased(evt);
            }
        });
        vistaempleado.getContentPane().add(txtBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, 210, -1));

        jLabel13.setFont(new java.awt.Font("Times New Roman", 2, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(0, 204, 0));
        jLabel13.setText("Introduzca Usuario o Nro de C.I.");
        vistaempleado.getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        jButton1.setText("Aceptar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        vistaempleado.getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 10, -1, -1));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setText("Empleado:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 60, -1, -1));
        getContentPane().add(txtempleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 60, 170, -1));

        jLabel1.setText("Fecha hasta:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 100, -1, 20));

        jLabel3.setText("Fecha desde:");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, -1, 20));

        jLabel4.setText("Evento:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, -1, -1));

        tablaauditoria.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(tablaauditoria);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 860, 310));

        jLabel5.setText("Tabla:");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 140, -1, -1));
        getContentPane().add(txtIdEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 40, 30));

        txttabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txttablaActionPerformed(evt);
            }
        });
        getContentPane().add(txttabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 140, 120, -1));

        btnEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/buscar.png"))); // NOI18N
        btnEmpleado.setText("ver");
        btnEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEmpleadoActionPerformed(evt);
            }
        });
        getContentPane().add(btnEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 60, 90, 20));

        jButton2.setText("Ver Movimientos");
        jButton2.setEnabled(false);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 60, -1, -1));

        txtfecha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtfechaFocusLost(evt);
            }
        });
        txtfecha.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtfechaMouseClicked(evt);
            }
        });
        getContentPane().add(txtfecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 100, 170, -1));

        txtfecha2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtfecha2MouseClicked(evt);
            }
        });
        getContentPane().add(txtfecha2, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 100, 140, -1));

        btnFecha.setText("Ver Movimientos");
        btnFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFechaActionPerformed(evt);
            }
        });
        getContentPane().add(btnFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 100, -1, -1));

        jLabel6.setBackground(new java.awt.Color(0, 153, 0));
        jLabel6.setForeground(new java.awt.Color(0, 204, 0));
        jLabel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 153, 0))); // NOI18N
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 880, 330));

        btnImprimirDos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/info1_1.png"))); // NOI18N
        btnImprimirDos.setText("Ver Pdf");
        btnImprimirDos.setEnabled(false);
        btnImprimirDos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirDosActionPerformed(evt);
            }
        });
        getContentPane().add(btnImprimirDos, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 60, -1, 20));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/human.png"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, -10, 110, 210));

        jcomboevento.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccionar", "Insertar", "Modificar", "Eliminar", "Consulta", "Anulado", "Logueo" }));
        getContentPane().add(jcomboevento, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 140, 170, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/social.png"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 40, 90, 180));

        btnEvento.setText("ver");
        btnEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEventoActionPerformed(evt);
            }
        });
        getContentPane().add(btnEvento, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 140, -1, -1));

        jButton3.setText("Buscar");
        jButton3.setEnabled(false);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 193, -1, 20));

        jButton4.setText("Imprimir");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 210, -1, -1));

        jButton5.setText("Ver pdf");
        jButton5.setEnabled(false);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 100, 70, -1));

        checEmpleado.setText("Empleado");
        checEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checEmpleadoActionPerformed(evt);
            }
        });
        getContentPane().add(checEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 190, -1, -1));

        checFecha.setText("Fecha");
        checFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checFechaActionPerformed(evt);
            }
        });
        getContentPane().add(checFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 190, -1, -1));

        checEvento.setText("Evento");
        checEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checEventoActionPerformed(evt);
            }
        });
        getContentPane().add(checEvento, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 190, -1, -1));

        checTabla.setText("Tabla");
        checTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checTablaActionPerformed(evt);
            }
        });
        getContentPane().add(checTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 190, -1, -1));

        jButton6.setText("Imprmir");
        jButton6.setEnabled(false);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 570, -1, -1));

        jButton7.setText("pdf");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 140, -1, -1));

        btnImprimirEmpleado.setBackground(new java.awt.Color(0, 153, 0));
        btnImprimirEmpleado.setForeground(new java.awt.Color(0, 204, 0));
        btnImprimirEmpleado.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Filtros", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 0, 11), new java.awt.Color(0, 153, 0))); // NOI18N
        getContentPane().add(btnImprimirEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 20, 730, 200));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tablaEmpleadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaEmpleadoMouseClicked

        if(txtBuscar.getText().isEmpty()){
            seleccionartabla();
        }
        else{int i = tablaEmpleado.getSelectedRow();
            //hacemos una condicion de que si la varialbe i es igual a - es que no se ha seleccionado ninguna fila
            if (i == -1) {

                JOptionPane.showMessageDialog(null, "Favor seleccione una fila");

            } else { //de lo contrario si se selecciono la fila
                //creamos nuestras varialbes que va a contener los datos de la fila seleccionado en este caso
                //nom va a tener el dato que se encuentra en la fila (i),recuerda que esa variable es la fila //seleccionada,columna 0,que tiene el campo nombre me imagino,y hacemos lo mismo con los otros.getValueAt Obetemos el valor que se encuentra en la tabla indicando la fila y columna.
                //haciendo un Casting a String ese valor.
                String id = (String) tablaEmpleado.getValueAt(i, 0);
                String nombre = (String) tablaEmpleado.getValueAt(i, 1);
//              

                txtIdEmpleado.setText((id));
                this.txtempleado.setText(nombre);
                
               
            }

        }

    }//GEN-LAST:event_tablaEmpleadoMouseClicked

    private void tablaEmpleadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tablaEmpleadoKeyReleased
//        seleccionartabla();
    }//GEN-LAST:event_tablaEmpleadoKeyReleased

    private void txtBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBuscarActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtBuscarActionPerformed

    private void txtBuscarKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtBuscarKeyReleased
        if(txtBuscar.getText().isEmpty()){
            cargarempleados("");
        }
        else{//cargarempleadosbuscar("");

        }
    }//GEN-LAST:event_txtBuscarKeyReleased

    private void btnEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEmpleadoActionPerformed
        // TODO add your handling code here:
        vistaempleado.setModal(true);
        vistaempleado.setSize(504, 181);
        vistaempleado.setLocationRelativeTo(this);
        vistaempleado.setVisible(true);
    }//GEN-LAST:event_btnEmpleadoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        jButton2.setEnabled(true);
        this.vistaempleado.dispose();
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
       cargarauditoriaempleadobuscar("");
       jButton6.setEnabled(true);
       btnImprimirDos.setEnabled(true);
       
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btnFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFechaActionPerformed
        // TODO add your handling code here:
        cargarauditoriafechabuscar("");
        jButton5.setEnabled(true);
    }//GEN-LAST:event_btnFechaActionPerformed

    private void txttablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txttablaActionPerformed
        // TODO add your handling code here:
        cargarauditoriatablabuscar("");
    }//GEN-LAST:event_txttablaActionPerformed

    private void btnImprimirDosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirDosActionPerformed
        // TODO add your handling code here:
              
        String ubicacion="/reportes/AuditoriaEmpleadoDos.jasper";
        String parametro11=(txtempleado.getText());
        jasper.runReporte_parametroSting(ubicacion,"usuario" ,parametro11);
       
        
        
    }//GEN-LAST:event_btnImprimirDosActionPerformed

    private void btnEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEventoActionPerformed
        // TODO add your handling code here:
         cargarauditoriaeventobuscar("");
    }//GEN-LAST:event_btnEventoActionPerformed

    private void txtfecha2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtfecha2MouseClicked
        // TODO add your handling code here:
        btnImprimirDos.setEnabled(true);
    }//GEN-LAST:event_txtfecha2MouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        jButton6.setEnabled(true);
        
        if(checEmpleado.isSelected() && checFecha.isSelected() && checEvento.isSelected()){
             cargarauditoriaFechaBuscareventoUsuario("");
            
        }else{
            if(checEmpleado.isSelected() && checEvento.isSelected() && checTabla.isSelected()){
                                 cargarauditoriaparametrotres("");

                          }
            else{
                    if(checEmpleado.isSelected() && checEvento.isSelected() ){
                            cargarauditoriaparametroevento("");
            
                                }
                        else{
                                if(checEmpleado.isSelected() && checFecha.isSelected() ){
                                    
                                    cargarauditoriaFechaEmpleadobuscar("");
                                    System.out.println("heee");
                                 }else{
                                    cargarauditoriageneral("");
                                }
                                
                    }
            
                }
       
        
        
        
         }
    
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        jasper.runReporte("/reportes/AuditoriaGeneralEmpleado.jasper");
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        try {
            // TODO add your handling code here:
            Fecha = txtfecha.getCalendar();
            Fecha.setTime(txtfecha.getDate());
            String dia = String.valueOf(Fecha.get(Calendar.DATE));
            String mes = String.valueOf(Fecha.get(Calendar.MONTH) + 1);
            String anio = String.valueOf(Fecha.get(Calendar.YEAR));
            String date1 =  dia + "-" + mes + "-" + anio;
            
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            //String dateInString = date1;
            
            Date datef1 = formatter.parse(date1);
            //System.out.println(date);
            System.out.println(formatter.format(datef1));
            
            Fechap = txtfecha2.getCalendar();
            Fechap.setTime(txtfecha2.getDate());
            String diaa = String.valueOf(Fechap.get(Calendar.DATE));
            String mess = String.valueOf(Fechap.get(Calendar.MONTH) + 1);
            String anioo = String.valueOf(Fechap.get(Calendar.YEAR));
            String date2 =  diaa + "-" + mess + "-" + anioo ;
            
             SimpleDateFormat formatear = new SimpleDateFormat("dd-MM-yyyy");
             Date datef2 = formatear.parse(date2);
            
           
            
            String ubicacionn = "/reportes/AuditoriaFecha.jasper";
            //String parametro11=String.valueOf(fechadesde.get(Calendar.DATE));
            //  String parametro12=(fechahasta.getText());
            jasper.runReporte_parametrofecha(ubicacionn, "fechaUno", "fechaDos", datef1, datef2);
        } catch (ParseException ex) {
            Logger.getLogger(Auditoria.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        try {
            // TODO add your handling code here:
            MessageFormat headerFormat = new MessageFormat("Informe");
            MessageFormat footerFormat = new MessageFormat ("detalles");
            tablaauditoria.print(JTable.PrintMode.NORMAL, headerFormat, footerFormat);
        } catch (PrinterException ex) {
            Logger.getLogger(Auditoria.class.getName()).log(Level.SEVERE, null, ex);
        }
            
    }//GEN-LAST:event_jButton6ActionPerformed

    private void txtfechaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtfechaFocusLost
        // TODO add your handling code here:
      
    }//GEN-LAST:event_txtfechaFocusLost

    private void txtfechaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtfechaMouseClicked
        // TODO add your handling code here:
      
    }//GEN-LAST:event_txtfechaMouseClicked

    private void checEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checEmpleadoActionPerformed
        // TODO add your handling code here:
        jButton3.setEnabled(true);
    }//GEN-LAST:event_checEmpleadoActionPerformed

    private void checFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checFechaActionPerformed
        // TODO add your handling code here:
        jButton3.setEnabled(true);
    }//GEN-LAST:event_checFechaActionPerformed

    private void checEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checEventoActionPerformed
        // TODO add your handling code here:
        jButton3.setEnabled(true);
    }//GEN-LAST:event_checEventoActionPerformed

    private void checTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checTablaActionPerformed
        // TODO add your handling code here:
        jButton3.setEnabled(true);
    }//GEN-LAST:event_checTablaActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        String evento= (String) jcomboevento.getSelectedItem();
          String ubicacion="/reportes/AuditoriaEvento.jasper";
        String parametro11=(evento);
        jasper.runReporte_parametroSting(ubicacion,"evento" ,parametro11);
    }//GEN-LAST:event_jButton7ActionPerformed

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
            java.util.logging.Logger.getLogger(Auditoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Auditoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Auditoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Auditoria.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Auditoria dialog = new Auditoria(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btnEmpleado;
    private javax.swing.JButton btnEvento;
    private javax.swing.JButton btnFecha;
    private javax.swing.JButton btnImprimirDos;
    private javax.swing.JLabel btnImprimirEmpleado;
    private javax.swing.JCheckBox checEmpleado;
    private javax.swing.JCheckBox checEvento;
    private javax.swing.JCheckBox checFecha;
    private javax.swing.JCheckBox checTabla;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox jcomboevento;
    private javax.swing.JTable tablaEmpleado;
    private javax.swing.JTable tablaauditoria;
    private javax.swing.JTextField txtBuscar;
    private javax.swing.JLabel txtIdEmpleado;
    private javax.swing.JTextField txtempleado;
    private com.toedter.calendar.JDateChooser txtfecha;
    private com.toedter.calendar.JDateChooser txtfecha2;
    private javax.swing.JTextField txttabla;
    private javax.swing.JDialog vistaempleado;
    // End of variables declaration//GEN-END:variables
}
