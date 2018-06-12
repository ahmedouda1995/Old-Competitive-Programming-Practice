package ds_math_alg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class SegmentTree3 {
	
	static int blue[];
	static int red[];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int n, q;
		n = sc.nextInt();
		q = sc.nextInt();
		
		blue = new int[n];
		red = new int[n];
		
		for(int i = 0; i < n; ++i)
		{
			blue[i] = sc.nextInt() % 2;
			red[i] = sc.nextInt() % 2;
		}
		
		SegmentTree st = new SegmentTree(n);
		char next;
		int lo, hi;
		
		while(q-- > 0)
		{
			next = sc.next().charAt(0);
			switch (next) {
			case 'a':
				out.println(st.query(0, n - 1));
				break;
			case 'i':
				lo = sc.nextInt() - 1;
				st.updatePoint(lo, true, sc.nextInt());
				break;
			case 'm':
				lo = sc.nextInt() - 1;
				st.updatePoint(lo, false, sc.nextInt());
				break;
			default:
				lo = sc.nextInt() - 1;
				hi = sc.nextInt() - 1;
				st.updateRange(lo, hi);
			}
		}
		
		out.flush();
	}

	static class SegmentTree {

		int n;
		Node sTree[];
		boolean lazy[];
		
		public SegmentTree(int N) {
			n = N;
			int sz = getSz(n);
			sTree = new Node[sz];
			lazy = new boolean[sz];
			build(1, 0, n - 1);
		}
		
		static class Node
		{
			int blueUps, redUps;
			int blueEvens, blueUpEvens;
			int redOdds, redUpOdds;
			
			public Node() {}
			
			int getValue() { return blueUpEvens + redUpOdds; }
			
			void flip()
			{
				blueUpEvens = blueEvens - blueUpEvens;
				redUpOdds = redOdds - redUpOdds;
				
				int n = redUps + blueUps;
				blueUps = n - blueUps;
				redUps = n - blueUps;
			}
			
			@Override
			public String toString() {
				return blueUpEvens + " " + redUpOdds;
			}
		}
		
		Node merge(Node left, Node right)
		{
			Node res = new Node();
			res.blueUps = left.blueUps + right.blueUps;
			res.redUps = left.redUps + right.redUps;
			res.blueEvens = left.blueEvens + right.blueEvens;
			res.redOdds = left.redOdds + right.redOdds;
			res.blueUpEvens = left.blueUpEvens + right.blueUpEvens;
			res.redUpOdds = left.redUpOdds + right.redUpOdds;
			return res;
		}
		
		private int getSz(int n) {
			int sz = 1;
			while(sz < n) sz <<= 1;
			return sz <<= 1;
		}

		void build(int node, int b, int e) {
			if(b == e)
			{
				sTree[node] = new Node();
				sTree[node].blueEvens = (blue[b] % 2 == 0)?1:0;
				sTree[node].redOdds = (red[b] % 2 == 1)?1:0;
				sTree[node].redUpOdds = 0;
				sTree[node].blueUpEvens = sTree[node].blueEvens;
				sTree[node].redUps = 0;
				sTree[node].blueUps = 1;
			}
			else
			{
				int mid = (b + e >> 1);
				build(node << 1, b, mid);
				build(node << 1 | 1, mid + 1, e);
				sTree[node] = merge(sTree[node << 1], sTree[node << 1 | 1]);
			}
		}
		
		void updatePoint(int idx, boolean add, int val)
		{
			updatePoint(1, idx, 0, n - 1, add, val);
		}

		void updatePoint(int node, int idx, int b, int e, boolean add, int val) {
			if(b == e)
			{
				if(add)
				{
					if(sTree[node].blueUps == 1)
					{
						blue[b] = (blue[b] + val) % 2;
						sTree[node].blueEvens = (blue[b] % 2 == 0)?1:0;
						sTree[node].blueUpEvens = sTree[node].blueEvens;
					}
					else
					{
						red[b] = (red[b] + val) % 2;
						sTree[node].redOdds = (red[b] % 2 == 1)?1:0;
						sTree[node].redUpOdds = sTree[node].redOdds;
					}
				}
				else
				{
					if(sTree[node].blueUps == 1)
					{
						blue[b] = (int) ((1l * blue[b] * val) % 2L);
						sTree[node].blueEvens = (blue[b] % 2 == 0)?1:0;
						sTree[node].blueUpEvens = sTree[node].blueEvens;
					}
					else
					{
						red[b] = (int) ((1l * red[b] * val) % 2L);
						sTree[node].redOdds = (red[b] % 2 == 1)?1:0;
						sTree[node].redUpOdds = sTree[node].redOdds;
					}
				}
			}
			else
			{
				int mid = (b + e >> 1);
				propagate(node, b, mid, e);
				if(idx <= mid)
					updatePoint(node << 1, idx, b, mid, add, val);
				else
					updatePoint(node << 1 | 1, idx, mid + 1, e, add, val);
				sTree[node] = merge(sTree[node << 1], sTree[node << 1 | 1]);
			}
		}
		
		void updateRange(int lo, int hi)
		{
			updateRange(1, 0, n - 1, lo, hi);
		}
		
		void updateRange(int node, int b, int e, int lo, int hi)
		{
			if(e < lo || b > hi) return;
			if(b >= lo && e <= hi)
			{
				lazy[node] ^= true;
				sTree[node].flip();
			}
			else
			{
				int mid = (b + e >> 1);
				propagate(node, b, mid, e);
				updateRange(node << 1, b, mid, lo, hi);
				updateRange(node << 1 | 1, mid + 1, e, lo, hi);
				sTree[node] = merge(sTree[node << 1], sTree[node << 1 | 1]);
			}
		}

		private void propagate(int node, int b, int mid, int e) {
			if(lazy[node])
			{
				sTree[node << 1].flip();
				sTree[node << 1 | 1].flip();
			}
			lazy[node << 1] ^= lazy[node];
			lazy[node << 1 | 1] ^= lazy[node];
			lazy[node] = false;
		}
		
		int query(int lo, int hi)
		{
			return sTree[1].getValue();
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