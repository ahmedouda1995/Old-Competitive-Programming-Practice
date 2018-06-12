package ch3_Divide_and_Conquer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_12032_The_Monkey {

	static int [] a = new int[100000];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), cases = 1;
		while(t-- > 0){
			int n = sc.nextInt();
			for(int i = 0; i < n; ++i) a[i] = sc.nextInt();
			
			out.println("Case " + cases++ + ": " + solve(n));
		}
		
		out.flush();
		out.close();
	}
	
	private static int solve(int n) {
		int l = 1, h = (int) 1e7;
		int oldLow = -1, oldHigh = -1;
		int ans = 0;
		while((h - l) > 0 && (oldLow != l || oldHigh != h)){
			oldLow = l; oldHigh = h;
			int mid = l + (h - l) / 2;
			if(check(n, mid)){
				ans = mid;
				h = mid;
			}
			else
				l = mid;
		}
		return ans;
	}

	private static boolean check(int n, int k){
		int level = 0;
		for(int i = 0; i < n; ++i) {
			int diff = a[i] - level;
			if(diff > k)
				return false;
			else if(diff == k)
				k--;
			
			level += diff;
		}
		return true;
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