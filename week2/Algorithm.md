## 子域名访问计数

```java
class Solution {
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();
        for (int i=0;i<cpdomains.length;i++){
            String[] temp = cpdomains[i].split(" ");
            int count = Integer.parseInt(temp[0]);
            String[] split = temp[1].split("\\.");
            String s="";
            for(int j=split.length-1;j>=0;j--){
                s = "".equals(s) ? split[j] : (split[j]+"."+s);
                if (map.containsKey(s)){
                    map.put(s, (int)map.get(s)+count);
                }else{
                    map.put(s, count);
                }
            }
        }
        List<String> ans = new ArrayList<>();
        for(Map.Entry entry:map.entrySet()){
            StringBuilder sb = new StringBuilder();
            sb.append(entry.getValue()).append(" ").append(entry.getKey());
            ans.add(sb.toString());
        }
        return ans;
    }
}
```





## 数据的度

```java
class Solution {
    public int findShortestSubArray(int[] nums) {
        Map<Integer, int[]> map = new HashMap<>();
        for (int i=0;i<nums.length;i++){
            if (map.containsKey(nums[i])){
                int[] temp =  (int[])map.get(nums[i]);
                temp[0]+=1;
                temp[2]=i;
                map.put(nums[i], temp);
            }else{
                map.put(nums[i], new int[]{1, i, i});
            }
        }

        int minLen=0;
        int maxArr=0;
        for(Map.Entry<Integer, int[]> entry:map.entrySet()){
            int[] temp = (int[])entry.getValue();
            if (maxArr < temp[0]){
                maxArr = temp[0];
                minLen = temp[2]-temp[1]+1;
            }else if(maxArr == temp[0]){
                minLen = Math.min(minLen, temp[2]-temp[1]+1);
            }
        }
        return minLen;
    }
}
```

