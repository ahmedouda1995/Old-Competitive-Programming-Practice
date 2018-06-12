package ch3_greedy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_10718_Bit_Mask {

	// not solved by myself - i am still weak at biwise operations
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		String s; long N, L, U, ans;
		
		while((s =sc.nextLine()) != null) {
			StringTokenizer st = new StringTokenizer(s);
			N = Long.parseLong(st.nextToken());
			L = Long.parseLong(st.nextToken());
			U = Long.parseLong(st.nextToken());
			
			ans = 0;
			
			for(int i=0;i <= 31;++i) {
				
				long state = (N & (1L << (31 - i)));
				
				if(state > 0) {
					long temp = state - 1;
				    temp |= ans;
				    if(temp < L){
				     ans |= state;
				    }
				}
				else {
					long temp = ans;
				    temp |= (1L << (31-i));
				    if(temp <= U){
				     ans = temp;
				    }
				}
			}
			out.println(ans);
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