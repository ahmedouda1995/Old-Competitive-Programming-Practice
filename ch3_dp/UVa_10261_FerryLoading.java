package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_10261_FerryLoading {

	static int N, L;
	static int memo[][] = new int[200][10001];
	static int arr[] = new int[200];
	static PrintWriter out = new PrintWriter(System.out);
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));

		int t = sc.nextInt();
		boolean first = true;
		
		while(t-- > 0) {
			if(first) first = false; else out.println();
			L = sc.nextInt() * 100;
			int i = 0;
			while(true) {
				int tmp = sc.nextInt();
				if(tmp == 0) break;
				if(i >= 200) continue;
				arr[i++] = tmp;
			}
			N = i;
			
			for(i = 0; i < N; ++i) Arrays.fill(memo[i], -1);
			out.println(solve(0, 0, 0));
			print(0, 0, 0);
		}
		
		out.flush();
		out.close();
	}
	
	private static void print(int idx, int l, int r) {
		int n = solve(0, 0, 0);
		
		while(n-- > 0) {
			int left = 0, right = 0, curr = solve(idx, l, r);
			if(l + arr[idx] <= L) {
				left = 1 + solve(idx + 1, l + arr[idx], r);
			}
			if(r + arr[idx] <= L) {
				right = 1 + solve(idx + 1, l, r + arr[idx]);
			}
			if(left == curr) {
				out.println("port");
				l += arr[idx];
			}
			else if(right == curr) {
				out.println("starboard");
				r += arr[idx];
			}
			idx++;
		}
	}

	private static int solve(int idx, int l, int r) {
		if(idx == N) return 0;
		if(l + arr[idx] > L && r + arr[idx] > L) return 0;
		if(memo[idx][l] != -1) return memo[idx][l];
		
		int left = 0, right = 0;
		if(l + arr[idx] <= L) {
			left = 1 + solve(idx + 1, l + arr[idx], r);
		}
		if(r + arr[idx] <= L) {
			right = 1 + solve(idx + 1, l, r + arr[idx]);
		}
		
		return memo[idx][l] = Math.max(left, right);
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