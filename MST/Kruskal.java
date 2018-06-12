package MST;

import java.util.Collections;
import java.util.LinkedList;

// O(Elog(V))

public class Kruskal {

	static int E = 7;
	static LinkedList<Triple> edgeList = new LinkedList<Triple>();
	
	public static void main(String[] args) {
		edgeList.add(new Triple(2, 3, 8)); edgeList.add(new Triple(0, 4, 6));
		edgeList.add(new Triple(0, 1, 4)); edgeList.add(new Triple(1, 2, 2));
		edgeList.add(new Triple(0, 2, 4)); edgeList.add(new Triple(0, 3, 6));
		edgeList.add(new Triple(3, 4, 9));
		
		Collections.sort(edgeList);
		UFDS ds = new UFDS(5);
		int sum = 0;
		for(Triple t : edgeList) {
			if(ds.numSets == 1) break;
			if(!ds.isSameSet(t.a, t.b)) {
				ds.unionSet(t.a, t.b);
				sum += t.w;
				System.out.println(t.a + " > " + t.b + " " + t.w);
			}
		}
		System.out.println("sum = " + sum);
	}
	
	static class UFDS {
		int numSets;
		int setSize[], p[], rank[];
		
		public UFDS(int n) {
			numSets = n;
			setSize = new int[n];
			p = new int[n];
			rank = new int[n];
			for(int i = 0; i < n; ++i) { setSize[i] = 1; p[i] = i; }
		}
		
		public int findSet(int i) { return (i == p[i])?i:(p[i] = findSet(p[i])); }
		public boolean isSameSet(int i, int j) { return findSet(i) == findSet(j); }
		
		public int numDisjointSets() { return numSets; }
		public int sizeOfSet(int i) { return setSize[findSet(i)]; }
		
		public void unionSet(int i, int j) {
			if(isSameSet(i, j)) return;
			
			int x = findSet(i), y = findSet(j);
			numSets--;
			if(rank[x] > rank[y]) { p[y] = x; setSize[x] += setSize[y]; }
			else {
				p[x] = y; setSize[y] += setSize[x];
				if(rank[x] == rank[y]) rank[y]++;
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
			return Integer.compare(this.w, t.w);
		}
	}
}
