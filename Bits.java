import java.util.*;

//
// Bits
// Created by Ren, Qide on 4/28/16.

public class Bits {
    
    // swap i-th and j-th bit
    public static long swapBits(long n, int i, int j) {
        // only need to change if i-th & j-th bits are different
        if (((n >>> i) & 1) != ((n >>> j) & 1)) {
            // only need to flip, using xor
            long bitMask = (1 << i) | (1 << j);
            n ^= bitMask;
        }
        return n;
    }
    
    static long closestIntSameBitCount(long n) {
        // swap right most 2 bits that differ
        final int MAX_BITS = 63;
        
        for (int i = 0; i < MAX_BITS - 1; ++i) {
            if (((n >>> i) & 1) != ((n >>> i + 1) & 1)) {
                long bitMask = (1 << i) | (1 << i + 1);
                return n ^ bitMask;
            }
        }
        throw new RuntimeException("all 0s/1s");
    }
    
    static long multiply(long a, long b) {
        long sum = 0;
        while (a != 0) {
            if ((a & 1) != 0) {
                sum += b;
            }
            b <<= 1;
            a >>>= 1;
        }
        return sum;
    }
    
    static long divide(long x, long y) {
        long result = 0;
        int power = 32;
        long yPower = y << power;
        while (x > y) {
            while (x < yPower) {
                power--;
                yPower >>>= 1;
            }
            
            result += (1 << power);
            x -= yPower;
        }
        
        return result;
    }
    
    static double power(double a, long n) {
        double result = 1.0;  // a^0 is 1
        long power = n;
        
        while (power > 0) {
            if ((power & 1) > 0) {
                result *= a;
            }
            a *= a;
            power >>>= 1;
        }
        
        return result;
    }
    
    static long reverse(long x) {
        long remain = x;
        long result = 0;
        while (remain > 0) {
            result = result * 10 + remain % 10;
            remain = remain / 10;
        }
        return result;
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //double d = scanner.nextDouble();
        long n = scanner.nextLong();
        //long m = scanner.nextLong();
        
        //System.out.println(Long.toBinaryString(n));
        
        //long x = swapBits(n, i, j);
        //long x = closestIntSameBitCount(n);
        //long x = multipley(n, m);
        //long x = divide(n, m);
        //double x = power(d, m);
        long x = reverse(n);
        System.out.println(x);
        //System.out.println(Long.toBinaryString(x));        
    }
}