package apsp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class UVa_925_Nomoreprerequisitesplease {

	static int N;
	static boolean adj[][];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		String [] arr;
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		
		while(t-- > 0) {
			map.clear();
			N = sc.nextInt();
			arr = new String[N];
			
			for(int i = 0; i < N; ++i) {
				arr[i] = sc.nextLine();
			}
			
			Arrays.sort(arr);
			
			for(int i = 0; i < N; ++i) {
				map.put(arr[i], i);
			}
			
			adj = new boolean[N][N];
			
			int k = sc.nextInt(), m;
			String a, b;
			
			while(k-- > 0) {
				a = sc.next();
				m = sc.nextInt();
				
				while(m-- > 0) {
					b = sc.next();
					adj[map.get(a)][map.get(b)] = true;
				}
			}
			
			floyd1();
			
			floyd2();
			
			ArrayList<String> res = new ArrayList<>();
			
			for(int i = 0; i < N; ++i) {
				res.clear();
				for(int j = 0; j < N; ++j) {
					if(adj[i][j]) res.add(arr[j]);
				}
				if(res.size() > 0) {
					out.print(arr[i] + " ");
					out.print(res.size());
					for(String ss : res) out.print(" " + ss);
					out.println();
				}
			}
		}
		
		out.flush();
		out.close();
	}

	private static void floyd1() {
		for(int k = 0; k < N; ++k)
			for(int i = 0; i < N; ++i)
				for(int j = 0; j < N; ++j) {
					adj[i][j] |= (adj[i][k] & adj[k][j]);
				}
	}

	private static void floyd2() {
		for(int k = 0; k < N; ++k)
			for(int i = 0; i < N; ++i)
				for(int j = 0; j < N; ++j) {
					if(adj[i][k] & adj[k][j]) {
						adj[i][j] = false;
					}
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