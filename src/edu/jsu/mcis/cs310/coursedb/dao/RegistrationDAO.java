package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class RegistrationDAO {
    
    private final DAOFactory daoFactory;
    
    RegistrationDAO(DAOFactory daoFactory) {
        this.daoFactory = daoFactory;
    }
    
    public boolean create(int studentid, int termid, int crn) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        Statement stmt = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                // INSERT YOUR CODE HERE
                
                String query = "INSERT INTO registration (studentid, termid, crn) VALUES (?, ?, ?)";
                ps = conn.prepareStatement(query);
                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                ps.setInt(3, crn);
                
                int rowsAffected = ps.executeUpdate();
                result = (rowsAffected > 0);
                
                stmt = conn.createStatement();
                String countQuery = "SELECT COUNT(*) AS total FROM registration";
                rs = stmt.executeQuery(countQuery);
                if (rs.next()) {
                    System.out.println("Total registrations after insertion:" + rs.getInt("total"));
                }
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (stmt != null) { try {stmt.close(); } catch (Exception e) { e.printStackTrace(); } }
        }
        
        return result;
        
    }

    public boolean delete(int studentid, int termid, int crn) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        Statement stmt = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                // INSERT YOUR CODE HERE
                
                String query = "DELETE FROM registration WHERE studentid = ? AND termid = ? AND crn = ?";
                ps = conn.prepareStatement(query);
                ps.setInt(1, studentid);
                ps.setInt(2,termid);
                ps.setInt(3, crn);
                
                int rowsAffected = ps.executeUpdate();
                result = (rowsAffected > 0);
                
                stmt = conn.createStatement();
                String countQuery = "SELECT COUNT(*) AS total FROM registration";
                ResultSet rs = stmt.executeQuery(countQuery);
                if (rs.next()) {
                    System.out.println("Total registrations after deletion:" + rs.getInt("total"));
                }
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {

            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (stmt != null) { try {stmt.close(); } catch (Exception e) { e.printStackTrace(); } }
        }
        
        return result;
        
    }
    
    public boolean delete(int studentid, int termid) {
        
        boolean result = false;
        
        PreparedStatement ps = null;
        Statement stmt = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                // INSERT YOUR CODE HERE
                
                String query = "DELETE FROM registration WHERE studentid = ? AND termid = ?";
                ps = conn.prepareStatement(query);
                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                
                int rowsAffected = ps.executeUpdate();
                result = (rowsAffected > 0);
                
                stmt = conn.createStatement();
                String countQuery = "SELECT COUNT(*) AS total FROM registration";
                ResultSet rs = stmt.executeQuery(countQuery);
                if (rs.next()) {
                    System.out.println("Total registrations after withdrawal:" + rs.getInt("total"));
                }
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {

            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (stmt != null) { try {stmt.close(); } catch (Exception e) { e.printStackTrace(); } }
        }
        
        return result;
        
    }

    public String list(int studentid, int termid) {
        
        String result = null;
        
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSetMetaData rsmd = null;
        Statement stmt = null;
        
        try {
            
            Connection conn = daoFactory.getConnection();
            
            if (conn.isValid(0)) {
                
                // INSERT YOUR CODE HERE
                
                String query = "SELECT * FROM registration WHERE studentid = ? AND termid = ? ORDER BY crn";
                ps = conn.prepareStatement(query);
                ps.setInt(1, studentid);
                ps.setInt(2, termid);
                
                rs = ps.executeQuery();
                
                rsmd = rs.getMetaData();
                
                if (rsmd != null) {
                    int columnCount = rsmd.getColumnCount();
                    System.out.println("Number of columns: " + columnCount);
                
                    for (int i = 1; i <= columnCount; i++) {
                        String columnName = rsmd.getColumnName(i);
                        int columnType = rsmd.getColumnType(i);
                        System.out.println("Column " + i + ": " + columnName + " (Type: " + columnType + ")");
                    }
                } else {
                        System.out.println("ResultSetMetaData is null. No metadata available.");
                        }
                
                result = DAOUtility.getResultSetAsJson(rs);
                
                stmt = conn.createStatement();
                String countQuery = "SELECT COUNT(*) AS total FROM registration WHERE studentid = ? AND termid = ?";
                PreparedStatement countPs = conn.prepareStatement(countQuery);
                countPs. setInt(1, studentid);
                countPs.setInt(2,termid);
                
                ResultSet countRs = countPs.executeQuery();
                if (countRs.next()) {
                    System.out.println("Total registrations:" + countRs.getInt("total"));
                }
                
                countRs.close();
                countPs.close();
                stmt.close();
                
            }
            
        }
        
        catch (Exception e) { e.printStackTrace(); }
        
        finally {
            
            if (rs != null) { try { rs.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (ps != null) { try { ps.close(); } catch (Exception e) { e.printStackTrace(); } }
            if (stmt != null) { try {stmt.close(); } catch (Exception e) { e.printStackTrace(); } }
        }
        
        return result;
        
    }
    
}
