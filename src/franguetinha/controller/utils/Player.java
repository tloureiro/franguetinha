package franguetinha.controller.utils;

import java.io.IOException;
import java.io.PrintWriter;


public class Player {
	
	private Process process;

	public Player() {
		
	}
	

	public void play(String path){
		
		try {
			String[] command = {"omxplayer", path};
			if(this.isPlaying()){
				PrintWriter printWriter = new PrintWriter(process.getOutputStream());
				printWriter.print("q");
				printWriter.flush();
			}
			process = Runtime.getRuntime().exec(command);
		} catch (IOException e) {

			String[] command = {"vlc", path};
			if(this.isPlaying()){
				process.destroy();
			}
			try {
				process = Runtime.getRuntime().exec(command);
			} catch (IOException e1) {
				e1.printStackTrace();
			}			
		}
	}
	
	public boolean isPlaying(){
		boolean ret;
		
		if(process == null){
			ret = false;
		}else{
			try{
				process.exitValue();
				ret = false;
			}catch(Exception e){
				ret = true;
			}
		}
		
		return ret;
	}
}
