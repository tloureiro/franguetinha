package franguetinha.controller;

import franguetinha.controller.model.FileEntry;
import franguetinha.controller.utils.FilesListUtil;
import franguetinha.controller.utils.Player;
import franguetinha.view.View;


public class Controller {
	
	private View view;
	private Player player;

	public void start() {

		this.player = new Player();
		this.view = new View();
		this.openFolder(".");
		
	}
	
	public void playMedia(String path){
		this.player.play(path);
	}
	
	public void openFolder(String path){
		
		view.clearFileEntries();
		
		FilesListUtil filesListUtil = new FilesListUtil(path);
		
		for (final FileEntry fileEntry : filesListUtil.listFileEntries()) {

			if(fileEntry.getAction() == FileEntry.FILE_ACTION.OPEN_FOLDER){
				view.addFileEntry(fileEntry.getDisplayName(), new Runnable(){
					public void run() {
						openFolder(fileEntry.getFullpath());
					};
				});    
			}else if(fileEntry.getAction() == FileEntry.FILE_ACTION.OPEN_MEDIA){
				view.addFileEntry(fileEntry.getDisplayName(), new Runnable(){
					public void run() {
						playMedia(fileEntry.getFullpath());
					};
				});
			}
		}		
	}
}
