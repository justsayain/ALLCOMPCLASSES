 import java.util.Scanner;  
	public class PalindromeNumber
   {
      public static void main(String args[]){
      
         System.out.println("Please Enter a number : ");
         int palindrome = new Scanner(System.in).nextInt();
      
         if(isPalindrome(palindrome)){
            System.out.println(palindrome + " is a prime and also a palindromic prime.");
         }
         else{
            System.out.println(palindrome + " is not a prime nor a palindromic prime.");
         } 
      
      }
   	
		public static boolean isPrime(int number) {
         for (int i = 2; i<= number / 2; i++) {
            if (number % i == 0)
               return false;
         }
         return true;
      }

      public static boolean isPalindrome(int number) {
         int palindrome = number; 
         int reverse = 0;
      
         while (palindrome != 0) {
            int remainder = palindrome % 10;
            reverse = reverse * 10 + remainder;
            palindrome = palindrome / 10;
         }
			  if (number == reverse) {
            return true;
         }
         return false;
      }
   
   }
