import java.util.*;
import java.math.*;

class ArrayProblems {
    
    public static void main(String[] args) {
        
        //printCollection(input);
        //evenOdd(input);
        //printCollection(input);
        
        int[] steps = {3,3,1,0,2,0,0};
        boolean canReachEnd = canReachEnd(steps);
        System.out.println(canReachEnd);
        List<Integer> intList = Arrays.asList(2,4,1,2,3,1,2,3,1,4,4,1,4,2,3,3,2,1,3,3,2,4,2,1,3,2,1,2,3,1,4);
        //List<Integer> intList = Arrays.asList(3,1,4,2,1,4,2,3);
        printCollection(intList);
        groupValues4PossibleValue(intList, 1, 2, 3, 4);
        printCollection(intList);
        

        int[] input = {1,1,3,3,3,5,7,7,7,9};
        int uniqCount = removeDuplicateInSortedArray(input);
        System.out.println(uniqCount);


        int[] prices = {12,11,13,9,12,8,14,13,15};
        System.out.println(maxProfitOneSell(prices));
        System.out.println(maxProfitTwoSell(prices));
        

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
    public static int maxProfitOneSell(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = Integer.MIN_VALUE;
        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            }
            int profit = price - minPrice;
            if (profit > maxProfit) {
                maxProfit = profit;
            }
        }
        return maxProfit;
    }
    
    // second buy must after first sell
    public static int maxProfitTwoSell(int[] prices) {
        List<Integer> firstProfit = new ArrayList<Integer>();
        int firstMin = Integer.MAX_VALUE;
        int firstMaxProfit = 0;
        for (int p : prices) {
            firstMin = Math.min(p, firstMin);
            firstMaxProfit = Math.max(firstMaxProfit, p - firstMin);
            firstProfit.add(firstMaxProfit);
        }
        
        List<Integer> secondProfit = new ArrayList<Integer>(prices.length);
        for (int i = 0; i < prices.length; ++i) {
            secondProfit.add(0);
        }
        int secondMax = Integer.MIN_VALUE;
        int secondMaxProfit = 0;
        for (int i = prices.length - 1; i >= 0; i--) {
            secondMax = Math.max(prices[i], secondMax);
            secondMaxProfit = Math.max(secondMaxProfit, secondMax - prices[i]);
            secondProfit.set(i, secondMaxProfit);
        }
        
        int totalMax = 0;
        for (int i = 0; i < firstProfit.size() - 1; ++i) {
            totalMax = Math.max(totalMax, firstProfit.get(i) + secondProfit.get(i+1));
        }
        
        return totalMax;
    }
    
    public static void dutchFlagPartition(List<Integer> input) {
        Integer pivot = input.get(0);
        
        int smaller = 0, equals = 0, bigger = input.size();
        while (equals < bigger) {
            if (input.get(equals) < pivot) {
                Collections.swap(input, equals++, smaller++);
            } else if (input.get(equals) == pivot) {
                equals++;
            } else {
                Collections.swap(input, equals, --bigger);
            }
        }
    }
    
    public static void groupValues4PossibleValue(List<Integer> input, Integer value1, Integer value2, 
                                                Integer value3, Integer value4) {
        int index1 = 0, index2 = 0, index3 = input.size();
        int current = 0;
        
        while (current < index3) {
            if (input.get(current) == value1) {
                Collections.swap(input, index1, current++);
                index1++;
                index2++;
            } else if (input.get(current) == value2) {
                Collections.swap(input, index2, current++);
                index2++;
            } else if (input.get(current) == value3 || input.get(current) == value4) {
                Collections.swap(input, current, --index3);
            } else {
                throw new RuntimeException("unknow value:" + input.get(current));
            }
        }
        System.out.println("current:" + current);
        System.out.println("index3:" + index3);
        
        current = index3;
        while(current < input.size()) {
            if (input.get(current) == value3) {
                Collections.swap(input, current, index3);
                index3++;
            }
            current++;
        }
    }
    
    static <E> void printCollection(Collection<E> input) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (E o : input) {
            sb.append(o).append(",");
        }
        sb.append("]");
        System.out.println(sb.toString());
    }
}