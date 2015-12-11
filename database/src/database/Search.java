package GutenSearch;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * search1 A class that handles searching through the ParagraphObjects for a
 * specific term
 *
 * @author group 7
 */
public class Search {

	String[] termArray;
	List<ParagraphObject> l1 = new ArrayList<ParagraphObject>();
	List<ParagraphObject> l2 = new ArrayList<ParagraphObject>();
	String[] expholder;
	int index = 0;
	String words;

	/**
	 * A search object constructor
	 *
	 * @param query
	 *            - the term(s) to be searched
	 */
	public Search(String query) {
		this.words = query;
		String query1 = query;
		for (int i = 0; i < query1.length(); i++) {
			if (query1.charAt(i) == '?') {
				for (char j = 'a'; j <= 'z'; j++) {
					query += "| " + query.substring(0, i) + j + " ";
				}
			}
		}
		String[] firstWord1 = query.split("\\&| |\\|");
		for (String str : firstWord1) {
			Bucket b = Database.buckets.get(str);
			if (b != null) {
				for (ParagraphObject po : b) {
					l1.add(po);

				}

			}
		}

		query = query.toLowerCase();
		String[] firstWord = query.split("\\|");
		termArray = new String[firstWord.length];
		expholder = new String[firstWord.length];

		for (String word : firstWord) {
			parseOr(word);
		}
	}

	/**
	 * A helper method for parsing OR terms
	 *
	 * @param exp
	 *            - the expression to be parsed
	 */
	public void parseOr(String exp) {
		if (exp.indexOf('&') >= 0) {
			expholder = exp.split("\\&");

			parseAnd(expholder);

		} else {
			expholder = exp.split(" ");
			for (String e : expholder) {
				addPars(e);
			}
		}
	}

	/**
	 * A helper method for parsing single terms
	 *
	 * @param exp
	 *            - the expression to be parsed
	 */
	public void addPars(String exp) {

		Bucket b = Database.buckets.get(exp);
		if (b != null) {
			for (ParagraphObject po : b) {
				l1.add(po);

			}
		}
	}

	/**
	 * A helper method for parsing AND terms
	 *
	 * @param holder
	 *            - an array containing joined expressions
	 */
	public void parseAnd(String[] holder) {
		List<ParagraphObject> ll1 = new ArrayList<ParagraphObject>();
		List<ParagraphObject> ll2 = new ArrayList<ParagraphObject>();
		index = 0;
		for (int i = 0; i < holder.length; i++) {
			if (index == 0) {

				Bucket b = Database.buckets.get(holder[i]);
				if (b != null) {
					for (ParagraphObject po : b) {
						ll1.add(po);

					}
					index++;

				}
			} else {

				Bucket b = Database.buckets.get(holder[i]);
				if (b != null) {
					for (ParagraphObject po : b) {
						ll2.add(po);

					}
					ll1.retainAll(ll2);
					ll2.clear();

				}
			}
		}

		l1.retainAll(ll1);
	}

	/**
	 * A method to return the results of the search
	 *
	 * @return a String representation of the search results
	 */
	public String results() {
		Set<ParagraphObject> hld = new LinkedHashSet<>(l1);
		l1.clear();
		l1.addAll(hld);

		String str = "";
		if (l1.size() > 0) {
			for (ParagraphObject po : l1) {
				str += po.toString();
			}
			return str;
		} else {
			return "QUERY TERM NOT FOUND";
		}
	}

	/**
	 * A method to return the results of the search
	 *
	 * @return a String representation of the search results
	 */
	public String advancedresults() {
		System.out.println(this.words);
		Set<ParagraphObject> hld = new LinkedHashSet<>(l1);
		l1.clear();
		l1.addAll(hld);

		String str = "";
		if (l1.size() > 0) {
			for (ParagraphObject po : l1) {
				if (po.paragraph.toLowerCase().replace("\n", " ").contains(this.words.toLowerCase())) {
					str += po.toString();
				}
				// str += po.toString();
			}
			return str;
		} else {
			return "QUERY TERM NOT FOUND";
		}
	}

	/**
	 * A helper method for parsing wildcard expressions
	 *
	 * @param text
	 *            - the expression to be parsed
	 */
	public static void wild(String text) {

		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) == '?') {
				for (char j = 'a'; j <= 'z'; j++) {
					new Search(text.replace('?', j));
				}
			}
		}
	}

	/**
	 * A helper method for parsing star expressions
	 *
	 * @param text
	 *            - the expression to be parsed
	 */
	public static void star(String text) {
		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) == '*') {
				new Search(text.substring(0, i));

			}
		}
	}
}
