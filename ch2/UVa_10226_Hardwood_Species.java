package ch2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.Map.Entry;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class UVa_10226_Hardwood_Species {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("Input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		int t = sc.nextInt();
		sc.nextLine();
		DecimalFormat f = new DecimalFormat("0.0000");
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		while(t-- > 0){
			map.clear();
			String s; int count = 0;
			while((s = sc.nextLine()) != null && s.length() > 0){
				count++;
				if(map.containsKey(s))
					map.replace(s, map.get(s) + 1);
				else
					map.put(s, 1);
			}
			for(Entry<String, Integer> e : map.entrySet()){
				out.println(e.getKey() + " " + f.format((e.getValue() * 100.0) / (count * 1.0)));
			}
			if(t > 0)
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