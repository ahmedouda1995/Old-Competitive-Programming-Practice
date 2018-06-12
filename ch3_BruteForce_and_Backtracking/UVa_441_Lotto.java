package ch3_BruteForce_and_Backtracking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_441_Lotto {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n; String s = "";
		int [] a = new int[12];
		while((n = sc.nextInt()) != 0){
			out.print(s);
			for(int i = 0; i < n; ++i) a[i] = sc.nextInt();
			for (int i = 0; i < n - 5; i++) {
				for (int j = i + 1; j < n - 4; j++) {
					for (int j2 = j + 1; j2 < n - 3; j2++) {
						for (int k = j2 + 1; k < n - 2; k++) {
							for (int k2 = k + 1; k2 < n - 1; k2++) {
								for (int l = k2 + 1; l < n; l++) {
									out.printf("%d %d %d %d %d %d\n", a[i], a[j], a[j2], a[k], a[k2], a[l]);
								}
							}
						}
					}
				}
			}
			s = "\n";
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