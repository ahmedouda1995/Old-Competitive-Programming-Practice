package geometry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVA_10502 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int n, m;
		int res;
		char s[];
		
		while((n = sc.nextInt()) != 0)
		{
			m = sc.nextInt();
			res = 0;
			
			int grid[][] = new int[n][m];
			
			for(int i = 0; i < n; ++i)
			{
				s = sc.nextLine().toCharArray();
				
				for(int j = 0; j < m; ++j)
				{
					grid[i][j] = ((s[j] == '1')?1:0) +
							(i > 0?grid[i - 1][j]:0) +
							(j > 0?grid[i][j - 1]:0) -
							(i > 0 && j > 0?grid[i - 1][j - 1]:0);
				}
			}
			
			for(int up = 0; up < n; ++up)
				for(int down = up; down < n; ++down)
					for(int l = 0; l < m; ++l)
						for(int r = l; r < m; ++r)
						{
							int full = (down - up + 1) * (r - l + 1);
							int sum = grid[down][r];
							sum -= (l > 0?grid[down][l - 1]:0);
							sum -= (up > 0?grid[up - 1][r]:0);
							sum += (up > 0 && l > 0?grid[up - 1][l - 1]:0);
							
							res += (sum == full)?1:0;
						}
			
			out.println(res);
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
