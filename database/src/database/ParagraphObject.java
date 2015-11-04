package GutenSearch;
/*
 * ParagraphObject
 * A class that creates paragraph objects
 *
 * @author group 7
 */
public class ParagraphObject {
  public String title;
  public int ordinal;
  public String author;
  public String paragraph;
  public int arrindex;

  /*
   * A ParagraphObject constructor
   *
   * @param paragraph1 - the text of the paragraph
   * @param title1 - the title of the text
   * @param author1 - the author of the text
   * @param ord - the ordinal number of the paragraph
   * @param i - the array index of the paragraph
   */
  public ParagraphObject(String paragraph1, String title1, String author1, int ord, int i)
  {
    arrindex = i;
    ordinal = ord;
    paragraph = paragraph1;
    title = title1;
    author = author1;
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
   * A helper method to add text to Buckets
   *
   * @param key - the term(s) to be added
   */
  public void addToBucket(String key)
  {
    Bucket value = Database.buckets.get(key);
    //if the Bucket exists
    if (value != null)
    {
      value.addParObject(Database.pomap.get(Integer.toString(arrindex)));
      Database.buckets.put(key, value);
    }
    else
    {
      Bucket buck = new Bucket(key);
      ParagraphObject p = Database.pomap.get(Integer.toString(arrindex));
      buck.addParObject(p);
      Database.buckets.put(key, buck);
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
   * A helper method to convert the ParagraphObject to string
   *
   * @return str the string representation of the ParagraphObject
   */
  public String toString()
  {
    String str = String.format("Title: %s\nAuthor: %s\nOrdinal: %d\n", title, author, ordinal);
    for(int i = 0; i < paragraph.length(); i++)
    {
        if('.' == paragraph.charAt(i))
        {
            return str += paragraph.substring(0,i+1) + "\n\n";
        }
    }
    
    str += paragraph + "\n\n";
    return str;
  }
}
