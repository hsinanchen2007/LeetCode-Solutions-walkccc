struct T {
  int size;
  int right;
  T(int size, int right) : size(size), right(right) {}
};

class Solution {
 public:
  vector<int> minInterval(vector<vector<int>>& intervals,
                          vector<int>& queries) {
    vector<int> ans(queries.size(), -1);
    auto compare = [](const T& a, const T& b) { return a.size > b.size; };
    priority_queue<T, vector<T>, decltype(compare)> pq(compare);
    vector<vector<int>> qs;

    for (int i = 0; i < queries.size(); ++i)
      qs.push_back({queries[i], i});

    sort(begin(intervals), end(intervals));
    sort(begin(qs), end(qs));

    int i = 0;  // intervals' pointer
    for (const auto& q : qs) {
      while (i < intervals.size() && intervals[i][0] <= q[0]) {
        pq.emplace(intervals[i][1] - intervals[i][0] + 1, intervals[i][1]);
        ++i;
      }
      while (!pq.empty() && pq.top().right < q[0])
        pq.pop();
      if (!pq.empty())
        ans[q[1]] = pq.top().size;
    }

    return ans;
  }
};
