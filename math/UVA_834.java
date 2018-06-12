package math;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class UVA_834 {

	static ArrayList<Integer> res = new ArrayList<Integer>();
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		while(sc.ready())
		{
			res.clear();
			
			solve(sc.nextInt(), sc.nextInt());
			StringBuilder sb = new StringBuilder();
			sb.append("[" + res.get(0));
			for(int i = 1; i < res.size(); ++i)
			{
				if(i == 1)
					sb.append(";");
				sb.append(res.get(i));
				if(i != res.size() - 1)
					sb.append(",");
			}
			
			sb.append("]");
			out.println(sb.toString());
		}
		
		out.flush();
		out.close();
	}
	
	private static void solve(int a, int b) {
		if(b == 0) return;
		res.add(a / b);
		solve(b, a - (a / b) * b);
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
