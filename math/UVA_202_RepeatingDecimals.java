package math;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;

public class UVA_202_RepeatingDecimals {

	static int result, rem, idx;
	static HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
	static ArrayList<Integer> decDigits = new ArrayList<Integer>();
	
	public static void main(String[] args) throws IOException {
		
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		int a, b;
		
		while(sc.ready())
		{
			map.clear();
			decDigits.clear();
			a = sc.nextInt(); b = sc.nextInt();
			idx = 0;
			
			result = a / b;
			rem = a % b;
			
			while(!map.containsKey(rem))
			{
				System.out.println(rem);
				map.put(rem, idx++);
				result = rem * 10 / b;
				rem = (rem * 10) % b;
				decDigits.add(result);
			}
			
			int cycleStart = map.get(rem);
			
			int nCurDig = 0;
			
			out.printf("%d/%d = %d.", a, b, a / b);
			
			for (; nCurDig < cycleStart && nCurDig < 50; nCurDig++)
				out.printf("%d", decDigits.get(nCurDig));
			
			out.printf("(");
			for (; nCurDig < idx && nCurDig < 50; nCurDig++)
				out.printf("%d", decDigits.get(nCurDig));

			
			if (nCurDig < idx)
				out.printf("...");
			out.printf(")\n");
			out.printf("   %d = number of digits in repeating cycle\n\n",
					idx - cycleStart);
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
