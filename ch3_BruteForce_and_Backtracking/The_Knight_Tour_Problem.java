package ch3_BruteForce_and_Backtracking;

import java.util.Arrays;

public class The_Knight_Tour_Problem {

	static int N = 8;
	
	public static void main(String[] args) {
		solveKT();
	}

	// A utility function to check if i,j are valid indices for N*N chess board
	static boolean isSafe(int x, int y, int sol[][]) {
		return (x >= 0 && x < N && y >= 0 && y < N && sol[x][y] == -1);
	}
	
	static void printSolution(int sol[][]) {
		for(int [] a : sol) System.out.println(Arrays.toString(a));
	}
	
	/* This function solves the Knight Tour problem
    using Backtracking.  This  function mainly
    uses solveKTUtil() to solve the problem. It
    returns false if no complete tour is possible,
    otherwise return true and prints the tour.
    Please note that there may be more than one
    solutions, this function prints one of the
    feasible solutions.  */
	
	private static boolean solveKT() {
		int sol[][] = new int[8][8];
		for(int i = 0; i < N; ++i) Arrays.fill(sol[i], -1);
		/* xMove[] and yMove[] define next move of Knight.
        xMove[] is for next value of x coordinate
        yMove[] is for next value of y coordinate */
		int xMove[] = {2, 1, -1, -2, -2, -1, 1, 2};
        int yMove[] = {1, 2, 2, 1, -1, -2, -2, -1};
        // Since the Knight is initially at the first block
        sol[0][0] = 0;
        /* Start from 0,0 and explore all tours using
        solveKTUtil() */
        if (!solveKTUtil(0, 0, 1, sol, xMove, yMove)) {
            System.out.println("Solution does not exist");
            return false;
        } else
            printSolution(sol);
 
        return true;
	}
	/* A recursive utility function to solve Knight
    Tour problem */
	private static boolean solveKTUtil(int x, int y, int move, int[][] sol,
			int[] xMove, int[] yMove) {

		int k, next_x, next_y;
        if (move == N * N)
            return true;
        for (k = 0; k < 8; k++) {
        	next_x = x + xMove[k]; next_y = y + yMove[k];
        	if(isSafe(next_x, next_y, sol)){
        		sol[next_x][next_y] = move;
        		if(solveKTUtil(next_x, next_y, move + 1, sol, xMove, yMove))
        			return true;
        		else
        			sol[next_x][next_y] = -1;
        	}
        	
        }
        return false;
	}
}
