package by.it.group151003.mytnik.lesson05;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Scanner;

public class A_QSort {
    private static class Segment implements Comparable<Segment> {
        private final int start;
        private final int stop;

        public Segment(int start, int stop) {
            if (start > stop) {
                throw new IllegalArgumentException("f ranked");
            }
            this.start = start;
            this.stop = stop;
        }

        public int getStart() {
            return start;
        }

        public int getStop() {
            return stop;
        }

        @Override
        public int compareTo(Segment o) {
            return this.start - o.start;
        }
    }


    public int[] getAccessory(InputStream stream) {
        Scanner scanner = new Scanner(stream);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        int m = scanner.nextInt();
        int[] points = new int[m];

        for (int i = 0; i < n; i++) {
            segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());
        }

        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }

        quickSwowt(segments, 0, segments.length - 1);

        return Arrays.stream(points)
                .map(point -> (int) Arrays.stream(segments)
                        .filter(segment -> segment.getStart() <= point && segment.getStop() >= point)
                        .count())
                .toArray();
    }

    private void quickSwowt(Segment[] segwments, int lweft, int right) {
        if (lweft < right) {
            int pwivot = pwartition(segwments, lweft, right);
            quickSwowt(segwments, lweft, pwivot - 1);
            quickSwowt(segwments, pwivot + 1, right);
        }
    }

    private void swaw(Segment[] segwments, int i, int j) {
        Segment twemp = segwments[i];
        segwments[i] = segwments[j];
        segwments[j] = twemp;
    }

    private int pwartition(Segment[] segwments, int lweft, int right) {
        int pwivot = segwments[right].getStart();
        int i = (lweft - 1);
        for (int j = lweft; j < right; j++) {
            if (segwments[j].getStart() <= pwivot) {
                i++;
                swaw(segwments, i, j);
            }
        }
        swaw(segwments, i + 1, right);
        return i + 1;
    }
}
