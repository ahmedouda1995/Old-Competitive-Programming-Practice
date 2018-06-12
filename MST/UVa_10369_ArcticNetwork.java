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

public class UVa_10369_ArcticNetwork {

	static LinkedList<Triple> edgeList = new LinkedList<Triple>();
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), n, m;
		int [] posX = new int[500], posY = new int[500];
		
		while(t-- > 0) {
			edgeList.clear();
			n = sc.nextInt(); m = sc.nextInt();
			
			for(int i = 0; i < m; ++i) { posX[i] = sc.nextInt(); posY[i] = sc.nextInt(); }
			
			for(int i = 0; i < m; ++i) {
				for(int j = i + 1; j < m; ++j) {
					edgeList.add(new Triple(i, j, dist(posX[i], posY[i], posX[j], posY[j])));
				}
			}
			Collections.sort(edgeList);
			UFDS ds = new UFDS(m);
			
			double max = 0;
			
			for(Triple e : edgeList) {
				if(ds.numSets == n) break;
				if(!ds.isSameSet(e.a, e.b)) {
					ds.unionSet(e.a, e.b);
					max = Math.max(max, e.w);
				}
			}
			
			out.printf("%.2f\n", max);
		}
		
		out.flush();
		out.close();
	}
	
	public static double dist(double x1, double y1, double x2, double y2) {
		return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
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