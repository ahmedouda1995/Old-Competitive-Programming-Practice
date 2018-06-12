package data_structures;

public class SegmentTreeI {

	int N;
	STNode nodes[];
	
	public SegmentTreeI(int [] arr, int N) {
		this.N = N;
		nodes = new STNode[getSTSize(N)];
		buildST(arr, 1, 0, N - 1);
	}
	
	int getSTSize(int n) {
		int size = 1;
		while(size < n) size <<= 1;
		return size << 1;
	}
	
	private void buildST(int [] arr, int stIdx, int lo, int hi) {
		if(lo == hi) {
			nodes[stIdx] = new STNode();
			nodes[stIdx].assignLeaf(arr[lo]);
		}
		else {
			int left = (stIdx << 1), right = left + 1;
			int mid = lo + (hi - lo) / 2;
			
			buildST(arr, left, lo, mid);
			buildST(arr, right, mid + 1, hi);
			nodes[stIdx] = new STNode();
			nodes[stIdx].merge(nodes[left], nodes[right]);
		}
	}

	int getValue(int lo, int hi) {
		STNode result = getValue(1, 0, N - 1, lo, hi);
		return result.getValue();
	}
	
	private STNode getValue(int stIdx, int left, int right, int lo, int hi) {
		if(left == lo && right == hi) return nodes[stIdx];
		else {
			int mid = left + (right - left) / 2;
			
			if(lo > mid)
				return getValue((stIdx << 1) + 1, mid + 1, right, lo, hi);
			if(hi <= mid)
				return getValue((stIdx << 1), left, mid, lo, hi);
			
			STNode leftRes = getValue((stIdx << 1), left, mid, lo, mid);
			STNode rightRes = getValue((stIdx << 1) + 1, mid + 1, right, mid + 1, hi);
			STNode res = new STNode();
			res.merge(leftRes, rightRes);
			return res;
		}
	}

	void update(int index, int value) {
		  update(1, 0, N-1, index, value);
	}
	
	private void update(int stIdx, int lo, int hi, int index, int value) {
		if(lo == hi) nodes[stIdx].assignLeaf(value);
		else {
			int mid = lo + (hi - lo) / 2;
			int left = (stIdx << 1), right = left + 1;
			
			if (index <= mid)
			    update(left, lo, mid, index, value);
			else
			    update(right, mid+1, hi, index, value);
			  
			  nodes[stIdx].merge(nodes[left], nodes[right]);
		}
	}

	static class STNode {

		int max;
		
		void assignLeaf(int val) { max = val; }
		
		void merge(STNode left, STNode right) {
			max = Math.max(left.max, right.max);
		}
		
		int getValue() { return max; }
	}
	
	public static void main(String[] args) {
		int [] arr = {-1, 2, 3, -1};
		SegmentTreeI st = new SegmentTreeI(arr, arr.length);
		System.out.println(st.getValue(0, 3));
	}
}