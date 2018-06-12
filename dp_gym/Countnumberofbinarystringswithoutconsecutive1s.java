package dp_gym;

public class Countnumberofbinarystringswithoutconsecutive1s {

	public static void main(String[] args) {
		System.out.println(solve(5));
	}

	private static int solve(int n) {
		if(n == 0) return 0;
		
		int a[] = new int[n + 1];
		int b[] = new int[n + 1];
		a[1] = 1; b[1] = 1;
		
		for(int i = 2; i <= n; ++i) {
			a[i] = a[i - 1] + b[i - 1];
			b[i] = a[i - 1];
		}
			
		return a[n] + b[n];
	}
}
