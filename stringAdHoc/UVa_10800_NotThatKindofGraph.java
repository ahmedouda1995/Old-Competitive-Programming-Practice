package stringAdHoc;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class UVa_10800_NotThatKindofGraph {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int t = sc.nextInt(), cases = 1;
		
		char s[];
		int arr[];
		
		TreeMap<Character, Character> map = new TreeMap<Character, Character>();
		map.put('R', '/'); map.put('C', '_'); map.put('F', '\\');
		
		while(t-- > 0) {
			s = sc.nextLine().toCharArray();
			arr = new int[s.length];
			
			arr[0] = (s[0] == '\\')?-1:0;
			int max = arr[0], min = arr[0];
			
			for(int i = 1; i < arr.length; ++i) {
				if(s[i] == 'C') {
					if(s[i - 1] == 'R') arr[i] = arr[i - 1] + 1;
					else arr[i] = arr[i - 1];
				}
				else if(s[i] == 'R') {
					if(s[i - 1] == 'R') arr[i] = arr[i - 1] + 1;
					else  arr[i] = arr[i - 1];
				}
				else {
					if(s[i - 1] == 'R') arr[i] = arr[i - 1];
					else arr[i] = arr[i - 1] - 1;
				}
				max = Math.max(max, arr[i]);
				min = Math.min(min, arr[i]);
			}
			
			out.printf("Case #%d:\n", cases++);
			StringBuilder sb;
			for(int i = max; i >= min; i--) {
				sb = new StringBuilder();
				sb.append("| ");
				for(int j = 0; j < arr.length; ++j) {
					if(arr[j] == i) sb.append(map.get(s[j]));
					else sb.append(" ");
				}
				out.println(sb.toString().trim());
			}
			
			out.print("+-");
			for(int i = 0; i <= arr.length; ++i) out.print("-");
			out.println("\n");
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