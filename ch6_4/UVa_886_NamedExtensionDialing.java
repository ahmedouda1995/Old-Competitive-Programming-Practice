package ch6_4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class UVa_886_NamedExtensionDialing {

	static ArrayList<String> arr1 = new ArrayList<String>();
	static ArrayList<String> arr2 = new ArrayList<String>();
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		String s, last, ext;
		TreeSet<Integer> set = new TreeSet<Integer>();
		int [] val =
			{2, 2, 2, 3, 3, 3, 4, 4, 4, 5, 5, 5, 6, 6, 6, 7, 7, 7, 7, 8, 8, 8, 9, 9, 9, 9};
		StringBuilder sb;
		
		while((s = sc.next()) != null) {
			s = s.toLowerCase();
			if(Character.isDigit(s.charAt(0))) break;
			last = sc.next().toLowerCase(); ext = sc.next();
			set.add(Integer.parseInt(ext));
			
			sb = new StringBuilder();
			sb.append(val[s.charAt(0) - 'a']);
			for(int i = 0; i < last.length(); ++i)
				sb.append(val[last.charAt(i) - 'a']);
			arr1.add(sb.toString());
			arr2.add(ext);
		}

		if(s != null) {
			do {
				if(s.length() == 4 && set.contains(Integer.parseInt(s)))
					out.println(s);
				else {
					String tmp = match(s);
					if(tmp.length() == 0)
						out.println(0);
					else
						out.println(tmp.trim());
				}
			}while((s = sc.nextLine()) != null);
		}
		
		out.flush();
		out.close();
	}
	
	private static String match(String s) {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < arr1.size(); ++i) {
			if(arr1.get(i).startsWith(s))
				sb.append(arr2.get(i) + " ");
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