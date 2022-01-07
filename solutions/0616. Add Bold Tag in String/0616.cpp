class Solution {
 public:
  string addBoldTag(string s, vector<string>& dict) {
    const int n = s.length();

    string ans;
    vector<bool> bold(n);  // bold[i] := true if s[i] should be bolded

    int boldEnd = -1;  // s[i:boldEnd] should be bolded
    for (int i = 0; i < n; ++i) {
      for (const string& word : dict)
        if (s.substr(i).find(word) == 0)  // startsWith
          boldEnd = max(boldEnd, i + (int)word.length());
      bold[i] = boldEnd > i;
    }

    // construct the string with bold tags
    int i = 0;

    while (i < n)
      if (bold[i]) {
        int j = i;
        while (j < n && bold[j])
          ++j;
        // s[i:j] should be bolded
        ans += "<b>" + s.substr(i, j - i) + "</b>";
        i = j;
      } else {
        ans += s[i++];
      }

    return ans;
  }
};
