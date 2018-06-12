package sssp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class UVa_924_SpreadingtheNews {

	static ArrayList<Integer> adj[];
	static int dist[];
	static int N, INF = (int) 1e9, max, maxDay;
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		N = sc.nextInt();
		adj = new ArrayList[N];
		for(int i = 0; i < N; ++i) adj[i] = new ArrayList<Integer>();
		
		int m;
		
		for(int i = 0; i < N; ++i) {
			m = sc.nextInt();
			for(int j = 0; j < m; ++j) adj[i].add(sc.nextInt());
		}
		
		m = sc.nextInt();
		
		for(int i = 0; i < m; ++i) {
			max = 0; maxDay = 0;
			bfs(sc.nextInt());
			if(max == 0)
				out.println(0);
			else
				out.println(max + " " + maxDay);
		}
		
		out.flush();
		out.close();
	}
	
	private static void bfs(int src) {
		dist = new int[N];
		Arrays.fill(dist, INF);
		Queue<Integer> q = new LinkedList<Integer>();
		dist[src] = 0; q.add(src);
		
		int currDay = 1, currCount = 0;
		
		while(!q.isEmpty()) {
			int u = q.poll();
			
			for(int v : adj[u]) {
				if(dist[v] == INF) {
					dist[v] = dist[u] + 1;
					q.offer(v);
					if(dist[v] == currDay) {
						currCount++;
						if(currCount > max) {
							max = currCount;
							maxDay = dist[v];
						}
					}
					else {
						currDay++;
						currCount = 1;
						if(currCount > max) {
							max = currCount;
							maxDay = dist[v];
						}
					}
				}
			}
		}
	}

	static class Pair implements Comparable<Pair>{
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Pair p) {
			if(Integer.compare(this.x, p.x) == 0)
				return Integer.compare(this.y, p.y);
			return Integer.compare(this.x, p.x);
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