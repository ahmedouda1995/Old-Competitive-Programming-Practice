package geometry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVA_10865 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int n;
		
		int x[];
		int y[];
		
		while((n = sc.nextInt()) > 0)
		{
			x = new int[n];
			y = new int[n];
			
			int score1 = 0, score2 = 0;
			
			for(int i = 0; i < n; ++i)
			{
				x[i] = sc.nextInt();
				y[i] = sc.nextInt();
			}
			
			int a = x[n / 2], b = y[n / 2];
			
			for(int i = 0; i < n; ++i)
			{
				if(x[i] > a && y[i] > b || x[i] < a && y[i] < b)
					score1++;
				if(x[i] < a && y[i] > b || x[i] > a && y[i] < b)
					score2++;
			}
			
			out.println(score1 + " " + score2);
				
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
