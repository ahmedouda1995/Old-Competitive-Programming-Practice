package MST;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class UVa_1160_X_Plosives {

	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int a, b; String s;
		
		ArrayList<Pair> arr = new ArrayList<Pair>(); 
		TreeMap<Integer, Integer> map = new TreeMap<Integer, Integer>();
		while((s = sc.nextLine()) != null) {
			map.clear(); arr.clear();
			int k = 0;
			
			String [] tmp = s.split(" ");
			a = Integer.parseInt(tmp[0]);
			b = Integer.parseInt(tmp[1]);
			
			if(!map.containsKey(a))
				map.put(a, k++);
			if(!map.containsKey(b))
				map.put(b, k++);
			arr.add(new Pair(map.get(a), map.get(b)));
			while((a = sc.nextInt()) != -1) {
				b = sc.nextInt();
				if(!map.containsKey(a))
					map.put(a, k++);
				if(!map.containsKey(b))
					map.put(b, k++);
				arr.add(new Pair(map.get(a), map.get(b)));
			}
			
			UFDS ds = new UFDS(k);
			int res = 0;
			
			for(Pair p : arr) {
				if(ds.isSameSet(p.x, p.y)) res++;
				else ds.unionSet(p.x, p.y);
			}
			out.println(res);
			sc.nextLine();
		}
		
		out.flush();
		out.close();
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