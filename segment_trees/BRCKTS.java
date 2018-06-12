package segment_trees;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class BRCKTS {

	static int N;
	static char s[];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int m, next;
		
		for(int i = 1; i <= 10; ++i) {
			N = sc.nextInt();
			s = sc.nextLine().toCharArray();
			SegmentTree st = new SegmentTree();
			m = sc.nextInt();
			
			out.printf("Test %d:\n", i);
			while(m-- > 0) {
				next = sc.nextInt();
				if(next == 0)
					out.println(st.query()?"YES":"NO");
				else
					st.update(next - 1);
			}
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
		
		SegmentTree() {
			nodes = new STNode[getSTSize()];
			build(1, 0, N - 1);
		}

		private void build(int stIdx, int lo, int hi) {
			if(lo == hi) {
				nodes[stIdx] = new STNode();
				nodes[stIdx].assignLeaf(s[lo]);
			}
			else {
				int mid = (lo + hi) >> 1;
				int left = (stIdx << 1), right = left + 1;
				build(left, lo, mid);
				build(right, mid + 1, hi);
				nodes[stIdx] = new STNode();
				nodes[stIdx].merge(nodes[left], nodes[right]);
			}
		}

		boolean query() {
			return nodes[1].getVal();
		}

		void update(int idx) {
			update(1, 0, N - 1, idx);
		}
		
		private void update(int stIdx, int lo, int hi, int idx) {
			if(lo == hi) {
				if(s[lo] == '(') { nodes[stIdx].assignLeaf(')'); s[lo] = ')'; }
				else { nodes[stIdx].assignLeaf('('); s[lo] = '('; }
			}
			else {
				int mid = (lo + hi) >> 1;
				int left = (stIdx << 1), right = left + 1;
				if(idx > mid)
					update(right, mid + 1, hi, idx);
				else
					update(left, lo, mid, idx);
				nodes[stIdx].merge(nodes[left], nodes[right]);
			}
		}

		private int getSTSize() {
			int sz = 1;
			while(sz < N) sz <<= 1;
			return sz << 1;
		}
		
		
		static class STNode {
			int openBr, closedBr;
			
			void assignLeaf(char c) {
				if(c == '(') { openBr = 1; closedBr = 0; }
				else { openBr = 0; closedBr = 1; }
			}
			
			void merge(STNode left, STNode right) {
				int match = Math.min(left.openBr, right.closedBr);
				openBr = left.openBr + right.openBr - match;
				closedBr = left.closedBr + right.closedBr - match;
			}
			
			boolean getVal() { return openBr == 0 && closedBr == 0; }
		}
	}
}