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

public class SegmentTree2 {
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int n = sc.nextInt();
		long arr[] = new long[n];
		for(int i = 0; i < n; ++i) arr[i] = sc.nextLong();
		
		int q = sc.nextInt();
		int op, lo, hi, val;
		SegmentTree st = new SegmentTree(arr);
		while(q-- > 0)
		{
			op =sc.nextInt();
			if(op == 1)
			{
				lo = sc.nextInt() - 1;
				hi = sc.nextInt() - 1;
				out.println(st.query(lo, hi));
			}
			else
			{
				lo = sc.nextInt() - 1;
				hi = sc.nextInt() - 1;
				val = sc.nextInt();
				st.updateRange(lo, hi, val);
			}
		}
		
		out.flush();
		out.close();
	}
	
	static class Node
	{
		static final int MAX = 62;
		int arr[];
		
		public Node(long n) {
			arr = new int[MAX];
			int i = 0;
			while(n > 0)
			{
				if((n & 1) == 1)
					arr[i] = 1;
				i++;
				n >>= 1;
			}
		}
		
		public Node(int arr[])
		{
			this.arr = arr;
		}
		
		static int[] merge(Node a, Node b)
		{
			int arr[] = new int[MAX];
			for(int i = 0; i < MAX; ++i)
				arr[i] = a.arr[i] + b.arr[i];
			return arr;
		}
		
		void xor(long val, int size)
		{
			int i = 0;
			while(val > 0)
			{
				if((val & 1) == 1) arr[i] = size - arr[i];
				val >>= 1;
				i++;
			}
		}
		
		long val()
		{
			long ans = 0;
			long rem = 0, tmp;
			for(int i = 0; i < MAX; ++i)
			{
				tmp = arr[i] + rem;
				rem = tmp / 2;
				if(tmp % 2 == 1) ans |= (1L << i);
			}
			return ans;
		}
	}
	
	static class SegmentTree {

		int n;
		Node sTree[];
		long arr[], lazy[];
		
		public SegmentTree(long in[]) {
			n = in.length; arr = in;
			int sz = getSz(n);
			sTree = new Node[sz];
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
				sTree[node] = new Node(arr[b]);
			else
			{
				int mid = (b + e >> 1);
				build(node << 1, b, mid);
				build(node << 1 | 1, mid + 1, e);
				sTree[node] = new Node(Node.merge(sTree[node << 1], sTree[node << 1 | 1]));
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
				sTree[node].xor(val, e - b + 1);
				lazy[node] ^= val;
			}
			else
			{
				int mid = (b + e >> 1);
				propagate(node, b, mid, e);
				updateRange(node << 1, b, mid, lo, hi, val);
				updateRange(node << 1 | 1, mid + 1, e, lo, hi, val);
				sTree[node] = new Node(Node.merge(sTree[node << 1], sTree[node << 1 | 1]));
			}
		}

		private void propagate(int node, int b, int mid, int e) {
			sTree[node << 1].xor(lazy[node], mid - b + 1);
			lazy[node << 1] ^= lazy[node];
			sTree[node << 1 | 1].xor(lazy[node], e - mid);
			lazy[node << 1 | 1] ^= lazy[node];
			lazy[node] = 0;
		}
		
		long query(int lo, int hi)
		{
			return query(1, 0, n - 1, lo, hi).val();
		}

		private Node query(int node, int b, int e, int lo, int hi) {
			if(e < lo || b > hi) return new Node(0);
			if(b >= lo && e <= hi)
				return sTree[node];
			else
			{
				int mid = (b + e >> 1);
				propagate(node, b, mid, e);
				Node left = query(node << 1, b, mid, lo, hi);
				Node right = query(node << 1 | 1, mid + 1, e, lo, hi);
				return new Node(Node.merge(left, right));
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