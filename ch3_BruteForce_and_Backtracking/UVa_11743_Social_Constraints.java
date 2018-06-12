package ch3_BruteForce_and_Backtracking;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class UVa_11743_Social_Constraints {
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		int n, m; int [] arr; Triple cond[];
		while((n = sc.nextInt()) != 0 | (m = sc.nextInt()) != 0){
			arr = new int[n]; for(int i = 0; i < n; ++i) arr[i] = i;
			cond = new Triple[m]; for(int i = 0; i < m; ++i) cond[i] = new Triple(sc.nextInt(), sc.nextInt(), sc.nextInt());
			out.println(permute(arr, cond));
		}
		
		out.flush();
		out.close();
	}
	
	static class Triple{
		int x, y, z;
		public Triple(int x, int y, int z) {
			this.x = x; this.y = y; this.z = z;
		}
	}
	
	private static int permute(int[] arr, Triple[] cond) {
		return permute(0, arr, cond);
	}

	private static int permute(int i, int[] arr, Triple[] cond) {
		int res = 0;
		if(i == arr.length - 1){
			for(int j = 0; j < cond.length; ++j) {
				if(cond[j].z < 0){
					int [] p = index(arr, cond[j].x, cond[j].y);
					int p1 = p[0], p2 = p[1];
					if(Math.abs(p1 - p2) < -cond[j].z)
						return 0;
				}
				else {
					int [] p = index(arr, cond[j].x, cond[j].y);
					int p1 = p[0], p2 = p[1];
					if(Math.abs(p1 - p2) > cond[j].z)
						return 0;
				}
			}
			return 1;
		}
		for(int j = i; j < arr.length; ++j){
			swap(i, j, arr);
			res += permute(i + 1, arr, cond);
			swap(i, j, arr);
		}
		return res;
	}

	private static int[] index(int[] arr, int x, int y) {
		int [] p = new int[2];
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] == x)
				p[0] = i;
			if(arr[i] == y)
				p[1] = i;
		}
		return p;
	}

	public static void swap(int i, int j, int [] a){
		int tmp = a[i];
		a[i] = a[j];
		a[j] = tmp;
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