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

public class UVa_12397_RomanNumerals {

	static TreeMap<Integer, String> map = new TreeMap<Integer, String>(Collections.reverseOrder());
	static TreeMap<Character, Integer> map2 = new TreeMap<Character, Integer>();
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		map.put(1, "I");   map.put(5, "V");    map.put(10, "X");  map.put(50, "L");
		map.put(100, "C"); map.put(500, "D");  map.put(1000, "M");
		map.put(4, "IV");  map.put(9, "IX");   map.put(40, "XL");
		map.put(90, "XC"); map.put(400, "CD"); map.put(900, "CM");
		
		map2.put('I', 1); map2.put('V', 2); map2.put('X', 2); map2.put('L', 2);
		map2.put('C', 2); map2.put('D', 3); map2.put('M', 4);
		String s;
		
		while((s = sc.nextLine()) != null) out.println(AtoR(Integer.parseInt(s)));
		
		out.flush();
		out.close();
	}
	
	public static int AtoR(int n) {
		StringBuilder sb = new StringBuilder();
		int res = 0;
		for(Entry<Integer, String> e : map.entrySet()) {
			while(n >= e.getKey()) {
				sb.append(e.getValue());
				n -= e.getKey();
			}
		}
		
		for(char c : sb.toString().toCharArray()) {
			res += map2.get(c);
		}
		return res;
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