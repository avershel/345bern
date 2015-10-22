package gutenSearch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
//import java.util.Collection;
import java.util.HashMap;

public class database {
	public static HashMap<String, bucket> buckets;

	public database()
	{
		buckets = new HashMap<String,bucket>();
	}
	
	public void addTxtFiles(File filename) throws FileNotFoundException, IOException
	{
		parseText(readFile(filename.toString()));
	}

	
	public static String readFile(String fileName) throws IOException 
	{
	    BufferedReader buffread = new BufferedReader(new FileReader(fileName));
	    try 
	    {
	        StringBuilder stringbuild = new StringBuilder();
	        String line = buffread.readLine();

	        while (line != null) {
	        	stringbuild.append(line);
	        	stringbuild.append("\n");
	            line = buffread.readLine();
	        }
	        return stringbuild.toString();
	    } 
	    finally 
	    {
	    	buffread.close();
	    }
	}
	
	/**
	 * Takes in string object(the full text) 
	 * extracts title, author, converts to paragraph objects
	 * @param text - the full text file
	 */
	public static void parseText(String text) 
	{
		String author = "";
		String title = "";
		int index;
	    String[] paragraphs = text.split("\n");
	    for (String paragraph : paragraphs) 
	    {
	        String arr[] = paragraph.split(" ");
	        if(arr[0].equals("Author:"))
	        {
	        	for(int i = 1; i<arr.length; i++)
	        	{
	        		author += arr[i];
	        		author += " ";
	        	}
	        	//System.out.println("The Author is: " + author);
	            
	        }
	        
	        if(arr[0].equals("Title:"))
	        {
	        	for(int i = 1; i<arr.length; i++)
	        	{
	        		title += arr[i];
	        		title += " ";
	        	}
	        	//System.out.println("The Title is: " + title);
	        }
	        
	    }
	    index = 1;
	    for (String paragraph : paragraphs) 
	    {
	    	if(!paragraph.equals(""))
	    	{
	    		//new paragraph object
	    		//set title
	    		//set author
	    		//set paragraph
	    		new paragraphObject(paragraph, title, author, index);
	    		index ++;
	    		//System.out.println("Paragraph: " + paragraph.trim());
	    
	    	}
	    }
	}
	
	
	public String search(String query)
	{
		return new search1(query).results();
		
	}
}
