package ch6_4;

import java.util.Arrays;

public class KMP {

	static char [] text, pat;
	static int lps[];
	static int N, M;
	
	public static void main(String[] args) {
		text = "I DO NOT LIKE SEVENTY SEV BUT SEVENTY SEVENTY SEVEN".toCharArray();
		pat = "SEVENTY SEVEN".toCharArray();
		N = text.length; M = pat.length;
		lps = new int[M];
		kmpPreprocess();
		System.out.println(Arrays.toString(lps));
		kmpSearch();
	}

	private static void kmpPreprocess() {
		int i = 1, j = 0; lps[0] = 0;
		while(i < M) {
			if(pat[i] == pat[j]) lps[i++] = ++j;
			else if(j != 0) j = lps[j - 1];
			else lps[i++] = 0;
		}
	}

	private static void kmpSearch() {
		for(int i = 0, j = 0; i < N; ++i) {
			while(j > 0 && text[i] != pat[j]) j = lps[j - 1];
			if(text[i] == pat[j]) j++;
			if(j == M) {
				System.out.println(i - M + 1);
				j = lps[j - 1];
			}
		}
	}
}
