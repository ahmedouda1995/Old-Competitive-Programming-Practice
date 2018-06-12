package dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVA_11450_Wedding_shopping {

	static int[][] price = new int [25][25], memo = new int[210][25];
	static int c, m, score;
	
		public static void main(String[] args) throws IOException{
			Scanner sc = new Scanner(System.in);
			PrintWriter out = new PrintWriter(System.out);
			
			int t = sc.nextInt();
			
			while(t-- > 0){
				m = sc.nextInt(); c = sc.nextInt();
				for (int i = 0; i < c; i++) {
					price[i][0] = sc.nextInt();
					for (int j = 1; j <= price[i][0]; j++) {
						price[i][j] = sc.nextInt();
					}
				}
				
				for(int i=0;i<memo.length;i++)
					Arrays.fill(memo[i], -1);
				
				score = shop(m, 0);
				
				if (score < 0) out.println("no solution");
				else out.println(score);
			}
			
			out.flush();
			out.close();
		}
		private static int shop(int money, int g) {
			if (money < 0) return Integer.MIN_VALUE;
			if (g == c) return m - money;
			
			if (memo[money][g] != -1) return memo[money][g];
			int ans = -1;
			for (int model = 1; model <= price[g][0]; model++)
				ans = Math.max(ans, shop(money - price[g][model], g + 1));
			
			return memo[money][g] = ans;
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
