/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crudmaster;

import java.awt.Point;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.PrintStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import applaundry.Koneksi;
import applaundry.ResultSetTableModel;
import java.awt.event.KeyEvent;
/**
 *
 * @author akrilvha
 */
public class Item_laundry extends javax.swing.JInternalFrame {
    
    ResultSet rs;
    Koneksi dbCon;
    int coltbl, coltbl2;
    String satuan;
    
    private static Item_laundry myInstance;

    public static Item_laundry getInstance() {
        if (myInstance == null) {
            myInstance = new Item_laundry();
        }
        return myInstance;
    }
    
    public void StartRun(){
        dbCon = new Koneksi();
        
        ClearInput();
        loadtable_harga();
        loadtable_pewangi();
        loadcombo_ukuran();
        autoID();
        count_dataTable();
    }
    
    public void autoID(){        
        String kodeHarga = dbCon.autoKode("daftar_harga", "id_item", "PR");
        jTextField1.setText(kodeHarga);
        
        String kodePewangi = dbCon.autoKode("pewangi", "id_pewangi", "PW");
        jTextField6.setText(kodePewangi);
    }
    
     public void ClearInput(){
        jTextField1.setText("");
        jTextField2.setText("");
        jTextField3.setText("");
        jTextField4.setText("");
        jTextField6.setText("");
        jTextField7.setText("");
        jTextField1.disable();
        jTextField6.disable();
        
        jButton1.setVisible(true);
        jButton2.setVisible(false);
        jButton5.setVisible(true);
        jButton6.setVisible(false);
        jButton3.setEnabled(false);
        jButton7.setEnabled(false);

        buttonGroup1.clearSelection();
        
        jComboBox2.setSelectedIndex(0);
        jComboBox3.setSelectedIndex(0);
        
        autoID();
    }
    
    //======================LOAD DATA======================// 
    public void loadtable_harga(){
        rs= dbCon.querySelectAll("daftar_harga");
        tbl_harga.setModel(new ResultSetTableModel(rs));
    }
    
    public void loadtable_pewangi(){
        rs= dbCon.querySelectAll("pewangi");
        tbl_pewangi.setModel(new ResultSetTableModel(rs));
    }
    
    public void loadcombo_ukuran(){
        try{                
            rs = dbCon.eksekusiQuery("select ukuran from daftar_harga group by ukuran");
            while(rs.next()){
                jComboBox2.addItem(rs.getString("ukuran"));
            }
        }catch(Exception ex)
        {}
    }
    
    //======================END LOAD DATA======================// 
    
    public void cariHarga(){
       
        count_dataTable();
    }
    
    public void cariPewangi(){
        
        String cari = jTextField5.getText();
        rs = dbCon.eksekusiQuery("select * from pewangi where nama_pewangi like '%"+cari+"%'");
        tbl_pewangi.setModel(new ResultSetTableModel(rs)); 
        count_dataTable();
    }
    
    public void count_dataTable(){
        coltbl = tbl_harga.getRowCount();
        jl_count_tbl.setText(coltbl+" data ditemukan");
        
        coltbl2 = tbl_pewangi.getRowCount();
        jLabel8.setText(coltbl2+" data ditemukan");
        
        try{                
            rs = dbCon.eksekusiQuery("select Sum(case when ketersediaan = 'Ya' then 1 Else 0 End) as 'avail', Sum(case when ketersediaan = 'Tidak' then 1 else 0 end) as 'notavail' from pewangi");
            while(rs.next()){
               jLabel15.setText(rs.getString("avail")+" Jenis Pewangi");
               jLabel16.setText(rs.getString("notavail")+" Jenis Pewangi");
            }
        }catch(Exception ex)
        {}
    }
    
    public void cari_dftarhrg(){
        String cari = jt_cari.getText();
        String jcombo = (String) jComboBox2.getSelectedItem();
        
//        if (jcombo == null && jcombo == "-Pilih Ukuran-"){
            rs = dbCon.eksekusiQuery("select * from daftar_harga "
            + "where item like '%"+cari+"%'");
            tbl_harga.setModel(new ResultSetTableModel(rs)); 
//        
   
    }
    
    public void cari_cmbBox(){
        String cari = jt_cari.getText();
        String jcombo = (String) jComboBox2.getSelectedItem();
        
        if (cari != null && jcombo != null && jcombo != "-Pilih Ukuran-"){
            rs = dbCon.eksekusiQuery("select * from daftar_harga "
                + "where item like '%"+cari+"%' AND ukuran like '%"+jcombo+"%'");
            tbl_harga.setModel(new ResultSetTableModel(rs));
        } else if (jcombo != null && jcombo != "-Pilih Ukuran-"){
            rs = dbCon.eksekusiQuery("select * from daftar_harga "
                + "where ukuran like '%"+jcombo+"%'");
            tbl_harga.setModel(new ResultSetTableModel(rs));
        }
    }
    
    class ItemChangeListener implements ItemListener{

        @Override
        public void itemStateChanged(ItemEvent e) {
             //To change body of generated methods, choose Tools | Templates.
            if (e.getStateChange() != ItemEvent.SELECTED){
                Object item = e.getItem();
                cari_cmbBox();
            }
        }
        
    }
    /**
     * Creates new form Item_laundry
     */
    public Item_laundry() {
        initComponents();
        StartRun();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_harga = new javax.swing.JTable();
        jl_count_tbl = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jTextField4 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jComboBox2 = new javax.swing.JComboBox();
        jt_cari = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_pewangi = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        jPanel8 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jPanel9 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jComboBox3 = new javax.swing.JComboBox();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel21 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setTitle("Item Laundry");

        tbl_harga.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_harga.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_hargaMouseClicked(evt);
            }
        });
        tbl_harga.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbl_hargaKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_harga);

        jl_count_tbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl_count_tbl.setText("Data Ditemukan");

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane1)
                    .add(jl_count_tbl, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 174, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jl_count_tbl)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jLabel2.setText("Id Item");

        jLabel3.setText("Nama Item");

        jLabel4.setText("Ukuran");

        jLabel5.setText("Satuan");

        jLabel6.setText("Harga");

        jRadioButton1.setText("Pcs");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        jRadioButton2.setText("Kg");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/save.png"))); // NOI18N
        jButton1.setText("Simpan");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Modify.png"))); // NOI18N
        jButton2.setText(" Ubah  ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/delete.png"))); // NOI18N
        jButton3.setText("Hapus");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/batal.png"))); // NOI18N
        jButton4.setText("Batal");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel2)
                            .add(jLabel3))
                        .add(18, 18, 18)
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                                .add(jTextField1)
                                .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 172, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                            .add(jTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 172, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(41, 41, 41)
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel5)
                            .add(jLabel6))
                        .add(23, 23, 23)
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel4Layout.createSequentialGroup()
                                .add(jRadioButton1)
                                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                                .add(jRadioButton2))
                            .add(jTextField4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 176, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(jLabel4))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jButton2)
                    .add(jButton1))
                .add(6, 6, 6)
                .add(jButton3)
                .add(6, 6, 6)
                .add(jButton4)
                .add(17, 17, 17))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .add(9, 9, 9)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel5)
                    .add(jLabel2)
                    .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jRadioButton1)
                    .add(jRadioButton2))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jTextField2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jLabel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                            .add(jLabel6)
                            .add(jTextField4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel4)
                    .add(jTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(3, 3, 3)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jButton2)
                    .add(jButton1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jButton3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jButton4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Pencarian"));

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-Pilih Ukuran-" }));
        jComboBox2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jComboBox2MouseReleased(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jComboBox2MouseClicked(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jComboBox2MouseExited(evt);
            }
        });
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jt_cari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jt_cariActionPerformed(evt);
            }
        });
        jt_cari.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jt_cariKeyReleased(evt);
            }
        });

        jButton9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Refresh.png"))); // NOI18N
        jButton9.setBorderPainted(false);
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jt_cari, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 187, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jComboBox2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton9)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jButton9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 29, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jComboBox2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(jt_cari, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel20.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("DAFTAR HARGA");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 709, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel20, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .add(0, 0, Short.MAX_VALUE))
            .add(jPanel1Layout.createSequentialGroup()
                .add(6, 6, 6)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jLabel20)
                .add(6, 6, 6)
                .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(6, 6, 6)
                .add(jPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Daftar Harga", jPanel1);

        jLabel7.setText("Cari");

        jTextField5.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField5KeyReleased(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel6Layout = new org.jdesktop.layout.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .add(16, 16, 16)
                .add(jLabel7)
                .add(18, 18, 18)
                .add(jTextField5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 182, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel7)
                    .add(jTextField5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tbl_pewangi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbl_pewangi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_pewangiMouseClicked(evt);
            }
        });
        tbl_pewangi.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbl_pewangiKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_pewangi);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("Data Ditemukan");

        org.jdesktop.layout.GroupLayout jPanel7Layout = new org.jdesktop.layout.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane2)
                    .add(jLabel8, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 129, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel8)
                .add(275, 275, 275))
        );

        jLabel9.setText("∑ Pewangi Tersedia :");

        jLabel10.setText("∑ Pewangi Tidak Tersedia :");

        jLabel15.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel15.setText("jLabel15");

        jLabel16.setFont(new java.awt.Font("Lucida Grande", 1, 18)); // NOI18N
        jLabel16.setText("jLabel16");

        org.jdesktop.layout.GroupLayout jPanel8Layout = new org.jdesktop.layout.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel8Layout.createSequentialGroup()
                .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jSeparator1))
                    .add(jPanel8Layout.createSequentialGroup()
                        .add(20, 20, 20)
                        .add(jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel10)
                            .add(jLabel9)
                            .add(jLabel15)
                            .add(jLabel16))
                        .add(0, 80, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel8Layout.createSequentialGroup()
                .add(19, 19, 19)
                .add(jLabel9)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel15)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel10)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jLabel16)
                .addContainerGap(15, Short.MAX_VALUE))
        );

        jLabel12.setText("Id Pewangi");

        jLabel13.setText("Nama Pewangi");

        jLabel14.setText("Ketersediaan");

        jComboBox3.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Ya", "Tidak" }));

        org.jdesktop.layout.GroupLayout jPanel9Layout = new org.jdesktop.layout.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel12)
                    .add(jLabel13)
                    .add(jLabel14))
                .add(23, 23, 23)
                .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jTextField7)
                    .add(jTextField6)
                    .add(jComboBox3, 0, 217, Short.MAX_VALUE))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel9Layout.createSequentialGroup()
                .add(13, 13, 13)
                .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel12)
                    .add(jTextField6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel13)
                    .add(jTextField7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel9Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel14)
                    .add(jComboBox3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(17, Short.MAX_VALUE))
        );

        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/save.png"))); // NOI18N
        jButton5.setText("Simpan");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Modify.png"))); // NOI18N
        jButton6.setText("  Ubah  ");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/delete.png"))); // NOI18N
        jButton7.setText("Hapus");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/batal.png"))); // NOI18N
        jButton8.setText("Batal");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel21.setText("DAFTAR PEWANGGI");

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(6, 6, 6)
                .add(jPanel7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .add(jPanel2Layout.createSequentialGroup()
                .add(6, 6, 6)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jSeparator3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 697, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                        .add(jPanel2Layout.createSequentialGroup()
                            .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                .add(jButton6)
                                .add(jButton5))
                            .add(3, 3, 3)
                            .add(jButton7)
                            .add(1, 1, 1)
                            .add(jButton8))
                        .add(jPanel2Layout.createSequentialGroup()
                            .add(jPanel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(18, 18, 18)
                            .add(jPanel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))))
                .add(0, 6, Short.MAX_VALUE))
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel21, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .add(jLabel21)
                .add(6, 6, 6)
                .add(jSeparator3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(6, 6, 6)
                .add(jPanel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(6, 6, 6)
                .add(jPanel7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 165, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(6, 6, 6)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(18, 18, 18)
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(jButton6)
                    .add(jButton5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jButton7, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jButton8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Daftar Pewangi", jPanel2);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(6, 6, 6)
                .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tbl_hargaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_hargaMouseClicked
        // TODO add your handling code here:

        tbl_harga.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && row != -1) {
                    // your valueChanged overridden method
                    jButton1.setVisible(false);
                    jButton2.setVisible(true);
                    jButton3.setEnabled(true);
                    String ukuran;
                    coltbl = tbl_harga.getSelectedRow();
                    jTextField1.setText(String.valueOf(tbl_harga.getValueAt(coltbl,0)));
                    jTextField2.setText(String.valueOf(tbl_harga.getValueAt(coltbl,1)));
                    if(String.valueOf(tbl_harga.getValueAt(coltbl,2)).isEmpty()){
                        ukuran = "";
                    }
                    else{
                        ukuran = String.valueOf(tbl_harga.getValueAt(coltbl,2));
                    }
                    jTextField3.setText(ukuran);
                    if(String.valueOf(tbl_harga.getValueAt(coltbl,3)) == "pcs"){
                        jRadioButton1.setSelected(true);
                    }
                    else{
                        jRadioButton2.setSelected(true);
                    }
                    jTextField4.setText(String.valueOf(tbl_harga.getValueAt(coltbl,4)));
                }
            }
        });

    }//GEN-LAST:event_tbl_hargaMouseClicked

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void jComboBox2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox2MouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2MouseReleased

    private void jComboBox2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox2MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2MouseClicked

    private void jComboBox2MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jComboBox2MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2MouseExited

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
        jComboBox2.addItemListener(new ItemChangeListener());
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jt_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jt_cariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jt_cariActionPerformed

    private void jt_cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_cariKeyReleased
        // TODO add your handling code here:
        cari_dftarhrg();
    }//GEN-LAST:event_jt_cariKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if(jRadioButton1.isSelected()){
            satuan = "pcs";
        }
        else{
            satuan = "kg";
        }

        try{
            String [] kolom = {"id_item", "item","ukuran",
                "satuan","harga"};
            String [] isi = { jTextField1.getText(),jTextField2.getText(),jTextField3.getText(),
                satuan, jTextField4.getText()};
            System.out.println(dbCon.queryInsert("daftar_harga", kolom, isi));

            JOptionPane.showMessageDialog(this,"Data Sukses Di Simpan");
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(this,"Gagal eksekusi data");
        }

        StartRun();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        if(jRadioButton1.isSelected()){
            satuan = "pcs";
        }
        else{
            satuan = "kg";
        }

        try{
            String [] kolom = {"id_item", "item","ukuran",
                "satuan","harga"};
            String [] isi = { jTextField1.getText(),jTextField2.getText(),jTextField3.getText(),
                satuan, jTextField4.getText()};
            System.out.println(dbCon.queryUpdate("daftar_harga", kolom, isi, "id_item ='"+jTextField1.getText()+"'"));

            JOptionPane.showMessageDialog(this,"Data Sukses Di Simpan");
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(this,"Gagal eksekusi data");
        }

        StartRun();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        String Data = jTextField2.getText();
        int dialog = JOptionPane.showConfirmDialog(rootPane, "Anda yakin ingin menghapus data ini '"+Data+"'?", "WARNING", JOptionPane.YES_NO_OPTION);
        if (dialog == JOptionPane.YES_OPTION){
            try{
                String id=String.valueOf(tbl_harga.getValueAt(tbl_harga.getSelectedRow(),0));
                dbCon.queryDelete("daftar_harga","id_item='"+id+"'");

                JOptionPane.showMessageDialog(this,"Data Sukses Di Hapus");
            }catch (Exception ex) {
                JOptionPane.showMessageDialog(this,"Gagal eksekusi data");
            }

            StartRun();
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        ClearInput();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTextField5KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField5KeyReleased
        // TODO add your handling code here:
        cariPewangi();
    }//GEN-LAST:event_jTextField5KeyReleased

    private void tbl_pewangiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_pewangiMouseClicked
        // TODO add your handling code here:
        tbl_pewangi.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && row != -1) {
                    // your valueChanged overridden method
                    jButton5.setVisible(false);
                    jButton6.setVisible(true);
                    jButton7.setEnabled(true);
                    coltbl2 = tbl_pewangi.getSelectedRow();
                    jTextField6.setText(String.valueOf(tbl_pewangi.getValueAt(coltbl2,0)));
                    jTextField7.setText(String.valueOf(tbl_pewangi.getValueAt(coltbl2,1)));
                    jComboBox3.setSelectedItem(String.valueOf(tbl_pewangi.getValueAt(coltbl2,2)));
                }
            }
        });

    }//GEN-LAST:event_tbl_pewangiMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        String ketersediaan = (String) jComboBox3.getSelectedItem();

        try{
            String [] kolom = {"id_pewangi", "nama_pewangi","ketersediaan"};
            String [] isi = { jTextField6.getText(),jTextField7.getText(), ketersediaan};
            System.out.println(dbCon.queryInsert("pewangi", kolom, isi));

            JOptionPane.showMessageDialog(this,"Data Sukses Di Simpan");
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(this,"Gagal eksekusi data");
        }

        StartRun();

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        String ketersediaan = (String) jComboBox3.getSelectedItem();

        try{
            String [] kolom = {"id_pewangi", "nama_pewangi","ketersediaan"};
            String [] isi = { jTextField6.getText(),jTextField7.getText(), ketersediaan};
            System.out.println(dbCon.queryUpdate("pewangi", kolom, isi, "id_pewangi='"+jTextField6.getText()+"'"));

            JOptionPane.showMessageDialog(this,"Data Sukses Di Simpan");
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(this,"Gagal eksekusi data");
        }

        StartRun();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        String Data = jTextField7.getText();
        int dialog = JOptionPane.showConfirmDialog(rootPane, "Anda yakin ingin menghapus data ini '"+Data+"'?", "WARNING", JOptionPane.YES_NO_OPTION);
        if (dialog == JOptionPane.YES_OPTION){
            try{
                String id=String.valueOf(tbl_pewangi.getValueAt(tbl_pewangi.getSelectedRow(),0));
                dbCon.queryDelete("pewangi","id_pewangi='"+id+"'");

                JOptionPane.showMessageDialog(this,"Data Sukses Di Hapus");
            }catch (Exception ex) {
                JOptionPane.showMessageDialog(this,"Gagal eksekusi data");
            }

            StartRun();
        }
        
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        ClearInput();
    }//GEN-LAST:event_jButton8ActionPerformed

    private void tbl_hargaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_hargaKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            jButton1.setVisible(false);
            jButton2.setVisible(true);
            String ukuran;
            coltbl = tbl_harga.getSelectedRow();
            jTextField1.setText(String.valueOf(tbl_harga.getValueAt(coltbl,0)));
            jTextField2.setText(String.valueOf(tbl_harga.getValueAt(coltbl,1)));
            if(String.valueOf(tbl_harga.getValueAt(coltbl,2)).isEmpty()){
                ukuran = "";
            }
            else{
                ukuran = String.valueOf(tbl_harga.getValueAt(coltbl,2));
            }
            jTextField3.setText(ukuran);
            if(String.valueOf(tbl_harga.getValueAt(coltbl,3)) == "pcs"){
                jRadioButton1.setSelected(true);
            }
            else{
                jRadioButton2.setSelected(true);
            }
            jTextField4.setText(String.valueOf(tbl_harga.getValueAt(coltbl,4)));
        }
    }//GEN-LAST:event_tbl_hargaKeyPressed

    private void tbl_pewangiKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_pewangiKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            jButton5.setVisible(false);
            jButton6.setVisible(true);
            coltbl2 = tbl_pewangi.getSelectedRow();
            jTextField6.setText(String.valueOf(tbl_pewangi.getValueAt(coltbl2,0)));
            jTextField7.setText(String.valueOf(tbl_pewangi.getValueAt(coltbl2,1)));
            jComboBox3.setSelectedItem(String.valueOf(tbl_pewangi.getValueAt(coltbl2,2)));
        }

    }//GEN-LAST:event_tbl_pewangiKeyPressed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        jt_cari.setText("");
        jComboBox2.setSelectedIndex(0);
        count_dataTable();
    }//GEN-LAST:event_jButton9ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    public javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JLabel jl_count_tbl;
    private javax.swing.JTextField jt_cari;
    private javax.swing.JTable tbl_harga;
    private javax.swing.JTable tbl_pewangi;
    // End of variables declaration//GEN-END:variables
}
