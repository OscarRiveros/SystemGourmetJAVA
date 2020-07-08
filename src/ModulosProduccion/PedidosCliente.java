/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModulosProduccion;

import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * @author USER
 */
public class PedidosCliente extends javax.swing.JDialog {

    /**
     * Creates new form PedidosCliente
     */
    double totalBurger;//total hamburguesas
    double totalPizza;
    double totalDrinks;
    double total;
    double[] sumPizza = new double[30];
    double[] sumDrinks = new double[8];
    int pizzaQty1 = 0;
    double subSum1 = 0;
    double subSum2 = 0;
    double subSum3 = 0;
    double subSum4 = 0;
    double subSum5 = 0;
    double subSum6 = 0;
    double subSum7 = 0;
    double subSum8 = 0;
    double subSum9 = 0;
    double subSum10 = 0;
    double subSum11 = 0;
    double subSum12 = 0;
    int qty = 0;
    int qty1 = 0;
    int qty2 = 0;
    int qty3 = 0;
    int qty4 = 0;
    int qty5 = 0;
    int qty6 = 0;
    int qty7 = 0;
    int qty8 = 0;
    int qty9 = 0;
    int qty10 = 0;
    int qty11 = 0;
    
    public PedidosCliente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        imageRescaling();
    }
    
    public void total(){
        total = totalBurger + totalPizza + totalDrinks;
        totalPayableLabel.setText(String.valueOf(total));
    }
    
    public void imageRescaling(){
        
        
        //imagenes de hamburguesas
        ImageIcon burger1 = new ImageIcon(getClass().getResource("/Images/hamburger.png"));
        Image img1 = burger1.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        burger_1.setIcon(new ImageIcon(img1));
        
        ImageIcon burger2 = new ImageIcon(getClass().getResource("/Images/bacon-burger.png"));
        Image img2 = burger2.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        burger_2.setIcon(new ImageIcon(img2));
        
        ImageIcon burger3 = new ImageIcon(getClass().getResource("/Images/BbqChickenSandwich.png"));
        Image img3 = burger3.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        burger_3.setIcon(new ImageIcon(img3));
        
        ImageIcon burger4 = new ImageIcon(getClass().getResource("/Images/bbqPulledPork.png"));
        Image img4 = burger4.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        burger_4.setIcon(new ImageIcon(img4));
        
        ImageIcon burger5 = new ImageIcon(getClass().getResource("/Images/chicken&Fish.png"));
        Image img5 = burger5.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        burger_5.setIcon(new ImageIcon(img5));
        
        ImageIcon burger6 = new ImageIcon(getClass().getResource("/Images/bigMac.png"));
        Image img6 = burger6.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        burger_6.setIcon(new ImageIcon(img6));
        
        ImageIcon burger7 = new ImageIcon(getClass().getResource("/Images/grilledChickenSandwich.png"));
        Image img7 = burger7.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        burger_7.setIcon(new ImageIcon(img7));
        
        ImageIcon burger8 = new ImageIcon(getClass().getResource("/Images/grilledVege.png"));
        Image img8 = burger8.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        burger_8.setIcon(new ImageIcon(img8));
        
        ImageIcon burger9 = new ImageIcon(getClass().getResource("/Images/opener-burger.png"));
        Image img9 = burger9.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        burger_9.setIcon(new ImageIcon(img9));
        
        ImageIcon burger10 = new ImageIcon(getClass().getResource("/Images/premiumCrispyChicken.png"));
        Image img10 = burger10.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        burger_10.setIcon(new ImageIcon(img10));
        
        ImageIcon burger11 = new ImageIcon(getClass().getResource("/Images/rustlersFlameGrilledChickenSandwich.png"));
        Image img11 = burger11.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        burger_11.setIcon(new ImageIcon(img11));
        
        ImageIcon burger12 = new ImageIcon(getClass().getResource("/Images/chikenBurger.png"));
        Image img12 = burger12.getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);
        burger_12.setIcon(new ImageIcon(img12));
        

//imagenes pitzas
        ImageIcon pizz1 = new ImageIcon(getClass().getResource("/Images/margaritaPizza.png"));
        Image pizImg1 = pizz1.getImage().getScaledInstance(85, 85, Image.SCALE_SMOOTH);
        pizza1.setIcon(new ImageIcon(pizImg1));
        
        ImageIcon pizz2 = new ImageIcon(getClass().getResource("/Images/tikaBoti.png"));
        Image pizImg2 = pizz2.getImage().getScaledInstance(85, 85, Image.SCALE_SMOOTH);
        pizza2.setIcon(new ImageIcon(pizImg2));
        
        ImageIcon pizz3 = new ImageIcon(getClass().getResource("/Images/cheesePizza.png"));
        Image pizImg3 = pizz3.getImage().getScaledInstance(85, 85, Image.SCALE_SMOOTH);
        pizza3.setIcon(new ImageIcon(pizImg3));
        
        ImageIcon pizz4 = new ImageIcon(getClass().getResource("/Images/pizza_PNG7149.png"));
        Image pizImg4 = pizz4.getImage().getScaledInstance(85, 85, Image.SCALE_SMOOTH);
//        pizza4.setIcon(new ImageIcon(pizImg4));
        
        ImageIcon pizz5 = new ImageIcon(getClass().getResource("/Images/pizza_PNG7151.png"));
        Image pizImg5 = pizz5.getImage().getScaledInstance(85, 85, Image.SCALE_SMOOTH);
//        pizza5.setIcon(new ImageIcon(pizImg5));
        
        ImageIcon pizz6 = new ImageIcon(getClass().getResource("/Images/pizza2.png"));
        Image pizImg6 = pizz6.getImage().getScaledInstance(85, 85, Image.SCALE_SMOOTH);
        pizza6.setIcon(new ImageIcon(pizImg6));
        
        ImageIcon pizz7 = new ImageIcon(getClass().getResource("/Images/pizza3.png"));
        Image pizImg7 = pizz7.getImage().getScaledInstance(85, 85, Image.SCALE_SMOOTH);
        pizza7.setIcon(new ImageIcon(pizImg7));
        
        ImageIcon pizz8 = new ImageIcon(getClass().getResource("/Images/pizza4.png"));
        Image pizImg8 = pizz8.getImage().getScaledInstance(85, 85, Image.SCALE_SMOOTH);
        pizza8.setIcon(new ImageIcon(pizImg8));
        
        ImageIcon pizz9 = new ImageIcon(getClass().getResource("/Images/pizza5.png"));
        Image pizImg9 = pizz9.getImage().getScaledInstance(85, 85, Image.SCALE_SMOOTH);
//        pizza9.setIcon(new ImageIcon(pizImg9));
        
        ImageIcon pizz10 = new ImageIcon(getClass().getResource("/Images/pizza6.png"));
        Image pizImg10 = pizz10.getImage().getScaledInstance(85, 85, Image.SCALE_SMOOTH);
//        pizza10.setIcon(new ImageIcon(pizImg10));
        
        ImageIcon cocaCola = new ImageIcon(getClass().getResource("/Images/coca-cola-logo-png-22.png"));
        Image cocaImg = cocaCola.getImage().getScaledInstance(270, 130, Image.SCALE_SMOOTH);
        jLabel21.setIcon(new ImageIcon(cocaImg));
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        burger13000 = new javax.swing.JCheckBox();
        burger_1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jLabel7 = new javax.swing.JLabel();
        burger_2 = new javax.swing.JLabel();
        burger18000 = new javax.swing.JCheckBox();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jSpinner2 = new javax.swing.JSpinner();
        burger_3 = new javax.swing.JLabel();
        burger22000 = new javax.swing.JCheckBox();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jSpinner3 = new javax.swing.JSpinner();
        burger_4 = new javax.swing.JLabel();
        burger25000 = new javax.swing.JCheckBox();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jSpinner4 = new javax.swing.JSpinner();
        burger_5 = new javax.swing.JLabel();
        burger300 = new javax.swing.JCheckBox();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jSpinner5 = new javax.swing.JSpinner();
        burger_6 = new javax.swing.JLabel();
        burger425 = new javax.swing.JCheckBox();
        jLabel22 = new javax.swing.JLabel();
        jSpinner6 = new javax.swing.JSpinner();
        jLabel23 = new javax.swing.JLabel();
        burger27500 = new javax.swing.JCheckBox();
        burger_7 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jSpinner7 = new javax.swing.JSpinner();
        burger28000 = new javax.swing.JCheckBox();
        burger_8 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jSpinner8 = new javax.swing.JSpinner();
        burger525 = new javax.swing.JCheckBox();
        burger_9 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jSpinner9 = new javax.swing.JSpinner();
        burger29500 = new javax.swing.JCheckBox();
        burger_10 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jSpinner10 = new javax.swing.JSpinner();
        burger510 = new javax.swing.JCheckBox();
        burger_11 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jSpinner11 = new javax.swing.JSpinner();
        burger540 = new javax.swing.JCheckBox();
        burger_12 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jSpinner12 = new javax.swing.JSpinner();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        subSumLabel = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        checkPizza2800 = new javax.swing.JCheckBox();
        pizza1 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        s2800 = new javax.swing.JSpinner();
        m2800 = new javax.swing.JSpinner();
        l2800 = new javax.swing.JSpinner();
        checkPizza750 = new javax.swing.JCheckBox();
        pizza2 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        s37500 = new javax.swing.JSpinner();
        m39500 = new javax.swing.JSpinner();
        jLabel27 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        l37500 = new javax.swing.JSpinner();
        checkPizza850 = new javax.swing.JCheckBox();
        pizza3 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        s10500 = new javax.swing.JSpinner();
        m15000 = new javax.swing.JSpinner();
        jLabel39 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        l850 = new javax.swing.JSpinner();
        jPanel6 = new javax.swing.JPanel();
        jLabel71 = new javax.swing.JLabel();
        totalPizzaLabel = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        pizza6 = new javax.swing.JLabel();
        checkPizza650 = new javax.swing.JCheckBox();
        jLabel52 = new javax.swing.JLabel();
        jLabel53 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        l28000 = new javax.swing.JSpinner();
        m24000 = new javax.swing.JSpinner();
        s16000 = new javax.swing.JSpinner();
        checkPizza830 = new javax.swing.JCheckBox();
        pizza7 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        l25000 = new javax.swing.JSpinner();
        m22000 = new javax.swing.JSpinner();
        s18000 = new javax.swing.JSpinner();
        checkPizza950 = new javax.swing.JCheckBox();
        pizza8 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        s19000 = new javax.swing.JSpinner();
        jLabel61 = new javax.swing.JLabel();
        jLabel62 = new javax.swing.JLabel();
        l28000m = new javax.swing.JSpinner();
        m24000i = new javax.swing.JSpinner();
        jPanel9 = new javax.swing.JPanel();
        jCheckBox1 = new javax.swing.JCheckBox();
        cokeRegular = new javax.swing.JSpinner();
        jCheckBox2 = new javax.swing.JCheckBox();
        pepsiRegular = new javax.swing.JSpinner();
        jCheckBox3 = new javax.swing.JCheckBox();
        spriteRegular = new javax.swing.JSpinner();
        jCheckBox5 = new javax.swing.JCheckBox();
        coke1500 = new javax.swing.JSpinner();
        jCheckBox6 = new javax.swing.JCheckBox();
        pepsi1500 = new javax.swing.JSpinner();
        jCheckBox7 = new javax.swing.JCheckBox();
        sprite1500 = new javax.swing.JSpinner();
        jLabel21 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel47 = new javax.swing.JLabel();
        totalDrinksLabel = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        totalPayableLabel = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        changeLabel = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        amountPaidField = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        noteLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jTabbedPane1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTabbedPane1.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N

        burger13000.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                burger13000ActionPerformed(evt);
            }
        });

        burger_1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                burger_1MouseClicked(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Grand Angus");

        jSpinner1.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        jSpinner1.setEnabled(false);
        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner1StateChanged(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel7.setText("QTY:");

        burger18000.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                burger18000ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Bacon Burger");

        jLabel11.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel11.setText("QTY:");

        jSpinner2.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        jSpinner2.setEnabled(false);
        jSpinner2.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner2StateChanged(evt);
            }
        });

        burger22000.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                burger22000ActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("BBQ Veggie");

        jLabel14.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel14.setText("QTY:");

        jSpinner3.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        jSpinner3.setEnabled(false);
        jSpinner3.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner3StateChanged(evt);
            }
        });

        burger25000.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                burger25000ActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel16.setText("Grilled Chicken Rs.500");

        jLabel17.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel17.setText("QTY:");

        jSpinner4.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        jSpinner4.setEnabled(false);
        jSpinner4.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner4StateChanged(evt);
            }
        });

        burger300.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                burger300ActionPerformed(evt);
            }
        });

        jLabel19.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel19.setText("Chicken Veggie");

        jLabel20.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel20.setText("QTY:");

        jSpinner5.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        jSpinner5.setEnabled(false);
        jSpinner5.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner5StateChanged(evt);
            }
        });

        burger425.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                burger425ActionPerformed(evt);
            }
        });

        jLabel22.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel22.setText("Big Mac");

        jSpinner6.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        jSpinner6.setEnabled(false);
        jSpinner6.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner6StateChanged(evt);
            }
        });

        jLabel23.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel23.setText("QTY:");

        burger27500.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                burger27500ActionPerformed(evt);
            }
        });

        jLabel25.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel25.setText("Big Fish");

        jLabel26.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel26.setText("QTY:");

        jSpinner7.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        jSpinner7.setEnabled(false);
        jSpinner7.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner7StateChanged(evt);
            }
        });

        burger28000.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                burger28000ActionPerformed(evt);
            }
        });

        jLabel28.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel28.setText("Double Stack");

        jLabel29.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel29.setText("QTY:");

        jSpinner8.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        jSpinner8.setEnabled(false);
        jSpinner8.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner8StateChanged(evt);
            }
        });

        burger525.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                burger525ActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel31.setText("Hawaiian");

        jLabel32.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel32.setText("QTY:");

        jSpinner9.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        jSpinner9.setEnabled(false);
        jSpinner9.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner9StateChanged(evt);
            }
        });

        burger29500.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                burger29500ActionPerformed(evt);
            }
        });

        jLabel34.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel34.setText("Double BBQ");

        jLabel35.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel35.setText("QTY:");

        jSpinner10.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        jSpinner10.setEnabled(false);
        jSpinner10.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner10StateChanged(evt);
            }
        });

        burger510.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                burger510ActionPerformed(evt);
            }
        });

        jLabel37.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Hash & Cheese");

        jLabel38.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel38.setText("QTY:");

        jSpinner11.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        jSpinner11.setEnabled(false);
        jSpinner11.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner11StateChanged(evt);
            }
        });

        burger540.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                burger540ActionPerformed(evt);
            }
        });

        jLabel40.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel40.setText("Grilled Chicken Rs.500");

        jLabel41.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        jLabel41.setText("QTY:");

        jSpinner12.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        jSpinner12.setEnabled(false);
        jSpinner12.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jSpinner12StateChanged(evt);
            }
        });

        jPanel5.setBackground(new java.awt.Color(0, 204, 51));

        jLabel6.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("SUB-TOTAL");

        subSumLabel.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        subSumLabel.setForeground(new java.awt.Color(255, 255, 255));
        subSumLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        subSumLabel.setText("0.0");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 456, Short.MAX_VALUE)
                .addComponent(subSumLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(subSumLabel))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(burger13000)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(burger_1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(burger27500)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(burger_7, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel26)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner7, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(burger18000)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(burger_2, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(burger28000)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(burger_8, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel29)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner8, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(55, 55, 55)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(burger22000)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(burger_3, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel14)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(burger525)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(burger_9, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel32)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner9, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(122, 122, 122)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(burger25000)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(burger_4, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(burger29500)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(burger_10, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel35)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner10, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(72, 72, 72)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(burger300)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(burger_5, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner5, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(burger510)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(burger_11, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel38)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jSpinner11, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(82, 82, 82))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(burger425)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(burger_6, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(burger540)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(burger_12, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel41)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSpinner12, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel40, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(burger_6, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(burger425)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSpinner6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(burger_5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(burger300)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel19)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSpinner5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(burger_4, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(burger25000)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSpinner4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(burger_3, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(burger22000)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSpinner3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(burger_2, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(burger18000)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(burger13000))
                            .addComponent(burger_1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 81, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(burger_12, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addComponent(burger540)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel40)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jSpinner12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel41))
                        .addGap(35, 35, 35))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(burger_11, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addComponent(burger510)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel37)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jSpinner11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel38)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(burger_10, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addComponent(burger29500)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel34)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jSpinner10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel35)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(burger_9, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addComponent(burger525)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel31)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jSpinner9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel32)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(burger_8, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addComponent(burger28000)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jSpinner8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel29)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(burger_7, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGap(35, 35, 35)
                                        .addComponent(burger27500)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel25)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jSpinner7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel26))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Burgers", jPanel2);

        checkPizza2800.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkPizza2800ActionPerformed(evt);
            }
        });

        jLabel12.setText("S:");

        jLabel15.setText("M:");

        jLabel18.setText("L:");

        s2800.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        s2800.setEnabled(false);
        s2800.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                s2800StateChanged(evt);
            }
        });

        m2800.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        m2800.setEnabled(false);
        m2800.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                m2800StateChanged(evt);
            }
        });

        l2800.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        l2800.setEnabled(false);
        l2800.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                l2800StateChanged(evt);
            }
        });

        checkPizza750.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkPizza750ActionPerformed(evt);
            }
        });

        jLabel24.setText("S:");

        s37500.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        s37500.setEnabled(false);
        s37500.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                s37500StateChanged(evt);
            }
        });

        m39500.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        m39500.setEnabled(false);
        m39500.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                m39500StateChanged(evt);
            }
        });

        jLabel27.setText("M:");

        jLabel30.setText("L:");

        l37500.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        l37500.setEnabled(false);
        l37500.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                l37500StateChanged(evt);
            }
        });

        checkPizza850.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkPizza850ActionPerformed(evt);
            }
        });

        jLabel36.setText("S:");

        s10500.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        s10500.setEnabled(false);
        s10500.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                s10500StateChanged(evt);
            }
        });

        m15000.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        m15000.setEnabled(false);
        m15000.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                m15000StateChanged(evt);
            }
        });

        jLabel39.setText("M:");

        jLabel42.setText("L:");

        l850.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        l850.setEnabled(false);
        l850.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                l850StateChanged(evt);
            }
        });

        jPanel6.setBackground(new java.awt.Color(0, 204, 102));

        jLabel71.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(255, 255, 255));
        jLabel71.setText("SUB-TOTAL");

        totalPizzaLabel.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        totalPizzaLabel.setForeground(new java.awt.Color(255, 255, 255));
        totalPizzaLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totalPizzaLabel.setText("0.0");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel71)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 506, Short.MAX_VALUE)
                .addComponent(totalPizzaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel71)
                    .addComponent(totalPizzaLabel))
                .addContainerGap())
        );

        checkPizza650.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkPizza650ActionPerformed(evt);
            }
        });

        jLabel52.setText("S:");

        jLabel53.setText("M:");

        jLabel54.setText("L:");

        l28000.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        l28000.setEnabled(false);
        l28000.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                l28000StateChanged(evt);
            }
        });

        m24000.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        m24000.setEnabled(false);
        m24000.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                m24000StateChanged(evt);
            }
        });

        s16000.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        s16000.setEnabled(false);
        s16000.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                s16000StateChanged(evt);
            }
        });

        checkPizza830.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkPizza830ActionPerformed(evt);
            }
        });

        jLabel56.setText("S:");

        jLabel57.setText("M:");

        jLabel58.setText("L:");

        l25000.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        l25000.setEnabled(false);
        l25000.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                l25000StateChanged(evt);
            }
        });

        m22000.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        m22000.setEnabled(false);
        m22000.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                m22000StateChanged(evt);
            }
        });

        s18000.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        s18000.setEnabled(false);
        s18000.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                s18000StateChanged(evt);
            }
        });

        checkPizza950.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkPizza950ActionPerformed(evt);
            }
        });

        jLabel60.setText("S:");

        s19000.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        s19000.setEnabled(false);
        s19000.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                s19000StateChanged(evt);
            }
        });

        jLabel61.setText("M:");

        jLabel62.setText("L:");

        l28000m.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        l28000m.setEnabled(false);
        l28000m.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                l28000mStateChanged(evt);
            }
        });

        m24000i.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(0), Integer.valueOf(0), null, Integer.valueOf(1)));
        m24000i.setEnabled(false);
        m24000i.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                m24000iStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pizza6, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(checkPizza650)))
                .addGap(27, 27, 27)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel53)
                    .addComponent(jLabel54, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel52, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(m24000, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(l28000, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(s16000, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkPizza830)
                    .addComponent(pizza7, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jLabel57)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(m22000, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(l25000, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel10Layout.createSequentialGroup()
                            .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(s18000, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(pizza8, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel61)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(m24000i, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel10Layout.createSequentialGroup()
                                    .addComponent(jLabel62, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(l28000m, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel10Layout.createSequentialGroup()
                                    .addComponent(jLabel60, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(s19000, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(checkPizza950))
                .addContainerGap(53, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addComponent(checkPizza650)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pizza6, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(checkPizza830)
                    .addComponent(checkPizza950))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(0, 1, Short.MAX_VALUE)
                        .addComponent(pizza7, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(pizza8, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel52)
                            .addComponent(s16000, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(m24000, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel53))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel54)
                            .addComponent(l28000, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel56)
                                    .addComponent(s18000, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(m22000, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel57))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel58)
                                    .addComponent(l25000, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel60)
                                    .addComponent(s19000, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(m24000i, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel61))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel62)
                                    .addComponent(l28000m, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(checkPizza2800)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pizza1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(s2800, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel15)
                                    .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(l2800, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(m2800, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(checkPizza750)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pizza2, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel27)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(m39500, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(l37500, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(s37500, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkPizza850)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pizza3, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel39)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(m15000, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel42, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(l850, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(s10500, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(checkPizza2800))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(s2800, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(m2800, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel15))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18)
                                    .addComponent(l2800, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(pizza3, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel36)
                                    .addComponent(s10500, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(m15000, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel39))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel42)
                                    .addComponent(l850, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(checkPizza850))
                            .addComponent(pizza1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(4, 4, 4)
                                .addComponent(pizza2, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel24)
                                    .addComponent(s37500, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(m39500, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel27))
                                .addGap(12, 12, 12)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel30)
                                    .addComponent(l37500, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(checkPizza750)))))
                .addGap(18, 18, 18)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(83, 83, 83)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Pizza", jPanel3);

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Drinks", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("SansSerif", 0, 14), new java.awt.Color(102, 102, 102))); // NOI18N
        jPanel9.setForeground(new java.awt.Color(102, 102, 102));

        jCheckBox1.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jCheckBox1.setText("CocaColaRegular)");
        jCheckBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox1ActionPerformed(evt);
            }
        });

        cokeRegular.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        cokeRegular.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        cokeRegular.setEnabled(false);
        cokeRegular.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                cokeRegularStateChanged(evt);
            }
        });

        jCheckBox2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jCheckBox2.setText("Pepsi(Regular)");
        jCheckBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox2ActionPerformed(evt);
            }
        });

        pepsiRegular.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        pepsiRegular.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        pepsiRegular.setEnabled(false);
        pepsiRegular.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                pepsiRegularStateChanged(evt);
            }
        });

        jCheckBox3.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jCheckBox3.setText("Sprite(Regular)");
        jCheckBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox3ActionPerformed(evt);
            }
        });

        spriteRegular.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        spriteRegular.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        spriteRegular.setEnabled(false);
        spriteRegular.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spriteRegularStateChanged(evt);
            }
        });

        jCheckBox5.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jCheckBox5.setText("CocaCola(1.5 LTR)");
        jCheckBox5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox5ActionPerformed(evt);
            }
        });

        coke1500.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        coke1500.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        coke1500.setEnabled(false);
        coke1500.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                coke1500StateChanged(evt);
            }
        });

        jCheckBox6.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jCheckBox6.setText("Pepsi(1.5 LTR)");
        jCheckBox6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox6ActionPerformed(evt);
            }
        });

        pepsi1500.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        pepsi1500.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        pepsi1500.setEnabled(false);
        pepsi1500.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                pepsi1500StateChanged(evt);
            }
        });

        jCheckBox7.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        jCheckBox7.setText("Sprite(1.5 LTR)");
        jCheckBox7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jCheckBox7ActionPerformed(evt);
            }
        });

        sprite1500.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        sprite1500.setModel(new javax.swing.SpinnerNumberModel(Integer.valueOf(1), Integer.valueOf(1), null, Integer.valueOf(1)));
        sprite1500.setEnabled(false);
        sprite1500.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sprite1500StateChanged(evt);
            }
        });

        jPanel8.setBackground(new java.awt.Color(0, 204, 51));

        jLabel47.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(255, 255, 255));
        jLabel47.setText("SUB-TOTAL");

        totalDrinksLabel.setFont(new java.awt.Font("SansSerif", 1, 18)); // NOI18N
        totalDrinksLabel.setForeground(new java.awt.Color(255, 255, 255));
        totalDrinksLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totalDrinksLabel.setText("0.0");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel47)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(totalDrinksLabel)
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel47)
                    .addComponent(totalDrinksLabel))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(jCheckBox7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(sprite1500, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(jCheckBox6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(pepsi1500, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(jCheckBox5)
                                        .addGap(18, 18, 18)
                                        .addComponent(coke1500, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jCheckBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cokeRegular, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addComponent(jCheckBox2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pepsiRegular, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addComponent(jCheckBox3)
                                .addGap(44, 44, 44)
                                .addComponent(spriteRegular, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(73, 73, 73))))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox1)
                            .addComponent(cokeRegular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox2)
                            .addComponent(pepsiRegular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox3)
                            .addComponent(spriteRegular, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jCheckBox5)
                            .addComponent(coke1500, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pepsi1500, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCheckBox6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jCheckBox7)
                    .addComponent(sprite1500, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        totalPayableLabel.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        totalPayableLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        totalPayableLabel.setText("0.0");

        jLabel33.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel33.setText("Total a pagar");

        jLabel43.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel43.setText("Cambio");

        changeLabel.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        changeLabel.setText("0.0");

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel8.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        jLabel8.setText("Cantidad pagada");

        amountPaidField.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        amountPaidField.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        amountPaidField.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, new java.awt.Color(204, 204, 204), java.awt.Color.white, java.awt.Color.white));

        jButton1.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jButton1.setText("Cambio");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        noteLabel.setFont(new java.awt.Font("SansSerif", 0, 12)); // NOI18N
        noteLabel.setForeground(new java.awt.Color(255, 0, 51));
        noteLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(amountPaidField, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(noteLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(noteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(amountPaidField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33)
                    .addComponent(jLabel43))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totalPayableLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(changeLabel, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(22, 22, 22))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totalPayableLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel43, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(changeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 812, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(12, Short.MAX_VALUE)
                        .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(90, 90, 90)
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void burger13000ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_burger13000ActionPerformed
        // TODO add your handling code here:

        if(burger13000.isSelected()){
            jSpinner1.setEnabled(true);
            subSum1 = subSum1 + 13000;
            totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            subSumLabel.setText(String.valueOf(totalBurger));
        }
        else{
            jSpinner1.setEnabled(false);
            jSpinner1.setValue(1);
            subSum1 = 0;
            totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            subSumLabel.setText(String.valueOf(totalBurger));

        }
        total();
    }//GEN-LAST:event_burger13000ActionPerformed

    private void burger_1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_burger_1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_burger_1MouseClicked

    private void jSpinner1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner1StateChanged
        // TODO add your handling code here:
        double spinRate1 = 13000;
        if(burger13000.isSelected()){
            int preQty;
            preQty = (Integer)jSpinner1.getValue();
            if(preQty > qty){
                qty = preQty;
                subSum1 = spinRate1 * preQty;
                totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            }
            else if(preQty<=qty){
                qty = preQty;
                subSum1= spinRate1 * preQty;
                totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            }
            subSumLabel.setText(String.valueOf(totalBurger));
            total();
        }
        total();
    }//GEN-LAST:event_jSpinner1StateChanged

    private void burger18000ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_burger18000ActionPerformed
        // TODO add your handling code here:
        if(burger18000.isSelected()){
            jSpinner2.setEnabled(true);
            subSum2 = subSum2 + 18000;
            totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            subSumLabel.setText(String.valueOf(totalBurger));
        }
        else{
            jSpinner2.setEnabled(false);
            jSpinner2.setValue(1);
            subSum2 = 0;
            totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            subSumLabel.setText(String.valueOf(totalBurger));

        }
        total();
    }//GEN-LAST:event_burger18000ActionPerformed

    private void jSpinner2StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner2StateChanged
        // TODO add your handling code here:
        double spinRate2 = 18000;
        if(burger18000.isSelected()){
            int preQty1;
            preQty1 = (Integer)jSpinner2.getValue();
            if(preQty1 > qty1){
                qty1 = preQty1;
                subSum2 = spinRate2 * preQty1;
                totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            }
            else if(preQty1<=qty1){
                qty1 = preQty1;
                subSum2 = spinRate2 * preQty1;
                totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            }
            subSumLabel.setText(String.valueOf(totalBurger));
        }
        total();
    }//GEN-LAST:event_jSpinner2StateChanged

    private void burger22000ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_burger22000ActionPerformed
        // TODO add your handling code here:
        if(burger22000.isSelected()){
            jSpinner3.setEnabled(true);
            subSum3 = subSum3 + 22000;
            totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            subSumLabel.setText(String.valueOf(totalBurger));
        }
        else{
            jSpinner3.setEnabled(false);
            jSpinner3.setValue(1);
            subSum3 = 0;
            totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            subSumLabel.setText(String.valueOf(totalBurger));
        }
        total();
    }//GEN-LAST:event_burger22000ActionPerformed

    private void jSpinner3StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner3StateChanged
        // TODO add your handling code here:
        double spinRate3 = 22000;
        if(burger22000.isSelected()){
            int preQty1;
            preQty1 = (Integer)jSpinner3.getValue();
            if(preQty1 > qty2){
                qty2 = preQty1;
                subSum3 = spinRate3 * preQty1;
                totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            }
            else if(preQty1<=qty2){
                qty2 = preQty1;
                subSum3 = spinRate3 * preQty1;
                totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            }
            subSumLabel.setText(String.valueOf(totalBurger));
        }
        total();
    }//GEN-LAST:event_jSpinner3StateChanged

    private void burger25000ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_burger25000ActionPerformed
        // TODO add your handling code here:
        if(burger25000.isSelected()){
            jSpinner4.setEnabled(true);
            subSum4 = subSum4 + 25000;
            totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            subSumLabel.setText(String.valueOf(totalBurger));
        }
        else{
            jSpinner4.setEnabled(false);
            jSpinner4.setValue(1);
            subSum4 = 0;
            totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            subSumLabel.setText(String.valueOf(totalBurger));
        }
        total();
    }//GEN-LAST:event_burger25000ActionPerformed

    private void jSpinner4StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner4StateChanged
        // TODO add your handling code here:
        double spinRate4 = 25000;
        if(burger25000.isSelected()){
            int preQty1;
            preQty1 = (Integer)jSpinner4.getValue();
            if(preQty1 > qty3){
                qty3 = preQty1;
                subSum4 = spinRate4 * preQty1;
                totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            }
            else if(preQty1<=qty3){
                qty3 = preQty1;
                subSum4 = spinRate4 * preQty1;
                totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            }
            subSumLabel.setText(String.valueOf(totalBurger));
        }
        total();
    }//GEN-LAST:event_jSpinner4StateChanged

    private void burger300ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_burger300ActionPerformed
        // TODO add your handling code here:
        if(burger300.isSelected()){
            jSpinner5.setEnabled(true);
            subSum5 = subSum5 + 300;
            totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            subSumLabel.setText(String.valueOf(totalBurger));
        }
        else{
            jSpinner5.setEnabled(false);
            jSpinner5.setValue(1);
            subSum5 = 0;
            totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            subSumLabel.setText(String.valueOf(totalBurger));
        }
        total();
    }//GEN-LAST:event_burger300ActionPerformed

    private void jSpinner5StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner5StateChanged
        // TODO add your handling code here:
        double spinRate5 = 300;
        if(burger300.isSelected()){
            int preQty1;
            preQty1 = (Integer)jSpinner5.getValue();
            if(preQty1 > qty4){
                qty4 = preQty1;
                subSum5 = spinRate5 * preQty1;
                totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            }
            else if(preQty1<=qty4){
                qty4 = preQty1;
                subSum5 = spinRate5 * preQty1;
                totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            }
            subSumLabel.setText(String.valueOf(totalBurger));
        }
        total();
    }//GEN-LAST:event_jSpinner5StateChanged

    private void burger425ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_burger425ActionPerformed
        // TODO add your handling code here:
        if(burger425.isSelected()){
            jSpinner6.setEnabled(true);
            subSum6 = subSum6 + 425;
            totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            subSumLabel.setText(String.valueOf(totalBurger));
        }
        else{
            jSpinner6.setEnabled(false);
            jSpinner6.setValue(1);
            subSum6 = 0;
            totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            subSumLabel.setText(String.valueOf(totalBurger));
        }
        total();
    }//GEN-LAST:event_burger425ActionPerformed

    private void jSpinner6StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner6StateChanged
        // TODO add your handling code here:
        double spinRate6 = 425;
        if(burger425.isSelected()){
            int preQty1;
            preQty1 = (Integer)jSpinner6.getValue();
            if(preQty1 > qty5){
                qty5 = preQty1;
                subSum6 = spinRate6 * preQty1;
                totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            }
            else if(preQty1<=qty5){
                qty5 = preQty1;
                subSum6 = spinRate6 * preQty1;
                totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            }
            subSumLabel.setText(String.valueOf(totalBurger));
        }
        total();
    }//GEN-LAST:event_jSpinner6StateChanged

    private void burger27500ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_burger27500ActionPerformed
        // TODO add your handling code here:
        if(burger27500.isSelected()){
            jSpinner7.setEnabled(true);
            subSum7 = subSum7 + 27500;
            totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            subSumLabel.setText(String.valueOf(totalBurger));
        }
        else{
            jSpinner7.setEnabled(false);
            jSpinner7.setValue(1);
            subSum7 = 0;
            totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            subSumLabel.setText(String.valueOf(totalBurger));
        }
        total();
    }//GEN-LAST:event_burger27500ActionPerformed

    private void jSpinner7StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner7StateChanged
        // TODO add your handling code here:
        double spinRate7 = 27500;
        if(burger27500.isSelected()){
            int preQty1;
            preQty1 = (Integer)jSpinner7.getValue();
            if(preQty1 > qty6){
                qty6 = preQty1;
                subSum7 = spinRate7 * preQty1;
                totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            }
            else if(preQty1<=qty6){
                qty6 = preQty1;
                subSum7 = spinRate7 * preQty1;
                totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            }
            subSumLabel.setText(String.valueOf(totalBurger));
        }
        total();
    }//GEN-LAST:event_jSpinner7StateChanged

    private void burger28000ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_burger28000ActionPerformed
        // TODO add your handling code here:
        if(burger28000.isSelected()){
            jSpinner8.setEnabled(true);
            subSum8 = subSum8 + 28000;
            totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            subSumLabel.setText(String.valueOf(totalBurger));
        }
        else{
            jSpinner8.setEnabled(false);
            jSpinner8.setValue(1);
            subSum8 = 0;
            totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            subSumLabel.setText(String.valueOf(totalBurger));
        }
        total();
    }//GEN-LAST:event_burger28000ActionPerformed

    private void jSpinner8StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner8StateChanged
        // TODO add your handling code here:
        double spinRate8 = 28000;
        if(burger28000.isSelected()){
            int preQty1;
            preQty1 = (Integer)jSpinner8.getValue();
            if(preQty1 > qty7){
                qty7 = preQty1;
                subSum8 = spinRate8 * preQty1;
                totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            }
            else if(preQty1<=qty7){
                qty7 = preQty1;
                subSum8 = spinRate8 * preQty1;
                totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            }
            subSumLabel.setText(String.valueOf(totalBurger));
        }
        total();
    }//GEN-LAST:event_jSpinner8StateChanged

    private void burger525ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_burger525ActionPerformed
        // TODO add your handling code here:
        if(burger525.isSelected()){
            jSpinner9.setEnabled(true);
            subSum9 = subSum9 + 25000;
            totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            subSumLabel.setText(String.valueOf(totalBurger));
        }
        else{
            jSpinner9.setEnabled(false);
            jSpinner9.setValue(1);
            subSum9 = 0;
            totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            subSumLabel.setText(String.valueOf(totalBurger));
        }
        total();
    }//GEN-LAST:event_burger525ActionPerformed

    private void jSpinner9StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner9StateChanged
        // TODO add your handling code here:
        double spinRate9 = 25000;
        if(burger525.isSelected()){
            int preQty1;
            preQty1 = (Integer)jSpinner9.getValue();
            if(preQty1 > qty8){
                qty8 = preQty1;
                subSum9 = spinRate9 * preQty1;
                totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            }
            else if(preQty1<=qty8){
                qty8 = preQty1;
                subSum9 = spinRate9 * preQty1;
                totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            }
            subSumLabel.setText(String.valueOf(totalBurger));
        }
        total();
    }//GEN-LAST:event_jSpinner9StateChanged

    private void burger29500ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_burger29500ActionPerformed
        // TODO add your handling code here:
        if(burger29500.isSelected()){
            jSpinner10.setEnabled(true);
            subSum10 = subSum10 + 29500;
            totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            subSumLabel.setText(String.valueOf(totalBurger));
        }
        else{
            jSpinner10.setEnabled(false);
            jSpinner10.setValue(1);
            subSum10 = 0;
            totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            subSumLabel.setText(String.valueOf(totalBurger));
        }
        total();
    }//GEN-LAST:event_burger29500ActionPerformed

    private void jSpinner10StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner10StateChanged
        // TODO add your handling code here:
        double spinRate10 = 29500;
        if(burger29500.isSelected()){
            int preQty1;
            preQty1 = (Integer)jSpinner10.getValue();
            if(preQty1 > qty9){
                qty9 = preQty1;
                subSum10 = spinRate10 * preQty1;
                totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            }
            else if(preQty1<=qty9){
                qty9 = preQty1;
                subSum10 = spinRate10 * preQty1;
                totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            }
            subSumLabel.setText(String.valueOf(totalBurger));
        }
        total();
    }//GEN-LAST:event_jSpinner10StateChanged

    private void burger510ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_burger510ActionPerformed
        // TODO add your handling code here:
        if(burger510.isSelected()){
            jSpinner11.setEnabled(true);
            subSum11 = subSum11 + 510;
            totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            subSumLabel.setText(String.valueOf(totalBurger));
        }
        else{
            jSpinner11.setEnabled(false);
            jSpinner11.setValue(1);
            subSum11 = 0;
            totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            subSumLabel.setText(String.valueOf(totalBurger));
        }
        total();
    }//GEN-LAST:event_burger510ActionPerformed

    private void jSpinner11StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner11StateChanged
        // TODO add your handling code here:
        double spinRate11 = 510;
        if(burger510.isSelected()){
            int preQty1;
            preQty1 = (Integer)jSpinner11.getValue();
            if(preQty1 > qty10){
                qty10 = preQty1;
                subSum11 = spinRate11 * preQty1;
                totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            }
            else if(preQty1<=qty10){
                qty10 = preQty1;
                subSum11 = spinRate11 * preQty1;
                totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            }
            subSumLabel.setText(String.valueOf(totalBurger));
        }
        total();
    }//GEN-LAST:event_jSpinner11StateChanged

    private void burger540ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_burger540ActionPerformed
        // TODO add your handling code here:
        if(burger540.isSelected()){
            jSpinner12.setEnabled(true);
            subSum12 = subSum12 + 540;
            totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            subSumLabel.setText(String.valueOf(totalBurger));
        }
        else{
            jSpinner12.setEnabled(false);
            jSpinner12.setValue(1);
            subSum12 = 0;
            totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            subSumLabel.setText(String.valueOf(totalBurger));
        }
        total();
    }//GEN-LAST:event_burger540ActionPerformed

    private void jSpinner12StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jSpinner12StateChanged
        // TODO add your handling code here:
        double spinRate12 = 540;
        if(burger540.isSelected()){
            int preQty1;
            preQty1 = (Integer)jSpinner12.getValue();
            if(preQty1 > qty11){
                qty11 = preQty1;
                subSum12 = spinRate12 * preQty1;
                totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            }
            else if(preQty1<=qty11){
                qty11 = preQty1;
                subSum12 = spinRate12 * preQty1;
                totalBurger = subSum2 + subSum1 + subSum3 + subSum4 + subSum5 + subSum6 + subSum7 + subSum8 + subSum9 + subSum10 + subSum11 + subSum12;
            }
            subSumLabel.setText(String.valueOf(totalBurger));
        }
        total();
    }//GEN-LAST:event_jSpinner12StateChanged

    private void checkPizza2800ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkPizza2800ActionPerformed
        // TODO add your handling code here:
        if(checkPizza2800.isSelected()){
            s2800.setEnabled(true);
            m2800.setEnabled(true);
            l2800.setEnabled(true);
        }
        else{
            s2800.setEnabled(false);
            s2800.setValue(0);
            m2800.setEnabled(false);
            m2800.setValue(0);
            l2800.setEnabled(false);
            l2800.setValue(0);
            sumPizza[0] = 0;
            totalPizzaLabel.setText(String.valueOf(totalPizza));
        }
        total();
    }//GEN-LAST:event_checkPizza2800ActionPerformed

    private void s2800StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_s2800StateChanged
        // TODO add your handling code here:
        double sPrice1 = 2800;
        int preQty;
        preQty = (Integer)s2800.getValue();
        if(preQty > pizzaQty1){
            pizzaQty1 = preQty;
            sumPizza[0] = sPrice1 * pizzaQty1;
            totalPizza = sumPizza[0] + sumPizza[1] + sumPizza[2] + sumPizza[3] + sumPizza[4] + sumPizza[5] + sumPizza[6] + sumPizza[7] + sumPizza[8] + sumPizza[9] + sumPizza[10] + sumPizza[11] + sumPizza[12] + sumPizza[13] + sumPizza[14] + sumPizza[15] + sumPizza[16] + sumPizza[17] + sumPizza[18] + sumPizza[19] + sumPizza[20] + sumPizza[21] + sumPizza[22] + sumPizza[23] + sumPizza[24] + sumPizza[25] + sumPizza[26] + sumPizza[27] + sumPizza[28] + sumPizza[29] ;
        }
        else if(preQty <= pizzaQty1){
            pizzaQty1 = preQty;
            sumPizza[0] = sPrice1 * pizzaQty1;
            totalPizza = sumPizza[0] + sumPizza[1] + sumPizza[2] + sumPizza[3] + sumPizza[4] + sumPizza[5] + sumPizza[6] + sumPizza[7] + sumPizza[8] + sumPizza[9] + sumPizza[10] + sumPizza[11] + sumPizza[12] + sumPizza[13] + sumPizza[14] + sumPizza[15] + sumPizza[16] + sumPizza[17] + sumPizza[18] + sumPizza[19] + sumPizza[20] + sumPizza[21] + sumPizza[22] + sumPizza[23] + sumPizza[24] + sumPizza[25] + sumPizza[26] + sumPizza[27] + sumPizza[28] + sumPizza[29] ;
        }
        totalPizzaLabel.setText(String.valueOf(totalPizza));
        total();
    }//GEN-LAST:event_s2800StateChanged

    private void m2800StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_m2800StateChanged
        // TODO add your handling code here:
        double mPrice1 = 1000;
        int preQty;
        preQty = (Integer)m2800.getValue();
        if(preQty > pizzaQty1){
            pizzaQty1 = preQty;
            sumPizza[1] = mPrice1 * pizzaQty1;
            totalPizza = sumPizza[0] + sumPizza[1] + sumPizza[2] + sumPizza[3] + sumPizza[4] + sumPizza[5] + sumPizza[6] + sumPizza[7] + sumPizza[8] + sumPizza[9] + sumPizza[10] + sumPizza[11] + sumPizza[12] + sumPizza[13] + sumPizza[14] + sumPizza[15] + sumPizza[16] + sumPizza[17] + sumPizza[18] + sumPizza[19] + sumPizza[20] + sumPizza[21] + sumPizza[22] + sumPizza[23] + sumPizza[24] + sumPizza[25] + sumPizza[26] + sumPizza[27] + sumPizza[28] + sumPizza[29] ;
        }
        else if(preQty <= pizzaQty1){
            pizzaQty1 = preQty;
            sumPizza[1] = mPrice1 * pizzaQty1;
            totalPizza = sumPizza[0] + sumPizza[1] + sumPizza[2] + sumPizza[3] + sumPizza[4] + sumPizza[5] + sumPizza[6] + sumPizza[7] + sumPizza[8] + sumPizza[9] + sumPizza[10] + sumPizza[11] + sumPizza[12] + sumPizza[13] + sumPizza[14] + sumPizza[15] + sumPizza[16] + sumPizza[17] + sumPizza[18] + sumPizza[19] + sumPizza[20] + sumPizza[21] + sumPizza[22] + sumPizza[23] + sumPizza[24] + sumPizza[25] + sumPizza[26] + sumPizza[27] + sumPizza[28] + sumPizza[29] ;
        }
        totalPizzaLabel.setText(String.valueOf(totalPizza));
        total();
    }//GEN-LAST:event_m2800StateChanged

    private void l2800StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_l2800StateChanged
        // TODO add your handling code here:
        double lPrice1 = 1300;
        int preQty;
        preQty = (Integer)l2800.getValue();
        if(preQty > pizzaQty1){
            pizzaQty1 = preQty;
            sumPizza[2] = lPrice1 * pizzaQty1;
            totalPizza = sumPizza[0] + sumPizza[1] + sumPizza[2] + sumPizza[3] + sumPizza[4] + sumPizza[5] + sumPizza[6] + sumPizza[7] + sumPizza[8] + sumPizza[9] + sumPizza[10] + sumPizza[11] + sumPizza[12] + sumPizza[13] + sumPizza[14] + sumPizza[15] + sumPizza[16] + sumPizza[17] + sumPizza[18] + sumPizza[19] + sumPizza[20] + sumPizza[21] + sumPizza[22] + sumPizza[23] + sumPizza[24] + sumPizza[25] + sumPizza[26] + sumPizza[27] + sumPizza[28] + sumPizza[29] ;
        }
        else if(preQty <= pizzaQty1){
            pizzaQty1 = preQty;
            sumPizza[2] = lPrice1 * pizzaQty1;
            totalPizza = sumPizza[0] + sumPizza[1] + sumPizza[2] + sumPizza[3] + sumPizza[4] + sumPizza[5] + sumPizza[6] + sumPizza[7] + sumPizza[8] + sumPizza[9] + sumPizza[10] + sumPizza[11] + sumPizza[12] + sumPizza[13] + sumPizza[14] + sumPizza[15] + sumPizza[16] + sumPizza[17] + sumPizza[18] + sumPizza[19] + sumPizza[20] + sumPizza[21] + sumPizza[22] + sumPizza[23] + sumPizza[24] + sumPizza[25] + sumPizza[26] + sumPizza[27] + sumPizza[28] + sumPizza[29] ;
        }
        totalPizzaLabel.setText(String.valueOf(totalPizza));
        total();
    }//GEN-LAST:event_l2800StateChanged

    private void checkPizza750ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkPizza750ActionPerformed
        // TODO add your handling code here:
        if(checkPizza750.isSelected()){
            s37500.setEnabled(true);
            m39500.setEnabled(true);
            l37500.setEnabled(true);
        }
        else{
            s37500.setEnabled(false);
            s37500.setValue(0);
            m39500.setEnabled(false);
            m39500.setValue(0);
            l37500.setEnabled(false);
            l37500.setValue(0);
        }
        total();
    }//GEN-LAST:event_checkPizza750ActionPerformed

    private void s37500StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_s37500StateChanged
        // TODO add your handling code here:
        double sPrice1 = 37500;
        int preQty;
        preQty = (Integer)s37500.getValue();
        if(preQty > pizzaQty1){
            pizzaQty1 = preQty;
            sumPizza[3] = sPrice1 * pizzaQty1;
            totalPizza = sumPizza[0] + sumPizza[1] + sumPizza[2] + sumPizza[3] + sumPizza[4] + sumPizza[5] + sumPizza[6] + sumPizza[7] + sumPizza[8] + sumPizza[9] + sumPizza[10] + sumPizza[11] + sumPizza[12] + sumPizza[13] + sumPizza[14] + sumPizza[15] + sumPizza[16] + sumPizza[17] + sumPizza[18] + sumPizza[19] + sumPizza[20] + sumPizza[21] + sumPizza[22] + sumPizza[23] + sumPizza[24] + sumPizza[25] + sumPizza[26] + sumPizza[27] + sumPizza[28] + sumPizza[29] ;
        }
        else if(preQty <= pizzaQty1){
            pizzaQty1 = preQty;
            sumPizza[3] = sPrice1 * pizzaQty1;
            totalPizza = sumPizza[0] + sumPizza[1] + sumPizza[2] + sumPizza[3] + sumPizza[4] + sumPizza[5] + sumPizza[6] + sumPizza[7] + sumPizza[8] + sumPizza[9] + sumPizza[10] + sumPizza[11] + sumPizza[12] + sumPizza[13] + sumPizza[14] + sumPizza[15] + sumPizza[16] + sumPizza[17] + sumPizza[18] + sumPizza[19] + sumPizza[20] + sumPizza[21] + sumPizza[22] + sumPizza[23] + sumPizza[24] + sumPizza[25] + sumPizza[26] + sumPizza[27] + sumPizza[28] + sumPizza[29] ;
        }
        totalPizzaLabel.setText(String.valueOf(totalPizza));
        total();
    }//GEN-LAST:event_s37500StateChanged

    private void m39500StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_m39500StateChanged
        // TODO add your handling code here:
        double sPrice1 = 45500;
        int preQty;
        preQty = (Integer)m39500.getValue();
        if(preQty > pizzaQty1){
            pizzaQty1 = preQty;
            sumPizza[4] = sPrice1 * pizzaQty1;
            totalPizza = sumPizza[0] + sumPizza[1] + sumPizza[2] + sumPizza[3] + sumPizza[4] + sumPizza[5] + sumPizza[6] + sumPizza[7] + sumPizza[8] + sumPizza[9] + sumPizza[10] + sumPizza[11] + sumPizza[12] + sumPizza[13] + sumPizza[14] + sumPizza[15] + sumPizza[16] + sumPizza[17] + sumPizza[18] + sumPizza[19] + sumPizza[20] + sumPizza[21] + sumPizza[22] + sumPizza[23] + sumPizza[24] + sumPizza[25] + sumPizza[26] + sumPizza[27] + sumPizza[28] + sumPizza[29] ;
        }
        else if(preQty <= pizzaQty1){
            pizzaQty1 = preQty;
            sumPizza[4] = sPrice1 * pizzaQty1;
            totalPizza = sumPizza[0] + sumPizza[1] + sumPizza[2] + sumPizza[3] + sumPizza[4] + sumPizza[5] + sumPizza[6] + sumPizza[7] + sumPizza[8] + sumPizza[9] + sumPizza[10] + sumPizza[11] + sumPizza[12] + sumPizza[13] + sumPizza[14] + sumPizza[15] + sumPizza[16] + sumPizza[17] + sumPizza[18] + sumPizza[19] + sumPizza[20] + sumPizza[21] + sumPizza[22] + sumPizza[23] + sumPizza[24] + sumPizza[25] + sumPizza[26] + sumPizza[27] + sumPizza[28] + sumPizza[29] ;
        }
        totalPizzaLabel.setText(String.valueOf(totalPizza));
        total();
    }//GEN-LAST:event_m39500StateChanged

    private void l37500StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_l37500StateChanged
        // TODO add your handling code here:
        double sPrice1 = 50500;
        int preQty;
        preQty = (Integer)l37500.getValue();
        if(preQty > pizzaQty1){
            pizzaQty1 = preQty;
            sumPizza[5] = sPrice1 * pizzaQty1;
            totalPizza = sumPizza[0] + sumPizza[1] + sumPizza[2] + sumPizza[3] + sumPizza[4] + sumPizza[5] + sumPizza[6] + sumPizza[7] + sumPizza[8] + sumPizza[9] + sumPizza[10] + sumPizza[11] + sumPizza[12] + sumPizza[13] + sumPizza[14] + sumPizza[15] + sumPizza[16] + sumPizza[17] + sumPizza[18] + sumPizza[19] + sumPizza[20] + sumPizza[21] + sumPizza[22] + sumPizza[23] + sumPizza[24] + sumPizza[25] + sumPizza[26] + sumPizza[27] + sumPizza[28] + sumPizza[29] ;
        }
        else if(preQty <= pizzaQty1){
            pizzaQty1 = preQty;
            sumPizza[5] = sPrice1 * pizzaQty1;
            totalPizza = sumPizza[0] + sumPizza[1] + sumPizza[2] + sumPizza[3] + sumPizza[4] + sumPizza[5] + sumPizza[6] + sumPizza[7] + sumPizza[8] + sumPizza[9] + sumPizza[10] + sumPizza[11] + sumPizza[12] + sumPizza[13] + sumPizza[14] + sumPizza[15] + sumPizza[16] + sumPizza[17] + sumPizza[18] + sumPizza[19] + sumPizza[20] + sumPizza[21] + sumPizza[22] + sumPizza[23] + sumPizza[24] + sumPizza[25] + sumPizza[26] + sumPizza[27] + sumPizza[28] + sumPizza[29] ;
        }
        totalPizzaLabel.setText(String.valueOf(totalPizza));
        total();
    }//GEN-LAST:event_l37500StateChanged

    private void checkPizza850ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkPizza850ActionPerformed
        // TODO add your handling code here:
        if(checkPizza850.isSelected()){
            s10500.setEnabled(true);
            m15000.setEnabled(true);
            l850.setEnabled(true);
        }
        else{
            s10500.setEnabled(false);
            s10500.setValue(0);
            m15000.setEnabled(false);
            m15000.setValue(0);
            l850.setEnabled(false);
            l850.setValue(0);
        }
        total();
    }//GEN-LAST:event_checkPizza850ActionPerformed

    private void s10500StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_s10500StateChanged
        // TODO add your handling code here:
        double sPrice1 = 10000;
        int preQty;
        preQty = (Integer)s10500.getValue();
        if(preQty > pizzaQty1){
            pizzaQty1 = preQty;
            sumPizza[6] = sPrice1 * pizzaQty1;
            totalPizza = sumPizza[0] + sumPizza[1] + sumPizza[2] + sumPizza[3] + sumPizza[4] + sumPizza[5] + sumPizza[6] + sumPizza[7] + sumPizza[8] + sumPizza[9] + sumPizza[10] + sumPizza[11] + sumPizza[12] + sumPizza[13] + sumPizza[14] + sumPizza[15] + sumPizza[16] + sumPizza[17] + sumPizza[18] + sumPizza[19] + sumPizza[20] + sumPizza[21] + sumPizza[22] + sumPizza[23] + sumPizza[24] + sumPizza[25] + sumPizza[26] + sumPizza[27] + sumPizza[28] + sumPizza[29] ;
        }
        else if(preQty <= pizzaQty1){
            pizzaQty1 = preQty;
            sumPizza[6] = sPrice1 * pizzaQty1;
            totalPizza = sumPizza[0] + sumPizza[1] + sumPizza[2] + sumPizza[3] + sumPizza[4] + sumPizza[5] + sumPizza[6] + sumPizza[7] + sumPizza[8] + sumPizza[9] + sumPizza[10] + sumPizza[11] + sumPizza[12] + sumPizza[13] + sumPizza[14] + sumPizza[15] + sumPizza[16] + sumPizza[17] + sumPizza[18] + sumPizza[19] + sumPizza[20] + sumPizza[21] + sumPizza[22] + sumPizza[23] + sumPizza[24] + sumPizza[25] + sumPizza[26] + sumPizza[27] + sumPizza[28] + sumPizza[29] ;
        }
        totalPizzaLabel.setText(String.valueOf(totalPizza));
        total();
    }//GEN-LAST:event_s10500StateChanged

    private void m15000StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_m15000StateChanged
        // TODO add your handling code here:
        double sPrice1 = 15000;
        int preQty;
        preQty = (Integer)m15000.getValue();
        if(preQty > pizzaQty1){
            pizzaQty1 = preQty;
            sumPizza[7] = sPrice1 * pizzaQty1;
            totalPizza = sumPizza[0] + sumPizza[1] + sumPizza[2] + sumPizza[3] + sumPizza[4] + sumPizza[5] + sumPizza[6] + sumPizza[7] + sumPizza[8] + sumPizza[9] + sumPizza[10] + sumPizza[11] + sumPizza[12] + sumPizza[13] + sumPizza[14] + sumPizza[15] + sumPizza[16] + sumPizza[17] + sumPizza[18] + sumPizza[19] + sumPizza[20] + sumPizza[21] + sumPizza[22] + sumPizza[23] + sumPizza[24] + sumPizza[25] + sumPizza[26] + sumPizza[27] + sumPizza[28] + sumPizza[29] ;
        }
        else if(preQty <= pizzaQty1){
            pizzaQty1 = preQty;
            sumPizza[7] = sPrice1 * pizzaQty1;
            totalPizza = sumPizza[0] + sumPizza[1] + sumPizza[2] + sumPizza[3] + sumPizza[4] + sumPizza[5] + sumPizza[6] + sumPizza[7] + sumPizza[8] + sumPizza[9] + sumPizza[10] + sumPizza[11] + sumPizza[12] + sumPizza[13] + sumPizza[14] + sumPizza[15] + sumPizza[16] + sumPizza[17] + sumPizza[18] + sumPizza[19] + sumPizza[20] + sumPizza[21] + sumPizza[22] + sumPizza[23] + sumPizza[24] + sumPizza[25] + sumPizza[26] + sumPizza[27] + sumPizza[28] + sumPizza[29] ;
        }
        totalPizzaLabel.setText(String.valueOf(totalPizza));
        total();
    }//GEN-LAST:event_m15000StateChanged

    private void l850StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_l850StateChanged
        // TODO add your handling code here:
        double sPrice1 = 20000;
        int preQty;
        preQty = (Integer)l850.getValue();
        if(preQty > pizzaQty1){
            pizzaQty1 = preQty;
            sumPizza[8] = sPrice1 * pizzaQty1;
            totalPizza = sumPizza[0] + sumPizza[1] + sumPizza[2] + sumPizza[3] + sumPizza[4] + sumPizza[5] + sumPizza[6] + sumPizza[7] + sumPizza[8] + sumPizza[9] + sumPizza[10] + sumPizza[11] + sumPizza[12] + sumPizza[13] + sumPizza[14] + sumPizza[15] + sumPizza[16] + sumPizza[17] + sumPizza[18] + sumPizza[19] + sumPizza[20] + sumPizza[21] + sumPizza[22] + sumPizza[23] + sumPizza[24] + sumPizza[25] + sumPizza[26] + sumPizza[27] + sumPizza[28] + sumPizza[29] ;
        }
        else if(preQty <= pizzaQty1){
            pizzaQty1 = preQty;
            sumPizza[8] = sPrice1 * pizzaQty1;
            totalPizza = sumPizza[0] + sumPizza[1] + sumPizza[2] + sumPizza[3] + sumPizza[4] + sumPizza[5] + sumPizza[6] + sumPizza[7] + sumPizza[8] + sumPizza[9] + sumPizza[10] + sumPizza[11] + sumPizza[12] + sumPizza[13] + sumPizza[14] + sumPizza[15] + sumPizza[16] + sumPizza[17] + sumPizza[18] + sumPizza[19] + sumPizza[20] + sumPizza[21] + sumPizza[22] + sumPizza[23] + sumPizza[24] + sumPizza[25] + sumPizza[26] + sumPizza[27] + sumPizza[28] + sumPizza[29] ;
        }
        totalPizzaLabel.setText(String.valueOf(totalPizza));
        total();
    }//GEN-LAST:event_l850StateChanged

    private void checkPizza650ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkPizza650ActionPerformed
        // TODO add your handling code here:
        if(checkPizza650.isSelected()){
            s16000.setEnabled(true);
            m24000.setEnabled(true);
            l28000.setEnabled(true);
        }
        else{
            s16000.setEnabled(false);
            s16000.setValue(0);
            m24000.setEnabled(false);
            m24000.setValue(0);
            l28000.setEnabled(false);
            l28000.setValue(0);
        }
        total();
    }//GEN-LAST:event_checkPizza650ActionPerformed

    private void l28000StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_l28000StateChanged
        // TODO add your handling code here:
        double sPrice1 =28000;
        int preQty;
        preQty = (Integer)l28000.getValue();
        if(preQty > pizzaQty1){
            pizzaQty1 = preQty;
            sumPizza[17] = sPrice1 * pizzaQty1;
            totalPizza = sumPizza[0] + sumPizza[1] + sumPizza[2] + sumPizza[3] + sumPizza[4] + sumPizza[5] + sumPizza[6] + sumPizza[7] + sumPizza[8] + sumPizza[9] + sumPizza[10] + sumPizza[11] + sumPizza[12] + sumPizza[13] + sumPizza[14] + sumPizza[15] + sumPizza[16] + sumPizza[17] + sumPizza[18] + sumPizza[19] + sumPizza[20] + sumPizza[21] + sumPizza[22] + sumPizza[23] + sumPizza[24] + sumPizza[25] + sumPizza[26] + sumPizza[27] + sumPizza[28] + sumPizza[29] ;
        }
        else if(preQty <= pizzaQty1){
            pizzaQty1 = preQty;
            sumPizza[17] = sPrice1 * pizzaQty1;
            totalPizza = sumPizza[0] + sumPizza[1] + sumPizza[2] + sumPizza[3] + sumPizza[4] + sumPizza[5] + sumPizza[6] + sumPizza[7] + sumPizza[8] + sumPizza[9] + sumPizza[10] + sumPizza[11] + sumPizza[12] + sumPizza[13] + sumPizza[14] + sumPizza[15] + sumPizza[16] + sumPizza[17] + sumPizza[18] + sumPizza[19] + sumPizza[20] + sumPizza[21] + sumPizza[22] + sumPizza[23] + sumPizza[24] + sumPizza[25] + sumPizza[26] + sumPizza[27] + sumPizza[28] + sumPizza[29] ;
        }
        totalPizzaLabel.setText(String.valueOf(totalPizza));
        total();
    }//GEN-LAST:event_l28000StateChanged

    private void m24000StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_m24000StateChanged
        // TODO add your handling code here:
        double sPrice1 = 24000;
        int preQty;
        preQty = (Integer)m24000.getValue();
        if(preQty > pizzaQty1){
            pizzaQty1 = preQty;
            sumPizza[16] = sPrice1 * pizzaQty1;
            totalPizza = sumPizza[0] + sumPizza[1] + sumPizza[2] + sumPizza[3] + sumPizza[4] + sumPizza[5] + sumPizza[6] + sumPizza[7] + sumPizza[8] + sumPizza[9] + sumPizza[10] + sumPizza[11] + sumPizza[12] + sumPizza[13] + sumPizza[14] + sumPizza[15] + sumPizza[16] + sumPizza[17] + sumPizza[18] + sumPizza[19] + sumPizza[20] + sumPizza[21] + sumPizza[22] + sumPizza[23] + sumPizza[24] + sumPizza[25] + sumPizza[26] + sumPizza[27] + sumPizza[28] + sumPizza[29] ;
        }
        else if(preQty <= pizzaQty1){
            pizzaQty1 = preQty;
            sumPizza[16] = sPrice1 * pizzaQty1;
            totalPizza = sumPizza[0] + sumPizza[1] + sumPizza[2] + sumPizza[3] + sumPizza[4] + sumPizza[5] + sumPizza[6] + sumPizza[7] + sumPizza[8] + sumPizza[9] + sumPizza[10] + sumPizza[11] + sumPizza[12] + sumPizza[13] + sumPizza[14] + sumPizza[15] + sumPizza[16] + sumPizza[17] + sumPizza[18] + sumPizza[19] + sumPizza[20] + sumPizza[21] + sumPizza[22] + sumPizza[23] + sumPizza[24] + sumPizza[25] + sumPizza[26] + sumPizza[27] + sumPizza[28] + sumPizza[29] ;
        }
        totalPizzaLabel.setText(String.valueOf(totalPizza));
        total();
    }//GEN-LAST:event_m24000StateChanged

    private void s16000StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_s16000StateChanged
        // TODO add your handling code here:
        double sPrice1 = 16000;
        int preQty;
        preQty = (Integer)s16000.getValue();
        if(preQty > pizzaQty1){
            pizzaQty1 = preQty;
            sumPizza[15] = sPrice1 * pizzaQty1;
            totalPizza = sumPizza[0] + sumPizza[1] + sumPizza[2] + sumPizza[3] + sumPizza[4] + sumPizza[5] + sumPizza[6] + sumPizza[7] + sumPizza[8] + sumPizza[9] + sumPizza[10] + sumPizza[11] + sumPizza[12] + sumPizza[13] + sumPizza[14] + sumPizza[15] + sumPizza[16] + sumPizza[17] + sumPizza[18] + sumPizza[19] + sumPizza[20] + sumPizza[21] + sumPizza[22] + sumPizza[23] + sumPizza[24] + sumPizza[25] + sumPizza[26] + sumPizza[27] + sumPizza[28] + sumPizza[29] ;
        }
        else if(preQty <= pizzaQty1){
            pizzaQty1 = preQty;
            sumPizza[15] = sPrice1 * pizzaQty1;
            totalPizza = sumPizza[0] + sumPizza[1] + sumPizza[2] + sumPizza[3] + sumPizza[4] + sumPizza[5] + sumPizza[6] + sumPizza[7] + sumPizza[8] + sumPizza[9] + sumPizza[10] + sumPizza[11] + sumPizza[12] + sumPizza[13] + sumPizza[14] + sumPizza[15] + sumPizza[16] + sumPizza[17] + sumPizza[18] + sumPizza[19] + sumPizza[20] + sumPizza[21] + sumPizza[22] + sumPizza[23] + sumPizza[24] + sumPizza[25] + sumPizza[26] + sumPizza[27] + sumPizza[28] + sumPizza[29] ;
        }
        totalPizzaLabel.setText(String.valueOf(totalPizza));
        total();
    }//GEN-LAST:event_s16000StateChanged

    private void checkPizza830ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkPizza830ActionPerformed
        // TODO add your handling code here:
        if(checkPizza830.isSelected()){
            s18000.setEnabled(true);
            m22000.setEnabled(true);
            l25000.setEnabled(true);
        }
        else{
            s18000.setEnabled(false);
            s18000.setValue(0);
            m22000.setEnabled(false);
            m22000.setValue(0);
            l25000.setEnabled(false);
            l25000.setValue(0);
        }
        total();
    }//GEN-LAST:event_checkPizza830ActionPerformed

    private void l25000StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_l25000StateChanged
        // TODO add your handling code here:
        double sPrice1 = 25000;
        int preQty;
        preQty = (Integer)l25000.getValue();
        if(preQty > pizzaQty1){
            pizzaQty1 = preQty;
            sumPizza[20] = sPrice1 * pizzaQty1;
            totalPizza = sumPizza[0] + sumPizza[1] + sumPizza[2] + sumPizza[3] + sumPizza[4] + sumPizza[5] + sumPizza[6] + sumPizza[7] + sumPizza[8] + sumPizza[9] + sumPizza[10] + sumPizza[11] + sumPizza[12] + sumPizza[13] + sumPizza[14] + sumPizza[15] + sumPizza[16] + sumPizza[17] + sumPizza[18] + sumPizza[19] + sumPizza[20] + sumPizza[21] + sumPizza[22] + sumPizza[23] + sumPizza[24] + sumPizza[25] + sumPizza[26] + sumPizza[27] + sumPizza[28] + sumPizza[29] ;
        }
        else if(preQty <= pizzaQty1){
            pizzaQty1 = preQty;
            sumPizza[20] = sPrice1 * pizzaQty1;
            totalPizza = sumPizza[0] + sumPizza[1] + sumPizza[2] + sumPizza[3] + sumPizza[4] + sumPizza[5] + sumPizza[6] + sumPizza[7] + sumPizza[8] + sumPizza[9] + sumPizza[10] + sumPizza[11] + sumPizza[12] + sumPizza[13] + sumPizza[14] + sumPizza[15] + sumPizza[16] + sumPizza[17] + sumPizza[18] + sumPizza[19] + sumPizza[20] + sumPizza[21] + sumPizza[22] + sumPizza[23] + sumPizza[24] + sumPizza[25] + sumPizza[26] + sumPizza[27] + sumPizza[28] + sumPizza[29] ;
        }
        totalPizzaLabel.setText(String.valueOf(totalPizza));
        total();
    }//GEN-LAST:event_l25000StateChanged

    private void m22000StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_m22000StateChanged
        // TODO add your handling code here:
        double sPrice1 = 20000;
        int preQty;
        preQty = (Integer)m22000.getValue();
        if(preQty > pizzaQty1){
            pizzaQty1 = preQty;
            sumPizza[19] = sPrice1 * pizzaQty1;
            totalPizza = sumPizza[0] + sumPizza[1] + sumPizza[2] + sumPizza[3] + sumPizza[4] + sumPizza[5] + sumPizza[6] + sumPizza[7] + sumPizza[8] + sumPizza[9] + sumPizza[10] + sumPizza[11] + sumPizza[12] + sumPizza[13] + sumPizza[14] + sumPizza[15] + sumPizza[16] + sumPizza[17] + sumPizza[18] + sumPizza[19] + sumPizza[20] + sumPizza[21] + sumPizza[22] + sumPizza[23] + sumPizza[24] + sumPizza[25] + sumPizza[26] + sumPizza[27] + sumPizza[28] + sumPizza[29] ;
        }
        else if(preQty <= pizzaQty1){
            pizzaQty1 = preQty;
            sumPizza[19] = sPrice1 * pizzaQty1;
            totalPizza = sumPizza[0] + sumPizza[1] + sumPizza[2] + sumPizza[3] + sumPizza[4] + sumPizza[5] + sumPizza[6] + sumPizza[7] + sumPizza[8] + sumPizza[9] + sumPizza[10] + sumPizza[11] + sumPizza[12] + sumPizza[13] + sumPizza[14] + sumPizza[15] + sumPizza[16] + sumPizza[17] + sumPizza[18] + sumPizza[19] + sumPizza[20] + sumPizza[21] + sumPizza[22] + sumPizza[23] + sumPizza[24] + sumPizza[25] + sumPizza[26] + sumPizza[27] + sumPizza[28] + sumPizza[29] ;
        }
        totalPizzaLabel.setText(String.valueOf(totalPizza));
        total();
    }//GEN-LAST:event_m22000StateChanged

    private void s18000StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_s18000StateChanged
        // TODO add your handling code here:
        double sPrice1 = 18000;
        int preQty;
        preQty = (Integer)s18000.getValue();
        if(preQty > pizzaQty1){
            pizzaQty1 = preQty;
            sumPizza[18] = sPrice1 * pizzaQty1;
            totalPizza = sumPizza[0] + sumPizza[1] + sumPizza[2] + sumPizza[3] + sumPizza[4] + sumPizza[5] + sumPizza[6] + sumPizza[7] + sumPizza[8] + sumPizza[9] + sumPizza[10] + sumPizza[11] + sumPizza[12] + sumPizza[13] + sumPizza[14] + sumPizza[15] + sumPizza[16] + sumPizza[17] + sumPizza[18] + sumPizza[19] + sumPizza[20] + sumPizza[21] + sumPizza[22] + sumPizza[23] + sumPizza[24] + sumPizza[25] + sumPizza[26] + sumPizza[27] + sumPizza[28] + sumPizza[29] ;
        }
        else if(preQty <= pizzaQty1){
            pizzaQty1 = preQty;
            sumPizza[18] = sPrice1 * pizzaQty1;
            totalPizza = sumPizza[0] + sumPizza[1] + sumPizza[2] + sumPizza[3] + sumPizza[4] + sumPizza[5] + sumPizza[6] + sumPizza[7] + sumPizza[8] + sumPizza[9] + sumPizza[10] + sumPizza[11] + sumPizza[12] + sumPizza[13] + sumPizza[14] + sumPizza[15] + sumPizza[16] + sumPizza[17] + sumPizza[18] + sumPizza[19] + sumPizza[20] + sumPizza[21] + sumPizza[22] + sumPizza[23] + sumPizza[24] + sumPizza[25] + sumPizza[26] + sumPizza[27] + sumPizza[28] + sumPizza[29] ;
        }
        totalPizzaLabel.setText(String.valueOf(totalPizza));
        total();
    }//GEN-LAST:event_s18000StateChanged

    private void checkPizza950ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkPizza950ActionPerformed
        // TODO add your handling code here:
        if(checkPizza950.isSelected()){
            s19000.setEnabled(true);
            m24000i.setEnabled(true);
            l28000m.setEnabled(true);
        }
        else{
            s19000.setEnabled(false);
            s19000.setValue(0);
            m24000i.setEnabled(false);
            m24000i.setValue(0);
            l28000m.setEnabled(false);
            l28000m.setValue(0);
        }
        total();
    }//GEN-LAST:event_checkPizza950ActionPerformed

    private void s19000StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_s19000StateChanged
        // TODO add your handling code here:
        double sPrice1 = 19000;
        int preQty;
        preQty = (Integer)s19000.getValue();
        if(preQty > pizzaQty1){
            pizzaQty1 = preQty;
            sumPizza[21] = sPrice1 * pizzaQty1;
            totalPizza = sumPizza[0] + sumPizza[1] + sumPizza[2] + sumPizza[3] + sumPizza[4] + sumPizza[5] + sumPizza[6] + sumPizza[7] + sumPizza[8] + sumPizza[9] + sumPizza[10] + sumPizza[11] + sumPizza[12] + sumPizza[13] + sumPizza[14] + sumPizza[15] + sumPizza[16] + sumPizza[17] + sumPizza[18] + sumPizza[19] + sumPizza[20] + sumPizza[21] + sumPizza[22] + sumPizza[23] + sumPizza[24] + sumPizza[25] + sumPizza[26] + sumPizza[27] + sumPizza[28] + sumPizza[29] ;
        }
        else if(preQty <= pizzaQty1){
            pizzaQty1 = preQty;
            sumPizza[21] = sPrice1 * pizzaQty1;
            totalPizza = sumPizza[0] + sumPizza[1] + sumPizza[2] + sumPizza[3] + sumPizza[4] + sumPizza[5] + sumPizza[6] + sumPizza[7] + sumPizza[8] + sumPizza[9] + sumPizza[10] + sumPizza[11] + sumPizza[12] + sumPizza[13] + sumPizza[14] + sumPizza[15] + sumPizza[16] + sumPizza[17] + sumPizza[18] + sumPizza[19] + sumPizza[20] + sumPizza[21] + sumPizza[22] + sumPizza[23] + sumPizza[24] + sumPizza[25] + sumPizza[26] + sumPizza[27] + sumPizza[28] + sumPizza[29] ;
        }
        totalPizzaLabel.setText(String.valueOf(totalPizza));
        total();
    }//GEN-LAST:event_s19000StateChanged

    private void l28000mStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_l28000mStateChanged
        // TODO add your handling code here:
        double sPrice1 = 28000;
        int preQty;
        preQty = (Integer)l28000m.getValue();
        if(preQty > pizzaQty1){
            pizzaQty1 = preQty;
            sumPizza[23] = sPrice1 * pizzaQty1;
            totalPizza = sumPizza[0] + sumPizza[1] + sumPizza[2] + sumPizza[3] + sumPizza[4] + sumPizza[5] + sumPizza[6] + sumPizza[7] + sumPizza[8] + sumPizza[9] + sumPizza[10] + sumPizza[11] + sumPizza[12] + sumPizza[13] + sumPizza[14] + sumPizza[15] + sumPizza[16] + sumPizza[17] + sumPizza[18] + sumPizza[19] + sumPizza[20] + sumPizza[21] + sumPizza[22] + sumPizza[23] + sumPizza[24] + sumPizza[25] + sumPizza[26] + sumPizza[27] + sumPizza[28] + sumPizza[29] ;
        }
        else if(preQty <= pizzaQty1){
            pizzaQty1 = preQty;
            sumPizza[23] = sPrice1 * pizzaQty1;
            totalPizza = sumPizza[0] + sumPizza[1] + sumPizza[2] + sumPizza[3] + sumPizza[4] + sumPizza[5] + sumPizza[6] + sumPizza[7] + sumPizza[8] + sumPizza[9] + sumPizza[10] + sumPizza[11] + sumPizza[12] + sumPizza[13] + sumPizza[14] + sumPizza[15] + sumPizza[16] + sumPizza[17] + sumPizza[18] + sumPizza[19] + sumPizza[20] + sumPizza[21] + sumPizza[22] + sumPizza[23] + sumPizza[24] + sumPizza[25] + sumPizza[26] + sumPizza[27] + sumPizza[28] + sumPizza[29] ;
        }
        totalPizzaLabel.setText(String.valueOf(totalPizza));
        total();
    }//GEN-LAST:event_l28000mStateChanged

    private void m24000iStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_m24000iStateChanged
        // TODO add your handling code here:
        double sPrice1 = 24000;
        int preQty;
        preQty = (Integer)m24000i.getValue();
        if(preQty > pizzaQty1){
            pizzaQty1 = preQty;
            sumPizza[22] = sPrice1 * pizzaQty1;
            totalPizza = sumPizza[0] + sumPizza[1] + sumPizza[2] + sumPizza[3] + sumPizza[4] + sumPizza[5] + sumPizza[6] + sumPizza[7] + sumPizza[8] + sumPizza[9] + sumPizza[10] + sumPizza[11] + sumPizza[12] + sumPizza[13] + sumPizza[14] + sumPizza[15] + sumPizza[16] + sumPizza[17] + sumPizza[18] + sumPizza[19] + sumPizza[20] + sumPizza[21] + sumPizza[22] + sumPizza[23] + sumPizza[24] + sumPizza[25] + sumPizza[26] + sumPizza[27] + sumPizza[28] + sumPizza[29] ;
        }
        else if(preQty <= pizzaQty1){
            pizzaQty1 = preQty;
            sumPizza[22] = sPrice1 * pizzaQty1;
            totalPizza = sumPizza[0] + sumPizza[1] + sumPizza[2] + sumPizza[3] + sumPizza[4] + sumPizza[5] + sumPizza[6] + sumPizza[7] + sumPizza[8] + sumPizza[9] + sumPizza[10] + sumPizza[11] + sumPizza[12] + sumPizza[13] + sumPizza[14] + sumPizza[15] + sumPizza[16] + sumPizza[17] + sumPizza[18] + sumPizza[19] + sumPizza[20] + sumPizza[21] + sumPizza[22] + sumPizza[23] + sumPizza[24] + sumPizza[25] + sumPizza[26] + sumPizza[27] + sumPizza[28] + sumPizza[29] ;
        }
        totalPizzaLabel.setText(String.valueOf(totalPizza));
        total();
    }//GEN-LAST:event_m24000iStateChanged

    private void jCheckBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox1ActionPerformed
        // TODO add your handling code here:
        if(jCheckBox1.isSelected()){
            cokeRegular.setEnabled(true);
            sumDrinks[0] = sumDrinks[0] + 4500;
            totalDrinks = sumDrinks[0] + sumDrinks[1] + sumDrinks[2] + sumDrinks[3] + sumDrinks[4] + sumDrinks[5] + sumDrinks[6] + sumDrinks[7];
            totalDrinksLabel.setText(String.valueOf(totalDrinks));
            total();
        }
        else{
            cokeRegular.setEnabled(false);
            cokeRegular.setValue(1);
            sumDrinks[0] = 0;
            totalDrinks = sumDrinks[0] + sumDrinks[1] + sumDrinks[2] + sumDrinks[3] + sumDrinks[4] + sumDrinks[5] + sumDrinks[6] + sumDrinks[7];
            totalDrinksLabel.setText(String.valueOf(totalDrinks));
            total();
        }
    }//GEN-LAST:event_jCheckBox1ActionPerformed

    private void cokeRegularStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_cokeRegularStateChanged
        // TODO add your handling code here:
        double sPrice1 = 4500;
        int preQty;
        preQty = (Integer)cokeRegular.getValue();
        if(preQty > pizzaQty1){
            pizzaQty1 = preQty;
            sumDrinks[0] = sPrice1 * pizzaQty1;
            totalDrinks = sumDrinks[0] + sumDrinks[1] + sumDrinks[2] + sumDrinks[3] + sumDrinks[4] + sumDrinks[5] + sumDrinks[6] + sumDrinks[7];
        }
        else if(preQty <= pizzaQty1){
            pizzaQty1 = preQty;
            sumDrinks[0] = sPrice1 * pizzaQty1;
            totalDrinks = sumDrinks[0] + sumDrinks[1] + sumDrinks[2] + sumDrinks[3] + sumDrinks[4] + sumDrinks[5] + sumDrinks[6] + sumDrinks[7];
        }
        totalDrinksLabel.setText(String.valueOf(totalDrinks));
        total();
    }//GEN-LAST:event_cokeRegularStateChanged

    private void jCheckBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox2ActionPerformed
        // TODO add your handling code here:
        if(jCheckBox2.isSelected()){
            pepsiRegular.setEnabled(true);
            sumDrinks[0] = sumDrinks[0] + 4500;
            totalDrinks = sumDrinks[0] + sumDrinks[1] + sumDrinks[2] + sumDrinks[3] + sumDrinks[4] + sumDrinks[5] + sumDrinks[6] + sumDrinks[7];
            totalDrinksLabel.setText(String.valueOf(totalDrinks));
            total();
        }
        else{
            sumDrinks[0] = 0;
            totalDrinks = sumDrinks[0] + sumDrinks[1] + sumDrinks[2] + sumDrinks[3] + sumDrinks[4] + sumDrinks[5] + sumDrinks[6] + sumDrinks[7];
            totalDrinksLabel.setText(String.valueOf(totalDrinks));
            pepsiRegular.setEnabled(false);
            pepsiRegular.setValue(1);
            total();
        }
    }//GEN-LAST:event_jCheckBox2ActionPerformed

    private void pepsiRegularStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_pepsiRegularStateChanged
        // TODO add your handling code here:
        double sPrice1 = 4500;
        int preQty;
        preQty = (Integer)pepsiRegular.getValue();
        if(preQty > pizzaQty1){
            pizzaQty1 = preQty;
            sumDrinks[1] = sPrice1 * pizzaQty1;
            totalDrinks = sumDrinks[0] + sumDrinks[1] + sumDrinks[2] + sumDrinks[3] + sumDrinks[4] + sumDrinks[5] + sumDrinks[6] + sumDrinks[7];
        }
        else if(preQty <= pizzaQty1){
            pizzaQty1 = preQty;
            sumDrinks[1] = sPrice1 * pizzaQty1;
            totalDrinks = sumDrinks[0] + sumDrinks[1] + sumDrinks[2] + sumDrinks[3] + sumDrinks[4] + sumDrinks[5] + sumDrinks[6] + sumDrinks[7];
        }
        totalDrinksLabel.setText(String.valueOf(totalDrinks));
        total();
    }//GEN-LAST:event_pepsiRegularStateChanged

    private void jCheckBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox3ActionPerformed
        // TODO add your handling code here:
        if(jCheckBox3.isSelected()){
            spriteRegular.setEnabled(true);
        }
        else{
            spriteRegular.setEnabled(false);
            spriteRegular.setValue(1);
            total();
        }
    }//GEN-LAST:event_jCheckBox3ActionPerformed

    private void spriteRegularStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spriteRegularStateChanged
        // TODO add your handling code here:
        double sPrice1 = 4000;
        int preQty;
        preQty = (Integer)spriteRegular.getValue();
        if(preQty > pizzaQty1){
            pizzaQty1 = preQty;
            sumDrinks[2] = sPrice1 * pizzaQty1;
            totalDrinks = sumDrinks[0] + sumDrinks[1] + sumDrinks[2] + sumDrinks[3] + sumDrinks[4] + sumDrinks[5] + sumDrinks[6] + sumDrinks[7];
        }
        else if(preQty <= pizzaQty1){
            pizzaQty1 = preQty;
            sumDrinks[2] = sPrice1 * pizzaQty1;
            totalDrinks = sumDrinks[0] + sumDrinks[1] + sumDrinks[2] + sumDrinks[3] + sumDrinks[4] + sumDrinks[5] + sumDrinks[6] + sumDrinks[7];
        }
        totalDrinksLabel.setText(String.valueOf(totalDrinks));
        total();
    }//GEN-LAST:event_spriteRegularStateChanged

    private void jCheckBox5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox5ActionPerformed
        // TODO add your handling code here:
        if(jCheckBox5.isSelected()){
            coke1500.setEnabled(true);
        }
        else{
            coke1500.setEnabled(false);
            coke1500.setValue(1);
            total();
        }
    }//GEN-LAST:event_jCheckBox5ActionPerformed

    private void coke1500StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_coke1500StateChanged
        // TODO add your handling code here:
        double sPrice1 = 75;
        int preQty;
        preQty = (Integer)coke1500.getValue();
        if(preQty > pizzaQty1){
            pizzaQty1 = preQty;
            sumDrinks[4] = sPrice1 * pizzaQty1;
            totalDrinks = sumDrinks[0] + sumDrinks[1] + sumDrinks[2] + sumDrinks[3] + sumDrinks[4] + sumDrinks[5] + sumDrinks[6] + sumDrinks[7];
        }
        else if(preQty <= pizzaQty1){
            pizzaQty1 = preQty;
            sumDrinks[4] = sPrice1 * pizzaQty1;
            totalDrinks = sumDrinks[0] + sumDrinks[1] + sumDrinks[2] + sumDrinks[3] + sumDrinks[4] + sumDrinks[5] + sumDrinks[6] + sumDrinks[7];
        }
        totalDrinksLabel.setText(String.valueOf(totalDrinks));
        total();
    }//GEN-LAST:event_coke1500StateChanged

    private void jCheckBox6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox6ActionPerformed
        // TODO add your handling code here:
        if(jCheckBox6.isSelected()){
            pepsi1500.setEnabled(true);
        }
        else{
            pepsi1500.setEnabled(false);
            pepsi1500.setValue(1);
            total();
        }
    }//GEN-LAST:event_jCheckBox6ActionPerformed

    private void pepsi1500StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_pepsi1500StateChanged
        // TODO add your handling code here:
        double sPrice1 = 70;
        int preQty;
        preQty = (Integer)pepsi1500.getValue();
        if(preQty > pizzaQty1){
            pizzaQty1 = preQty;
            sumDrinks[5] = sPrice1 * pizzaQty1;
            totalDrinks = sumDrinks[0] + sumDrinks[1] + sumDrinks[2] + sumDrinks[3] + sumDrinks[4] + sumDrinks[5] + sumDrinks[6] + sumDrinks[7];
        }
        else if(preQty <= pizzaQty1){
            pizzaQty1 = preQty;
            sumDrinks[5] = sPrice1 * pizzaQty1;
            totalDrinks = sumDrinks[0] + sumDrinks[1] + sumDrinks[2] + sumDrinks[3] + sumDrinks[4] + sumDrinks[5] + sumDrinks[6] + sumDrinks[7];
        }
        totalDrinksLabel.setText(String.valueOf(totalDrinks));
        total();
    }//GEN-LAST:event_pepsi1500StateChanged

    private void jCheckBox7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jCheckBox7ActionPerformed
        // TODO add your handling code here:
        if(jCheckBox7.isSelected()){
            sprite1500.setEnabled(true);
        }
        else{
            sprite1500.setEnabled(false);
            sprite1500.setValue(1);
            total();
        }
    }//GEN-LAST:event_jCheckBox7ActionPerformed

    private void sprite1500StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sprite1500StateChanged
        // TODO add your handling code here:
        double sPrice1 = 70;
        int preQty;
        preQty = (Integer)sprite1500.getValue();
        if(preQty > pizzaQty1){
            pizzaQty1 = preQty;
            sumDrinks[6] = sPrice1 * pizzaQty1;
            totalDrinks = sumDrinks[0] + sumDrinks[1] + sumDrinks[2] + sumDrinks[3] + sumDrinks[4] + sumDrinks[5] + sumDrinks[6] + sumDrinks[7];
        }
        else if(preQty <= pizzaQty1){
            pizzaQty1 = preQty;
            sumDrinks[6] = sPrice1 * pizzaQty1;
            totalDrinks = sumDrinks[0] + sumDrinks[1] + sumDrinks[2] + sumDrinks[3] + sumDrinks[4] + sumDrinks[5] + sumDrinks[6] + sumDrinks[7];
        }
        totalDrinksLabel.setText(String.valueOf(totalDrinks));
        total();
    }//GEN-LAST:event_sprite1500StateChanged

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        //operaciones de cambio de lo pagado
        String amount = amountPaidField.getText();
        double paidAmount = Double.parseDouble(amount);
        if(paidAmount < total){
            noteLabel.setText("Note: Paid amount can't be less than TOTAL PAYABLE!!!");
        }
        else{
            changeLabel.setText(String.valueOf(paidAmount - total));
            noteLabel.setText("");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

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
            java.util.logging.Logger.getLogger(PedidosCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PedidosCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PedidosCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PedidosCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                PedidosCliente dialog = new PedidosCliente(new javax.swing.JFrame(), true);
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
    private javax.swing.JTextField amountPaidField;
    private javax.swing.JCheckBox burger13000;
    private javax.swing.JCheckBox burger18000;
    private javax.swing.JCheckBox burger22000;
    private javax.swing.JCheckBox burger25000;
    private javax.swing.JCheckBox burger27500;
    private javax.swing.JCheckBox burger28000;
    private javax.swing.JCheckBox burger29500;
    private javax.swing.JCheckBox burger300;
    private javax.swing.JCheckBox burger425;
    private javax.swing.JCheckBox burger510;
    private javax.swing.JCheckBox burger525;
    private javax.swing.JCheckBox burger540;
    private javax.swing.JLabel burger_1;
    private javax.swing.JLabel burger_10;
    private javax.swing.JLabel burger_11;
    private javax.swing.JLabel burger_12;
    private javax.swing.JLabel burger_2;
    private javax.swing.JLabel burger_3;
    private javax.swing.JLabel burger_4;
    private javax.swing.JLabel burger_5;
    private javax.swing.JLabel burger_6;
    private javax.swing.JLabel burger_7;
    private javax.swing.JLabel burger_8;
    private javax.swing.JLabel burger_9;
    private javax.swing.JLabel changeLabel;
    private javax.swing.JCheckBox checkPizza2800;
    private javax.swing.JCheckBox checkPizza650;
    private javax.swing.JCheckBox checkPizza750;
    private javax.swing.JCheckBox checkPizza830;
    private javax.swing.JCheckBox checkPizza850;
    private javax.swing.JCheckBox checkPizza950;
    private javax.swing.JSpinner coke1500;
    private javax.swing.JSpinner cokeRegular;
    private javax.swing.JButton jButton1;
    private javax.swing.JCheckBox jCheckBox1;
    private javax.swing.JCheckBox jCheckBox2;
    private javax.swing.JCheckBox jCheckBox3;
    private javax.swing.JCheckBox jCheckBox5;
    private javax.swing.JCheckBox jCheckBox6;
    private javax.swing.JCheckBox jCheckBox7;
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
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner10;
    private javax.swing.JSpinner jSpinner11;
    private javax.swing.JSpinner jSpinner12;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JSpinner jSpinner3;
    private javax.swing.JSpinner jSpinner4;
    private javax.swing.JSpinner jSpinner5;
    private javax.swing.JSpinner jSpinner6;
    private javax.swing.JSpinner jSpinner7;
    private javax.swing.JSpinner jSpinner8;
    private javax.swing.JSpinner jSpinner9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JSpinner l25000;
    private javax.swing.JSpinner l2800;
    private javax.swing.JSpinner l28000;
    private javax.swing.JSpinner l28000m;
    private javax.swing.JSpinner l37500;
    private javax.swing.JSpinner l850;
    private javax.swing.JSpinner m15000;
    private javax.swing.JSpinner m22000;
    private javax.swing.JSpinner m24000;
    private javax.swing.JSpinner m24000i;
    private javax.swing.JSpinner m2800;
    private javax.swing.JSpinner m39500;
    private javax.swing.JLabel noteLabel;
    private javax.swing.JSpinner pepsi1500;
    private javax.swing.JSpinner pepsiRegular;
    private javax.swing.JLabel pizza1;
    private javax.swing.JLabel pizza2;
    private javax.swing.JLabel pizza3;
    private javax.swing.JLabel pizza6;
    private javax.swing.JLabel pizza7;
    private javax.swing.JLabel pizza8;
    private javax.swing.JSpinner s10500;
    private javax.swing.JSpinner s16000;
    private javax.swing.JSpinner s18000;
    private javax.swing.JSpinner s19000;
    private javax.swing.JSpinner s2800;
    private javax.swing.JSpinner s37500;
    private javax.swing.JSpinner sprite1500;
    private javax.swing.JSpinner spriteRegular;
    private javax.swing.JLabel subSumLabel;
    private javax.swing.JLabel totalDrinksLabel;
    private javax.swing.JLabel totalPayableLabel;
    private javax.swing.JLabel totalPizzaLabel;
    // End of variables declaration//GEN-END:variables
}
