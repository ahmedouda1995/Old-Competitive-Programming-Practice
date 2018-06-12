package dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;


// 	COMPLETE SEARCH

public class Knapsack {

	static int [] memo = new int[3000];
	static int [] values = new int[1020], weights = new int[1020];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		int t = sc.nextInt();
		
		while(t-- > 0){
			Arrays.fill(memo, -1);
			int n = sc.nextInt();
			for (int i = 0; i < n; i++) {
				values[i] = sc.nextInt(); weights[i] = sc.nextInt();
			}
			int people = sc.nextInt(), result = 0;
			for (int i = 0; i < people; i++) {
				result += knapSack(sc.nextInt(), n);
			}
			out.println(result);
		}
		
		out.flush();
		out.close();
	}

	private static int knapSack(int knapSackWeight, int n) {
		if(knapSackWeight <= 0)
			return 0;
		//if(memo[knapSackWeight] != -1)
			//return memo[knapSackWeight];
		int max = -1;
		for (int i = 0; i < n; i++) {
			if(weights[i] <= knapSackWeight && values[i] != -1){
				int tmp = values[i];
				values[i] = -1;
				max =  Math.max(tmp + knapSack(knapSackWeight - weights[i], n), max);
				values[i] = tmp;
			}
		}
		//System.out.println(Arrays.toString(memo));
		if(max == -1)
			return 0;
		return memo[knapSackWeight] = max;
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
/*
2
3
72 17
44 23
31 24
1
26
6
64 26
85 22
52 4
99 18
39 13
54 9
4
23
20
20
26
*/