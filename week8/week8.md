## 冗余连接

```
class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        int[] parent = new int[n+1];
        for(int i=0;i<n+1;i++){
            parent[i]=i;
        }
        for(int i=0;i<n;i++){
            int[] edge=edges[i];
            int node1=edge[0],node2=edge[1];
            if(find(parent,node1)!=find(parent,node2)){
                union(parent,node1,node2);
            }else{
                return edge;
            }
        }
        return new int[0];
    }

    public int find(int[] parent, int index){
        if(parent[index]!=index){
            parent[index]=find(parent,parent[index]);
        }
        return parent[index];
    }

    public void union(int[] parent,int index1,int index2){
        parent[find(parent,index1)]=find(parent,index2);
    }
}
```



## 岛屿数量

```
class Solution {
    class UnionFind {
        int count;
        int[] parent;
        int[] rank;

        public UnionFind(char[][] grid) {
            count = 0;
            int m = grid.length;
            int n = grid[0].length;
            parent = new int[m * n];
            rank = new int[m * n];
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j] == '1') {
                        parent[i * n + j] = i * n + j;
                        ++count;
                    }
                    rank[i * n + j] = 0;
                }
            }
        }

        public int find(int i) {
            if (parent[i] != i) parent[i] = find(parent[i]);
            return parent[i];
        }

        public void union(int x, int y) {
            int rootx = find(x);
            int rooty = find(y);
            if (rootx != rooty) {
                if (rank[rootx] > rank[rooty]) {
                    parent[rooty] = rootx;
                } else if (rank[rootx] < rank[rooty]) {
                    parent[rootx] = rooty;
                } else {
                    parent[rooty] = rootx;
                    rank[rootx] += 1;
                }
                --count;
            }
        }

        public int getCount() {
            return count;
        }
    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int m = grid.length;
        int n = grid[0].length;
        UnionFind uf = new UnionFind(grid);
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (grid[i][j] == '1') {
                    grid[i][j] = '0';
                    if (i - 1 >= 0 && grid[i-1][j] == '1') {
                        uf.union(i * m + i, (i-1) * m + i);
                    }
                    if (i + 1 < m && grid[i+1][j] == '1') {
                        uf.union(i * n + j, (i+1) * n + j);
                    }
                    if (j - 1 >= 0 && grid[i][j-1] == '1') {
                        uf.union(i * n + j, i * n + j - 1);
                    }
                    if (j + 1 < n && grid[i][j+1] == '1') {
                        uf.union(i * n + j, i * n + j + 1);
                    }
                }
            }
        }

        return uf.getCount();
    }
}
```
