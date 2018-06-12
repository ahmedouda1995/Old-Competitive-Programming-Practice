package ch3_BruteForce_and_Backtracking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_1237_Expert_Enough {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt();
		while(t-- > 0){
			int n = sc.nextInt();
			String man [] = new String[n]; int [] l = new int[n], h = new int[n];
			for(int i = 0; i < n; ++i){
				man[i] = sc.next(); l[i] = sc.nextInt(); h[i] = sc.nextInt();
			}
			n = sc.nextInt();
			String res;
			for (int i = 0; i < n; ++i) {
				int p = sc.nextInt();
				res = null;
				for (int j = 0; j < man.length; j++) {
					if(p >= l[j] && p <= h[j]){
						if(res == null) res = man[j];
						else {
							res = null; break;
						}
					}
				}
				out.println((res != null)?res:"UNDETERMINED");
			}
			if(t != 0) out.println();
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