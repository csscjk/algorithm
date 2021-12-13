package suanfayushujujiegou.chapter4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import suanfayushujujiegou.tools.Tool;

public class Chapter4Test {
    private int[] arr;
    @Before
    public void before(){
        int arrLength = 1_00_0000;
        int rangL = 12;
        int rangR = 1_0000_0000;
        this.arr= Tool.generateRandomArray(arrLength, rangL, rangR);
        Tool.testStart();
    }
    @After
    public void after(){
        Tool.testEnd();
        Tool.isSorted(arr);
    }
    @Test
    public void testHeapSort() {
        Chapter4.heapSort2(arr);
        Tool.soutArr(arr,1,20);
    }
}