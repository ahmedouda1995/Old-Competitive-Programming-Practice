package data_structures;

import java.util.Arrays;

public class SegmentTree_simple_app_p1 {

	int N;
	SegmentTreeNode nodes[];
	
	public SegmentTree_simple_app_p1(int arr[], int N) {
		this.N = N;
		nodes = new SegmentTreeNode[getSegmentTreeSize(N)];
		for(int i = 0; i < nodes.length; ++i) nodes[i] = new SegmentTreeNode();
		buildTree(arr, 1, 0, N - 1);
	}
	
	static class SegmentTreeNode {
		int data;
		
		public SegmentTreeNode() {}
		
		public SegmentTreeNode(int data) {
			this.data = data;
		}
		
		void assignLeaf(int value) {
		    this.data = value;
		}
		
		void merge(SegmentTreeNode left, SegmentTreeNode right) {
		    int l =  left.data;
		    int r =  right.data;
		    this.data = Math.max(l, r);
		 }
		
		 int getValue() {
			return this.data;
		}
		 
		 @Override
		public String toString() {
			return Integer.toString(this.data);
		}
	}
	
	int getSegmentTreeSize(int n) {
		int size = 1;
		while(size < n) size <<= 1;
		return size << 1;
	}
	
	void buildTree(int arr[], int stIndex, int lo, int hi) {
		if(lo == hi)
			nodes[stIndex].assignLeaf(arr[lo]);
		else {
			int left = 2 * stIndex, right = left + 1;
			int mid = lo + (hi - lo) / 2;
			buildTree(arr, left, lo, mid);
			buildTree(arr, right, mid + 1, hi);
			nodes[stIndex].merge(nodes[left], nodes[right]);
		}
	}
	
	// V is the type of the required aggregate statistic
	int getValue(int lo, int hi) {
	  SegmentTreeNode result = getValue(1, 0, N-1, lo, hi);
	  return result.getValue();
	}
	
	private SegmentTreeNode getValue(int stIndex, int left, int right, int lo, int hi) {
		if (left == lo && right == hi)
		    return nodes[stIndex];
		
		int mid = left + (right - left) / 2;
		if (lo > mid)
			return getValue(2*stIndex+1, mid+1, right, lo, hi);
		if (hi <= mid)
		    return getValue(2*stIndex, left, mid, lo, hi);
		
		SegmentTreeNode leftResult = getValue(2*stIndex, left, mid, lo, mid);
		SegmentTreeNode rightResult = getValue(2*stIndex+1, mid+1, right, mid+1, hi);
		SegmentTreeNode result = new SegmentTreeNode();
		result.merge(leftResult, rightResult);
		return result;
	}
	
	void update(int index, int value) {
		  update(1, 0, N-1, index, value);
	}

	private void update(int stIndex, int lo, int hi, int index, int value) {
		if(lo == hi) nodes[stIndex].assignLeaf(value);
		else {
			int mid = lo + (hi - lo) / 2;
			int left = 2 * stIndex, right = left + 1;
			
			if(index > mid)
				update(right, mid + 1, hi, index, value);
			else
				update(left, lo, mid, index, value);
			
			nodes[stIndex].merge(nodes[left], nodes[right]);
		}
	}

	public static void main(String[] args) {
		int [] arr = {1, 5, 4, 6, 9};
		SegmentTree_simple_app_p1 st = new SegmentTree_simple_app_p1(arr, 5);
		System.out.println(Arrays.toString(st.nodes));
		st.update(3, -1);
		System.out.println(Arrays.toString(st.nodes));
		System.out.println(st.getValue(1, 3));
	}
}
