package segment_trees;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class UVa_10909_LuckyNumber {

	static int N = 2000001;
	static int arr[] = new int[N];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		for(int i = 1; i < 2000000; i += 2) arr[i] = 1;
		
		SegmentTree st = new SegmentTree();
		
		int available = 1000_000, removed, next = 2;
		
		while(next <= available) {
			int kth = st.getKth(next);
			
			int i = 1;
			removed = 0;
			
			while(i * kth - removed <= available) {
				st.deleteKth(i * kth - removed);
				available--; i++; removed++;
			}
			next++;
		}
		
		ArrayList<Integer> a = new ArrayList<Integer>();
		
		for(int i = 1; i < 2000000; ++i)
			if(arr[i] == 1) a.add(i);
		
		int n, i;
		
		while(sc.ready()) {
			n = sc.nextInt();
			
			if((n & 1) == 1)
				out.printf("%d is not the sum of two luckies!\n", n);
			else {
				i = n / 2;
				
				int pos = Collections.binarySearch(a, i);
				
				if(pos < 0) pos = -(pos + 1) - 1;
				
				while(pos >= 0 && !(arr[a.get(pos)] == 1 && arr[n - a.get(pos)] == 1)) pos--;
				
				if(pos >= 0 && arr[a.get(pos)] == 1 && arr[n - a.get(pos)] == 1)
					out.printf("%d is the sum of %d and %d.\n", n, a.get(pos), n - a.get(pos));
				else
					out.printf("%d is not the sum of two luckies!\n", n);
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
		
		int nodes[];
		
		public SegmentTree() {
			nodes = new int[1 << 22];
			build(1, 0, N - 1);
		}

		private void build(int stIdx, int lo, int hi) {
			if(lo == hi) nodes[stIdx] = arr[lo];
			else {
				int mid = (lo + hi) >> 1;
				int left = stIdx << 1, right = left | 1;
				
				build(left, lo, mid);
				build(right, mid + 1, hi);
				
				nodes[stIdx] = nodes[left] + nodes[right];
			}
		}
		
		int getKth(int k) {
			return getKth(1, 0, N - 1, k);
		}
		
		private int getKth(int stIdx, int lo, int hi, int k) {
			if(lo == hi) return lo;
			
			int mid = (lo + hi) >> 1;
			int left = stIdx << 1, right = left | 1;
			
			if(nodes[left] >= k) return getKth(left, lo, mid, k);
			else return getKth(right, mid + 1, hi, k - nodes[left]);
		}

		void deleteKth(int k) {
			deleteKth(1, 0, N - 1, k);
		}

		private void deleteKth(int stIdx, int lo, int hi, int k) {
			if(lo == hi) {
				nodes[stIdx] = 0;
				arr[lo] = 0;
			}
			else {
				int mid = (lo + hi) >> 1;
				int left = stIdx << 1, right = left | 1;
				
				if(nodes[left] >= k) deleteKth(left, lo, mid, k);
				else deleteKth(right, mid + 1, hi, k - nodes[left]);
				
				nodes[stIdx] = nodes[left] + nodes[right];
			}
		}
	}
}