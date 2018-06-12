package ch3_BruteForce_and_Backtracking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.StringTokenizer;

public class UVa_11935_Through_the_Desert {
	
	static final double EPS = 1e-3;
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		String s; DecimalFormat f = new DecimalFormat("0.000");
		while(!(s = sc.nextLine()).equals("0 Fuel consumption 0")){
			double litres = 0.0, litresRes = 0.0; 
			int leaks = 0, leakStart = 0, start = 0, consumption = Integer.parseInt(s.split(" Fuel consumption ")[1]);
			while(!(s = sc.nextLine()).contains("Goal")){
				if(s.contains("Fuel consumption")){
					String [] tmp = s.split(" Fuel consumption ");
					int now = Integer.parseInt(tmp[0]), cons = Integer.parseInt(tmp[1]);
					litres += ((now - start) / 100.0) * consumption;
					consumption = cons;
					start = now;
				}
				else if(s.contains("Leak")){
					int now = Integer.parseInt(s.split(" Leak")[0]);
					litres += (now - leakStart) * leaks;
					leaks++;
					leakStart = now;
				}
				else if(s.contains("Gas station")){
					int now = Integer.parseInt(s.split(" Gas station")[0]);
					litres += ((now - start) / 100.0) * consumption;
					litres += (now - leakStart) * leaks;
					leakStart = now;
					if(litres > litresRes)
						litresRes = litres;
					litres = 0;
					start = now;
				}
				else {
					int now = Integer.parseInt(s.split(" Mechanic")[0]);
					litres += (now - leakStart) * leaks;
					leaks = 0;
				}
			}
			int now = Integer.parseInt(s.split(" Goal")[0]);
			litres += (now - leakStart) * leaks;
			litres += ((now - start) / 100.0) * consumption;
			if(litres > litresRes)
				litresRes = litres;
			out.println(f.format(litresRes));
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