package segment_trees;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SerejaandBrackets {

	static int N;
	static char s[];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		s = sc.nextLine().toCharArray();
		N = s.length;
		SegmentTree seg = new SegmentTree();
		
		int m = sc.nextInt();
		
		while(m-- > 0) {
			out.println(seg.query(sc.nextInt() - 1, sc.nextInt() - 1));
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
		
		static class STNode {
			int left, right;
			int openBr, closedBr;
			
			void assignLeaf(char c, int l, int r) {
				left = l; right = r;
				if(c == '(') { openBr = 1; closedBr = 0; }
				else { openBr = 0; closedBr = 1; }
			}
			
			void merge (STNode left, STNode right) {
				this.left = left.left; this.right = right.right;
				int matched = Math.min(left.openBr, right.closedBr);
				openBr = left.openBr + right.openBr - matched;
				closedBr = left.closedBr + right.closedBr - matched;
			}
			
			int getVal() {
				return (right - left + 1) - (openBr + closedBr);
			}
			
			@Override
			public String toString() {
				return "(" + openBr + ", " + closedBr + ", " + left + ", " + right + ")";
			}
		}
		
		public SegmentTree() {
			nodes = new STNode[getSTSize()];
			build(1, 0, N - 1);
		}

		private void build(int stIdx, int lo, int hi) {
			if(lo == hi) {
				nodes[stIdx] = new STNode();
				nodes[stIdx].assignLeaf(s[lo], lo, hi);
			}
			else {
				int mid = (lo + hi) >> 1;
				int left = (stIdx << 1), right = left | 1;
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
			if(left == lo && right == hi) return nodes[stIdx];
			
			int mid = (left + right) >> 1;
			int l = (stIdx << 1), r = l | 1;
			
			if(lo > mid)
				return query(r, mid + 1, right, lo, hi);
			else if(hi <= mid)
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