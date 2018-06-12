package ch2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("progress_pie.txt"));
		PrintWriter out = new PrintWriter(new File("progress_pie.out"));
		
		int t = sc.nextInt();
		
		double v1x = 0.0, v1y = 50.0, v2x, v2y;
		int cases = 1;
		while(t-- > 0){
			int p = sc.nextInt(), x = sc.nextInt() - 50, y = sc.nextInt() - 50;
			if(p != 0 && x == 0 && y == 0){
				out.println("Case #"+ cases++ + ": black");
				continue;
			}
			if(p == 0){
				out.println("Case #"+ cases++ + ": white");
				continue;
			}
			v2x = 50.0 * Math.cos((90 - ((p / 100.0) * 360)) * (Math.PI/ 180.0));
			v2y = 50.0 * Math.sin((90 - ((p / 100.0) * 360)) * (Math.PI/ 180.0));
			if(inSector(((p / 100.0) * 360), x, y))
				out.println("Case #"+ cases + ": black");
			else
				out.println("Case #"+ cases + ": white");
			cases++;
		}
		
		out.flush();
		out.close();
	}
	
	public static boolean inSector(double angle,int x, int y){
		double a = (90 - Math.atan2(y, x) * (180.0 / Math.PI));
		if(a < 0)
			a += 360.0;
		return ((a < (angle + 1e-6)) && (x * x + y * y <= 2500));
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
