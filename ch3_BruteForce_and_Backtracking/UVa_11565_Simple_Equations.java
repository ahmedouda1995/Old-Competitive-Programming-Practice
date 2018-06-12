package ch3_BruteForce_and_Backtracking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_11565_Simple_Equations {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		int t = sc.nextInt(), a, b, c;
		while(t-- > 0){
			boolean printed = false;
			a = sc.nextInt(); b = sc.nextInt(); c = sc.nextInt();
			label : for (int i = -22; i <= 22; i++) {
				for (int j = -100; j <= 100 ; j++) {
					for (int k = -100; k <= 100 ; k++) {
						if(i != j && j != k && k != i && i + j + k == a && i * j *k == b &&
								i * i + j * j + k * k == c){
							out.println(i + " " + j + " " + k); printed = true; break label;
						}
					}
				}
			}
			if(!printed) out.println("No solution.");
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