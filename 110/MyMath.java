/* Programmer: Steve Delgado
Comp 108 Tues/Thur 8-9:15
Project 14 Part 1
*/
public class MyMath{
  public static double power(double x, double y){
    double z=1;
    for(int i=1; i<=y; i++){
      z *=x;
    }
    return z;
  }
  public static int factorial(int n){
    int a=1;
    for(int i=1;i<=n;i++){
      a*=i;
    }
    return a;
  }

  public static int fibonacci(int n){
    int num1=0, num2=1;
    for(int i=0; i<n; i++) {
      int newNum = num1;
      num1 = num2;
      num2 = newNum + num2;
    }
    return num1;
  }

  public static double pi(int n){
    double sum=1;
    for (int i = 1; i < n; i++) {
      sum += (1.0/(2*i+1))*Math.pow(-1,i);
    }
    return sum*4;
  }


  public static double sqrt(double num){
    double lastGuess = 1;
    double nextGuess = 1;
    for( int i = 1; i <= num; i++)
    {
      lastGuess=nextGuess;
      nextGuess = 0.5 *(lastGuess+(num / lastGuess));
      if(Math.abs(nextGuess-lastGuess)<0.001)
      break;
    }
    return nextGuess;
  }
  public static String conversion(int num,int base){
    String result= "";
    while(num!=0){
      int remainder = num%base;
      num/=base;
      if(remainder>=0&&remainder<=9)
      result=(char)(remainder + '0')+result;
      else
      result= (char)(remainder -10+'A')+result;
    }
    return result;
  }
  public static boolean isPrime(int number) {
    for (int i = 2; i<= number / 2; i++) {
      if (number % i == 0) {
        return false;
      }
    }
    return true;
  }}
