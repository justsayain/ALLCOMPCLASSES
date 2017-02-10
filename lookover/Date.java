public class Date {
// static properties
private static String[] mnames = {null,"Jan","Feb","Mar", "Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
private static int[] mdays = {0,31,28,31,30,31,30,31,31,30,31,30,31};
// nonstatic properties
private int month, day, year;
// private methods
private static boolean isLeap(int yr) {if(yr%400==0)
				return true;
			else if(yr%100==0)
				return false;
			else if(yr%4==0)
				return true;
			else return false;
	 }
private static int dpy(int yr) { 
	int totalDays=0;
	if(isLeap(yr))
		totalDays=366;
	else
		totalDays=365;
	return totalDays;
 }
private static int dpm(int m, int yr) {
	int daypm=0;
	if(isLeap(yr)&&m==1)
		daypm=31;
	if(isLeap(yr)&&m==2)
		daypm=29;
	else 
		daypm=28;
	if(isLeap(yr)&&m==3)
		daypm=31;
	if(isLeap(yr)&&m==4)
		daypm=30;
	if(isLeap(yr)&&m==5)
		daypm=31;
	if(isLeap(yr)&&m==6)
		daypm=30;
	if(isLeap(yr)&&m==7)
		daypm=31;
	if(isLeap(yr)&&m==8)
		daypm=31;
	if(isLeap(yr)&&m==9)
		daypm=30;
	if(isLeap(yr)&&m==10)
		daypm=31;
	if(isLeap(yr)&&m==11)
		daypm=30;
	if(isLeap(yr)&&m==12)
		daypm=31;
		return daypm;		
}
//private static boolean isValid(int doy, int y) { ... }
//private static boolean isValid(int m, int d, int y) { ... }
// constructors
public Date() {
	month=1;
	day=1;
	year=2013; }
//public Date(int doy, int y) { ... }
//public Date(Date d) { ... }
public Date(int m, int d, int y) {
	 if(m>=1&&m<=12)								//Check to see if month is between Jan-Dec(1-12)
		month = m;
	else
		month = 1;									//placeholder
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
	else if (d>=1 && d<=dpm(month,y)){
		day=d;
	}
	else
		day=1;
			 }
// set methods
//public void setDate(int m, int d, int y) { ... }
//public void setDate(int doy, int y) { ... }
//public void setDate(Date d) { ... }
// get methods
//public int getDay() { ... }
//public int getMonth() { ... }
//public int getYear() { ... }
public int getDoy() { 
	int total=0;
		for(int m=1;m<this.month;m++){
			total+=mdays[m];
			if(m==2&&isLeap(this.year))
				total++;
			}
			total+=this.day;
			return total;
}
// formatter
public String toString() { 
	return ""+month+"/"+day+"/"+year;
}
// comparisons
//public int compareTo(Date d) { ... }
//public boolean equals(Date d) { ... }
// arithmetic
//public static Date addDays(Date cd, int d) { ... }
//public void addDays(int d) { ... }
//public static Date toDate(int doy, int yr) { ... }
//public static int diff(Date d1, Date d2) { ... }
//public int diff(Date d) { ... }
}
class Driver {
public static void main(String[] args) {
Date d;
// addDays tests
d = new Date(1,1,2006);
System.out.println(d);
}
}