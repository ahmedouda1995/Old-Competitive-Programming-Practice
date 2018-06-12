package dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Assignment3_q2 {

	static int [][] memo = new int [1000][1001];
	static int [] difficulty = new int[1000], value = new int[1000];
	static int n, maxDifficulty = 0;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		n = sc.nextInt();
		
		for (int i = 0; i < memo.length; i++) Arrays.fill(memo[i], -1);
		
		for (int i = 0; i < n; i++) {
			difficulty[i] = sc.nextInt(); value[i] = sc.nextInt();
			maxDifficulty = Math.max(difficulty[i], maxDifficulty);
		}
		
		out.println(topDown1(0, 0)); // (item 0, difficulty level 0)
		
		for (int i = 0; i < memo.length; i++) Arrays.fill(memo[i], -1);
		
		out.println(topDown(n-1, maxDifficulty)); // (item n - 1, difficulty level maxDifficulty)
			
		out.flush();
		out.close();
	}
	
	private static int topDown1(int item, int difficultyLevel) {
		if(item >= n)
			return 0;
		if(memo[item][difficultyLevel] != -1)
			return memo[item][difficultyLevel];
		if(difficulty[item] >= difficultyLevel)
			return memo[item][difficultyLevel] = 
				Math.max(value[item] + topDown1(item + 1, difficulty[item]),
				topDown1(item + 1, difficultyLevel));
		else
			return memo[item][difficultyLevel] = topDown1(item + 1, difficultyLevel);
	}
	
	private static int topDown(int item, int difficultyLevel) {
		if(item < 0)
			return 0;
		if(memo[item][difficultyLevel] != -1)
			return memo[item][difficultyLevel];
		if(difficulty[item] <= difficultyLevel)
			return memo[item][difficultyLevel] = 
				Math.max(value[item] + topDown(item - 1, difficulty[item]),
				topDown(item - 1, difficultyLevel));
		else
			return memo[item][difficultyLevel] = topDown(item - 1, difficultyLevel);
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
1 10
0 1
3 5

7
1 5
4 6
5 2
2 1
3 13
2 14
6 5
	 */
}
