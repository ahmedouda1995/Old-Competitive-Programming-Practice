package acp_dp;

public class TheNumberGameDiv2 {

	static String s, rev, pat;
	
	public static int minimumMoves(int A, int B) {
		s = Integer.toString(A);
		pat = Integer.toString(B);
		rev = "";
		for(int i = s.length() - 1; i >= 0; i--) rev += s.charAt(i);
		
		if(!s.contains(pat) && !rev.contains(pat))
			return -1;
		return solve();
	}

	private static int solve() {
		int min = 100;
		
		if(s.contains(pat)) {
			int pos = s.indexOf(pat);
			int tmp = (pos != 0)?2 + pos:0;
			min = Math.min(min, tmp + s.length() - (pos + pat.length()));
		}
		
		if(rev.contains(pat)) {
			int pos = rev.indexOf(pat);
			int tmp = 1 + ((pos != 0)?pos:0);
			min = Math.min(min, tmp + s.length() - (pos + pat.length()));
		}
		return min;
	}
	
	public static void main(String[] args) {
		System.out.println(minimumMoves(25, 5));
	}
}
