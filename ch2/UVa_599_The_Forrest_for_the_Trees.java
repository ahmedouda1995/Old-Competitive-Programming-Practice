package ch2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class UVa_599_The_Forrest_for_the_Trees {
	
	static LinkedList<Integer> adj[];
	static boolean vis[], v[];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		int t = sc.nextInt();
		while(t-- > 0){
			adj = new LinkedList[26];
			for (int i = 0; i < adj.length; i++) {
				adj[i] = new LinkedList<Integer>();
			}
			v = new boolean[26];
			vis = new boolean[26];
			String s;
			while((s = sc.nextLine()).charAt(0) != '*'){
				String e [] = s.substring(1, s.length() - 1).split(",");
				adj[e[0].charAt(0) - 'A'].add(e[1].charAt(0) - 'A');
				adj[e[1].charAt(0) - 'A'].add(e[0].charAt(0) - 'A');
			}
			String n[] = sc.nextLine().split(",");
			for (int i = 0; i < n.length; ++i) {
				v[n[i].charAt(0) - 'A'] = true;
			}
			int trees = 0, acorns = 0;
			for (int i = 0; i < 26; ++i) {
				if(v[i] && !vis[i]){
					if(dfs(i) > 1)
						trees++;
					else
						acorns++;
				}
			}
			out.println("There are " + trees + " tree(s) and " + acorns + " acorn(s).");
		}
		
		out.flush();
		out.close();
	}
	
	public static int dfs(int i){
		int count = 1;
		vis[i] = true;
		for (int v : adj[i]) {
			if(!vis[v])
				count += dfs(v);
		}
		return count;
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