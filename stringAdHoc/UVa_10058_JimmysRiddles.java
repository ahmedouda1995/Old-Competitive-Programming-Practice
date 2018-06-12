package stringAdHoc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_10058_JimmysRiddles {

	private static final String VERB = "(hate|love|know|like)s*";
	private static final String NOUN = "(tom|jerry|goofy|mickey|jimmy|dog|cat|mouse)";
	private static final String ARTICLE = "(a|the)";
	private static final String ACTOR = "(" + NOUN + "|" + ARTICLE + " " + NOUN + ")";
	private static final String ACTIVE_LIST = "(" + ACTOR + " and )*" + ACTOR;
	private static final String ACTION = ACTIVE_LIST + " " + VERB + " " + ACTIVE_LIST;
	private static final String STATEMENT = ACTION + "( , " + ACTION + ")*";
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		while(sc.ready()) {
			out.println(sc.nextLine().replaceAll("\\s+", " ").trim().matches(STATEMENT)?"YES I WILL":"NO I WON'T");
		}

		out.flush();
		out.close();
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