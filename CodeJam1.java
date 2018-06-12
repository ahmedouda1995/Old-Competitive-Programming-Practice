import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class CodeJam1 {
	
	static int N, K;
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("pancake.out");

		int t = sc.nextInt(), cases = 1;
		
		while(t-- > 0) {
			char s[] = sc.next().toCharArray();
			int K = sc.nextInt();
			N = s.length;
			
			int res = 0;
			int score = 0;
			
			for(int i = 0; i < N - K + 1; ++i) {
				if(s[i] == '+')
					score++;
				else {
					res++;
					score++;
					for(int j = i; j < i + K; j++) {
						if(s[j] == '+') s[j] = '-'; else s[j] = '+';
					}
				}
			}
			
			for(int i = N - K + 1; i < N; ++i)
				if(s[i] == '+')
					score++;
			
			if(score == N)
				out.printf("Case #%d: %d\n", cases++, res);
			else
				out.printf("Case #%d: IMPOSSIBLE\n", cases++);
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