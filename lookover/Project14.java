public class Project14{
public static void main(String[]args){
System.out.println("8! is: "+ MyMath.factorial(8));
System.out.println("The first 10 prime fibonacci numbers are: ");

for (int b=2; b<=30;b++){
int c= MyMath.fibonacci(b);
if(MyMath.isPrime(c))
System.out.println("fibonacci("+b+")"+MyMath.fibonacci(b));
}


System.out.println("The value of pi is: "+MyMath.pi(100000));
System.out.println("The square root of 16 is: "+MyMath.sqrt(16));
System.out.println("125 in Binary is: "+MyMath.conversion(125,2));
System.out.println("125 in Octal is: "+MyMath.conversion(125,8));
System.out.println("125 in Hexadecimal is: "+MyMath.conversion(125,16));
}}