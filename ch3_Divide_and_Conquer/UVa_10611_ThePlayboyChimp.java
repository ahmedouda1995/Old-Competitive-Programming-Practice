package ch3_Divide_and_Conquer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_10611_ThePlayboyChimp {

	static int N;
	static int arr[];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		N = sc.nextInt();
		arr = new int[N];
		
		for(int i = 0; i < N; ++i) arr[i] = sc.nextInt();
		
		int q = sc.nextInt(), a, lower, upper;
		
		while(q-- > 0) {
			a = sc.nextInt();
			
			lower = lowerBound(a);
			upper = upperBound(a);
			
			if(lower >= a)
				out.print("X");
			else
				out.print(lower);
			
			if(upper <= a)
				out.println(" X");
			else
				out.println(" " + upper);
		}
		
		out.flush();
		out.close();
	}
	
	private static int lowerBound(int a) {
		int lo = 0, hi = N - 1, ans = arr[N - 1] + 1, mid;
		
		while(lo <= hi) {
			mid = (lo + hi >> 1);
			
			if(arr[mid] < a) {
				ans = arr[mid];
				lo = mid + 1;
			}
			else
				hi = mid - 1;
		}
		
		return ans;
	}
	
	private static int upperBound(int a) {
		int lo = 0, hi = N - 1, ans = -1, mid;
		
		while(lo <= hi) {
			mid = (lo + hi >> 1);
			
			if(arr[mid] > a) {
				ans = arr[mid];
				hi = mid - 1;
			}
			else
				lo = mid + 1;
		}
		
		return ans;
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