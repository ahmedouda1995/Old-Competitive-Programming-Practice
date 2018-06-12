package ch3_greedy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVA_11389_The_Bus_Driver_Problem {

	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int n, d, r, result;
		int [] mor, aft;
		
		while(true){
			n = sc.nextInt();
			d = sc.nextInt();
			r = sc.nextInt();
			if(n == 0 && d == 0 && r == 0)
				break;
			mor = new int[n]; aft = new int[n];
			
			for (int i = 0; i < mor.length; i++) 
				mor[i] = sc.nextInt();
			
			for (int i = 0; i < aft.length; i++) 
				aft[i] = sc.nextInt();
			Arrays.sort(mor); Arrays.sort(aft);
			result = 0;
			
			for (int i = 0, j = aft.length - 1; i < aft.length; i++, j--) {
				if(mor[i] + aft[j] > d)
					result += ((mor[i] + aft[j]) - d) * r;
			}
			out.println(result);
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
