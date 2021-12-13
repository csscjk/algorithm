package suanfayushujujiegou.chapter6;

public class Chapter6 {
    /*
    * 并查集
    *   作用: 解决连接问题，用来判断数据之间是不是连接的。
    *
    * */
    public static class UnionFind_version1{
        /**
         *  使用一个数组来实现，数组的索引表示操作的数据，根据数组中的元素得知各个数据之间是否相连。 例如： 如果arr[i] == arr[j] 则表示，i 和 j 相连。
         *  采用两级结构，当 p 和 q 合并时，会让 p 指向 q，p的后代也指向 q，即 p的后代将变为 p的兄弟。
         *
         *  版本1，效率低。
         */
        private int[] ids; // 用数组所以来表示数据，根据数组中的元素判断数据之间是否相连即是否处于同一组中。
        private int count;// 用来表示 并查集中有多少个元素。

        public UnionFind_version1(int count){
            this.count=count;
            this.ids = new int[count];
            for(int i=0;i<count;i++){
                this.ids[i]=i;// 初始时，每个元素自己独立成一个组。
            }
        }
        public int find(int p){
            assert p>=0&&p<this.count: " p 越界";
            return this.ids[p];
        }
        public boolean isConnected(int p,int q){
            return find(p)==find(q);
        }
        public void unionElements(int p,int q){
            int pID = find(p);
            int qID = find(q);
            if(pID==qID){
                return;
            }
            for(int i=0;i<this.count;i++){
                if(this.ids[i]==pID){
                    this.ids[i]=qID;
                }
            }
        }

    }
    public static class UnionFind_version2{
        /*
        *  多级结构 : 并查集中的操作时间复杂度，近乎是O(1)的
        * */
        private int[] parent;
        // private int[] nodeNum; // nodeNum[i] 表示 i 所拥有的节点数。，节点数多层次不一定深，而我们优化的出发点是让 层次更浅。
        private int[] rank;// rand[i] 表示 i 所拥有的最大深度
        private int count;
        public UnionFind_version2(int count){
            this.count = count;
            this.parent = new int[this.count];
            this.rank = new int[this.count];
            for(int i=0;i<this.count;i++){
                this.parent[i]=i;
                this.rank[i]=1;
            }
        }
        public int[] getParent(){
            return this.parent;
        }
        public int getCount(){
            return this.count;
        }
        public int find(int p){
            assert p>=0&&p<this.count:" p 越界 ";
            while(this.parent[p]!=p){ // 一直往上追寻，知道找到根节点。
                p=this.parent[p];
            }
            return this.parent[p];
        }
        public  boolean isConnected(int p,int q){
            return this.find(p)==this.find(q);
        }
        public void unionElement(int q,int p){
            int pparent= this.find(p);
            int qparent = this.find(q);
            if(pparent==qparent){
                return;
            }
            /*
            * 将节点数少的根节点插入到节点数多的根节点上。
            * */
            if(this.rank[pparent]<this.rank[qparent]){
                this.parent[pparent] = qparent;
            }else{
                if(this.rank[pparent]==this.rank[qparent]){
                    this.rank[pparent]++;
                }
                this.parent[qparent]=pparent;
            }

        }
        public int findByPathCompression(int p){
            /**
             * 路径压缩算法：
             *  在遍历的时候，顺便降低树的层次。
             *      例子：
             *      p=4
             *       4 -> 3 -> 2 -> 1 -> 0
             *      4的父节点 为 3 不为4，所以 4 不是根节点，所以  3->
             *                                                    2 ->1 ->0
             *                                              4->
             *       p= parent[4]=2
             *      2 的父节点 为  1 不为 2，所以 2不是根节点 ，所以                   1 ->
             *                                                           3->             0
             *                                                                  2 ->
             *                                                           4->
             *      p = parent[2] = 0
             *      0的父节点为0，所以0为根节点 ，返回0； 同时树的层次发生了改变。
             * */
            assert p>=0 && p < this.count : " p 越界 ";
            while(p!=this.parent[p]){
                parent[p] = this.parent[parent[p]]; // 相当于每次都跳了一步
                p = this.parent[p];
            }
            return this.parent[p];
        }
    }
}
