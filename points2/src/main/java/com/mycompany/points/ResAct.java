/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.points;

import java.util.ArrayList;

/**
 *
 * @author q
 */
public class ResAct {
    ArrayList<MyPoint> myPoints;

    @Override
    public String toString() {
        String s = "ResAct{" + "myPoints=" ;
        if(myPoints!=null){
            for (MyPoint mp : myPoints){ 
                s += mp.toString();
            }
        }
        s+='}';
        return s;
    }
    
}
