package ch2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

// Runtime error if you do not check if DS is not empty before removing from it

public class Uva_11995_I_Can_Guess_the_Data_Structure {
	public static void main(String[] args) throws IOException{
		PrintWriter out = new PrintWriter(System.out);
		
		BufferedReader br = new BufferedReader(new FileReader("input.txt"));
		String s;
		Stack<Integer> stack = new Stack<Integer>();
		Queue<Integer> q = new LinkedList<Integer>();
		PriorityQueue<Integer> pq = new PriorityQueue<Integer>(new Comparator<Integer>() {

			@Override
			public int compare(Integer o1, Integer o2) {
				return o2 - o1;
			}
		});
		boolean isStack, isQueue, isPq;
		while((s = br.readLine()) != null){
			int n = Integer.parseInt(s);
			stack.clear(); q.clear(); pq.clear();
			isStack = isQueue = isPq = true;
			for (int i = 0; i < n; ++i) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				if(Integer.parseInt(st.nextToken()) == 1){
					int item = Integer.parseInt(st.nextToken());
					if(isStack)
						stack.push(item);
					if(isQueue)
						q.offer(item);
					if(isPq)
						pq.offer(item);
				}
				else {
					int item = Integer.parseInt(st.nextToken());
					if(isStack && (stack.isEmpty() || stack.pop() != item))
						isStack = false;
					if(isQueue && (q.isEmpty() || q.poll() != item))
						isQueue = false;
					if(isPq && (pq.isEmpty() || pq.poll() != item))
						isPq = false;
				}
			}
			if(!isStack && !isQueue && !isPq)
				out.println("impossible");
			else if((isStack && isQueue) || (isQueue && isPq) || (isStack && isPq))
				out.println("not sure");
			else if(isStack)
				out.println("stack");
			else if(isQueue)
				out.println("queue");
			else
				out.println("priority queue");
		}
		
		out.flush();
		out.close();
	}
}

