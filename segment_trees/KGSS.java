package segment_trees;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class KGSS {

	static final int INF = (int) 1e9;
	static int N;
	static int arr[];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		N = sc.nextInt();
		arr = new int[N];
		for(int i = 0; i < N; ++i) arr[i] = sc.nextInt();
		int m = sc.nextInt();
		SegmentTree st = new SegmentTree();
		
		while(m-- > 0) {
			if(sc.next().charAt(0) == 'Q')
				out.println(st.query(sc.nextInt() - 1, sc.nextInt() - 1));
			else
				st.update(sc.nextInt() - 1, sc.nextInt());
		}

		out.flush();
		out.close();
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
	
	static class SegmentTree {
		
		STNode nodes[];
		
		SegmentTree() {
			nodes = new STNode[getSTSize()];
			build(1, 0, N - 1);
		}

		private void build(int stIdx, int lo, int hi) {
			if(lo == hi) {
				nodes[stIdx] = new STNode();
				nodes[stIdx].assignLeaf(arr[lo]);
			}
			else {
				int mid = (lo + hi) >> 1;
				int left = (stIdx << 1), right = left + 1;
				build(left, lo, mid);
				build(right, mid + 1, hi);
				nodes[stIdx] = new STNode();
				nodes[stIdx].merge(nodes[left], nodes[right]);
			}
		}

		int query(int lo, int hi) {
			return query(1, 0, N - 1, lo, hi).getVal();
		}

		private STNode query(int stIdx, int left, int right, int lo, int hi) {
			if(left == lo && right == hi) {
				return nodes[stIdx];
			}
			
			int mid = (left + right) >> 1;
			
			int l = (stIdx << 1), r = l + 1;
				
			if(lo > mid)
				return query(r, mid + 1, right, lo, hi);
			if(hi <= mid)
				return query(l, left, mid, lo, hi);
			
			STNode leftRes = query(l, left, mid, lo, mid);
			STNode rightRes = query(r, mid + 1, right, mid + 1, hi);
			STNode res = new STNode();
			res.merge(leftRes, rightRes);
			return res;
		}

		void update(int idx, int val) {
			update(1, 0, N - 1, idx, val);
		}
		
		private void update(int stIdx, int lo, int hi, int idx, int val) {
			if(lo == hi) nodes[stIdx].assignLeaf(val);
			else {
				int mid = (lo + hi) >> 1;
				int left = (stIdx << 1), right = left + 1;
				if(idx > mid)
					update(right, mid + 1, hi, idx, val);
				else
					update(left, lo, mid, idx, val);
				nodes[stIdx].merge(nodes[left], nodes[right]);
			}
		}

		private int getSTSize() {
			int sz = 1;
			while(sz < N) sz <<= 1;
			return sz << 1;
		}
		
		
		static class STNode {
			int max1, max2;
			
			void assignLeaf(int val) {
				max1 = val;
				max2 = -INF;
			}
			
			void merge(STNode left, STNode right) {
				int tmp[] = {left.max1, left.max2, right.max1, right.max2};
				Arrays.sort(tmp);
				max1 = tmp[3];
				max2 = tmp[2];
			}
			
			int getVal() { return max1 + max2; }
			@Override
			public String toString() {
				return "(" + max1 + ", " + max2 + ")";
			}
		}
	}
}