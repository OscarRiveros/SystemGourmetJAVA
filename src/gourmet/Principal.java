/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gourmet;

import ModulosProduccion.CobroCliente;
import ModulosProduccion.FacturaCompra;
import ModulosProduccion.FacturaVenta;
import ModulosProduccion.PedidosCliente;
import ModulosProduccion.Recetas;
import Metodos.abm;
import static Metodos.abm.idUsuario;
import ModulosProduccion.ListaPedidos;
import ModulosProduccion.PedidoClienteDos;
import ModulosProduccion.PedidoProveedor;
import ModulosProduccion.Produccion;
import Vista.RespaldarBD;
import Vista.RestaurarBD;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import movimiento.Auditoria;
import movimiento.gastos;
import org.jvnet.substance.SubstanceLookAndFeel;
import productos.Administrar;
import productos.Agregar;
import productos.AgregarProveedor;
import productos.Ciudad;
import productos.Departamento;
import productos.Region;
import reportes.reporte;

/**
 *
 * @author IVAN
 */
public class Principal extends javax.swing.JFrame {

    RegistrarCliente JFRegistrarCliente;
    private abm abm;
    private ResultSet rs, sentenciaa;
    RegistrarEmpleado JFRegistrarEmpleado;
    AgregarProveedor JFAgreProveedor;
    //Administrar JFAdministrar; 
    Agregar JFAgregarProduc;
    ListaPedidos JDPedidosList;
    Auditoria JDAuditoria;
    FacturaVenta JDFacturaVenta;
    PedidoProveedor JDPedidoProveedor;
    PedidosCliente JDPedidos;
    PedidoClienteDos JDPedidoss;
    gastos JDGastos;
    private reporte jasper;
    private Connection conec;

    //variables calculos de caja
    private boolean v_control;
    conexionBD cn;
    public static int idUsuario;
    private boolean vacio;
    private ResultSet rst, rsx, rstt;
    Calendar Fecha;
    Calendar Fechap;
    Calendar Fecha2;
    String usuario;
    String codUsuario;
    String claveActual;
    private conexionBD com;
    Departamento JDialog4;
    Ciudad JDialog5;
    Region JDialog6;
    Recetas JDialog7;
    Produccion JDialog8;
    FacturaCompra JDialog9;

    public Principal() {
        initComponents();

        jasper = new reporte();

        abm = new abm();//Creamos una nueva instancia
        login.setModal(true);
        login.setSize(450, 300);
        login.setLocationRelativeTo(this);
        login.setVisible(true);

        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((pantalla.width - 500) / 2, (pantalla.height - 500) / 2);
        this.setLocationRelativeTo(null);
        setVisible(true);

        //btnInventario.setVisible(false);
        //btnNuevaCompra.setVisible(false);
        //jMenuItem6.setVisible(false);
        //jMenuItem4.setVisible(false);
        // jMenuItem9.setVisible(false);
        // jMenuItem5.setVisible(false);
        //  jMenuItem7.setVisible(false);
        //  jMenuItem8.setVisible(false);
        //  jMenuItem11.setVisible(false);
        Fecha = new GregorianCalendar();
        Fecha2 = new GregorianCalendar();
        //  txtIdCaja.setVisible(false);
        txtsi.setVisible(false);
        jMenu1.setVisible(false);
        montoentrada();
        montogastos();
        montosalida();
        calendario();
        calendario2();
        obtenerfecha();
        idcaja();
        if (caja() == 1) {
            habilitarbotones(true);
            txtCierreCaja.setVisible(true);
            txtapertura.setVisible(false);

            montoapertura();
        } else {

            habilitarbotones(false);
            txtCierreCaja.setEnabled(false);
            txtapertura.setEnabled(true);
            montoapertura();
        }
 
    }

    //METODOS PARA CALCULOS DE CAJA 
    void obtenerfecha() {
        Date fecha = new Date();
        SimpleDateFormat fe = new SimpleDateFormat("yyyy/MM/dd");
        this.fecha.setText(fe.format(fecha));
    }

    public void habilitarbotones(boolean h) { //metodo encargado de habilitar o deshabilitar botonoes
//   btnNuevaVenta.setEnabled(h);

        btnNuevoPedido.setEnabled(h);
        mNuevaCompra.setEnabled(h);
//   mNuevaVenta.setEnabled(h);
        btnEntradas.setEnabled(h);
        btnArticulos.setEnabled(h);
    }

    void calendario() {
        fechainicio.setDate(Fecha.getTime());

        Fecha = fechainicio.getCalendar();
        Fecha.setTime(fechainicio.getDate());
        String dia = String.valueOf(Fecha.get(Calendar.DATE));
        String mes = String.valueOf(Fecha.get(Calendar.MONTH) + 1);
        String anio = String.valueOf(Fecha.get(Calendar.YEAR));
        String date = anio + "/" + mes + "/" + dia;
    }

    void calendario2() {
        fechainicio.setDate(Fecha.getTime());

        Fecha = fechainicio.getCalendar();
        Fecha.setTime(fechainicio.getDate());
        String dia = String.valueOf(Fecha.get(Calendar.DATE));
        String mes = String.valueOf(Fecha.get(Calendar.MONTH) + 1);
        String anio = String.valueOf(Fecha.get(Calendar.YEAR));
        String date = anio + "/" + mes + "/" + dia;
    }

    void idcaja() {
        try {
            rs = abm.nuevo("idcaja", "caja");
            rs.first();
            txtIdCaja.setText(String.valueOf(rs.getInt("codigo") + 1));
            //idpagocuta.setText(String.valueOf(rs.getInt("codigo") + 1));
        } catch (SQLException ex) {

        }
    }

    public boolean validardatos() {
        vacio = false;
        if (txtapertura.getText().isEmpty() && txtCierreCaja.getText().isEmpty()) {
            vacio = true;
        }
        if (txtentrada.getText().isEmpty() && txtCierreCaja.getText().isEmpty()) {
            vacio = true;
        }

        return vacio;

    }

    void montoentrada() {
        try {
            Statement consulta = (Statement) conexionBD.ConectarBD().createStatement();
            rst = consulta.executeQuery("select  sum(entregainicial) as entregainicial, c.estado from facturaventa inner join caja as c \n"
                    + " where fecha between c.fechainicio and curdate() and c.estado='1'");
            rst.first();

            String valor = rst.getString("entregainicial");
            Integer totalentrada = 0;
            if (valor == null) {
                totalentrada = 0;
                System.out.println("vacio");
            } else {
                totalentrada = rst.getInt("entregainicial");
            }
            txtentrada.setText(Integer.toString(totalentrada));
            txtentrada1.setText(Integer.toString(totalentrada));
            System.out.println("emtro 2016");
        } catch (SQLException ex) {
        }
    }

    void montogastos() {
        try {

            Statement consulta = (Statement) conexionBD.ConectarBD().createStatement();
            rst = consulta.executeQuery("select sum(monto) as monto from gastos inner join caja as c \n"
                    + " where fecha between c.fechainicio and curdate() and c.estado='1'");
            rst.first();
            String valor = rst.getString("monto");
            Integer gastos = 0;

            if (valor == null) {
                gastos = 0;
                System.out.println("vacio");
            } else {
                gastos = rst.getInt("monto");
            }

            txtGastos.setText(Integer.toString(gastos));
        } catch (SQLException ex) {
        }
    }

    public int caja() {
        int estado = 0;
        try {

            Statement consulta = (Statement) conexionBD.ConectarBD().createStatement();
            rst = consulta.executeQuery("select estado from caja");
            //rst = consulta.executeQuery("select sum(entregainicial) as entregainicial from facturaventa");
            rst.last();
            String valor = rst.getString("estado");

            if (valor == null) {
                estado = 0;
                System.out.println("vacio");
            } else {
                estado = rst.getInt("estado");
            }

        } catch (SQLException ex) {
        }
        return estado;
    }

    public void montoapertura() {
        try {
            Statement consulta = (Statement) conexionBD.ConectarBD().createStatement();
            rsx = consulta.executeQuery("select apertura from caja");
            rsx.last();
            String apertura = rsx.getString("apertura");

            txtapertura.setText((apertura));
            txtapertura1.setText((apertura));
        } catch (SQLException ex) {
        }
    }

    void montosalida() {
        try {
            Statement consulta = (Statement) conexionBD.ConectarBD().createStatement();
            rsx = consulta.executeQuery("select sum(entregainicial) as entregainicial  from facturacompra inner join caja as c\n"
                    + "where fecha between c.fechainicio and curdate() and c.estado='1'");
            rsx.first();
            Integer valor = rsx.getInt("entregainicial");
            txtSalida.setText(Integer.toString(valor));
            Integer totalentrada = 0;
            System.out.println(valor);
            if (valor == null) {
                totalentrada = 0;
                System.out.println("vaciosdvsdsdvxcvxdv");
            } else {
                totalentrada = rst.getInt("entregainicial");
            }
            txtsi.setText(Integer.toString(totalentrada));
            txtSalida.setText(Integer.toString(totalentrada));
            System.out.println("vacio");
            System.out.println(totalentrada);
        } catch (SQLException ex) {
        }
    }

    void calculos() {
        int apertura, saldo, entrada, salida, resta, gastos, restagasto;

        apertura = (int) Double.parseDouble(String.valueOf(txtapertura.getText()));
        entrada = (int) Double.parseDouble(String.valueOf(txtentrada.getText()));
        saldo = apertura + entrada;

        salida = (int) Double.parseDouble(String.valueOf(txtSalida.getText()));
        gastos = (int) Double.parseDouble(String.valueOf(txtGastos.getText()));
        restagasto = salida + gastos;

        if (saldo > restagasto) {
            resta = saldo - restagasto;
            txtCierre.setText(String.valueOf(resta));
        } else {
            resta = restagasto - saldo;
            txtCierre.setText(String.valueOf(resta));
        }

    }

    void diferencia() {
        int cierre, saldo, caja;
        cierre = (int) Double.parseDouble(String.valueOf(txtCierre.getText()));
        caja = (int) Double.parseDouble(String.valueOf(txtCierreCaja.getText()));
        // if(cierre>caja){
        saldo = cierre - caja;
        txtDiferencia.setText(String.valueOf(saldo));
        // }
        //  else{
        //   saldo = caja - cierre;
        //  txtDiferencia.setText(String.valueOf(saldo));
        //  }       
    }

    void limpiar() {
        txtapertura.setText("");
        txtentrada.setText("");
        txtGastos.setText("");
        txtSalida.setText("");
        txtCierre.setText("");
        txtCierreCaja.setText("");
        txtDiferencia.setText("");
    }

    void deshabilitarcomun() {

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        login = new javax.swing.JDialog();
        jSplitPane2 = new javax.swing.JSplitPane();
        PanelSuperior = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        PanelInferior = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtContraseña = new javax.swing.JPasswordField();
        jLabel8 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        apertura = new javax.swing.JDialog();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        txtapertura = new javax.swing.JTextField();
        txtentrada = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        btnMovimiento = new javax.swing.JButton();
        txtIdCaja = new javax.swing.JTextField();
        txtSalir = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        fechainicio = new com.toedter.calendar.JDateChooser();
        cierre = new javax.swing.JDialog();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        txtSalida = new javax.swing.JTextField();
        jLabel39 = new javax.swing.JLabel();
        txtCierre = new javax.swing.JTextField();
        txtCierreCaja = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        txtGastos = new javax.swing.JTextField();
        txtDiferencia = new javax.swing.JTextField();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtapertura1 = new javax.swing.JTextField();
        txtentrada1 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        btnMovimiento1 = new javax.swing.JButton();
        txtSalir1 = new javax.swing.JButton();
        jLabel17 = new javax.swing.JLabel();
        fecha = new javax.swing.JTextField();
        txtsi = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        parametrofac = new javax.swing.JDialog();
        txtparametro = new javax.swing.JTextField();
        btnInforme = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        parametrorecibo = new javax.swing.JDialog();
        txtparametro1 = new javax.swing.JTextField();
        btnInforme1 = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        parametrocliente = new javax.swing.JDialog();
        txtparametrocliente = new javax.swing.JTextField();
        btnInforme2 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        informecaja = new javax.swing.JDialog();
        btnFecha = new javax.swing.JButton();
        jLabel27 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        fechadesde = new com.toedter.calendar.JDateChooser();
        fechahasta = new com.toedter.calendar.JDateChooser();
        parametroreceta = new javax.swing.JDialog();
        txtNombreReceta = new javax.swing.JTextField();
        jButton2 = new javax.swing.JButton();
        informeCompra = new javax.swing.JDialog();
        btnFecha1 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        fechadesde1 = new com.toedter.calendar.JDateChooser();
        fechahasta1 = new com.toedter.calendar.JDateChooser();
        informeventa = new javax.swing.JDialog();
        btnFecha2 = new javax.swing.JButton();
        jLabel36 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        fechadesde2 = new com.toedter.calendar.JDateChooser();
        fechahasta2 = new com.toedter.calendar.JDateChooser();
        panelBotones = new javax.swing.JPanel();
        btnClientes = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        btnProveedores = new javax.swing.JButton();
        btnArticulos = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnNuevoPedido = new javax.swing.JButton();
        btnEntradas = new javax.swing.JButton();
        btnInventario = new javax.swing.JButton();
        jLabel30 = new javax.swing.JLabel();
        btnProduccion = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        txtCompras = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        btnRecetas = new javax.swing.JButton();
        peido = new javax.swing.JButton();
        btnFacturar = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jmArchivo = new javax.swing.JMenu();
        MenuRegistro = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuItem2 = new javax.swing.JMenuItem();
        jmSalir = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        mNuevaCompra = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        jMenuItem21 = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenuItem24 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        MenuAuditoria = new javax.swing.JMenu();
        jMenuItem15 = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();

        login.setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        login.setTitle("Control de Acceso");

        jSplitPane2.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);

        jLabel2.setFont(new java.awt.Font("Bell MT", 1, 36)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 204, 0));
        jLabel2.setText("Control de acceso");

        javax.swing.GroupLayout PanelSuperiorLayout = new javax.swing.GroupLayout(PanelSuperior);
        PanelSuperior.setLayout(PanelSuperiorLayout);
        PanelSuperiorLayout.setHorizontalGroup(
            PanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSuperiorLayout.createSequentialGroup()
                .addGap(56, 56, 56)
                .addComponent(jLabel2)
                .addContainerGap(68, Short.MAX_VALUE))
        );
        PanelSuperiorLayout.setVerticalGroup(
            PanelSuperiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PanelSuperiorLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jSplitPane2.setTopComponent(PanelSuperior);

        PanelInferior.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Bell MT", 1, 14)); // NOI18N
        jLabel6.setText("Usuario");
        PanelInferior.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, 67, -1));
        PanelInferior.add(txtUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 30, 146, -1));

        jLabel7.setFont(new java.awt.Font("Bell MT", 1, 14)); // NOI18N
        jLabel7.setText("Clave");
        PanelInferior.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, -1, -1));

        txtContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtContraseñaActionPerformed(evt);
            }
        });
        txtContraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtContraseñaKeyPressed(evt);
            }
        });
        PanelInferior.add(txtContraseña, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 70, 150, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/users.png"))); // NOI18N
        PanelInferior.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 30, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/candado.png"))); // NOI18N
        PanelInferior.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 50, -1, -1));

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        PanelInferior.add(btnCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 110, -1, -1));

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });
        PanelInferior.add(btnAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 110, -1, -1));

        jSplitPane2.setRightComponent(PanelInferior);

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login.getContentPane());
        login.getContentPane().setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        loginLayout.setVerticalGroup(
            loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(loginLayout.createSequentialGroup()
                .addComponent(jSplitPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        apertura.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        apertura.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel10.setFont(new java.awt.Font("Trebuchet MS", 2, 36)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(0, 204, 0));
        jLabel10.setText("Apertura de Caja");
        apertura.getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));

        jLabel11.setText("Fecha de Movimiento");
        apertura.getContentPane().add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 70, -1, -1));

        jLabel12.setText("Monto Apertura:");
        apertura.getContentPane().add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 150, -1, -1));

        jLabel13.setText("Monto Entrada:");
        apertura.getContentPane().add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 190, -1, -1));

        txtapertura.setText("0");
        txtapertura.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtaperturaFocusLost(evt);
            }
        });
        txtapertura.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtaperturaKeyTyped(evt);
            }
        });
        apertura.getContentPane().add(txtapertura, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 150, 123, -1));

        txtentrada.setText("0");
        txtentrada.setEnabled(false);
        apertura.getContentPane().add(txtentrada, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 123, -1));

        jLabel18.setBorder(javax.swing.BorderFactory.createTitledBorder("Apertura de Caja"));
        apertura.getContentPane().add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 120, 230, 120));

        btnMovimiento.setText("Abrir Caja");
        btnMovimiento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMovimientoActionPerformed(evt);
            }
        });
        apertura.getContentPane().add(btnMovimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 260, -1, -1));

        txtIdCaja.setEnabled(false);
        apertura.getContentPane().add(txtIdCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 50, -1));

        txtSalir.setText("Salir");
        txtSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSalirActionPerformed(evt);
            }
        });
        apertura.getContentPane().add(txtSalir, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 260, -1, -1));

        jLabel22.setText("*");
        apertura.getContentPane().add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 150, -1, -1));

        jLabel23.setText("*");
        apertura.getContentPane().add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 200, 30, 10));

        fechainicio.setEnabled(false);
        apertura.getContentPane().add(fechainicio, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 30, 94, -1));

        cierre.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel37.setText("Monto Salida:");
        cierre.getContentPane().add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 160, -1, -1));

        jLabel38.setText("Cierre de Caja del Sistema:");
        cierre.getContentPane().add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 200, 140, 20));

        txtSalida.setText("0");
        txtSalida.setEnabled(false);
        txtSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSalidaActionPerformed(evt);
            }
        });
        cierre.getContentPane().add(txtSalida, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 160, 123, -1));

        jLabel39.setText("Cierre Caja en Efectivo:");
        cierre.getContentPane().add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(95, 240, 130, 20));

        txtCierre.setText("0");
        txtCierre.setEnabled(false);
        txtCierre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCierreActionPerformed(evt);
            }
        });
        cierre.getContentPane().add(txtCierre, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 200, 123, -1));

        txtCierreCaja.setText("0");
        txtCierreCaja.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtCierreCajaFocusLost(evt);
            }
        });
        txtCierreCaja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCierreCajaMouseClicked(evt);
            }
        });
        txtCierreCaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCierreCajaActionPerformed(evt);
            }
        });
        txtCierreCaja.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCierreCajaKeyTyped(evt);
            }
        });
        cierre.getContentPane().add(txtCierreCaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 240, 123, -1));

        jLabel40.setBorder(javax.swing.BorderFactory.createTitledBorder("Arqueo Caja"));
        cierre.getContentPane().add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 190, 290, 140));

        jLabel41.setText("Gastos Varios:");
        cierre.getContentPane().add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 110, -1, -1));

        txtGastos.setText("0");
        txtGastos.setEnabled(false);
        txtGastos.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtGastosFocusLost(evt);
            }
        });
        cierre.getContentPane().add(txtGastos, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 110, 120, -1));

        txtDiferencia.setText("0");
        txtDiferencia.setEnabled(false);
        txtDiferencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiferenciaActionPerformed(evt);
            }
        });
        cierre.getContentPane().add(txtDiferencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 280, 123, -1));

        jLabel42.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cierre.getContentPane().add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 270, 240, 40));

        jLabel43.setText("Diferencia cierre:");
        cierre.getContentPane().add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 280, -1, -1));

        jLabel14.setText("Fecha de Movimiento");
        cierre.getContentPane().add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, -1, -1));

        jLabel15.setText("Monto Apertura:");
        cierre.getContentPane().add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        jLabel16.setText("Monto Entrada:");
        cierre.getContentPane().add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, -1, -1));

        txtapertura1.setText("0");
        txtapertura1.setEnabled(false);
        txtapertura1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtapertura1FocusLost(evt);
            }
        });
        txtapertura1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtapertura1KeyTyped(evt);
            }
        });
        cierre.getContentPane().add(txtapertura1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 100, 123, -1));

        txtentrada1.setEnabled(false);
        cierre.getContentPane().add(txtentrada1, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 123, -1));

        jLabel19.setBorder(javax.swing.BorderFactory.createTitledBorder("Apertura de Caja"));
        cierre.getContentPane().add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 230, 120));

        btnMovimiento1.setText("Cerrar Caja");
        btnMovimiento1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMovimiento1ActionPerformed(evt);
            }
        });
        cierre.getContentPane().add(btnMovimiento1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 340, -1, -1));

        txtSalir1.setText("Salir");
        txtSalir1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSalir1ActionPerformed(evt);
            }
        });
        cierre.getContentPane().add(txtSalir1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 340, -1, -1));

        jLabel17.setFont(new java.awt.Font("Trebuchet MS", 2, 36)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(51, 204, 0));
        jLabel17.setText("Cierre de Caja");
        cierre.getContentPane().add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 10, -1, -1));

        fecha.setEnabled(false);
        cierre.getContentPane().add(fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 110, -1));
        cierre.getContentPane().add(txtsi, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 40, 80, -1));

        jLabel24.setText("*");
        cierre.getContentPane().add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 240, 20, 20));

        jLabel44.setBorder(javax.swing.BorderFactory.createTitledBorder("Cierre de Caja"));
        cierre.getContentPane().add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 70, 290, 120));

        parametrofac.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        parametrofac.getContentPane().add(txtparametro, new org.netbeans.lib.awtextra.AbsoluteConstraints(21, 12, 219, -1));

        btnInforme.setText("Ver Informe");
        btnInforme.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInformeActionPerformed(evt);
            }
        });
        parametrofac.getContentPane().add(btnInforme, new org.netbeans.lib.awtextra.AbsoluteConstraints(267, 11, -1, -1));

        jLabel20.setText("Escriba el codigo/ID de la Factura");
        parametrofac.getContentPane().add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(51, 40, -1, -1));

        btnInforme1.setText("Ver Informe");
        btnInforme1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInforme1ActionPerformed(evt);
            }
        });

        jLabel21.setText("Escriba el codigo/ID del Recibo");

        javax.swing.GroupLayout parametroreciboLayout = new javax.swing.GroupLayout(parametrorecibo.getContentPane());
        parametrorecibo.getContentPane().setLayout(parametroreciboLayout);
        parametroreciboLayout.setHorizontalGroup(
            parametroreciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(parametroreciboLayout.createSequentialGroup()
                .addGroup(parametroreciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(parametroreciboLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(txtparametro1, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnInforme1))
                    .addGroup(parametroreciboLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel21)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        parametroreciboLayout.setVerticalGroup(
            parametroreciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(parametroreciboLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(parametroreciboLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtparametro1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInforme1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel21))
        );

        btnInforme2.setText("Ver Informe");
        btnInforme2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInforme2ActionPerformed(evt);
            }
        });

        jLabel25.setText("Escriba el C.INº Del Cliente");

        javax.swing.GroupLayout parametroclienteLayout = new javax.swing.GroupLayout(parametrocliente.getContentPane());
        parametrocliente.getContentPane().setLayout(parametroclienteLayout);
        parametroclienteLayout.setHorizontalGroup(
            parametroclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(parametroclienteLayout.createSequentialGroup()
                .addGroup(parametroclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(parametroclienteLayout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(txtparametrocliente, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(btnInforme2))
                    .addGroup(parametroclienteLayout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addComponent(jLabel25)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        parametroclienteLayout.setVerticalGroup(
            parametroclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(parametroclienteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(parametroclienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtparametrocliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnInforme2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel25))
        );

        jLabel26.setText("jLabel26");

        informecaja.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnFecha.setText("Aceptar");
        btnFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFechaActionPerformed(evt);
            }
        });
        informecaja.getContentPane().add(btnFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, -1, -1));

        jLabel27.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(0, 204, 0));
        jLabel27.setText("Ingrese Fecha Desde:");
        informecaja.getContentPane().add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel28.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(51, 204, 0));
        jLabel28.setText("Ingrese Fecha Hasta:");
        informecaja.getContentPane().add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));
        informecaja.getContentPane().add(fechadesde, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, -1, -1));
        informecaja.getContentPane().add(fechahasta, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, -1, -1));

        jButton2.setText("Imprimir Receta");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout parametrorecetaLayout = new javax.swing.GroupLayout(parametroreceta.getContentPane());
        parametroreceta.getContentPane().setLayout(parametrorecetaLayout);
        parametrorecetaLayout.setHorizontalGroup(
            parametrorecetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(parametrorecetaLayout.createSequentialGroup()
                .addGap(70, 70, 70)
                .addGroup(parametrorecetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton2)
                    .addComponent(txtNombreReceta, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(204, Short.MAX_VALUE))
        );
        parametrorecetaLayout.setVerticalGroup(
            parametrorecetaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(parametrorecetaLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(txtNombreReceta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton2)
                .addContainerGap(218, Short.MAX_VALUE))
        );

        informeCompra.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnFecha1.setText("Aceptar");
        btnFecha1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecha1ActionPerformed(evt);
            }
        });
        informeCompra.getContentPane().add(btnFecha1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, -1, -1));

        jLabel29.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(0, 204, 0));
        jLabel29.setText("Ingrese Fecha Desde:");
        informeCompra.getContentPane().add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel31.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(51, 204, 0));
        jLabel31.setText("Ingrese Fecha Hasta:");
        informeCompra.getContentPane().add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));
        informeCompra.getContentPane().add(fechadesde1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 10, -1, -1));
        informeCompra.getContentPane().add(fechahasta1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 60, -1, -1));

        informeventa.getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnFecha2.setText("Aceptar");
        btnFecha2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFecha2ActionPerformed(evt);
            }
        });
        informeventa.getContentPane().add(btnFecha2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 90, -1, -1));

        jLabel36.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabel36.setForeground(new java.awt.Color(0, 204, 0));
        jLabel36.setText("Ingrese Fecha Desde:");
        informeventa.getContentPane().add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        jLabel45.setFont(new java.awt.Font("Times New Roman", 2, 14)); // NOI18N
        jLabel45.setForeground(new java.awt.Color(51, 204, 0));
        jLabel45.setText("Ingrese Fecha Hasta:");
        informeventa.getContentPane().add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, -1));
        informeventa.getContentPane().add(fechadesde2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, -1, -1));
        informeventa.getContentPane().add(fechahasta2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 60, -1, -1));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(51, 204, 0));
        setName("JFRPrincipal"); // NOI18N
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panelBotones.setBackground(new java.awt.Color(0, 204, 0));
        panelBotones.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(panelBotones, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        btnClientes.setFont(new java.awt.Font("Bell MT", 0, 12)); // NOI18N
        btnClientes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/clientes.png"))); // NOI18N
        btnClientes.setText("Clientes");
        btnClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClientesActionPerformed(evt);
            }
        });
        getContentPane().add(btnClientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 80, 130, -1));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jLabel1.setFont(new java.awt.Font("Bell MT", 0, 18)); // NOI18N
        jLabel1.setText("VENTAS");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(121, 121, 121)
                .addComponent(jLabel1)
                .addContainerGap(137, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 330, -1));

        jPanel5.setBackground(new java.awt.Color(204, 204, 204));

        jLabel4.setFont(new java.awt.Font("Bell MT", 0, 18)); // NOI18N
        jLabel4.setText("COMPRAS");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(120, 120, 120)
                .addComponent(jLabel4)
                .addContainerGap(124, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                .addContainerGap())
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 200, 330, -1));

        btnProveedores.setFont(new java.awt.Font("Bell MT", 0, 14)); // NOI18N
        btnProveedores.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/proovedores.png"))); // NOI18N
        btnProveedores.setText("Proveedores");
        btnProveedores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProveedoresActionPerformed(evt);
            }
        });
        getContentPane().add(btnProveedores, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 310, 170, -1));

        btnArticulos.setFont(new java.awt.Font("Bell MT", 0, 14)); // NOI18N
        btnArticulos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/articulos.png"))); // NOI18N
        btnArticulos.setText("Productos");
        btnArticulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnArticulosActionPerformed(evt);
            }
        });
        getContentPane().add(btnArticulos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 430, 140, -1));

        jPanel4.setBackground(new java.awt.Color(204, 204, 204));

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("STOCK DE MERCADERIAS");
        jLabel3.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(86, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(46, 46, 46))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 340, 30));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/chef.jpg"))); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 610, 560));

        btnNuevoPedido.setFont(new java.awt.Font("Bell MT", 0, 12)); // NOI18N
        btnNuevoPedido.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/nuevacoompra.png"))); // NOI18N
        btnNuevoPedido.setText("Pedido a Proveedor");
        btnNuevoPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoPedidoActionPerformed(evt);
            }
        });
        getContentPane().add(btnNuevoPedido, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, 180, -1));

        btnEntradas.setFont(new java.awt.Font("Bell MT", 0, 14)); // NOI18N
        btnEntradas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/entrada.png"))); // NOI18N
        btnEntradas.setText("Gastos Varios");
        btnEntradas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntradasActionPerformed(evt);
            }
        });
        getContentPane().add(btnEntradas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 250, 140, 40));

        btnInventario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/inventario.png"))); // NOI18N
        btnInventario.setText("Movimiento de Caja");
        btnInventario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInventarioActionPerformed(evt);
            }
        });
        getContentPane().add(btnInventario, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 430, 140, 40));

        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/pizaa.png"))); // NOI18N
        getContentPane().add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 430, 70, 70));

        btnProduccion.setText("Produccion");
        btnProduccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProduccionActionPerformed(evt);
            }
        });
        getContentPane().add(btnProduccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 490, 130, 40));

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/pizaa.png"))); // NOI18N
        getContentPane().add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 280, 70, 70));

        txtCompras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/add_1.png"))); // NOI18N
        txtCompras.setText("Compras");
        txtCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtComprasActionPerformed(evt);
            }
        });
        getContentPane().add(txtCompras, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 130, -1));

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/pizaa.png"))); // NOI18N
        getContentPane().add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 70, 70));

        btnRecetas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/fac.png"))); // NOI18N
        btnRecetas.setText("Lista de Productos");
        btnRecetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecetasActionPerformed(evt);
            }
        });
        getContentPane().add(btnRecetas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 490, 160, -1));

        peido.setText("Pedidos Gourmet");
        peido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                peidoActionPerformed(evt);
            }
        });
        getContentPane().add(peido, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 120, 40));

        btnFacturar.setText("Facturar");
        btnFacturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFacturarActionPerformed(evt);
            }
        });
        getContentPane().add(btnFacturar, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 140, 120, 40));

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/pizaa.png"))); // NOI18N
        getContentPane().add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 70, 70));

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/pizaa.png"))); // NOI18N
        getContentPane().add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 70, 70));

        jmArchivo.setText("Archivo");

        MenuRegistro.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        MenuRegistro.setText("Registrar Empleado");
        MenuRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuRegistroActionPerformed(evt);
            }
        });
        jmArchivo.add(MenuRegistro);

        jMenuItem1.setText("Region");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jmArchivo.add(jMenuItem1);

        jMenuItem12.setText("Departamento");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jmArchivo.add(jMenuItem12);

        jMenuItem13.setText("Ciudad");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jmArchivo.add(jMenuItem13);
        jmArchivo.add(jSeparator1);

        jMenuItem2.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem2.setText("Cambiar de Usuario");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jmArchivo.add(jMenuItem2);

        jmSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_MASK));
        jmSalir.setText("Salir");
        jmSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmSalirActionPerformed(evt);
            }
        });
        jmArchivo.add(jmSalir);

        jMenuBar1.add(jmArchivo);

        jMenu1.setText("Compras");

        mNuevaCompra.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.ALT_MASK));
        mNuevaCompra.setText("Nueva Compra");
        mNuevaCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mNuevaCompraActionPerformed(evt);
            }
        });
        jMenu1.add(mNuevaCompra);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Informes");

        jMenuItem3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/info1.png"))); // NOI18N
        jMenuItem3.setText("Informe de Productos");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem3);

        jMenuItem6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/info1.png"))); // NOI18N
        jMenuItem6.setText("Ciudad");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        jMenuItem4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/info1.png"))); // NOI18N
        jMenuItem4.setText("Factura");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem4);

        jMenuItem9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/info3.png"))); // NOI18N
        jMenuItem9.setText("Info. Caja");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem9);

        jMenuItem10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/info.png"))); // NOI18N
        jMenuItem10.setText("Info. Stock Bajos");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem10);

        jMenuItem5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/info2.png"))); // NOI18N
        jMenuItem5.setText("Pagos a Proveedor");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/info2.png"))); // NOI18N
        jMenuItem7.setText("Region");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem7);

        jMenuItem8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/info3.png"))); // NOI18N
        jMenuItem8.setText("Departamento");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem8);

        jMenuItem11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/resources/info3.png"))); // NOI18N
        jMenuItem11.setText("Info. Caja por Fecha");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem11);

        jMenuItem17.setText("Imprimir Receta");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem17);

        jMenu5.setText("Movimientos");

        jMenuItem21.setText("Apertura/Entrada");
        jMenuItem21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem21ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem21);

        jMenuItem22.setText("Salida Caja");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem22);

        jMenuItem23.setText("Salida Compra");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem23);

        jMenuItem24.setText("Entrada Venta");
        jMenuItem24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem24ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem24);

        jMenu2.add(jMenu5);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Pedidos");

        jMenuItem14.setText("Lista de Pedidos");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem14);

        jMenuItem18.setText("Lista General Pedidos");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem18);

        jMenuBar1.add(jMenu3);

        MenuAuditoria.setText("Auditoria");

        jMenuItem15.setText("Auditorias");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        MenuAuditoria.add(jMenuItem15);

        jMenuItem16.setText("Informe General PDF");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        MenuAuditoria.add(jMenuItem16);

        jMenuBar1.add(MenuAuditoria);

        jMenu4.setText("Respaldo BD");

        jMenuItem19.setText("Backup de BD");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem19);

        jMenuItem20.setText("Restaurar BD");
        jMenuItem20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem20ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem20);

        jMenuBar1.add(jMenu4);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClientesActionPerformed
        JFRegistrarCliente = new RegistrarCliente();
        JFRegistrarCliente.setLocationRelativeTo(this);
        JFRegistrarCliente.setVisible(true);
    }//GEN-LAST:event_btnClientesActionPerformed

    private void MenuRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuRegistroActionPerformed
        JFRegistrarEmpleado = new RegistrarEmpleado();
        JFRegistrarEmpleado.setLocationRelativeTo(this);
        JFRegistrarEmpleado.setVisible(true);
    }//GEN-LAST:event_MenuRegistroActionPerformed

    private void jmSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmSalirActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jmSalirActionPerformed

    private void btnProveedoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProveedoresActionPerformed
        JFAgreProveedor = new AgregarProveedor();
        JFAgreProveedor.setLocationRelativeTo(this);
        JFAgreProveedor.setModal(true);
        JFAgreProveedor.setVisible(true);
    }//GEN-LAST:event_btnProveedoresActionPerformed

    private void btnArticulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnArticulosActionPerformed
        JFAgregarProduc = new Agregar();
        JFAgregarProduc.setLocationRelativeTo(this);
        JFAgregarProduc.setModal(true);
        JFAgregarProduc.setVisible(true);
    }//GEN-LAST:event_btnArticulosActionPerformed

    private void txtContraseñaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtContraseñaKeyPressed

    }//GEN-LAST:event_txtContraseñaKeyPressed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        login();

    }//GEN-LAST:event_btnAceptarActionPerformed

    public void login() {
        rs = abm.consultaLogin("*", "empleado", txtUsuario.getText(), txtContraseña.getText());
        try {
            if (rs.getRow() != 0) {
                abm.nomUsuario = rs.getString("nombre");
                abm.apeUsuario = rs.getString("apellido");
                abm.nivel = Integer.valueOf(rs.getString("nivel"));
                abm.idUsuario = Integer.valueOf(rs.getString("idempleado"));
                int idusuario= Integer.valueOf(rs.getString("idempleado"));
                login.dispose();
                String nomNivel = null;
                if (abm.nivel == 1) {
                    nomNivel = "ADMINISTRADOR";
                    MenuRegistro.setEnabled(true);
                    btnArticulos.setEnabled(true);
                    btnProduccion.setEnabled(true);

                } else {
                    nomNivel = "Usuario Comun";
                    MenuRegistro.setEnabled(false);
                    btnArticulos.setEnabled(false);
                    btnProduccion.setEnabled(false);
                    MenuAuditoria.setEnabled(false);
                }
                this.setTitle("Espacio Gourmet:     Usuario: " + abm.nomUsuario + abm.apeUsuario + abm.idUsuario + "   Nivel: " + nomNivel);
                 Statement sentencia = (Statement) conexionBD.ConectarBD().createStatement();
                int auditoria = sentencia.executeUpdate("insert into auditoria (idusuario,comandoejecutado,evento,fecha,hora,tabla)"
                     + " values ("+idusuario+",'"+nomNivel+"','Logueo',current_date(),current_time(),'empleado')");
            } else {
                JOptionPane.showMessageDialog(null, "El usuario no existe");
            }
        } catch (Exception e) {
            System.out.println("Error al validar usuario");
        }
    }


    private void btnNuevoPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoPedidoActionPerformed
        JDPedidoProveedor = new PedidoProveedor(this, true);
        JDPedidoProveedor.setLocationRelativeTo(this);
        JDPedidoProveedor.setVisible(true);

    }//GEN-LAST:event_btnNuevoPedidoActionPerformed

    private void mNuevaCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mNuevaCompraActionPerformed
        // TODO add your handling code here:
//        JDFacturaCompra= new FacturaCompra(this,true);
//        JDFacturaCompra.setLocationRelativeTo(this);
//        JDFacturaCompra.setVisible(true);
    }//GEN-LAST:event_mNuevaCompraActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        abm = new abm();//Creamos una nueva instancia

        login.setModal(true);
        login.setSize(450, 300);
        login.setLocationRelativeTo(this);
        login.setVisible(true);
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void btnEntradasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntradasActionPerformed
        // TODO add your handling code here:
        JDGastos = new gastos(this, true);
        JDGastos.setLocationRelativeTo(this);
        JDGastos.setVisible(true);

    }//GEN-LAST:event_btnEntradasActionPerformed

    private void txtaperturaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtaperturaFocusLost
        // TODO add your handling code here:
        //  calculos();
    }//GEN-LAST:event_txtaperturaFocusLost

    private void txtaperturaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtaperturaKeyTyped
        // TODO add your handling code here:
        char caracter = evt.getKeyChar();
        if ((caracter < '0' || (caracter > '9')) && (caracter != '\b')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtaperturaKeyTyped

    private void btnMovimientoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMovimientoActionPerformed
        // TODO add your handling code here:
        vacio = validardatos();

        if (vacio == true) {
            JOptionPane.showMessageDialog(null, "Completar los datos marcados en aterisco");

        } else {

            int idempleado = abm.idUsuario;

            Fecha2 = fechainicio.getCalendar();
            Fecha2.setTime(fechainicio.getDate());
            String dia = String.valueOf(Fecha2.get(Calendar.DATE));
            String mes = String.valueOf(Fecha2.get(Calendar.MONTH) + 1);
            String anio = String.valueOf(Fecha2.get(Calendar.YEAR));

            if (caja() == 0) {
                idcaja();
                int estado = 1;
                v_control = abm.insertar("caja", txtIdCaja.getText() + ",'" + anio + "/" + mes + "/" + dia + "'," + txtapertura.getText() + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + estado + ",'" + anio + "/" + mes + "/" + dia + "'," + idempleado);
                // v_control = abm.insertar("caja", txtIdCaja.getText() + ",'" + anioo + "/" + mess + "/" + diaa + "',"+txtapertura.getText() +"," + txtentrada.getText()+"," + txtSalida.getText() + "," + txtCierre.getText() + ","+txtCierre.getText()+"," + txtDiferencia.getText()+","+estado+","+idempleado);

                if (v_control == true) {
                    JOptionPane.showMessageDialog(null, "Se ha Abierto la Caja");
                    btnArticulos.setEnabled(true);
                }
                habilitarbotones(true);
                MenuRegistro.setEnabled(false);
                //btnArticulos.setEnabled(false);

            }

            /* else{
             if(caja()==1){
             int estado=0;
             v_control = abm.insertar("caja", txtIdCaja.getText() + ",'" + anio + "/" + mes + "/" + dia + "',"+txtapertura.getText() +"," + txtentrada.getText()+"," + txtSalida.getText() + "," + txtCierre.getText() + ","+txtCierreCaja.getText()+"," + txtDiferencia.getText()+","+estado+","+idempleado);
             //v_control= abm.modificar("FacturaVenta", "estado='"+estado+"', "+"anulado='"+anulado+"', "+ "saldo=saldo-"+txtmontopagado.getText(), " idfacturaventa="+txtidfac.getText());
             System.out.println(caja());
             if (v_control==true){
             JOptionPane.showMessageDialog(null,"Se ha guardado los datos");

             }

             habilitarbotones(false);
             }
             }*/
            limpiar();
            montoentrada();
            montogastos();
            montosalida();
            apertura.dispose();
            //btnMovimiento.setEnabled(false);
            // txtapertura.setEnabled(false);
            // txtCierreCaja.setEnabled(true);
        }

    }//GEN-LAST:event_btnMovimientoActionPerformed

    private void txtSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSalirActionPerformed
        // TODO add your handling code here:
        this.apertura.dispose();
    }//GEN-LAST:event_txtSalirActionPerformed

    private void txtSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSalidaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtSalidaActionPerformed

    private void txtCierreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCierreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCierreActionPerformed

    private void txtCierreCajaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCierreCajaMouseClicked
        // TODO add your handling code here:
        calculos();
        //montosalida();
    }//GEN-LAST:event_txtCierreCajaMouseClicked

    private void txtCierreCajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCierreCajaActionPerformed
        // TODO add your handling code here:
        diferencia();
    }//GEN-LAST:event_txtCierreCajaActionPerformed

    private void txtCierreCajaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCierreCajaFocusLost
        // TODO add your handling code here:
        diferencia();
    }//GEN-LAST:event_txtCierreCajaFocusLost

    private void txtCierreCajaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCierreCajaKeyTyped
        // TODO add your handling code here:
        char caracter = evt.getKeyChar();
        if ((caracter < '0' || (caracter > '9')) && (caracter != '\b')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtCierreCajaKeyTyped

    private void txtGastosFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGastosFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_txtGastosFocusLost

    private void txtDiferenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiferenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiferenciaActionPerformed

    private void txtapertura1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtapertura1FocusLost
        // TODO add your handling code here:
        calculos();
    }//GEN-LAST:event_txtapertura1FocusLost

    private void txtapertura1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtapertura1KeyTyped
        // TODO add your handling code here:
        char caracter = evt.getKeyChar();
        if ((caracter < '0' || (caracter > '9')) && (caracter != '\b')) {
            evt.consume();
        }
    }//GEN-LAST:event_txtapertura1KeyTyped

    private void btnMovimiento1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMovimiento1ActionPerformed
        // TODO add your handling code here:
        vacio = validardatos();
        if (vacio == true) {
            JOptionPane.showMessageDialog(null, "Completar los datos marcados en aterisco");

        } else {

            int idempleado = abm.idUsuario;

            Fecha2 = fechainicio.getCalendar();
            Fecha2.setTime(fechainicio.getDate());
            String dia = String.valueOf(Fecha2.get(Calendar.DATE));
            String mes = String.valueOf(Fecha2.get(Calendar.MONTH) + 1);
            String anio = String.valueOf(Fecha2.get(Calendar.YEAR));

            if (caja() == 0) {

                int estado = 1;
                v_control = abm.insertar("caja", txtIdCaja.getText() + ",'" + anio + "/" + mes + "/" + dia + "'," + txtapertura.getText() + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + 0 + "," + estado + ",'" + anio + "/" + mes + "/" + dia + "'," + idempleado);
                // v_control = abm.insertar("caja", txtIdCaja.getText() + ",'" + anioo + "/" + mess + "/" + diaa + "',"+txtapertura.getText() +"," + txtentrada.getText()+"," + txtSalida.getText() + "," + txtCierre.getText() + ","+txtCierre.getText()+"," + txtDiferencia.getText()+","+estado+","+idempleado);
                System.out.println("despues del print");
                if (v_control == true) {
                    JOptionPane.showMessageDialog(null, "Se ha Habierto la Caja");

                }
                habilitarbotones(true);

            } else {
                String consulta = "";
                if (caja() == 1) {
                    try {
                        Statement stm = (Statement) cn.ConectarBD().createStatement();
                        consulta = "select COUNT(*) from caja ";
                        ResultSet rS = stm.executeQuery(consulta);
                        rS.last();
                        int idultimo = rS.getInt("COUNT(*)");
                        int estado = 0;
                        System.out.println("ocnasicbsdkucbsuadbfyis    " + txtIdCaja.getText());
                        v_control = abm.modificar("caja", "entrada=" + txtentrada1.getText() + "," + "salida=" + txtSalida.getText() + "," + "cierre=" + txtCierre.getText() + "," + "cierrecaja=" + txtCierreCaja.getText() + "," + "diferenciacierre=" + txtDiferencia.getText() + "," + "estado=" + estado + "," + "fechacierre='" + fecha.getText() + "'", "idcaja=" + idultimo);
                        // v_control = abm.insertar("caja", txtIdCaja.getText() + ",'" + anioo + "/" + mess + "/" + diaa + "',"+txtapertura1.getText() +"," + txtentrada1.getText()+"," + txtSalida.getText() + "," + txtCierre.getText() + ","+txtCierreCaja.getText()+"," + txtDiferencia.getText()+","+estado+","+idempleado);
                        //v_control= abm.modificar("FacturaVenta", "estado='"+estado+"', "+"anulado='"+anulado+"', "+ "saldo=saldo-"+txtmontopagado.getText(), " idfacturaventa="+txtidfac.getText());
                        System.out.println(caja());
                        if (v_control == true) {
                            JOptionPane.showMessageDialog(null, "Se ha Cerrado la Caja");
                        }

                        habilitarbotones(false);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
            }

            limpiar();
            montoentrada();
            montogastos();
            montosalida();
            cierre.dispose();
            ///btnMovimiento1.setEnabled(false);
            //txtCierreCaja.setEnabled(false);
            // txtapertura.setEnabled(true);
        }
    }//GEN-LAST:event_btnMovimiento1ActionPerformed

    private void txtSalir1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSalir1ActionPerformed
        // TODO add your handling code here:
        this.cierre.dispose();
    }//GEN-LAST:event_txtSalir1ActionPerformed

    private void btnInformeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInformeActionPerformed
        // TODO add your handling code here:
        this.parametrofac.dispose();
        String ubicacion = "/reportes/Factura.jasper";
        Integer parametro1 = Integer.valueOf(txtparametro.getText());

        jasper.runReporte_parametro(ubicacion, "idfacturaventa", parametro1);
    }//GEN-LAST:event_btnInformeActionPerformed

    private void btnInforme1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInforme1ActionPerformed
        // TODO add your handling code here:
        this.parametrorecibo.dispose();
        String ubicacionn = "/src/reportes/Recibo.jasper";
        Integer parametro11 = Integer.valueOf(txtparametro1.getText());
        jasper.runReporte_parametro(ubicacionn, "idpagos", parametro11);
    }//GEN-LAST:event_btnInforme1ActionPerformed

    private void btnInforme2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInforme2ActionPerformed
        // TODO add your handling code here:
        this.parametrocliente.dispose();
        String ubicacionn = "/src/reportes/Deudas_Clientes.jasper";
        Integer parametro11 = Integer.valueOf(txtparametrocliente.getText());
        jasper.runReporte_parametro(ubicacionn, "idcliente", parametro11);
    }//GEN-LAST:event_btnInforme2ActionPerformed

    private void btnFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFechaActionPerformed
        try {
            // TODO add your handling code here:
            Fecha = fechadesde.getCalendar();
            Fecha.setTime(fechadesde.getDate());
            String dia = String.valueOf(Fecha.get(Calendar.DATE));
            String mes = String.valueOf(Fecha.get(Calendar.MONTH) + 1);
            String anio = String.valueOf(Fecha.get(Calendar.YEAR));
            String date1 =  dia + "-" + mes + "-" + anio;
            
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            //String dateInString = date1;
            
            Date datef1 = formatter.parse(date1);
            // *********************************
            Fechap = fechahasta.getCalendar();
            Fechap.setTime(fechahasta.getDate());
            String diaa = String.valueOf(Fechap.get(Calendar.DATE));
            String mess = String.valueOf(Fechap.get(Calendar.MONTH) + 1);
            String anioo = String.valueOf(Fechap.get(Calendar.YEAR));
            String date2 =  diaa + "-" + mess + "-" + anioo ;
            
             SimpleDateFormat formatear = new SimpleDateFormat("dd-MM-yyyy");
            //String dateInString = date1;
            
            Date datef2 = formatear.parse(date2);
            //  **********************************/
            
            String ubicacionn = "/reportes/Informe_Caja.jasper";
            //String parametro11=String.valueOf(fechadesde.get(Calendar.DATE));
            //  String parametro12=(fechahasta.getText());
            jasper.runReporte_parametrofecha(ubicacionn, "fechainicio", "fechacierre", datef1, datef2);
        } catch (ParseException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btnFechaActionPerformed

    private void btnInventarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInventarioActionPerformed
        // TODO add your handling code here:
       // montogastos();

        //caja.setSize(596, 387);
        montoentrada();
        montogastos();
        montosalida();

        if (caja() == 1) {

            apertura.setVisible(false);
            cierre.setModal(true);
            cierre.setSize(592, 387);
            cierre.setLocationRelativeTo(this);
            cierre.setVisible(true);
            // txtCierreCaja.setEnabled(true);
            // txtapertura.setEnabled(false);
            // montoapertura();
        } else {
            if (caja() == 0) {

                cierre.setVisible(false);
                apertura.setModal(true);
                apertura.setSize(390, 350);
                apertura.setLocationRelativeTo(this);
                apertura.setVisible(true);
                // txtCierreCaja.setEnabled(false);
                // txtapertura.setEnabled(true);
                montoapertura();

            }

        }

    }//GEN-LAST:event_btnInventarioActionPerformed

    private void txtContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtContraseñaActionPerformed
        // TODO add your handling code here:
        login();
    }//GEN-LAST:event_txtContraseñaActionPerformed

    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        // TODO add your handling code here:
        JDialog4 = new Departamento();
        JDialog4.setLocationRelativeTo(this);
        JDialog4.setModal(true);
        JDialog4.setVisible(true);
    }//GEN-LAST:event_jMenuItem12ActionPerformed

    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        // TODO add your handling code here:
        JDialog5 = new Ciudad();
        JDialog5.setLocationRelativeTo(this);
        JDialog5.setModal(true);
        JDialog5.setVisible(true);
    }//GEN-LAST:event_jMenuItem13ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        // TODO add your handling code here:
        JDialog6 = new Region();
        JDialog6.setLocationRelativeTo(this);
        JDialog6.setModal(true);
        JDialog6.setVisible(true);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void btnRecetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecetasActionPerformed
        // TODO add your handling code here:
        JDialog7 = new Recetas();
        JDialog7.setLocationRelativeTo(this);
        JDialog7.setModal(false);
        JDialog7.setVisible(true);
    }//GEN-LAST:event_btnRecetasActionPerformed

    private void btnProduccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProduccionActionPerformed
        // TODO add your handling code here:
        JDialog8 = new Produccion();
        JDialog8.setLocationRelativeTo(this);
        JDialog8.setModal(true);
        JDialog8.setVisible(true);
    }//GEN-LAST:event_btnProduccionActionPerformed

    private void txtComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtComprasActionPerformed
        // TODO add your handling code here:
        JDialog9 = new FacturaCompra();
        JDialog9.setLocationRelativeTo(this);
        JDialog9.setModal(true);
        JDialog9.setVisible(true);
    }//GEN-LAST:event_txtComprasActionPerformed

    private void peidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_peidoActionPerformed
        // TODO add your handling code here:
        JDPedidoss = new PedidoClienteDos(this, true);
        JDPedidoss.setLocationRelativeTo(this);
        JDPedidoss.setVisible(true);
    }//GEN-LAST:event_peidoActionPerformed

    private void btnFacturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFacturarActionPerformed
        // TODO add your handling code here:
        JDFacturaVenta = new FacturaVenta(this, true);
        JDFacturaVenta.setLocationRelativeTo(this);
        JDFacturaVenta.setVisible(true);

    }//GEN-LAST:event_btnFacturarActionPerformed

    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        // TODO add your handling code here:
        JDPedidosList = new ListaPedidos(this, true);
        JDPedidosList.setVisible(true);
        JDPedidosList.setLocationRelativeTo(this);
    }//GEN-LAST:event_jMenuItem14ActionPerformed

    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        // TODO add your handling code here:
        JDAuditoria = new Auditoria(this, false);
        JDAuditoria.setVisible(true);
        JDAuditoria.setLocationRelativeTo(this);
    }//GEN-LAST:event_jMenuItem15ActionPerformed

    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        // TODO add your handling code here:
        jasper.runReporte("/reportes/AuditoriaGeneralEmpleado.jasper");
    }//GEN-LAST:event_jMenuItem16ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String ubicacion = "/reportes/Recetas.jasper";
        String parametro11 = (txtNombreReceta.getText());
        System.out.println(parametro11);
        jasper.runReporte_parametroSting(ubicacion, "producto", parametro11);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        // TODO add your handling code here:
        jasper.runReporte("/reportes/ListaPedidoGeneral.jasper");
    }//GEN-LAST:event_jMenuItem18ActionPerformed

    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        // TODO add your handling code here:
        RespaldarBD copiasegura = new RespaldarBD(this, true);
        copiasegura.setVisible(true);
    }//GEN-LAST:event_jMenuItem19ActionPerformed

    private void jMenuItem20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem20ActionPerformed
        // TODO add your handling code here:
        RestaurarBD restore = new RestaurarBD(this, true);
        restore.setVisible(true);
    }//GEN-LAST:event_jMenuItem20ActionPerformed

    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        // TODO add your handling code here:
        parametroreceta.setModal(false);
        parametroreceta.setSize(400, 400);
        parametroreceta.setLocationRelativeTo(this);
        parametroreceta.setVisible(true);
    }//GEN-LAST:event_jMenuItem17ActionPerformed

    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        // TODO add your handling code here:
        // informecaja.setModal(true);
        informecaja.setSize(400, 400);
        informecaja.setLocationRelativeTo(this);
        informecaja.setVisible(true);
    }//GEN-LAST:event_jMenuItem11ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        // TODO add your handling code here:
        jasper.runReporte("/reportes/Departamento.jasper");
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        // TODO add your handling code here:
        //        parametrocliente.setModal(true);
        //        parametrocliente.setSize(380, 100);
        //        parametrocliente.setLocationRelativeTo(this);
        //        parametrocliente.setVisible(true);
        jasper.runReporte("/reportes/Region.jasper");
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        // TODO add your handling code here:
        jasper.runReporte("/reportes/Pagos_Proveedor.jasper");
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        // TODO add your handling code here:
        jasper.runReporte("/reportes/StockBaja.jasper");
    }//GEN-LAST:event_jMenuItem10ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        // TODO add your handling code here:
        jasper.runReporte("/reportes/CajaSimple.jasper");
    }//GEN-LAST:event_jMenuItem9ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:

        parametrofac.setModal(true);
        parametrofac.setSize(380, 100);
        parametrofac.setLocationRelativeTo(this);
        parametrofac.setVisible(true);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        // TODO add your handling code here:
        jasper.runReporte("/reportes/CiudadOriental.jasper");
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        // TODO add your handling code here:
        jasper.runReporte("/reportes/Productos.jasper");
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem21ActionPerformed
        // TODO add your handling code here:
         jasper.runReporte("/reportes/AperturaCaja.jasper");
    }//GEN-LAST:event_jMenuItem21ActionPerformed

    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        // TODO add your handling code here:
         jasper.runReporte("/reportes/SalidaCaja.jasper");
    }//GEN-LAST:event_jMenuItem22ActionPerformed

    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        // TODO add your handling code here:
        informeCompra.setSize(400, 400);
        informeCompra.setLocationRelativeTo(this);
        informeCompra.setVisible(true);
         //jasper.runReporte("/reportes/MontoFacturaCompra.jasper");
    }//GEN-LAST:event_jMenuItem23ActionPerformed

    private void jMenuItem24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem24ActionPerformed
        // TODO add your handling code here:
        informeventa.setSize(400, 400);
        informeventa.setLocationRelativeTo(this);
        informeventa.setVisible(true);
         //jasper.runReporte("/reportes/MontoFacturaVenta.jasper");
    }//GEN-LAST:event_jMenuItem24ActionPerformed

    private void btnFecha2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecha2ActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            Fecha = fechadesde2.getCalendar();
            Fecha.setTime(fechadesde2.getDate());
            String dia = String.valueOf(Fecha.get(Calendar.DATE));
            String mes = String.valueOf(Fecha.get(Calendar.MONTH) + 1);
            String anio = String.valueOf(Fecha.get(Calendar.YEAR));
            String date1 =  dia + "-" + mes + "-" + anio;
            
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            //String dateInString = date1;
            
            Date datef1 = formatter.parse(date1);
            // *********************************
            Fechap = fechahasta2.getCalendar();
            Fechap.setTime(fechahasta2.getDate());
            String diaa = String.valueOf(Fechap.get(Calendar.DATE));
            String mess = String.valueOf(Fechap.get(Calendar.MONTH) + 1);
            String anioo = String.valueOf(Fechap.get(Calendar.YEAR));
            String date2 =  diaa + "-" + mess + "-" + anioo ;
            
             SimpleDateFormat formatear = new SimpleDateFormat("dd-MM-yyyy");
            //String dateInString = date1;
            
            Date datef2 = formatear.parse(date2);
            //  **********************************/
            
            String ubicacionn = "/reportes/MontoFacturaVenta.jasper";
            //String parametro11=String.valueOf(fechadesde.get(Calendar.DATE));
            //  String parametro12=(fechahasta.getText());
            jasper.runReporte_parametrofecha(ubicacionn, "fechaUno", "fechaDos", datef1, datef2);
        } catch (ParseException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }

        
    }//GEN-LAST:event_btnFecha2ActionPerformed

    private void btnFecha1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFecha1ActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            Fecha = fechadesde1.getCalendar();
            Fecha.setTime(fechadesde1.getDate());
            String dia = String.valueOf(Fecha.get(Calendar.DATE));
            String mes = String.valueOf(Fecha.get(Calendar.MONTH) + 1);
            String anio = String.valueOf(Fecha.get(Calendar.YEAR));
            String date1 =  dia + "-" + mes + "-" + anio;
            
            SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
            //String dateInString = date1;
            
            Date datef1 = formatter.parse(date1);
            // *********************************
            Fechap = fechahasta1.getCalendar();
            Fechap.setTime(fechahasta1.getDate());
            String diaa = String.valueOf(Fechap.get(Calendar.DATE));
            String mess = String.valueOf(Fechap.get(Calendar.MONTH) + 1);
            String anioo = String.valueOf(Fechap.get(Calendar.YEAR));
            String date2 =  diaa + "-" + mess + "-" + anioo ;
            
             SimpleDateFormat formatear = new SimpleDateFormat("dd-MM-yyyy");
            //String dateInString = date1;
            
            Date datef2 = formatear.parse(date2);
            //  **********************************/
            
            String ubicacionn = "/reportes/MontoFacturaCompra.jasper";
            //String parametro11=String.valueOf(fechadesde.get(Calendar.DATE));
            //  String parametro12=(fechahasta.getText());
            jasper.runReporte_parametrofecha(ubicacionn, "fechaUno", "fechaDos", datef1, datef2);
        } catch (ParseException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btnFecha1ActionPerformed

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
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        //tema para el sistema usando substance
        Principal.setDefaultLookAndFeelDecorated(true);
        SubstanceLookAndFeel.setSkin("org.jvnet.substance.skin.EmeraldDuskSkin");/*Skin*/

        SubstanceLookAndFeel.setCurrentTheme("org.jvnet.substance.theme.SubstanceLimeGreenTheme");/*Tema*/


        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu MenuAuditoria;
    private javax.swing.JMenuItem MenuRegistro;
    private javax.swing.JPanel PanelInferior;
    private javax.swing.JPanel PanelSuperior;
    private javax.swing.JDialog apertura;
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnArticulos;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnClientes;
    private javax.swing.JButton btnEntradas;
    private javax.swing.JButton btnFacturar;
    private javax.swing.JButton btnFecha;
    private javax.swing.JButton btnFecha1;
    private javax.swing.JButton btnFecha2;
    private javax.swing.JButton btnInforme;
    private javax.swing.JButton btnInforme1;
    private javax.swing.JButton btnInforme2;
    private javax.swing.JButton btnInventario;
    private javax.swing.JButton btnMovimiento;
    private javax.swing.JButton btnMovimiento1;
    private javax.swing.JButton btnNuevoPedido;
    private javax.swing.JButton btnProduccion;
    private javax.swing.JButton btnProveedores;
    private javax.swing.JButton btnRecetas;
    private javax.swing.JDialog cierre;
    private javax.swing.JTextField fecha;
    private com.toedter.calendar.JDateChooser fechadesde;
    private com.toedter.calendar.JDateChooser fechadesde1;
    private com.toedter.calendar.JDateChooser fechadesde2;
    private com.toedter.calendar.JDateChooser fechahasta;
    private com.toedter.calendar.JDateChooser fechahasta1;
    private com.toedter.calendar.JDateChooser fechahasta2;
    private com.toedter.calendar.JDateChooser fechainicio;
    private javax.swing.JDialog informeCompra;
    private javax.swing.JDialog informecaja;
    private javax.swing.JDialog informeventa;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem24;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JSplitPane jSplitPane2;
    private javax.swing.JMenu jmArchivo;
    private javax.swing.JMenuItem jmSalir;
    private javax.swing.JDialog login;
    private javax.swing.JMenuItem mNuevaCompra;
    private javax.swing.JPanel panelBotones;
    private javax.swing.JDialog parametrocliente;
    private javax.swing.JDialog parametrofac;
    private javax.swing.JDialog parametroreceta;
    private javax.swing.JDialog parametrorecibo;
    private javax.swing.JButton peido;
    private javax.swing.JTextField txtCierre;
    private javax.swing.JTextField txtCierreCaja;
    private javax.swing.JButton txtCompras;
    private javax.swing.JPasswordField txtContraseña;
    private javax.swing.JTextField txtDiferencia;
    private javax.swing.JTextField txtGastos;
    private javax.swing.JTextField txtIdCaja;
    private javax.swing.JTextField txtNombreReceta;
    private javax.swing.JTextField txtSalida;
    private javax.swing.JButton txtSalir;
    private javax.swing.JButton txtSalir1;
    private javax.swing.JTextField txtUsuario;
    private javax.swing.JTextField txtapertura;
    private javax.swing.JTextField txtapertura1;
    private javax.swing.JTextField txtentrada;
    private javax.swing.JTextField txtentrada1;
    private javax.swing.JTextField txtparametro;
    private javax.swing.JTextField txtparametro1;
    private javax.swing.JTextField txtparametrocliente;
    private javax.swing.JTextField txtsi;
    // End of variables declaration//GEN-END:variables
}
