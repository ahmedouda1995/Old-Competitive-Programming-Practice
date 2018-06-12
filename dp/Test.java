package dp;

import java.util.ArrayList;

public class Test {

	public static void main(String[] args) {
		ArrayList<Integer> a = new ArrayList<Integer>();
		rec(a, 0);
	}

	private static void rec(ArrayList<Integer> a, int n) {
		if(n > 1){
			System.out.println(a);
			return;
		}
		a.add(n);
		rec(a, n+2);
		rec(a, n+1);
		
	}
	
	
}
