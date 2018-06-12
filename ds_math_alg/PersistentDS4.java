package ds_math_alg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class PersistentDS4 {

	static int [] sz, prev, val;
	static int version = 1, C;
	static PrintWriter out;
	static StringBuilder sb;
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		out = new PrintWriter(System.out);
		
		int q = sc.nextInt(), v;
		
		sz = new int[q + 2];
		prev = new int[q + 2];
		val = new int[q + 2];
		sb = new StringBuilder();
		sz[0] = 0;
		prev[0] = -1;
		
		while(q-- > 0)
		{
			switch (sc.next().charAt(0)) {
			case 'e':
				v = sc.nextInt();
				prev[version] = version - 1;
				sz[version] = sz[prev[version]] + 1;
				val[version++] = v;
				;break;
			case 'd':
				sz[version] = sz[version - 1] - 1;
				if(sz[version] < 0) sz[version] = 0;
				prev[version] = prev[version - 1];
				val[version] = val[version - 1];
				version++;
				;break;
			default:
				v = sc.nextInt();
				if(sz[v] == 0) sb.append("empty\n");
				else print(v, C = sz[v]);
			}
		}
		out.print(sb);
		
		out.flush();
		out.close();
	}
	
	private static void print(int v, int c) {
		if(c == 0) return;
		else
		{
			print(prev[v], c - 1);
			sb.append(val[v] + ((c == C)?"\n":" "));
		}
	}

	static class Scanner {
		BufferedReader br;
		StringTokenizer st;

		public Scanner(InputStream in) {
			br = new BufferedReader(new InputStreamReader(in));
		}

		public Scanner(String file) throws FileNotFoundException {
			br = new BufferedReader(new FileReader(file));
		}

		String next() throws IOException {
			while (st == null || !st.hasMoreTokens()) {
				st = new StringTokenizer(br.readLine());
			}
			return st.nextToken();
		}

		String nextLine() throws IOException {
			return br.readLine();
		}

		int nextInt() throws NumberFormatException, IOException {
			return Integer.parseInt(next());
		}

		long nextLong() throws NumberFormatException, IOException {
			return Long.parseLong(next());
		}

		double nextDouble() throws NumberFormatException, IOException {
			return Double.parseDouble(next());
		}

		boolean ready() throws IOException {
			return br.ready();
		}
	}
}
