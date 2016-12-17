import java.util.*;

class ArrayProblems {
    
    public static void main(String[] args) {
        
        //printArray(input);
        //evenOdd(input);
        //printArray(input);
        
        int[] steps = {3,3,1,0,2,0,0};
        boolean canReachEnd = canReachEnd(steps);
        System.out.println(canReachEnd);

        int[] input = {1,1,3,3,3,5,7,7,7,9};
        int uniqCount = removeDuplicateInSortedArray(input);
        System.out.println(uniqCount);


        double[] prices = {310,310,275,295,260,270,290,230,255,250};
        System.out.println(maxProfitOneSell(prices));

    }
    
    /* partion array into 2 parts, even numbers and odd number.
    solution: use 2 cursor, one starts from 0, the other starts from last index.
    */
    static void evenOdd(int[] input) {
        int nextEven = 0;
        int nextOdd = input.length - 1;
        while (nextEven < nextOdd) {
            if (input[nextEven] % 2 == 0) {
                nextEven++;
            } else {
                int temp = input[nextEven];
                input[nextEven] = input[nextOdd];
                input[nextOdd] = temp;
                nextOdd--;
            }
        }
    }
    
    /*
    game: A[i] means max position one can advance from i. given array of max steps,
    return if it's possible to reach the end.
    solution: iterating the input, remembering the furthest position we can reach so far
    */
    public static boolean canReachEnd(int[] maxAdvanceSteps) {
        int furthestPosition = 0;
        for (int i = 0; i < maxAdvanceSteps.length; ++i) {
            if (i + maxAdvanceSteps[i] > furthestPosition) {
                furthestPosition = i + maxAdvanceSteps[i];
            }
            if (furthestPosition <= i && i != maxAdvanceSteps.length - 1) {
                return false;
            }
        }
        return true;
    }
    
    /*
    * solution: two cursors, one for read, one for write
    */
    public static int removeDuplicateInSortedArray(int[] input) {
        int writeIndex = 1;
        for (int i = 1; i < input.length; ++i) {
            if (input[i - 1] != input[i]) {
                input[writeIndex++] = input[i];
            }
        }
        int count = writeIndex;
        while (writeIndex < input.length) {
            input[writeIndex++] = 0;
        }
        return count;
    }
    
    /*
    * given array of stock price on different days, find max profit you can make by buying and selling one share.
    * solution: max profit on each day is the difference between current price and the lowest price we've seen so far.
    * compute that for each day and return the max among them.
    */
    public static double maxProfitOneSell(double[] prices) {
        double minPrice = Double.MAX_VALUE;
        double maxProfit = Double.MIN_VALUE;
        for (double price : prices) {
            if (price < minPrice) {
                minPrice = price;
            }
            double profit = price - minPrice;
            if (profit > maxProfit) {
                maxProfit = profit;
            }
        }
        return maxProfit;
    }
    
    static void printArray(int[] input) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int o : input) {
            sb.append(o).append(",");
        }
        sb.append("]");
        System.out.println(sb.toString());
    }
}