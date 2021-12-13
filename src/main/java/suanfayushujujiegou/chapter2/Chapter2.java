package suanfayushujujiegou.chapter2;

import suanfayushujujiegou.tools.Tool;

public class Chapter2 {
    /**
     * 选择排序，时间复杂度O(n^2)
     */
     public  static void selectSort(int[] arr){
          assert arr!=null:" selectSort 中 arr 为 null";
          for(int i=0; i<arr.length;i++){
              int minIndex = i;
              for(int j=i+1;j<arr.length;j++){
                  if(arr[j]<arr[minIndex]){
                      minIndex=j;
                  }
              }
              if(minIndex!=i){
                  Tool.swap(arr,i,minIndex);
              }
          }
     }

    /**
     *  插入排序时间复杂度 O(n^2), 插入排序非常适合对近乎有序的数据进行排序。
     */
     public static void insertSort(int[] arr){
         assert arr!=null:" insertSort arr 为 null;";
         for(int i=1;i<arr.length;i++){
             int current=arr[i]; // 用 current 指代当前 需要确定位置的元素
             int j=i-1;
             for(;j>=0&&current<arr[j];j--){
               arr[j+1]=arr[j];// 无需 将 current 的值赋给 j位置，因为下一次循环，current的位置又变回 j+1
             }
             arr[j+1]=current;
         }
     }

    /**
     *  归并排序 时间复杂度 O(nlogn),空间复杂度O(n)
     */

}
