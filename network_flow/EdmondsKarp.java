package network_flow;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class EdmondsKarp {

	static int mf, f, s, t;
	static final int MAX = 5, INF = (int) 1e9;
	static int resid[][] = new int[MAX][MAX];
	static ArrayList<Integer> adj[] = new ArrayList[MAX];
	static int p[];
	
	public static void main(String[] args) {
		
		for(int i = 0; i < MAX; ++i) adj[i] = new ArrayList<Integer>();
		adj[0].add(2); adj[0].add(3);
		adj[2].add(1); adj[2].add(3); adj[2].add(4);
		adj[3].add(4);
		adj[4].add(1);
		// back edges
		adj[2].add(0); adj[3].add(0);
		adj[1].add(2); adj[3].add(2); adj[4].add(2);
		adj[4].add(3);
		adj[1].add(4);
		
		resid = new int[][]{{0, 0, 100, 50, 0},
							{0, 0, 0, 0, 0},
							{0, 50, 0, 50, 50},
							{0, 0, 0, 0, 100},
							{0, 125, 0, 0, 0}};
		
		mf = 0; s = 0; t = 1;
		
		while(true) {
			f = 0;
			
			p = new int[MAX];
			Arrays.fill(p, -1);
			
			Queue<Integer> q = new LinkedList<Integer>();
			q.offer(s);
			p[s] = s;
			
			while(!q.isEmpty()) {
				int u = q.poll();
				
				if(u == t) break;
				
				for(int v : adj[u]) {
					if(resid[u][v] > 0 && p[v] == -1) {
						p[v] = u;
						q.offer(v);
					}
				}
			}
			if(p[t] == -1) break;
			augment(t, INF);
			mf += f;
		}
		
		System.out.println(mf);
	}

	private static void augment(int v, int minEdge) {
		if(v == s) { f = minEdge; return; }
		augment(p[v], Math.min(minEdge, resid[p[v]][v]));
		resid[p[v]][v] -= f; resid[v][p[v]] += f;
	}
}