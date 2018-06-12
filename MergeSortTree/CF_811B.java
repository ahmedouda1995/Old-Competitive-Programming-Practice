package MergeSortTree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CF_811B {
	
	static int arr[];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int n, m;
		n = sc.nextInt();
		m = sc.nextInt();
		
		arr = new int[n];
		
		for(int i = 0; i < n; ++i) arr[i] = sc.nextInt();
		
		SegmentTree st = new SegmentTree(n);
		
		int l, r, x;
		
		while(m-- > 0) {
			l = sc.nextInt() - 1;
			r = sc.nextInt() - 1;
			x = sc.nextInt() - 1;
			if(l + st.query(l, r, x) == x)
				out.println("Yes");
			else
				out.println("No");
		}
		
		out.flush();
		out.close();
	}

	static class SegmentTree {
		ArrayList<Integer> nodes[];
		int n;
		
		public SegmentTree(int n) {
			int sz = getSTSize(n);
			nodes = new ArrayList[sz];
			this.n = n;
			for(int i = 0; i < sz; ++i)
				nodes[i] = new ArrayList<>();
			build(1, 0, n - 1);
		}

		private void build(int stIdx, int l, int r) {
			if(l == r) nodes[stIdx].add(arr[l]);
			else {
				int mid = l + (r - l) / 2;
				build(stIdx << 1, l, mid);
				build((stIdx << 1) | 1, mid + 1, r);
				merge(stIdx, stIdx << 1, (stIdx << 1) | 1);
			}
		}
		
		void merge(int idx, int left, int right) {
			int n = nodes[left].size();
			int m = nodes[right].size();
			
			int i, j;
			i = j = 0;
			
			while(i < n || j < m) {
				if(i == n) nodes[idx].add(nodes[right].get(j++));
				else if(j == m) nodes[idx].add(nodes[left].get(i++));
				else if(nodes[left].get(i) < nodes[right].get(j))
					nodes[idx].add(nodes[left].get(i++));
				else
					nodes[idx].add(nodes[right].get(j++));
			}

		}
		
		int query(int l, int r, int x) {
			return query(1, 0, n - 1, l, r, x);
		}

		private int query(int stIdx, int lo, int hi, int l, int r, int x) {
			if(lo > r || hi < l) return 0;
			else if(lo >= l && hi <= r) {
				return lowerBound(stIdx, x);
			}
			else {
				int mid = lo + (hi - lo) / 2;
				return query(stIdx << 1, lo, mid, l, r, x) + query((stIdx << 1) | 1, mid + 1, hi, l, r, x);
			}
		}

		private int lowerBound(int stIdx, int x) {
			int lo = 0, hi = nodes[stIdx].size() - 1, mid;
			
			while(lo <= hi) {
				mid = lo + (hi - lo) / 2;
				if(nodes[stIdx].get(mid) >= arr[x])
					hi = mid - 1;
				else
					lo = mid + 1;
			}
			
			return lo;
		}

		private int getSTSize(int n) {
			int sz = 1;
			while(sz < n) sz <<= 1;
			return (sz << 1);
		}
	}
	
	static class Pair implements Comparable<Pair> {
		int a, b;
		
		public Pair(int x, int y) {
			a = x;
			b = y;
		}
		
		@Override
		public int compareTo(Pair p) {
			return Integer.compare(Math.min(2 * p.a, p.b), Math.min(2 * a, b));
		}
		
		@Override
		public String toString() {
			return a + " " + b;
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