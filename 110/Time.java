// class definition to represent
// military time
// int hour: 0-23
// int minute: 0-59

public class Time{
	private int hour;
	private int minute;

	//reset any time to 0:0
	public Time(){
		hour = 0;
		minute = 0;
	}

	public Time(int h, int m){
		if(h>=0 && h<24)
			hour=h;
		else
			hour =0;

		if(m>=0 && m<60)
			minute=m;
		else
			minute =0;
	}
	
	public String toString(){
		return hour + ":" + minute;
	}
}
