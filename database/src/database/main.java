package gutenSearch;

import java.io.FileNotFoundException;
import java.io.IOException;

public class main {
	public static void main(String [] args) throws FileNotFoundException, IOException
	{
		database db = new database();
		db.addTxtFiles(args[0]);
		System.out.println(db.search("was&the"));
	}
}
