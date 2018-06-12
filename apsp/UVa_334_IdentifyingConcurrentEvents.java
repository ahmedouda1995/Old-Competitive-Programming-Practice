package apsp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeMap;

// TRANSITIVE CLOSURE

public class UVa_334_IdentifyingConcurrentEvents {

	static int NC, INF = (int) 1e9;
	static boolean[][] adj;
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		TreeMap<Integer, String> map2 = new TreeMap<Integer, String>();
		int cases = 1;
		
		while((NC = sc.nextInt()) != 0) {
			map.clear(); map2.clear();
			
			adj = new boolean[200][200];
			
			int k = 0, ne, prev; String s;
			
			for(int i = 0; i < NC; ++i) {
				ne = sc.nextInt();
				
				if(ne > 0) {
					s = sc.next();
					
					if(!map.containsKey(s)) {
						map.put(s, k);
						map2.put(k++, s);
					}
					
					prev = map.get(s);
					ne--;
					
					while(ne-- > 0) {
						s = sc.next();
						
						if(!map.containsKey(s)) {
							map.put(s, k);
							map2.put(k++, s);
						}
						
						adj[prev][map.get(s)] = true;
						prev = map.get(s);
					}
				}
			}
			
			for(int i = 0; i < k; ++i) adj[i][i] = true;
			
			ne = sc.nextInt();
			
			String a, b;
			while(ne-- > 0) {
				a = sc.next(); b = sc.next();
				adj[map.get(a)][map.get(b)] = true;
			}

			
			floyd(k);
			
			ArrayList<String> res = new ArrayList<String>();
			
			int count = 0;
			
			for(int i = 0; i < k; ++i)
				for(int j = i + 1; j < k; ++j) {
					if(!adj[i][j] && !adj[j][i]) {
						if(count < 2) {
							res.add(map2.get(i));
							res.add(map2.get(j));
						}
						count++;
					}
				}
			
			if(count > 0) {
				out.printf("Case %d, %d concurrent events:\n", cases++, count);
				for(int i = 0; i < res.size(); i += 2) {
					out.printf("(%s,%s) ", res.get(i), res.get(i + 1));
				}
				out.println();
			}
			else
				out.printf("Case %d, no concurrent events.\n", cases++);
		}
		
		out.flush();
		out.close();
	}

	private static void floyd(int n) {
		for(int k = 0; k < n; ++k)
			for(int i = 0; i < n; ++i)
				for(int j = 0; j < n; ++j)
					adj[i][j] |= (adj[i][k] & adj[k][j]);
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