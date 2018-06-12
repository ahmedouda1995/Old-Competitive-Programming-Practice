package dp_gym;

import java.util.Stack;

public class LargestRectangularAreainaHistogram {

	static int histogram[] = {0, 2, 2, 2};
	static int N = histogram.length;
	
	public static void main(String[] args) {
		System.out.println(solve());
	}

	private static int solve() {
		Stack<Integer> st = new Stack<Integer>();
		
		int maxArea = 0;
		int i = 0;
		
		while(i < N) {
			if(st.isEmpty() || histogram[st.peek()] <= histogram[i])
				st.push(i++);
			else {
				while(!st.isEmpty() && histogram[st.peek()] > histogram[i]) {
					int top = st.pop();
					maxArea = Math.max(maxArea, histogram[top] * ((st.isEmpty())?i:(i - st.peek() - 1)));
				}
			}
		}
		
		while(!st.isEmpty()) {
			int top = st.pop();
			maxArea = Math.max(maxArea, histogram[top] * ((st.isEmpty())?i:(i - st.peek() - 1)));
		}
		
		return maxArea;
	}
}
