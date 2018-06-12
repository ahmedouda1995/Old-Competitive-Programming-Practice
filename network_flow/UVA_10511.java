package network_flow;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class UVA_10511 {

	static final int INF = (int) 1e9, MAX = 5_000;
	static ArrayList<Integer> adj[] = new ArrayList[MAX];
	static int res[][] = new int[MAX][MAX];
	static int p[];
	static int S = 0, T = 1;
	
	public static void main(String[] args) throws IOException {
		//Scanner sc = new Scanner(System.in);
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		sc.nextLine();
		HashMap<String, Integer> mapPeople = new HashMap<String, Integer>();
		HashMap<String, Integer> mapClub = new HashMap<String, Integer>();
		HashMap<String, Integer> mapParty = new HashMap<String, Integer>();
		String names[] = new String[MAX];
		int count;
		
		while(t-- > 0)
		{
			mapPeople.clear();
			mapClub.clear();
			mapParty.clear();
			
			for(int i = 0; i < MAX; ++i)
			{
				adj[i] = new ArrayList<Integer>();
				Arrays.fill(res[i], 0);
			}
			String s, in[];
			count = 2;
			HashSet<Integer> parties = new HashSet<Integer>();
			HashSet<Integer> people = new HashSet<Integer>();
			HashSet<Integer> clubs = new HashSet<Integer>();
			
			while(sc.ready() && (s = sc.nextLine()).length() > 0)
			{
				in = s.split(" ");
				
				for(int i = 0; i < in.length; ++i)
				{
					switch (i) {
					case 0:
						if(!mapPeople.containsKey(in[i]))
							mapPeople.put(in[i], count++);
						break;
					case 1:
						if(!mapParty.containsKey(in[i]))
							mapParty.put(in[i], count++);
						break;
					default:
						if(!mapClub.containsKey(in[i]))
							mapClub.put(in[i], count++);
						break;
					}
						
					if(i > 1)
					{
						if(res[S][mapClub.get(in[i])] == 0)
						{
							res[S][mapClub.get(in[i])] = 1;
							adj[S].add(mapClub.get(in[i]));
							adj[mapClub.get(in[i])].add(S);
						}
						
						clubs.add(mapClub.get(in[i]));
						adj[mapClub.get(in[i])].add(mapPeople.get(in[0]));
						adj[mapPeople.get(in[0])].add(mapClub.get(in[i]));
						
						res[mapClub.get(in[i])][mapPeople.get(in[0])] = 1;
						names[mapClub.get(in[i])] = in[i];
					}
				}
				names[mapPeople.get(in[0])] = in[0];
				names[mapParty.get(in[1])] = in[1];
				adj[mapPeople.get(in[0])].add(mapParty.get(in[1]));
				adj[mapParty.get(in[1])].add(mapPeople.get(in[0]));
				res[mapPeople.get(in[0])][mapParty.get(in[1])] = 1;
				parties.add(mapParty.get(in[1]));
				people.add(mapPeople.get(in[0]));
			}
			
			for(int i : parties)
			{
				adj[i].add(T);
				adj[T].add(i);
				
				res[i][T] = (clubs.size() - 1) / 2;
			}
			int mf = maxFlow();
			
			if(mf != clubs.size())
				out.println("Impossible.");
			else
			{
				for(int i : clubs)
				{
					if(res[i][S] == 1)
					{
						for(int j : people)
						{
							if(res[j][i] == 1)
							{
								out.println(names[j] + " " + names[i]);
								break;
							}
						}
					}
				}
			}
			if(t > 0)
				out.println();
		}
		
		out.flush();
		out.close();
	}
	
	private static int maxFlow() {
		int mf = 0;
		
		while(true) {
			p = new int[MAX];
			Arrays.fill(p, -1);
			Queue<Integer> q = new LinkedList<Integer>();
			q.offer(S); p[S] = S;
			
			while(!q.isEmpty()) {
				int u = q.poll();
				
				if(u == T) break;
				
				for(int v : adj[u]) {
					if(p[v] == -1 && res[u][v] > 0) {
						p[v] = u;
						q.offer(v);
					}
				}
			}
			
			if(p[1] == -1) break;
			
			mf += augment(1, INF);
		}
		
		return mf;
	}

	private static int augment(int v, int minEdge) {
		if(v == S) return minEdge;
		
		minEdge = augment(p[v], Math.min(minEdge, res[p[v]][v]));
		res[p[v]][v] -= minEdge; res[v][p[v]] += minEdge;
		return minEdge;
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