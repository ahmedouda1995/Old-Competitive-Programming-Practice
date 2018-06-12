package ch9_14;

import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;



public class UVa_10327_FlipSort {

	public static void main(String[]args) throws IOException  {

			Scanner sc=new Scanner(new FileReader("input.txt"));
			PrintWriter out = new PrintWriter(System.out);
			
			while (sc.hasNext()) {

				int count=sc.nextInt();

				int swapCount;

					int [] values=new int [count];

					for (int i=0;i<values.length;i++) {

						values[i]=sc.nextInt();

					}
					int [] b = new int[count];
					swapCount = mergeInv(values, 0, count - 1, b);

				out.printf("Minimum exchange operations : %d\n", swapCount);

			}
			out.flush();
			out.close();
			sc.close();
	}
	
	private static int mergeInv(int[] a, int l, int h, int[] b) {
		int inv = 0;
		
		if(l < h) {
			int mid = (l + h) / 2;
			inv += mergeInv(a, l, mid, b);
			inv += mergeInv(a, mid + 1, h, b);
			inv += merge(a, l, mid, h, b);
		}
		
		return inv;
	}

	private static int merge(int[] a, int l, int mid, int h, int[] b) {
		int inv = 0;
		
		for(int i = l, j = mid + 1, k = l; k <= h; ++k) {
			if(i > mid)
				b[k] = a[j++];
			else if(j > h)
				b[k] = a[i++];
			else {
				if(a[i] <= a[j])
					b[k] = a[i++];
				else {
					b[k] = a[j++];
					inv += (mid - i + 1);
				}
			}
		}
		
		
		System.arraycopy(b, l, a, l, (h - l + 1));
		return inv;
	}

}