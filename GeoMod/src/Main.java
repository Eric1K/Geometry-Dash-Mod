import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

@SuppressWarnings("serial")
public class Main extends JPanel implements ActionListener {
	//TODO ADD DESIRED SCREEN RESOLUTION AND FIX NO FILE CHOSEN

    JLabel madeby = new JLabel();
   JButton install = new JButton("Install Mod");
   JButton restore = new JButton("Remove Mod");
   JLabel credit = new JLabel();
   
  public Main() {
	  

     add(madeby);
     madeby.setText("©2019 Made by Eric Feng");
    add(credit);
    credit.setText("Mod by Italian APK Downloader");
     add(install);
    add(restore);
     
    install.addActionListener(new ActionListener()
    {
    	 public void actionPerformed(ActionEvent e)
    	 {
    		 FileManager.Install();
    	 }
    });
    
    restore.addActionListener(new ActionListener()
    {
    	 public void actionPerformed(ActionEvent e)
    	 {
    		 FileManager.Restore();
    	 }
    });
    
    
   }

  public void actionPerformed(ActionEvent e) {
 
  }
   
  public Dimension getPreferredSize(){
          return new Dimension(330, 630);
    }
    

  public static void main(String s[]) {
	  try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e1) {
			
			e1.printStackTrace();
			System.out.println("Missing Libraries... Try redownloading?");
		} catch (InstantiationException e1) {
			
			e1.printStackTrace();
			System.out.println("Failed to instantiate");
		} catch (IllegalAccessException e1) {
			
			e1.printStackTrace();
			System.out.println("Cannot access UI");
		} catch (UnsupportedLookAndFeelException e1) {
			
			e1.printStackTrace();
			System.out.println("Unsupported UI");
		}
	  int response = JOptionPane.showConfirmDialog(null, "Do you want to install/restore the ingame mod menu?", "Confirm",
		        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		    if (response == JOptionPane.NO_OPTION) {
		      System.out.println("No button clicked");
		    } else if (response == JOptionPane.YES_OPTION) {
		      System.out.println("Yes button clicked");
		    } else if (response == JOptionPane.CLOSED_OPTION) {
		      System.out.println("JOptionPane closed");
		    }
	 if(response == JOptionPane.YES_OPTION)
	 {
		 JFrame frame = new JFrame("Geometry Dash Mod Wizard");
		    Main panel = new Main();
		    frame.addWindowListener(
		      new WindowAdapter() {
		        public void windowClosing(WindowEvent e) {
		          System.exit(0);
		          }
		        }
		      );
		    frame.getContentPane().add(panel,"Center");
		    frame.setSize(panel.getPreferredSize());
		    frame.setVisible(true);
	 }else
	 {
		 //Process proc = Runtime.getRuntime().exec("Launcher.jar");
		System.exit(1);
	 }
		    
	 
     
    
    //frame.setResizable(false);
    }
}
