package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Stack;
import java.util.StringTokenizer;

public class UVa_12582_WeddingofSultan {

	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), cases = 1;
		String s;
		
		while(t-- > 0) {
			s = sc.nextLine();
			ArrayList<Pair> a = new ArrayList<Pair>();
			Stack<Pair> st = new Stack<Pair>();
			st.push(new Pair(s.charAt(0),  0));
			for(int i = 1; i < s.length(); ++i) {
				if(st.peek().c == s.charAt(i))
					a.add(st.pop());
				else {
					Pair tmp = st.pop();
					st.push(new Pair(tmp.c, tmp.n + 1));
					st.push(new Pair(s.charAt(i), 1));
				}
			}
			
			Collections.sort(a);
			out.printf("Case %d\n", cases++);
			for(Pair p : a) out.printf("%c = %d\n", p.c, p.n);
		}
		
		out.flush();
		out.close();
	}
	
	static class Pair implements Comparable<Pair>{
		char c;
		int n;
		
		public Pair(char c, int n) {
			this.c = c;
			this.n = n;
		}

		@Override
		public int compareTo(Pair p) {
			return Character.compare(c, p.c);
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