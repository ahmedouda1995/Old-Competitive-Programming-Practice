package sssp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class UVa_12160_UnlocktheLock {

	static final int INF = (int) 1e9;
	static int dist[] = new int[10000];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int cases = 1, start, end, buttons;
		ArrayList<Integer> but = new ArrayList<Integer>();
		
		while((start = sc.nextInt()) != 0 | (end = sc.nextInt()) != 0 | (buttons = sc.nextInt()) != 0) {
			
			for(int i = 0; i < 10000; ++i) dist[i] = INF;
			but.clear();
			
			while(buttons-- > 0) {
				but.add(sc.nextInt());
			}
			
			Queue<Integer> q = new LinkedList<Integer>();
			dist[start] = 0; q.offer(start);
			
			while(!q.isEmpty()) {
				int u = q.poll();
				
				if(u == end) break;
				
				for(int b : but) {
					int v = u + b;
					if(v > 9999) v %= 10000;
					if(dist[v] == INF) {
						q.offer(v);
						dist[v] = dist[u] + 1;
					}
				}
			}
			
			out.printf("Case %d: ", cases++);
			
			if(dist[end] == INF)
				out.println("Permanently Locked");
			else
				out.println(dist[end]);
		}
		
		out.flush();
		out.close();
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