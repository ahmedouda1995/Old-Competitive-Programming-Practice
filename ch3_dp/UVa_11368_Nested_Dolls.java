package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class UVa_11368_Nested_Dolls {

	static ArrayList<Pair> a = new ArrayList<Pair>();
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		
		while(t-- > 0) {
			a.clear();
			int n = sc.nextInt();
			for(int i = 0; i < n; ++i) a.add(new Pair(sc.nextInt(), sc.nextInt()));
			
			Collections.sort(a);
			
			int k = 1;
			ArrayList<Integer> tmp = new ArrayList<Integer>();
			tmp.add(a.get(0).w);
			
			while(a.get(k) == a.get(0)) {
				tmp.add(0, a.get(k).w);
			}
			
			for(; k < a.size(); ++k) {
				
				Pair p = a.get(k);
				int pos = ceil(p.w, tmp);
				
				if(pos == -1) tmp.add(p.w);
				else if(tmp.get(pos) == p.w) {
					int pos2 = floor(p.w, tmp);
					if(pos2 >= pos && pos2 + 1 < tmp.size()) {
						tmp.set(pos2 + 1, p.w);
					}
					else
						tmp.add(pos, p.w);
				}
				else tmp.set(pos, p.w);
			}
			
			out.println(tmp.size());
		}
		
		out.flush();
		out.close();
	}
	
	static int floorUtil(int key, int l, int r, ArrayList<Integer> tmp)
	{
	    int m;
	 
	    while( r - l > 1 )
	    {
	        m = l + (r - l)/2;
	 
	        if(tmp.get(m) <= key)
	            l = m;
	        else
	            r = m;
	    }
	 
	    return l;
	}
	 
	// Initial call
	static int floor(int key, ArrayList<Integer> tmp)
	{
	    // Add error checking if key < A[0]
	    if(key < tmp.get(0))
	        return -1;
	 
	    // Observe boundaries
	    return floorUtil(key, 0, tmp.size(), tmp);
	}
	
	static int ceil(int key, ArrayList<Integer> tmp) {
		if(key > tmp.get(tmp.size() - 1)) return -1;
		return ceilUtil(-1, tmp.size() - 1, key, tmp);
	}

	private static int ceilUtil(int l, int r, int key, ArrayList<Integer> tmp) {
		int m;
		
		while(r - l > 1) {
			m = l + (r - l) / 2;
			
			if(tmp.get(m) >= key) r = m;
			else l = m;
		}
		
		return r;
	}

	static class Pair implements Comparable<Pair>{
		int l;
		int w;
		
		public Pair(int l, int w) {
			this.l = l;
			this.w = w;
		}

		@Override
		public int compareTo(Pair p) {
			if(Integer.compare(p.l, l) == 0)
				return Integer.compare(w, p.w);
			//1
			//5
			//20 20 10 10 10 20 10 30 10 40
			return Integer.compare(p.l, l);
		}
		
		@Override
		public String toString() {
			return "(" + l + ", " + w + ")";
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