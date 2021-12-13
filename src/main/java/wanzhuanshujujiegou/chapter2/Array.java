package wanzhuanshujujiegou.chapter2;

/**
 * Date: 2021/12/8 19:30
 */

/**
 * 创建Array类，对java数组进行二次封装
 */
public class Array {
    private int[] data;// 数据
    private int size; // 数组中存储的元素的个数

    /**
     * 用户传入 capacity，构建数组
     *
     * @param capacity 数组的容量，即最多能够存放多少个元素
     */
    public Array(int capacity) {
        this.data = new int[capacity];
        this.size = 0;
    }

    /**
     * 用户不知道capacity是多少时，可以调用该方法
     */
    public Array() {
        // 将capacity设置为 10
        this(10);
    }

    /**
     * 查看数组中有多少元素
     *
     * @return 数组中元素的个数
     */
    public int getSize() {
        return this.size;
    }

    /**
     * 查看数组的容量
     *
     * @return 数组的容量
     */
    public int getCapacity() {
        return data.length;
    }

    /**
     * 数组中是否有元素
     *
     * @return 数组中是否有元素
     */
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * 向数组末尾添加一个元素
     *
     * @param e 被添加到数组中的元素
     */
    public void addLast(int e) {
        this.add(this.size, e);
    }

    /**
     * 向数组头部添加一个元素
     *
     * @param e 被添加的元素
     */
    public void addFirst(int e) {
        this.add(0, e);
    }

    /**
     * 在指定位置处添加一个元素
     *
     * @param index 添加的位置
     * @param e     添加的元素
     */
    public void add(int index, int e) {
        assert this.size < this.data.length : " add failed,Array is full";
        assert index >= 0 && index <= this.size : " add failed ,index is illegal";
        // 操作同一个数组相当于是，将数组的某一部分进行整体的平移
        System.arraycopy(this.data, index, this.data, index + 1, this.size - index);
        this.data[index] = e;
        this.size++;
    }

    /**
     * 根据索引获取元素
     *
     * @param index 元素的索引
     * @return index处的元素
     */
    public int get(int index) {
        assert index >= 0 && index < this.size : " get failed ,index is illegal";
        return this.data[index];
    }

    /**
     * 修改指定位置处的元素
     *
     * @param index 元素的索引
     * @param e     index处元素的新值
     */
    public void set(int index, int e) {
        assert index >= 0 && index < this.size : " set failed ,index is illegal";
        this.data[index] = e;
    }

    /**
     * 查看数组中是否存在指定的元素
     *
     * @param e 指定的元素
     * @return e是否存在数组中
     */
    public boolean contains(int e) {
        for (int i = 0; i < this.size; i++) {
            if (this.data[i] == e) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查看元素e 在数组中的索引，如果e不存在于数组中则返回-1
     *
     * @param e 查找的元素
     * @return e在数组中的索引
     */
    public int find(int e) {
        for (int i = 0; i < this.size; i++) {
            if (this.data[i] == e) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 删除指定位置处的元素并将该元素返回
      * @param index 指定位置处的元素
     * @return 被删除的元素
     */
 public int remove(int index){
        assert index>=0&&index<this.size:" remove failed, index is illegal";
        int removeElement = this.data[index];
     if (this.size - 1 - index >= 0) System.arraycopy(this.data, index + 1, this.data, index, this.size - 1 - index);
        this.size--;
        return removeElement;
 }

    /**
     * 删除第一个元素
     * @return 被删除的元素
     */
 public int removeFirst(){
     return this.remove(0);
 }

    /**
     * 删除数组中最后一个元素
     * @return 被删除的元素
     */
    public  int removeLast(){
     return this.remove(this.size-1);
}

    /**
     * 给定一个元素，如果数组中存在该元素则删除该元素
     * @param e 被删除的元素
     * @return   是否进行了删除操作
     */
    public boolean removeElement(int e){
        int index = this.find(e);
        if(index!=-1){
            this.remove(index);
            return true;
        }
        return false;
}
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Array: size=%d , capacity=%d ,", this.size, this.data.length));
        builder.append("data=[ ");
        for (int i = 0; i < this.size; i++) {
            builder.append(this.data[i]);
            if (i != this.size - 1) {
                builder.append(",");
            }
        }
        builder.append(" ] ");
        return builder.toString();
    }
}
