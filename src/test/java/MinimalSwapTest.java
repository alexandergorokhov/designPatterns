import minimal.swap.MinimalSwap;
import org.junit.Assert;
import org.junit.Test;

public class MinimalSwapTest {



    int[] array = {7, 1, 3, 2, 4, 5, 6};
    int[] arrayMin = {7, 1, 3, 2, 4, 5, 6};
    int[] arrayOrdered = {1,2,3,4,5};

    @Test
    public void findMaximumTest(){
        int result = MinimalSwap.findMaximumReturnValue(array);
        Assert.assertEquals(7,result);
    }

    @Test
    public void findMinimumTest(){
        int result = MinimalSwap.findMinimumReturnValue(array);
        Assert.assertEquals(1,result);
    }
    @Test
    public void findNumberIndex(){
        int number = 2;
        int result = MinimalSwap.findNumberReturnIndex(array, number);
        Assert.assertEquals(3,result);

        number = 7;
        result = MinimalSwap.findNumberReturnIndex(array, number);
        Assert.assertEquals(0,result);
    }


    @Test
    public void isArrayOrderedTest(){
        boolean result = MinimalSwap.isArraySorted(array);
        Assert.assertEquals(false,result);


        result = MinimalSwap.isArraySorted(arrayOrdered);
        Assert.assertEquals(true,result);
    }

    @Test
    public void minimalSwapsTest1(){
        int[] array = {7, 1, 3, 2, 4, 5, 6};
        int result = MinimalSwap.findMinimalSwaps(array);
        Assert.assertEquals(5,result);


    }

    @Test
    public void minimalSwapsTest2(){
        int[] array = {4,1,2,3};
        int result = MinimalSwap.findMinimalSwaps(array);
        Assert.assertEquals(3,result);

    }

    @Test
    public void minimalSwapsTest3(){
        int[] array = {2,3,4,1,5};
        int result = MinimalSwap.findMinimalSwaps(array);
        Assert.assertEquals(3,result);

    }

    @Test
    public void minimalSwapsTest4(){
        int[] array = {1,3,5,2,4,6,7};
        int result = MinimalSwap.findMinimalSwaps(array);
        Assert.assertEquals(3,result);

    }

    @Test
    public void minimalSwapsTest5(){
        int[] array = {1,3,5,2,4,6,7};
        int result = MinimalSwap.minimumSwapsExternal(array);
        Assert.assertEquals(3,result);

    }

    @Test
    public void minimalSwapsTest6(){
        int[] array = {1,3,5,2,4,6,7};
        int result = MinimalSwap.minimumSwapsMy(array);
        Assert.assertEquals(3,result);

    }

}
