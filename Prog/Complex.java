import java.util.*;
   class Complex{
      private double real;
      private double imag;
      public Complex() { 
			this.real=0.0;
			this.imag=0.0;
		}
		
      public Complex(double re, double im) { 
			this.real=re;
			this.imag=im;
		}
      public Complex plus(Complex c) { 
			Complex result = new Complex();
			result.real= this.real+c.real;
			result.imag= this.imag+c.imag;
         return result;
			
		//return new Complex(this.real+c.real, this.imag+c.imag);
		}
      public Complex minus(Complex c) { 
			Complex result = new Complex();
			result.real= this.real-c.real;
			result.imag= this.imag-c.imag;
         return result;
      }
      public Complex times(Complex c) { 
         Complex result = new Complex();
			result.real= this.real*c.real - this.imag*c.imag;
			result.imag= this.real*c.imag + this.imag*c.real;
         return result;
		}
      public Complex divide(Complex c) { 
         Complex result = new Complex();
			result.real= this.real/c.real;
			result.imag= this.imag/c.imag;
         return result;
		}
   
   
   
   
   
      public String toString() { 
         String s= "";
         s=s+this.real+" + "+this.imag+"i";					//real #(3,5) ---- 3+5i
         return s;
      }
	}
class ComplexDriver{
		public static void main(String[]args){
			Complex x= new Complex();						//default constructor
			Complex c= new Complex(3,5);
			Complex d= new Complex(4,4);
			Complex e= c.plus(d);
			Complex f= c.minus(d);
			Complex g= c.times(d);
			Complex h= c.divide(d);
			System.out.println(x.toString());
			System.out.println(e.toString());
			System.out.println(f.toString());
			System.out.println(g.toString());
			System.out.println(h.toString());
		}
   }
