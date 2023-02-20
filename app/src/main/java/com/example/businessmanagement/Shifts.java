package com.example.businessmanagement;

public class Shifts {
    private int year;
    private int month;
    private String start;
    private String end;
    private String loc;
    private String date;
    private int count;
    private double shiftTime;

    public double getShiftTime() {
        return shiftTime;
    }

    public Shifts setShiftTime(double shiftTime) {
        this.shiftTime = shiftTime;
        return this;
    }

    public Shifts setCount(int count) {
        this.count = count;
        return this;
    }

    public int getCount() {
        return count;
    }

    public Shifts(int year, int month, String start, String end, String loc, String date,int count,double shiftTime) {
        this.year = year;
        this.month = month;
        this.start = start;
        this.end = end;
        this.loc = loc;
        this.date = date;
        this.count=count;
        this.shiftTime=shiftTime;
    }

    public Shifts() {
    }

    public int getYear() {
        return year;
    }

    public Shifts setYear(int year) {
        this.year = year;
        return this;
    }

    public int getMonth() {
        return month;
    }

    public Shifts setMonth(int month) {
        this.month = month;
        return this;
    }

    public String getStart() {
        return start;
    }

    public Shifts setStart(String start) {
        this.start = start;
        return this;
    }

    public String getEnd() {
        return end;
    }

    public Shifts setEnd(String end) {
        this.end = end;
        return this;
    }

    public String getLoc() {
        return loc;
    }

    public Shifts setLoc(String loc) {
        this.loc = loc;
        return this;
    }

    public String getDate() {
        return date;
    }

    public Shifts setDate(String date) {
        this.date = date;
        return this;
    }
}
