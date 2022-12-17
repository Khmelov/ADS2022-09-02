package by.it.group151004.burbouski.lesson12;

import java.util.Arrays;

public class TaskB {
	static final int INF = 1000000000;
	static int[][] g = new int[][] {
	/* */   /*a    b    c    d    e    f    g    h    i    s(j)*/
	/*a*/	{ INF, 4,   -2,  INF, INF, INF, INF, INF, INF, INF },
	/*b*/	{ INF, INF, INF, INF, INF, INF, -2 , -4 , INF, INF },
	/*c*/	{ INF, INF, INF, 2  , INF, 1  , INF, INF, INF, INF },
	/*d*/	{ INF, INF, INF, INF, INF, INF, INF, INF, INF, INF },
	/*e*/	{ INF, INF, INF, INF, INF, -2 , INF, 3  , INF, INF },
	/*f*/	{ INF, INF, INF, 3  , INF, INF, INF, INF, INF, INF },
	/*g*/	{ INF, INF, INF, INF, INF, INF, INF, INF, -1 , INF },
	/*h*/	{ INF, INF, INF, INF, INF, INF, 1  , INF, INF, INF },
	/*i*/	{ INF, INF, INF, INF, INF, INF, INF, 1  , INF, INF },
	/*g*/	{ 7  , INF, 6  , INF, 6  , 5  , INF, INF, INF, INF }};
	static int[] dist = new int[10];
	static int[] path = new int[10];

	static void bellmanFord(int s) {
		Arrays.fill(dist, INF);
		Arrays.fill(path, -1);
		dist[s] = 0;

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < 10; k++) {
					if (dist[k] > dist[j] + g[j][k]) {
						dist[k] = dist[j] + g[j][k];
						path[k] = j;
					}
				}
			}
		}
	}

	static void printPath(int s, int e) {
		if (s == e) {
			System.out.print((char) (s + 'A') + " ");
		} else if (path[e] == -1) {
			System.out.println("No path from " + (char) (s + 'A') + " to " + (char) (e + 'A'));
		} else {
			printPath(s, path[e]);
			System.out.print((char) (e + 'A') + " ");
		}
	}

	public static void main(String[] args) {
		bellmanFord(0);

		for (int i = 0; i < 10; i++) {
			System.out.print("Shortest path from A to " + (char) (i + 'A') + ": ");
			printPath(0, i);
			System.out.println("\nDistance: " + dist[i]);
		}
	}
}