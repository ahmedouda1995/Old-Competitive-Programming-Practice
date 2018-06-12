package ch3_BruteForce_and_Backtracking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class UVa_12488_Start_Grid {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		ArrayList<Integer> st;
		String s; int n, fin[];
		while((s = sc.nextLine()) != null){
			n = Integer.parseInt(s);
			st = new ArrayList<Integer>(n); fin = new int[n];
			for (int i = 0; i < n; ++i) st.add(sc.nextInt());
			for (int i = 0; i < n; ++i) fin[i] = sc.nextInt();
			int overTakes = 0;
			for (int i = 0; i < fin.length; i++) {
				int pos = st.indexOf(fin[i]);
				st.remove(pos);
				overTakes += (pos - i);
				st.add(i, fin[i]);
			}
			out.println(overTakes);
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