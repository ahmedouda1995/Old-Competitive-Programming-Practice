package MST;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class UVa_10600_ACMcontestandBlackout {

	static ArrayList<Triple> edgeList = new ArrayList<Triple>();
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), n, m;
		boolean taken[];
		
		while(t-- > 0) {
			edgeList.clear();
			n = sc.nextInt(); m = sc.nextInt();
			
			for(int i = 0; i < m; ++i) {
				edgeList.add(new Triple(sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt()));
			}
			
			Collections.sort(edgeList);
			UFDS ds = new UFDS(n);
			taken = new boolean[edgeList.size()];
			
			int cost1 = 0;
			int k = 0;
			for(Triple e : edgeList) {
				if(!ds.isSameSet(e.a, e.b)) {
					ds.unionSet(e.a, e.b);
					cost1 += e.w;
					taken[k] = true;
				}
				k++;
			}
			int testCost, cost2 = (int) 1e9;
			for(int i = 0; i < edgeList.size(); ++i) {
				if(taken[i]) {
					ds = new UFDS(n);
					testCost = 0; k = 0;
					for(Triple e : edgeList) {
						if(k != i && !ds.isSameSet(e.a, e.b)) {
							ds.unionSet(e.a, e.b);
							testCost += e.w;
						}
						k++;
					}
					if(ds.numSets == 1) cost2 = Math.min(cost2, testCost);
				}
			}
			
			out.println(cost1 + " " + cost2);
		}
		
		out.flush();
		out.close();
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