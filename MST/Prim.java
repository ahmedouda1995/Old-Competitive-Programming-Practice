package MST;

import java.util.LinkedList;
import java.util.PriorityQueue;

public class Prim {

	static int V = 5;
	static LinkedList<Pair> adj[] = new LinkedList[V];
	static boolean taken[] = new boolean[V];
	static PriorityQueue<Triple> pq = new PriorityQueue<Triple>();
	
	public static void main(String[] args) {
		
		for(int i = 0; i < V; ++i) adj[i] = new LinkedList<Pair>();
		
		adj[0].add(new Pair(1, 4)); adj[1].add(new Pair(0, 4));
		adj[1].add(new Pair(2, 2)); adj[2].add(new Pair(1, 2));
		adj[0].add(new Pair(2, 4)); adj[2].add(new Pair(0, 4));
		adj[0].add(new Pair(3, 6)); adj[3].add(new Pair(0, 6));
		adj[2].add(new Pair(3, 8)); adj[3].add(new Pair(2, 8));
		adj[0].add(new Pair(4, 6)); adj[4].add(new Pair(0, 6));
		adj[3].add(new Pair(4, 9)); adj[4].add(new Pair(3, 9));
		
		process(0);
		int mst_cost = 0;
		
		while(!pq.isEmpty()) {
			Triple t = pq.poll();
			if(!taken[t.b]) {
				System.out.println(t.a + " > " + t.b + " " + t.w);
				mst_cost += t.w;
				process(t.b);
			}
		}
		
		System.out.println(mst_cost);
	}
	
	public static void process(int i) {
		taken[i] = true;
		for(Pair p : adj[i]) {
			if(!taken[p.v])
				pq.offer(new Triple(i, p.v, p.w));
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
				return Integer.compare(this.b, t.b);
			return Integer.compare(this.w, t.w);
		}
	}
	
	static class Pair {
		int v;
		int w;
		
		public Pair(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
}
