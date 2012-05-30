package controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import play.mvc.Controller;

public class Application extends Controller {

    public static void index() {
        render();
    }
    
    public static void viewResults(){
    	render("../views/Application/viewSearchResults.html");
    }
    
    public static void tio(){
    	
    	String imagepath = params.get("path");
    	render(imagepath);
    }
    
    public static void uploadPicture() {
    	System.out.println("inside uploadPicture");
    	
    	String image = params.get("captured_image");
    	
    	System.out.println("image path is "+image);
    	File picture = new File("C:\\Users\\kard\\Desktop\\KARTHICK\\eBay\\BILLING\\workspace\\Ushatryiton\\"+image);
    	
    	File saved = copyTo(picture);
    }
    
    public static File copyTo(File photo1){
		System.out.println("Absolute path :"+photo1.getAbsolutePath());
    	
    	File photo2 = 
    			new File("C:\\Users\\kard\\Desktop\\KARTHICK\\eBay\\BILLING\\workspace\\Ushatryiton\\public\\images\\savedimages\\saved_"+photo1.getName());
    			//new File("\\public\\images\\savedimages\\saved_"+photo1.getName());
    	try{
    		 System.out.println("photo1 is "+photo1);
    		  InputStream in = new FileInputStream(photo1);
    		  OutputStream out = new FileOutputStream(photo2);
    		  byte[] buf = new byte[1024];
    		  int len;
    		  while ((len = in.read(buf)) > 0){
    			  out.write(buf, 0, len);
    		  }
    		  in.close();
    		  out.close();
    		  
    	}
	    catch(FileNotFoundException ex){
	    	System.out.println(ex.getMessage() + " in the specified directory.");
	    }
	    catch(IOException e){
	    	System.out.println(e.getMessage());  
	    }
    	return photo2;
    	
    }
    
    public static void viewSavedPhotos(){
    	System.out.println("inside viewSavedPhotos");
    	List<String> savedPhotos = new ArrayList<String>();
    	List<String> savedPhotoNames = new ArrayList<String>();
    	
    	File dir = new File("C:\\\\Users\\\\kard\\\\Desktop\\\\KARTHICK\\\\eBay\\\\BILLING\\\\workspace\\\\Ushatryiton\\\\public\\\\images\\\\savedimages\\\\");
    	File[] files = dir.listFiles();
    	for (int fileInList = 0; fileInList < files.length; fileInList++)  
    	 {  
    		savedPhotos.add(files[fileInList].getAbsolutePath());
    		savedPhotoNames.add(files[fileInList].getName());
    		
    		System.out.println(files[fileInList].toString());  
    	 } 
    	
    	System.out.println("saved photos :"+savedPhotos);
    	render(savedPhotos, savedPhotoNames);
    }
    
}