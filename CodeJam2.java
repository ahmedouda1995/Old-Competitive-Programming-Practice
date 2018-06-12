import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CodeJam2 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("tidy.out");

		int t = sc.nextInt(), cases = 1;
		long n;
		String s;
		
		while(t-- > 0) {
			n = sc.nextLong();
			
			while(true) {
				s = Long.toString(n);
				int min = s.length() - 1;
				int st = -1;
				
				for(int i = s.length() - 2; i >= 0; --i) {
					if(s.charAt(i) < s.charAt(min))
						min = i;
					else if(s.charAt(i) > s.charAt(min)) {
						st = i + 1;
						break;
					}
				}
				if(st == -1)
					break;
				else
					n = n - Long.parseLong(s.substring(st, s.length())) - 1;
			}
			
			out.printf("Case #%d: ", cases++);
			out.println(n);
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