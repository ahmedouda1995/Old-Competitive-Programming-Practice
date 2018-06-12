package ch3_BruteForce_and_Backtracking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_11242_Tour_de_France {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int f, r, front[], rear[];
		double tmp[];
		DecimalFormat formatter = new DecimalFormat("0.00");
		while((f = sc.nextInt()) != 0){
			r = sc.nextInt();
			front = new int[f]; rear = new int[r];
			tmp = new double[(f * r)];
			for (int i = 0; i < f; ++i) front[i] = sc.nextInt();
			for (int i = 0; i < r; ++i) rear[i] = sc.nextInt();
			int k = 0;
			for (int i = 0; i < f; i++)
				for (int j = 0; j < r; j++)
					tmp[k++] = (rear[j] * 1.0) / front[i];

			double max = -1.0;
			Arrays.sort(tmp);
			for (int i = 1; i < tmp.length; i++) {
				if((tmp[i] / tmp[i - 1]) > max)
					max = (tmp[i] / tmp[i - 1]);
			}
			out.println(formatter.format(max));
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