package dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVA_wedding_tabular {

	static int[][] price = new int [21][21];
	static boolean [][] memo;
	static int c, m, score;
	
		public static void main(String[] args) throws IOException{
			Scanner sc = new Scanner(System.in);
			PrintWriter out = new PrintWriter(System.out);
			
			int t = sc.nextInt();
			
			while(t-- > 0){
				memo = new boolean[21][201];
				m = sc.nextInt(); c = sc.nextInt();
				for (int i = 0; i < c; i++) {
					price[i][0] = sc.nextInt();
					for (int j = 1; j <= price[i][0]; j++) {
						price[i][j] = sc.nextInt();
					}
				}
				
				score = shopTabular(c);
				
				if (score < 0) out.println("no solution");
				else out.println(score);
			}
			
			out.flush();
			out.close();
		}
		private static int shopTabular(int c) {
			for(int i=1;i<=price[0][0];i++)
				if(m - price[0][i] >= 0)
					memo[0][m - price[0][i]] = true;
			for (int i = 1; i < c; i++) {
				for (int money = 0; money < m; money++) 
					if (memo[i-1][money])
						for (int k = 1; k <= price[i][0]; k++) 
							if (money - price[i][k] >= 0)
								memo[i][money - price[i][k]] = true;
			}
			for(int i=0;i<memo[c-1].length;i++)
				if(memo[c-1][i])
					return m - i;
			return -1;
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
		/*
3
100 4
3 8 6 4 
2 5 10
4 1 3 3 7 
4 50 14 23 8 
20 3
3 4 6 8 
2 5 10
4 1 3 5 5 
5 3
3 6 4 8 
2 10 6 
4 7 3 1 7
		 */
}
