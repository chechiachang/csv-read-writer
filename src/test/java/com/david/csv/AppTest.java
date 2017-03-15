package com.david.csv;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest{
	App app = new App();
	
	@Test
	public void testWriteList1() throws ClassNotFoundException, SQLException{
		List<Entity> entities = new ArrayList();
		int id = App.writeList(entities);
		assertEquals(0, id);
	}
	
	@Test
	public void testParseLine1() throws NumberFormatException, ParseException {
		Entity entity = App.parseLine("1,a,2017-01-01,10001");
		assertNotNull(entity);
		assertEquals(1, entity.getId());
		assertEquals("a", entity.getName());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		assertEquals(sdf.parse("2017-01-01"), entity.getDate());
		assertEquals(10001l, entity.getUuid());
	}
	
}
