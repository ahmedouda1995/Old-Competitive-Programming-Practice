package ch2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_11350_Stern_Brocot_Tree {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt();
		while(t-- > 0){
			long resultN = 1, resultD = 1, leftN = 0, leftD = 1, rightN = 1, rightD = 0;
			char chars [] = sc.nextLine().toCharArray();
			for (int i = 0; i < chars.length; i++) {
				if(chars[i] == 'L'){
					long tmpN = resultN, tmpD = resultD;
					resultN += leftN; resultD += leftD;
					rightN = tmpN; rightD = tmpD;
				}
				else {
					long tmpN = resultN, tmpD = resultD;
					resultN += rightN; resultD += rightD;
					leftN = tmpN; leftD = tmpD;
				}
			}
			out.printf("%d/%d\n", resultN, resultD);
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