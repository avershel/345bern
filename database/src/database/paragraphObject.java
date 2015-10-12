package database;

public class paragraphObject {
	public String title;
	public int ordinal;
	public String author;
	public String paragraph;
	
	public paragraphObject(String paragraph1, String title1, String author1, int ord)
	{
		ordinal = ord;
		paragraph = paragraph1;
		title = title1;
		author = author1;
		String[] words = paragraph.replaceAll("[^a-zA-Z ]", "").toLowerCase().split("\\s+");
		for(String s: words)
		{

			addToBucket(s);
			
		}
	}
	
	public void addToBucket(String key)
	{
		bucket value = database.buckets.get(key);
		if (value != null) {
		    value.addParObject(this);
		    database.buckets.put(key, value);

		} else {
			bucket buck = new bucket(key);
		    buck.addParObject(this);
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
	
	public String toString()
	{
		return paragraph;
	}
}

