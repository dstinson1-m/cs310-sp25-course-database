package edu.jsu.mcis.cs310.coursedb.dao;

import java.sql.*;
import com.github.cliftonlabs.json_simple.*;
import java.util.ArrayList;

public class DAOUtility {
    
    public static final int TERMID_FA24 = 1;
    
    public static String getResultSetAsJson(ResultSet rs) {
        
        ArrayList<JsonObject> rows = new ArrayList<>();
        
        JsonArray records = new JsonArray();
        
        try {
        
            if (rs != null) {

                // INSERT YOUR CODE HERE
                
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                
                while (rs.next()) {
                    JsonObject row = new JsonObject();
                    
                    for (int i =1; i <= columnCount; i++) {
                        String columnLabel = metaData.getColumnLabel(i);
                        Object value = rs.getObject(i);
                        
                        row.put(columnLabel, value);
                    }
                    
                    records.add(row);
                }
                
                records.addAll(rows);

            }
            
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        
        return Jsoner.serialize(records);
        
    }
    
}
