package ch3_Divide_and_Conquer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_00183_Bit_Maps {
	
	static int counter;
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter("output.txt");
		
		String s = "";
		boolean done = false, haveIt = false;
		while(!done){
			if(!haveIt){
				if((s = sc.nextLine()).equals("#"))
					break;
			}
			StringTokenizer st = new StringTokenizer(s);
			String tmp = st.nextToken();
			int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken());
			int size = n * m;
			String ref = "";
			if(tmp.equals("B")){
				haveIt = false;
				if(n == m && n == 0){
					out.print("D"); out.printf("% 4d", n); out.printf("% 4d\n", m);
					out.println();
				}
				else {
					while(ref.length() < size)
						ref += sc.nextLine();
					char [][] matrix = new char[n][m];
					int k = 0;
					for(int i = 0; i < n; ++i)
						for(int j = 0; j < m; ++j)
							matrix[i][j] = ref.charAt(k++);
					out.print("D"); out.printf("% 4d", n); out.printf("% 4d\n", m);
					String res = b(matrix, 0, 0, n - 1, m - 1);
					while(res.length() > 50){
						out.println(res.substring(0, 50));
						res = res.substring(50);
					}
					if(res.length() > 0)
						out.println(res);
				}
			}
			else {
				if(n == m && n == 0){
					out.print("B"); out.printf("% 4d", n); out.printf("% 4d\n", m);
					out.println();
				} else {
					ref = "";
					tmp = "";
					while(!(tmp = sc.nextLine()).equals("#") && !tmp.contains(" "))
						ref += tmp;
					out.print("B"); out.printf("% 4d", n); out.printf("% 4d\n", m);
					
					String res = "";
					char [][] matrix = new char[n][m];
					counter = 0;
					d(matrix, ref, 0, 0, n - 1, m - 1);
					for(int j = 0; j < n; ++j){
						for (int k = 0; k < m; ++k) {
							if(res.length() == 50){
								out.println(res);
								res = "";
							}
							res += matrix[j][k];
						}
					}
					if(res.length() > 0)
						out.println(res);
					
					if(tmp.equals("#"))
						done = true;
					else {
						s = tmp;
						haveIt = true;
					}
				}
				
			}
		}
		
		out.flush();
		out.close();
	}
	

	private static void d(char[][] matrix, String ref, int sr, int sc, int er, int ec) {
		if(counter == ref.length())
			return;
		if(ref.charAt(counter) != 'D'){
			
			for (int j = sr; j <= er; j++) {
				for (int k = sc; k <= ec; k++) {
					matrix[j][k] = ref.charAt(counter);
				}
			}
			counter++;
		}
		else {
			counter++;
			int midRow = (sr + er) / 2, midCol = (sc + ec) / 2;
			if(ec - sc > 0){
				d(matrix, ref, sr, sc, midRow, midCol);
				d(matrix, ref, sr, midCol + 1, midRow, ec);
			}
			if(er - sr > 0 && ec - sc > 0){
				d(matrix, ref, midRow + 1, sc, er, midCol);
				d(matrix, ref, midRow + 1, midCol + 1, er, ec);
			}
			else if(ec - sc == 0){
				d(matrix, ref, sr, sc, midRow, midCol);
				d(matrix, ref, midRow + 1, sc, er, ec);
			}
		}
		
	}


	private static String b(char[][] matrix, int sr, int sc, int er, int ec) {
		String s = "";
		char c = check(matrix, sr, sc, er, ec);
		if(c != 'D')
			s += c;
		else {
			s += "D";
			int midRow = (sr + er) / 2, midCol = (sc + ec) / 2;
			if(ec - sc > 0){
				s += b(matrix, sr, sc, midRow, midCol);
				s += b(matrix, sr, midCol + 1, midRow, ec);
			}
			if(er - sr > 0 && ec - sc > 0){
				s += b(matrix, midRow + 1, sc, er, midCol);
				s += b(matrix, midRow + 1, midCol + 1, er, ec);
			}
			else if(ec - sc == 0){
				s += b(matrix, sr, sc, midRow, midCol);
				s += b(matrix, midRow + 1, sc, er, ec);
			}
		}
		return s;
	}

	private static char check(char [][] m, int sr, int sc, int er, int ec){
		char c = m[sr][sc];
		for(int i = sr; i <= er; ++i){
			for(int j = sc; j <= ec; ++j){
				if(m[i][j] != c)
					return 'D';
			}
		}
		return c;
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