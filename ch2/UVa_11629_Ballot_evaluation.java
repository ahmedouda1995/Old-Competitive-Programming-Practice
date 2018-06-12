package ch2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

// use Math.round(double * 10.0) / 10.0 trick or * number by 10 and use integers - GREAT TRICK

public class UVa_11629_Ballot_evaluation {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		int p = sc.nextInt(), g = sc.nextInt(); String s;
		TreeMap<String, Double> map = new TreeMap<String, Double>();
		for (int i = 0; i < p; ++i) {
			map.put(sc.next(), sc.nextDouble());
		}
		for (int i = 0; i < g; ++i) {
			s = sc.nextLine();
			StringTokenizer st = new StringTokenizer(s);
			double left = 0; String tmp; boolean guess = false;
			while(st.hasMoreTokens()){
				switch((tmp = st.nextToken())){
					case ">": guess = (Math.round(left * 10.0) / 10.0 > Double.parseDouble(st.nextToken()));break;
					case ">=": guess = ((Math.round(left * 10.0) / 10.0 >= Double.parseDouble(st.nextToken())));break;
					case "<": guess = ((Math.round(left * 10.0) / 10.0 < Double.parseDouble(st.nextToken())));break;
					case "<=": guess = ((Math.round(left * 10.0) / 10.0 <= Double.parseDouble(st.nextToken())));break;
					case "=": guess = ((Math.round(left * 10.0) / 10.0 == Double.parseDouble(st.nextToken())));break;
					default: if(!tmp.equals("+")) {
						left += map.get(tmp);
					}
				}
			}
			out.println("Guess #" + (i + 1) + " was " + ((guess)?"correct.":"incorrect."));
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