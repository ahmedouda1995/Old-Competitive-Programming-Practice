package dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Assignment3_q3 {
	static int n, c;
	static int [] weights = new int[50], values = new int[50];
	static int memo [][] = new int[2501][2501], itemCountChoice[][] = new int[2501][2501];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in); PrintWriter out = new PrintWriter(System.out);
		
		n = sc.nextInt(); c = n * n;
		for (int i = 0; i < n; i++) {
			weights[i] = sc.nextInt(); values[i] = sc.nextInt();
		}
		
		for (int i = 0; i < memo.length; i++)
			Arrays.fill(memo[i], -1);
		
		out.println(knapSackMod(0, c)); // (item, weight)
		printChoices(0, c, out);
		out.flush(); out.close();
	}
	
	private static void printChoices(int item, int remWeight, PrintWriter out) {
		if(remWeight <= 0 || item >= n)
			return;
		else{
			if(itemCountChoice[item][remWeight] != 0)
				out.println("take " + itemCountChoice[item][remWeight] + " item(s) from item " + (item + 1));
			printChoices(item + 1, remWeight - itemCountChoice[item][remWeight] * weights[item], out);
			
		}
	}

	private static int knapSackMod(int item, int remWeight) {
		if(remWeight <= 0 || item >= n)
			return 0;
		if(memo[item][remWeight] != -1)
			return memo[item][remWeight];
		int k = 0;
		int max = Integer.MIN_VALUE;
		int count = 0;
		while((k * weights[item]) <= remWeight){
			int tmp = (k * values[item]) + knapSackMod(item + 1, remWeight - (k * weights[item]));
			//max = Math.max(max, tmp);
			if(tmp > max){
				max = tmp;
				count = k;
			}
			k += (k == 0)?1:2;
		}
		itemCountChoice[item][remWeight] = count;
		return memo[item][remWeight] = max;
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
5
10 80
9 45
9 40
13 38
5 7
*/
