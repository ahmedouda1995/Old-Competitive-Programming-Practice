package segment_trees;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class XeniaandBitOperations {

	static int height;
	static int N;
	static int arr[];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out  = new PrintWriter(System.out);
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		height = Integer.parseInt(st.nextToken());
		N = 1 << height;
		int m = Integer.parseInt(st.nextToken());
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; ++i) arr[i] = Integer.parseInt(st.nextToken());
		
		SegmentTree seg = new SegmentTree();
		
		while(m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken()) - 1;
			int val = Integer.parseInt(st.nextToken());
			seg.update(idx, val);
			out.println(seg.query());
		}
		br.close();
		out.flush();
		out.close();
	}
	
	static class SegmentTree {
		int nodes[];
		boolean isEven;
		
		public SegmentTree() {
			nodes = new int[N << 1];
			isEven = (height & 1) == 0;
			build(1, 0, N - 1, isEven);
		}

		private void build(int stIdx, int lo, int hi, boolean even) {
			if(lo == hi) nodes[stIdx] = arr[lo];
			else {
				int mid = (lo + hi) >> 1;
				int left = stIdx << 1, right = left | 1;
				build(left, lo, mid, !even);
				build(right, mid + 1, hi, !even);
				if(even) nodes[stIdx] = nodes[left] ^ nodes[right];
				else nodes[stIdx] = nodes[left] | nodes[right];
			}
		}
		
		int query() { return nodes[1]; }
		
		void update(int idx, int val) {
			update(1, 0, N - 1, idx, val, isEven);
		}

		private void update(int stIdx, int lo, int hi, int idx, int val, boolean even) {
			if(lo == hi) nodes[stIdx] = val;
			else {
				int mid = (lo + hi) >> 1;
				int left = (stIdx << 1), right = left | 1;
				if(idx > mid)
					update(right, mid + 1, hi, idx, val, !even);
				else
					update(left, lo, mid, idx, val, !even);
				
				if(even) nodes[stIdx] = nodes[left] ^ nodes[right];
				else nodes[stIdx] = nodes[left] | nodes[right];
			}
		}
	}
}
