/* Programmer: Steve Delgado
Comp 108 Tues/Thur 8-9:15
*/
public class Project8{
public static void main(String[] args){
System.out.println("Miles   Kilometers");
int miles=1;
while(miles<=10){
double kilometers = miles*1.609;
System.out.printf("%-5d %8.3f \n",miles,kilometers);
miles++;
}
}}
