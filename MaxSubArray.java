import java.util.*;

class MaxSubArray {
	public static void main(String[] args) {
		int[] input = {13,-3,-25,20,-3,-16,-23,18,20,-7,12,-5,-22,15,-4,7};
		
		SubArray ret = findMaxSubArray(input, 0, input.length-1);
		System.out.println(ret);
	}
	
	
	public static class SubArray {
		int start;
		int end;
		int sum;
		
		@Override public String toString() {
			return String.format(Locale.US, "start:%d, end%d, max:%d", start, end, sum);
		}
	}
	
	static SubArray findMaxSubArray(int[] input, int low, int high) {
		SubArray ret = new SubArray();
		if (low == high) {
			ret.start = low;
			ret.end = low;
			ret.sum = input[low];
			return ret;
		}
		
		int mid = (low + high) / 2;
		SubArray leftSubArray = findMaxSubArray(input, low, mid);
		SubArray rightSubArray = findMaxSubArray(input, mid + 1, high);
		SubArray crossSubArray = findMaxSubArrayAcrossMid(input, low, high, mid);
		
		if (leftSubArray.sum >= rightSubArray.sum && leftSubArray.sum >= crossSubArray.sum) {
			return leftSubArray;
		} else if (rightSubArray.sum >= leftSubArray.sum && rightSubArray.sum >= crossSubArray.sum) {
			return rightSubArray;
		} else {
			return crossSubArray;
		}
	}
	
	static SubArray findMaxSubArrayAcrossMid(int[] input, int low, int high, int mid) {
		int leftSum = Integer.MIN_VALUE;
		int sum = 0;
		int leftPos = mid;
		for (int i = mid; i >= low; i--) {
			sum = sum + input[i];
			if (sum > leftSum) {
				leftSum = sum;
				leftPos = i;
			}
		}
		
		sum = 0;
		int rightSum = Integer.MIN_VALUE;
		int rightPos = mid;
		for (int i = mid + 1; i <= high; ++i) {
			sum = sum + input[i];
			if (sum > rightSum) {
				rightSum = sum;
				rightPos = i;
			}
		}
		
		SubArray ret = new SubArray();
		ret.start = leftPos;
		ret.end = rightPos;
		ret.sum = leftSum + rightSum;
		
		return ret;
	}
}