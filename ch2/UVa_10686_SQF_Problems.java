package ch2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class UVa_10686_SQF_Problems {
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int t = sc.nextInt();
		TreeMap<String, String> map = new TreeMap<String, String>();
		TreeMap<String, Integer> count = new TreeMap<String, Integer>();
		
		while(t-- > 0){
			map.clear();
			count.clear();
			int c = sc.nextInt();
			while(c-- > 0) {
				String s = sc.next(); int w = sc.nextInt(), p = sc.nextInt();
				count.put(s, p);
				for (int i = 0; i < w; ++i) map.put(sc.next(), s);
			}
			String next;
			boolean first = true;
			while((next = sc.nextLine()) != null && next.length() > 0){
				StringBuilder sb = new StringBuilder();
				for (int i = 0; i < next.length(); ++i) {
					if(Character.isLetter(next.charAt(i)))
						sb.append(next.charAt(i));
					else if(sb.length() > 0){
						String tmp = sb.toString();
						if(map.containsKey(tmp)){
							String cat = map.get(tmp); int co = count.get(map.get(tmp));
							count.replace(cat, (co - 1));
							if(co == 1)
								if(first){
									out.print(cat);
									first = false;
								} else 
									out.print(","+cat);
						}
						sb = new StringBuilder();
					}
				}
				// check after loop extremely important wrong more than once in different problems
				if(sb.length() > 0){
					String tmp = sb.toString();
					if(map.containsKey(tmp)){
						String cat = map.get(tmp); int co = count.get(map.get(tmp));
						count.replace(cat, (co - 1));
						if(co == 1)
							if(first){
								out.print(cat);
								first = false;
							} else 
								out.print(","+cat);
					}
				}
			}
			if(first)
				out.println("SQF Problem.");
			else
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