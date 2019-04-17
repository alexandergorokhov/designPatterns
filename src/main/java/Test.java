public class Test {


    public static void main(String[] args) {
        new Test().testOdd(2827534);

        new Test().testPalindrome("srenners");

        new Test().testPalindrome("bbdret");
        new Test().testPalindrome("aba");
    }

    private void testPalindrome(String palindrome) {
        Palindrom p = (a) -> {
            char[] orgString = palindrome.toCharArray();
            boolean result = true;
            for (int ai = 0; ai <= orgString.length / 2; ai++) {
                if (orgString[ai] == orgString[orgString.length - 1 - ai]) {
                    continue;
                } else {
                     result = false;
               //     break;
                }
            }
            return result;
        };

        System.out.println("input string " + palindrome + " is a palindrome: " + p.isPalindrome(palindrome));
    }

    private void testOdd(int numberToTest) {
        Odd o = (a) -> ((a % 2) != 0 ? "Odd" : "Even");
        System.out.println(o.isOdd(numberToTest));
    }

//    private void testPalindrome(String palindrome) {
//        StringBuilder stringBuilder = new StringBuilder(palindrome);
//        System.out.println("input string " + palindrome + " is a palindrome: " + palindrome.equals(stringBuilder.reverse().toString()));
//    }



    interface Palindrom {
        boolean isPalindrome(String a);

    }

    interface Odd {
        String isOdd(int a);
    }
}