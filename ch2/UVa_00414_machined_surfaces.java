package ch2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_00414_machined_surfaces {

	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		String s;
		int [] a;
		int min = Integer.MAX_VALUE, result;
		
		while(true){
			int n = sc.nextInt();
			a = new int[n];
			result = 0;
			min = Integer.MAX_VALUE;
			if(n == 0)
				break;
			for(int i = 0;i<n;i++){
				s = sc.nextLine();
				a[i] = countSpaces(s);
				min = Math.min(min, a[i]);
			}
			for (int i = 0; i < a.length; i++) {
				result += a[i] - min;
			}
			out.println(result);
		}
		
		out.flush();
		out.close();
	}
	private static int countSpaces(String s) {
		int i = 0, count = 0;
		while(i < s.length() && s.charAt(i) != ' ')
			i++;
		
		while(i < s.length() && s.charAt(i) != 'X'){
			count++;
			i++;
		}
		return count;
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
