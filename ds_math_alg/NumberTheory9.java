package ds_math_alg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class NumberTheory9 {
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int a, b, c;
		a = sc.nextInt();
		b = sc.nextInt();
		c = sc.nextInt();
		
		extendedEuclid(a, b);
		
		x *= (c / g);
		y *= (c / g);
		
		double lo = (1 - x) * 1.0 * g / b;
		double hi = (y - 1) * 1.0 * g / a;
		lo = Math.ceil(lo);
		hi = Math.floor(hi);
		out.println(((long)hi - (long)lo + 1) * 21 * 5);
		
		out.flush();
		out.close();
	}
	
    static long x, y, g;
    static void extendedEuclid(long a, long b) {
        if(b == 0) { x = 1; y = 0; g = a; return; }

        extendedEuclid(b, a % b);
        long nx = y;
        long ny = x - y * (a / b);

        x = nx; y = ny;
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
