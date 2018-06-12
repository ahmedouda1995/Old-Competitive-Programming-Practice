package ch2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class UVA_230_Borrowers {

	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		ArrayList<Book> a = new ArrayList<Book>();
		ArrayList<Book> toBeShelved = new ArrayList<Book>();
		Hashtable<String, String> h = new Hashtable<String, String>();
		
		String book, action;
		
		while(!(book = sc.nextLine()).equals("END")){
			String [] tmp = book.split(" by ");
			a.add(new Book(tmp[1], tmp[0]));
			h.put(tmp[0], tmp[1]);
		}
		
		Collections.sort(a);
		
		while(!(action = sc.nextLine()).equals("END")){
			if(action.startsWith("RETURN")){
				String [] titleRaw = action.split(" \"");
				String title = "\"" + titleRaw[1];
				toBeShelved.add(new Book(h.get(title), title));
			}
			else if(action.equals("SHELVE")){
				Collections.sort(toBeShelved);
				for(Book b : toBeShelved){
					String temp = getPrev(b, a);
					if(temp.equals("first"))
						out.println("Put " + b.title + " first");
					else 
						out.println("Put " + b.title + " after " + temp);
				}
				out.println("END");
				toBeShelved.clear();
			}
			else {
				String title = "\"" + action.split(" \"")[1];
				for(int i=0;i<a.size();i++){
					if(a.get(i).title.equals(title)){
						a.get(i).author = null;
						break;
					}	
				}
			}
		}
		
		out.flush();
		out.close();
	}

	private static String getPrev(Book b, ArrayList<Book> a) {
		int i;
		for(i = 0;i<a.size();i++){
			if(a.get(i).title.equals(b.title)){
				a.get(i).author = b.author;
				i--;
				break;
			}
				
		}
		
		while(i >= 0){
			if(a.get(i).author != null)
				return a.get(i).title;
			i--;
		}
		
		return "first";
	}

	static class Book implements Comparable{
		String author, title;
		
		public Book(String author, String title) {
			this.author = author;
			this.title = title;
		}
		
		@Override
		public String toString() {
			return "(" + author + "," + title + ")";
		}

		@Override
		public int compareTo(Object o) {
			Book second = (Book) o;
			if(this.author.compareTo(second.author) == 0)
				return this.title.compareTo(second.title);
			else return this.author.compareTo(second.author);
		}
	}
	
	static class Scanner{
		StringTokenizer st;
		BufferedReader br;

		public Scanner(InputStream s){	br = new BufferedReader(new InputStreamReader(s));}

		public Scanner(FileReader r){	br = new BufferedReader(r);}

		public String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}

		public int nextInt() throws IOException {return Integer.parseInt(next());}

		public long nextLong() throws IOException {return Long.parseLong(next());}

		public String nextLine() throws IOException {return br.readLine();}

		public double nextDouble() throws IOException { return Double.parseDouble(next()); }

		public boolean ready() throws IOException {return br.ready();}
	}
}
