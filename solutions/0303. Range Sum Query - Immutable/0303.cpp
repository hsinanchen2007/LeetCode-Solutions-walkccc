class NumArray {
 public:
  NumArray(vector<int>& nums) : prefix(nums.size() + 1) {
    partial_sum(begin(nums), end(nums), begin(prefix) + 1);
  }

  int sumRange(int i, int j) {
    return prefix[j + 1] - prefix[i];
  }

 private:
  vector<int> prefix;
};
