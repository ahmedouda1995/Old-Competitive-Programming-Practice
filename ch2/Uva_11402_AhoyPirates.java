package ch2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Uva_11402_AhoyPirates {

	static int arr[] = new int[1024000];
	static int N;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt(), times, cases = 1;
		String s;
		StringBuilder sb;
		
		while(t-- > 0) {
			sb = new StringBuilder();
			int m = sc.nextInt();
			int i = 0, j;
			N = 0;
			while(m-- > 0) {
				times = sc.nextInt();
				s = sc.nextLine();
				N += times * s.length();
				while(times-- > 0) {
					for(j = 0; j < s.length(); ++j) {
						arr[i++] = s.charAt(j) - '0';
					}
				}
			}
			
			m = sc.nextInt();
			SegTree st = new SegTree();
			
			char c;
			int a, b;
			sb.append("Case ").append(cases++).append(":\n");
			int q = 1;
			
			while(m-- > 0) {
				c = sc.next().charAt(0);
				a = sc.nextInt(); b = sc.nextInt();
				if(c == 'S') {
					sb.append(String.format("Q%d: %d\n", q++, st.query(a, b)));
				}
				else
					st.update(a, b, c);
			}
			
			out.print(sb.toString());
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
		
		int nodes[];
		char lazy[];
		
		public SegTree() {
			int sz = getSTSize();
			nodes = new int[sz];
			lazy = new char[sz];
			build(1, 0, N - 1);
		}
		
		void update(int lo, int hi, char c) {
			update(1, 0, N - 1, lo, hi, c);
		}
		
		private void update(int stIdx, int left, int right, int lo, int hi, char c) {
			if(lazy[stIdx] != 'X') {
				if(lazy[stIdx] == 'F')
					nodes[stIdx] = right - left + 1;
				else if(lazy[stIdx] == 'E')
					nodes[stIdx] = 0;
				else
					nodes[stIdx] = (right - left + 1) - nodes[stIdx];
				
				if(left != right) {
					handle(stIdx << 1, lazy[stIdx]);
					handle((stIdx << 1) | 1, lazy[stIdx]);
				}
				
				lazy[stIdx] = 'X';
			}
			
			if(left > hi || right < lo)
				return;
			
			if(left >= lo && right <= hi) {
				if(c == 'F')
					nodes[stIdx] = right - left + 1;
				else if(c == 'E')
					nodes[stIdx] = 0;
				else
					nodes[stIdx] = (right - left + 1) - nodes[stIdx];
				
				if(left != right) {
					handle(stIdx << 1, c);
					handle((stIdx << 1) | 1, c);
				}
			}
			else {
				int mid = (left + right) >> 1;
				int l = (stIdx << 1), r = l | 1;
				update(l, left, mid, lo, hi, c);
				update(r, mid + 1, right, lo, hi, c);
				nodes[stIdx] = nodes[l] + nodes[r];
			}
		}

		int query(int lo, int hi) {
			return query(1, 0, N - 1, lo, hi);
		}
		
		private int query(int stIdx, int left, int right, int lo, int hi) {
			if(lazy[stIdx] != 'X') {
				if(lazy[stIdx] == 'F')
					nodes[stIdx] = right - left + 1;
				else if(lazy[stIdx] == 'E')
					nodes[stIdx] = 0;
				else
					nodes[stIdx] = (right - left + 1) - nodes[stIdx];
				
				if(left != right) {
					handle(stIdx << 1, lazy[stIdx]);
					handle((stIdx << 1) + 1, lazy[stIdx]);
				}
				
				lazy[stIdx] = 'X';
			}
			
			if(left > hi || right < lo)
				return 0;
			if(left >= lo && right <= hi)
				return nodes[stIdx];
			
			int mid = (left + right) >> 1;
			int l = (stIdx << 1), r = l | 1;
			
			int leftRes = query(l, left, mid, lo, hi);
			int rightRes = query(r, mid + 1, right, lo, hi);
			
			return leftRes + rightRes;
		}

		private void handle(int i, char c) {
			if(c == 'F' || c == 'E')
				lazy[i] = c;
			else {
				if(lazy[i] == 'I')
					lazy[i] = 'X';
				else if(lazy[i] == 'X')
					lazy[i] = 'I';
				else if(lazy[i] == 'F')
					lazy[i] = 'E';
				else
					lazy[i] = 'F';
			}
		}

		private void build(int stIdx, int lo, int hi) {
			if(lo == hi) {
				nodes[stIdx] = arr[lo];
				lazy[stIdx] = 'X';
			}
			else {
				int mid = (lo + hi) >> 1;
				int left = stIdx << 1, right = left | 1;
				
				build(left, lo, mid);
				build(right, mid + 1, hi);
				lazy[stIdx] = 'X';
				nodes[stIdx] = nodes[left] + nodes[right];
			}
		}

		int getSTSize() {
			return (int)(2 * Math.pow(2, Math.ceil((Math.log(N) / Math.log(2)))));
		}
	}
	
}