package data_structures;

public class PersistentSegmentTree {

	static int arr[];
	
	static class SegmentTree {
		
		Node version[];
		
		public SegmentTree() {
			version = new Node[1000];
		}
		
		void build(Node node, int lo, int hi) {
			if(lo == hi) node.val = arr[lo];
			else {
				int mid = (lo + hi) >> 1;
				node.left = new Node();
				node.right = new Node();
				build(node.left, lo, mid);
			    build(node.right, mid+1, hi);
			    node.val = node.left.val + node.right.val;
			}
		}
		
		int query(Node node, int left, int right, int lo, int hi) {
			if(left == lo && right == hi) return node.val;
			int mid = (left + right) >> 1;
			
			if(lo > mid)
				return query(node.right, mid + 1, right, lo, hi);
			else if(hi <= mid)
				return query(node.left, left, mid, lo, hi);
			
			int leftRes = query(node.left, left, mid, lo, mid);
			int rightRes = query(node.right, mid + 1, right, mid + 1, hi);
			return leftRes + rightRes;
		}
		
		void upgrade(Node prev, Node cur, int low, int high, int idx, int value) {
			if (idx > high || idx < low || low > high)
		        return;
			if(low == high) { cur.val = value; return; }
			int mid = (low + high) >> 1;
			 if (idx <= mid) {
				 cur.right = prev.right;
				 cur.left = new Node();
			     upgrade(prev.left, cur.left, low, mid, idx, value);
			 }
			 else {
				 cur.left = prev.left;
				 cur.right = new Node();
				 upgrade(prev.right, cur.right, mid + 1, high, idx, value);
			 }
			 cur.val = cur.left.val + cur.right.val;
		}
		
		static class Node {
			int val;
			Node left, right;
			
			public Node() {}
			public Node(Node l, Node r, int v) {
				left = l; right = r; val = v;
			}
		}
	}
	
	public static void main(String[] args) {
		arr = new int[]{1, 2, 3, 4, 5};
		
		SegmentTree st = new SegmentTree();
		
		SegmentTree.Node root = new SegmentTree.Node();
		st.build(root, 0, arr.length - 1);
		st.version[0] = root;
		
		st.version[1] = new SegmentTree.Node();
	    st.upgrade(st.version[0], st.version[1], 0, arr.length - 1, 4, 1);
	    
	    st.version[2] = new SegmentTree.Node();
	    st.upgrade(st.version[1], st.version[2], 0, arr.length - 1, 2, 10);
	    
	    System.out.print("In version 1 , query(0,4) : ");
	    System.out.println(st.query(st.version[1], 0, arr.length - 1, 0, 4));
	 
	    System.out.print("In version 2 , query(3,4) : ");
	    System.out.println(st.query(st.version[2], 0, arr.length - 1, 3, 4));
	 
	    System.out.print("In version 0 , query(0,3) : ");
	    System.out.println(st.query(st.version[0], 0, arr.length - 1, 0, 3));
	}
}