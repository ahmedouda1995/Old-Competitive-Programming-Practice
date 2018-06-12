package ds_math_alg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NumberTheory6 {
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int n;
		
		n = sc.nextInt();
		
		int arr[] = new int[n];
		for(int i = 0; i < n; ++i)
			arr[i] = sc.nextInt();
		
		int ans[] = new int[n];
		
		for(int i = 0; i < n; ++i)
			for(int j = i; j < n; ++j)
			{
				if(gcd(arr[i], arr[j]) == 1)
				{
					ans[i]++;
					if(i != j)
						ans[j]++;
				}
			}
		
		for(int i = 0; i < n; ++i)
			out.print(ans[i] * ans[i] + " ");
		
		out.flush();
		out.close();
	}
	
	static long lcm(int a, int b)
	{
		return 1L * a / gcd(a, b) * b;
	}
	
	static int gcd(int a, int b) {
		return b == 0?a:gcd(b, a % b);
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