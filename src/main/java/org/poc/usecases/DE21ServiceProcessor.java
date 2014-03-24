package org.poc.usecases;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;

import javax.sql.DataSource;

import org.apache.camel.Exchange;
import org.apache.camel.ExchangePattern;
import org.apache.camel.Processor;

public class DE21ServiceProcessor implements Processor{
	
	DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		
		System.out.println(">>>>Inside HelloServiceProcessor<<<<<<<<");
		 Connection con = dataSource.getConnection();
	        Statement stmt = null;
	        DatabaseMetaData dbMeta = con.getMetaData();
	        System.out.println("Using datasource " + dbMeta.getDatabaseProductName() + ", URL " + dbMeta.getURL());
	        try {
	            stmt = con.createStatement();
	           
	            ResultSet rs = stmt.executeQuery("select * from message");
	            ResultSetMetaData meta = rs.getMetaData();
	            while (rs.next()) {
	                for (int c = 1; c <= meta.getColumnCount(); c++) {
	                    System.out.print(rs.getString(c) + ", ");
	                    exchange.getIn().setBody((String)rs.getString(c));
	                }
	                System.out.println();
	            }
	        } catch (Exception e) {
	            System.out.println(e.getMessage());
	            throw e;
	        } finally {
	            if (stmt != null) {
	                stmt.close();
	            }
	            if (con != null) {
	                con.close();
	            }
	        }
	       exchange.setPattern(ExchangePattern.InOnly);
	   // exchange.setOut(exchange.getIn());
		
	}

}
