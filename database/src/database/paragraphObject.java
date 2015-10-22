package gutenSearch;

public class paragraphObject {
	public String title;
	public int ordinal;
	public String author;
	public String paragraph;
	public int arrindex;
	
	public paragraphObject(String paragraph1, String title1, String author1, int ord, int i)
	{
		arrindex = i;
		ordinal = ord;
		paragraph = paragraph1;
		title = title1;
		author = author1;
		//eliminate symbols, split by " "

	}
	
	public void parse()
	{
		String[] words = paragraph.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
		for(String s: words)
		{
			addToBucket(s);
			
		}
	}
	
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
	
	public void editAuthor(String authorname)
	{
		author = authorname;
	}
	
	public void editTitle(String titleName)
	{
		title = titleName;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getAuthor()
	{
		return author;
	}
	
	public int getOrdinal()
	{
		return ordinal;
	}
	
	public String getPar()
	{
		return paragraph;
	}
	
	public String toString()
	{
		String str = String.format("Title: %s\tAuthor: %s\t Ordinal: %d\n", title, author, ordinal);
		str += paragraph + "\n";
		return str;
	}
	

	
	
}
