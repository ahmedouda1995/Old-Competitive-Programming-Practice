package stringAdHoc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_576_HaikuReview {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		String s;
		String tmp[];
		
		while(!(s = sc.nextLine()).equals("e/o/i")) {
			tmp = s.split("/");
			if(check(tmp[0]) != 5)
				out.println(1);
			else if(check(tmp[1]) != 7)
				out.println(2);
			else if(check(tmp[2]) != 5)
				out.println(3);
			else
				out.println("Y");
		}

		out.flush();
		out.close();
	}
	
	private static int check(String s) {
		return s.replaceAll("[^aeoiuy]+", " ").trim().split(" ").length;
		
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