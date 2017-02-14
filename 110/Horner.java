import java.util.*;

   public class Horner{
      public static double createintegral(double [] c, double x){
         int degree = c.length+1;
         double y=c[0];
         double power =x;
         for(int i=1;i<c.length;i++){
            y += c[i]*power;
            power*=x;
         }
         return y;

      }
      public static void main(String[]args){
         Scanner input = new Scanner(System.in);
         double [] c;
         double startx,stopx,step;
         int degree;
         System.out.print("Enter Degree of Polynomial: ");
         degree= input.nextInt();
         c= new double[degree+1];
         for (int i=0;i<c.length;i++){
            System.out.print("Please Enter Coefficient "+i+": ");
            c[i] = input.nextDouble();
         }

         System.out.print("Enter an x you want to begin with: ");
         startx = input.nextDouble();
         System.out.print("Enter an x where you want to stop: ");
         stopx = input.nextDouble();
         System.out.print("Enter step(how many times you want to move horizontally): ");
         step = input.nextDouble();
         step = (stopx-startx)/step;
         for(double x= startx; x<=stopx;x+=step){
            double y = createintegral(c,x);
            System.out.println(x+" ,"+y);
         }

      }
   }
