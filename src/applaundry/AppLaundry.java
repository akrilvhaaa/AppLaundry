/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package applaundry;

import com.jtattoo.plaf.graphite.GraphiteLookAndFeel;
import crudmaster.menu_utama;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author akrilvha
 */
public class AppLaundry {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        try {
            UIManager.setLookAndFeel((new GraphiteLookAndFeel()));
            new menu_utama().setVisible(true);
        }
        catch(UnsupportedLookAndFeelException ex){
            JOptionPane.showMessageDialog(null, "tidak berfungsi");
     }
        
    }
    
}
