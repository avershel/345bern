package GutenSearch;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
/*
 * Database
 * A class that creates a Database
 *
 * @author group 7
 */
public class Database {
  public static HashMap<String, Bucket> buckets;
  public static int arrindex = 0;
  public static HashMap<String, ParagraphObject> pomap;
  public static int textcount = 0;

  /*
   * A ParagraphObject constructor
   *
   */
  public Database()
  {
    buckets = new HashMap<String,Bucket>();
    pomap = new HashMap<String,ParagraphObject>();
  }

  /*
   * A helper method to add text files to the Database
   *
   * @param filename -the file to add
   *
   */
  public void addTxtFiles(File filename) throws FileNotFoundException, IOException
  {
    parseText(readFile(filename.toString()));
  }

  /*
   * A helper method to read files to the Database
   *
   * @param filename -the file to read
   *
   */
  public static String readFile(String fileName) throws IOException
  {
	  textcount++;
    BufferedReader buffread = new BufferedReader(new FileReader(fileName));
    try
    {
      StringBuilder stringbuild = new StringBuilder();
      String line = buffread.readLine();

      while (line != null) {
        stringbuild.append(line);
        stringbuild.append("\n");
        line = buffread.readLine();
      }
      return stringbuild.toString();
    }
    finally
    {
      buffread.close();
    }
  }

  /**
   * Takes in string object(the full text)
   * extracts title, author, converts to paragraph objects
   * @param text - the full text file
   */
  public static void parseText(String text)
  {
    String author = "";
    String title = "";
    int index;
    String[] paragraphs = text.split("\n\n");

    for (String paragraph : paragraphs)
    {
      String arr[] = paragraph.split(" ");
      if(arr[0].equals("Author:"))
      {
        for(int i = 1; i<arr.length; i++)
        {
          author += arr[i];
          author += " ";
        }
      }
      if(arr[0].equals("\nTitle:"))
      {
        for(int j = 1; j<arr.length; j++)
        {
          title += arr[j];
          title += " ";
        }
      }
    }

    index = 1;
    for (String paragraph : paragraphs)
    {
      if(!paragraph.equals(""))
      {
        //new paragraph object
        //set title
        //set author
        //set paragraph
        ParagraphObject p0 = new ParagraphObject(paragraph, title, author, index, arrindex);
        pomap.put(Integer.toString(arrindex), p0);

        p0.parse();
        arrindex++;
        index ++;
      }
    }
  }

  /*
   * A helper method to search the Database
   *
   * @param query - the term(s) to search
   *
   */
  public String search(String query)
  {
    return new Search(query).results();
  }

  /**
   * Edits the source title and author of paragraph objects that match the entered "title" and "author" strings.
   * if the Administrator wants to edit those attributes
   *
   * @param title -title to change
   * @param author  -author to change
   * @param newTitle  -the new title
   * @param newAuthor -the new author
   */
  public void editBook(String title, String author,String newTitle, String newAuthor)
  {
    ParagraphObject po;

    for(String s : pomap.keySet())
    {
      po = pomap.get(s);
      if(po.getTitle().equals(title) && po.getAuthor().equals(author))
      {
        if(newTitle == null || newTitle.length() > 0 || newTitle.equals(""))
        {
          pomap.get(po.arrindex).editTitle(newTitle);
        }
        if(newAuthor == null || newAuthor.length() > 0 || newAuthor.equals(""))
        {
          pomap.get(po.arrindex).editAuthor(newAuthor);
        }
      }
    }
  }
}
