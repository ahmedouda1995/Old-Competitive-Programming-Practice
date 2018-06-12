package ch3_greedy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_12321_Gas_Stations {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int L, G; Pair a[];
		while((L = sc.nextInt()) != 0 | (G = sc.nextInt()) != 0){
			
			a = new Pair[G];
			
			for(int i = 0; i < G; ++i) {
				int x = sc.nextInt();
				int r = sc.nextInt();
				a[i] = new Pair(x - r, x + r);
			}
			
			Arrays.sort(a);

			boolean printed = false;
			int furthest = 0;
			int max = 0, result = 0;
			for(int i = 0; i < G && furthest < L; ++i) {
				if(a[i].start <= furthest) {
					max = Math.max(max, a[i].end);
				}
				else {
					result++;
					furthest = max;
					if(a[i].start <= furthest){
						max = Math.max(max, a[i].end);
					}
					else {
						out.println(-1);
						printed = true;
						break;
					}
				}
			}
			
			if(furthest < L && max >= L){
				result++;
				furthest = max;
			}
			
			if(!printed)
				if(furthest >= L)
					out.println(G - result);
				else
					out.println(-1);
		}
		
		out.flush();
		out.close();
	}
	
	static class Pair implements Comparable<Pair>{
		int start, end;
		
		public Pair(int start, int end) {
			this.start = start;
			this.end = end;
		}
		
		public int compareTo(Pair p){
			return this.start - p.start;
		}
		
		@Override
		public String toString() {
			return "(" + start + ", " + end + ")";
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