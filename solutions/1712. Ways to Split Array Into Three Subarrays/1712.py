class Solution:
  def waysToSplit(self, nums: List[int]) -> int:
    kMod = int(1e9 + 7)
    ans = 0
    prefix = list(itertools.accumulate(nums))

    def firstGreaterEqual(i: int) -> int:
      l = i + 1
      r = len(prefix) - 1
      while l < r:
        m = (l + r) // 2
        if prefix[m] - prefix[i] >= prefix[i]:
          r = m
        else:
          l = m + 1
      return l

    def firstGreater(i: int) -> int:
      l = i + 1
      r = len(prefix) - 1
      while l < r:
        m = (l + r) // 2
        if prefix[m] - prefix[i] > prefix[-1] - prefix[m]:
          r = m
        else:
          l = m + 1
      return l

    for i in range(len(prefix) - 2):
      j = firstGreaterEqual(i)
      if j == len(prefix) - 1:
        break

      mid = prefix[j] - prefix[i]
      right = prefix[-1] - prefix[j]
      if mid > right:
        continue

      k = firstGreater(i)
      ans = (ans + k - j) % kMod

    return ans
