package suanfayushujujiegou.chapter2;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import suanfayushujujiegou.tools.Tool;

public class Chapter2MainTest  {
    private int[] arr;
    @Before
    public void before(){
        int arrLength = 1000000;
        int rangL = 12;
        int rangR = 10000;
        this.arr= Tool.generateRandomArray(arrLength, rangL, rangR);
        Tool.testStart();
    }
    @After
    public void after(){
        Tool.testEnd();
        Tool.isSorted(arr);
    }
    @Test
    public void testSelectSort(){
      Chapter2.selectSort(arr);
    }
    @Test
    public void testInsertSort(){
        Chapter2.insertSort(arr);
    }


}