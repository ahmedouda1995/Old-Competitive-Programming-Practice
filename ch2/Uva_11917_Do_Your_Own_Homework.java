package ch2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Uva_11917_Do_Your_Own_Homework {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		int t = sc.nextInt(), cases = 1;
		while(t-- > 0){
			map.clear();
			int n = sc.nextInt();
			for (int i = 0; i < n; ++i) {
				map.put(sc.next(), sc.nextInt());
			}
			int d = sc.nextInt();
			String s = sc.next();
			if(!map.containsKey(s) || map.get(s) > (d + 5))
				out.println("Case " + cases + ": Do your own homework!");
			else if(map.get(s) <= d)
				out.println("Case " + cases + ": Yesss");
			else
				out.println("Case " + cases + ": Late");
			cases++;
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