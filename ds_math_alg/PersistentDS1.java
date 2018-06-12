package ds_math_alg;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class PersistentDS1 {

	static final int MAX = (int) 1e5 + 5;
	static int [] val, min, prev;
	static int version = 1;
	
	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		PrintWriter out = new PrintWriter(System.out);
		
		val = new int[MAX];
		min = new int[MAX];
		prev = new int[MAX];
		
		prev[0] = MAX;
		val[0] = min[0] = Integer.MAX_VALUE;
		
		int q = sc.nextInt();
		String s[];
		while(q-- > 0)
		{
			s = sc.nextLine().split(" ");
			if(s.length == 3)
			{
				val[version] = Integer.parseInt(s[1]);
				prev[version] = Integer.parseInt(s[2]);
				min[version] = Math.min(val[version], min[prev[version]]);
				version++;
			}
			else if(s[0].charAt(0) == 'p')
			{
				int cur = Integer.parseInt(s[1]);
				int prevVersion = prev[cur];
				if(prevVersion == MAX)
				{
					prev[version] = MAX;
					val[version] = Integer.MAX_VALUE;
					min[version] = Integer.MAX_VALUE;
					out.println("null");
				}
				else
				{
					val[version] = val[prevVersion];
					prev[version] = prev[prevVersion];
					min[version] = min[prevVersion];
					out.println(val[cur]);
				}
				version++;
			}
			else
			{
				int v = Integer.parseInt(s[1]);
				out.println(prev[v] == MAX?"null":min[v]);
			}
		}
		
		out.flush();
		out.close();
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