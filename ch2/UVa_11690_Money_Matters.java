package ch2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_11690_Money_Matters {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		int [] money;
		while(t-- > 0){
			int n = sc.nextInt(), m = sc.nextInt(); money = new int[n];
			UFDS ds = new UFDS(n, money);
			for (int i = 0; i < n; ++i) money[i] = sc.nextInt();
			for (int i = 0; i < m; ++i) ds.unionSet(sc.nextInt(), sc.nextInt());
			boolean printed = false;
			for (int i = 0; i < money.length; i++)
				if(money[i] != 0){
					out.println("IMPOSSIBLE");
					printed = true;
					break;
				}
			if(!printed)
				out.println("POSSIBLE");
		}
		out.flush();
		out.close();
	}
	
	static public class UFDS {

		int numSets, p[], rank[], setSize[], money[];
		
		public UFDS(int n, int [] money) {
			this.numSets = n;
			this.p = new int[n];
			this.rank = new int[n];
			this.setSize = new int[n];
			for(int i = 0;i < n;++i){ p[i] = i; rank[i] = 0; setSize[i] = 1; }
			this.money = money;
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
			if(rank[x] > rank[y]) {
				p[y] = x; setSize[x] += setSize[y];
				money[x] += money[y]; money[y] = 0;
			}
			else {
				p[x] = y; setSize[y] += setSize[x];
				money[y] += money[x]; money[x] = 0;
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