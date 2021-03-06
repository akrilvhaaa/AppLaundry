/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crudmaster;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import applaundry.Koneksi;
import applaundry.ResultSetTableModel;
import java.awt.Color;
import java.awt.event.KeyEvent;
/**
 *
 * @author akrilvha
 */
public class Karyawan extends javax.swing.JInternalFrame {
    
     ResultSet rs;
    Koneksi dbCon;
    int coltbl;
    String jenis_kelamin, result, id;
     
    String tmp = "yyyy-MM-dd";
    SimpleDateFormat format = new SimpleDateFormat(tmp); 
    Date dateNow = new Date();
    
    private static Karyawan myInstance;

    public static Karyawan getInstance() {
        if (myInstance == null) {
            myInstance = new Karyawan();
        }
        return myInstance;
    }
    
    public void StartRun(){
        dbCon = new Koneksi();
        
        ClearInput();
        loadtable_karyawan();
        autoID();
        count_dataTable();
        
        //design
        //jPanel5.setBackground(new Color(0.0f, 0.0f, 0.0f, 0.5f));
    }
    
    public void autoID(){
        try
         {
             rs = dbCon.eksekusiQuery("select max(id_karyawan) from karyawan");
             while(rs.next())
             {
                 int no = rs.getInt(1);
                 result = Integer.toString(no+1);
                 jt_id.setText(result);
                 jt_id.setEnabled(false);
             }
         }
         catch (Exception ex)
         {}
    }
    
    public void ClearInput(){
        jt_id.setText("");
        jt_nama.setText("");
        jt_telp.setText("");
        jr_laki.setSelected(false);
        jr_perempuan.setSelected(false);
        jdt_mulai_kerja.setDate(dateNow);
        ja_alamat.setText("");
        jButton2.setVisible(false);
        btn_simpan.setVisible(true);
        jt_cari.setText("");
    }
    
    public void loadtable_karyawan(){
        rs= dbCon.querySelectAll("karyawan");
        tbl_karyawan.setModel(new ResultSetTableModel(rs));
    }

    public void cari(){
        String cari = jt_cari.getText();
        
        rs = dbCon.eksekusiQuery("select * from karyawan "
                + "where id_karyawan like '%"+cari+"%' OR nama like '%"+cari+"%'");
        tbl_karyawan.setModel(new ResultSetTableModel(rs)); 
        
        count_dataTable();
    }
    
    public void count_dataTable(){
        coltbl = tbl_karyawan.getRowCount();
        jl_count_tbl.setText(coltbl+" data ditemukan");
    }
    /**
     * Creates new form Karyawan
     */
    public Karyawan() {
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jt_id = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jt_nama = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jr_laki = new javax.swing.JRadioButton();
        jr_perempuan = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        jt_telp = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        ja_alamat = new javax.swing.JTextArea();
        jdt_mulai_kerja = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_karyawan = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jt_cari = new javax.swing.JTextField();
        jl_count_tbl = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jPanel5 = new javax.swing.JPanel();
        btn_simpan = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        btn_batal = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setTitle("Karyawan");

        jLabel1.setText("Id Karyawan");

        jLabel2.setText("Nama Karyawan");

        jLabel3.setText("Jenis Kelamin");

        jr_laki.setText("Laki-laki");

        jr_perempuan.setText("Perempuan");

        jLabel4.setText("Telp");

        jLabel6.setText("Tanggal Kerja");

        jLabel5.setText("Alamat");

        ja_alamat.setColumns(20);
        ja_alamat.setRows(5);
        jScrollPane1.setViewportView(ja_alamat);

        org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(6, 6, 6)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jLabel1)
                        .add(52, 52, 52)
                        .add(jt_id, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 255, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(23, 23, 23)
                        .add(jLabel6)
                        .add(18, 18, 18)
                        .add(jdt_mulai_kerja, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 192, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel2)
                            .add(jLabel3)
                            .add(jLabel4))
                        .add(28, 28, 28)
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jt_nama, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 255, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jr_laki)
                                .add(6, 6, 6)
                                .add(jr_perempuan))
                            .add(jt_telp, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 255, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .add(23, 23, 23)
                        .add(jLabel5)
                        .add(59, 59, 59)
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 291, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel1Layout.createSequentialGroup()
                .add(6, 6, 6)
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(6, 6, 6)
                        .add(jLabel1))
                    .add(jt_id, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 28, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(jdt_mulai_kerja, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel1Layout.createSequentialGroup()
                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(jt_nama, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                .add(6, 6, 6)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jr_laki)
                                    .add(jr_perempuan)
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(26, 26, 26)
                                        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                                            .add(jt_telp, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                                            .add(jLabel4)))))
                            .add(jPanel1Layout.createSequentialGroup()
                                .add(6, 6, 6)
                                .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                                    .add(jPanel1Layout.createSequentialGroup()
                                        .add(jLabel2)
                                        .add(12, 12, 12)
                                        .add(jLabel3))
                                    .add(jLabel5))))
                        .add(2, 6, Short.MAX_VALUE))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())))
        );

        tbl_karyawan.setModel(new javax.swing.table.DefaultTableModel(
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
        tbl_karyawan.setToolTipText("Klik 2 Kali untuk Edit");
        tbl_karyawan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbl_karyawanMouseClicked(evt);
            }
        });
        tbl_karyawan.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tbl_karyawanKeyPressed(evt);
            }
        });
        jScrollPane2.setViewportView(tbl_karyawan);

        jLabel7.setText("Cari");

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

        org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jLabel7)
                .add(26, 26, 26)
                .add(jt_cari, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 182, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(jLabel7)
                    .add(jt_cari, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jl_count_tbl.setFont(new java.awt.Font("Lucida Grande", 1, 13)); // NOI18N
        jl_count_tbl.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jl_count_tbl.setText("Data Ditemukan");

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jl_count_tbl, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane2)
                    .add(jPanel3Layout.createSequentialGroup()
                        .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
                .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(jScrollPane2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 164, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jl_count_tbl, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 24, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(0, 102, 153));

        org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 61, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 0, Short.MAX_VALUE)
        );

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/karyawan.png"))); // NOI18N
        jLabel8.setText(" ");

        jLabel20.setFont(new java.awt.Font("Abadi MT Condensed Extra Bold", 0, 36)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(153, 0, 0));
        jLabel20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel20.setText("DATA KARYAWAN");

        org.jdesktop.layout.GroupLayout jPanel6Layout = new org.jdesktop.layout.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .add(jPanel4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(9, 9, 9)
                .add(jLabel8, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 43, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(7, 7, 7)
                .add(jLabel20)
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .add(jSeparator2)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel8, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 47, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, jLabel20))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jSeparator2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );

        jPanel5.setBackground(new java.awt.Color(102, 102, 102));

        btn_simpan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/save.png"))); // NOI18N
        btn_simpan.setText("Simpan");
        btn_simpan.setToolTipText("Simpan Data Baru");
        btn_simpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_simpanActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Modify.png"))); // NOI18N
        jButton2.setText("  Ubah  ");
        jButton2.setToolTipText("Simpan Perubahan");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/delete.png"))); // NOI18N
        jButton1.setText("Hapus");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        btn_batal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/batal.png"))); // NOI18N
        btn_batal.setText("Batal");
        btn_batal.setToolTipText("");
        btn_batal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_batalActionPerformed(evt);
            }
        });

        jButton11.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Refresh.png"))); // NOI18N
        jButton11.setText("Refresh");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jButton11)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .add(btn_simpan)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton2)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jButton1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(btn_batal)
                .add(12, 12, 12))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(btn_batal, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                        .add(btn_simpan)
                        .add(jButton2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jButton1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(jButton11)))
                .addContainerGap())
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(jPanel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .add(jPanel6, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jPanel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .add(0, 3, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_simpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_simpanActionPerformed
        // TODO add your handling code here:
        if(jr_laki.isSelected()){
            jenis_kelamin = "L";
        }
        else{
            jenis_kelamin = "P";
        }

        try{
            String [] kolom = {"id_karyawan", "nama","jenis_kelamin",
                "telp","alamat","tgl_mulai_kerja"};
            String [] isi = { jt_id.getText(),jt_nama.getText(),jenis_kelamin,
                jt_telp.getText(), ja_alamat.getText(),
                String.valueOf(format.format(jdt_mulai_kerja.getDate()))};
            System.out.println(dbCon.queryInsert("karyawan", kolom, isi));

            JOptionPane.showMessageDialog(this,"Data Sukses Di Simpan");
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(this,"Gagal eksekusi data");
        }

        StartRun();
    }//GEN-LAST:event_btn_simpanActionPerformed

    private void btn_batalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_batalActionPerformed
        // TODO add your handling code here:
        ClearInput();
        autoID();
        loadtable_karyawan();
    }//GEN-LAST:event_btn_batalActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int dialogResult = JOptionPane.showConfirmDialog(this, "Apakah anda yakin untuk menghapus data ini ?", "", dialogButton);
        if (dialogResult == 0) {
            try {
                String id = String.valueOf(tbl_karyawan.getValueAt(tbl_karyawan.getSelectedRow(), 0));
                dbCon.queryDelete("karyawan", "id_karyawan='" + id + "'");

                JOptionPane.showMessageDialog(this, "Data Sukses Di Hapus");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Gagal eksekusi data");
            }
            StartRun();
        } else {
            System.out.println("No Option");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try{

            if(jr_laki.isSelected()){
                jenis_kelamin = "L";
            }
            else{
                jenis_kelamin = "P";
            }

            String [] kolom = {"id_karyawan", "nama","jenis_kelamin",
                "telp","alamat","tgl_mulai_kerja"};
            String [] isi = { jt_id.getText(),jt_nama.getText(),jenis_kelamin,
                jt_telp.getText(), ja_alamat.getText(),
                String.valueOf(format.format(jdt_mulai_kerja.getDate()))};

            System.out.println(dbCon.queryUpdate("karyawan", kolom, isi, "id_karyawan ='"+jt_id.getText()+"'"));

            JOptionPane.showMessageDialog(this,"Data Sukses Di Simpan");
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(this,"Gagal eksekusi data");
        }

        StartRun();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tbl_karyawanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_karyawanMouseClicked
        // TODO add your handling code here:
        tbl_karyawan.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                JTable table =(JTable) mouseEvent.getSource();
                Point point = mouseEvent.getPoint();
                int row = table.rowAtPoint(point);
                if (mouseEvent.getClickCount() == 2 && row != -1) {
                    // your valueChanged overridden method
                    btn_simpan.setVisible(false);
                    jButton2.setVisible(true);
                    coltbl = tbl_karyawan.getSelectedRow();
                    jt_id.setText(String.valueOf(tbl_karyawan.getValueAt(coltbl, 0)));
                    jt_nama.setText(String.valueOf(tbl_karyawan.getValueAt(coltbl, 1)));
                    jt_telp.setText(String.valueOf(tbl_karyawan.getValueAt(coltbl, 3)));
                    ja_alamat.setText(String.valueOf(tbl_karyawan.getValueAt(coltbl, 4)));
                    jdt_mulai_kerja.setDate((Date) tbl_karyawan.getValueAt(coltbl, 5));

                    jenis_kelamin = String.valueOf(tbl_karyawan.getValueAt(coltbl, 2));

                    switch(jenis_kelamin){
                        case "L": jr_laki.setSelected(true);
                        break;
                        case "P": jr_perempuan.setSelected(true);
                        break;
                    }
                }
            }
        });

    }//GEN-LAST:event_tbl_karyawanMouseClicked

    private void jt_cariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jt_cariActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jt_cariActionPerformed

    private void jt_cariKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jt_cariKeyReleased
        // TODO add your handling code here:
        cari();
    }//GEN-LAST:event_jt_cariKeyReleased

    private void tbl_karyawanKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbl_karyawanKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            btn_simpan.setVisible(false);
            jButton2.setVisible(true);
            coltbl = tbl_karyawan.getSelectedRow();
            jt_id.setText(String.valueOf(tbl_karyawan.getValueAt(coltbl, 0)));
            jt_nama.setText(String.valueOf(tbl_karyawan.getValueAt(coltbl, 1)));
            jt_telp.setText(String.valueOf(tbl_karyawan.getValueAt(coltbl, 3)));
            ja_alamat.setText(String.valueOf(tbl_karyawan.getValueAt(coltbl, 4)));
            jdt_mulai_kerja.setDate((Date) tbl_karyawan.getValueAt(coltbl, 5));

            jenis_kelamin = String.valueOf(tbl_karyawan.getValueAt(coltbl, 2));

            switch (jenis_kelamin) {
                case "L":
                    jr_laki.setSelected(true);
                    break;
                case "P":
                    jr_perempuan.setSelected(true);
                    break;
            }
        }
    }//GEN-LAST:event_tbl_karyawanKeyPressed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        loadtable_karyawan();
        count_dataTable();
    }//GEN-LAST:event_jButton11ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_batal;
    private javax.swing.JButton btn_simpan;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTextArea ja_alamat;
    private com.toedter.calendar.JDateChooser jdt_mulai_kerja;
    private javax.swing.JLabel jl_count_tbl;
    private javax.swing.JRadioButton jr_laki;
    private javax.swing.JRadioButton jr_perempuan;
    private javax.swing.JTextField jt_cari;
    private javax.swing.JTextField jt_id;
    private javax.swing.JTextField jt_nama;
    private javax.swing.JTextField jt_telp;
    private javax.swing.JTable tbl_karyawan;
    // End of variables declaration//GEN-END:variables
}
