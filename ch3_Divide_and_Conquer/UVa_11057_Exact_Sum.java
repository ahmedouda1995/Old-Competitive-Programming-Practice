package ch3_Divide_and_Conquer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_11057_Exact_Sum {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		String s;
		while((s = sc.nextLine()) != null){
			int n = Integer.parseInt(s);
			int [] a = new int[n];
			for(int i = 0; i < n; ++i) a[i] = sc.nextInt();
			int m = sc.nextInt();
			Arrays.sort(a);
			int diff = Integer.MAX_VALUE;
			int l = 0, r = n - 1;
			for (int i = 0; i < n - 1; i++) {
				int pos = Arrays.binarySearch(a, i + 1, n, m - a[i]);
				if(pos >= 0){
					int diffTmp = Math.abs(a[i] - a[pos]);
					if(diffTmp < diff){
						diff = diffTmp;
						l = i; r = pos;
					}
				}
			}
			out.println("Peter should buy books whose prices are " + a[l] + " and " + a[r] + ".");
			out.println();
			sc.nextLine();
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