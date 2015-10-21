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
	
	public void addTxtFiles(String filename) throws FileNotFoundException, IOException
	{
		parseText(readFile(filename));
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
		String[] firstWord = query.split(" ");
		
		//multi word query
		if(firstWord.length > 1)
		{
			boolean check = false;
			bucket b = buckets.get(firstWord[0]);
			bucket queryb = new bucket(query);
			for(paragraphObject po : b)
			{
				String[] words = po.toString().replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
				for(int i = 0; i < words.length; i++)
				{
					if(words[i].equals(firstWord[0]) && i < words.length + firstWord.length)
					{
						for(int ii = 1; ii < firstWord.length; ii++)
						{
							if(!words[i+1].equals(firstWord[i]))
							{
								check = false;
								break;
								//second word matches
							}
							else
							{
								check = true;
							}
						}
						if(check)
						{
							queryb.addParObject(po);
						}
					}
				}
			}
			return queryb.toString();
		}
		//single word query
		else
		{
			//System.out.println("QUERY ------> " + query);
			return buckets.get(query).toString();
		}
	}
}
