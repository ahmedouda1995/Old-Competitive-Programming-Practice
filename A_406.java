import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class A_406 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int a, c;
		long b, d;
		
		a = sc.nextInt(); b = sc.nextInt();
		c = sc.nextInt(); d = sc.nextInt();
		
		
		if(b == d)
			out.println(b);
		else if((odd(b) && even(a) && even(d) && even(c))
		|| (odd(d) && even(c) && even(b) && even(a))
		|| a == c)
			out.println(-1);
		else {
			while(b != d) {
				if(b < d)
					b += a;
				else
					d += c;
			}
			out.println(b);
		}
		
		out.flush();
		out.close();
	}
	
	static boolean odd(long n) { return (n & 1) == 1; }
	static boolean even(long n) { return !odd(n); }
	
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