package ds_math_alg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SegmentTree7 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int n, q;
		
		n = sc.nextInt();
		q = sc.nextInt() + sc.nextInt();
		
		int op;
		SegmentTree st = new SegmentTree(n);
		
		while(q-- > 0)
		{
			op = sc.nextInt();
			switch(op)
			{
			case 1:
				st.updateRange(sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt() + 1);
				break;
			case 2:
				out.println(st.query(sc.nextInt() - 1, sc.nextInt() - 1));
			}
		}
		
		out.flush();
	}
	
	static class SegmentTree {

		int n;
		long arr[], sTree[], lazy[];
		boolean isSet[];
		
		public SegmentTree(int N) {
			n = N; arr = new long[N];
			int sz = getSz(n);
			sTree = new long[sz];
			lazy = new long[sz];
			isSet = new boolean[sz];
			build(1, 0, n - 1);
		}
		
		private int getSz(int n) {
			int sz = 1;
			while(sz < n) sz <<= 1;
			return sz <<= 1;
		}

		void build(int node, int b, int e) {
			if(b == e)
				sTree[node] = arr[b];
			else
			{
				int mid = (b + e >> 1);
				build(node << 1, b, mid);
				build(node << 1 | 1, mid + 1, e);
				sTree[node] = sTree[node << 1] + sTree[node << 1 | 1];
			}
		}
		
		void updateRange(int lo, int hi, long val)
		{
			updateRange(1, 0, n - 1, lo, hi, val);
		}
		
		void updateRange(int node, int b, int e, int lo, int hi, long val)
		{
			if(e < lo || b > hi) return;
			if(b >= lo && e <= hi)
			{
				sTree[node] = (e - b + 1) * val;
				lazy[node] = val;
				isSet[node] = true;
			}
			else
			{
				int mid = (b + e >> 1);
				propagate(node, b, mid, e);
				updateRange(node << 1, b, mid, lo, hi, val);
				updateRange(node << 1 | 1, mid + 1, e, lo, hi, val);
				sTree[node] = sTree[node << 1] + sTree[node << 1 | 1];
			}
		}

		private void propagate(int node, int b, int mid, int e) {
			if(!isSet[node]) return;
			sTree[node << 1] = (mid - b + 1) * lazy[node];
			lazy[node << 1] = lazy[node];
			isSet[node << 1] = true;
			sTree[node << 1 | 1] = (e - mid) * lazy[node];
			lazy[node << 1 | 1] = lazy[node];
			isSet[node << 1 | 1] = true;
			isSet[node] = false;
		}
		
		long query(int lo, int hi)
		{
			return query(1, 0, n - 1, lo, hi);
		}

		private long query(int node, int b, int e, int lo, int hi) {
			if(e < lo || b > hi) return 0;
			if(b >= lo && e <= hi)
				return sTree[node];
			else
			{
				int mid = (b + e >> 1);
				propagate(node, b, mid, e);
				long left = query(node << 1, b, mid, lo, hi);
				long right = query(node << 1 | 1, mid + 1, e, lo, hi);
				return left + right;
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