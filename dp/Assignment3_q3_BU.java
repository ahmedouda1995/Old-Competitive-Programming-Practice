package dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Assignment3_q3_BU {
	static int n, c;
	static int [] weights = new int[50], values = new int[50];
	static int dp [][] = new int[2501][2501], itemCountChoice[][] = new int[2501][2501];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in); PrintWriter out = new PrintWriter(System.out);
		
		n = sc.nextInt(); c = n * n;
		for (int i = 0; i < n; i++) {
			weights[i] = sc.nextInt(); values[i] = sc.nextInt();
		}
		
		out.println(knapSackModBU());
		printChoices(n - 1, c, out);
		
		out.flush(); out.close();
	}
	
	private static void printChoices(int item, int remWeight, PrintWriter out) {
		if(remWeight <= 0 || item < 0)
			return;
		else{
			printChoices(item - 1, remWeight - itemCountChoice[item][remWeight] * weights[item], out);
			if(itemCountChoice[item][remWeight] != 0)
				out.println("take " + itemCountChoice[item][remWeight] + " item(s) from item " + (item + 1));
		}
	}
	
	private static int knapSackModBU() {
		for (int j = 1; j <= c; j++) {
			if((j/weights[0]) == 0)
				dp[0][j] = 0;
			else if((j/weights[0]) % 2 == 1){
				dp[0][j] = (j/weights[0]) * values[0];
				itemCountChoice[0][j] = (j/weights[0]);
			}
			else{
				dp[0][j] = ((j/weights[0]) - 1) * values[0];
				itemCountChoice[0][j] = ((j/weights[0]) - 1);
			}
		}
		for (int i = 1; i < n; i++) {
			for (int j = 0; j <= c; j++) {
				if(j == 0)
					dp[i][j] = 0;
				else {
					int k = 0;
					int max = Integer.MIN_VALUE;
					int count = 0;
					while((k * weights[i]) <= j){
						//max = Math.max(max, (k * values[i]) + dp[i - 1][j - (k * weights[i])]);
						int tmp = (k * values[i]) + dp[i - 1][j - (k * weights[i])];
						if (tmp > max){
							max = tmp;
							count = k;
						}
						k += (k == 0)?1:2;
					}
					itemCountChoice[i][j] = count;
					dp[i][j] = max;
				}
			}
		}
		return dp[n-1][c];
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
