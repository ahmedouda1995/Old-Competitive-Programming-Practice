package acp_dp;

import java.util.Arrays;

public class MatchNumbersHard {

	// not complete
	
	static int N;
	static long arr[];
	
	public static String[] maxNumber(String[] matches, String n) {
		N = matches.length;
		arr = new long[N];
		long min = Long.MAX_VALUE;
		int minNumber = -1;
		for(int i = 0; i < N; ++i) {
			arr[i] = Long.parseLong(matches[i]);
			if(arr[i] <= min) {
				min = arr[i];
				minNumber = i;
			}
		}
		long num = Long.parseLong(n);
		if(num < min) return new String[]{"0", "", ""};
		StringBuilder sb = new StringBuilder();
		if(minNumber == 0 && N > 1) {
			long min2 = Long.MAX_VALUE, minNumber2 = 1;
			for(int i = 1; i < N; ++i) {
				if(arr[i] < min2) {
					min2 = arr[i]; minNumber2 = i;
				}
			}
			if(num >= min2) {
				num -= min2;
				sb.append(minNumber2);
			}
		}
		while(num >= min) { sb.append(minNumber); num -= min; }
		for(int i = 0; i < sb.length(); ++i) {
			for(int j = N - 1; j > 0; --j) {
				long tmp = arr[j] - (arr[(int) (sb.charAt(i) - '0')]);
				if(tmp <= num) {
					sb.replace(i, i + 1, Integer.toString(j));
					num -= tmp;
					break;
				}
			}
		}
		
		if(sb.charAt(0) == '0')
			return new String[]{"1", "0", "0"};
		else if(sb.length() <= 50)
			return new String[]{sb.length() + "", sb.toString(), sb.toString()};
		else
			return new String[]{sb.length() + "", sb.substring(0, 50), sb.substring(50, sb.length())};
	}
	
	public static void main(String[] args) {
		System.out.println(Arrays.toString(maxNumber(new String[]{"1", "10"}, "1000000")));
	}
}
