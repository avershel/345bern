package GutenSearch;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
/**
 * Bucket
 * A class that creates a Bucket 
 *
 * @author group 7
 */
public class Bucket implements Iterable<ParagraphObject>{
  public String key;
  public Set<ParagraphObject> parList = new HashSet<ParagraphObject>();
  public int size = 0;

  /**
   * A ParagraphObject constructor
   * @param BucketKey the key of the Bucket
   */
  public Bucket(String BucketKey)
  {
    this.key = BucketKey;
  }

  /**
   * A helper method for accepting ParagraphObjects
   *
   * @param po the ParagraphObject to add
   */
  public void addParObject(ParagraphObject po)
  {
    this.parList.add(po);
    size++;
  }

  /**
   * A helper method to convert the Bucket to string
   *
   * @return str the string representation of the Bucket
   */
  public String toString()
  {
    String str = "";
    for(ParagraphObject po : parList)
    {
      str += po.toString();
    }
    return str;
  }

  /**
   * A helper method to convert the Bucket to string
   *
   * @return parlist.size the size of the Bucket
   */
  public int length()
  {
    return parList.size();
  }

  @Override
  public Iterator<ParagraphObject> iterator() {
    return parList.iterator();
  }

}
