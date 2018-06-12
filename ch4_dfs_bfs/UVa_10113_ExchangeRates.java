package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class UVa_10113_ExchangeRates {

	static LinkedList<Triple> adj[] = new LinkedList[60];
	static TreeMap<String, Integer> map = new TreeMap<String, Integer>();
	static boolean vis[];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		for(int i = 0; i < 60; ++i) adj[i] = new LinkedList<Triple>();
		String s;
		StringTokenizer st;
		int k = 0;
		while(!(s = sc.nextLine()).contains(".")) {
			
			if(s.charAt(0) == '!') {
				
				s = s.substring(1);
				st = new StringTokenizer(s);
				int a = Integer.parseInt(st.nextToken());
				String sa = st.nextToken();
				st.nextToken();
				int b = Integer.parseInt(st.nextToken());
				String sb = st.nextToken();
				int g = gcd(a, b);
				a /= g; b /= g;
				if(!map.containsKey(sa)) {
					map.put(sa, k);
					k++;
				}
				adj[map.get(sa)].add(new Triple(sb, b, a));
				if(!map.containsKey(sb)) {
					map.put(sb, k);
					k++;
				}
				adj[map.get(sb)].add(new Triple(sa, a, b));
			}
			else {
				vis = new boolean[60];
				s = s.substring(1);
				st = new StringTokenizer(s);
				String sa = st.nextToken();
				st.nextToken();
				String sb = st.nextToken();
				if(!sa.equals(sb)) {
					int [] res = dfs(map.get(sa).intValue(), map.get(sb).intValue());
					
					if(res == null)
						out.printf("? %s = ? %s\n", sa, sb);
					else {
						int tmp = gcd(res[0], res[1]);
						res[0] /= tmp;
						res[1] /= tmp;
						out.printf("%d %s = %d %s\n", res[1], sa, res[0], sb);
					}
				}
				else {
					out.printf("1 %s = 1 %s\n", sa, sb);
				}
			}
		}
		
		out.flush();
		out.close();
	}
	
	private static int[] dfs(int i, int j) {
		vis[i] = true;
		for(Triple t : adj[i]) {
			if(map.get(t.v) == j) return new int[] {t.x, t.y};
			if(!vis[map.get(t.v)]) {
				int [] tmp = dfs(map.get(t.v).intValue(), j);
				if(tmp != null) {
					return new int[] {t.x * tmp[0], t.y * tmp[1]};
				}
			}
		}
		return null;
	}

	static class Triple {
		String v;
		int x;
		int y;
		
		public Triple(String v, int x, int y) {
			this.v = v;
			this.x = x;
			this.y = y;
		}
		
		@Override
		public String toString() {
			return v + " " + x + " " + y;
		}
	}
	
	public static int gcd(int n, int m) {
		return (n % m) == 0? m : gcd(m, n % m);
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