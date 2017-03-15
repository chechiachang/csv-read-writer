package com.david.csv;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class App {
	private static final String csvFile = "C:\\Users\\davidchang\\workspace\\project\\csv\\csv.csv";
	private static final String ojdbcUrl = "jdbc:oracle:thin:@localhost:1521:xe";
	private static final String ojdbcUser = "system";
	private static final String ojdbcPassword = "12345678";
	
	public static void main(String[] args) {		
		try{
			List<Entity> entities = readCsvFile(csvFile);
			int lastId = writeList(entities);
			System.out.println("Updated. ID: " + lastId);
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public static int writeList(List<Entity> entities) throws ClassNotFoundException, SQLException {
		Connection connection = getConnection();
		int id = 0;
		for(Entity entity : entities){
			PreparedStatement ps = connection.prepareStatement("INSERT INTO entities VALUES(?, ?, ?, ?)");
			ps.setInt(1, entity.getId());
			ps.setString(2, entity.getName());
			ps.setTimestamp(3, new Timestamp(entity.getDate().getTime()));
			ps.setLong(4, entity.getUuid());
			id = ps.executeUpdate();
		}
		return id;
	}

	public static List<Entity> readCsvFile(String file) throws IOException, NumberFormatException, ParseException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String line = "";
		List<Entity> entities = new ArrayList<Entity>();
		
		br.readLine();
		while((line = br.readLine()) != null){
			Entity entity = parseLine(line);
			entities.add(entity);
		}
		return entities;
	}

	public static Entity parseLine(String line) throws NumberFormatException, ParseException {
		String[] values = line.split(",");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		Entity entity = new Entity(
				Integer.valueOf(values[0]),
				values[1],
				sdf.parse(values[2]), 
				Long.valueOf(values[3]));
		return entity;
	}

	private static Connection getConnection() throws ClassNotFoundException, SQLException{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = DriverManager.getConnection(ojdbcUrl, ojdbcUser, ojdbcPassword);
		return connection;
	}
}
