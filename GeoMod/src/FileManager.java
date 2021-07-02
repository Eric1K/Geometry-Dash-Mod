import java.io.File;
import java.io.IOException;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;

public class FileManager {
	
	public static void Install()
	{
		boolean fin1;
		boolean fin2;
		File moddll = new File("modmenu/gdmodmenuit.dll");
		File libcurl = new File("modmenu/libcurl.dll");
		File source = new File(System.getProperty("user.dir"));
		
		moddll.setWritable(true);
		libcurl.setWritable(true);
		
		System.out.println(moddll.getAbsolutePath());
		try {
		    FileUtils.copyFileToDirectory(moddll, source);
		    fin1 = true;
		} catch (IOException e) {
		    e.printStackTrace();
		    fin1 = false;
		}
		
		try {
		    FileUtils.copyFileToDirectory(libcurl, source);
		    fin2 = true;
		} catch (IOException e) {
		    e.printStackTrace();
		    fin2 = false;
		}
		
		moddll.setReadOnly();
		System.out.println("GDMODMENUIT.dll REPLACED: " + fin1);
		System.out.println("LIBCURL.dll REPLACED: " + fin2);
		
		
		if(fin1 == false)
		{
			JOptionPane.showMessageDialog(null, "Failed to copy gdmodmenuit.dll", "Error", 2);
			if(fin2 == false)
			{
				JOptionPane.showMessageDialog(null, "Failed to copy libcurl.dll", "Error", 2);
				System.exit(-1);
			}else
			{
				JOptionPane.showMessageDialog(null,"Successfully copied libcurl.dll, gdmodmenuit.dll failed");
				System.exit(-1);
			}
		}
		
		if(fin2 == false)
		{
			JOptionPane.showMessageDialog(null, "Failed to copy libcurl.dll", "Error", 2);
			if(fin1 == false)
			{
				JOptionPane.showMessageDialog(null, "Failed to copy gdmodmenuit.dll", "Error", 2);
				System.exit(-1);
			}else
			{
				JOptionPane.showMessageDialog(null,"Successfully copied gdmodmenuit.dll, gdmodmenuit.dll failed");
				System.exit(-1);
			}
		}
		
		
		if(fin1 && fin2 == true)
		{
			JOptionPane.showMessageDialog(null, "Successfully Installed!");
			JOptionPane.showMessageDialog(null, "To Activate menu, go to options and turn on mod menu. You may need to restart the game a few times for it to appear.");
			System.exit(1);
		}
		
		
		
		
	}

	public static void Restore()
	{
		
		boolean fin1 = false;
		boolean renamed = false;
		
		File moddll = new File("gdmodmenuit.dll");
		File libcurl = new File("libcurl.dll");
		File libcurlbak = new File("modmenu/libcurl.dll.bak");
		File source = new File(System.getProperty("user.dir"));
		
		moddll.delete();
		libcurl.delete();
		
		try {
		    FileUtils.copyFileToDirectory(libcurlbak, source);
		    File libcurlbak1= new File("libcurl.dll.bak");
		    File libcurlres = new File ("libcurl.dll");
		    
		    renamed = libcurlbak1.renameTo(libcurlres);
		    fin1 = true;
		    if(fin1 = true)
		    {
		    	JOptionPane.showMessageDialog(null, "Successfully restored!");
		    	System.exit(1);
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		    JOptionPane.showMessageDialog(null, "Failed to restore files", "Error", 2);
		}
	}
}
