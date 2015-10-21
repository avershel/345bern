package gutenSearch;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class search1 {
	
	String [] termArray;
	List<paragraphObject> l1 = new ArrayList<paragraphObject>();
	List<paragraphObject> l2 = new ArrayList<paragraphObject>();
	String []expholder;
	int index = 0;

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
	
	//adds single word buckets to query
	public void addPars(String exp)
	{
		
			bucket b = database.buckets.get(exp);
			if(b != null)
			{
				for(paragraphObject po : b)
				{
					l2.add(po);

				}
				l1.retainAll(l2);
				l2.clear();
			}
	}
	
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
}
