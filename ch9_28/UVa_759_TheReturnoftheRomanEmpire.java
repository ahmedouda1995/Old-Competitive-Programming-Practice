package ch9_28;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.Map.Entry;

public class UVa_759_TheReturnoftheRomanEmpire {
	static TreeMap<Integer, String> map = new TreeMap<Integer, String>(Collections.reverseOrder());
	static TreeMap<String, Integer> map2 = new TreeMap<String, Integer>();
	static PrintWriter out = new PrintWriter(System.out);
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		
		map.put(1, "I");   map.put(5, "V");    map.put(10, "X");  map.put(50, "L");
		map.put(100, "C"); map.put(500, "D");  map.put(1000, "M");
		map.put(4, "IV");  map.put(9, "IX");   map.put(40, "XL");
		map.put(90, "XC"); map.put(400, "CD"); map.put(900, "CM");
		
		String s;
		for(int i = 1; i <= 3999; ++i) map2.put(AtoR(i), i);
		while((s = sc.nextLine()) != null) {
			if(map2.containsKey(s))
				out.println(map2.get(s));
			else
				out.println("This is not a valid number");
		}
		
		
		out.flush();
		out.close();
	}

	public static String AtoR(int n) {
		StringBuilder sb = new StringBuilder();
		for(Entry<Integer, String> e : map.entrySet()) {
			while(n >= e.getKey()) {
				sb.append(e.getValue());
				n -= e.getKey();
			}
		}
		
		return sb.toString();
	}
	
	static class Pair {
		char c;
		int n;
		
		public Pair(char c, int n) {
			this.c = c;
			this.n = n;
		}
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