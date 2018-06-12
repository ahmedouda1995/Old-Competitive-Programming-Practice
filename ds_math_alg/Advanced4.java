package ds_math_alg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Advanced4 {
	
	static final double EPS = 1e-7;
	static int f;
	static int c[];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int n;
		n = sc.nextInt();
		f = sc.nextInt();
		
		c = new int[n];
		for(int i = 0; i < n; ++i)
			c[i] = sc.nextInt();
		
		double lo = 0, hi = 1e9, mid, ans = -1;
		
		while((hi - lo) > EPS)
		{
			mid = (lo + hi) / 2;
			if(can(mid))
				ans = hi = mid;
			else
				lo = mid;
		}
		out.printf("%.6f\n", ans);
		
		out.flush();
		out.close();
	}
	
	private static boolean can(double time) {
		
		double rem = 0.0;
		for(int i = 0; i < c.length; ++i)
		{
			if(rem + f * time < c[i])
				return false;
			rem += f * time - c[i];
		}
		return true;
	}
	
	static class Scanner 
	{
		StringTokenizer st; BufferedReader br;
		Scanner(InputStream system) {br = new BufferedReader(new InputStreamReader(system));}
		Scanner(String file) throws FileNotFoundException {br = new BufferedReader(new FileReader(file));}
		String next() throws IOException {
			while (st == null || !st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
			return st.nextToken(); }
		String nextLine()throws IOException{return br.readLine();}
		int nextInt() throws IOException {return Integer.parseInt(next());}
		double nextDouble() throws IOException {return Double.parseDouble(next());}
		char nextChar()throws IOException{return next().charAt(0);}
		Long nextLong()throws IOException{return Long.parseLong(next());}
		boolean ready() throws IOException{return br.ready();}
	}
}