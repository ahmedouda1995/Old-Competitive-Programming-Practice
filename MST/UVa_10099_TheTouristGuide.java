package MST;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class UVa_10099_TheTouristGuide {

	static LinkedList<Triple> edgeList = new LinkedList<Triple>();
	static LinkedList<Pair> adj[] = new LinkedList[100];
	static boolean vis[] = new boolean[100];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n, m, cases = 1;
		
		while((n = sc.nextInt()) != 0 | (m = sc.nextInt()) != 0) {
			edgeList.clear();
			for(int i = 0; i < n; ++i) {
				adj[i] = new LinkedList<Pair>();
				vis[i] = false;
			}
			
			for(int i = 0; i < m; ++i)
				edgeList.add(new Triple(sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt()));
			
			Collections.sort(edgeList);
			
			UFDS ds = new UFDS(n);
			
			for(Triple e : edgeList) {
				if(!ds.isSameSet(e.a, e.b)) {
					ds.unionSet(e.a, e.b);
					adj[e.a].add(new Pair(e.b, e.w));
					adj[e.b].add(new Pair(e.a, e.w));
				}
			}
			
			int s = sc.nextInt() - 1, d = sc.nextInt() - 1, t = sc.nextInt();
			
			out.printf("Scenario #%d\n", cases++);
			out.printf("Minimum Number of Trips = %d\n", (int)Math.ceil(((t * 1.0) / (maximin(s, d) - 1))));
			out.println();
		}
		
		out.flush();
		out.close();
	}
	
	private static int maximin(int i, int j) {
		vis[i] = true;
		
		int res = -1;
		if(i == j) return 1000_000;
		
		for(Pair p : adj[i]) {
			if(!vis[p.x]) {
				int tmp = maximin(p.x, j);
				if(tmp != -1) {
					res = Math.min(tmp, p.y);
					break;
				}
			}
		}
		return res;
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