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

public class UVa_344_RomanDigititis {

	static TreeMap<Integer, String> map = new TreeMap<Integer, String>(Collections.reverseOrder());
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		map.put(1, "I");   map.put(5, "V");    map.put(10, "X");  map.put(50, "L");
		map.put(100, "C"); map.put(500, "D");  map.put(1000, "M");
		map.put(4, "IV");  map.put(9, "IX");   map.put(40, "XL");
		map.put(90, "XC"); map.put(400, "CD"); map.put(900, "CM");
		
		int n;
		String [] a = new String[101];
		for(int i = 1; i <= 100; ++i) a[i] = AtoR(i);
		int i, v, x, l, c;
		while((n = sc.nextInt()) != 0) {
			i = v = x = l = c = 0;
			for(int j = 1; j <= n; ++j) {
				String tmp = a[j];
				for(int k = 0; k < tmp.length(); ++k) {
					switch(tmp.charAt(k)) {
					case 'I':i++;break;
					case 'V':v++;break;
					case 'X':x++;break;
					case 'L':l++;break;
					case 'C':c++;break;
					}
				}
			}
			out.printf("%d: %d i, %d v, %d x, %d l, %d c\n", n, i, v, x, l, c);
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