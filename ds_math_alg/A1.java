package ds_math_alg;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class A1 {
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int len = sc.nextInt();
		int n = sc.nextInt();
		String cur = sc.nextLine();
		out.println(cur);
		n--;
		String next;
		while(n-- > 0)
		{
			next = sc.nextLine();
			String tmp = "";
			for(int i = 0; i < cur.length(); ++i)
				if(cur.charAt(i) == next.charAt(i))
					tmp += cur.charAt(i);
				else
					break;
			cur = tmp;
			out.println((cur.length() == 0?".":cur));
		}
		
		out.flush();
		out.close();
	}
	
	static class Pair implements Comparable<Pair>
	{
		int id;
		double angle;
		
		public Pair(int x, int y, int _id) {
			angle = Math.atan2(y, x);
			if(angle < 0.0) angle += 2 * Math.PI;
			id = _id;
		}
		@Override
		public int compareTo(Pair p) {
			return Double.compare(angle, p.angle);
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