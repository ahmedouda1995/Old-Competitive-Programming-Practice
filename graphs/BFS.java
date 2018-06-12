package graphs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BFS {
	static LinkedList<Integer> adj[];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int n = sc.nextInt();
		adj = new LinkedList[n];
		for (int i = 0; i < n; i++) adj[i] = new LinkedList<Integer>();
		int e = sc.nextInt();
		for (int i = 0; i < e; i++) adj[sc.nextInt()].add(sc.nextInt());
		
		bfs(5);
		out.println("> " + bfs(5, 5)); // on which level
		
		out.flush();
		out.close();
	}
	
	private static void bfs(int i) {
		Queue<Integer> q = new LinkedList<Integer>();
		boolean vis[] = new boolean[adj.length];
		
		q.offer(i);
		vis[i] = true;
		
		while(!q.isEmpty()){
			int curr = q.poll();
			System.out.println(curr);
			
			for (int j : adj[curr]) {
				if(!vis[j]){
					q.offer(j);
					vis[j] = true;
				}
			}
		}
	}
	
	private static int bfs(int i, int dest) {
		Queue<Pair> q = new LinkedList<Pair>();
		boolean vis[] = new boolean[adj.length];
		q.offer(new Pair(i, 0));
		vis[i] = true;
		
		while(!q.isEmpty()){
			Pair curr = q.poll();
			if(curr.x == dest)
				return curr.y;
			for (int j : adj[curr.x]) {
				if(!vis[j]){
					q.offer(new Pair(j, curr.y + 1));
					vis[j] = true;
				}
			}
		}
		return -1;
	}
	
	static class Pair{
		int x, y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
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
