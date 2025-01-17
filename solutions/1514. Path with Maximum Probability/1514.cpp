class Solution {
 public:
  double maxProbability(int n, vector<vector<int>>& edges,
                        vector<double>& succProb, int start, int end) {
    vector<vector<pair<int, double>>> graph(n);  // {a: {b, prob_ab}}
    priority_queue<pair<double, int>> pq;        // {prob, u}: max-heap
    pq.emplace(1.0, start);
    vector<bool> seen(n);
    vector<double> probs(n);  // probs[i] := max prob to reach node i

    for (int i = 0; i < edges.size(); ++i) {
      const int a = edges[i][0];
      const int b = edges[i][1];
      const double prob = succProb[i];
      graph[a].emplace_back(b, prob);
      graph[b].emplace_back(a, prob);
    }

    while (!pq.empty()) {
      const auto [prob, u] = pq.top();
      pq.pop();
      if (seen[u])
        continue;
      seen[u] = true;
      for (const auto& [nextNode, edgeProb] : graph[u])
        if (probs[nextNode] < prob * edgeProb) {
          probs[nextNode] = prob * edgeProb;
          pq.emplace(probs[nextNode], nextNode);
        }
    }

    return probs[end];
  }
};
