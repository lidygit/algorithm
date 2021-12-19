## 在 D 天内送达包裹的能力

```
class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int left=0,right=0;
        for(int i=0;i<weights.length;i++){
            left = Math.max(left, weights[i]);
            right+=weights[i];
        }
        while(left<right){
            int mid = left + ((right -left)>>1);
            int need = 1, cur = 0;
            for(int i=0;i<weights.length;i++){
                if (cur + weights[i]>mid){
                    need++;
                    cur=0;
                }
                cur+=weights[i];
            }
            if(need<=days){
                right=mid;
            }else{
                left=mid+1;
            }
        }
        return left;
    }
}
```



## 搜索二维矩阵

```
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n= matrix[0].length;
        int low=-1,high=m-1;
        while(low<high){
            int mid=low+((high-low+1)>>1);
            if (matrix[mid][0]<=target){
                low=mid;
            }else{
                high=mid-1;
            }
        }
        if (low<0){
            return false;
        }
        int low2=0,high2=n-1;
        while(low2<=high2){
            int mid=low2+((high2-low2)>>1);
            if (matrix[low][mid]<target){
                low2=mid+1;
            }else if (matrix[low][mid]>target){
                high2=mid-1;
            }else{
                return true;
            }
        }
        return false;
    }
}
```

