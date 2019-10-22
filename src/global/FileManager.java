package global;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import GUI_StartFrame.LoadBookPair;
import book.Book;

public class FileManager {
	

	
	public static Object loadJSONFile(String fileName, Type type) {
		Gson gson = new Gson();
		Object jsonObj = null;
		try {
			Path path = new File(getFolterPath() + fileName).toPath();
			if(! Files.exists(path)) {
				File yourFile = new File(getFolterPath() + fileName);
				yourFile.createNewFile();
			}
			Reader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8);
			jsonObj = gson.fromJson(reader, type);
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonObj;
		
	}
	
	public static boolean saveJSONFile(String filename, Object o) {
		GsonBuilder builder = new GsonBuilder();
		builder.setPrettyPrinting();
		Gson gson = builder.create();
		String json = gson.toJson(o);
		try (OutputStreamWriter writer =
	             new OutputStreamWriter(new FileOutputStream(getFolterPath() + filename), StandardCharsets.UTF_8)){
			writer.write(json);
			writer.flush();
			//TODO if false, then save old version!!!!!!! and don't delete everything!!!
		} catch (IOException e) {
	       e.printStackTrace();
	   }
		return true;
	}
	
	public static ArrayList<LoadBookPair> getFileList() {
		
		ArrayList<LoadBookPair> booklist = new ArrayList<LoadBookPair>();
		
		File dir = new File(getFolterPath());
		File[] listOfFiles = dir.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile()) {
		    //System.out.println("File " + listOfFiles[i].getName());
			if(!listOfFiles[i].getName().equals("usersettings.json")) {				
				Book openBook = (Book) loadJSONFile(listOfFiles[i].getName(), Book.class);
				//System.out.println("Booktitle " + openBook.getTitle());
				booklist.add(new LoadBookPair(listOfFiles[i].getName(), openBook.getTitle()));
			}
		  }
		}
		
		return booklist;
	}
	
	public static boolean exportTXTfile(String filename, String text) {
		FileWriter saveFile = null;
		try {
			//WRITE new File
			saveFile = new FileWriter(filename);
			saveFile.write(text);
			saveFile.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public static String getSavingPath() {
		String pathOfTHISclass = FileManager.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		int lastIndexOfBookEditor = pathOfTHISclass.indexOf("BookEditor") + 10;
		//TODO: Testen ob das mit der Jar geht!
		//TODO: Oder ganz anders Speichern (Nutzer sucht Speicherort aus!?!?!)
		String path = pathOfTHISclass.substring(0, lastIndexOfBookEditor);
		return path;
	}
	
	private static String getFolterPath() {
		String folderPath = "MyBooks\\";
		
		Path path = new File(folderPath).toPath();
		if(! Files.exists(path)) {
			new File(folderPath).mkdir();
			try {
				Files.createDirectories(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return folderPath;
	}

}
