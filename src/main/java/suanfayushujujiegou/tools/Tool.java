package suanfayushujujiegou.tools;

import java.util.Arrays;
import java.util.Date;
import java.util.Random;

public class Tool {
    private static Date start;
    public static int[] generateRandomArray(int n ,int rangeL,int rangeR){
        assert rangeR >= rangeL:" rangR 需要大于 rangL";
        Random random = new Random();
        int[] arr = new int[n];
        for(int i=0;i<n;i++){
            arr[i] = rangeL+ random.nextInt(rangeR-rangeL+1);
        }
        return arr;
    }
    public static int[] generateNearlyOrderArray(int n,int swapTimes){
        int[] arr = new int[n];
        Random random = new Random();
        for(int i=0;i<arr.length;i++){
            arr[i]= random.nextInt(n+1);
        }
        Arrays.sort(arr);
        for(int i=0;i<swapTimes;i++){
            int j= random.nextInt(swapTimes);
            swap(arr,i,j);
        }

        return arr;
    }
    public static void soutArr(int[] arr){
        System.out.println(Arrays.toString(arr));
    }
    public static void soutArr(int[] arr,int begin,int end){

        System.out.println(Arrays.toString(Arrays.copyOfRange(arr,begin,end)));
    }
    public static void soutArr(int[][] arr){
        System.out.println(Arrays.deepToString(arr));;
    }
    public static void  testStart(){
        start=new Date();
    }
    public static void testEnd(){
        if(start==null){
            System.out.println(" Tool.start 为 null ");
            return;
        }
        Date end = new Date();
        double during = (end.getTime() - start.getTime())/1000.0;
        System.out.println(during+" s");
    }
    public static boolean isSorted(int[] arr){
       assert arr!=null:" isSorted 的参数 arr 为 null";
        for(int i=0;i<arr.length-1;i++){
            if(arr[i+1]<arr[i]){
                System.out.println("arr 无序");
                return false;
            }
        }
        System.out.println("arr 有序");
        return true;
    }
    public static void swap(int[] arr,int i,int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j]=temp;
    }
}
