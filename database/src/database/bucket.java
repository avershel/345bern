package database;

import java.util.ArrayList;
import java.util.Iterator;

public class bucket implements Iterable<paragraphObject>{
	public String key;
	public ArrayList<paragraphObject> parList = new ArrayList<paragraphObject>();
	
	public bucket(String bucketKey)
	{
		this.key = bucketKey;
	}
	
	public void addParObject(paragraphObject po)
	{
		this.parList.add(po);
	}
	
	public String toString()
	{
		String str = "";
		for(paragraphObject po : parList)
		{
			str += po;
		}
		
		return str;
	}

	@Override
	public Iterator<paragraphObject> iterator() {
		// TODO Auto-generated method stub
		return parList.iterator();
	}

}
