/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jdbcexample;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author brian
 */
public class JDBCExample {
Connection con = null;
Statement stmt;

    /**
     */
    public JDBCExample()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            con = DriverManager.getConnection("jdbc:mysql:///pokemon", "root","password");
            if (!con.isClosed())          
                System.out.println("Successfully connected to MySQL server...");
            }
            catch(Exception e) {
                    System.err.println("Exception:" + e.getMessage());
                    } 
        
        
        }
    
    public DefaultTableModel getData()
    {
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("POK_ID");
        model.addColumn("POK_NAME");
        model.addColumn("SPECIES_ID");
        model.addColumn("POK_HEIGHT");
        model.addColumn("POK_WEIGHT");
        model.addColumn("POK_BASE_EXPERIENCE");
        String Que = "SELECT * FROM pokemon";
        try{
          
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(Que);
          
            while (rs.next())
            {
                String pid = rs.getString(1);
                String pname = rs.getString(2);
                String sid = rs.getString(3);
                String pokh = rs.getString(4);
                String pokw = rs.getString(5);
                String pokb = rs.getString(6);
                model.addRow(new String [] {pid, pname, sid, pokh, pokw, pokb});
     
            }
            return model;
          
        }
        catch(Exception e) 
        {
            JOptionPane.showMessageDialog(null, e);
        }
        return null;
        }
    
    public boolean addData(String aNum, String mCode, String ttaf, String ttel, String tter)
    {   
        String sql =  "INSERT INTO pokemon(AC_NUMBER, MOD_CODE, AC_TTAF, AC_TTEL, AC_TTER) "
                + "VALUES ('"+aNum+"','"+mCode+"' , '"+ttaf+"' , '"+ttel+"' , '"+tter+"')";
        try
        {
            Statement stmt = con.createStatement();
            stmt.execute(sql);
                              
        }
         catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }
        return true;
    }
    
      public boolean delete(String id)
        {
           String sql = "DELETE FROM aircraft WHERE AC_NUMBER ='"+ id+"'";
           
           try{
      
             Statement stmt = con.createStatement();
            stmt.execute(sql);
            return true;
        }
        catch (SQLException err)
        {
            System.out.println(err.getMessage());
        }
        return false;    
        } 
      
     public boolean update(String id,String aNum, String mCode, String ttaf, String ttel, String tter)
    {
      
        String sql = "UPDATE aircraft SET AC_NUMBER = '"+aNum+"', "
        + "MOD_CODE = '"+mCode+"' , AC_TTAF = '"+ttaf+"' ,AC_TTEL = '"+ttel+"', AC_TTER = '"+tter+"'";
                    
        try
        {
             Statement stmt = con.createStatement();
            stmt.execute(sql);
            return true;
        }
        catch (SQLException err)
        {
            System.out.println(err.getMessage() + "Data Not Updated");
        }
        return false;
    }
        
    }
  
