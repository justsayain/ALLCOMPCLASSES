/*	Steve Delgado
Program Assignment #1
Due Date: 9/10/13 Date turned in:9/9/13
This program creates different methods that calculate how many right traingles you can have with given perimeter,
the next number that has double prime factors as well as the next double prime pair. It also calculates the sum of
the digits given a value.
*/
class Utilities{
  // method to calculate how many right triangles can you obtain
  // with given perimeter
  public static int right_triangles(int perimeter){
    int a;
    int b;
    int c;
    int count=0;
    for(a=1;a<perimeter/2;a++){
      for(b=a;b<perimeter/2;b++){
        c=perimeter-a-b;
        if(a*a+b*b==c*c)
        count++;
      }
    }
    return count;
  }

  // method to check whether a number is prime or not
  private static boolean isPrime(int num) {
    boolean result = true;
    for(int i=2;i<num;i++) {
      if(num%i==0)
      result= false;
    }
    if(num==1)
      result=false;
    return result;
  }

  // method to check whether the number contains only two prime factors.
  private static boolean isDoublePrime(int num){
    int div=2;
    boolean done= false;
    boolean result= false;
    while(!done){
      if(num%div==0){
        done=true;
        if(isPrime(num/div))
        result = true;
        else
        result = false;
      }
      div++;
      if(num==1)
      result=false;
    }
    return result;
  }
  // finds the next number that has 2 factors that are prime.
  public static int nextDoublePrime(int num){
    while(isDoublePrime(num)!=true)
      num++;
    return num;
  }
  //method that tells whether or not a number and it's consecutive integer are prime.
  public static int twoDoublePrimes(int num){
    while(!(isDoublePrime(num)==true&&isDoublePrime(num+1)==true))
    num++;
    return num;
  }
  // Adds digits from given value
  public static int digitSum(int num){
    int sum=0;
    int digit;
    while(num!=0){
      digit=num%10;
      sum+=digit;
      num=num/10;
    }
    return sum;
  }
  // finds the next number that has the same sum as the value.
  public static int nextDigitSumPair(int num){
    while(digitSum(num)!= digitSum(num*2)){
      num++;
    }
    return num;
  }
}
