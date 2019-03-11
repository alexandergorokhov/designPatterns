package minimal.swap;

import java.util.Arrays;
import java.util.stream.IntStream;

public class MinimalSwap {

    public static int findMinimalSwaps(int[] array) {

        int swapsCount = 0;
        boolean ordered = isArraySorted(array);
        int minIndex = findNumberReturnIndex(array, findMinimumReturnValue(array));
        int minNumber = findMinimumReturnValue(array);
        int switchIndex = 0;

        while (!ordered) {
            if (switchIndex != minIndex) {
                int aux = array[switchIndex];
                array[switchIndex] = array[minIndex];
                array[minIndex] = aux;
                swapsCount++;
            }


            if (isArraySorted(array)) {
                ordered = true;

            }
            minIndex = findNumberReturnIndex(array, ++minNumber);
            switchIndex++;

        }

        return swapsCount;
    }


    public static boolean isArraySorted(int[] arr) {

        int previous = 0;

        for (int i = 1; i <= arr.length - 1; i++) {
            if ((arr[previous] > arr[i])) return false;
            previous = i;
        }
        return true;
    }

    public static int findNumberReturnIndex(int[] array, int target) {

        int resultIndex = IntStream.range(0, array.length).filter(i -> target == array[i]).findFirst().getAsInt();
        return resultIndex;
    }

    public static int findMaximumReturnValue(int[] array) {
        int result = Arrays.stream(array).max().getAsInt();
        return result;
    }

    public static int findMinimumReturnValue(int[] array) {
        int result = Arrays.stream(array).min().getAsInt();
        return result;
    }

    public static int minimumSwapsExternal(int[] arr) {
        int first = 0;
        int last = arr.length - 1;
        int swaps = 0;
        while (first < last) {
            while (arr[first] == first + 1 && first < last) {
                first++;
            }
            if (first < last) {
                int temp = arr[arr[first] - 1];
                arr[arr[first] - 1] = arr[first];
                arr[first] = temp;
                swaps++;
            }
        }
        return swaps;
    }

    public static int minimumSwapsMy(int[] arr) {
        int first = 0;
        int last = arr.length - 1;
        int swaps = 0;
        while (first < last) {
            while (arr[first] == first + 1 && first < last) {
                first++;
            }
            if(first<last){
                int temp = arr[arr[first]-1];
                arr[arr[first]-1] = arr[first];
                arr[first] = temp;
                swaps++;
            }

        }


        return swaps;
    }


}
