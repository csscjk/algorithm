package wanzhuansuanfamianshi.chapter3;

import junit.framework.TestCase;

import java.util.HashSet;
import java.util.Set;

public class Tools3Test extends TestCase {

    public void testTool1() {
        int[] arr1 = {0,110,0,4,2,4,0,1};
        Tools3.tool1(arr1);
    }
    public void testTool2(){
        int[] arr1={0,2,1,0,1,2,0,1,2};
        Tools3.tool2_1(arr1);
    }
    public void testTool3(){
        int[] arr1={1,2,3,4,5,6,7,8};
        Tools3.tool3(arr1,10);
    }
    public void testTool4(){
        int[] arr1={5,1,2,3,5,4,6,7,8};
     Tools3.tool4(arr1,199);

    }
    public void testTool5(){

        Tools3.tool5("abcdefgadkc");
    }
    public void testTool(){
        int[] arr1={9,11,12,14,22,33,4,5,5,6,7,8};
        int[] arr2={5,1,2,3,5,4,6,7,8};
       // Tools3.tool6(arr1,arr2);
        Tools3.tool7(arr1,arr2);
    }
    public void testTool8(){
        int[] arr1={9,11,14,3,1,
                9,22,33,4,5,5,6,7,8};
        Tools3.tool9(arr1,5);
    }

}