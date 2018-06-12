package math;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class UVA_107 {

	static final double EPS = 1e-9;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int height, workers;
		
		while((height = sc.nextInt()) != 0 | (workers = sc.nextInt()) != 0)
		{
			int i = 1;
			int stack = workers;
			int n = 1;
			
			while(i * i <= workers)
			{
				if(workers % i == 0)
				{
					if(check(workers, height, i, i + 1))
					{
						n = i;
						break;
					}
					if(check(workers, height, workers / i, workers / i + 1))
					{
						n = workers / i;
						break;
					}
				}
				i++;
			}
			
			int len = height;
			
			int total = 0;
			int mult = 0;
			
			//System.out.println(n);
			
			while(len > 1)
			{
				stack += Math.pow(n, mult) * len;
				len /= (n + 1);
				total += Math.pow(n, mult);
				mult++;
			}
			
			out.println(total + " " + stack);
		}
		
		out.flush();
		out.close();
	}
	
	private static boolean check(int workers, int height, int n, int nP1) {
		while(workers > 1 && height > 1 && workers % n == 0 && height % nP1 == 0)
		{
			workers /= n;
			height /= nP1;
		}
		
		return workers == 1 && height == 1;
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
