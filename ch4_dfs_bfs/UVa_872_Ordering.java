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

public class UVa_872_Ordering {

	static LinkedList<Integer> adj[] = new LinkedList[26];
	static TreeMap<Integer, Character> map2 = new TreeMap<Integer, Character>();
	static int indegree[] = new int[26];
	static int N;
	static ArrayList<Integer> res = new ArrayList<Integer>();
	static PrintWriter out = new PrintWriter(System.out);
	static boolean vis[] = new boolean[26];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		
		int t = sc.nextInt();
		
		TreeMap<Character, Integer> map = new TreeMap<Character, Integer>();
		
		while(t-- > 0) {
			map.clear(); map2.clear();
			res.clear();
			int k = 0;
			sc.nextLine();
			
			String [] tmp = sc.nextLine().split(" ");
			Arrays.sort(tmp);
			
			for(String s : tmp) {
				map.put(s.charAt(0), k); map2.put(k++, s.charAt(0));
			}
			N = k;
			for(int i = 0; i < k; ++i) {
				adj[i] = new LinkedList<Integer>();
				vis[i] = false;
			}
			tmp = sc.nextLine().split(" ");
			
			for(String s : tmp) adj[map.get(s.charAt(0))].add(map.get(s.charAt(2)));
			for(int i = 0; i < k; ++i) indegree[i] = 0;
			for(int i = 0; i < k; ++i) for(int v : adj[i]) indegree[v]++;
			
			if(!topoSorts())
				out.println("NO");
			
			if(t > 0)
				out.println();
		}
		
		out.flush();
		out.close();
	}
	
	private static boolean topoSorts() {
		boolean flag = false;
		
		for(int i = 0; i < N; ++i) {
			if(!vis[i] && indegree[i] == 0) {
				flag = true;
				vis[i] = true;
				res.add(i);
				for(int v : adj[i]) indegree[v]--;
				
				if(!topoSorts())
					return false;
				
				vis[i] = false;
				res.remove(res.size() - 1);
				for(int v : adj[i]) indegree[v]++;
			}
		}
		
		if(!flag) {
			if(res.size() != N)
				return false;
			int i;
			for(i = 0; i < N - 1; ++i) out.print(map2.get(res.get(i)) + " ");
			out.println(map2.get(res.get(i)));
		}
		return true;
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