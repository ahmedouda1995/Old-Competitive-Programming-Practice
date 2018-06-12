package ch4_7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

public class UVa103_StackingBoxes {

	static final int INF = (int) 1e9;
	static int N, K;
	static Box boxes[];
	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);

		while(sc.ready()) {
			K = sc.nextInt();
			N = sc.nextInt();
			
			boxes = new Box[K];
			
			int [] a;
			
			for(int i = 1; i <= K; ++i) {
				a = new int[N];
				for(int j = 0; j < N; ++j) a[j] = sc.nextInt();
				Arrays.sort(a);
				boxes[i - 1] = new Box(i, a);
			}
			
			Arrays.sort(boxes);
			
			int maxInd = 0;
			int lis[] = new int[K];
			int sol[] = new int[K];
			lis[0] = 1; sol[0] = -1;
			
			for(int i = 1; i < K; ++i) {
				lis[i] = 1; sol[i] = -1;
				for(int j = i - 1; j >= 0; j--) {
					if(boxes[i].fits(boxes[j]) && lis[j] + 1 > lis[i]) {
						lis[i] = lis[j] + 1;
						sol[i] = j;
					}
				}
				if(lis[maxInd] < lis[i]) maxInd = i;
			}
			out.println(lis[maxInd]);
			while(sol[maxInd] != -1) {
				out.print(boxes[maxInd].idx + " ");
				maxInd = sol[maxInd];
			}
			out.println(boxes[maxInd].idx);
		}

		out.flush();
		out.close();
	}
	
	static class Box implements Comparable<Box>{
		int idx;
		int[] arr;
		
		Box(int i, int [] a) {
			idx = i;
			arr = a;
		}

		@Override
		public int compareTo(Box b) {
			
			for(int i = 0; i < arr.length; ++i) {
				if(arr[i] > b.arr[i])
					return -1;
				else if(arr[i] < b.arr[i])
					return 1;
			}
			return 0;
		}
		
		boolean fits(Box b) {
			for(int i = 0; i < arr.length; ++i) {
				if(arr[i] >= b.arr[i])
					return false;
			}
			return true;
		}
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