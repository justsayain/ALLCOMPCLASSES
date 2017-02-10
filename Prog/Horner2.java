/* Programmer: Steve Delgado
Comp 110/110L Spring 2013 Mon/Wed 8-11am
Project 3: Part 3
Polygon Evaluator Version 3 */

   import java.util.*;

   public class Horner2{
      public static double evalpoly(double [] c, double x){
      	int degree = c.length+1;
					
      }
      public static void main(String[]args){
         Scanner input = new Scanner(System.in);
         double [] c;
         double y=0,startx,stopx,step;
         int degree;
         System.out.print("Enter Degree of Polynomial: ");
         degree= input.nextInt();
         c= new double[degree+1];
         for (int i=0;i<c.length;i++){
            System.out.print("Please Enter Coefficient "+i+": "); 
            c[i] = input.nextDouble();
         }
			// create start x, stop x, step;
			//start x 
			//stop x
			// step
			//for(double x= startx; x<=stopx;x+=step){
			//double y = evalpoly(c,x)
			//print (x+","+y);
			//}
         System.out.print("Enter an x you want to begin with: ");
         startx = input.nextDouble();
			System.out.print("Enter an x where you want to stop: ");
      	stopx = input.nextDouble();
			System.out.print("top: ");
      	stopx = input.nextDouble();
			
         double power =x;
         y =c[0];
         for(int i=1;i<c.length;i++){
         
            y += c[i]*power;
            power *=x;
         }
         System.out.println("Value: "+y);
      }
   }