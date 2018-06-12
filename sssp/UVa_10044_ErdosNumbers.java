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
import java.util.TreeMap;

public class UVa_10044_ErdosNumbers {

	static int INF = (int) 1e9;
	static ArrayList<ArrayList<Integer>> adj = new ArrayList<ArrayList<Integer>>();
	static ArrayList<Integer> dist = new ArrayList<Integer>();
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt(), P, N, cases = 1;
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		
		while(t-- > 0) {
			map.clear();
			adj.clear();
			dist.clear();
			P = sc.nextInt(); N = sc.nextInt();
			
			String s;
			String arr[];
			String names[];
			int k = 0, src = -1;
			
			while(P-- > 0) {
				while((s = sc.nextLine()).length() == 0);
				s = s.split(":")[0];
				arr = s.split(",");
				int size = arr.length / 2;
				names = new String[size];
				
				for(int m = 0; m < names.length; m++) {
					names[m] = arr[2 * m].trim() + ", " + arr[2 * m + 1].trim();
					if(!map.containsKey(names[m])) {
						if(names[m].equals("Erdos, P."))
							src = k;
						map.put(names[m], k);
						dist.add(INF);
						adj.add(new ArrayList<Integer>());
						k++;
					}
				}
				
				for(int i = 0; i < names.length; ++i)
					for(int j = i + 1; j < names.length; ++j) {
						adj.get(map.get(names[i])).add(map.get(names[j]));
						adj.get(map.get(names[j])).add(map.get(names[i]));
					}
			}
			
			if(src != -1)
				bfs(src);
			out.printf("Scenario %d\n", cases++);
			while(N-- > 0) {
				while((s = sc.nextLine()).length() == 0);
				if(map.get(s) == null || src == -1) {
					out.println(s + " infinity");
					continue;
				}
				int n = map.get(s);
				if(dist.get(n) == INF)
					out.println(s + " infinity");
				else
					out.println(s + " " + dist.get(n));
			}
		}
		
		out.flush();
		out.close();
	}
	
	private static void bfs(int src) {
		dist.set(src, 0);
		Queue<Integer> q = new LinkedList<Integer>();
		q.offer(src);
		
		while(!q.isEmpty()) {
			int u = q.poll();
			
			for(int v : adj.get(u)) {
				if(dist.get(v) == INF) {
					dist.set(v, dist.get(u) + 1);
					q.offer(v);
				}
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