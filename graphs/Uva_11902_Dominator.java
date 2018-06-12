package graphs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Uva_11902_Dominator {

	static LinkedList<Integer> adj[];
	static boolean vis[];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int t = sc.nextInt();
		int cases = 1;
		while(t-- > 0){
			int n = sc.nextInt();
			vis = new boolean[n];
			adj = new LinkedList[n];
			for (int i = 0; i < n; i++) adj[i] = new LinkedList<Integer>();
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if(sc.nextInt() == 1)
						adj[i].add(j);
				}
			}
			boolean arrRoot [] = new boolean[n];
			dfsUtil(0, arrRoot, -1);
			boolean vertex [] = new boolean[n];
			out.println("Case " + cases + ":");
			printSeperator(n, out);
			for (int i = 0; i < n; i++) {
				Arrays.fill(vertex, false);
				Arrays.fill(vis, false);
				dfsUtil(0, vertex, i);
				for (int j = 0; j < vertex.length; j++) {
					out.print("|");
					if(vertex[j] != arrRoot[j])
						out.print("Y");
					else
						out.print("N");
				}
				out.println("|");
				printSeperator(n, out);
			}
			cases++;
		}
		out.flush();
		out.close();
	}
	
	private static void printSeperator(int n, PrintWriter out) {
		out.print("+");
		for (int i = 0; i < (2 * n - 1); i++) out.print("-");
		out.println("+");
	}

	public static void dfsUtil(int i, boolean arr[], int turnOff){
		if(i != turnOff){
			vis[i] = true;
			arr[i] = true;
			for (int j : adj[i]) {
				if(!vis[j])
					dfsUtil(j, arr, turnOff);
			}
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
