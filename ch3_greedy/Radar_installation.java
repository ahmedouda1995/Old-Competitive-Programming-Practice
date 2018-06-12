package ch3_greedy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Radar_installation {

	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int n, d;
		Pair [] a;
		boolean falseInput = false;
		int cases = 1;
		while(true){
			falseInput = false;
			n = sc.nextInt();
			d = sc.nextInt();
			if(n == 0 && d == 0)
				break;
			a = new Pair[n];
			for(int i=0;i<n;i++){
				int x = sc.nextInt();
				int y = sc.nextInt();
				if(y > d || y < 0){
					falseInput = true;
				}
				double dx = Math.sqrt((double)d * d - (double)y * y);
				a[i] = new Pair((double)x - dx, (double)x + dx);
			}
			sc.nextLine();
			if(falseInput)
				out.println("Case " + cases + ": " + -1);
			else {
					Arrays.sort(a);
					int count = 0;
					
				
					out.println("Case " + cases + ": " + count);
				}
			cases++;
		}
		
		out.flush();
		out.close();
	}

	static class Pair implements Comparable<Pair>{
		double x, y;
		
		public Pair(double x, double y) {
			this.x = x;
			this.y = y;
		}
		
		@Override
		public String toString() {
			return "(" + x + "," + y + ")";
		}

		@Override
		public int compareTo(Pair p) {
			return Double.compare(this.x, p.x);
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
//3 2
//1 2
//-3 1
//2 1
//
//1 2
//0 2
//
//0 0