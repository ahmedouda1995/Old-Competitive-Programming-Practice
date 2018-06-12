package ch3_BruteForce_and_Backtracking;

import java.util.Map.Entry;
import java.util.TreeMap;

public class String_Subset {

	public static void main(String[] args) {
		char [] input = "AA".toCharArray();
		TreeMap<Character, Integer> map = new TreeMap<Character, Integer>();
		for(char c : input){
			map.compute(c, (key, val) -> {
				if(val == null)
					return 1;
				else
					return val + 1;
			});
		}
		int [] count = new int[map.size()];
		char [] str = new char[map.size()];
		int i = 0;
		for(Entry<Character, Integer> e : map.entrySet()){
			str[i] = e.getKey();
			count[i++] = e.getValue();
		}
		char [] res = new char[input.length];
		solve(str, count, 0, 0, res);
	}

	private static void solve(char[] str, int[] count, int start, int len, char[] res) {
		print(res, len);
		for (int i = start; i < str.length; i++) {
			if(count[i] == 0)
				continue;
			res[len] = str[i]; count[i]--;
			solve(str, count, i, len + 1, res);
			count[i]++;
		}
	}

	private static void print(char[] res, int len) {
		for (int i = 0; i < len; i++) {
			System.out.print(res[i]);
		}
		System.out.println();
	}
}
