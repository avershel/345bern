package database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
public class textObject {
	
	public String title;
	public String author;
	
	public textObject(File file) throws FileNotFoundException, IOException
	{
		String str = "";

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       str +=line;
		    }
		}
		parseIntoParagraphs(str);
		
	}
	
	public void parseIntoParagraphs(String str)
	{
		String lines[] = str.split("\\r?\\n");
		for(int i = 0; i < lines.length; i++)
		{
			new paragraphObject(lines[i], title, author, i);
		}		
	}
	
	public void editAuthor(String authorname)
	{
		author = authorname;
	}
	
	public void editTitle(String titleName)
	{
		title = titleName;
	}
	

}
