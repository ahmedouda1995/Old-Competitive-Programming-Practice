package ch2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class UVa_10507_Waking_up_brain {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		while(true){
			LinkedList<Character> adj[] = new LinkedList[26];
			for(int i = 0; i < 26; ++i) adj[i] = new LinkedList<Character>();
			Queue q = new LinkedList<Character>(), q2 = new LinkedList<Character>();
			int n = sc.nextInt() - 3, m = sc.nextInt();
			TreeMap<Character, Pair> map = new TreeMap<Character, Pair>();
			String s = sc.next();
			map.put(s.charAt(0), new Pair(null, true)); map.put(s.charAt(1), new Pair(null, true));
			map.put(s.charAt(2), new Pair(null, true));
			
			for (int i = 0; i < m; ++i) {
				s = sc.next();
				if(!map.containsKey(s.charAt(0)))
					map.put(s.charAt(0), new Pair(new TreeSet<Character>(), false));
				if(!map.containsKey(s.charAt(1)))
					map.put(s.charAt(1), new Pair(new TreeSet<Character>(), false));
				boolean f = map.get(s.charAt(0)).awake, sec = map.get(s.charAt(1)).awake;
				if(f){
					if(!sec){
						Pair p = map.get(s.charAt(1));
						p.set.add(s.charAt(0));
						if(p.set.size() == 3){
							q.add(s.charAt(1));
						}
					}
				}
				else if(sec){
					Pair p = map.get(s.charAt(0));
					p.set.add(s.charAt(1));
					if(p.set.size() == 3){
						q.add(s.charAt(0));
					}
				}
				else {
					adj[s.charAt(0) - 'A'].add(s.charAt(1));
					adj[s.charAt(1) - 'A'].add(s.charAt(0));
				}
			}
			int years = 0;
			while(!q.isEmpty()) {
				while(!q.isEmpty()){
					char c = (char) q.poll();
					n--;
					for(char cc : adj[c - 'A']){
						Pair p = map.get(cc);
						p.set.add(c);
						if(p.set.size() == 3){
							q2.add(cc);
						}
					}
				}
				years++;
				q = new LinkedList<Character>(q2); q2.clear();
			}
			if(n == 0)
				out.println("WAKE UP IN, " + years + ", YEARS");
			else
				out.println("THIS BRAIN NEVER WAKES UP");
			if(sc.nextLine() == null)
				break;
		}
		
		out.flush();
		out.close();
	}
	
	static class Pair{
		TreeSet<Character> set;
		boolean awake;
		
		public Pair(TreeSet<Character> set, boolean awake) {
			this.set = set;
			this.awake = awake;
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