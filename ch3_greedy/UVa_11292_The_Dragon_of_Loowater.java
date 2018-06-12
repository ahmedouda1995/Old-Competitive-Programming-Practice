package ch3_greedy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_11292_The_Dragon_of_Loowater {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int n, m;
		while((n = sc.nextInt()) != 0 | (m = sc.nextInt()) != 0){
			int [] a = new int[m], b = new int[n];
			
			for(int i = 0; i < n; ++i) b[i] = sc.nextInt();
			for(int i = 0; i < m; ++i) a[i] = sc.nextInt();
			
			Arrays.sort(a); Arrays.sort(b);
			if(n > m)
				out.println("Loowater is doomed!");
			else {
				long money = 0;
				int i, j;
				for(i = 0,j = 0; i < m && j < n; ++i){
					if(a[i] >= b[j]){
						money += a[i];
						++j;
					}
				}
				if(j == n)
					out.println(money);
				else
					out.println("Loowater is doomed!");
			}
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