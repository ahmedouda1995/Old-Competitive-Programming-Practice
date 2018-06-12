package fenwick_tree;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Potentiometers {

	static int N;
	static int arr[];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		boolean first = true;
		int cases = 1;
		
		while((N = sc.nextInt()) != 0) {
			
			if(first) first = false; else out.println();
			
			arr = new int[N];
			for(int i = 0; i < N; ++i) arr[i] = sc.nextInt();
			FenwickTree ft = new FenwickTree();
			String s;
			
			out.printf("Case %d:\n", cases++);
			
			while(!(s = sc.next()).equals("END")) {
				if(s.charAt(0) == 'M')
					out.println(ft.rsq(sc.nextInt(), sc.nextInt()));
				else {
					int idx = sc.nextInt(), val = sc.nextInt();
					ft.adjust(idx, val - arr[idx - 1]);
					arr[idx - 1] = val;
				}
			}
		}
		
		out.flush();
		out.close();
	}
	
	static class FenwickTree {
		
		int f[];
		
		public FenwickTree() {
			f = new int[N + 1];
			for(int i = 0; i < N; ++i) adjust(i + 1, arr[i]);
		}
		
		private void adjust(int idx, int diff) {
			while(idx <= N) {
				f[idx] += diff;
				idx += (idx & -idx);
			}
		}

		int rsq(int a) {
			int sum = 0;
			while(a > 0) {
				sum += f[a];
				a -= (a & -a);
			}
			return sum;
		}
		
		int rsq(int a, int b) { return rsq(b) - rsq(a - 1); }
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