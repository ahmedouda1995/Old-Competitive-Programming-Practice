package apsp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class UVa_12319_EdgetownsTrafficJams {

	static int INF = (int) 1e9;
	static int bef[][] = new int[100][100];
	static int aft[][] = new int[100][100];
	static int N;
	
	public static void main(String[] args) throws IOException{
		PrintWriter out = new PrintWriter(System.out);
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		StringTokenizer st;
		
		String s;
		
		while(!(s = br.readLine()).equals("0")) {
			N = Integer.parseInt(s);
			int a, b;
			
			for(int i = 0; i < N; ++i) {
				Arrays.fill(bef[i], INF);
				Arrays.fill(aft[i], INF);
				bef[i][i] = 0; aft[i][i] = 0;
			}
			
			for(int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				
				while(st.hasMoreTokens()) {
					b = Integer.parseInt(st.nextToken());
					bef[a - 1][b - 1] = 1;
				}
			}
			for(int i = 0; i < N; ++i) {
				st = new StringTokenizer(br.readLine());
				a = Integer.parseInt(st.nextToken());
				
				while(st.hasMoreTokens()) {
					b = Integer.parseInt(st.nextToken());
					aft[a - 1][b - 1] = 1;
				}
			}
			
			st = new StringTokenizer(br.readLine());
			
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			floyd(true); floyd(false);
			
			if(check(a, b))
				out.println("Yes");
			else
				out.println("No");
		}
		
		out.flush();
		br.close();
		out.close();
	}

	private static boolean check(int a, int b) {
		for(int i = 0; i < N; ++i)
			for(int j = 0; j < N; ++j) {
				if(aft[i][j] == INF)
					return false;
				else if(aft[i][j] > a * bef[i][j] + b)
					return false;
			}
		return true;
	}

	private static void floyd(boolean b) {
		int [][] a; a = (b)?bef:aft;
		 
		for(int k = 0; k < N; ++k)
			for(int i = 0; i < N; ++i)
				for(int j = 0; j < N; ++j)
					a[i][j] = Math.min(a[i][j], a[i][k] + a[k][j]);
	}
}