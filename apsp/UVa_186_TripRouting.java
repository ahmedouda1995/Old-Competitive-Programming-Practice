package apsp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class UVa_186_TripRouting {

	static final int INF = (int) 1e9;
	static int adj[][] = new int[100][100];
	static int p[][] = new int[100][100];
	static PrintWriter out = new PrintWriter(System.out);
	static TreeMap<Integer, String> map2 = new TreeMap<Integer, String>();
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		TreeMap<Pair, String> routeName = new TreeMap<Pair, String>();
		
		String s;
		String a[];
		int k = 0;
		
		for(int i = 0; i < 100; ++i) Arrays.fill(adj[i], INF);
		for(int i = 0; i < 100; ++i) Arrays.fill(p[i], i);
		
		while((s = sc.nextLine()).length() > 0) {
			a = s.split(",");
			if(!map.containsKey(a[0])) {
				map2.put(k, a[0]);
				map.put(a[0], k++);
			}
			if(!map.containsKey(a[1])) {
				map2.put(k, a[1]);
				map.put(a[1], k++);
			}
			
			if(adj[map.get(a[0])][map.get(a[1])] > Integer.parseInt(a[3])) {
				routeName.put(new Pair(map.get(a[0]), map.get(a[1])), a[2]);
				routeName.put(new Pair(map.get(a[1]), map.get(a[0])), a[2]);
				adj[map.get(a[0])][map.get(a[1])] = Integer.parseInt(a[3]);
				adj[map.get(a[1])][map.get(a[0])] = Integer.parseInt(a[3]);
			}
		}

		
		floyd(k);
		
		while((s = sc.nextLine()) != null) {
			out.println(); out.println();
			
			out.printf("%-20s %-20s %-10s Miles\n", "From", "To", "Route");
			out.println("-------------------- -------------------- ---------- -----");
			
			int st = -1, end = -1;
			a = s.split(",");
			st = map.get(a[0]);
			end = map.get(a[1]);
			int terminate = end, begin = p[st][end];
			solve(st, begin, terminate, map, routeName);
			out.printf("%-53s-----\n", "");
			out.printf("%-42s%-10s %5d\n", "", "Total", adj[st][end]);
		}
		
		out.flush();
		out.close();
	}

	private static void solve(int st, int begin, int terminate, TreeMap<String, Integer> map, TreeMap<Pair, String> routeName) {
		if(begin == st) {
			out.printf("%-20s ", map2.get(begin)); out.printf("%-20s ", map2.get(terminate));
			out.printf("%-10s ", routeName.get(new Pair(begin, terminate)));
			out.printf("%5d\n", adj[begin][terminate]);
		}
		else {
			solve(st, p[st][begin], begin, map, routeName);
			out.printf("%-20s ", map2.get(begin)); out.printf("%-20s ", map2.get(terminate));
			out.printf("%-10s ", routeName.get(new Pair(begin, terminate)));
			out.printf("%5d\n", adj[begin][terminate]);
		}
	}

	private static void floyd(int n) {
		for(int k = 0; k < n; ++k)
			for(int i = 0; i < n; ++i)
				for(int j = 0; j < n; ++j) {
					if(adj[i][j] > adj[i][k] + adj[k][j]) {
						adj[i][j] = adj[i][k] + adj[k][j];
						p[i][j] = p[k][j];
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