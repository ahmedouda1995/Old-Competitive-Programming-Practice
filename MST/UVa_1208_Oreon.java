package MST;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

import MST.Prim.Triple;

public class UVa_1208_Oreon {

	static LinkedList<Pair> adj[];
	static PriorityQueue<Triple> pq = new PriorityQueue<Triple>();
	static boolean taken[];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), cases = 1;
		
		while(t-- > 0) {
			pq.clear();
			int v = sc.nextInt();
			adj	= new LinkedList[v];
			taken = new boolean[v];
			
			for(int i = 0; i < v; ++i) adj[i] = new LinkedList<Pair>();
			
			String tmp[];
			
			for(int i = 0; i < v; ++i) {
				tmp = sc.nextLine().split(",");
				for(int j = 0; j < v; ++j) {
					if(i != j && Integer.parseInt(tmp[j].trim()) != 0){
						adj[i].add(new Pair(j, Integer.parseInt(tmp[j].trim())));
					}
				}
			}
			
			process(0);
			out.printf("Case %d:\n", cases++);
			
			ArrayList<Triple> res = new ArrayList<Triple>();
			
			while(!pq.isEmpty()) {
				Triple edge = pq.poll();
				if(!taken[edge.b]) {
					int small, big;
					if(edge.a < edge.b) {
						small = edge.a; big = edge.b;
					}
					else {
						small = edge.b; big = edge.a;
					}
					res.add(new Triple(small, big, edge.w));
					process(edge.b);
				}
			}
			
			Collections.sort(res);
			
			for(Triple edge : res) {
				out.println(((char) ('A' + edge.a)) + "-" + ((char) ('A' + edge.b)) + " " + edge.w);
			}
		}
		
		out.flush();
		out.close();
	}
	
	private static void process(int i) {
		taken[i] = true;
		
		for(Pair p : adj[i]) {
			if(!taken[p.v]) {
				pq.add(new Triple(i, p.v, p.w));
			}
		}
	}

	static class Triple implements Comparable<Triple> {
		int a;
		int b;
		int w;
		
		public Triple(int a, int b, int w) {
			this.a = a;
			this.b = b;
			this.w = w;
		}

		@Override
		public int compareTo(Triple t) {
			if(Integer.compare(this.w, t.w) == 0)
				return Integer.compare(this.a, t.a);
			return Integer.compare(this.w, t.w);
		}
	}
	
	static class Pair implements Comparable<Pair>{
		int v;
		int w;
		
		public Pair(int v, int w) {
			this.v = v;
			this.w = w;
		}

		@Override
		public int compareTo(Pair p) {
			return Integer.compare(this.w, p.w);
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