package suanfayushujujiegou.chapter7;

import java.util.ArrayList;

/*
 *  图 论:
 *    有向图，无向图，带权图，无权图，自环边，平行边，简单图，完全图，
 *
 *          用于表示图的数据结构：
 *           邻接矩阵: 适合于稠密图
 *
 *           邻接表:  适合于稀疏图
 *    通过统计深度优先遍历的次数可以得知，图中有几个连通分量，
 *     在遍历的时候给每个连通分量中的顶点都做上相同的标记，
 *     则再遍历结束之后我们就能得知任意两个节点之间是否是连通的。
 *     使用深度优先遍历可以得知两个 连通的节点之间的路径，
 *     该路径未必是最短的。v,w 之间的路径 可以从 v开始进行深度优先遍历。
 *     遍历过程中记录路径，遍历结束后回推
 *
 *  深度优先遍历时间复杂度 :
 *          稀疏图 (邻接表) : O(V+E);
 *          稠密图 (邻接矩阵) : O(V^2);
 *
 * 广度优先遍历: 广度优先遍历可以得到无权图中两个节点之间的最短路径， 无论这个无权图是有向图还是无向图。
 * 最小生成树: 针对带权无向图。针对连通图，最小生成森林。
 * 切分定理: 给定任意的切分，横切边中权值最小的边必定属于最小生成树。    将一个连通图一分为二， 则连接part1 和 part2 的边被称为 横切边。
 *      可以通过反正法证明， 树增加一条边变成环，环去掉一条边变成树。
 *   Prim 算法生成最小生成树：
 *          最小堆 + 切分定理
 *         从任意一个顶点，出发， 将该顶点的边加入最小堆中，选出权值最小的边。判断 该边的两个顶点是否都已经被加入到最小生成树中即判断该边是否是横切边，
 *                 如果该边的两个顶点都在最小生成树中，则舍弃该边即该边不是横切边，
 *              再次从最小堆中拿出最小的边，再次判断，直至最小边的两个顶点不都在最小生成树中，将不再最小生成树中的顶点加入到最小生成树中，将该顶点的边加入到最小堆中。
 *          继续从最小堆中拿出权值最小的边重复上述操作，直至最小堆为空或所有顶点均都加入到了最小生成树中。
 *    优化： 借助最小索引堆：
 *              索引为顶点编号 ， 值为与目前已知的与该顶点连接的最小的边。 从任意顶点出发， 如果是横切边，且权值比已知的小，则将权值加入索引堆中，替换旧值， 从索引堆找出权值最小的边。
 *                  得到不在最小生成树中的顶点，继续以上步骤。
 *
 * kruskal 算法：
 *      kruskal算法每次性处理所有的边（当然边的个数会不断减少），从所有的边中寻找最小的边，前提：加入该边后不会构成环。
 *          借助最小堆得到最小边， 借助并查集得知 加入最小边后是否会形成一个环，最小边对应的两个顶点如果已经连接在一起了，那么再加入该边后，必定会形成一个环。
 *      Prim 算法 每一只处理当前涉及的边（当然边数可能增加，也可能不变），从当前涉及的边中寻找最短的边。
 *
 * 迪杰斯特拉算法 时间复杂度 O( ElogV )， 求单源最短路径， 不能带有负权边。
 *      A，B,C,D,E,F
 *      求 A 到各个顶点的最短路径。
 *      首先 从 A出发 求 A到各个顶点的路径，
 *             第一步： 此时 得到 A 到各个顶点的路径， 最短的路径必定为 A到此顶点的最终路径， 因为 A 经过 B 到达 C，绝对不会小于 B，而 A 到到达 C 已经小于 B了， 所有
 *            这些 最短的路径必定为 A到达此顶点C的最短路径。
 *              第二步： 接着 从 C出发 进行松弛操作， 比较 C到达 B，D，E，F各个顶点的距离 + A到达C的距离 是否小于
 *             A到达 B，D，E，F各个顶点的距离，如果小于则更新 A到达B，D，E，F各个顶点的距离，
 *               重复 第一步的操作，从A到达B，D，E，F各个顶点的距离中选出最短的路径，然后重复第二步操作
 * 一个图中如果拥有负权环，那这个图将不再拥有最短路径，因为我们可以在这个环上不停的转，使得他的路径越来越短，
 * */
public class Chapter7 {

    /**
     * 稠密图
     */
    public static class DenseGraph {
        private int n, m; // n 为顶点数，m为边数。
        private boolean directed; //  标记这个图是有向图还是无向图。
        private boolean[][]  edges; //  edges[i][v] 用于表示顶点 i，v之间是否有边。
        public DenseGraph(int n, boolean directed) {
            this.directed = directed;
            this.n = n;
            this.m = 0;
            this.edges = new boolean[n][n];
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++)
                 this.edges[i][j]=false;
            }
        }
        public int getN(){
            return this.n;
        }
        public int getM(){
            return this.m;
        }
        /*
        *  在顶点 v 和 顶点 w 之间 添加一条边
        * */
        public void addEdge(int v,int w){
            assert v>=0&&v<this.n:" v 越界";
            assert w>=0&&w<this.n:" w 越界";
            if(this.hasEdge(v,w)){
                return;
            }
            this.edges[v][w]=true;
            if(!this.directed){
                this.edges[w][v]=true;
            }
            this.m++;
        }
        /*
        *  判断 顶点 v 和 顶点 w之间是否有边。
        * */
        public boolean hasEdge(int v,int w){
            assert v>=0&&v<this.n:" v 越界";
            assert w>=0&&w<this.n:" w 越界";
            return this.edges[v][w];
        }

    }/**
     *  稀疏图
     */
    public static class SparseGraph{
        private int n,m;// n 为顶点数，m为边数。
        private boolean directed; // 用来标记 图是否是有有向图。
        private ArrayList[] edges; // 邻接表
        public SparseGraph(int n,boolean directed){
            this.n = n;
            this.m = 0;
            this.directed = directed;
            this.edges=new ArrayList[n];
            for(int i=0;i<n;i++){
                this.edges[i] = new ArrayList<Integer>();
            }
        }
        public void addEdge(int v,int w){
            assert v>=0 && v<this.n: " v 越界";
            assert w>=0 && w<this.n: " w 越界";
            if(this.hasEdge(v,w)){
                return;
            }
            this.edges[v].add(w);
            if(!this.directed){
                this.edges[w].add(v);
            }
            this.m++;
        }
        public boolean hasEdge(int v,int w){
            assert v>=0 && v<this.n: " v 越界";
            assert w>=0 && w<this.n: " w 越界";
            return this.edges[v].contains(w);
        }

    }

}
