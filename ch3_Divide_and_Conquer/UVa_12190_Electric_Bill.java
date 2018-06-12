package ch3_Divide_and_Conquer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_12190_Electric_Bill {

	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int a, b;
		while((a = sc.nextInt()) != 0 | (b = sc.nextInt()) != 0){
			int watts = getWatts(a);
			out.println(solve(watts, a, b));
		}
		
		out.flush();
		out.close();
	}
	
	private static int solve(int watts, int a, int b) {
		int l = 0, h = watts / 2 + 1;
		int lOld = -1, hOld = -1;
		for(int i = 0; i < 35; ++i){
			lOld = l; hOld = h;
			int mid = l + (h - l) / 2;
			int x = money(mid), y = money(watts - mid);
			if(y - x == b)
				return x;
			if(y - x > b)
				l = mid;
			else
				h = mid;
		}
		return money((l + h) / 2);
	}

	private static int money(int watts){
		int [] am = {100, 9900, 990000};
		int [] c = {2, 3, 5};
		int i = 0;
		int res = 0;
		while(watts != 0 && i < 3){
			if(watts >= am[i]){
				res += am[i] * c[i];
				watts -= am[i];
			}
			else {
				res += watts * c[i];
				watts = 0;
			}
			i++;
		}
		if(watts > 0)
			res += watts * 7;
		return res;
	}
	
	private static int getWatts(int a) {
		int [] am = {2 * 100, 3 * 9900, 5 * 990000};
		int [] c = {2, 3, 5};
		int i = 0, watts = 0;
		while(a != 0 && i < 3){
			if(a >= am[i]){
				watts += am[i] / c[i];
				a -= am[i];
			}
			else {
				watts += (a / c[i]);
				a = 0;
			}
			i++;
		}
		if(a > 0)
			watts += (a / 7);
		
		return watts;
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