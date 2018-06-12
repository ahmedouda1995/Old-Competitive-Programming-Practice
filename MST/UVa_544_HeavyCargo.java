package MST;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class UVa_544_HeavyCargo {

	static LinkedList<Pair> adj[] = new LinkedList[200];
	static LinkedList<Triple> edgeList = new LinkedList<Triple>();
	static boolean vis[] = new boolean[200];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n, m, cases = 1;
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		while((n = sc.nextInt()) != 0 | (m = sc.nextInt()) != 0) {
			map.clear(); edgeList.clear();
			
			for(int i = 0; i < n; ++i) {
				adj[i] = new LinkedList<Pair>();
				vis[i] = false;
			}
			
			int k = 0; String a, b;
			for(int i = 0; i < m; ++i) {
				a = sc.next(); b = sc.next();
				if(!map.containsKey(a))
					map.put(a, k++);
				if(!map.containsKey(b))
					map.put(b, k++);
				edgeList.add(new Triple(map.get(a), map.get(b), sc.nextInt()));
			}
			
			int start = map.get(sc.next()), dest = map.get(sc.next());
			
			Collections.sort(edgeList);
			UFDS ds = new UFDS(n);
			
			for(Triple e : edgeList) {
				if(!ds.isSameSet(e.a, e.b)) {
					ds.unionSet(e.a, e.b);
					adj[e.a].add(new Pair(e.b, e.w));
					adj[e.b].add(new Pair(e.a, e.w));
				}
			}
			
			
			out.printf("Scenario #%d\n", cases++);
			out.printf("%d tons\n", dfs(start, dest));
			out.println();
		}
		
		out.flush();
		out.close();
	}
	
	private static int dfs(int i, int j) {
		vis[i] = true;
		
		int min = -1;
		
		if(i == j) return 1000_000;
		
		for(Pair v : adj[i]) {
			if(!vis[v.x]) {
				int tmp = dfs(v.x, j);
				if(tmp != -1)
					min = Math.min(v.y, tmp);
			}
		}
		return min;
	}

	static class UFDS {
		int numSets;
		int setSize[], p[], rank[];
		
		public UFDS(int n) {
			numSets = n;
			setSize = new int[n];
			p = new int[n];
			rank = new int[n];
			for(int i = 0; i < n; ++i) { setSize[i] = 1; p[i] = i; }
		}
		
		public int findSet(int i) { return (i == p[i])?i:(p[i] = findSet(p[i])); }
		public boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }
		
		public int numDisjointSets() { return numSets; }
		public int sizeOfSet(int i) { return setSize[findSet(i)]; }
		
		public void unionSet(int i, int j) {
			if(isSameSet(i, j)) return;
			
			int x = findSet(i), y = findSet(j);
			numSets--;
			if(rank[x] > rank[y]) { p[y] = x; setSize[x] += setSize[y]; }
			else {
				p[x] = y; setSize[y] += setSize[x];
				if(rank[x] == rank[y]) rank[y]++;
			}
		}
	}
	
	static class Triple implements Comparable<Triple> {
		int a;
		int b;
		int w;
		
		public Triple(int a, int b, int w) {
			this.a = a;
			this.b = b;
			this.w = w;
		}

		@Override
		public int compareTo(Triple t) {
			return Integer.compare(t.w, this.w);
		}
		
		@Override
		public String toString() {
			return "(" + a + ", " + b + ", " + w + ")";
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
		
		@Override
		public String toString() {
			return "(" + x + ", " + y + ")";
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