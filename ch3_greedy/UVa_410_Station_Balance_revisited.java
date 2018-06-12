package ch3_greedy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;


public class UVa_410_Station_Balance_revisited {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		DecimalFormat f = new DecimalFormat("0.00000");
		
		String s; int sets = 1;
		while((s = sc.nextLine()) != null){
			int C = Integer.parseInt(s.split(" ")[0]), S = Integer.parseInt(s.split(" ")[1]);
			Integer [] a = new Integer[S];
			
			double am = 0;
			
			for(int i = 0; i < S; ++i) {
				a[i] = sc.nextInt();
				am += a[i];
			}
			
			am /= C;
			
			Arrays.sort(a, new Comparator<Integer>() {
				public int compare(Integer o1, Integer o2) {
					return o2 - o1;
				}
			});
			
			int [] b1 = new int[C], b2 = new int[C];
			
			Arrays.fill(b1, -1); Arrays.fill(b2, -1);
			int i;
			
			for(i = 0; i < S && i < C; ++i) b1[i] = a[i];
			
			for(int j = i - 1; i < S; --j, ++i) b2[j] = a[i];
			
			double imbalance = 0;
			
			out.println("Set #" + sets++);
			
			for (int j = 0; j < b1.length; j++) {
				int tmp = 0;
				out.print(" " + j + ":");
				if(b1[j] != -1) {
					out.print(" " + b1[j]);
					tmp += b1[j];
				}
				if(b2[j] != -1) {
					out.print(" " + b2[j]);
					tmp += b2[j];
				}
				imbalance += Math.abs(tmp - am);
				out.println();
			}
			out.println("IMBALANCE = " + f.format(imbalance));
			out.println();
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