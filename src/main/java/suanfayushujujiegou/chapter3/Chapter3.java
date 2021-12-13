package suanfayushujujiegou.chapter3;


import suanfayushujujiegou.tools.Tool;

public class Chapter3 {
    //-----归并排序-------

    /**
     * 归并排序时间复杂度 O(nlogn)
     * 对 arr 的 [left,right] 区域进行排序 和 归并
     * 自顶向下的实现
     * <p>
     * 思想： 先将数据一份为二，对着两部分数据进行排序，对这两部分排完序后再将这两部分进行合并。
     * 对这两部分数据进行排序时，也执行同样的操作，先将每份数据一份为二，然后进行排序，合并，。。 不断递归，直至数据量变为1
     */
    public static void mergerSort(int[] arr, int left, int right) {
        if (left >= right || right >= arr.length || left < 0) {
            return;
        }
        int mid = left + (right - left) / 2;
        mergerSort(arr, left, mid);
        mergerSort(arr, mid + 1, right);
        merger(arr, left, mid, right);
    }

    /**
     * 对 arr的  [left,right]范围 进行归并。
     */
    private static void merger(int[] arr, int left, int mid, int right) {
        if (arr[mid] <= arr[mid + 1]) {
            return;
        }
        if (arr[left] >= arr[right]) {
            while (left < right) {
                Tool.swap(arr, left, right);
                left++;
                right--;
            }
            return;
        }
        int[] copy = new int[right - left + 1];
        for (int i = left; i <= right; i++) {
            copy[i - left] = arr[i];
        }
        int leftCur = left;// 左边下次需要去比较的元素
        int rightCur = mid + 1;// 右边下次需要去比较的元素
        int curPos = left;// 下次需要填充数值的位置
        while (leftCur <= mid && rightCur <= right) {
            if (copy[leftCur - left] < copy[rightCur - left]) {
                arr[curPos] = copy[leftCur - left];
                curPos++;
                leftCur++;
            } else {
                arr[curPos] = copy[rightCur - left];
                curPos++;
                rightCur++;
            }
        }
        while (leftCur <= mid) {
            arr[curPos] = copy[leftCur - left];
            leftCur++;
            curPos++;
        }
        while (rightCur <= right) {
            arr[curPos] = copy[rightCur - left];
            rightCur++;
            curPos++;
        }
    }

    /**
     * 归并排序: 自低向上的实现
     */
    public static void mergerSortBU(int[] arr) {
        for (int sz = 1; sz <= arr.length; sz += sz) {
            for (int i = 0; i + sz < arr.length; i += sz + sz) {
                // 注意 一组内的数据已经有序， merger 操作 是对两组数据进行合并， i 到 i+sz-1 内的数据已经有序了，
                // 两组数据的长度不同也没关系，因为，我们可以通过 i，i+size-1这些索引判断出 哪些部分的数据的相对位置已经确定了。
                merger(arr, i, i + sz - 1, Math.min(i + sz + sz - 1, arr.length - 1));
            }
        }
    }

    //-------快速排序-------------

    /**
     * 先从总共的数据中选择一个数据，然后通过将该数据与其他数据进行比较从而得到该数据的最终位置，
     * 在比较的时候会将小于该数据的的元素放到该数组前面，大于该数据的元素放到数组后面，这样当该数据的位置确定的时候
     * 小于该数据的元素就会在该数据所在位置之前，大于该数据的元素就会在该数据所在位置之后。
     * 然后对该数据所在位置之前的元素 和 该数据所在位置之后的元素 重复之前 上述操作， 元素个数为 1 时结束。
     */
    public static void quickSort(int[] arr, int begin,int end) {

        if(begin>=end){
            return;
        }
        int position = partition(arr,begin,end);
        quickSort(arr,begin,position-1);
        quickSort(arr,position+1,end);
    }

    /**
     * 返回 position , position满足 条件: arr[begin,position-1] <= arr[position] <= arr[position+1,end]
     */
    private static int partition(int[] arr,int begin,int end){
        int position=begin;
        int curEle = arr[begin];
        // 确定 curEle的位置
        // 整个过程满足条件 :  arr[begin+1,position] < curEle; curEle < arr[position+1,i)
        for(int i=begin+1;i<=end;i++){
            // 如果 arr[i]>= curEle , i直接++，就行。
            // 但是如果 arr[i] < curEle,那么就需要将 arr[i] 和  arr[position+1] 进行交换，并令 position++
            if(arr[i]<curEle){
                Tool.swap(arr,i,position+1);
                position++;
            }
        }
        Tool.swap(arr,position,begin);
        return position;
    }

}
