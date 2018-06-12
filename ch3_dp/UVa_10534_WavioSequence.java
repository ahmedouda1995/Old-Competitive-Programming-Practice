package ch3_dp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

public class UVa_10534_WavioSequence {

	static int a[] = new int[10000];
	static int dpAsc[] = new int[10000];
	static int dpDesc[] = new int[10000];
	
	public static void main(String[] args) throws IOException{
		Scanner sc = new Scanner(new FileReader("input.txt"));
		PrintWriter out = new PrintWriter(System.out);
		
		String s; int n;
		
		while((sc.hasNextInt())) {
			n = sc.nextInt();
			
			if(n == 0)
				out.println(0);
			else {
				for(int i = 0; i < n; ++i) a[i] = sc.nextInt();
				
				lisAsc(n); lisDesc(n);
				//System.out.println(Arrays.toString(dpAsc));
				//System.out.println(Arrays.toString(dpDesc));
				int max = 0;
				for(int i = 0 ; i < n; ++i) {
					
					max = Math.max(Math.min(dpAsc[i], dpDesc[i]) * 2 - 1, max);
				}
				
				out.println(max);
			}
		}
		
		out.flush();
		out.close();
	}
	
	private static void lisAsc(int n) {
		ArrayList<Integer> L = new ArrayList<Integer>();
		
		for(int i = 0; i < n; ++i) {
			int pos = Collections.binarySearch(L, a[i]);
			if(pos < 0) pos = -(pos + 1);
			if(pos >= L.size()) {
				L.add(a[i]);
				dpAsc[i] = pos + 1;
			}
			else {
				L.set(pos, a[i]);
				dpAsc[i] = pos + 1;
			}
		}
	}

	private static void lisDesc(int n) {
		ArrayList<Integer> L = new ArrayList<Integer>();
		
		for(int i = n - 1; i >= 0; --i) {
			int pos = Collections.binarySearch(L, a[i]);
			if(pos < 0) pos = -(pos + 1);
			if(pos >= L.size()) {
				L.add(a[i]);
				dpDesc[i] = pos + 1;
			}
			else {
				L.set(pos, a[i]);
				dpDesc[i] = pos + 1;
			}
		}
	}
}