package theGutenSearch;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
/*
 * search1
 * A class that handles searching
 * 
 * @author group 7
 */
public class search1 {
	
	String [] termArray;
	List<paragraphObject> l1 = new ArrayList<paragraphObject>();
	List<paragraphObject> l2 = new ArrayList<paragraphObject>();
	String []expholder;
	int index = 0;

	/*
	 * A search object constructor
	 * 
	 * @param query - the term(s) to be searched
	 */
	public search1(String query)
	{
		
		
		String[] firstWord1 = query.split("\\&| |\\|");
		for(String str : firstWord1)
		{
			bucket b = database.buckets.get(str);
			if(b != null)
			{
				for(paragraphObject po : b)
				{
					l1.add(po);

				}

			}
		}

		query = query.toLowerCase();
		String[] firstWord = query.split("\\|");
		termArray = new String[firstWord.length];
		expholder = new String[firstWord.length];

		for(String word: firstWord)
		{
			parseOr(word);
		}
		

	}
	
	/*
	 * A helper method for parsing OR terms
	 * 
	 * @param exp - the expression to be parsed
	 */
	public void parseOr(String exp)
	{
		if(exp.indexOf('&') >= 0)
		{
			expholder = exp.split("\\&");

			parseAnd(expholder);

		}
		else
		{
			expholder = exp.split(" ");
			for(String e : expholder)
			{
				addPars(e);
			}
			
		}
		
	}
	
	/*
	 * A helper method for parsing single terms
	 * 
	 * @param exp - the expression to be parsed
	 */	public void addPars(String exp)
	{
		
			bucket b = database.buckets.get(exp);
			if(b != null)
			{
				for(paragraphObject po : b)
				{
					l1.add(po);

				}
//				l1.retainAll(l2);
//				l2.clear();
			}
	}

		/*
		 * A helper method for parsing AND terms
		 * 
		 * @param holder - an array containing joined expressions
		 */
	public void parseAnd(String[] holder)
	{
		List<paragraphObject> ll1 = new ArrayList<paragraphObject>();
		List<paragraphObject> ll2 = new ArrayList<paragraphObject>();
		index = 0;
		for(int i = 0; i < holder.length; i++)
		{
			if(index == 0)
			{

				bucket b = database.buckets.get(holder[i]);
				if(b != null)
				{
					for(paragraphObject po : b)
					{
						ll1.add(po);

					}
					index++;

				}
			}
			else
			{

				bucket b = database.buckets.get(holder[i]);
				if(b != null)
				{
					for(paragraphObject po : b)
					{
						ll2.add(po);

					}
					ll1.retainAll(ll2);
					ll2.clear();

				}
			}
		}
		

		l1.retainAll(ll1);

	}
	
	/*
	 * A method to return the results of the search
	 * 
	 * @return a String representation of the search results
	 */
	public String results()
	{
		
		
		
		Set<paragraphObject> hld = new LinkedHashSet<>(l1);
		l1.clear();
		l1.addAll(hld);
		
		String str = "";
		if(l1.size() > 0)
		{
			for(paragraphObject po: l1)
			{
				str += po.toString();
			}
			return str;
		}
		else
		{
			return "QUERY TERM NOT FOUND";
		}
	}
	
	/*
	 * A helper method for parsing wildcard expressions
	 * 
	 * @param text - the expression to be parsed
	 */
	public static void wild(String text)
	{
		
		for(int i = 0; i < text.length(); i++)
		{
			if(text.charAt(i) == '?')
			{
				for(char j = 'a'; j<='z';j++)
				{
					new search1(text.replace('?',j));
				}
			}
		}
	}
	
	/*
	 * A helper method for parsing star expressions
	 * 
	 * @param text - the expression to be parsed
	 */
	public static void star(String text)
	{
		for(int i=0; i < text.length();i++)
		{
			if(text.charAt(i)=='*')
			{
				new search1(text.substring(0,i));
				
			}
		}
	}
}
