package gutenSearch;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class bucket implements Iterable<paragraphObject>{
	public String key;
	public Set<paragraphObject> parList = new HashSet<paragraphObject>();
	public int size = 0;
	
	public bucket(String bucketKey)
	{
		this.key = bucketKey;
	}
	
	public void addParObject(paragraphObject po)
	{
		this.parList.add(po);
		size++;
	}
	
	public String toString()
	{
		String str = "";
		for(paragraphObject po : parList)
		{
			str += po.toString();
		}
		return str;
	}
	
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
