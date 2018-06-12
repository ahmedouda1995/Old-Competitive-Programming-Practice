package dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVA_507_Jill_Rides_Again {

	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt(), total = t, routes;
		int [] ratings;
		
		while(t-- > 0){
			routes = sc.nextInt(); routes--;
			ratings = new int[routes];
			for (int i = 0; i < ratings.length; i++) {
				ratings[i] = sc.nextInt();
			}
			maxSumSubsequence(ratings, out, total - t, sc);
		}
		out.flush();
		out.close();
	}
	
	private static void maxSumSubsequence(int[] ratings, PrintWriter out, int route, Scanner sc) throws IOException {
		int maxSoFar = ratings[0], max = maxSoFar;
		int start = 0, end = 0, startFinal = start, endFinal = end;
		for (int i = 1; i < ratings.length; i++) {
			
			//maxSoFar = Math.max(ratings[i], maxSoFar + ratings[i]);
			if(ratings[i] > maxSoFar + ratings[i]){
				maxSoFar = ratings[i];
				start = end = i;
			}
			else if(maxSoFar + ratings[i] > ratings[i]){
				maxSoFar = maxSoFar + ratings[i];
				end = i;
			}
			else {
				maxSoFar = ratings[i];
				end = i;
			}
			
			//max = Math.max(max, maxSoFar);
			if(maxSoFar > max){
				max = maxSoFar;
				endFinal = end; startFinal = start;
			}
			else if(maxSoFar == max && (end - start > endFinal - startFinal)){
					endFinal = end; startFinal = start;
			}
		}
		if(max <= 0)
			out.println("Route " + route + " has no nice parts");
		else 
			out.println("The nicest part of route " + route + " is between stops " + (startFinal + 1) + " and " + (endFinal + 2));
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
3
3
	-1
	6
10
	4
	-5
	4
	-3
	4
	4
	-4
	4
	-5
4
	-2
	-3
	-4
*/