import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Check {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt(), cases = 1;
		long n;
		String s;
		
		while(t-- > 0) {
			n = sc.nextLong();
			
			while(f(n)) {
				n--;
			}
			
			out.printf("Case #%d: ", cases++);
			out.println(n);
		}
		
		out.flush();
		out.close();
	}
	
	static boolean f(long n) {
		String s = n + "";
		
		char max = s.charAt(0);
		
		for(int i = 1; i < s.length(); ++i) {
			if(s.charAt(i) > max) max = s.charAt(i);
			else
				if(s.charAt(i) < max)
					return true;
		}
		return false;
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