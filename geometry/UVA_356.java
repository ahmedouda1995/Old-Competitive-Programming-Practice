package geometry;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVA_356 {

	static int dr[] = {0, 0, 1, 1};
	static int dc[] = {0, 1, 0, 1};
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n;
		int x, y,cnt, inside, hasSeg;
		
		boolean first = true;
		
		while(sc.ready())
		{
			if (first) first = false; else out.println();
			n = sc.nextInt() * 2;
			double r = (n - 1) / 2.0;
			x = y = n / 2;
			inside = hasSeg = 0;
			
			for(int i = 0; i < n; ++i)
				for(int j = 0; j < n; ++j)
				{
					cnt = 0;
					for(int k = 0; k < 4; ++k)
					{
						if(dist(x, y, i + dr[k], j + dc[k]) <= r)
							cnt++;
					}
					
					if(cnt == 4)
						inside++;
					else if(cnt > 0)
						hasSeg++;
				}
			
			out.printf("In the case n = %d, %d cells contain segments of the circle.\n", n / 2, hasSeg);
			out.printf("There are %d cells completely contained in the circle.\n", inside);
			
			
		}
		
		out.flush();
		out.close();
	}
	
	static double dist(int x1, int y1, int x2, int y2)
	{
		return Math.sqrt(1.0 * (x1 - x2) * (x1 - x2) + 1.0 * (y1 - y2) * (y1 - y2));
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
