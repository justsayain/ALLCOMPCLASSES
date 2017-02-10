
   public class PalindromePrime
   {
      public static void main(String[] args)
      {
         int count = 1;
         for (int i = 100;i<=500 ; i++)
         {
            if ((isPrime(i)) && (isPalindrome(i))) {
               System.out.print(i + " ");
               if (count % 10 == 0) {
                  System.out.println();
               }
               if (count == 20)
               {
                  break;
               }
               count++;
            }
         }
      }
   
   
      public static boolean isPrime(int number) {
         for (int i = 2; i<= number / 2; i++) {
            if (number % i == 0) {
               return false;
            }
         }
         return true;
      }
   
   
      static int reversal(int number) {
         int result = 0;
         while (number != 0) {
            int lastDigit = number % 10;
            result = result * 10 + lastDigit;
            number /= 10;
         }
         return result;
      }
   
   
      static boolean isPalindrome(int number) {
         return number == reversal(number);
      }
   }