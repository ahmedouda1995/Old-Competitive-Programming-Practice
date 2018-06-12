package geometry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVA_201 {
	
	static int n;
	static int [][] hor, ver;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int m;
		char c;
		int row, col;
		
		int cases = 1;
		
		boolean first = true;
		
		while(sc.ready())
		{
			if(first)
				first = false;
			else
				out.printf("\n**********************************\n\n");
			
			out.printf("Problem #%d\n\n", cases++);
			n = sc.nextInt();
			m = sc.nextInt();
			
			hor = new int[n][n - 1];
			ver = new int[n - 1][n];
			
			while(m-- > 0)
			{
				c = sc.next().charAt(0);
				
				if(c == 'H')
				{
					row = sc.nextInt() - 1;
					col = sc.nextInt() - 1;
					
					if(row < hor.length && col < hor[0].length)
					hor[row][col] = 1;
				}
				else
				{
					col = sc.nextInt() - 1;
					row = sc.nextInt() - 1;
					
					
					if(row < ver.length && col < ver[0].length)
						ver[row][col] = 1;
				}
			}
			
			boolean found = false;
			
			for(int window = 1; window < n; ++window)
			{
				int cnt = 0;
				
				for(int i = 0; i + window + 1 <= n; ++i)
				{
					for(int j = 0; j + window + 1 <= n; ++j)
					{
						if(check(i, j, window))
							cnt++;
					}
				}
				
				if(cnt > 0)
				{
					found = true;
					out.printf("%d square (s) of size %d\n", cnt, window);
				}
			}
			
			if(!found)
				out.println("No completed squares can be found.");
			
		}
		
		out.flush();
		out.close();
	}
	
	private static boolean check(int a, int b, int window) {
		
		for(int i = b; i < b + window; ++i)
			if(hor[a][i] != 1)
				return false;
		
		for(int i = b; i < b + window; ++i)
			if(hor[a + window][i] != 1)
				return false;
		
		for(int i = a; i < a + window; ++i)
			if(ver[i][b] != 1)
				return false;
		
		for(int i = a; i < a + window; ++i)
			if(ver[i][b + window] != 1)
				return false;
		
		return true;
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