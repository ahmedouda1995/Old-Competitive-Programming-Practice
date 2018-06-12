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

public class Uva_860_Entropy_Text_Analyzer {
	
	static String ref = ",.:;!? \t\n\"()";
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		DecimalFormat formatter = new DecimalFormat("0.0");
		TreeMap<String, Integer> map = new TreeMap<String, Integer>();
		String s, item;
		int lambda;
		//ArrayList<String> arr;
		label: while(true){
			lambda = 0;
			
			//arr = new ArrayList<String>();
			while(!(s = sc.next()).equals("****END_OF_TEXT****")){
				if(s.equals("****END_OF_INPUT****"))
					break label;
				item = prepare(s);
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < s.length(); ++i) {
					if(!ref.contains(String.valueOf(s.charAt(i))))
						sb.append(s.charAt(i));
					else if(sb.length() > 0){
						lambda++;
						//arr.add(item);
						if(map.containsKey(item))
							map.put(item, map.get(item) + 1);
						else
							map.put(item, 1);
						sb = new StringBuilder();
					}
				}
				if(sb.length() > 0){
					lambda++;
					//arr.add(item);
					if(map.containsKey(item))
						map.put(item, map.get(item) + 1);
					else
						map.put(item, 1);
				}
			}
			double et = 0, emax = Math.log10(lambda), erel;
			for(Entry<String, Integer> e: map.entrySet()){
				et += e.getValue() * (Math.log10(lambda) - Math.log10(e.getValue()));
			}
			et *= (1.0 / lambda);
			erel = (et / emax) * 100;
			if(lambda == 0)
				out.println("0 0.0 0");
			else
				out.println(lambda + " " + formatter.format(et) + " " + Math.round(erel));
			map.clear();
		}
		
		out.flush();
		out.close();
	}
	
	public static String prepare(String s){
		StringBuilder sb = new StringBuilder(s.toLowerCase());
		while(sb.length() > 0 && ref.contains(String.valueOf(sb.charAt(0)))){
			sb.deleteCharAt(0);
		}
		while(sb.length() > 0 && ref.contains(String.valueOf(sb.charAt(sb.length() - 1)))){
			sb.deleteCharAt(sb.length() - 1);
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