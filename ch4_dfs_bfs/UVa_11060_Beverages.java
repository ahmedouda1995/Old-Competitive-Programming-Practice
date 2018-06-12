package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class UVa_11060_Beverages {

	static LinkedList<Integer> adj[] = new LinkedList[100];
	static ArrayList<Integer> res = new ArrayList<Integer>();
	static int indegree[] = new int[100];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n, m, cases = 1;
		
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		TreeMap<Integer, String> map2 = new TreeMap<Integer, String>();
		String t;
		while((t = sc.nextLine()) != null) {
			map.clear();
			n = Integer.parseInt(t);
			for(int i = 0; i < n; ++i) { adj[i] = new LinkedList<Integer>(); }
			for(int i = 0; i < n; ++i) {
				String tmp = sc.nextLine();
				map.put(tmp, i); map2.put(i, tmp);
			}
			m = sc.nextInt();
			for(int i = 0; i < m; ++i) adj[map.get(sc.next())].add(map.get(sc.next()));
			
			res.clear();
			for(int i = 0; i < n; ++i) indegree[i] = 0;
			for(int i = 0; i < n; ++i) for(int v : adj[i]) indegree[v]++;
			kahn(n);
			
			out.printf("Case #%d: Dilbert should drink beverages in this order:", cases++);
			for(int i = 0; i < n; ++i) out.print(" " + map2.get(res.get(i)));
			out.println(".");
			out.println();
			
			sc.nextLine();
		}
		
		out.flush();
		out.close();
	}
	
	private static void kahn(int n) {
		PriorityQueue<Integer> q = new PriorityQueue<Integer>();
		for(int i = 0; i < n; ++i) if(indegree[i] == 0) q.add(i);
		
		while(!q.isEmpty()) {
			int i = q.poll();
			res.add(i);
			for(int v : adj[i]) {
				indegree[v]--;
				if(indegree[v] == 0) q.add(v);
			}
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