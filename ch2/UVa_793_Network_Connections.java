package ch2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_793_Network_Connections {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		
		while(t-- > 0){
			UFDS ds = new UFDS(sc.nextInt());
			String s; int correct = 0, wrong = 0;
			while((s = sc.nextLine()) != null && s.length() > 0){
				StringTokenizer st = new StringTokenizer(s);
				char c = st.nextToken().charAt(0);
				if(c == 'c')
					ds.unionSet(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
				else {
					if(ds.isSameSet(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1))
						correct++;
					else
						wrong++;
				}
			}
			if(t == 0)
				out.printf("%d,%d\n", correct, wrong);
			else
				out.printf("%d,%d\n\n", correct, wrong);
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