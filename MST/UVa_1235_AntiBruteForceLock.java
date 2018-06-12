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

public class UVa_1235_AntiBruteForceLock {

	static LinkedList<Triple> edgeList = new LinkedList<Triple>();	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), n;
		
		String s[] = new String[500];
		while(t-- > 0) {
			edgeList.clear();
			
			n = sc.nextInt();
			for(int i = 0; i < n; ++i) s[i] = sc.next();
			
			int res = 100;
			
			for(int i = 0; i < n; ++i) {
				res = Math.min(res, diff("0000", s[i]));
				
			}
			
			for(int i = 0; i < n; ++i) {
				for(int j = i + 1; j < n; ++j) {
					edgeList.add(new Triple(i, j, diff(s[i], s[j])));
				}
			}
			Collections.sort(edgeList);
			//System.out.println(edgeList);
			
			UFDS ds = new UFDS(n);
			
			for(Triple edge : edgeList) {
				if(!ds.isSameSet(edge.a, edge.b)) {
					ds.unionSet(edge.a, edge.b);
					res += edge.w;
				}
			}
			
			out.println(res);
		}
		
		out.flush();
		out.close();
	}
	
	public static int diff(String s1, String s2) {
		int res = 0;
		int a, b;
		for(int i = 0; i < 4; ++i) {
			a = Integer.parseInt(s1.charAt(i) + "");
			b = Integer.parseInt(s2.charAt(i) + "");
			res += Math.min(Math.abs(a - b), Math.abs(9 - Math.abs(a - b) + 1));
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