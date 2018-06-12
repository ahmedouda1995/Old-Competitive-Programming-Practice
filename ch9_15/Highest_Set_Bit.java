package ch9_15;

import java.util.LinkedList;
import java.util.Queue;

public class Highest_Set_Bit {

	public static void main(String[] args) {
		 	int n = 3, k = 3;
			
			//System.out.println(((n & ~(Integer.highestOneBit(n))) << 1) | 1);
			
			Queue<Integer> q = new LinkedList<Integer>();
			for(int i = 1; i <= n; ++i) q.add(i);
			
			while(q.size() != 1){
				for(int i = 0; i < k - 1; ++i)
					q.offer(q.poll());
				q.poll();
			}
			System.out.println(q.poll());
	}

	private static int hsb(int i) {
		i |= (i >>  1);
	    i |= (i >>  2);
	    i |= (i >>  4);
	    i |= (i >>  8);
	    i |= (i >> 16);
	    return i - (i >>> 1);
	}
}
