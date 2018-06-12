import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sam1 {

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		int [] pos1X = new int[3];
		int [] pos1Y = new int[3];
		int [] pos2X = new int[3];
		int [] pos2Y = new int[3];
		
		for(int i = 0; i < 3; ++i) {
			pos1X[i] = sc.nextInt();
			pos1Y[i] = sc.nextInt();
		}
		
		for(int i = 0; i < 3; ++i) {
			pos2X[i] = sc.nextInt();
			pos2Y[i] = sc.nextInt();
		}
		
		int a[] = new int[3];
		int b[] = new int[3];
		
		for(int i = 0; i < 2; ++i) {
			a[i] = dist(pos1X[i], pos1Y[i], pos1X[i + 1], pos1Y[i + 1]);
			b[i] = dist(pos2X[i], pos2Y[i], pos2X[i + 1], pos2Y[i + 1]);
		}
		
		a[2] = dist(pos1X[2], pos1Y[2], pos1X[0], pos1Y[0]);
		b[2] = dist(pos2X[2], pos2Y[2], pos2X[0], pos2Y[0]);
		
		Arrays.sort(a); Arrays.sort(b);
		
		if((a[0] * 1.0 / b[0]) == (a[1] * 1.0 / b[1]) && (a[0] * 1.0 / b[0]) == (a[2] * 1.0 / b[2]))
			out.println("YES");
		else
			out.println("NO");
		
		out.flush();
		out.close();
	}
	
	static int dist(int x1, int y1, int x2, int y2) {
		return (x2 - x1) * (x2 - x1) + (y2 - y1) * (y2 - y1);
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