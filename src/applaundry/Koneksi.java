/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package applaundry;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;
import java.awt.Component;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.view.JasperViewer;
import sun.nio.cs.ext.ISCII91;

/**
 *
 * @author Bolehjadi
 */
public class Koneksi {

    String user="root";
    String pass="";
    String dbname = "dblaundry";
    String SQL;
    ResultSet rs=null;
    Connection cn = null;
    Statement st=null;
    Map parameter = new HashMap();
    private Component rootPane;
    
    public void  koneksi() {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Belum bisa inisialisasi driver");
        }
        try
        {
            cn=(Connection) DriverManager.getConnection("jdbc:mysql://localhost/"+dbname,user,pass);
            st=(Statement) cn.createStatement();

        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, "Belum ada koneksi ke db");
        }
    }
    
    public java.sql.Connection closeKoneksi() {
        
        try {
           cn.close();
        } catch (Exception e) {
           
        }
        return cn;
    }

    public java.sql.Connection getConnection() {
        return cn;
    }
    
    
    public ResultSet eksekusiQuery(String sql) {
        
        koneksi();  
        
            try {
                st = (Statement) cn.createStatement();
                rs = st.executeQuery(sql);
                System.out.println(sql);
            } catch (SQLException ex) {
            }
        return rs;   
    }

    public String eksekusiUpdate(String sql) {
       
       koneksi();
       String result = "";
       
            try {
                st = (Statement) cn.createStatement();
                st.executeUpdate(sql);
            } catch (SQLException ex) {
                result = ex.toString();
            }
            
        return result;

    }

//Fungsi untuk eksekusi query select semua kolom
    public ResultSet querySelectAll(String namaTabel) {
        
        koneksi();
        SQL = "SELECT * FROM " + namaTabel;
        System.out.println(SQL);
        return this.eksekusiQuery(SQL);
        
    }

//Overload fungsi untuk eksekusi query select semua kolom dengan where
    public ResultSet querySelectAll(String namaTabel, String kondisi) {
        
        koneksi();
        SQL = "SELECT * FROM " + namaTabel + " WHERE " + kondisi;
        return this.eksekusiQuery(SQL);
        
    }

//Fungsi untuk eksekusi query select dengan kolom spesifik
    public ResultSet querySelect(String[] namaKolom, String namaTabel) {
        
        koneksi();
        int i;
        SQL = "SELECT ";
        
        for (i = 0; i <= namaKolom.length - 1; i++) {
            SQL += namaKolom[i];
            if (i < namaKolom.length - 1) {
                SQL += ",";
            }
        }
        
        SQL += " FROM " + namaTabel;
        return this.eksekusiQuery(SQL);
        
    }

//Overload fungsi untuk eksekusi query select dengan kolom spesifik dengan where
    public ResultSet fcSelectCommand(String[] namaKolom, String namaTabel, String kondisi) {
        
        koneksi();
        int i;
        SQL = "SELECT ";
        
        for (i = 0; i <= namaKolom.length - 1; i++) {
            SQL += namaKolom[i];
            if (i < namaKolom.length - 1) {
                SQL += ",";
            }
        }

        SQL += " FROM " + namaTabel + " WHERE " + kondisi;
        return this.eksekusiQuery(SQL);

    }
    
 public String queryInsert(String namaTabel, String[] isiTabel) {
        
        koneksi();
        int i;
        SQL = "INSERT INTO " + namaTabel + " VALUES(";

        for (i = 0; i <= isiTabel.length - 1; i++) {
            SQL += "'" + isiTabel[i] + "'";
            if (i < isiTabel.length - 1) {
                SQL += ",";
            }
        }

        SQL += ")";
        return this.eksekusiUpdate(SQL);
        
    }
//Fungsi eksekusi query insert
    public String queryInsert(String namaTabel, String[] namaKolom,String[] isiTabel) {
        
        koneksi();
        int i;
        SQL = "INSERT INTO " + namaTabel +"("; 
        for (i = 0; i <= namaKolom.length - 1; i++) {
            SQL +=namaKolom[i];
            if (i < namaKolom.length - 1) {
                SQL += ",";
            }
        }
        SQL+=") VALUES(";
        for (i = 0; i <= isiTabel.length - 1; i++) {
            SQL += "'" + isiTabel[i] + "'";
            if (i < isiTabel.length - 1) {
                SQL += ",";
            }
        }

        SQL += ")";
        return this.eksekusiUpdate(SQL);
        
    }

//Fungsi eksekusi query update
    public String queryUpdate(String namaTabel, String[] namaKolom, String[] isiTabel, String kondisi) {
        
        koneksi();
        int i;
        SQL = "UPDATE " + namaTabel + " SET ";

        for (i = 0; i <= namaKolom.length - 1; i++) {
            SQL += namaKolom[i] + "='" + isiTabel[i] + "'";
            if (i < namaKolom.length - 1) {
                SQL += ",";
            }
        }

        SQL += " WHERE " + kondisi;
        return this.eksekusiUpdate(SQL);
        
    }

//Fungsi eksekusi query delete
    public String queryDelete(String namaTabel) {
       
        koneksi();
        SQL = "DELETE FROM " + namaTabel;
        return this.eksekusiUpdate(SQL);
        
    }

//Overload fungsi eksekusi query delete dengan where
    public String queryDelete(String namaTabel, String kondisi) {
        
        koneksi();
        SQL = "DELETE FROM " + namaTabel + " WHERE " + kondisi;
        return this.eksekusiUpdate(SQL);

    }
    
    public String autoKode(String namaTabel, String kolom, String charkode){
        String kode=null;
        try{    
            String no;
            rs = this.eksekusiQuery("select "+kolom+" as no from "+namaTabel+" order by "+kolom+" DESC LIMIT 1");            
            if(!rs.first()){
               int autoNum = 1;
               if(charkode.length() < 3){
                    kode = charkode+String.format("%03d", autoNum);
               }
               else if(charkode.length() >2){
                   kode = charkode+String.format("%04d", autoNum);
               }
            }   
            else {
               if(charkode.length() < 3){
                    no = rs.getString("no").substring(2,5);
                    int autoNum = Integer.parseInt(no) + 1; 
                    kode = charkode+String.format("%03d", autoNum);
               }
               else if(charkode.length() >2){
                   no = rs.getString("no").substring(3,7);
                   int autoNum = Integer.parseInt(no) + 1; 
                   kode = charkode+String.format("%04d", autoNum);
               }
            }
            
        }catch(Exception ex)
        {}
        return kode;
    }
    
    public void createReport(String reportName, String[][]param, boolean getPrint){  
        try {
            
            String path="./src/report/"+reportName+".jasper";
            
            if(param!=null){
                for(int i=0; i<param.length; i++){
                    parameter.put(param[i][0],param[i][1]); 
                }
            }
            
            JasperPrint print = JasperFillManager.fillReport(path,parameter, this.cn);
            if(getPrint==true){
                //direct print
                JasperPrintManager.printPage(print,0, true);
            }
            else if(getPrint==false){
                //didn't direct print
                JasperViewer.viewReport(print, false);
            }
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(rootPane,"Dokumen Tidak Ada"+ex);
        }
    }
}
