package apsp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_10171_MeetingProfMiguel {

	static int adj1[][] = new int[26][26];
	static int adj2[][] = new int[26][26];
	static int INF = (int) 1e9, N;
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		String s; char c; int a, b, k;
		
		while((N = sc.nextInt()) != 0) {
			
			for(int i = 0; i < 26; ++i) {
				Arrays.fill(adj1[i], INF);
				Arrays.fill(adj2[i], INF);
				adj1[i][i] = 0; adj2[i][i] = 0;
			}
			
			for(int i = 0; i < N; ++i) {
				s = sc.next();
				c = sc.next().charAt(0);
				a = sc.next().charAt(0) - 'A';
				b = sc.next().charAt(0) - 'A';
				k = sc.nextInt();
				if(s.equals("Y")) {
					if(a != b) {
						if(k < adj1[a][b])
							adj1[a][b] = k;
						if(c == 'B' && k < adj1[b][a])
							adj1[b][a] = k;
					}
				}
				else {
					if(a != b) {
						if(k < adj2[a][b])
							adj2[a][b] = k;
						if(c == 'B' && k < adj2[b][a])
							adj2[b][a] = k;
					}
				}
			}
			
			int st = sc.next().charAt(0) - 'A', end = sc.next().charAt(0) - 'A';
			
			floyd(true);
			floyd(false);
			
			int min = INF;
			
			ArrayList<Character> res = new ArrayList<Character>();
			
			for(int i = 0; i < 26; ++i) {
				if(adj1[st][i] + adj2[end][i] < min) {
					min = adj1[st][i] + adj2[end][i];
					res.clear();
					res.add((char)(i + 'A'));
				}
				else if(adj1[st][i] + adj2[end][i] == min){
					res.add((char)(i + 'A'));
				}
			}
			
			if(min == INF)
				out.println("You will never meet.");
			else {
				out.print(min);
				for(char ch : res) out.print(" " + ch);
				out.println();
			}
		}
		
		out.flush();
		out.close();
	}

	private static void floyd(boolean b) {
		int [][] a = (b)?adj1:adj2;
		
		for(int k = 0; k < 26; ++k)
			for(int i = 0; i < 26; ++i)
				for(int j = 0; j < 26; ++j)
					a[i][j] = Math.min(a[i][j], a[i][k] + a[k][j]);
	}

	static class Pair implements Comparable<Pair>{
		int x;
		int y;
		
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public int compareTo(Pair p) {
			if(Integer.compare(this.x, p.x) == 0)
				return Integer.compare(this.y, p.y);
			return Integer.compare(this.x, p.x);
		}
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