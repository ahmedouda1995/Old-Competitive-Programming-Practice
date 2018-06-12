package segment_trees;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class PharaohsBank {

	static int N;
	static int arr[] = new int[100000];
	static PrintWriter out = new PrintWriter(System.out);
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));

		N = sc.nextInt();
		
		for(int i = 0; i < N; ++i) arr[i] = sc.nextInt();
		
		SegmentTree st = new SegmentTree();
		
		int m = sc.nextInt(), a, b;
		
		while(m-- > 0) {
			a = sc.nextInt() - 1;
			b = sc.nextInt() - 1;
			st.query(a, b);
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
			int sum, lo, hi;
			int max, maxSize;
			int maxPref, prefSt, prefEnd;
			int maxSuff, suffSt, suffEnd;
			
			void assignLeaf(int idx, int val) {
				sum = max = maxPref = maxSuff = val;
				prefSt = prefEnd = suffSt = suffEnd = lo = hi = idx;
				maxSize = 1;
			}
			
			void getVal() {
				out.println(max + " " + maxSize);
			}
			
			void merge(STNode left, STNode right) {
				if(left.maxPref > left.sum + right.maxPref) {
					maxPref = left.maxPref;
					prefSt = left.prefSt;
					prefEnd = left.prefEnd;
				}
				else {
					maxPref = left.sum + right.maxPref;
					prefSt = left.lo;
					prefEnd = right.prefEnd;
				}
				
				if(right.maxSuff > right.sum + left.maxSuff) {
					maxSuff = right.maxSuff;
					suffSt = right.suffSt;
					suffEnd = right.suffEnd;
				}
				else {
					maxSuff = right.sum + left.maxSuff;
					suffSt = left.suffSt;
					suffEnd = right.hi;
				}
				
				sum = left.sum + right.sum;
				lo = left.lo; hi = right.hi;
				
				max = sum; maxSize = hi - lo + 1;
				
				if(left.max > max || (left.max == max && left.maxSize > maxSize)) {
					max = left.max;
					maxSize = left.maxSize;
				}
				if(right.max > max || (right.max == max && right.maxSize > maxSize)) {
					max = right.max;
					maxSize = right.maxSize;
				}
				if(left.maxSuff + right.maxPref > max || (left.maxSuff + right.maxPref == max && right.prefEnd - left.suffSt + 1 > maxSize)) {
					max = left.maxSuff + right.maxPref;
					maxSize = right.prefEnd - left.suffSt + 1;
				}
			}
		}
		
		public SegmentTree() {
			nodes = new STNode[getSTSize()];
			build(1, 0, N - 1);
		}

		private void build(int stIdx, int lo, int hi) {
			if(lo == hi) {
				nodes[stIdx] = new STNode();
				nodes[stIdx].assignLeaf(lo, arr[lo]);
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

		void query(int lo, int hi) {
			query(1, 0, N - 1, lo, hi).getVal();
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