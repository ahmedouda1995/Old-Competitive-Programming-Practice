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

public class UVa_10048_Audiophobia {

	static LinkedList<Triple> edgeList = new LinkedList<Triple>();
	static LinkedList<Pair> adj[] = new LinkedList[100];
	static boolean vis[];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int c, s, q, cases = 1;
		
		boolean first = true;
		
		while((c = sc.nextInt()) != 0 | (s = sc.nextInt()) != 0 | (q = sc.nextInt()) != 0) {
			edgeList.clear();
			if(first) first = false;
			else out.println();
			
			for(int i = 0; i < s; ++i)
				edgeList.add(new Triple(sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt()));
			
			Collections.sort(edgeList);
			for(int i = 0; i < c; ++i) adj[i] = new LinkedList<Pair>();
			UFDS ds = new UFDS(c);
			
			for(Triple e : edgeList) {
				if(!ds.isSameSet(e.a, e.b)) {
					ds.unionSet(e.a, e.b);
					adj[e.a].add(new Pair(e.b, e.w));
					adj[e.b].add(new Pair(e.a, e.w));
				}
			}
			
			out.printf("Case #%d\n", cases++);
			
			int a, b;
			
			for(int i = 0; i < q; ++i) {
				a = sc.nextInt() - 1; b = sc.nextInt() - 1;
				vis = new boolean[c];
				int res = minimax(a, b);
				out.println((res == -1)?"no path":res);
			}
		}
		
		out.flush();
		out.close();
	}
	
	private static int minimax(int a, int b) {
		vis[a] = true;
		
		int res = -1;
		
		if(a == b) return 0;
		
		for(Pair p : adj[a]) {
			if(!vis[p.x]) {
				int tmp = minimax(p.x, b);
				if(tmp != -1) {
					res = Math.max(p.y, tmp);
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
			return Integer.compare(this.w, t.w);
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