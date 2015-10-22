package theGutenSearch;
/*
 * paragraphObject
 * A class that creates paragraph objects
 * 
 * @author group 7
 */
public class paragraphObject {
	public String title;
	public int ordinal;
	public String author;
	public String paragraph;
	public int arrindex;
	
	/*
	 * A paragraphObject constructor
	 * 
	 * @param paragraph1 - the text of the paragraph
	 * @param title1 - the title of the text
	 * @param author1 - the author of the text
	 * @param ord - the ordinal number of the paragraph
	 * @param i - the array index of the paragraph
	 */
	public paragraphObject(String paragraph1, String title1, String author1, int ord, int i)
	{
		arrindex = i;
		ordinal = ord;
		paragraph = paragraph1;
		title = title1;
		author = author1;
		//eliminate symbols, split by " "

	}
	
	/*
	 * A helper method to parse the paragraph
	 *
	 */
	public void parse()
	{
		String[] words = paragraph.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
		for(String s: words)
		{
			addToBucket(s);
			
		}
	}
	
	/*
	 * A helper method to add text to buckets
	 * 
	 * @param key - the term(s) to be added
	 */
	public void addToBucket(String key)
	{
		bucket value = database.buckets.get(key);
		//if the bucket exists
		if (value != null) {
		    value.addParObject(database.pomap.get(Integer.toString(arrindex)));
		    database.buckets.put(key, value);
			
		} else {

				bucket buck = new bucket(key);
				paragraphObject p = database.pomap.get(Integer.toString(arrindex));
			    buck.addParObject(p);
			    database.buckets.put(key, buck);	
			
		}
	}
	
	/*
	 * A helper method to edit author
	 * 
	 * @param authorname - the new authors name
	 */
	public void editAuthor(String authorname)
	{
		author = authorname;
	}
	
	/*
	 * A helper method to edit title
	 * 
	 * @param titlename - the new title
	 */
	public void editTitle(String titleName)
	{
		title = titleName;
	}
	
	/*
	 * A helper method to get title
	 * 
	 */
	public String getTitle()
	{
		return title;
	}
	
	/*
	 * A helper method to get author
	 * 
	 */
	public String getAuthor()
	{
		return author;
	}
	
	/*
	 * A helper method to get the ordinal number
	 * 
	 */
	public int getOrdinal()
	{
		return ordinal;
	}
	
	/*
	 * A helper method to get the text
	 * 
	 */
	public String getPar()
	{
		return paragraph;
	}
	
	/*
	 * A helper method to convert the paragraphObject to string
	 * 
	 * @return str the string representation of the paragraphObject
	 */
	public String toString()
	{
		String str = String.format("Title: %s\nAuthor: %s\nOrdinal: %d\n", title, author, ordinal);
		//String []hold = paragraph.split(".");
		str += paragraph + "\n\n";
		return str;
	}
	

	
	
}
