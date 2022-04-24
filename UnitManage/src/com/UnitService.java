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