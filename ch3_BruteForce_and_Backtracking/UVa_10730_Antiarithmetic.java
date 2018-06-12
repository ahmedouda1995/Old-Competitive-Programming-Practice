package ch3_BruteForce_and_Backtracking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// use boolean seen array for pruning & to be able to use only 2 loops

public class UVa_10730_Antiarithmetic {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		String s;
		while(!(s = sc.nextLine()).equals("0")){
			String tmp[] = s.split(":");
			int n = Integer.parseInt(tmp[0]);
			int [] a = new int[n];
			StringTokenizer st = new StringTokenizer(tmp[1]);
			for (int i = 0; i < n; ++i) a[i] = Integer.parseInt(st.nextToken());
			boolean seen[] = new boolean[n];
			boolean thereIsProg = false;
			
			label : for (int i = 0; i < n; i++) {
				seen[a[i]] = true;
				for (int j = i + 1; j < n; j++) {
					seen[a[j]] = false;
				}
				for (int j = i + 1; j < n; j++) {
					seen[a[j]] = true;
					int next = a[j] + a[j] - a[i];
					if(next >= 0 && next < n && !seen[next]){
						thereIsProg = true;
						break label;
					}
				}
			}
			if(thereIsProg)
				out.println("no");
			else
				out.println("yes");
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