/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.points;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author q
 */
public class MainServer {
     Model m = BuildModel.build();

    int port = 1234;
    InetAddress ip = null;

    ExecutorService service = Executors.newCachedThreadPool();
    
    ArrayList<ClientAtServer> allclients = new ArrayList<>(); 
    
    
    void broadcast(){
        for(ClientAtServer cas: allclients){
            cas.sendAllPoints();
        }
    }
    
    public void ServerStart(){
        ServerSocket ss;

        try{
            DB_jdbc db = new DB_jdbc();
            m.init(db); 
            
            
//            m.addPoints(new MyPoint(250,250));
//            m.addPoints(new MyPoint(150,50));
            
            m.addObserver(()->{broadcast();});
            
            ip = InetAddress.getLocalHost();
            ss = new ServerSocket(port,0,ip);
            System.out.append("Server started\n");

            while(true) {
                Socket cs;
                cs = ss.accept();
                System.out.append("Client connected\n");
                ClientAtServer cas = new ClientAtServer(cs,this);
                allclients.add(cas);
                service.submit(cas);
            }



        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public static void main(String[] args){
        new MainServer().ServerStart();


    }
}
