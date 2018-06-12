package ds_math_alg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SegmentTree1 {
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int n = sc.nextInt();
		long arr[] = new long[n];
		for(int i = 0; i < n; ++i) arr[i] = sc.nextInt();
		
		SegmentTree st = new SegmentTree(arr);
		
		int q = sc.nextInt(), lo, hi, val;
		StringTokenizer tok;
		while(q-- > 0)
		{
			tok = new StringTokenizer(sc.nextLine());
			if(tok.countTokens() == 2)
			{
				lo = Integer.parseInt(tok.nextToken());
				hi = Integer.parseInt(tok.nextToken());
				
				if(lo <= hi)
					out.println(st.query(lo, hi));
				else
					out.println(Math.min(st.query(0, hi), st.query(lo, arr.length - 1)));
			}
			else
			{
				lo = Integer.parseInt(tok.nextToken());
				hi = Integer.parseInt(tok.nextToken());
				val = Integer.parseInt(tok.nextToken());
				if(lo <= hi)
					st.updateRange(lo, hi, val);
				else
				{
					st.updateRange(0, hi, val);
					st.updateRange(lo, arr.length - 1, val);
				}
			}
		}
		
		out.flush();
		out.close();
	}
	
	static class SegmentTree {

		int n;
		long arr[], sTree[], lazy[];
		
		public SegmentTree(long in[]) {
			n = in.length; arr = in;
			int sz = getSz(n);
			sTree = new long[sz];
			lazy = new long[sz];
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
				sTree[node] = Math.min(sTree[node << 1], sTree[node << 1 | 1]);
			}
		}
		
		void updatePoint(int idx, int val)
		{
			updatePoint(1, idx, 0, n - 1, val);
		}

		void updatePoint(int node, int idx, int b, int e, int val) {
			if(b == e) sTree[node] += val;
			else
			{
				int mid = (b + e >> 1);
				if(idx <= mid)
					updatePoint(node << 1, idx, b, mid, val);
				else
					updatePoint(node << 1 | 1, idx, mid + 1, e, val);
				sTree[node] = Math.min(sTree[node << 1], sTree[node << 1 | 1]);
			}
		}
		
		void updateRange(int lo, int hi, int val)
		{
			updateRange(1, 0, n - 1, lo, hi, val);
		}
		
		void updateRange(int node, int b, int e, int lo, int hi, int val)
		{
			if(e < lo || b > hi) return;
			if(b >= lo && e <= hi)
			{
				sTree[node] += val;
				lazy[node] += val;
			}
			else
			{
				int mid = (b + e >> 1);
				propagate(node, b, mid, e);
				updateRange(node << 1, b, mid, lo, hi, val);
				updateRange(node << 1 | 1, mid + 1, e, lo, hi, val);
				sTree[node] = Math.min(sTree[node << 1], sTree[node << 1 | 1]);
			}
		}

		private void propagate(int node, int b, int mid, int e) {
			sTree[node << 1] += lazy[node];
			lazy[node << 1] += lazy[node];
			sTree[node << 1 | 1] += lazy[node];
			lazy[node << 1 | 1] += lazy[node];
			lazy[node] = 0;
		}
		
		long query(int lo, int hi)
		{
			return query(1, 0, n - 1, lo, hi);
		}

		private long query(int node, int b, int e, int lo, int hi) {
			if(e < lo || b > hi) return Long.MAX_VALUE;
			if(b >= lo && e <= hi)
				return sTree[node];
			else
			{
				int mid = (b + e >> 1);
				propagate(node, b, mid, e);
				long left = query(node << 1, b, mid, lo, hi);
				long right = query(node << 1 | 1, mid + 1, e, lo, hi);
				return Math.min(left, right);
			}
		}
	}
	
	static class Scanner 
	{
		StringTokenizer st;
		BufferedReader br;

		Scanner(InputStream system) {br = new BufferedReader(new InputStreamReader(system));}
		Scanner(String file) throws FileNotFoundException {br = new BufferedReader(new FileReader(file));}
		String next() throws IOException 
		{
			while (st == null || !st.hasMoreTokens()) 
				st = new StringTokenizer(br.readLine());
			return st.nextToken();
		}
		String nextLine()throws IOException{return br.readLine();}
		int nextInt() throws IOException {return Integer.parseInt(next());}
		double nextDouble() throws IOException {return Double.parseDouble(next());}
		char nextChar()throws IOException{return next().charAt(0);}
		Long nextLong()throws IOException{return Long.parseLong(next());}
		boolean ready() throws IOException{return br.ready();}
		void waitForInput(){for(long i = 0; i < 3e9; i++);}
	}
}