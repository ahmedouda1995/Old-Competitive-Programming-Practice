package ch4_dfs_bfs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class UVa_124_FollowingOrders {

	static LinkedList<Integer> adj[] = new LinkedList[20];
	static int indegree[] = new int[20];
	static boolean vis[] = new boolean[20];
	static ArrayList<Integer> res = new ArrayList<Integer>();
	static int N;
	static TreeMap<Integer, Character> map2 = new TreeMap<Integer, Character>();
	static PrintWriter out = new PrintWriter(System.out);
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		
		String s;
		
		TreeMap<Character, Integer> map = new TreeMap<Character, Integer>();
		boolean first = true;
		while((s = sc.nextLine()) != null) {
			
			if(!first) out.println();
			else first = false;
			
			map.clear(); map2.clear();
			String [] tmp = s.split(" ");
			Arrays.sort(tmp);
			N = tmp.length;
			for(int i = 0; i < N; ++i) adj[i] = new LinkedList<Integer>();
			for(int i = 0; i < tmp.length; ++i) {
				map.put(tmp[i].charAt(0), i); map2.put(i, tmp[i].charAt(0));
			}
			
			tmp = sc.nextLine().split(" ");
			
			for(int i = 0; i < tmp.length; i += 2)
				adj[map.get(tmp[i].charAt(0))].add(map.get(tmp[i + 1].charAt(0)));
			
			for(int i = 0; i < N; ++i) indegree[i] = 0;
			for(int i = 0; i < N; ++i) for(int v : adj[i]) indegree[v]++;
			
			toposorts();
		}
		
		out.flush();
		out.close();
	}
	
	private static void toposorts() {
		boolean flag = false;
		
		for(int i = 0; i < N; ++i) {
			if(!vis[i] && indegree[i] == 0) {
				flag = true;
				vis[i] = true;
				for(int v : adj[i]) indegree[v]--;
				res.add(i);
				
				toposorts();
				
				vis[i] = false;
				for(int v : adj[i]) indegree[v]++;
				res.remove(res.size() - 1);
			}
		}
		
		if(!flag) {
			for(int i = 0; i < N; ++i) out.print(map2.get(res.get(i)));
			out.println();
		}
	}

	public static int gcd(int n, int m) {
	    return (n % m) == 0? m : gcd(m, n % m);
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