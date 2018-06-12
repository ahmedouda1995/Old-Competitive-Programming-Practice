package ch2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class UVa_11348_Exhibition {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		TreeSet<Integer> unique = new TreeSet<Integer>(), notUnique = new TreeSet<Integer>();
		
		int t = sc.nextInt();
		int cases = 1;
		while(t-- > 0){
			int n = sc.nextInt();
			int [][] a = new int[n][];
			for (int i = 0; i < n; ++i) {
				a[i] = new int[sc.nextInt()];
				for (int j = 0; j < a[i].length; j++) {
					a[i][j] = sc.nextInt();
					if(!notUnique.contains(a[i][j]) && !unique.contains(a[i][j])){
						unique.add(a[i][j]);
					}
					else if(unique.contains(a[i][j])){
						unique.remove(a[i][j]);
						notUnique.add(a[i][j]);
					}
				}
			}
			double [] b = new double[n];
			double total = 0;
			for (int i = 0; i < a.length; i++) {
				for (int j = 0; j < a[i].length; j++) {
					if(unique.contains(a[i][j])){
						b[i]++;
						total++;
					}
				}
			}
			out.print("Case " + cases + ": ");
			for (int i = 0; i < b.length; i++) {
				out.print((b[i] / total) * 100+ "% ");
			}
			out.println();
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