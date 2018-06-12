package ch3_BruteForce_and_Backtracking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

// important

/* 
	1/k = 1/x + 1/y
	When x and y are equal, they are both 2k.
	1/k = 1/(2k) + 1/(2k)
	if x is larger than 2k, then y must be smaller than 2k.
	Since y can't be k, we only need to check y in [k+1, 2k]. 
	But how to compute x?
	1/x = 1/k - 1/y = (y-k) / (k*y)
	So if y-k divides k*y, then x = (k*y) / (y-k).

*/

public class UVa_10976_Fractions_Again {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		String s; int k;
		ArrayList<Integer> x = new ArrayList<Integer>(), y = new ArrayList<Integer>();
		while((s = sc.nextLine()) != null){
			k = Integer.parseInt(s);
			x.clear(); y.clear();
			int count = 0;
			for(int i = k + 1; i <= 2 * k; ++i){
				if((k * i) % (i - k) == 0){
					count++; x.add((k * i) / (i - k)); y.add(i);
				}
			}
			out.println(count);
			for (int i = 0; i < x.size(); i++) {
				out.println("1/" + k + " = " + "1/" + x.get(i) + " + " +  "1/" + y.get(i));
			}
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
