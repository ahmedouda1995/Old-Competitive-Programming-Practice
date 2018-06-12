package dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class KnapSack_top_down {

	static int [][] memo = new int[1000][31];
	static int [] wt = new int[1000], val = new int[1000];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int t = sc.nextInt();
		
		while(t-- > 0){
			for (int i = 0; i < memo.length; i++)
				Arrays.fill(memo[i], -1);
			int n = sc.nextInt();
			for (int i = 0; i < n; i++) {
				wt[i] = sc.nextInt(); val[i] = sc.nextInt();
			}
			int people = sc.nextInt(), result = 0;
			for (int i = 0; i < people; i++) {
				result += knapSack(sc.nextInt(), n-1);
			}
			out.println(result);
		}
		out.flush();
		out.close();
	}
	
	private static int knapSack(int remWeight,int item) {
		if(remWeight <= 0 || item < 0)
			return 0;
		if(memo[item][remWeight] != -1)
			return memo[item][remWeight];
		if(remWeight < wt[item])
			return memo[item][remWeight] = knapSack(remWeight, item - 1);
		else
			return memo[item][remWeight] = Math.max(val[item] + knapSack(remWeight - wt[item], item - 1), knapSack(remWeight, item - 1));
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
