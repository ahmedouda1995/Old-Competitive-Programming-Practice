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

public class UVa_10462_IsThereASecondWayLeft {

	static LinkedList<Triple> edgeList = new LinkedList<Triple>();
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), v, e, cases = 1;
		
		while(t-- > 0) {
			v = sc.nextInt(); e = sc.nextInt();
			edgeList.clear();
			for(int i = 0; i < e; ++i)
				edgeList.add(new Triple(sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt()));
			
			Collections.sort(edgeList);
			UFDS ds = new UFDS(v);
			
			int edges = 0;
			out.printf("Case #%d : ", cases++);
			boolean taken[] = new boolean[edgeList.size()];
			int k = 0;
			
			for(Triple edge : edgeList) {
				if(!ds.isSameSet(edge.a, edge.b)) {
					ds.unionSet(edge.a, edge.b);
					edges++;
					taken[k] = true;
				}
				k++;
			}
			if(ds.numSets != 1) out.println("No way");
			else if(edges == e) out.println("No second way");
			else {
				k = 0; int test, testCost = 0, min = (int) 1e9;
				for(Triple edge : edgeList) {
					if(taken[k]) {
						test = k; testCost = 0;
						ds = new UFDS(v);
						int i = 0;
						for(Triple edge1 : edgeList) {
							if(i != test && !ds.isSameSet(edge1.a, edge1.b)) {
								ds.unionSet(edge1.a, edge1.b);
								testCost += edge1.w;
							}
							i++;
						}
						if(ds.numSets == 1) min = Math.min(min, testCost);
					}
					k++;
				}
				
				if(min == ((int) 1e9)) out.println("No second way");
				else out.println(min);
			}
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