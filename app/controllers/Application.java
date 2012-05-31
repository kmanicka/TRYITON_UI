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
import sun.misc.BASE64Decoder;

public class Application extends Controller {

	public static void index() {
		render();
	}

	public static void viewResults() {
		render("../views/Application/viewSearchResults.html");
	}

	public static void tio() {
		String imagepath = params.get("path");
		render(imagepath);
	}
	
	
	public static void saveImage(String file) {
		
//		System.out.println("Application.saveImage() 1 " + file.substring(0,1000));
		
		String file1 = file.substring(22);
		
//		System.out.println("Application.saveImage() 2 " + file1.substring(0,1000));
		
		BASE64Decoder base64Decoder = new BASE64Decoder();
		try {
			byte[] decodedBytes = base64Decoder.decodeBuffer(file1);
			File newfile = new File("public" +File.separator +"images"+File.separator+"trialimages"+File.separator +"test_glass1.png");
			FileOutputStream writer = new FileOutputStream(newfile);
			System.out.println("Application.saveImage() imaged saved at path " + newfile.getAbsolutePath());
			writer.write(decodedBytes);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void uploadPicture(String fileName, File files) {
		// System.out.println("inside uploadPicture");

		String image = params.get("captured_image");

		System.out.println("image path is " + image);
		File picture = new File("/Users/kmanickavelu/workspace/TRYITON_UI/"
				+ image);

		File saved = copyTo(picture);

		render("Application/message.html");
	}

	public static File copyTo(File photo1) {
		// System.out.println("Absolute path :"+photo1.getAbsolutePath());

		File photo2 = new File(
				"/Users/kmanickavelu/workspace/TRYITON_UI/public/images/savedimages/saved_"
						+ photo1.getName());
		// new File("\\public\\images\\savedimages\\saved_"+photo1.getName());
		try {
			System.out.println("photo1 is " + photo1);
			System.out.println("photo saved at " + photo2);
			InputStream in = new FileInputStream(photo1);
			OutputStream out = new FileOutputStream(photo2);
			byte[] buf = new byte[1024];
			int len;
			while ((len = in.read(buf)) > 0) {
				out.write(buf, 0, len);
			}
			in.close();
			out.close();

		} catch (FileNotFoundException ex) {
			System.out
					.println(ex.getMessage() + " in the specified directory.");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return photo2;

	}

	public static void viewSavedPhotos() {
		System.out.println("inside viewSavedPhotos");
		List<String> savedPhotos = new ArrayList<String>();
		List<String> savedPhotoNames = new ArrayList<String>();

		File dir = new File(
				"public" +File.separator +"images"+File.separator+"trialimages"+File.separator);
		File[] files = dir.listFiles();
		for (int fileInList = 0; fileInList < files.length; fileInList++) {
			savedPhotos.add(files[fileInList].getAbsolutePath());
			savedPhotoNames.add(files[fileInList].getName());

			System.out.println(files[fileInList].toString());
		}

		System.out.println("saved photos :" + savedPhotos);
		render(savedPhotos, savedPhotoNames);
	}

}