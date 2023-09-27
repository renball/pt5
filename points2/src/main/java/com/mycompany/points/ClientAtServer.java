/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.points;

import com.google.gson.Gson;
import java.io.*;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author q
 */

public class ClientAtServer implements Runnable{
    Socket cs;
    MainServer ms;

    InputStream is;
    OutputStream os;
    DataInputStream dis;
    DataOutputStream dos;
    
    Gson gson = new Gson();
    
    Model m = BuildModel.build();
    
    public ClientAtServer(Socket cs) {
        this.cs = cs;
    }

    public ClientAtServer(Socket cs, MainServer ms) {
        this.cs = cs;
        this.ms = ms;

        try {
            os = cs.getOutputStream();
            dos = new DataOutputStream(os);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public void run() {
        try {
            is = cs.getInputStream();
            dis = new DataInputStream(is);
            while (true) {
                String s = dis.readUTF();
                System.out.println("Conncet: " + s);
                
                Messages msg = gson.fromJson(s,Messages.class);
                
                if(msg.action == PointActions.GETALL){
                    ms.broadcast();
                }
                
                if(msg.action == PointActions.ADD){
                    m.addPoints(msg.my_point);
                    //ms.broadcast();
                }
                if(msg.action == PointActions.MOVE){
                    m.move(msg.my_point);
                  //  ms.broadcast();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    
    void sendAllPoints(){
        try {
        ResAct ra = new ResAct();
        ra.myPoints = m.getMyPoints();
        String s = gson.toJson(ra);
        dos.writeUTF(s);
        } catch (IOException ex) {
            Logger.getLogger(ClientAtServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
