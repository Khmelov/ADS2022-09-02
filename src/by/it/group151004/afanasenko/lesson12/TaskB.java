package by.it.group151004.afanasenko.lesson12;

import java.util.Arrays;

public class TaskB {
	public int INF = 1000000000;
	public int[] dist = new int[10];
	public int[] path = new int[10];

	public int[][] matrix = {
			{ INF, 4, -2, INF, INF, INF, INF, INF, INF, INF },
			{ INF, INF, INF, INF, INF, INF, -2, -4, INF, INF },
			{ INF, INF, INF, 2, INF, 1, INF, INF, INF, INF },
			{ INF, INF, INF, INF, INF, INF, INF, INF, INF, INF },
			{ INF, INF, INF, INF, INF, -2, INF, 3, INF, INF },
			{ INF, INF, INF, 3, INF, INF, INF, INF, INF, INF },
			{ INF, INF, INF, INF, INF, INF, INF, INF, -1, INF },
			{ INF, INF, INF, INF, INF, INF, 1, INF, INF, INF },
			{ INF, INF, INF, INF, INF, INF, INF, 1, INF, INF },
			{ 7, INF, 6, INF, 6, 5, INF, INF, INF, INF } };

	public void bellmanFord(int s) {
		Arrays.fill(dist, INF);
		Arrays.fill(path, -1);
		dist[s] = 0;

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 10; j++) {
				for (int k = 0; k < 10; k++) {
					if (dist[k] > dist[j] + matrix[j][k]) {
						dist[k] = dist[j] + matrix[j][k];
						path[k] = j;
					}
				}
			}
		}
	}

	public void printPath(int s, int e) {
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
		TaskB sus = new TaskB();
		sus.bellmanFord(0);

		for (int i = 0; i < 10; i++) {
			System.out.print("Shortest path from A to " + (char) (i + 'A') + ": ");
			sus.printPath(0, i);
			System.out.println("\nDistance: " + sus.dist[i]);
		}
	}
}