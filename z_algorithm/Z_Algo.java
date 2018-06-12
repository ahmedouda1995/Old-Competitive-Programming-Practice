package z_algorithm;

import java.util.Arrays;
import java.util.Scanner;

public class Z_Algo {

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		int n;
		String str = sc.nextLine(); n = str.length();
		str += "#" + sc.nextLine();
		//System.out.println(Arrays.toString(zAlg(str.toCharArray())));
		int z[] = zAlg(str.toCharArray());
		int res = 0;
		for(int i = 0; i < z.length; ++i) if(z[i] == n) res++;
		System.out.println(res);
	}

	private static int[] zAlg(char[] s)
	{
		int n = s.length;
		int z[] = new int[n];
		
		for(int i = 1, L = 0, R = 0; i < n; ++i)
		{
			if(R < i)
			{
				L = R = i;
				while(R < n && s[R] == s[R - L]) R++;
				z[i] = R - L; R--;
			}
			else
			{
				int k = i - L;
				if(i + z[k] <= R) z[i] = z[k];
				else
				{
					L = i;
					while(R < n && s[R] == s[R - L]) R++;
					z[i] = R - L; R--;
				}
			}
		}
		return z;
	}
}
