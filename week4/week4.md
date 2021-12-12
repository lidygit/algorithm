## 被围绕的区域

### 深搜

```
class Solution {
    int n,m;
    char[][] board;
    public void solve(char[][] board) {
        this.board = board;
        n = board.length;
        if (n == 0) return;
        m = board[0].length;
        for (int i=0;i<n;i++){
            dfs(i, 0);
            dfs(i, m-1);
        }
        for (int i=1;i<m-1;i++){
            dfs(0, i);
            dfs(n-1, i);
        }
        for (int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if (board[i][j] == 'A'){
                    board[i][j] = 'O';
                }else if (board[i][j] == 'O'){
                    board[i][j] = 'X';
                }
            }
        }

    }

    public void dfs(int x,int y){
        if (x<0 || x>=n || y<0 || y>=m || board[x][y] != 'O') return;
        board[x][y] = 'A';
        dfs(x+1, y);
        dfs(x-1, y);
        dfs(x, y+1);
        dfs(x, y-1);
    }
}
```







## 设计推特

```
class Twitter {
    
    private Map<Integer, Tweet> twitter;// 用户id与推文关系
    private Map<Integer, Set<Integer>> follows; // 用户关注列表
    private static int timestamp = 0;
    private PriorityQueue<Tweet> maxHeap;

    public class Tweet{
        private int tweetId;
        private int timestamp;
        private Tweet next;
        public Tweet(int tweetId, int timestamp){
            this.tweetId = tweetId;
            this.timestamp  = timestamp ;
        }
    }

    public Twitter() {
        twitter = new HashMap<>();
        follows = new HashMap<>();
        maxHeap = new PriorityQueue<>((o1, o2) -> o2.timestamp - o1.timestamp);
    }
    
    public void postTweet(int userId, int tweetId) {
        timestamp++;
        if (twitter.containsKey(userId)){
            Tweet oldHead = twitter.get(userId);
            Tweet newHead = new Tweet(tweetId, timestamp);
            newHead.next = oldHead;
            twitter.put(userId, newHead);
        }else{
            Tweet tweet = new Tweet(tweetId, timestamp);
            twitter.put(userId, tweet);
        }
    }
    
    public List<Integer> getNewsFeed(int userId) {
        maxHeap.clear();

        if (twitter.containsKey(userId)){
            maxHeap.offer(twitter.get(userId));
        }
        Set<Integer> following = follows.get(userId);
        if (following != null && following.size() > 0){
            for (Integer id: following){
                Tweet tweet = twitter.get(id);
                if (tweet != null){
                    maxHeap.offer(tweet);
                }
            }
        }
        List<Integer> res = new ArrayList<>(10);
        int count = 0;
        while (!maxHeap.isEmpty() && count < 10){
            Tweet tweet = maxHeap.poll();
            res.add(tweet.tweetId);
            if (tweet.next != null){
                maxHeap.offer(tweet.next);
            }
            count++;
        }
        return res;

    }
    
    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId) return;
        Set<Integer> set = follows.get(followerId);
        if (set == null){
            set = new HashSet<>();
            set.add(followeeId);
            follows.put(followerId, set);
        }else{
            set.add(followeeId);
        }

    }
    
    public void unfollow(int followerId, int followeeId) {
        if (followerId == followeeId) return;
        Set<Integer> set = follows.get(followerId);
        if (set == null) return;
        set.remove(followeeId);

    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
```



