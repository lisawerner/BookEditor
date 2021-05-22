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
			Path path = new File(getFolderPath() + fileName).toPath();
			if(! Files.exists(path)) {
				File yourFile = new File(getFolderPath() + fileName);
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
	             new OutputStreamWriter(new FileOutputStream(getFolderPath() + filename), StandardCharsets.UTF_8)){
			writer.write(json);
			writer.flush();
			//TODO if false, then save old version!!!!!!! and don't delete everything!!!
		} catch (IOException e) {
	       e.printStackTrace();
	   }
		return true;
	}
	
	public static ArrayList<LoadBookPair> getFileList() {
		
		ArrayList<LoadBookPair> bookList = new ArrayList<>();
		
		File dir = new File(getFolderPath());
		File[] listOfFiles = dir.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
		  if (listOfFiles[i].isFile()) {
		    //System.out.println("File " + listOfFiles[i].getName());
			if(!listOfFiles[i].getName().equals(UserSettings.USER_SETTINGS_FILE_NAME)) {
				Book openBook = (Book) loadJSONFile(listOfFiles[i].getName(), Book.class);
				//System.out.println("BookTitle " + openBook.getTitle());
				bookList.add(new LoadBookPair(listOfFiles[i].getName(), openBook.getTitle()));
			}
		  }
		}
		
		return bookList;
	}
	
	public static boolean exportTextFile(String filename, String text) {
		FileWriter saveFile;
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
		String pathOfThisClass = FileManager.class.getProtectionDomain().getCodeSource().getLocation().getPath();
		int lastIndexOfBookEditor = pathOfThisClass.indexOf("BookEditor") + 10;
		//TODO: Test: Does this work with jar?
		//TODO: Or save it in another way? (Can the user decide the storage location!?!?!)
		return pathOfThisClass.substring(0, lastIndexOfBookEditor);
	}
	
	private static String getFolderPath() {
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
