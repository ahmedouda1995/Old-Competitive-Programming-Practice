package ch9_34;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class UVa_10017_TheNeverEndingTowersofHanoi {

	static char ch[] = {'A', 'B', 'C'};
	static int N, steps;
	static ArrayList<Integer> pegs[] = new ArrayList[3];
	static PrintWriter out = new PrintWriter(System.out);
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));

		int cases = 1;
		
		while((N = sc.nextInt()) != 0 | (steps = sc.nextInt()) != 0) {
			steps++;
			out.printf("Problem #%d\n\n", cases++);
			for(int i = 0; i < 3; ++i) pegs[i] = new ArrayList<Integer>();
			for(int i = N; i > 0; --i) pegs[0].add(i);
			solve(N, 0, 1, 2);
			if(steps > 0) print();
		}
		
		out.flush();
		out.close();
	}
	
	static void print() {
		for(int i = 0; i < 3; ++i) {
			out.print(ch[i] + "=>");
			if(pegs[i].size() > 0) out.print("  ");
			for(int k : pegs[i]) out.print(" " + k);
			out.println();
		}
		out.println();
	}
	
	private static void solve(int n, int src, int aux, int dest) {
		if(n > 0 && steps > 0) {
			solve(n - 1, src, dest, aux);
			if(steps > 0) print();
			pegs[dest].add(pegs[src].remove(pegs[src].size() - 1));
			steps--;
			solve(n - 1, aux, src, dest);
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