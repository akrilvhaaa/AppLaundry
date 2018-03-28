/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crudmaster;

import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import applaundry.Koneksi;
import applaundry.ResultSetTableModel;
/**
 *
 * @author akrilvha
 */
public class Laundry_selesai extends javax.swing.JInternalFrame {
    
    ResultSet rs;
    Koneksi dbCon;
    int coltbl;
    int diterima;
    int diproses;
    int selesai;
    int semua;
    int tahunini;
    int bulanini;
    int ttlLaundryLunas;
    int ttlLaundryBelumLunas;
    int laundryBelumDiambil;
    
    String tmp = "yyyy-MM-dd";
    SimpleDateFormat format = new SimpleDateFormat(tmp); 
    Date dateNow = new Date();
    Map parameter = new HashMap();
    
    private static Laundry_selesai myInstance;
    
    public static Laundry_selesai getInstance() {
        if (myInstance == null) {
            myInstance = new Laundry_selesai();
        }
        return myInstance;
    }
    
    public void StartRun(){
        dbCon = new Koneksi();
        jButton1.setEnabled(false);
        count_dataTable();
        label();
        loadtable_laundry();
    }
    
    public void loadtable_laundry(){
        String pilihan = (String) jComboBox1.getSelectedItem();
        
        if (pilihan.equals("Semua Laundry")){
            jButton1.setEnabled(false);
            try{                
                rs= dbCon.eksekusiQuery("select id_transaksi, nama_pelanggan,"
                        + "tanggal_masuk, tanggal_selesai, total, uang_muka,"
                        + "tanggal_ambil, nama_pengambil from view_laundry");
                jTable1.setModel(new ResultSetTableModel(rs));
            }catch(Exception ex)
            {}
        } else if (pilihan.equals("Laundry Selesai")){
            jButton1.setEnabled(true);
            try{                
                rs= dbCon.eksekusiQuery("select id_transaksi, nama_pelanggan,"
                        + "tanggal_masuk, tanggal_selesai, total, uang_muka,"
                        + "tanggal_ambil, nama_pengambil from view_laundry "
                    + "where status_laundry = 'Selesai' ");
                jTable1.setModel(new ResultSetTableModel(rs));
            }catch(Exception ex)
            {}
        } else if (pilihan.equals("Laundry Belum Diambil")){
            jButton1.setEnabled(true);
            try{                
                rs= dbCon.eksekusiQuery("select id_transaksi, nama_pelanggan,"
                        + "tanggal_masuk, tanggal_selesai, total, uang_muka,"
                        + "tanggal_ambil, nama_pengambil from view_laundry "
                    + "where nama_pengambil = ''");
                jTable1.setModel(new ResultSetTableModel(rs));
            }catch(Exception ex)
            {}
        } else if (pilihan.equals("Laundry Lunas")){
            jButton1.setEnabled(true);
            try{                
                rs= dbCon.eksekusiQuery("select id_transaksi, nama_pelanggan,"
                        + "tanggal_masuk, tanggal_selesai, total, uang_muka,"
                        + "tanggal_ambil, nama_pengambil from view_laundry "
                    + "where status_bayar = 'Lunas'");
                jTable1.setModel(new ResultSetTableModel(rs));
            }catch(Exception ex)
            {}
        }
        
    }
    
    public void count_dataTable(){
        coltbl = jTable1.getRowCount();
        jLabel1.setText(coltbl+" data ditemukan");
    }
    
    public void count_diterima(){
        try {
            rs = dbCon.eksekusiQuery("select count(id_transaksi) from view_laundry where status_laundry = 'Diterima'");
            while(rs.next()){
                diterima = rs.getInt(1);
                //System.out.println(diterima);
            }
        } catch (Exception e) {
        }
    }
    
    public void count_selesai(){
        try {
            rs = dbCon.eksekusiQuery("select count(id_transaksi) from view_laundry where status_laundry = 'Selesai'");
            while(rs.next()){
                selesai = rs.getInt(1);
                //System.out.println(selesai);
            }
        } catch (Exception e) {
        }
    }
    
    public void count_diproses(){
        try {
            rs = dbCon.eksekusiQuery("select count(id_transaksi) from view_laundry where status_laundry = 'Diproses'");
            while(rs.next()){
                diproses = rs.getInt(1);
                //System.out.println(diproses);
            }
        } catch (Exception e) {
        }
    }
    
    public void pendapatanSemua(){
        try {
            rs = dbCon.eksekusiQuery("select sum(total) as total from view_laundry where status_bayar = 'Lunas'");
            while(rs.next()){
                semua = rs.getInt(1);
            }
        } catch (Exception e) {
        }
    }
    
    public void pendapatanTahunIni(){
        try {
            rs = dbCon.eksekusiQuery("select sum(total) as total from view_laundry where status_bayar = 'Lunas'"
                    + "and YEAR(NOW())");
            while(rs.next()){
                tahunini = rs.getInt(1);
            }
        } catch (Exception e) {
        }
    }
    
    public void pendapatanBulanIni(){
        try {
            rs = dbCon.eksekusiQuery("select sum(total) as total from view_laundry where status_bayar = 'Lunas'"
                    + "and MONTH(NOW())");
            while(rs.next()){
                bulanini = rs.getInt(1);
            }
        } catch (Exception e) {
        }
    }
    
    public void TotalLaundryLunas(){
        try {
            rs = dbCon.eksekusiQuery("select count(id_transaksi) as total_lunas from view_laundry where status_bayar = 'Lunas'");
            while(rs.next()){
                ttlLaundryLunas = rs.getInt(1);
            }
        } catch (Exception e) {
        }
    }
    
    public void TotalLaundryBelumLunas(){
        try {
            rs = dbCon.eksekusiQuery("select count(id_transaksi) as belum_lunas from view_laundry where status_bayar = 'Belum Lunas'");
            while(rs.next()){
                ttlLaundryBelumLunas = rs.getInt(1);
            }
        } catch (Exception e) {
        }
    }
    
    public void LaundryBelumDiambil(){
        try {
            rs = dbCon.eksekusiQuery("select count(id_transaksi) as ttl from view_laundry where status_laundry = 'Selesai'"
                    + "and nama_pengambil IS NULL");
            while(rs.next()){
                laundryBelumDiambil = rs.getInt(1);
            }
        } catch (Exception e) {
        }
    }
    
    public void label(){
        count_diproses();
        count_diterima();
        count_selesai();
        jLabel18.setText(String.valueOf(diterima));
        jLabel23.setText(String.valueOf(selesai));
        jLabel19.setText(String.valueOf(diproses));
        
        pendapatanSemua();
        jLabel10.setText(String.valueOf(semua));
        
        pendapatanTahunIni();
        jLabel11.setText(String.valueOf(tahunini));
        
        pendapatanBulanIni();
        jLabel12.setText(String.valueOf(bulanini));
        
        TotalLaundryLunas();
        jLabel25.setText(String.valueOf(ttlLaundryLunas));
        
        TotalLaundryBelumLunas();
        jLabel27.setText(String.valueOf(ttlLaundryBelumLunas));
        
        LaundryBelumDiambil();
        jLabel26.setText(String.valueOf(laundryBelumDiambil));
    }
    
    public void cari() {
        String cari = jTextField1.getText();
        String pilihan = (String) jComboBox1.getSelectedItem();

        if (pilihan.equals("Semua Laundry")) {
            try {
                rs = dbCon.eksekusiQuery("select id_transaksi, nama_pelanggan,"
                        + "tanggal_masuk, tanggal_selesai, total, uang_muka,"
                        + "tanggal_ambil, nama_pengambil from view_laundry where nama_pelanggan like '%"+cari+"%' OR id_transaksi like '%"+cari+"%'");
                jTable1.setModel(new ResultSetTableModel(rs));
            } catch (Exception ex) {
            }
        } else if (pilihan.equals("Laundry Selesai")) {
            jButton1.setEnabled(true);
            try {
                rs = dbCon.eksekusiQuery("select id_transaksi, nama_pelanggan,"
                        + "tanggal_masuk, tanggal_selesai, total, uang_muka,"
                        + "tanggal_ambil, nama_pengambil from view_laundry "
                        + "where status_laundry = 'Selesai' and nama_pelanggan like '%"+cari+"%' OR id_transaksi like '%"+cari+"%'");
                jTable1.setModel(new ResultSetTableModel(rs));
            } catch (Exception ex) {
            }
        } else if (pilihan.equals("Laundry Belum Diambil")) {
            jButton1.setEnabled(true);
            try {
                rs = dbCon.eksekusiQuery("select id_transaksi, nama_pelanggan,"
                        + "tanggal_masuk, tanggal_selesai, total, uang_muka,"
                        + "tanggal_ambil, nama_pengambil from view_laundry "
                        + "where nama_pengambil = '' and nama_pelanggan like '%"+cari+"%' OR id_transaksi like '%"+cari+"%'");
                jTable1.setModel(new ResultSetTableModel(rs));
            } catch (Exception ex) {
            }
        } else if (pilihan.equals("Laundry Lunas")) {
            jButton1.setEnabled(true);
            try {
                rs = dbCon.eksekusiQuery("select id_transaksi, nama_pelanggan,"
                        + "tanggal_masuk, tanggal_selesai, total, uang_muka,"
                        + "tanggal_ambil, nama_pengambil from view_laundry "
                        + "where status_bayar = 'Lunas' and nama_pelanggan like '%"+cari+"%' OR id_transaksi like '%"+cari+"%'");
                jTable1.setModel(new ResultSetTableModel(rs));
            } catch (Exception ex) {
            }
        }
        count_dataTable();
    }
    
    /**
     * Creates new form Laundry_selesai
     */
    public Laundry_selesai() {
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

        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel17 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jLabel23 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jSeparator9 = new javax.swing.JSeparator();
        jLabel27 = new javax.swing.JLabel();
        jSeparator10 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox();

        setClosable(true);
        setIconifiable(true);
        setTitle("Data Laundry");
        setToolTipText("");
        setAutoscrolls(true);

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane2.setAutoscrolls(true);

        jLabel20.setFont(new java.awt.Font("Lucida Grande", 0, 24)); // NOI18N
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel20.setText("DATA LAUNDRY");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Data Ditemukan");

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .add(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 338, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jLabel1)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Informasi Pendapatan Laundry"));
        jPanel3.setName(""); // NOI18N

        jLabel6.setText("∑ Semua Pendapatan");

        jLabel7.setText("∑ Pendapatan Tahun ini");

        jLabel8.setText("∑ Pendapatan Bulan ini");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("jLabel10");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel12.setText("jLabel12");

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel13.setText("Rp");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel15.setText("Rp");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("jLabel11");

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel17.setText("Rp");

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(10, 10, 10)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jLabel6)
                        .add(58, 58, 58)
                        .add(jLabel13)
                        .add(6, 6, 6)
                        .add(jLabel10))
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jLabel7)
                        .add(39, 39, 39)
                        .add(jLabel17)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jLabel11))
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jLabel8)
                        .add(44, 44, 44)
                        .add(jLabel15)
                        .add(6, 6, 6)
                        .add(jLabel12))))
            .add(jPanel3Layout.createSequentialGroup()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jSeparator1)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jSeparator6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 296, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 296, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .add(11, 11, 11)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jLabel6)
                    .add(jLabel13)
                    .add(jLabel10))
                .add(6, 6, 6)
                .add(jSeparator1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(6, 6, 6)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel7)
                    .add(jLabel17)
                    .add(jLabel11))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jSeparator3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jSeparator6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(8, 8, 8)
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jLabel15)
                        .add(jLabel12))
                    .add(jLabel8))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Informasi Pengerjaan Laundry"));
        jPanel4.setName(""); // NOI18N

        jLabel9.setText("∑ Laundry diterima");

        jLabel14.setText("∑ Laundry Selesai");

        jLabel16.setText("∑ Laundry diproses");

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel18.setText("jLabel10");

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel19.setText("jLabel12");

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel23.setText("jLabel11");

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .add(10, 10, 10)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(jLabel9)
                        .add(49, 49, 49)
                        .add(jLabel18))
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(jLabel14)
                        .add(58, 58, 58)
                        .add(jLabel23))
                    .add(jPanel4Layout.createSequentialGroup()
                        .add(jLabel16)
                        .add(47, 47, 47)
                        .add(jLabel19))))
            .add(jPanel4Layout.createSequentialGroup()
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jSeparator7))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jSeparator4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 258, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(50, 50, 50)
                .add(jSeparator5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 296, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel4Layout.createSequentialGroup()
                .add(11, 11, 11)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel9)
                    .add(jLabel18))
                .add(6, 6, 6)
                .add(jSeparator4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(6, 6, 6)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel23)
                    .add(jLabel14))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jSeparator5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jSeparator7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(8, 8, 8)
                .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel16)
                    .add(jLabel19))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Informasi Transaksi Laundry"));
        jPanel5.setName(""); // NOI18N

        jLabel21.setText("∑ Laundry Lunas");

        jLabel22.setText("∑ Laundry Belum Lunas");

        jLabel24.setText("∑ Laundry Belum Diambil");

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel25.setText("jLabel10");

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel26.setText("jLabel12");

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel27.setText("jLabel11");

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .add(10, 10, 10)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel5Layout.createSequentialGroup()
                        .add(jLabel21)
                        .add(69, 69, 69)
                        .add(jLabel25))
                    .add(jPanel5Layout.createSequentialGroup()
                        .add(jLabel22)
                        .add(30, 30, 30)
                        .add(jLabel27))
                    .add(jPanel5Layout.createSequentialGroup()
                        .add(jLabel24)
                        .add(18, 18, 18)
                        .add(jLabel26))))
            .add(jPanel5Layout.createSequentialGroup()
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel5Layout.createSequentialGroup()
                        .addContainerGap()
                        .add(jSeparator10))
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jSeparator8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 258, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(50, 50, 50)
                .add(jSeparator9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 296, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .add(11, 11, 11)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel21)
                    .add(jLabel25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 14, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(6, 6, 6)
                .add(jSeparator8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(6, 6, 6)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel22)
                    .add(jLabel27))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(jSeparator9, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jSeparator10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 10, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(8, 8, 8)
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel24)
                    .add(jLabel26))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/print.png"))); // NOI18N
        jButton1.setText("print");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setText("Cari");

        jTextField1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextField1KeyReleased(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 226, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel2)
                    .add(jTextField1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Semua Laundry", "Laundry Selesai", "Laundry Belum Diambil", "Laundry Lunas" }));
        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });
        jComboBox1.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jComboBox1PropertyChange(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel6Layout = new org.jdesktop.layout.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jSeparator2)
                    .add(jLabel20, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel6Layout.createSequentialGroup()
                        .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 273, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 327, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 273, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(0, 37, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel6Layout.createSequentialGroup()
                        .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                        .add(jButton1)
                        .add(14, 14, 14)))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel20)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(jButton1)
                        .add(jComboBox1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel6);

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 949, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 490, Short.MAX_VALUE)
                .add(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int param = jComboBox1.getSelectedIndex();
            
        if (param == 1){
            String reportName="LaundrySelesai";
            dbCon.createReport(reportName, null, false);
        } else if (param == 2){
            String reportName="LaundryBelumDiambil";
            dbCon.createReport(reportName, null, false);
        } else if (param == 3){
            String reportName="LaundryLunas";
            dbCon.createReport(reportName, null, false);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jComboBox1PropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jComboBox1PropertyChange
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox1PropertyChange

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        // TODO add your handling code here:
        loadtable_laundry();
        count_dataTable();
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void jTextField1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextField1KeyReleased
        // TODO add your handling code here:
        cari();
    }//GEN-LAST:event_jTextField1KeyReleased

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    // End of variables declaration//GEN-END:variables
}
