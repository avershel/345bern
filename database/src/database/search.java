package gutenSearch;

import java.util.ArrayList;
import java.util.List;

public class search {

	//list of terms
	String [] termArray;
	int index = 0;
	
	
	
	public search(String query)
	{
		String[] firstWord = query.split(" ");
		termArray = new String[firstWord.length];
		for(String word: firstWord)
		{
			addTerm(word);
		}
	}
	
	public void addTerm(String term)
	{
		termArray[index] = term;
		index++;
	}
	
	
	public String results()
	{
		if(termArray.length == 1)
		{
			bucket b = database.buckets.get(termArray[0]);
			if(b != null)
			{
				return database.buckets.get(termArray[0]).toString();

			}
			else
			{
				return "QUERY TERM NOT FOUND";

			} 
		}
		else if(termArray.length > 1)
		{
			List<paragraphObject> l1 = new ArrayList<paragraphObject>();
			List<paragraphObject> l2 = new ArrayList<paragraphObject>();
			String str = "";
				
			for(paragraphObject po : database.buckets.get(termArray[0]))
			{
				l1.add(po);
			}
			//check all buckets for commmon elements
			for(int i = 1; i < termArray.length; i++)
			{
				for(paragraphObject po : database.buckets.get(termArray[i]))
				{
					l2.add(po);
				}
				l1.retainAll(l2);
				l2.clear();
			}
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
		else
		{
			return "NO QUERY TERMS ENTERED";
		}
	}
}
