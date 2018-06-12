package math;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVA_10281 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		String [] in, tmp;
		
		double speed = 0.0;
		double elapsed = 0.0;
		double prev = 0;
		
		while(sc.ready())
		{
			in = sc.nextLine().split(" ");
			
			tmp = in[0].split(":");
			double t = Double.parseDouble(tmp[0]) * 3600;
			t += Double.parseDouble(tmp[1]) * 60;
			t += Double.parseDouble(tmp[2]);
			elapsed += (t - prev) / 3600.0 * speed;
			
			if(in.length == 1)
				out.printf("%s %.2f km\n", in[0], elapsed);
			else
				speed = Double.parseDouble(in[1]);
			
			prev = t;
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
