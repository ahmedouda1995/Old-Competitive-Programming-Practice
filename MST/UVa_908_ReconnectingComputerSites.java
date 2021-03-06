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

public class UVa_908_ReconnectingComputerSites {

	static LinkedList<Triple> edgeList = new LinkedList<Triple>();
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		String s; int n, k;
		boolean first = true;
		
		while((s = sc.nextLine()) != null) {
			edgeList.clear();
			if (first) first = false;
			else out.println();
			
			n = Integer.parseInt(s);
			int costOriginal = 0;
			String [] tmp;
			for(int i = 0; i < n - 1; ++i) {
				tmp = sc.nextLine().split(" ");
				costOriginal += Integer.parseInt(tmp[2]);
			}
			
			k = sc.nextInt();
			
			for(int i = 0; i < k; ++i)
				edgeList.add(new Triple(sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt()));
			
			k = sc.nextInt();
			
			for(int i = 0; i < k; ++i)
				edgeList.add(new Triple(sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt()));
			
			Collections.sort(edgeList);
			
			int cost = 0;
			UFDS ds = new UFDS(n);
			for(Triple t : edgeList) {
				if(!ds.isSameSet(t.a, t.b)) {
					ds.unionSet(t.a, t.b);
					cost += t.w;
				}
			}
			
			out.println(costOriginal);
			out.println(cost);
			
			sc.nextLine();
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