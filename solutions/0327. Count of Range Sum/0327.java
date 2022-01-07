class Solution {
  public int countRangeSum(int[] nums, int lower, int upper) {
    final int n = nums.length;

    long[] prefix = new long[n + 1];

    for (int i = 0; i < n; ++i)
      prefix[i + 1] = (long) nums[i] + prefix[i];

    mergeSort(prefix, 0, n, lower, upper);

    return count;
  }

  private int count = 0;

  private void mergeSort(long[] prefix, int l, int r, int lower, int upper) {
    if (l >= r)
      return;

    final int m = l + (r - l) / 2;
    mergeSort(prefix, l, m, lower, upper);
    mergeSort(prefix, m + 1, r, lower, upper);
    merge(prefix, l, m, r, lower, upper);
  }

  private void merge(long[] prefix, int l, int m, int r, int lower, int upper) {
    int lo = m + 1; // 1st index s.t. prefix[lo] - prefix[i] >= lower
    int hi = m + 1; // 1st index s.t. prefix[hi] - prefix[i] > upper

    // for each index i in range [l, m], add hi - lo to count
    for (int i = l; i <= m; ++i) {
      while (lo <= r && prefix[lo] - prefix[i] < lower)
        ++lo;
      while (hi <= r && prefix[hi] - prefix[i] <= upper)
        ++hi;
      count += hi - lo;
    }

    long[] sorted = new long[r - l + 1];
    int k = 0;     // sorted's index
    int i = l;     // left's index
    int j = m + 1; // right's index

    while (i <= m && j <= r)
      if (prefix[i] < prefix[j])
        sorted[k++] = prefix[i++];
      else
        sorted[k++] = prefix[j++];

    // put possible remaining left part to the sorted array
    while (i <= m)
      sorted[k++] = prefix[i++];

    // put possible remaining right part to the sorted array
    while (j <= r)
      sorted[k++] = prefix[j++];

    System.arraycopy(sorted, 0, prefix, l, sorted.length);
  }
}