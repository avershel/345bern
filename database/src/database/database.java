package database;

import java.io.File;
import java.io.FileNotFoundException;
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
		addTxtFiles(new File(filename));
	}
	
	public void addTxtFiles(File file) throws FileNotFoundException, IOException
	{
		new textObject(file);
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
			return buckets.get(query).toString();
		}
	}
}
