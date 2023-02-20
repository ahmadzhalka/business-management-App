package com.example.businessmanagement;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.util.Log;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class DataManneger {
    static String amdminid=null;
    static Worker worker=new Worker();
    static ArrayList<Shifts> sh=new ArrayList<>();
    static HashMap<Integer, Worker> workers = new HashMap<>();
    static  ArrayList<Integer> id=new ArrayList<>();
    static int currentMonth;
    static Shifts shif=new Shifts();

    public static Shifts getShif() {
        return shif;
    }

    public static void setShif(Shifts shif) {
        DataManneger.shif = shif;
    }

    public static ArrayList<String> getOrders() {
        return orders;
    }

    public static void setOrders(ArrayList<String> orders) {
        DataManneger.orders = orders;
    }

    static  ArrayList<String> orders=new ArrayList<>();

    public static ArrayList<Integer> getId() {
        return id;
    }

    public static void setId(ArrayList<Integer> id) {
        DataManneger.id = id;
        for (int i = 0; i <DataManneger.id .size() ; i++) {
            Log.w("pttt", ""+i);

        }
    }

    public static void setWorkers(HashMap<Integer, Worker> workers) {
        DataManneger.workers = workers;
    }

    public static ArrayList<Shifts> getSh() {
        return sh;
    }

    public static void setSh(ArrayList<Shifts> sh) {
        DataManneger.sh = sh;
    }
    public static void setSh1(Shifts shifts) {
        DataManneger.sh.add(shifts);
    }

    public static Worker getWorker() {
        return worker;
    }

    public static void setWorker(Worker worker) {
        DataManneger.worker = worker;
    }


    public static HashMap<Integer, Worker> getWorkers() {
        return workers;
    }


    public static void addWorker(Worker w){
        workers.put(w.getId(),w);
    }
    public static Boolean chekisin(Worker w){
        if (workers.containsKey(w.getId())){
            return true;
        }
        return false;
    }
    @NonNull
    public static Boolean chekisid(String s){
        int i= Integer.parseInt(s);
        if (workers.containsKey(i)){
            return true;
        }
        return false;
    }

    public static void setAmdminid(String amdminid) {
        DataManneger.amdminid = amdminid;
    }


    public static void setCurrentMonth(int currentMonth) {
        DataManneger.currentMonth = currentMonth;
    }

    public static String getAmdminid() {
        return amdminid;
    }
    public static ArrayList<Shifts>  getShiftsBYM(){
        ArrayList<Shifts> shift=new ArrayList<>();

        for (int i = 0; i <getSh().size() ; i++) {

            if(getSh().get(i).getMonth()==currentMonth)
                shift.add(getSh().get(i));
        }
        return shift;
    }
}
