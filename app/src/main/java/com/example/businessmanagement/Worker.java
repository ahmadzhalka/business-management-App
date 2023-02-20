package com.example.businessmanagement;

import java.util.ArrayList;

public class Worker {
    public  ArrayList<Shifts> shift;
    private String name;
    private String title;
    private int id;
    private int salary;
    public Worker() {
        shift=new ArrayList<>();
    }


    public Worker(ArrayList<Shifts> shift, String name, String title, int id, int salary) {
        this.shift = shift;
        this.name = name;
        this.title = title;
        this.id = id;
        this.salary = salary;
    }



    public  ArrayList<Shifts> getShift() {
        return shift;
    }

    public int getId() {
        return id;
    }

    public Worker setId(int id) {
        this.id = id;
        return this;
    }

    public int getSalary() {
        return salary;
    }

    public Worker setSalary(int salary) {
        this.salary = salary;
        return this;
    }

    public String getName() {
        return name;
    }

    public Worker setName(String name) {
        this.name = name;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Worker setTitle(String title) {
        this.title = title;
        return this;
    }
    public  void setShift(Shifts p){
        shift.add(p);
    }
    public  ArrayList<Shifts> getShifts(){
        return shift;
    }
    public  void setShiftarr(ArrayList<Shifts> p){
        shift=p;
    }



}
