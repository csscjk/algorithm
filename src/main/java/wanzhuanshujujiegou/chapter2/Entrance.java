package wanzhuanshujujiegou.chapter2;

import javax.rmi.ssl.SslRMIClientSocketFactory;
import java.util.Arrays;

/**
 * Date: 2021/12/8 19:15
 */

public class Entrance {
    public static void main(String[] args) {
       int[] arr = new int[10];
       for(int i=0;i<10;i++){
           arr[i]=i;
       }
        System.arraycopy(arr,3,arr,0,5);
        System.out.println(Arrays.toString(arr));
    }
}
