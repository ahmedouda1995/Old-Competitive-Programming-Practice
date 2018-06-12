package ch3_Divide_and_Conquer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.StringTokenizer;


// CHOOSING PRECISION + BISECTION METHOD
// Learn http://www.algorithmist.com/index.php/UVa_10341 secant & newton methods

public class UVa_10341_Solve_It {
	
	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(new File("output.txt"));
		DecimalFormat f = new DecimalFormat("0.0000");
		String str;
		
		while((str = sc.nextLine()) != null){
			StringTokenizer st = new StringTokenizer(str);
			int p = Integer.parseInt(st.nextToken()), q = Integer.parseInt(st.nextToken());
			int r = Integer.parseInt(st.nextToken()), s = Integer.parseInt(st.nextToken());
			int t = Integer.parseInt(st.nextToken()), u = Integer.parseInt(st.nextToken());
			double a = check(0, p, q, r, s, t, u), b = check(1, p, q, r, s, t, u);
			if(a * b > 0)
				out.println("No solution");
			else
				out.println(f.format(solve(p, q, r, s, t, u)));
		}
		
		out.flush();
		out.close();
	}
	
	private static double solve(int p, int q, int r, int s, int t, int u) {
		double l = 0, h = 1, mid;
		//while(l + EPS < h) 
		//for(int i = 0; i < 14; ++i) also correct
		while(l + EPS < h){
			mid = (l + h) / 2;
			double ret = check(mid, p, q, r, s, t, u);
			double a = check(l, p, q, r, s, t, u);
			if(a * ret <= 0)
				h = mid;
			else
				l = mid;
		}
		return (l + h) / 2;
	}

	private static double check(double x, int p, int q, int r, int s, int t, int u){
		double res = 0.0;
		res += p * Math.exp(-x);
		res += q * Math.sin(x);
		res += r * Math.cos(x);
		res += s * Math.tan(x);
		res += t * x * x;
		res += u;
		return res;
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