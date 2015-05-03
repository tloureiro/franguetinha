package franguetinha.controller.utils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import franguetinha.controller.model.FileEntry;
import franguetinha.controller.model.FileEntry.FILE_ACTION;

public class FilesListUtil {
	
	private File rootDir;

	public FilesListUtil(String rootDirPath) {

		this.rootDir = new File(rootDirPath);
	}
	
	
	public static void main(String[] args) {
		FilesListUtil filesListUtil = new FilesListUtil("test/testfolder");
		
		for (FileEntry fileEntry: filesListUtil.listFileEntries()) {
			System.out.println(fileEntry.getDisplayName());
		}
	}
	
	public boolean isValid(){

		return this.rootDir.exists() && this.rootDir.isDirectory();
	}
	
	
	public List<FileEntry> listFileEntries(){
		
		List<FileEntry> entries = new ArrayList<FileEntry>();

		File[] files = rootDir.listFiles();
		
		Arrays.sort(files);
		
		if(this.rootDir.getAbsoluteFile().getParent() != null){
			FileEntry entry = new FileEntry();
			entry.setDisplayName("..");
			entry.setFullpath(this.rootDir.getAbsoluteFile().getParentFile().getAbsolutePath());
			entry.setAction(FILE_ACTION.OPEN_FOLDER);
			entries.add(entry);
		}
		
		for (File file : files) {
			
			if(!file.isDirectory()){
				try {
					String type = Files.probeContentType(Paths.get(file.getAbsolutePath()));
					if(type.contains("audio") || type.contains("video")){
						FileEntry entry = new FileEntry();
						entry.setDisplayName(file.getName());
						entry.setFullpath(file.getAbsolutePath());
						entry.setAction(FILE_ACTION.OPEN_MEDIA);
						entries.add(entry);						
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				FileEntry entry = new FileEntry();
				entry.setDisplayName(file.getName() + "/");
				entry.setFullpath(file.getAbsolutePath());
				entry.setAction(FILE_ACTION.OPEN_FOLDER);
				entries.add(entry);
			}
			
		}
		
		return entries;
	}
	
}
