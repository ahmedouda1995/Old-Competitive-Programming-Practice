package sssp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class UVa_10067_PlayingwithWheels {

	static TreeSet<Integer> states = new TreeSet<Integer>();
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		int s1, s2, s3, s4;
		int e1, e2, e3, e4;
		
		while(t-- > 0) {
			states.clear();
			
			s1 = sc.nextInt(); s2 = sc.nextInt(); s3 = sc.nextInt(); s4 = sc.nextInt();
			e1 = sc.nextInt(); e2 = sc.nextInt(); e3 = sc.nextInt(); e4 = sc.nextInt();
			
			int finalState = getState(e1, e2, e3, e4);
			
			saveState(s1, s2, s3, s4);
			
			Queue<Integer> q = new LinkedList<Integer>();
			
			q.offer(s1); q.offer(s2); q.offer(s3); q.offer(s4); q.offer(0);
			
			int m = sc.nextInt();
			
			while(m-- > 0) {
				s1 = sc.nextInt(); s2 = sc.nextInt(); s3 = sc.nextInt(); s4 = sc.nextInt();
				saveState(s1, s2, s3, s4);
			}
			
			int dist = 0;
			boolean found = false;
			int tmp;
			
			while(!q.isEmpty()) {
				s1 = q.poll(); s2 = q.poll(); s3 = q.poll(); s4 = q.poll();
				dist = q.poll();
				
				if(getState(s1, s2, s3, s4) == finalState) {
					found = true;
					break;
				}
				
				tmp = getState((s1 + 1) % 10, s2, s3, s4);
				if(!states.contains(tmp)) {
					q.offer((s1 + 1) % 10); q.offer(s2); q.offer(s3); q.offer(s4);
					q.offer(dist + 1); states.add(tmp);
				}
				
				tmp = getState((s1 - 1 + 10) % 10, s2, s3, s4);
				if(!states.contains(tmp)) {
					q.offer((s1 - 1 + 10) % 10); q.offer(s2); q.offer(s3); q.offer(s4);
					q.offer(dist + 1); states.add(tmp);
				}
				
				tmp = getState(s1, (s2 + 1) % 10, s3, s4);
				if(!states.contains(tmp)) {
					q.offer(s1); q.offer((s2 + 1) % 10); q.offer(s3); q.offer(s4);
					q.offer(dist + 1); states.add(tmp);
				}
				
				tmp = getState(s1, (s2 - 1 + 10) % 10, s3, s4);
				if(!states.contains(tmp)) {
					q.offer(s1); q.offer((s2 - 1 + 10) % 10); q.offer(s3); q.offer(s4);
					q.offer(dist + 1); states.add(tmp);
				}
				
				tmp = getState(s1, s2, (s3 + 1) % 10, s4);
				if(!states.contains(tmp)) {
					q.offer(s1); q.offer(s2); q.offer((s3 + 1) % 10); q.offer(s4);
					q.offer(dist + 1); states.add(tmp);
				}
				
				tmp = getState(s1, s2, (s3 - 1 + 10) % 10, s4);
				if(!states.contains(tmp)) {
					q.offer(s1); q.offer(s2); q.offer((s3 - 1 + 10) % 10); q.offer(s4);
					q.offer(dist + 1); states.add(tmp);
				}
				
				tmp = getState(s1, s2, s3, (s4 + 1) % 10);
				if(!states.contains(tmp)) {
					q.offer(s1); q.offer(s2); q.offer(s3); q.offer((s4 + 1) % 10);
					q.offer(dist + 1); states.add(tmp);
				}
				
				tmp = getState(s1, s2, s3, (s4 - 1 + 10) % 10);
				if(!states.contains(tmp)) {
					q.offer(s1); q.offer(s2); q.offer(s3); q.offer((s4 - 1 + 10) % 10);
					q.offer(dist + 1); states.add(tmp);
				}
			}
			
			if(found)
				out.println(dist);
			else
				out.println(-1);
		}
		
		out.flush();
		out.close();
	}

	private static int getState(int s1, int s2, int s3, int s4) {
		int s = 0;
		s |= (s1 << (0 * 4));
		s |= (s2 << (1 * 4));
		s |= (s3 << (2 * 4));
		s |= (s4 << (3 * 4));
		return s;
	}
	
	private static void saveState(int s1, int s2, int s3, int s4) {
		int s = 0;
		s |= (s1 << (0 * 4));
		s |= (s2 << (1 * 4));
		s |= (s3 << (2 * 4));
		s |= (s4 << (3 * 4));
		states.add(s);
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