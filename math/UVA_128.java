package math;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVA_128 {

	static final int MOD = 34943;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		//Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);

		String s;
		char in[];
		
		while(!(s = sc.nextLine()).equals("#"))
		{
			in = s.toCharArray();
			long rem = 0;
			
			for(int i = 0; i < in.length; ++i)
				rem = (rem * (1 << 8) % MOD + in[i]) % MOD;
			
			rem = (rem * (1 << 16)) % MOD;
			
			rem = (MOD - rem) % MOD;
			
			String tmp = "0000" + Long.toHexString(rem);
			tmp = tmp.substring(tmp.length() - 4, tmp.length());
			
			in  = tmp.toCharArray();
			
			for(int i = 0; i < 4; ++i)
				if(in[i] >= 'a' && in[i] <= 'z') in[i] = (char) (in[i] - ('a' - 'A'));
			
			out.printf("%s%s %s%s\n", in[0], in[1], in[2], in[3]);
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
