public class MedianOfTwoSortedArrays {

  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    if (nums1 == null || nums2 == null) {
      return 0.0;
    }

    int m = nums1.length;
    int n = nums2.length;
    int l = (m + n + 1) / 2; // left half of the combined median
    int r = (m + n + 2) / 2; // right half of the combined median

    // If the nums1.length + nums2.length is ODD, both function will return the same number
    // Else if nums1.length + nums2.length is EVEN,
    //   both function will return the left number and right number that make up a median
    if ((m + n) % 2 != 0) {
      return getKth(nums1, 0, nums2, 0, l);
    }

    return (getKth(nums1, 0, nums2, 0, l) + getKth(nums1, 0, nums2, 0, r)) / 2.0;
  }

  // finds the Kth element in nums1 + nums2
  private double getKth(int[] nums1, int start1, int[] nums2, int start2, int k) {
    int nums1Length = nums1.length;
    int nums2Length = nums2.length;

    // If nums1 is exhausted, return kth number in nums2
    if (start1 > nums1Length - 1) {
      return nums2[start2 + k - 1];
    }

    // If nums2 is exhausted, return kth number in nums1
    if (start2 > nums2Length - 1) {
      return nums1[start1 + k - 1];
    }

    // If k == 1, return the first number that is smaller of the two
    if (k == 1) {
      return Math.min(nums1[start1], nums2[start2]);
    }

    int mid1 = Integer.MAX_VALUE;
    int mid2 = Integer.MAX_VALUE;

    if (start1 + k / 2 - 1 < nums1Length) {
      mid1 = nums1[start1 + k / 2 - 1];
    }
    if (start2 + k / 2 - 1 < nums2Length) {
      mid2 = nums2[start2 + k / 2 - 1];
    }

    // Throw away half of the array from nums1 or nums2. And cut k in half
    if (mid1 < mid2) {
      // nums1.right + nums2
      return getKth(nums1, start1 + k / 2, nums2, start2, k - k / 2);
    } else {
      // nums1 + nums2.right
      return getKth(nums1, start1, nums2, start2 + k / 2, k - k / 2);
    }
  }
}
