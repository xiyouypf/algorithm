package 前缀树;

import java.util.HashMap;

public class TrieTree {
    public static class Node1 {
        public int pass;
        public int end;
        public Node1[] nexts;

        public Node1() {
            pass = 0;
            end = 0;
            nexts = new Node1[26];
        }
    }

    public static class Trie1 {
        private Node1 root = new Node1();

        public Trie1() {
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] chars = word.toCharArray();
            Node1 curNode = root;
            curNode.pass++;
            for (char c : chars) {
                int idx = c - 'a';
                if (curNode.nexts[idx] == null) {
                    curNode.nexts[idx] = new Node1();
                }
                curNode = curNode.nexts[idx];
                curNode.pass++;
            }
            curNode.end++;
        }

        public void delete(String word) {
            if (search(word) != 0) {
                char[] chars = word.toCharArray();
                Node1 curNode = root;
                curNode.pass--;
                for (char c : chars) {
                    int idx = c - 'a';
                    if (curNode.nexts[idx].pass == 1) {
                        curNode.nexts[idx] = null;
                        return;
                    }
                    curNode = curNode.nexts[idx];
                    curNode.pass--;
                }
                curNode.end--;
            }
        }

        // word这个单词之前加入过几次
        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] chars = word.toCharArray();
            Node1 curNode = root;
            for (char c : chars) {
                int idx = c - 'a';
                if (curNode.nexts[idx] == null) {
                    return 0;
                }
                curNode = curNode.nexts[idx];
            }
            return curNode.end;
        }

        // 所有加入的字符串中，有几个是以pre这个字符串作为前缀的
        public int prefixNumber(String pre) {
            if (pre == null) {
                return 0;
            }
            char[] chars = pre.toCharArray();
            Node1 curNode = root;
            for (char c : chars) {
                int idx = c - 'a';
                if (curNode.nexts[idx] == null) {
                    return 0;
                }
                curNode = curNode.nexts[idx];
            }
            return curNode.pass;
        }
    }

    public static class Node2 {
        public int pass;
        public int end;
        public HashMap<Integer, Node2> nexts;

        public Node2() {
            pass = 0;
            end = 0;
            nexts = new HashMap<>();
        }
    }

    public static class Trie2 {
        private Node2 root = new Node2();

        public Trie2() {
        }

        public void insert(String word) {
            if (word == null) {
                return;
            }
            char[] chars = word.toCharArray();
            Node2 curNode = root;
            curNode.pass++;
            for (char c : chars) {
                int idx = c;
                if (!curNode.nexts.containsKey(idx)) {
                    curNode.nexts.put(idx, new Node2());
                }
                curNode = curNode.nexts.get(idx);
                curNode.pass++;
            }
            curNode.end++;
        }

        public void delete(String word) {
            if (search(word) != 0) {
                char[] chars = word.toCharArray();
                Node2 curNode = root;
                curNode.pass--;
                for (char c : chars) {
                    int idx = c;
                    if (curNode.nexts.get(idx).pass == 1) {
                        curNode.nexts.remove(idx);
                        return;
                    }
                    curNode = curNode.nexts.get(idx);
                    curNode.pass--;
                }
                curNode.end--;
            }
        }

        // word这个单词之前加入过几次
        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] chars = word.toCharArray();
            Node2 curNode = root;
            for (char c : chars) {
                int idx = c;
                if (curNode.nexts.get(idx) == null) {
                    return 0;
                }
                curNode = curNode.nexts.get(idx);
            }
            return curNode.end;
        }

        // 所有加入的字符串中，有几个是以pre这个字符串作为前缀的
        public int prefixNumber(String pre) {
            if (pre == null) {
                return 0;
            }
            char[] chars = pre.toCharArray();
            Node2 curNode = root;
            for (char c : chars) {
                int idx = c;
                if (curNode.nexts.get(idx) == null) {
                    return 0;
                }
                curNode = curNode.nexts.get(idx);
            }
            return curNode.pass;
        }
    }

    // for test
    public static String generateRandomString(int strLen) {
        char[] ans = new char[(int) (Math.random() * strLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            int value = (int) (Math.random() * 6);
            ans[i] = (char) (97 + value);
        }
        return String.valueOf(ans);
    }

    // for test
    public static String[] generateRandomStringArray(int arrLen, int strLen) {
        String[] ans = new String[(int) (Math.random() * arrLen) + 1];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = generateRandomString(strLen);
        }
        return ans;
    }

    public static void main(String[] args) {
        int arrLen = 100;
        int strLen = 20;
        int testTimes = 100000;
        for (int i = 0; i < testTimes; i++) {
            String[] arr = generateRandomStringArray(arrLen, strLen);
            Trie1 trie1 = new Trie1();
            Trie2 trie2 = new Trie2();
            for (int j = 0; j < arr.length; j++) {
                double decide = Math.random();
                if (decide < 0.25) {
                    trie1.insert(arr[j]);
                    trie2.insert(arr[j]);
                } else if (decide < 0.5) {
                    trie1.delete(arr[j]);
                    trie2.delete(arr[j]);
                } else if (decide < 0.75) {
                    int ans1 = trie1.search(arr[j]);
                    int ans2 = trie2.search(arr[j]);
                    if (ans1 != ans2) {
                        System.out.println("Oops!");
                    }
                } else {
                    int ans1 = trie1.prefixNumber(arr[j]);
                    int ans2 = trie2.prefixNumber(arr[j]);
                    if (ans1 != ans2) {
                        System.out.println("Oops!");
                    }
                }
            }
        }
        System.out.println("finish!");

    }
}
