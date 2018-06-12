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

public class UVa_388_GalacticImport {

	static ArrayList<Integer> adj[] = new ArrayList[27];
	static int N, INF = (int) 1e9;
	static int dist[] = new int[27];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		String s; int m;
		TreeMap<Character, Integer> map = new TreeMap<Character, Integer>();
		TreeMap<Integer, Character> map2 = new TreeMap<Integer, Character>();
		double val[] = new double[27];
		
		while((s = sc.nextLine()) != null) {
			map.clear(); map2.clear();
			N = Integer.parseInt(s);
			for(int i = 0; i <= N; ++i) {
				adj[i] = new ArrayList<Integer>();
				dist[i] = INF;
			}
			char c; double d;
			int k = 1;
			for(int i = 0; i < N; ++i) {
				c = sc.next().charAt(0);
				d = sc.nextDouble();
				s = sc.next();
				if(!map.containsKey(c)) {
					map2.put(k, c);
					map.put(c, k++);
				}
				val[map.get(c)] = d;
				
				for(int j = 0; j < s.length(); ++j) {
					if(s.charAt(j) == '*') {
						adj[0].add(map.get(c));
						adj[map.get(c)].add(0);
					}
					else {
						if(!map.containsKey(s.charAt(j))) {
							map2.put(k,s.charAt(j));
							map.put(s.charAt(j), k++);
						}
						adj[map.get(s.charAt(j))].add(map.get(c));
						adj[map.get(c)].add(map.get(s.charAt(j)));
					}
				}
			}
			
			bfs(0); double max = -1; char maxC = 'Z'; char res = 'Z';
			for(int i = 1; i <= N; ++i) {
				if(dist[i] != INF) {
					res = map2.get(i);
					double rel = calc(val[i], dist[i] - 1);
					if(rel == max) {
						if(res < maxC)
							maxC = res;
					}
					else if(rel > max) {
						max = rel;
						maxC = res;
					}
				}
			}
			out.printf("Import from %c\n", maxC);
			
		}
		
		out.flush();
		out.close();
	}
	
	private static double calc(double d, int i) {
		while(i-- > 0) {
			d -= 0.05 * d;
		}
		return d;
	}

	private static void bfs(int src) {
		Queue<Integer> q = new LinkedList<Integer>();
		
		dist[0] = 0; q.offer(0);
		
		while(!q.isEmpty()) {
			int u = q.poll();
			
			for(int v : adj[u]) {
				if(dist[v] == INF) {
					dist[v] = dist[u] + 1;
					q.offer(v);
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