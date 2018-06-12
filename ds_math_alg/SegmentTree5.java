package ds_math_alg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SegmentTree5 {

	static int arr[];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int n, q, op;
		
		n = sc.nextInt(); q = sc.nextInt();
		arr = new int[n];
		for(int i = 0; i < n; ++i) arr[i] = sc.nextInt();
		
		SegmentTree st = new SegmentTree();
		
		while(q-- > 0)
		{
			op = sc.nextInt();
			if(op == 1)
				st.updatePoint(sc.nextInt() - 1, sc.nextInt());
			else
				out.println(st.query(sc.nextInt() - 1, sc.nextInt() - 1));
		}
		out.flush();
	}
	
	static class SegmentTree {

		int n;
		Node sTree[];
		
		public SegmentTree() {
			n = arr.length;
			int sz = getSz(n);
			sTree = new Node[sz];
			build(1, 0, n - 1);
		}
		
		private int getSz(int n) {
			int sz = 1;
			while(sz < n) sz <<= 1;
			return sz <<= 1;
		}

		void build(int node, int b, int e) {
			if(b == e)
				sTree[node] = new Node(b);
			else
			{
				int mid = (b + e >> 1);
				build(node << 1, b, mid);
				build(node << 1 | 1, mid + 1, e);
				sTree[node] = Node.merge(sTree[node << 1], b, mid, sTree[node << 1 | 1], mid + 1, e);
			}
		}
		
		void updatePoint(int idx, int val)
		{
			updatePoint(1, idx, 0, n - 1, val);
		}

		void updatePoint(int node, int idx, int b, int e, int val) {
			if(b == e) arr[b] = val;
			else
			{
				int mid = (b + e >> 1);
				if(idx <= mid)
					updatePoint(node << 1, idx, b, mid, val);
				else
					updatePoint(node << 1 | 1, idx, mid + 1, e, val);
				sTree[node] = Node.merge(sTree[node << 1], b, mid, sTree[node << 1 | 1], mid + 1, e);
			}
		}
		
		int query(int lo, int hi)
		{
			Node res = query(1, 0, n - 1, lo, hi);
			return res.best.r - res.best.l + 1;
		}

		private Node query(int node, int b, int e, int lo, int hi) {
			if(e < lo || b > hi) return null;
			if(b >= lo && e <= hi) return sTree[node];
			else
			{
				int mid = (b + e >> 1);
				Node left = query(node << 1, b, mid, lo, hi);
				Node right = query(node << 1 | 1, mid + 1, e, lo, hi);
				if(left == null) return right;
				if(right == null) return left;
				return Node.merge(left, b, mid, right, mid + 1, e);
			}
		}
		
		static class Node
		{
			Pair prefix, suffix, best;
			
			public Node()
			{
				prefix = new Pair();
				suffix = new Pair();
				best = new Pair();
			}
			
			public Node(int a) {
				prefix = new Pair(a, a);
				suffix = new Pair(a, a);
				best = new Pair(a, a);
			}
			
			static Node merge(Node left, int lo1, int hi1, Node right, int lo2, int hi2)
			{
				Node ret = new Node();
				if(left.prefix.l == lo1 && left.prefix.r == hi1 && arr[hi1] <= arr[lo2])
				{
					ret.prefix.l = lo1;
					ret.prefix.r = right.prefix.r;
				}
				else
				{
					ret.prefix.l = left.prefix.l;
					ret.prefix.r = left.prefix.r;
				}
				
				if(right.suffix.l == lo2 && right.suffix.r == hi2 && arr[hi1] <= arr[lo2])
				{
					ret.suffix.l = left.suffix.l;
					ret.suffix.r = hi2;
				}
				else
				{
					ret.suffix.l = right.suffix.l;
					ret.suffix.r = right.suffix.r;
				}
				
				ret.best.l = left.best.l;
				ret.best.r = left.best.r;
				
				if(arr[hi1] <= arr[lo2] && right.prefix.r - left.suffix.l > ret.best.r - ret.best.l)
				{
					ret.best.l = left.suffix.l;
					ret.best.r = right.prefix.r;
				}
				if(right.best.r - right.best.l > ret.best.r - ret.best.l)
				{
					ret.best.l = right.best.l;
					ret.best.r = right.best.r;
				}
				if(ret.prefix.r - ret.prefix.l > ret.best.r - ret.best.l)
				{
					ret.best.l = ret.prefix.l;
					ret.best.r = ret.prefix.r;
				}
				if(ret.suffix.r - ret.suffix.l > ret.best.r - ret.best.l)
				{
					ret.best.l = ret.suffix.l;
					ret.best.r = ret.suffix.r;
				}
				return ret;
			}
			
			static class Pair
			{
				int l, r;
				public Pair() {}
				public Pair(int lo, int hi) {
					l = lo; r = hi;
				}
			}
		}
	}
	
	static class Scanner 
	{
		StringTokenizer st; BufferedReader br;
		Scanner(InputStream system) {br = new BufferedReader(new InputStreamReader(system));}
		Scanner(String file) throws FileNotFoundException {br = new BufferedReader(new FileReader(file));}
		String next() throws IOException {
			while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
			return st.nextToken(); }
		String nextLine()throws IOException{return br.readLine();}
		int nextInt() throws IOException {return Integer.parseInt(next());}
		double nextDouble() throws IOException {return Double.parseDouble(next());}
		char nextChar()throws IOException{return next().charAt(0);}
		Long nextLong()throws IOException{return Long.parseLong(next());}
		boolean ready() throws IOException{return br.ready();}
	}
}