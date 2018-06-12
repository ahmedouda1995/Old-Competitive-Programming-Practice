package ch6_5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_11552_FewestFlops {

	static final int INF = (int) 1e9;
	static int N, K;
	static int memo[][];
	static int arr[][];
	static int count[];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt();
		char [] s;
		
		while(t-- > 0) {
			K = sc.nextInt(); s = sc.next().toCharArray();
			
			N = s.length / K;
			arr = new int[N][];
			count = new int[N];
			for(int i = 0; i < N; ++i) arr[i] = new int[26];
			
			for(int i = 0; i < N; ++i) {
				for(int j = i * K; j < i * K + K; ++j)
					arr[i][s[j] - 'a']++;
				int c = 0;
				for(int j = 0; j < 26; ++j)
					if(arr[i][j] > 0) c++;
				count[i] = c;
			}
			memo = new int[N][27];
			//for(int i = 0; i < N; ++i) System.out.println(Arrays.toString(arr[i]));
			for(int i = 0; i < N; ++i) Arrays.fill(memo[i], -1);
			out.println(solve(0, 26));
		}

		out.flush();
		out.close();
	}
	
	private static int solve(int idx, int prev) {
		if(idx == N) return 0;
		if(memo[idx][prev] != -1) return memo[idx][prev];
		
		int min = INF;
		
		for(int i = 0; i < 26; ++i) {
			if(arr[idx][i] == K) {
				if(prev == i)
					{ min = solve(idx + 1, i); break; }
				else
					{ min = count[idx] + solve(idx + 1, i); break; }
			}
			else if(arr[idx][i] > 0) {
				for(int j = i + 1; j < 26; ++j) {
					if(arr[idx][j] > 0) {
						if(i == prev)
							min = Math.min(min, count[idx] - 1 + solve(idx + 1, j));
						else
							min = Math.min(min, count[idx] + solve(idx + 1, j));
						if(j == prev)
							min = Math.min(min, count[idx] - 1 + solve(idx + 1, i));
						else
							min = Math.min(min, count[idx] + solve(idx + 1, i));
					}
				}
			}
		}
		return memo[idx][prev] = min;
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