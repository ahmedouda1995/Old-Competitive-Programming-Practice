package ch3_greedy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_11389_The_Bus_Driver_Problem_rev {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n, d, r;
		int mor[], aft[];
		while((n = sc.nextInt()) != 0 | (d = sc.nextInt()) != 0 | (r = sc.nextInt()) != 0) {
			mor = new int[n]; aft = new int[n];
			for(int i = 0; i < n; ++i) mor[i] = sc.nextInt();
			for(int i = 0; i < n; ++i) aft[i] = sc.nextInt();
			
			Arrays.sort(mor); Arrays.sort(aft);
			int hours = 0;
			for(int i = 0; i < n; ++i){
				if(mor[i] + aft[n - i - 1] > d){
					hours += (mor[i] + aft[n - i - 1]) - d;
				}
			}
			
			out.println(hours * r);
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