package sssp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class UVa_10150_Doublets {

	static final int INF = (int) 1e9;
	static ArrayList<Integer> adj[] = new ArrayList[25143];
	static int p[] = new int[25143];
	static PrintWriter out = new PrintWriter(System.out);
	static String [] arr = new String[25143];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		
		String s;
		int k = 0;
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		
		while((s = sc.nextLine()).length() > 0) {
			arr[k] = s; map.put(s, k++);
		}
		
		for(int i = 0; i < k; ++i) {
			adj[i] = new ArrayList<Integer>();
			p[i] = -1;
		}
		
		for(int i = 0; i < k; ++i) {
			for(int j = 0; j < arr[i].length(); ++j) {
				StringBuilder sb = new StringBuilder(arr[i]);
				for(int m = 1; m < 26; ++m) {
					char c = (char) (((arr[i].charAt(j) - 'a' + m) % 26) + 'a');
					sb.setCharAt(j, c);
					if(map.containsKey(sb.toString())) {
						adj[i].add(map.get(sb.toString()));
					}
				}
			}
		}
		
		int a, b;
		
		boolean first = true;
		
		while(sc.ready()) {
			
			if(first) first = false; else out.println();
			a = map.get(sc.next());
			b = map.get(sc.next());
			
			for(int i = 0; i < k; ++i) {
				p[i] = -1;
			}
			
			if(!bfs(a, b))
				out.println("No solution.");
		}
		
		out.flush();
		out.close();
	}

	private static boolean bfs(int src, int dest) {
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(src); p[src] = src;
		
		while(!q.isEmpty()) {
			int u = q.poll();
			
			if(u == dest) { print(src, dest); return true; }
			
			for(int v : adj[u]) {
				if(p[v] == -1) {
					p[v] = u;
					q.offer(v);
				}
			}
		}
		return false;
	}

	private static void print(int src, int dest) {
		if(src == dest) out.println(arr[src]);
		else {
			print(src, p[dest]);
			out.println(arr[dest]);
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