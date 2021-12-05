## 全排列2

```
class Solution {

    List<List<Integer>> res = new ArrayList<>();
    public List<List<Integer>> permuteUnique(int[] nums) {
        int n = nums.length;
        if (n == 0){
            return res;
        }
        Arrays.sort(nums);
        boolean[] used = new boolean[n];
        Deque<Integer> path = new ArrayDeque<>();
        dfs(nums, n, 0, used, path);
        return res;
    }
    
    public void dfs(int[] nums, int n, int depth, boolean[] used, Deque<Integer> path){
        if (depth == n){
            res.add(new ArrayList<>(path));
        }
        for (int i=0;i<n;i++){
            if(used[i]){
                continue;
            }
            if(i>0&&nums[i]==nums[i-1]&&used[i-1]==false){
                continue;
            }
            path.addLast(nums[i]);
            used[i] = true;
            dfs(nums, n, depth+1, used, path);
            used[i] = false;
            path.removeLast();
        }
    }
}
```



## 从中序与后续遍历构造二叉树

```
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    Map<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int n = inorder.length;
        for (int i = 0; i < n; i++) {
            map.put(inorder[i], i);
        }
        return build(inorder, postorder, 0, n-1, 0, n-1);
    }

    public TreeNode build(int[] inorder, int[] postorder, int inorderLeft, int inorderRight, int postorderLeft, int postorderRight) {
        if (inorderLeft > inorderRight){
            return null;
        }
        int root = postorder[postorderRight];
        int rootIndex = (Integer)map.get(root);
        int subTreeLen = rootIndex - inorderLeft;
        TreeNode node = new TreeNode(root);
        node.left = build(inorder, postorder, inorderLeft, rootIndex - 1, postorderLeft, postorderLeft + subTreeLen -1);
        node.right = build(inorder, postorder, rootIndex + 1, inorderRight, postorderLeft + subTreeLen, postorderRight - 1);
        return node;

    }
}
```

