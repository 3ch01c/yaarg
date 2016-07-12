package edu.nmt.cs.itweb;

import java.sql.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

class Stats{
    
    public static void Update(String strChange, String magChange, String dexChange, String luckChange, String stat_points, String charClass, String charId) throws SQLException{

		//Connection con = DBConnection.getConnection();
                //Statement stmt = con.createStatement();

                //ResultSet classInfo = stmt.executeQuery("select * from `class` where `class`='" + charClass +
                  //                                      "'");
                //ResultSet charInfo = stmt.executeQuery("select * from `character` where `id`='" + charId +
                 //                                       "'");
                //classInfo.next();
                //charInfo.next();
                
                //int newHp = Integer.parseInt(charInfo.getString("hp")) + (strChange * Integer.parseInt(classInfo.getString("hp_per_str")));

                //stmt.executeUpdate("update `character` set `stat_points`='" + stat_points + "' where `id`='" + charId + "';");

    }

}