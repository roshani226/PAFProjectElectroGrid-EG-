package com;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML 
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;

import model.Unit;

@Path("/Unit")
public class UnitService {
	Unit UnitObj = new Unit();

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String readUnit() {
		return UnitObj.readUnit();
	}
@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertUnit(@FormParam("user_account_no") String user_account_no,			
	 @FormParam("usage_date") String usage_date,
	 @FormParam("used_units") String used_units,
	 @FormParam("price_per_unit") String price_per_unit)
	 //@FormParam("total_price") String total_price)
	{
	 String output = UnitObj.insertUnit (user_account_no, usage_date, used_units, price_per_unit);
	return output;
//Delete units	}
@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteUnit(String unitData)
	{

//Convert the input string to an XML document
	 Document doc = Jsoup.parse(unitData, "", Parser.xmlParser());
//Read the value from the element <ID>
	 String unit_id = doc.select("unit_id").text();
	 String output = UnitObj.deleteUnit(unit_id);
	return output;
	}
}