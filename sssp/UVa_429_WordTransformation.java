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
import java.util.TreeMap;

public class UVa_429_WordTransformation {

	static ArrayList<Integer> adj[] = new ArrayList[200];
	static int INF = (int) 1e9;
	static int dist[];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		sc.nextLine();
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		ArrayList<String> dic = new ArrayList<String>();
		String s;
		
		while(t-- > 0) {
			map.clear();
			dic.clear();
			
			int k = 0;
			while(!(s = sc.nextLine()).equals("*")) {
				dic.add(s);
				map.put(s, k);
				adj[k] = new ArrayList<Integer>();
				k++;
			}
			
			
			for(int i = 0; i < dic.size(); ++i) {
				for(int j = i + 1; j < dic.size(); ++j) {
					if(diff1(dic.get(i), dic.get(j))) {
						adj[i].add(j); adj[j].add(i);
					}
				}
			}
			
			String s1, s2; String [] tmp;
			while((s = sc.nextLine()) != null && s.length() > 0) {
				tmp = s.split(" ");
				s1 = tmp[0]; s2 = tmp[1];
				bfs(map.get(s1), map.get(s2), k);
				out.println(s1 + " " + s2 + " " + dist[map.get(s2)]);
			}
			if(t > 0) out.println();
		}
		
		out.flush();
		out.close();
	}
	
	private static void bfs(int src, int dest, int n) {
		dist = new int[n];
		Arrays.fill(dist, INF);
		Queue<Integer> q = new LinkedList<Integer>();
		
		dist[src] = 0; q.offer(src);
		
		while(!q.isEmpty()) {
			int u = q.poll();
			if(u == dest) return;
			
			for(int v : adj[u]) {
				if(dist[v] == INF) {
					dist[v] = dist[u] + 1;
					q.offer(v);
				}
			}
		}
	}

	static boolean diff1(String s1, String s2) {
		if(s1.length() != s2.length()) return false;
		int c = 0;
		for(int i = 0; i < s1.length(); ++i) if(s1.charAt(i) != s2.charAt(i)) c++;
		if(c == 1)
			return true;
		else
			return false;
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