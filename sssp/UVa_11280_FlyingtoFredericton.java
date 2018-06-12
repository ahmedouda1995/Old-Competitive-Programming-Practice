package sssp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class UVa_11280_FlyingtoFredericton {

	static int INF = (int) 1e9;
	static ArrayList<Edge> edgeList = new ArrayList<>();
	static int[] dist1 = new int[100];
	static int[] dist2 = new int[100];
	static ArrayList<Pair> res = new ArrayList<Pair>();
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), cases = 1, n, m;
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		
		while(t-- > 0) {
			map.clear();
			res.clear();
			edgeList.clear();
			n = sc.nextInt();
			
			for(int i = 0; i < n; ++i) {
				dist1[i] = INF; dist2[i] = INF;
				map.put(sc.nextLine(), i);
			}
			m = sc.nextInt();
			
			for(int i = 0; i < m; ++i)
				edgeList.add(new Edge(map.get(sc.next()), map.get(sc.next()), sc.nextInt()));
			
			Collections.sort(edgeList);
//			System.out.println(edgeList);
			
			m = sc.nextInt();
			bellmanFord(n, n - 1);
			Collections.sort(res);
			out.printf("Scenario #%d\n", cases++);
			for(int i = 0; i < m; ++i) {
				int q = sc.nextInt();
				
				int min = INF;
				for(Pair p : res) {
					if(p.x > q) break;
					min = Math.min(min, p.y);
				}
				if(min != INF)
					out.printf("Total cost of flight(s) is $%d\n", min);
				else
					out.println("No satisfactory flights");
			}
			
			if(t > 0) out.println();
		}
		
		out.flush();
		out.close();
	}
	
	static void bellmanFord(int V, int dest) {
		
		dist1[0] = 0;
		
		for(int i = 0; i < V - 1; ++i) {
			for(Edge e : edgeList) {
				if(dist1[e.x] != INF && dist1[e.x] + e.z < dist1[e.y]) {
					if(e.y == dest) {
						res.add(new Pair(i, dist1[e.x] + e.z));
					}
					else {
						dist1[e.y] = dist1[e.x] + e.z;
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
	
	static class Edge implements Comparable<Edge>{
		int x;
		int y;
		int z;
		
		public Edge(int x, int y, int z) {
			this.x = x;
			this.y = y;
			this.z = z;
		}

		@Override
		public int compareTo(Edge e) {
			return Integer.compare(e.x, this.x);
		}
		
		@Override
		public String toString() {
			return "(" + x + ", " + y + ", " + z + ")";
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