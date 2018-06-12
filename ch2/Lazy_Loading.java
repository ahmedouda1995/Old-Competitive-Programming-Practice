package ch2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Lazy_Loading {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("lazy_loading.txt"));
		PrintWriter out = new PrintWriter(new File("lazy_loading.out"));
		
		int t = sc.nextInt(), cases = 1;
		while(t-- > 0){
			int n = sc.nextInt();
			int [] a = new int[n];
			for (int i = 0; i < a.length; i++) {
				a[i] = sc.nextInt();
			}
			Arrays.sort(a);
			int i = 0, j = a.length - 1;
			int result = 0;
			while(i <= j){
				int no = (int)(Math.ceil((50.0 / a[j])) - 1);
				if(j - i < no)
					break;
				while(i < j && no-- > 0) i++;
				result++;
				j--;
			}
			out.println("Case #" + cases++ + ": " + result);
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