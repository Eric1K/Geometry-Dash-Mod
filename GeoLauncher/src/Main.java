import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

@SuppressWarnings("serial")
public class Main extends JPanel implements ActionListener {

    JLabel madeby = new JLabel();
    JLabel website = new JLabel();
    JLabel version = new JLabel();
    JButton Texture = new JButton("Change Textures");
    JButton Mod = new JButton("Add Mod Menu");
    JButton Update = new JButton("Update");
    JButton Launch = new JButton("Launch Game");
    
  public Main() {
	  
     add(madeby);
     madeby.setText("©2019 Made by Eric Feng");
     add(website);
     website.setText("Site: Secretlygod.github.io");
     add(version);
     version.setText("v0.1 check the site for updates");
     add(Launch);
     add(Update);
     add(Mod);
     add(Texture);
     
    
      Update.addActionListener(new ActionListener()
     {
     	 public void actionPerformed(ActionEvent e)
     	 {
     		 //Launch("java -jar test.jar");
     		if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
     		    try {
					Desktop.getDesktop().browse(new URI("https://secretlygod.github.io/"));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (URISyntaxException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
     		}
     	 }
     });
      Mod.addActionListener(new ActionListener()
      {
      	 public void actionPerformed(ActionEvent e)
      	 {
      		Launch("java -jar mod.jar"); 
      	 }
      });
      Texture.addActionListener(new ActionListener()
      {
      	 public void actionPerformed(ActionEvent e)
      	 {
      		 Launch("java -jar texture.jar");
      		 System.exit(1);
      	 }
      });
      
      Launch.addActionListener(new ActionListener()
      {
      	 public void actionPerformed(ActionEvent e)
      	 {
      		 Launch("GeometryDash.exe");
      		 System.exit(1);
      	 }
      });
     
    
    
   }
  
  
  public void Launch(String name)
  {
	  try {
		Process proc = Runtime.getRuntime().exec(name);
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  }
  public void actionPerformed(ActionEvent e) {
 
  }
   
  public Dimension getPreferredSize(){
          return new Dimension(630, 630);
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
	  JFrame frame = new JFrame("SecretlyGod's Geometry Dash Launcher");
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
    frame.setResizable(false);
    frame.setVisible(true);
     
    
    //frame.setResizable(false);
    }
}
