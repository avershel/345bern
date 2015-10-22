package theGutenSearch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/*
 * bucket
 * A class that creates a bucket
 * 
 * @author group 7
 */
public class bucket implements Iterable<paragraphObject>{
	public String key;
	public Set<paragraphObject> parList = new HashSet<paragraphObject>();
	public int size = 0;
	
	
	/*
	 * A paragraphObject constructor
	 * @param bucketKey the key of the bucket
	 */
	public bucket(String bucketKey)
	{
		this.key = bucketKey;
	}
	
	/*
	 * A helper method for accepting paragraphObjects
	 * 
	 * @param po the paragraphObject to add
	 */
	public void addParObject(paragraphObject po)
	{
		this.parList.add(po);
		size++;
	}
	
	/*
	 * A helper method to convert the bucket to string
	 * 
	 * @return str the string representation of the bucket
	 */
	public String toString()
	{
		String str = "";
		for(paragraphObject po : parList)
		{
			str += po.toString();
		}
		return str;
	}
	
	/*
	 * A helper method to convert the bucket to string
	 * 
	 * @return parlist.size the size of the bucket
	 */
	public int length()
	{
		return parList.size();
	}

	@Override
	public Iterator<paragraphObject> iterator() {
		// TODO Auto-generated method stub
		return parList.iterator();
	}

}
