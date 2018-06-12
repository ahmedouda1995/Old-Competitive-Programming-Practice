package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class UVa_11284_ShoppingTrip {

	static long INF = (long) 1e15;
	static int N, M, STORES;
	static long[][] adj;
	static long memo[][] = new long[13][1 << 13];
	static TreeMap<Integer, Long> map = new TreeMap<Integer, Long>();
	static int [] arr;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt();
		
		while(t-- > 0) {
			map.clear();
			N = sc.nextInt() + 1; M = sc.nextInt();
			adj = new long[N][N];
			
			for(int i = 0; i < N; ++i) {
				Arrays.fill(adj[i], INF);
				adj[i][i] = 0;
			}
			
			int a, b;
			long c;
			StringTokenizer st;
			
			for(int i = 0; i < M; ++i) {
				a = sc.nextInt(); b = sc.nextInt();
				st = new StringTokenizer(sc.next(), ".");
				c = (Long.parseLong(st.nextToken()) * 100 + Long.parseLong(st.nextToken()));
				
				if(c < adj[a][b])
					adj[a][b] = adj[b][a] = c;
			}
			floyd();
			
			int m = sc.nextInt();
			
			while(m-- > 0) {
				a = sc.nextInt();
				st = new StringTokenizer(sc.next(), ".");
				c = (Long.parseLong(st.nextToken()) * 100 + Long.parseLong(st.nextToken()));
				
				if(!map.containsKey(a)) map.put(a, c);
				else {
					map.replace(a, map.get(a) + c);
				}
			}
			
			map.put(0, 0L);
			
			N = map.size();
			arr = new int[N];
			int k = 0;
			for(Entry<Integer, Long> e : map.entrySet()) {
				arr[k++] = e.getKey();
			}
			
			for(int i = 0; i < N; ++i) Arrays.fill(memo[i], -1);
			
			long res = solve(0, 1);
			if(res <= 0)
				out.println("Don't leave the house");
			else
				out.printf("Daniel can save $%.2f\n", (res / 100.0));
		}
		
		out.flush();
		out.close();
	}
	
	private static long solve(int vertex, int mask) {
		if(mask == (1 << N) - 1) return -adj[arr[vertex]][0];
		if(memo[vertex][mask] != -1) return memo[vertex][mask];
		
		long max = -adj[arr[vertex]][0];
		
		for(int i = 0; i < N; ++i) {
			if((((1 << i) & mask) == 0)) {
				max = Math.max(max, -adj[arr[vertex]][arr[i]] +
						map.get(arr[i]) + solve(i, mask | (1 << i)));
			}
		}
		return memo[vertex][mask] = max;
	}

	private static void floyd() {
		for(int k = 0; k < N; ++k)
			for(int i = 0; i < N; ++i)
				for(int j = 0; j < N; ++j) {
					adj[i][j] = Math.min(adj[i][j], adj[i][k] + adj[k][j]);
				}
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