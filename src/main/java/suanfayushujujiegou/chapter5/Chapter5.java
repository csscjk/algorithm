package suanfayushujujiegou.chapter5;

public class Chapter5 {
    /*二分查找法:
    *   对于有序的序列才能使用二分查找法
    *
    *               查找元素        插入元素        删除元素
    * 普通数组：        O(N)         O(N)            O(N)
    * 有序数组         O(logN)      O(N)            O(N)
    * 二分搜索树       O(logN)       O(logN)        O(logN)
     * */


    /*
    * 二分搜索树
    * */
    public static class BST{
        private int count;// 该树所拥有的节点数。
        private int data; // key
        private BST leftChild;// 左子树-
        private BST  rightChild; // 右子树
        public BST(int data){this.data=data;}
        public int getCount(){
            return this.count;
        }
        public int getData(){return this.data;}
        public void setData(int data){
            this.data=data;
        }
        public BST getLeftChild(){
            return this.leftChild;
        }
        public void setLeftChild(BST leftChild){
            this.leftChild = leftChild;
        }
        public BST getRightChild(){
            return this.rightChild;
        }
        public void setRightChild(BST rightChild){
            this.rightChild = rightChild;
        }
        public void insert(int data){
            if(this.data==data){

            }

        }
        private int toInsert(int data){
            return 0;
        }

    }

}
