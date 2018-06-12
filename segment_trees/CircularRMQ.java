package segment_trees;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import static java.lang.Math.min;

public class CircularRMQ {
	 
	static final int INF = (int) 1e9;
	static int N;
	static int arr[];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out  = new PrintWriter(System.out);
		
		N = Integer.parseInt(br.readLine());
		arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		for(int i = 0; i < N; ++i) arr[i] = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(br.readLine());
		
		SegmentTree seg = new SegmentTree();
		
		while(m-- > 0) {
			st = new StringTokenizer(br.readLine());
			if(st.countTokens() == 2) {
				int lo = Integer.parseInt(st.nextToken());
				int hi = Integer.parseInt(st.nextToken());
				out.println(seg.query(lo, hi));
			}
			else {
				int lo = Integer.parseInt(st.nextToken());
				int hi = Integer.parseInt(st.nextToken());
				int val = Integer.parseInt(st.nextToken());
				seg.update(lo, hi, val);
			}
		}
		br.close();
		out.flush();
		out.close();
	}
	
	static class SegmentTree {
		long [] nodes, lazy;
		
		public SegmentTree() {
			int sz = getSTSize();
			nodes = new long[sz];
			lazy = new long[sz];
			build(1, 0, N - 1);
		}

		private void build(int stIdx, int lo, int hi) {
			if(lo == hi) nodes[stIdx] = arr[lo];
			else {
				int mid = (lo + hi) >> 1;
				int left = (stIdx << 1), right = left + 1;
				build(left, lo, mid);
				build(right, mid + 1, hi);
				nodes[stIdx] = min(nodes[left], nodes[right]);
			}
		}

		void update(int lo, int hi, int diff) {
			if(lo > hi) {
				int lo1 = lo, hi1 = N - 1;
				int lo2 = 0, hi2 = hi;
				update(1, 0, N - 1, lo1, hi1, diff);
				update(1, 0, N - 1, lo2, hi2, diff);
			}
			else
				update(1, 0, N - 1, lo, hi, diff);
		}
		
		private void update(int stIdx, int left, int right, int lo, int hi, int diff) {
			if(lazy[stIdx] != 0) {
				nodes[stIdx] += lazy[stIdx];
				
				if(left != right) {
					lazy[stIdx << 1] += lazy[stIdx];
					lazy[(stIdx << 1) + 1] += lazy[stIdx];
				}
				lazy[stIdx] = 0;
			}
			
			if(lo > right || hi < left || left > right)
				return;
			
			int mid = (left + right) >> 1;
					
			if(left >= lo && right <= hi) {
				nodes[stIdx] += diff;
				
				if(left != right) {
					lazy[stIdx << 1] += diff;
					lazy[(stIdx << 1) + 1] += diff;
				}
				return;
			}
			
			update((stIdx << 1), left, mid, lo, hi, diff);
			update((stIdx << 1) + 1, mid + 1, right, lo, hi, diff);
			nodes[stIdx] = min(nodes[(stIdx << 1)], nodes[(stIdx << 1) + 1]);
		}

		long query(int lo, int hi) {
			if(lo > hi) {
				int lo1 = lo, hi1 = N - 1;
				int lo2 = 0, hi2 = hi;
				return min(query(1, 0, N - 1, lo1, hi1), query(1, 0, N - 1, lo2, hi2));
			}
			else
				return query(1, 0, N - 1, lo, hi);
		}
		
		private long query(int stIdx, int left, int right, int lo, int hi) {
			if(lazy[stIdx] != 0) {
				nodes[stIdx] += lazy[stIdx];
				
				if(left != right) {
					lazy[stIdx << 1] += lazy[stIdx];
					lazy[(stIdx << 1) + 1] += lazy[stIdx];
				}
				lazy[stIdx] = 0;
			}
			
			if(lo > right || hi < left || left > right)
				return INF;
			
			if(lo <= left && hi >= right)
				return nodes[stIdx];
			
			int mid = (left + right) >> 1;
					
			long leftRes = query((stIdx << 1), left, mid, lo, hi);
			long rightRes = query((stIdx << 1) + 1, mid + 1, right, lo, hi);
			
			return min(leftRes, rightRes);
		}

		private int getSTSize() {
			int sz = 1;
			while(sz < N) sz <<= 1;
			return sz << 1;
		}
	}
}
