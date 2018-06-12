package ch2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class UVa_10685_Nature {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		int c = 0, r = 0;
		while((c = sc.nextInt()) != 0 | (r = sc.nextInt()) != 0){
			UFDS ds = new UFDS(c);
			TreeMap<String, Integer> map = new TreeMap<String, Integer>();
			int max = 1;
			for (int i = 0; i < c; ++i) map.put(sc.next(), i);
			for (int i = 0; i < r; ++i) {
				String s1 = sc.next(), s2 = sc.next();
				ds.unionSet(map.get(s1), map.get(s2));
				max = Math.max(max, ds.sizeOfSet(map.get(s1)));
			}
			out.println(max);
		}
		
		out.flush();
		out.close();
	}
	
	static public class UFDS {

		private int numSets, p[], rank[], setSize[];
		
		public UFDS(int n) {
			this.numSets = n;
			this.p = new int[n];
			this.rank = new int[n];
			this.setSize = new int[n];
			for(int i = 0;i < n;++i){ p[i] = i; rank[i] = 0; setSize[i] = 1; }
		}
		public int findSet(int i){
			if(p[i] == i) return i;
			p[i] = findSet(p[i]);
			return p[i];
		}
		public boolean isSameSet(int i, int j) {return findSet(i) == findSet(j);}
		public void unionSet(int i, int j){
			if(isSameSet(i, j))
				return;
			numSets--;
			int x = findSet(i), y = findSet(j);
			if(rank[x] > rank[y]) {p[y] = x; setSize[x] += setSize[y];}
			else {
				p[x] = y; setSize[y] += setSize[x];
				if(rank[x] == rank[y]) rank[y]++;
			}
		}
		public int numDisjointSets() { return numSets; }
		public int sizeOfSet(int i) { return setSize[findSet(i)]; }
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