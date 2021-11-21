class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for(int i=n-1;i>=0;i--){
            if(digits[i] != 9){
                ++digits[i];
                for(int j=i+1;j<n;j++){
                    digits[j] = 0;
                }
                return digits;
            }
        }
        int[] res = new int[n+1];
        res[0] = 1;
        return res;
    }
}