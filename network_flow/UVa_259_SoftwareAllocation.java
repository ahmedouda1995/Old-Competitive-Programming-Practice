package network_flow;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class UVa_259_SoftwareAllocation {

	static final int INF = (int) 1e9;
	static ArrayList<Integer> adj[] = new ArrayList[38];
	static int resid[][] = new int[38][38];
	static int p[] = new int[38];
	static int src = 36, t = 37, f, mf;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		//new InputStreamReader(System.in)

		String s = null;
		TreeMap<Integer, Character> map = new TreeMap<Integer, Character>();
		
		int res[] = new int[10];
		
		while(br.ready()) {
			mf = 0;
			Arrays.fill(res, -1);
			map.clear();
			int check = 0;
			
			for(int i = 0; i < 38; ++i) {
				Arrays.fill(resid[i], 0);
				adj[i] = new ArrayList<Integer>();
			}
			
			String sp[];
			
			int node = 10;
			
			StringTokenizer st = new StringTokenizer("");
			
			while((st.hasMoreTokens()) || ((s = br.readLine()) != null && s.length() > 0)) {
				if(!st.hasMoreTokens())
					st = new StringTokenizer(s, ";");
				sp = st.nextToken().trim().split(" ");
				sp[0] = sp[0].trim(); sp[1] = sp[1].trim();
				char c = sp[0].charAt(0);
				int count = sp[0].charAt(1) - '0';
				
				check += count;
				
				while(count-- > 0) {
					map.put(node, c);
					
					for(int i = 0; i < sp[1].length(); ++i) {
						int a = sp[1].charAt(i) - '0';
						
						adj[a].add(node); adj[node].add(a);
						resid[a][node] = 1;
						resid[node][a] = 0;
					}
					node++;
				}
			}
			
			for(int i = 0; i < 10; ++i) {
				adj[36].add(i);
				adj[i].add(36);
				resid[36][i] = 1;
				resid[i][36] = 0;
			}
			
			for(int i = 10; i < node; ++i) {
				adj[i].add(37);
				adj[37].add(i);
				resid[i][37] = 1;
				resid[37][i] = 0;
			}
			
			boolean vis[] = new boolean[38];
			
			while(true) {
				Arrays.fill(vis, false);
				f = 0;
				Queue<Integer> q = new LinkedList<Integer>();
				q.offer(src);
				Arrays.fill(p, -1);
				vis[src] = true;
				
				while(!q.isEmpty()) {
					int u = q.poll();
					
					if(u == t) break;
					
					for(int v : adj[u]) {
						if(!vis[v] && resid[u][v] > 0) {
							vis[v] = true;
							p[v] = u;
							q.offer(v);
						}
					}
				}
				
				augment(t, INF);
				if(f == 0) break;
				mf += f;
			}
			
			if(mf < check)
				out.print("!");
			else {
				
				for(int i = 10; i < node; ++i) {
					if(resid[37][i] == 1) {
						for(int j = 0; j < 10; ++j) {
							if(resid[i][j] == 1) {
								res[j] = i;
								break;
							}
						}
					}
				}
				
				for(int i = 0; i < 10; ++i) {
					if(res[i] == -1)
						out.print("_");
					else
						out.print(map.get(res[i]));
				}
			}
			out.println();
		}
		
		br.close();
		out.flush();
		out.close();
	}
	
	static void augment(int v, int minEdge) {
		if(v == src) { f = minEdge; return; }
		
		if(p[v] != -1) {
			augment(p[v], Math.min(minEdge, resid[p[v]][v]));
			
			resid[p[v]][v] -= f; resid[v][p[v]] += f;
		}
	}
	
}