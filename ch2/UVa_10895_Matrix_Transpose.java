package ch2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class UVa_10895_Matrix_Transpose {
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		String s;
		while((s = sc.nextLine()) != null && s.length() != 0){
			StringTokenizer st = new StringTokenizer(s);
			int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
			int [] ind = new int[1000], a = new int[1000];
			LinkedList<Pair> adj[] = new LinkedList[m];
			
			for (int i = 0; i < n; i++) {
				int q = sc.nextInt();
				for (int j = 0; j < q; j++) ind[j] = sc.nextInt();
				for (int j = 0; j < q; j++) a[j] = sc.nextInt();
				for (int j = 0; j < q; j++) {
					if(adj[ind[j] - 1] == null)
						adj[ind[j] - 1] = new LinkedList<Pair>();
					adj[ind[j] - 1].add(new Pair(i + 1, a[j]));
				}
			}
			
			out.println(m + " " + n);
			for (int i = 0; i < adj.length; i++) {
				if(adj[i] == null){
					out.println(0);
					out.println();
				}
				else {
					out.print(adj[i].size());
					for (int j = 0; j < adj[i].size(); j++) out.print(" " + adj[i].get(j).x);
					out.println();
					for (int j = 0; j < adj[i].size() - 1; j++) out.print(adj[i].get(j).y + " ");
					out.println(adj[i].get(adj[i].size() - 1).y);
				}
			}
		}
		
		out.flush();
		out.close();
	}
	
	static class Pair implements Comparable<Pair>{
		int x, y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Pair p) {
			if(this.x == p.x)
				return this.y - p.y;
			else
				return this.x - p.x;
		}
		public String toString(){
			return x + " " + y;
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