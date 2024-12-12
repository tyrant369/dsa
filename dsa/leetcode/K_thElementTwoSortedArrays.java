

public class K_thElementTwoSortedArrays {

    public int kthElement(int a[], int b[], int k) {

        int n1 = a.length, n2 = b.length;

        if (n1 > n2) return  kthElement(b, a, k);

        int low = Math.max(0, k - n2), high = Math.min(k, n1);

        int left = k;

        while (low <= high) {

            int mid1 = low + (high - low) / 2;
            int mid2 = left - mid1;

            int l1 = (mid1 > 0) ? a[mid1 - 1] : Integer.MIN_VALUE;
            int l2 = (mid2 > 0) ? b[mid2 - 1] : Integer.MIN_VALUE;
            int r1 = (mid1 < n1) ? a[mid1] : Integer.MAX_VALUE;
            int r2 = (mid2 < n2) ? b[mid2] : Integer.MAX_VALUE;

            if (l1 <= r2 && l2 <= r1) return Math.max(l1, l2);

            if (l1 > r2) high = mid1 - 1;

            else low = mid1 + 1;
        }

        return -1;
    }
}