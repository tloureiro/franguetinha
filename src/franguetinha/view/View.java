package franguetinha.view;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.gui.Action;
import com.googlecode.lanterna.gui.Border.Invisible;
import com.googlecode.lanterna.gui.GUIScreen.Position;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.ActionListBox;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.Panel;
import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.input.Key.Kind;
import com.googlecode.lanterna.screen.Screen;

public class View {
	
	private final Window window;

	public View() {
		
		final GUIScreen textGUI;
		
		if(System.getenv().get("TERM").equals("xterm")){
			
			textGUI = TerminalFacade.createGUIScreen(TerminalFacade.createUnixTerminal(System.in, System.out));
		}else{
			textGUI = TerminalFacade.createGUIScreen(TerminalFacade.createScreen());
		}
		
	    Screen screen = textGUI.getScreen();
	    screen.startScreen();
	    
	    this.window = new Window(""){
	    	
	    	@Override
	    	public void onKeyPressed(Key key) {

	    		if(key.getKind() == Kind.Escape){
	    			this.close();
	    			System.exit(0);
	    		}
	    		
	    		super.onKeyPressed(key);
	    	}
	    };
	    
	    window.setBorder(new Invisible());
	    
		Panel bigPannel = new Panel(new Invisible(), Panel.Orientation.HORISONTAL);
    	bigPannel.addComponent(new Label("Files:"));
    	bigPannel.addComponent(new ActionListBox());
    	
    	window.addComponent(bigPannel);
    	
    	new Thread(){
    		
    		public void run() {
    			textGUI.showWindow(window,Position.NEW_CORNER_WINDOW);
    		};
    	}.start();
	}
	
	public void addFileEntry(String displayName, final Runnable action){
		
		ActionListBox actionListBox = (ActionListBox) ((Panel) window.getComponentAt(0)).getComponentAt(1);
		
		actionListBox.addAction(displayName, new Action() {
			@Override
			public void doAction() {
				action.run();
			}
		});
	}
	
	public void clearFileEntries(){
		ActionListBox actionListBox = (ActionListBox) ((Panel) window.getComponentAt(0)).getComponentAt(1);
		actionListBox.clearItems();
	}
}
