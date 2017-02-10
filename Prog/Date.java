public class Date{
		private int month;									//non static: not shared; multiple copies
		private int day;									//
		private int year;									//
		public int [] dpm ={0,31,28,31,30,31,30,31,31,30,31,30,31};
		
		public Date(int m, int d, int y){
			
			if(m>=1&&m<=12)								//Check to see if month is between Jan-Dec(1-12)
			month = m;
			else
			month = 1;										//placeholder
			if(d>=1&&d<=31)								//Check to see if the day is between 1-31
				day=d;
			else 
				day=1;
			year=y;
			
			if(month==2 && isLeap(y) && d<=29){
				day = d;
			}
			else if (month ==2 && !isLeap(y) && d>=1 && d<=28){
				day= d;
			}
			else if (d>=1 && d<=dpm[month]){
				day=d;
			}
			else
			day=1;
		}
		public Date(){
			month=1;
			day=1;
			year=2013;
		}
		public static boolean isLeap(int y){
			if(y%400==0)
				return true;
			else if(y%100==0)
				return false;
			else if(y%4==0)
				return true;
			else return false;
		}
		
		public int getDoy(){
			int total=0;
			for(int m=1;m<this.month;m++){
				total+=mdays[m];
				if(m=2&&isLeap(this.year)
					total++;
			}
			total+=this.day;
		}
		
		public String toString(){
			/*String s=" ";
			if(month<10)
				s+="0";
			s +=month +"/";
			if(day<10)
				s+="0";
			s+=year;
			return s;
			*/
			return ""+month+"/"+day+"/"+year;
		}
}
class DateDriver{
	public static void main(String[]args){
		System.out.println("Date Driver/Tester");
		Date x=new Date (3,21,2013);
		System.out.println(x.toString());
		
		for(int y=1890; y<=1910; y++){
			System.out.println(y+ ": "+Date.isLeap(y));
		}
		/*Date x = new Date();								//declare reference variable
		x.month=4;
		x.day=1;
		x.year=2013;
		Date d= new Date(4,1,2013);
		Date e= new Date();
		Date f= new Date(9,1,1987);
		System.out.print(d);
		*/
	}
}