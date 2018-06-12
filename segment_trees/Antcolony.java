package segment_trees;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Antcolony {

	static int N;
	static int arr[];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		N = sc.nextInt();
		arr = new int[N];
		
		for(int i = 0; i < N; ++i) arr[i] = sc.nextInt();
		SegTree st = new SegTree();
		int m = sc.nextInt();
		
		while(m-- > 0) {
			out.println(st.query(sc.nextInt() - 1, sc.nextInt() - 1));
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
	
	static class SegTree {
		
		STNode nodes[];
		
		static int gcd(int a, int b) { if(b == 0) return a; else return gcd(b, a % b); }
		
		static class STNode {
			int left, right;
			int min, num, gcd;
			
			void assignLeaf(int val, int left, int right) {
				min = gcd = val;
				num = 1;
				this.left = left; this.right = right;
			}
			
			void merge(STNode first, STNode sec) {
				left = first.left; right = sec.right;
				int minimum = Math.min(first.min, sec.min);
				min = minimum;
				if(minimum == first.min) num += first.num;
				if(minimum == sec.min) num += sec.num;
				this.gcd = gcd(first.gcd, sec.gcd);
			}
			
			int getValue() {
				if(gcd == min)
					return right - left + 1 - num;
				return right - left + 1;
			}
		}
		
		public SegTree() {
			nodes = new STNode[getSTSize()];
			build(1, 0, N - 1);
		}

		private void build(int stIdx, int lo, int hi) {
			if(lo == hi) {
				nodes[stIdx] = new STNode();
				nodes[stIdx].assignLeaf(arr[lo], lo, hi);
			}
			else {
				int mid = (lo + hi) >> 1;
				int left = stIdx << 1, right = left + 1;
				
				build(left, lo, mid);
				build(right, mid + 1, hi);
				
				nodes[stIdx] = new STNode();
				nodes[stIdx].merge(nodes[left], nodes[right]);
			}
		}
		
		int query(int lo, int hi) {
			return query(1, 0, N - 1, lo, hi).getValue();
		}

		private STNode query(int stIdx, int left, int right, int lo, int hi) {
			if(left == lo && right == hi) return nodes[stIdx];
			
			int mid = (left + right) >> 1;
			int l = (stIdx << 1), r = l | 1;
			
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

		private int getSTSize() {
			int sz = 1;
			while(sz < N) sz <<= 1;
			return sz << 1;
		}
	}
}