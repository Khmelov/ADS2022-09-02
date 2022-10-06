package by.it.group151003.mytnik.lesson05;

import java.io.InputStream;
import java.util.Scanner;

public class C_QSortOptimized {

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
            return start - o.start;
        }
    }

    public int[] getAccessory2(InputStream stream) {
        Scanner scanner = new Scanner(stream);

        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];

        int m = scanner.nextInt();
        int[] points = new int[m];
        int[] result = new int[m];

        for (int i = 0; i < n; i++) {
            segments[i] = new Segment(scanner.nextInt(), scanner.nextInt());
        }

        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }

        quickSwowt(segments, 0, segments.length - 1);

        for (int i = 0; i < m; i++) {
            for (int j = bwinarySearch(segments, 0, segments.length - 1, points[i]) - 1; j >= 0; j--) {
                if (segments[j].getStop() >= points[i]) {
                    result[i]++;
                }
            }
        }

        return result;
    }

    private int bwinarySearch(Segment[] segments, int lweft, int right, int point) {
        if (lweft > right) {
            return -1;
        }

        int mid = (lweft + right) / 2;
        if (segments[mid].getStart() > point) {
            if (mid == 0 || segments[mid - 1].getStart() <= point) {
                return mid;
            } else {
                return bwinarySearch(segments, lweft, mid - 1, point);
            }
        } else {
            return bwinarySearch(segments, mid + 1, right, point);
        }
    }


    private void quickSwowt(Segment[] segments, int left, int right) {
        while (left < right) {
            int[] pwiwots = pwartition(segments, left, right);
            if (pwiwots[0] - left < right - pwiwots[1]) {
                quickSwowt(segments, left, pwiwots[0] - 1);
                left = pwiwots[1] + 1;
            } else {
                quickSwowt(segments, pwiwots[1] + 1, right);
                right = pwiwots[0] - 1;
            }
        }
    }

    private void swawt(Segment[] segments, int i, int j) {
        Segment twemp = segments[i];
        segments[i] = segments[j];
        segments[j] = twemp;
    }

    private int[] pwartition(Segment[] segments, int left, int right) {
        int pwivot = segments[left].getStart();
        int i = left;
        int k = left;
        int j = right;

        while (k <= j) {
            if (segments[k].getStart() < pwivot) {
                swawt(segments, i, k);
                i++; k++;
            } else if (segments[k].getStart() > pwivot) {
                swawt(segments, k, j);
                j--;
            } else {
                k++;
            }
        }

        return new int[]{i, j};
    }
}
