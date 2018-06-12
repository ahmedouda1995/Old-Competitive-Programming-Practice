package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class UVa_459_Graph_Connectivity_UFDS {

	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		sc.nextLine();
		while(t-- > 0) {
			int n = sc.nextLine().charAt(0) - 'A' + 1;
			UFDS ds = new UFDS(n);
			String s;
			
			while((s = sc.nextLine()) != null && s.length() > 0)
				ds.union(s.charAt(0) - 'A', s.charAt(1) - 'A');

			out.println(ds.numSets);
			if(t > 0)
				out.println();
		}
		
		out.flush();
		out.close();
	}
	
	static class UFDS {
		int numSets, p[], rank[], setSize[];
		
		public UFDS(int n) {
			numSets = n;
			p = new int[n];
			rank = new int[n];
			setSize = new int[n];
			for(int i = 0; i < n; ++i) { p[i] = i; setSize[i] = 1; }
		}
		
		public int findSet(int i) {
			if(i != p[i])
				return p[i] = findSet(p[i]);
			return i;
		}
		private boolean isSameSet(int i, int j) {
			return findSet(i) == findSet(j);
		}
		public void union(int i, int j) {
			if(!isSameSet(i, j)) {
				numSets--;
				int x = findSet(i), y = findSet(j);
				if(rank[x] > rank[y]) {
					p[y] = x; setSize[x] += setSize[y];
				}
				else {
					p[x] = y;
					setSize[y] += setSize[x];
					if(rank[x] == rank[y]) rank[y]++;
				}
			}
		}
		public int numDisjointSets() { return numSets; }
		public int sizeOfSet(int i) { return setSize[findSet(i)]; }
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