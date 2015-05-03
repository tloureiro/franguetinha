package franguetinha;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import franguetinha.controller.model.FileEntry;
import franguetinha.controller.utils.FilesListUtil;

public class TestFileListUtil {
	
	@Test
	public void testValidInstatiations() {
		
		
		FilesListUtil filesListUtil = new FilesListUtil("crazyfolder");
		assertTrue(!filesListUtil.isValid());
		
		filesListUtil = new FilesListUtil("test/testfolder");
		assertTrue(filesListUtil.isValid());
		
	}
	
	@Test
	public void testFileListing(){
		
		FilesListUtil filesListUtil = new FilesListUtil("test/testfolder");
		
		assertTrue( filesListUtil.listFileEntries().size() == 5 );
		
		boolean found = false;
		for(FileEntry fileEntry : filesListUtil.listFileEntries()){
			if(fileEntry.getDisplayName().equals("..")){
				found = true;
			}
		}
		
		assertTrue(found);

		found = false;
		for(FileEntry fileEntry : filesListUtil.listFileEntries()){
			if(fileEntry.getDisplayName().equals("subfolder/")){
				found = true;
			}
		}
		
		assertTrue(found);
	}
	
}
