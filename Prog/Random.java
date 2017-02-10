/* Programmer: Steve Delgado
Comp 108 Tues/Thur 8-9:15
*/

   public class Random{
      public static void main(String[]args){
         int list=1,even=0,odd=0;
			System.out.println("Even    Odd");
			while(list<=10){
			int x=(int)(Math.random()*91+10);
			if(x%2==0){
			System.out.printf("%-2d\n",x);
			even++;
			}
			else{
			System.out.printf("%10d\n",x);
			odd++;
			}
			list++;
			}
			System.out.println("\n"+even+" are even");
			System.out.println(odd+" are odd");
			}}
