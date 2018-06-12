package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class UVa_10667_LargestBlock {

	static int grid[][] = new int[100][100];
	static int N;
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), m;
		
		while(t-- > 0) {
			N = sc.nextInt();
			fill(0, 0, N - 1, N - 1, 1);
			
			m = sc.nextInt();
			
			while(m-- > 0) {
				fill(sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt() - 1, 0);
			}
			
			out.println(solve());
		}
		
		out.flush();
		out.close();
	}

	private static void fill(int i, int j, int a, int b, int n) {
		int tmp = j;
		for(; i <= a; ++i) {
			for(j = tmp; j <= b; j++) {
				grid[i][j] = n;
			}
		}
	}

	private static int solve() {
		int max = hist(grid[0]);
		
		for(int i = 1; i < N; ++i) {
			for(int j = 0; j < N; ++j) {
				if(grid[i][j] == 1)
					grid[i][j] += grid[i - 1][j];
			}
			max = Math.max(max, hist(grid[i]));
		}
		return max;
	}

	private static int hist(int[] a) {
		Stack<Integer> st = new Stack<Integer>();
		
		int i = 0;
		int max = 0;
		
		while(i < N) {
			if(st.isEmpty() || a[st.peek()] <= a[i])
				st.push(i++);
			else {
				while(!st.isEmpty() && a[st.peek()] > a[i]) {
					int top = st.pop();
					max = Math.max(max, a[top] * ((st.isEmpty())?i:i - st.peek() - 1));
				}
			}
		}
		
		while(!st.isEmpty()) {
			int top = st.pop();
			max = Math.max(max, a[top] * ((st.isEmpty())?i:i - st.peek() - 1));
		}
		return max;
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