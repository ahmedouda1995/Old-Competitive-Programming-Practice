package dp_gym;

import java.util.ArrayList;

public class BinarySearch {

	static int a[] = {1, 3, 3, 3, 5, 7, 7, 8};
	
	public static void main(String[] args) {
		//System.out.println(binarySearch(7, 0, a.length));
		//System.out.println(floor(3));
		System.out.println(ceil(6));
	}

	private static int binarySearch(int key, int l, int r) {
		int m;
		
		while(r - l > 1) {
			m = l + (r - l) / 2;
			
			if(a[m] <= key)
				l = m;
			else
				r = m;
		}
		if(a[l] == key) return l;
		
		return -1;
	}
	
	static int floorUtil(int key, int l, int r)
	{
	    int m;
	 
	    while( r - l > 1 )
	    {
	        m = l + (r - l)/2;
	 
	        if(a[m] <= key)
	            l = m;
	        else
	            r = m;
	    }
	 
	    return l;
	}
	 
	// Initial call
	static int floor(int key)
	{
	    // Add error checking if key < A[0]
	    if( key < a[0] )
	        return -1;
	 
	    // Observe boundaries
	    return floorUtil(key, 0, a.length);
	}
	
	static int ceil(int key) {
		if(key > a[a.length - 1]) return -1;
		return ceilUtil(-1, a.length - 1, key);
	}

	private static int ceilUtil(int l, int r, int key) {
		int m;
		
		while(r - l > 1) {
			m = l + (r - l) / 2;
			
			if(a[m] >= key) r = m;
			else l = m;
		}
		
		return r;
	}
	
	static int bs(ArrayList<Integer> taken, int w) {
		int ans = taken.size(), lo = 0, hi = taken.size() - 1;

		while(lo <= hi)
		{
			int mid = lo + (hi - lo) / 2;
			if(taken.get(mid) > w)
			{
				ans = mid;
				hi = mid - 1;
			}
			else
				lo = mid + 1;
		}
		return ans;
	}
}
