package segment_trees;

import java.util.Arrays;

public class SegmentTree {

	int n, arr[], sTree[], lazy[];
	
	public SegmentTree(int in[]) {
		n = in.length; arr = in;
		int sz = getSz(n);
		sTree = new int[sz];
		lazy = new int[sz];
		build(1, 0, n - 1);
	}
	
	private int getSz(int n) {
		int sz = 1;
		while(sz < n) sz <<= 1;
		return sz <<= 1;
	}

	void build(int node, int b, int e) {
		if(b == e)
			sTree[node] = arr[b];
		else
		{
			int mid = (b + e >> 1);
			build(node << 1, b, mid);
			build(node << 1 | 1, mid + 1, e);
			sTree[node] = sTree[node << 1] + sTree[node << 1 | 1];
		}
	}
	
	void updatePoint(int idx, int val)
	{
		updatePoint(1, idx, 0, n - 1, val);
	}

	void updatePoint(int node, int idx, int b, int e, int val) {
		if(b == e) sTree[node] += val;
		else
		{
			int mid = (b + e >> 1);
			if(idx <= mid)
				updatePoint(node << 1, idx, b, mid, val);
			else
				updatePoint(node << 1 | 1, idx, mid + 1, e, val);
			sTree[node] = sTree[node << 1] + sTree[node << 1 | 1];
		}
	}
	
	void updateRange(int lo, int hi, int val)
	{
		updateRange(1, 0, n - 1, lo, hi, val);
	}
	
	void updateRange(int node, int b, int e, int lo, int hi, int val)
	{
		if(e < lo || b > hi) return;
		if(b >= lo && e <= hi)
		{
			sTree[node] += (e - b + 1) * val;
			lazy[node] += val;
		}
		else
		{
			int mid = (b + e >> 1);
			propagate(node, b, mid, e);
			updateRange(node << 1, b, mid, lo, hi, val);
			updateRange(node << 1 | 1, mid + 1, e, lo, hi, val);
			sTree[node] = sTree[node << 1] + sTree[node << 1 | 1];
		}
	}

	private void propagate(int node, int b, int mid, int e) {
		sTree[node << 1] += (mid - b + 1) * lazy[node];
		lazy[node << 1] += lazy[node];
		sTree[node << 1 | 1] += (e - mid) * lazy[node];
		lazy[node << 1 | 1] += lazy[node];
		lazy[node] = 0;
	}
	
	int query(int lo, int hi)
	{
		return query(1, 0, n - 1, lo, hi);
	}

	private int query(int node, int b, int e, int lo, int hi) {
		if(e < lo || b > hi) return 0;
		if(b >= lo && e <= hi)
			return sTree[node];
		else
		{
			int mid = (b + e >> 1);
			propagate(node, b, mid, e);
			int left = query(node << 1, b, mid, lo, hi);
			int right = query(node << 1 | 1, mid + 1, e, lo, hi);
			return left + right;
		}
	}
}
