package ds_math_alg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class NumberTheory2 {
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt();
		int n, 	fBegin, fJump, gBegin, gJump, c;
		
		while(t-- > 0)
		{
			n = sc.nextInt();
			fBegin = sc.nextInt();
			fJump = sc.nextInt() + 1;
			gBegin = sc.nextInt();
			gJump = sc.nextInt() + 1;
			
			if(fBegin > gBegin)
			{
				int tmp;
				tmp = fBegin; fBegin = gBegin; gBegin = tmp;
				tmp = fJump; fJump = gJump; gJump = tmp;
			}
			c = (gBegin - fBegin);
			// fJump*X + gJump*Y = (gBegin - fBegin)
			// fJump*X + fBegin = gJump*Y + gBegin
			extendedEuclid(fJump, gJump);
			
			x *= (c / g);
			y *= (c / g);
			if(c % g != 0)
				out.println(0);
			else
			{
				double factor = Math.ceil((1.0 * gBegin - fBegin) / fJump);
				int k = (int) Math.ceil((factor - x) * g / gJump);
				long firstPlaceToMeet = fBegin + (x + gJump / g * k) * fJump;

				// TODO
				if(firstPlaceToMeet > n)
					out.println(0);
				else
					out.println(1 + ((n - firstPlaceToMeet) / lcm(fJump, gJump)));
			}
		}
		
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
	static long gcd(long a, long b) { return b == 0?a:gcd(b, a % b); }
	static long lcm(long a, long b) { return (1L * a * b / gcd(a, b)); }

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