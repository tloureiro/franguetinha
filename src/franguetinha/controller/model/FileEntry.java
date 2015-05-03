package franguetinha.controller.model;

public class FileEntry {

	private String displayName;
	private String fullpath;
	private FILE_ACTION action;
	
	public enum FILE_ACTION{
		OPEN_MEDIA, OPEN_FOLDER
	}
	
	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getFullpath() {
		return fullpath;
	}

	public void setFullpath(String fullpath) {
		this.fullpath = fullpath;
	}

	public FILE_ACTION getAction() {
		return action;
	}

	public void setAction(FILE_ACTION action) {
		this.action = action;
	}
	
	

}
