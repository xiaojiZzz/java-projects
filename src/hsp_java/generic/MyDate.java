package hsp_java.generic;

@SuppressWarnings({"all"})
public class MyDate implements Comparable<MyDate> {
    private int year;
    private int month;
    private int day;

    public MyDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "MyDate{" +
                "year=" + year +
                ", month=" + month +
                ", day=" + day +
                '}';
    }

    @Override
    public int compareTo(MyDate o) { //比较 year month day

        int yearMinus = this.year - o.getYear();
        if (yearMinus != 0) {
            return yearMinus;
        }
        // year 相同，比较 month
        int monthMinus = this.month - o.getMonth();
        if (monthMinus != 0) {
            return monthMinus;
        }
        // month 相同，比较day
        int dayMinus = this.day - o.getDay();
        return dayMinus;
    }
}
