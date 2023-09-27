/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.points;

/**
 *
 * @author q
 */
public class Messages {
    PointActions action;
    
    MyPoint my_point;
    
    public Messages(PointActions action){
        this.action = action;
    }

    public Messages(MyPoint my_point) {
        this.my_point = my_point;
    }

    public Messages(PointActions action, MyPoint my_point) {
        this.action = action;
        this.my_point = my_point;
    }

    
    
    @Override
    public String toString() {
        return "Messages{" + "action=" + action + '}';
    }
    
    
    
}
