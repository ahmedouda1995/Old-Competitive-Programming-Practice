package ds_math_alg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Advanced3 {
	
	static final int MAX = (int) 1e5;
	static long[] totalSatisfaction;
	static int[] experience, satisfaction, lo, hi;
	static Event events[];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int arr[] = new int[MAX + 1];
		Arrays.fill(arr, -1);
		arr[1] = 0;
		SegmentTree st = new SegmentTree(arr);
		
		int n = sc.nextInt();
		int m = (n << 1);
		
		totalSatisfaction = new long[n];
		satisfaction = new int[n];
		experience = new int[n];
		events = new Event[m];
		lo = new int[n];
		hi = new int[n];
		
		for(int i = 0; i < n; ++i)
		{
			events[i] = new Event(i, sc.nextInt(), -1);
			events[i + n] = new Event(i, sc.nextInt(), 1);
			lo[i] = sc.nextInt();
			hi[i] = sc.nextInt();
			experience[i] = sc.nextInt();
			satisfaction[i] = sc.nextInt();
		}
		
		Arrays.sort(events);
		
		Event next;
		long ans = 0;
		
		for(int i = 0; i < m; ++i)
		{
			next = events[i];
			if(next.start == -1)
			{
				if(st.query(lo[next.idx], hi[next.idx]) > -1)
					totalSatisfaction[next.idx] = st.query(lo[next.idx], hi[next.idx]) + satisfaction[next.idx];
				else
					totalSatisfaction[next.idx] = -1;
			}
			else
			{
				ans = Math.max(ans, totalSatisfaction[next.idx]);
				if(totalSatisfaction[next.idx] > -1)
					st.updatePoint(experience[next.idx], totalSatisfaction[next.idx]);
			}
		}
		
		out.println(ans);
		
		out.flush();
		out.close();
	}
	
	static class Event implements Comparable<Event> {

		int time, idx;
		int start;
		
		public Event(int idx, int time, int start) {
			this.time = time;
			this.start = start;
			this.idx = idx;
		}
		
		@Override
		public int compareTo(Event e) {
			if(time == e.time)
				return start - e.start;
			else
				return time - e.time;
		}
	}
	
	static class SegmentTree {

		int n, arr[];
		long sTree[];
		
		public SegmentTree(int in[]) {
			n = in.length; arr = in;
			int sz = getSz(n);
			sTree = new long[sz];
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
				sTree[node] = Math.max(sTree[node << 1], sTree[node << 1 | 1]);
			}
		}
		
		void updatePoint(int idx, long val)
		{
			updatePoint(1, idx, 0, n - 1, val);
		}

		void updatePoint(int node, int idx, int b, int e, long val) {
			if(b == e) sTree[node] = Math.max(sTree[node], val);
			else
			{
				int mid = (b + e >> 1);
				if(idx <= mid)
					updatePoint(node << 1, idx, b, mid, val);
				else
					updatePoint(node << 1 | 1, idx, mid + 1, e, val);
				sTree[node] = Math.max(sTree[node << 1], sTree[node << 1 | 1]);
			}
		}
		
		long query(int lo, int hi)
		{
			return query(1, 0, n - 1, lo, hi);
		}

		private long query(int node, int b, int e, int lo, int hi) {
			if(e < lo || b > hi) return -1;
			if(b >= lo && e <= hi)
				return sTree[node];
			else
			{
				int mid = (b + e >> 1);
				long left = query(node << 1, b, mid, lo, hi);
				long right = query(node << 1 | 1, mid + 1, e, lo, hi);
				return Math.max(left, right);
			}
		}
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
}