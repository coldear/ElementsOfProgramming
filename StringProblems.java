class StringProblems {
    public static void main(String[] args) {
        System.out.println(stringToInt(intToString(-10)));
        System.out.println(stringToInt(intToString(10)));
        System.out.println(stringToInt(intToString(11235)));
        System.out.println(stringToInt(intToString(1)));
    }
    
    static int stringToInt(final String str) {
        boolean isNegtive = str.charAt(0) == '-';
        int result = 0;
        int start = isNegtive ? 1 : 0;
        for (int i = start; i < str.length(); ++i) {
            result = result * 10 + str.charAt(i) - '0';
        }
        
        return isNegtive ? -result : result;
    }
    
    static String intToString(int input) {
        boolean isNegative = false;
        if (input < 0) {
            isNegative = true;
            input *= -1;
        }
        
        StringBuilder sb = new StringBuilder();
        while (input > 0) {
            sb.append((char)('0' + input % 10));
            input /= 10;
        }
        if (isNegative) {
            sb.append("-");
        }
        
        return sb.reverse().toString();
    }
    
    
}