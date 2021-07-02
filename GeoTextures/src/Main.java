import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

@SuppressWarnings("serial")
public class Main extends JPanel implements ActionListener {
	//TODO ADD DESIRED SCREEN RESOLUTION AND FIX NO FILE CHOSEN
	JButton go = new JButton("Choose Pack Folder/ZIP");
	static String sourceFolder="";
	String theFile="";
	JFileChooser chooser;
	String choosertitle;
	
    JLabel madeby = new JLabel();
    JButton install = new JButton("Install Textures");
    JButton restore = new JButton("Restore Textures");
    JLabel credit = new JLabel();
   
   public Main() {
	  
	 go.addActionListener(this);
	 add(go);
     add(madeby);
     madeby.setText("©2019 Made by Eric Feng");
     add(install);
     add(restore);

     install.addActionListener(new ActionListener()
     {
     	 public void actionPerformed(ActionEvent e)
     	 {

	          String Folder = "";
	          int i = 0;
	          try (ZipFile zipFile = new ZipFile(sourceFolder)) {
	        	    Enumeration zipEntries = zipFile.entries();
	        	    while (zipEntries.hasMoreElements()) {
	        	        String fileName = ((ZipEntry) zipEntries.nextElement()).getName();
	        	        System.out.println(fileName);
	        	        if(i == 0)
	        	        {
	        	        	Folder = fileName;
	        	        }
	        	        i++;
	        	    }
	        	} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "Failed to get folder name.", "Error", 2);
				}
	          Folder = Folder.substring(0, Folder.length() - 1);
	          System.out.println(Folder);
	          
     		  FileManager.Install(Folder);
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
	   
	     //System.out.println("Choosing...");
	     chooser = new JFileChooser(); 
	     chooser.setCurrentDirectory(new java.io.File("."));
	     chooser.setDialogTitle(choosertitle);
	     FileNameExtensionFilter filter = new FileNameExtensionFilter(
	        "Zip Files", "zip");
	     chooser.setFileFilter(filter);
	     chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
	    
	    
	    if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) { 
	      
	         String dirr = "" + chooser.getCurrentDirectory();
	         File file = chooser.getSelectedFile();
	       
	      if(dirr.substring(dirr.length()-1,dirr.length()).equals(".")){
	           dirr = dirr.substring(0,dirr.length()-1);
	           sourceFolder=""+dirr + "" + file.getName();
	        }else{
	            
	            sourceFolder=""+dirr + "/" + file.getName();
	        }

	          System.out.println("Folder path: " + dirr + " | File Name: " + file.getName());
	          System.out.println(sourceFolder);
	 			//ExamineImage.lum(sourceFolder);

	          
	      }else {
	    	  JOptionPane.showMessageDialog(null, "No file chosen!"); 
	    	  System.out.println("No Selection ");
	      }
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
	  JOptionPane.showMessageDialog(null, "When installing, select the zip file to install. Make sure the zip contains a folder with the images, and only a folder!");
	  boolean response = true;
	 if(response = true)
	 {
		 JFrame frame = new JFrame("Texture Pack Wizard");
		 System.out.println(System.getProperty("user.dir"));   
		 Main panel = new Main();
		    frame.addWindowListener(
		      new WindowAdapter() {
		        public void windowClosing(WindowEvent e) {
		        	try {
						Process proc = Runtime.getRuntime().exec("SecretlyGod's Geometry Dash Pack.jar");
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
		        	System.exit(0);
		          }
		        }
		      );
		    frame.getContentPane().add(panel,"Center");
		    frame.setSize(panel.getPreferredSize());
		    frame.setVisible(true);
	 }else
	 {
		 try {
				Process proc = Runtime.getRuntime().exec("Launcher.jar");
				System.exit(1);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	 }
		    
	 
     
    
    //frame.setResizable(false);
    }
}
