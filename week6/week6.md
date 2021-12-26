## 70.爬楼梯

```
/** dp
 *
 *  - 转移方程： 用f(x)表示爬到第 x 级的方案数，f(x)=f(x-1)+f(x-2)
 *  - 边界条件： f(0)=1  f(1)=1
 *  - 滚动数组思想：  r 记录爬到第 x 级的方案数
 *                  p 记录爬到第 x-1 级的方案数
 *                  q 记录爬到第 x-2 级的方案数
 * */
class Solution {
    public int climbStairs(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 0; i < n; i++) {
            q = p;
            p = r;
            r = p + q;
        }
        return r;
    }
}
```

## 120.三角形最小路径和

时：O(n^2)

空：O(n^2)

```
/**
* 转移方程：f[i][j] = c[i][j] + min(f[i-1][j-1],f[i-1][j])
*          j=0 时，f[i][0] = c[i][0] + f[i-1][0]
*          j=i 时，f[i][i] = c[i][i] + f[i-1][i-1]
*          问题转化为求 min(f[n-1][0],f[n-1][n-1])
* 边界条件：f[0][0] = c[0][0]
*
* */
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[][] f = new int[n][n];
        f[0][0] = triangle.get(0).get(0);
        for (int i = 1; i < n; i++) {
            f[i][0] = triangle.get(i).get(0) + f[i-1][0];
            for (int j = 1; j < i; j++) {
                f[i][j] = triangle.get(i).get(j) + Math.min(f[i-1][j-1], f[i-1][j]);
            }
            f[i][i] = triangle.get(i).get(i) + f[i-1][i-1];
        }
        int minTotal = f[n-1][0];
        for (int i = 0; i < n; i++) {
            minTotal = Math.min(minTotal, f[n-1][i]);
        }
        return minTotal;
    }
}
```

