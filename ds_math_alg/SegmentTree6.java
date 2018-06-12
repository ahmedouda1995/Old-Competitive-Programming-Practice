package ds_math_alg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SegmentTree6 {

	static boolean[] res = new boolean[51];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int n, q;
		
		n = sc.nextInt();
		q = sc.nextInt();
		
		int op, L, R;
		SegmentTree st = new SegmentTree(n);
		StringBuilder sb = new StringBuilder();
		
		while(q-- > 0)
		{
			op = sc.nextInt();
			L = sc.nextInt() - 1; R = sc.nextInt() - 1;
			switch(op)
			{
			case 1: st.updatePoint(L, R + 1, 1); break;
			case 2: st.updatePoint(L, R + 1, -1); break;
			case 3:
			st.query(L, R);
			sb = new StringBuilder();
			int c = 0;
			for(int i = 1; i <= 50; ++i)
			{
				if(res[i])
				{
					c++;
					sb.append(" " + i);
				}
				res[i] = false;
			}
				out.print(c);
				out.println(sb.toString());
			break;
			}
		}
		out.flush();
	}
	
	static class SegmentTree {

		int n;
		int sTree[][];
		
		public SegmentTree(int N) {
			n = N;
			int sz = getSz(n);
			sTree = new int[sz][51];
		}
		
		private int getSz(int n) {
			int sz = 1;
			while(sz < n) sz <<= 1;
			return sz <<= 1;
		}
		
		void updatePoint(int idx, int val, int diff)
		{
			updatePoint(1, idx, 0, n - 1, val, diff);
		}

		void updatePoint(int node, int idx, int b, int e, int val, int diff) {
			if(b == e)
				sTree[node][val] += diff;
			else
			{
				sTree[node][val] += diff;
				int mid = (b + e >> 1);
				if(idx <= mid)
					updatePoint(node << 1, idx, b, mid, val, diff);
				else
					updatePoint(node << 1 | 1, idx, mid + 1, e, val, diff);
			}
		}
		
		void query(int lo, int hi)
		{
			query(1, 0, n - 1, lo, hi);
		}

		private void query(int node, int b, int e, int lo, int hi) {
			if(e < lo || b > hi) return;
			if(b >= lo && e <= hi)
			{
				for(int i = 1; i <= 50; ++i)
					if(sTree[node][i] > 0)
						res[i] = true;
			}
			else
			{
				int mid = (b + e >> 1);
				query(node << 1, b, mid, lo, hi);
				query(node << 1 | 1, mid + 1, e, lo, hi);
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