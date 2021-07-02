import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.JOptionPane;

import org.apache.commons.io.FileUtils;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;

public class FileManager {
	
	public static void Install(String Foldername)
	{
		boolean success = true;
		boolean unzip = true;
		boolean delete = true;
		boolean copy = true;
		
		try {
		       ZipFile zipFile = new ZipFile(Main.sourceFolder);
		       zipFile.extractAll(System.getProperty("user.dir"));
		  } catch (ZipException e) {
		      e.printStackTrace();
		      success = false;
		      unzip = false;
		  }
		
		File resbak = new File(System.getProperty("user.dir") + "/texturepacks" + "/Resources Back");
		File res = new File(System.getProperty("user.dir") + "/Resources");
		
		try {
			FileUtils.cleanDirectory(res);
		} catch (IOException e) {
			success = false;
			delete = false;
			e.printStackTrace();
		} 
		
		String sourceDir1Path = System.getProperty("user.dir") + "/Resources";
		String sourceDir2Path = System.getProperty("user.dir")+ "/" + Foldername;
 
		File dir1 = new File(sourceDir1Path);
		File dir2 = new File(sourceDir2Path);
		
		//moves 2nd's files to first
		mergeTwoDirectories(dir1, dir2);

		try {
			
			File temp = new File(System.getProperty("user.dir") + "/texturepacks/temp");
			FileUtils.copyDirectory(resbak, temp);
			mergeTwoDirectories(dir1, temp);
			FileUtils.deleteDirectory(temp);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			copy = false;
		}
		File tempTexture = new File(System.getProperty("user.dir")+ "/" + Foldername);
		tempTexture.delete();
		if(success == true)
		{
			JOptionPane.showMessageDialog(null, "Successfully Installed Textures!");
		}
		if(success == false)
		{
			if(delete == false)
			{
				JOptionPane.showMessageDialog(null, "Failed to clean Resources File", "Error", 2);
			}
			if(unzip == false)
			{
				JOptionPane.showMessageDialog(null, "Failed to unzip file", "Error", 2);
			}
			if(copy == false)
			{
				JOptionPane.showMessageDialog(null, "Failed to copy remaining backup files to Resources", "Error", 2);
			}
		}
	}

	public static void Restore()
	{
		boolean clean = true;
		boolean restore = true;
		boolean success = true;
		
		File cdir= new File(System.getProperty("user.dir") + "/Resources");
		try {
			FileUtils.cleanDirectory(cdir);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			clean = false;
			success = false;
		}
		
		File backup = new File(System.getProperty("user.dir") + "/texturepacks/Resources Back");
		
		
		try {
			//move 1 to 2
			FileUtils.copyDirectory(backup, cdir);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			restore = false;
			success = false;
		}
		
		if(success == true)
		{
			JOptionPane.showMessageDialog(null, "Successfully restored textures!");
		}
		if(success == false)
		{
			if(clean == false)
			{
				JOptionPane.showMessageDialog(null, "Failed to clean Resources Folder", "Error", 2);
			}
			
			if(restore == false)
			{
				JOptionPane.showMessageDialog(null, "Failed to copy default texture files to Resources", "Error", 2);
			}
			
			
		}
		
		
	}
	
	public static void mergeTwoDirectories(File dir1, File dir2){
		
		String targetDirPath = dir1.getAbsolutePath();
		System.out.println(targetDirPath);
		File[] files = dir2.listFiles();
		for (File file : files) {
			file.renameTo(new File(targetDirPath+File.separator+file.getName()));
			System.out.println(file.getName() + " is moved!");
		}
	}
}
