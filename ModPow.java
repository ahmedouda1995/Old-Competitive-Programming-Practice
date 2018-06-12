
public class ModPow {

	static final int mod = (int)1e6 + 3;
	
	public static void main(String[] args) {
		System.out.println(modPow(2, 7));
	}

	private static long modPow(long b, int e) {  //  O(log(e))
		
		long res = 1;
		b %= mod;
		
		while(e > 0) {
			if((e & 1) == 1) res =  (res * b) % mod;
			
			e >>= 1;
			b = (b * b) % mod;
		}
		return res;
	}
}
