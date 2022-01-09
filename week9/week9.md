## 转换成小写字母

```
class Solution {
    public String toLowerCase(String s) {
        if (s==null || "".equals(s)) return s;
        char[] chs = s.toCharArray();
        for(int i=0;i<chs.length;i++){
            if (chs[i]>64 && chs[i]<91){
                chs[i]|=32;
            }
        }
        return new String(chs);
    }
}
```



## 最后一个单词的长度

反向遍历

```
class Solution {
    public int lengthOfLastWord(String s) {
        s = s.trim();
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for(int i=n-1;i>=0;i--){
            if(s.charAt(i) != 32) {
                sb.append(s.charAt(i));
            } else {
                break;
            }
        }
        return sb.toString().length();

    }
}
```

