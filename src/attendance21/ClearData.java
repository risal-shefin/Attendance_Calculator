package attendance21;

import java.io.File;
import java.io.IOException;

public class ClearData {
	
	public void clear(File file) throws IOException {
		
		for(File child : file.listFiles() ) {
			
			if(child.isDirectory())
				clear(child);
			
			else if(!child.delete())
				throw new IOException();
			
		}
		
		if(!file.delete())
			throw new IOException();
		
	}
}
