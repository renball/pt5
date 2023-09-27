/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.points;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author q
 */
public class DB_jdbc {
    Connection c;
    void connect()
    {
        try {
           Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\q\\Documents\\NetBeansProjects\\points2\\BD_p.db");
            System.out.println("Opened database successfully");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public DB_jdbc() {
        connect();
    }
    
    ArrayList<MyPoint> getAllPoint(){
        ArrayList<MyPoint> res = new ArrayList<>();
        try{
            Statement st = c.createStatement();
            ResultSet r= st.executeQuery("SELECT * FROM bd_points");
            while(r.next())
            {
                System.out.println(r.getInt("X")+" "+ r.getInt("Y"));
                res.add(new MyPoint(r.getInt("X"), r.getInt("Y"), (int)r.getInt("ID")));
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return res;
    }
    
    public void addPointToBD (MyPoint mp){
        try {
            PreparedStatement pst = c.prepareStatement("INSERT OR IGNORE INTO bd_points(X, Y) VALUES (?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, mp.getX());
            pst.setInt(2, mp.getY());
            pst.executeUpdate();
            
            try (ResultSet generatedKeys = pst.getGeneratedKeys()){
                System.out.println("Key:"+generatedKeys.getLong(1));
                mp.setId((int)generatedKeys.getLong(1));
        }
            
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void movePointToBD (MyPoint mp){
        try {
            PreparedStatement pst = c.prepareStatement("UPDATE bd_points SET X=?, Y=? WHERE ID=?",
                    Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, mp.getX());
            pst.setInt(2, mp.getY());
            pst.setInt(3, mp.getId());
            pst.executeUpdate();
                        
        } catch (SQLException ex) {
            Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
