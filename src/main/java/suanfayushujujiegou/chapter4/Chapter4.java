package suanfayushujujiegou.chapter4;

import suanfayushujujiegou.tools.Tool;


import java.util.Random;

public class Chapter4 {
    /**
     * 时间复杂度 O(nlogn)
     * 堆： 最大堆，最小堆
     * 实现： 使用数组实现
     * 如果对一个堆进行标号，根节点标号为 1， 第一层 左节点标号为 2，右节点标号为 3，。。。。，可以发现
     * 如果对于节点 i，其左节点的标号为 2*i，右节点的标号为 2*i+1， 父节点的标号为 i/2
     * 应用：
     *  从 n 个元素中选出 m 个最小的元素： 如果排序后，选出100个元素，则时间复杂度为 O(nlogn),但是如果从 n 个元素中拿出 m 个 元素建立一个最大堆，然后将剩余的元素与堆顶的元素进行比较，
     *  如果小于堆顶的元素，则替换堆顶的元素，那么时间复杂度 降为 O(nlogM)
     */
    public static void heapSort(int[] arr) {
        Chapter4.MaxHeap maxHeap = new Chapter4.MaxHeap(arr.length);
        for (int i = 0; i < arr.length; i++) {
            maxHeap.insert(arr[i]);
        }
        // 得到从小到大的顺序
        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = maxHeap.extractMax();
        }
    }

    public static void heapSort2(int[] arr) {
        // 得到从小到大的顺序
        Chapter4.MaxHeap maxHeap = new Chapter4.MaxHeap(arr);
        for (int i = arr.length - 1; i >= 0; i--) {
            arr[i] = maxHeap.extractMax();
        }
    }


    /**
     * 最大堆
     */
    public static class MaxHeap {
        private int capacity = 100;
        private int[] data = null;
        private int count = 0;

        public MaxHeap() {
            this.data = new int[this.capacity];
            this.count = 0;
        }

        public MaxHeap(int capacity) {
            /**
             *  虽然一组数据可以创建出多个最大堆，但是这些最大堆的根节点一定都是相同的，即这些数据中的最大值。
             *  操作过程中我们要维持的是堆的定义而不是堆的形状。
             */
            this.capacity = capacity;// 堆中最大能够存储的数据量。
            this.data = new int[this.capacity + 1];// 用于存储堆中的数据，从索引为1处开始进行存储。
            this.count = 0;// count 表示堆中元素的个数，
        }

        /**
         * 从一个不是堆的数组上建堆
         *
         * @param data 建堆的数据
         */
        public MaxHeap(int[] data) {
            this.data = new int[data.length + 1];
            this.count = this.data.length - 1;
            this.capacity = this.data.length - 1;
            System.arraycopy(data, 0, this.data, 1, data.length);
            int begin = (this.data.length - 1) / 2; // 堆是一个完全二叉树， n/2 之后的节点 即叶子节点，都相当于是一个最大堆或最小堆了
            while (begin >= 1) {
                shiftDown(begin);// 进行shiftDown操作，使得 begin对应的也是一个最大堆，或最小堆。
                begin--;// 从后向前处理，直至到 根节点。
            }

        }

        /**
         * 向堆中添加一个元素，元素被放置到数组末尾，然后进行shiftUp操作，从而确定元素在堆中的位置
         *
         * @param element 被添加的元素
         */
        public void insert(int element) {
            if (this.count + 1 > this.capacity) {
                throw new RuntimeException(" 超过容量 ");
            }
            this.data[++this.count] = element; // 将数据存储到堆中
            shiftUp(this.count); // 确定数据在堆中存储的位置
        }

        /**
         * 从堆中取出一个元素，只能取出根节点出的元素，即最大的元素或最小的元素，然后将堆中最后一个元素放到堆顶，进行shiftDown操作，
         * 重新确定一个新堆，即维护堆的定义
         *
         * @return 堆中的最大值
         */
        public int extractMax() {
            if (this.count <= 0) {
                throw new RuntimeException(" 堆中没有数据 ");
            }
            Tool.swap(this.data, 1, this.count);
            int retEle = this.data[count--];
            shiftDown(1);
            return retEle;
        }

        public int getCount() {
            return this.count;
        }

        public int[] getData() {
            return this.data;
        }

        public int getCapacity() {
            return this.capacity;
        }

        private void shiftDown(int current) {
            if (current < 1 || 2 * current > count) {
                return;
            }
            int comPosition = 2 * current;
            if (2 * current + 1 <= this.count) {
                comPosition = this.data[comPosition] < this.data[comPosition + 1] ? comPosition + 1 : comPosition;
            }
            if (this.data[current] < this.data[comPosition]) {
                Tool.swap(this.data, comPosition, current);
                shiftDown(comPosition);
            }

        }

        private void shiftUp(int current) {
            if (current <= 0 || current >= count + 1) {
                throw new RuntimeException(" 位置越界 ");
            }
            if (current == 1) {
                return;
            }
            if (data[current / 2] < data[current]) {
                Tool.swap(data, current, current / 2);
                shiftUp(current / 2);
            }
        }
    }

    /**
     * 索引堆, 使用索引堆，可以保持与原数组之间的关联， 存储的不再是元素而是元素的索引。
     * 比较的data，交换的是索引, A是 B的软链接。
     */
    public static class IndexMaxHeap {
        private int[] data;
        private int[] indexes;// 存储data中的索引,indexes[i] =j ,则inverse[j] = i
        // 存储indexes中的索引，类似于双向链表。 inverse[indexes[i]]=i,  indexes[inverse[j]]=j, 因为data[0] 不用来存储数据
        // 所以用 0 来初始化 inverse
        private int[] inverse;
        private int capacity;
        private int count;

        public IndexMaxHeap() {
            this.capacity = 100;
            this.count = 0;
            this.data = new int[this.capacity + 1];
            this.indexes = new int[this.capacity + 1];
            this.inverse = new int[this.capacity + 1];
        }

        public IndexMaxHeap(int capacity) {
            this.capacity = capacity;
            this.count = 0;
            this.data = new int[this.capacity + 1];
            this.indexes = new int[this.capacity + 1];
            this.inverse = new int[this.capacity + 1];
        }

        public int getCapacity() {
            return this.capacity;
        }

        public int getCount() {
            return this.count;
        }

        public int[] getData() {
            return this.data;
        }

        public int[] getIndexes() {
            return this.indexes;
        }

        public int[] getInverse() {
            return this.inverse;
        }

        private void shiftUp(int curPos) {
            if (curPos <= 1 || curPos > count) {
                return;
            }
            int parPos = curPos / 2;
            if (this.data[this.indexes[parPos]] < this.data[this.indexes[curPos]]) {
                Tool.swap(this.indexes, curPos, parPos);
                this.inverse[this.indexes[curPos]]=curPos;
                this.inverse[this.indexes[parPos]]=parPos;
                this.shiftUp(curPos / 2);
            }
        }

        private void shiftDown(int curPos) {
            if (2 * curPos > count) {
                return;
            }
            int comPos = 2 * curPos;
            if (comPos + 1 <= count) {
                comPos = this.data[this.indexes[comPos]] < this.data[this.indexes[comPos + 1]] ? comPos + 1 : comPos;
            }
            if (this.data[this.indexes[curPos]] < this.data[this.indexes[comPos]]) {
                Tool.swap(this.indexes, curPos, comPos);
                this.inverse[this.indexes[curPos]] = curPos;
                this.inverse[this.indexes[comPos]] = comPos;
                shiftDown(comPos);
            }
        }

        public int extractIndexMax() {
            int indexMax = this.indexes[1];
            Tool.swap(this.indexes, 1, this.count);
            this.inverse[this.indexes[1]]=1;
            this.inverse[this.indexes[this.count]]=0;
            this.count--;
            this.shiftDown(1);
            return indexMax;
        }

        /**
         * 插入元素
         *
         * @param ele 用户插入的元素
         */
        public void insert(int ele) {
            assert count + 1 <= capacity : " 容量已满，无法插入元素";
            count++;
            data[count] = ele;
            this.indexes[count] = count;
            this.inverse[count] = count;
            this.shiftUp(count);
        }

        public int extractMax() {
            return this.data[this.extractIndexMax()];
        }
       public int  getItem(int index){
           assert index>=0&&index<this.count:" 索引越界";
           assert this.inverse[index+1]==0:" 元素不存在";
           return this.data[index+1];
       }
        public void change(int index,int item){
            assert index>=0&&index<this.count:" 索引越界";
            assert this.inverse[index+1]!=0:" 元素不存在";
            index = index+1;
            this.data[index] = item;
            int i = this.inverse[index];
            this.shiftDown(i);
            this.shiftUp(i);
        }
    }

}

