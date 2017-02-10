/* Programmer: Steve Delgado
Comp 110/110L Spring 2013 Mon/Wed 8-11am
Project 5
 */
   import java.util.*;
   public class PolyAreaOne{
      public static double evalpoly(double[] c, double x){
         int degree = c.length+1;
         double y=c[0];
         double power =x;
         for(int i=1;i<c.length;i++){
         
            y += c[i]*power;
            power*=x;
         }
         return y;
      }
   
      public static double evalarea(double []c, double a, double b, int nsteps){
         double area=0;
         double s=(b-a)/nsteps;
         for(double i=a;i<b;i+=s){
            double y= evalpoly(c,i);
            area += y*s;  
         }		
         return area;		
      }
   	
      public static double [] createintegral(double[] c){
         int n=c.length;
         int m=n+1;
         double []d = new double [m];
         int i = c.length-1;
         while(i>=0){
            d[i+1]=c[i]/(i+1);
            i--;		
         }
         d[0]=0;
         return d;
      	
      }
   
      public static void main(String[]args){
         Scanner input = new Scanner(System.in);
         double [] c;s
         double a,b;
         int nsteps;
         int n;
         System.out.print("Enter Degree of Polynomial: ");
         n= input.nextInt();
         c= new double[n+1];
         
         for (int i=c.length-1;i>=0;i--){
            System.out.print("Please Enter Coefficient "+i+": "); 
            c[i] = input.nextDouble();
         }
      	
      	
         
         System.out.print("Enter an x you want to begin with: ");
         a = input.nextDouble();
         System.out.print("Enter an x where you want to stop: ");
         b = input.nextDouble();
         System.out.print("Enter step(how many times you want to move horizontally): ");
         nsteps = input.nextInt();
         double s = (b-a)/nsteps;   
         for(double x= a; x<=b;x+=s){
            double y = evalpoly(c,x);
            System.out.println(x+" ,"+y);
         }
         double []d=createintegral(c);
         String f="f(x) = ";
         for(int i=d.length-1;i>=0;i--){
            if (i==0)
               f+=d[i];
            else
               f+=d[i] + "x^"+i+" + ";
         }
         System.out.println(f);
         double area= evalarea(c,a,b,nsteps);		
         System.out.println("area under the curve="+area);
         System.out.println(evalpoly(d,b)-evalpoly(d,a));
      }
   }