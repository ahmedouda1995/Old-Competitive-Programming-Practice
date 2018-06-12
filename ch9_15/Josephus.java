package ch9_15;

public class Josephus {

	public static void main(String[] args) {
		System.out.println(jp(5, 2));
	}
	
	private static int jp(int n, int k) {
		return jpUtil(n, k) + 1;
	}
	
	private static int jpUtil(int n, int k) {
		if(n == 1)
			return 0;
		return (jpUtil(n - 1, k) + k) % n;
	}
}
