package entities;

public class Date {
	// A simple substitute for the deprecated Date class, that does only what I need it to do.
	public static final int JANUARY	=1, FEBRUARY	=2,	MARCH		=3;
	public static final int APRIL	=4,	MAY			=5,	JUNE		=6;
	public static final int JULY	=7,	AUGUST		=8,	SEPTEMBER	=9;
	public static final int OCTOBER	=10,NOVEMBER	=11,DECEMBER	=12;
	
	private String date = null;
	private int year, month, day;

	public Date(int year, int month, int day)
	{
		this.year = year;
		if(month>12 || month < 1)
			System.out.println("INVALID MONTH");
		else
			this.month =month;
		if(day >=1)
			if(day <= 28)
				this.day = day;
			else if(hasThirtyDays())
				this.day=day;
			else if(hasThirtyOneDays())
				this.day=day;
		date=toString();
	}
	boolean hasThirtyOneDays() {
		switch(month)
		{
			case JANUARY:
			case MARCH:
			case MAY:
			case JULY:
			case AUGUST:
			case OCTOBER:
			case DECEMBER:
				return true;
			default: return false;
		}
	}
	
	boolean hasThirtyDays() 
	{
		switch(month)
		{
			case APRIL:
			case JUNE:
			case SEPTEMBER:
			case NOVEMBER:
				return true;
			default: return false;
		}
	}
	public String toString()
	{
		date = year+"-"+month+"-"+day;
		return date;
	}
	public static Date stringToDate(String s)
	{
		String ss[] = s.split("-");
		return new Date(Integer.parseInt(ss[0]),Integer.parseInt(ss[1]),Integer.parseInt(ss[2]));
	}
}
