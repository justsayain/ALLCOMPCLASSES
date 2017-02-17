/* Programmer: Steve Delgado
Comp 108 Tues/Thur 8-9:15
*/

public class Project9{
  public static void main(String[]args){
    int list=1;
    int even=0,odd=0;;
    while(list<=10){
      int x = (int)(Math.random()*91+10);
      System.out.print(x+ "\n");
      if(x%2==0)
      even++;
      else
      odd++;
      list++;
    }
    System.out.println("\n"+even+" are even");
    System.out.println(odd+" are odd");
  }
}
