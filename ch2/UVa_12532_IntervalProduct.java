package ch2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_12532_IntervalProduct {

	static int N;
	static int arr[] = new int[100_000];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int k;
		StringBuilder sb;
		
		while(sc.ready()) {
			N = sc.nextInt();
			k = sc.nextInt();
			
			for(int i = 0; i < N; ++i) arr[i] = sc.nextInt();
			SegmentTree st = new SegmentTree();
			sb = new StringBuilder();
			
			while(k -- > 0) {
				if(sc.next().charAt(0) == 'C')
					st.update(sc.nextInt() - 1, sc.nextInt());
				else {
					int tmp = st.query(sc.nextInt() - 1, sc.nextInt() - 1);
					if(tmp == 0) sb.append(0);
					else if(tmp < 0) sb.append('-');
					else sb.append('+');
				}
			}
			out.println(sb.toString());
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
		
		int [] nodes;
		
		public SegmentTree() {
			nodes = new int[getSTSize()];
			build(1, 0, N - 1);
		}

		private void build(int stIdx, int lo, int hi) {
			if(lo == hi) {
				if(arr[lo] < 0)
					nodes[stIdx] = -1;
				else if(arr[lo] == 0)
					nodes[stIdx] = 0;
				else
					nodes[stIdx] = 1;
			}
			else {
				int mid = (lo + hi) >> 1;
				int left = (stIdx << 1), right = left + 1;
				build(left, lo, mid);
				build(right, mid + 1, hi);
				nodes[stIdx] = nodes[left] * nodes[right];
			}
		}

		private int getSTSize() {
			int sz = 1;
			while(sz < N) sz <<= 1;
			return sz << 1;
		}
		
		int query(int lo, int hi) {
			return query(1, 0, N - 1, lo, hi);
		}

		private int query(int stIdx, int left, int right, int lo, int hi) {
			if(left == lo && right == hi)
				return nodes[stIdx];
			
			int mid = (left + right) >> 1;
			int l = (stIdx << 1), r = l + 1;
			if(lo > mid)
				return query(r, mid + 1, right, lo, hi);
			if(hi <= mid)
				return query(l, left, mid, lo, hi);
			
			int leftRes = query(l, left, mid, lo, mid);
			int rightRes = query(r, mid + 1, right, mid + 1, hi);
			
			return leftRes * rightRes;
		}
		
		void update(int idx, int val) {
			update(1, 0, N - 1, idx, val);
		}

		private void update(int stIdx, int lo, int hi, int idx, int val) {
			if(lo == hi) {
				if(val < 0)
					nodes[stIdx] = -1;
				else if(val == 0)
					nodes[stIdx] = 0;
				else
					nodes[stIdx] = 1;
			}
			else {
				int mid = (lo + hi) >> 1;
				int left = (stIdx << 1), right = left + 1;
				
				if(idx > mid)
					update(right, mid + 1, hi, idx, val);
				else
					update(left, lo, mid, idx, val);
				nodes[stIdx] = nodes[left] * nodes[right];
			}
		}
	}
}