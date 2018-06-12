package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class UVa_168_TheseusandtheMinotaur {

	static LinkedList<Integer> adj[] = new LinkedList[26];
	static boolean lit[] = new boolean[26];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		String s;
		
		while(!(s = sc.nextLine()).equals("#")) {
			Arrays.fill(lit, false);
			for(int i = 0; i < 26; ++i) adj[i] = new LinkedList<Integer>();
			String all[] = s.split(" ");
			String [] edges = all[0].split(";");
			edges[edges.length - 1] = edges[edges.length - 1].substring(0, edges[edges.length - 1].length() - 1);
			
			for(int i = 0; i < edges.length; ++i) {
				String tmp[] = edges[i].split(":");
				if(tmp.length > 1) {
					for(int j = 0; j < tmp[1].length(); ++j) 
						adj[tmp[0].charAt(0) - 'A'].add(tmp[1].charAt(j) - 'A');
				}
			}
			int posM = all[1].charAt(0) - 'A';
			int posJ = all[2].charAt(0) - 'A';
			int k = Integer.parseInt(all[3]);
			
			boolean trapped = false;
			int c = 1;
			int cavern = posM;
			int prev = posJ;
			StringBuilder sb = new StringBuilder();
			label : while(!trapped) {
				if(c == k) {
					lit[cavern] = true;
					sb.append((char) (cavern + 'A') + " ");
					c = 0;
				}
				c++;
				for(int i = 0; i < adj[cavern].size(); ++i) {
					if(!lit[adj[cavern].get(i)] && adj[cavern].get(i) != prev) {
						prev = cavern;
						cavern = adj[cavern].get(i);
						continue label;
					}
				}
				trapped = true;
			}
			
			if(sb.length() >= 2 && sb.charAt(sb.length() - 2) == ((char) (cavern + 'A'))) {
				sb.deleteCharAt(sb.length() - 1); sb.deleteCharAt(sb.length() - 1);
			}
			
			sb.append("/" + (char)(cavern + 'A'));
				
			out.println(sb.toString());	
		}
		
		out.flush();
		out.close();
	}
	
	static class Pair {
		char c;
		int n;
		
		public Pair(char c, int n) {
			this.c = c;
			this.n = n;
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