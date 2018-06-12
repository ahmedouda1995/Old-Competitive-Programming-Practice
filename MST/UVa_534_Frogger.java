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

public class UVa_534_Frogger {

	static LinkedList<Triple> edgeList = new LinkedList<Triple>();
	static boolean vis[];
	static LinkedList<Pair> adj[];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n, cases = 1;
		
		while((n = sc.nextInt()) != 0) {
			edgeList.clear();
			int posX[] = new int[n];
			int posY[] = new int[n];
			for(int i = 0; i < n; ++i) {
				posX[i] = sc.nextInt();
				posY[i] = sc.nextInt();
			}
			
			for(int i = 0; i < n; ++i) {
				for(int j = i + 1; j < n; ++j) {
					edgeList.add(new Triple(i, j, dist(posX[i], posY[i], posX[j], posY[j])));
				}
			}
			
			Collections.sort(edgeList);
			UFDS ds = new UFDS(n);
			
			adj = new LinkedList[n];
			for(int i = 0; i < n; ++i) adj[i] = new LinkedList<Pair>();
			
			for(Triple e : edgeList) {
				if(!ds.isSameSet(e.a, e.b)) {
					ds.unionSet(e.a, e.b);
					adj[e.a].add(new Pair(e.b, e.w));
					adj[e.b].add(new Pair(e.a, e.w));
				}
			}
			
			vis = new boolean[n];
			
			out.printf("Scenario #%d\n", cases++);
			out.printf("Frog Distance = %.3f\n", dfs(0));
			out.println();
		}
		
		out.flush();
		out.close();
	}
	
	private static double dfs(int i) {
		vis[i] = true;
		
		double max = -1;
		
		if(i == 1) return 0;
		
		for(Pair v : adj[i]) {
			if(!vis[v.b]) {
				double tmp = dfs(v.b);
				if(tmp != -1) {
					max = Math.max(v.w, tmp);
				}
			}
		}
		return max;
	}

	static class Pair {
		int b;
		double w;
		
		public Pair(int b, double w) {
			this.b = b;
			this.w = w;
		}
		
		@Override
		public String toString() {
			return "(" + b + ", " + w + ")";
		}
	}
	
	public static double dist(int x1, int y1, int x2, int y2) {
		return Math.sqrt((x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1));
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
		double w;
		
		public Triple(int a, int b, double w) {
			this.a = a;
			this.b = b;
			this.w = w;
		}

		@Override
		public int compareTo(Triple t) {
			return Double.compare(this.w, t.w);
		}
		
		@Override
		public String toString() {
			return "(" + a + ", " + b + ", " + w + ")";
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