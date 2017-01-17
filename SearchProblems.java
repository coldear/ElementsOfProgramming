import java.util.*;

class SearchProblems {
    public static void main(String[] args) {
        for (int i = 0; i < 10; ++i) {
            int kTh = findKth(Arrays.asList(4,5,6,1,2,7,9,0,3,8), i);
            System.out.println("#############" + kTh);
        }
    }
    
    // devide and conquer 
    public static int findKth(List<Integer> A, int k) {
        int left = 0, right = A.size() - 1;
        int result = -666;
        while (left <= right) {
            int pivotIndex = (right + left) / 2;
            System.out.println("left=" + left + ";right=" + right + ";pivotIndex=" + pivotIndex);
            System.out.println("before: " + A);
            int partitionIndex = partitionAroundPivot(A, left, right, pivotIndex);
            if (partitionIndex == k) {
                result = A.get(partitionIndex);
                break;
            } else if (partitionIndex > k) {
                right = partitionIndex - 1;
            } else {
                left = partitionIndex + 1;
            }
        }
        return result;
    }
    
    public static int partitionAroundPivot(List<Integer> A, int left, int right, int pivotIndex) {
        int pivotValue = A.get(pivotIndex);
        int partitionIndex = left;
        printSubList(A, left, right);
        
        Collections.swap(A, right, pivotIndex);
        for (int i = left; i < right; ++i) {
            if (A.get(i) < pivotValue) {
                Collections.swap(A, i, partitionIndex++);
            }
        }
        Collections.swap(A, right, partitionIndex);
        
        return partitionIndex;
    }
    
    public static <T> void printSubList(List<T> A, int left, int right) {
        System.out.print("sub list:[");
        for (int i = left; i <= right; ++i) {
            System.out.print(A.get(i));
            System.out.print(",");
        }
        System.out.println("]");
    }
}