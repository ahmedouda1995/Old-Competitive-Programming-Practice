package ch2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// have to use long, and use heap to always add the smallest 2 items either sum and a new item or two new items and sum is down the heap

public class Uva_10954_add_all {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int n;
		PriorityQueue<Long> q = new PriorityQueue<Long>();
		while((n = sc.nextInt()) != 0){
			for (int i = 0; i < n; i++) q.offer(sc.nextLong());
			long cost = 0, sum = 0;
			while(! q.isEmpty()){
				sum = q.poll();
				if(q.isEmpty())
					break;
				sum += q.poll();
				cost += sum;
				q.offer(sum);
			}out.println(cost);
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
}