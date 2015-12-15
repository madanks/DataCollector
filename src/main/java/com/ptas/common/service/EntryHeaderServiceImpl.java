package com.ptas.common.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialBlob;

import org.apache.commons.lang.ObjectUtils.Null;
import org.hibernate.Hibernate;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ptas.common.dao.EntryHeaderDAO;
import com.ptas.common.domain.EntryDetails;
import com.ptas.common.domain.EntryHeader;
import com.ptas.common.domain.UserInfo;
import com.ptas.common.util.SecurityUtil;

@Service
@Transactional
public class EntryHeaderServiceImpl implements EntryHeaderService {

	@Autowired
	private EntryHeaderDAO entryHeaderDAO;

	@Autowired
	private HttpServletRequest servletRequest;
	
	@Autowired
	UserInfoService userinfo;

	public EntryHeader getEntryHeader(Long entryheaderId) {
		return entryHeaderDAO.findOne(entryheaderId);
	}

	public EntryHeader findOne(Long id) {
		return entryHeaderDAO.findOne(id);
	}

	public String findOneID(Long id) {

		String json = null;
		try {
			EntryJson ej = new EntryJson();
			EntryHeader eh = new EntryHeader();

			ArrayList<EntryDetails> listed = new ArrayList<EntryDetails>();

			EntryHeader entryheader = entryHeaderDAO.findOne(id);
			Set<EntryDetails> led = entryheader.getEntryDetails();
			eh.setAssignee(entryheader.getAssignee());
			eh.setCategory(entryheader.getCategory());
			eh.setDeleted(entryheader.isDeleted());
			eh.setId(entryheader.getId());
			eh.setImage(entryheader.getImage());
			eh.setModel(entryheader.getModel());
			eh.setManufacturer(entryheader.getManufacturer());
			eh.setNote(entryheader.getNote());
			eh.setOrgId(entryheader.getOrgId());
			eh.setStatus(entryheader.getStatus());
			eh.setTempID(entryheader.getTempID());
			eh.setTempname(entryheader.getTempname());
			eh.setTemptype(entryheader.getTemptype());
			eh.setUid(entryheader.getUid());
			eh.setUpdatedDate(entryheader.getUpdatedDate());
			eh.setVin(entryheader.getVin());
			eh.setYear(entryheader.getYear());

			for (EntryDetails eddd : led) {
				EntryDetails entrydetails = new EntryDetails();
				// entrydetails.setEntryHeader(eddd.getEntryHeader());
				entrydetails.seteCheckItem(eddd.geteCheckItem());
				entrydetails.seteComment(eddd.geteComment());
				// entrydetails.setePic(eddd.getePic());
				entrydetails.seteRecordID(eddd.geteRecordID());
				entrydetails.seteType(eddd.geteType());
				entrydetails.seteValue(eddd.geteValue());
				entrydetails.setEvaluetype(eddd.getEvaluetype());
				entrydetails.setImage1(eddd.getImage1());
				entrydetails.setImage2(eddd.getImage2());
				entrydetails.setImage3(eddd.getImage3());
				entrydetails.setImage4(eddd.getImage4());
				entrydetails.setImage5(eddd.getImage5());

				entrydetails.setTemplateDetailID(eddd.getTemplateDetailID());
				listed.add(entrydetails);
			}
			ej.setEntryheader(eh);
			ej.setListentryDetails(listed);
			ObjectWriter ow = new ObjectMapper().writer()
					.withDefaultPrettyPrinter();
			json = ow.writeValueAsString(ej);
			return json;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	public String findOneMobID(Long id) {

		String json = null;
		try {
			EntryJson ej = new EntryJson();
			EntryHeader eh = new EntryHeader();

			ArrayList<EntryDetails> listed = new ArrayList<EntryDetails>();

			EntryHeader entryheader = entryHeaderDAO.findOne(id);
			Set<EntryDetails> led = entryheader.getEntryDetails();
			eh.setImage(entryheader.getImage());
			eh.setAssignee(entryheader.getAssignee());
			eh.setCategory(entryheader.getCategory());
			eh.setDeleted(entryheader.isDeleted());
			eh.setId(entryheader.getId());
			eh.setModel(entryheader.getModel());
			eh.setManufacturer(entryheader.getManufacturer());
			eh.setNote(entryheader.getNote());
			eh.setOrgId(entryheader.getOrgId());
			eh.setStatus(entryheader.getStatus());
			eh.setTempID(entryheader.getTempID());
			eh.setTempname(entryheader.getTempname());
			eh.setTemptype(entryheader.getTemptype());
			eh.setUid(entryheader.getUid());
			eh.setUpdatedDate(entryheader.getUpdatedDate());
			eh.setVin(entryheader.getVin());
			eh.setYear(entryheader.getYear());

			for (EntryDetails eddd : led) {
				EntryDetails entrydetails = new EntryDetails();
				// entrydetails.setEntryHeader(eddd.getEntryHeader());
				entrydetails.seteCheckItem(eddd.geteCheckItem());
				entrydetails.seteComment(eddd.geteComment());
				// entrydetails.setePic(eddd.getePic());
				entrydetails.seteRecordID(eddd.geteRecordID());
				entrydetails.seteType(eddd.geteType());
				entrydetails.seteValue(eddd.geteValue());
				entrydetails.setEvaluetype(eddd.getEvaluetype());
				entrydetails.setImage1(eddd.getImage1());
				entrydetails.setImage2(eddd.getImage2());
				entrydetails.setImage3(eddd.getImage3());
				entrydetails.setImage4(eddd.getImage4());
				entrydetails.setImage5(eddd.getImage5());

				entrydetails.setTemplateDetailID(eddd.getTemplateDetailID());
				listed.add(entrydetails);
			}
			ej.setEntryheader(eh);
			ej.setListentryDetails(listed);
			ObjectWriter ow = new ObjectMapper().writer()
					.withDefaultPrettyPrinter();
			json = ow.writeValueAsString(ej);

			return json;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
	public EntryHeader findOne(String idOrganizationype, String idValue)
			throws Exception {
		return entryHeaderDAO.findOne(idOrganizationype, idValue);
	}

	public List<EntryHeader> findAll() {
		return entryHeaderDAO.findAll();
	}

	public List<EntryHeader> findAll(String andClause) {
		return entryHeaderDAO.findAll(andClause);
	}

	public String getRecordJson() throws Exception {
		boolean flag=false;
		List<EntryHeader> eh=null;
		List<String> rol =SecurityUtil.getRoles();
		for(int i=0;i<rol.size();i++)
		{
			if(rol.get(i).equalsIgnoreCase("ADMIN")||rol.get(i).equalsIgnoreCase("SUPERVISOR"))
			{
				flag=true;
			}
		}
		if(flag)
		{
			eh = entryHeaderDAO.findAll("AND deleted=false AND status='Draft' AND orgId="+SecurityUtil.getUserOrganisation());
			
		}
		else
		{
			 eh = entryHeaderDAO.findAll("AND deleted=false AND status='Draft' AND uid="+SecurityUtil.getUserId()+" AND orgId="+SecurityUtil.getUserOrganisation());

		}

		ArrayList<EntryHeader> JEH = new ArrayList<EntryHeader>();
		for (EntryHeader EH : eh) {
			UserInfo u= userinfo.findOne(EH.getUid());
			EntryHeader E = new EntryHeader();
			E.setAssignee(EH.getAssignee());
			E.setCategory(EH.getCategory());
			E.setInspected(u.getFirstName()+' '+u.getLastName());
			E.setDeleted(EH.isDeleted());
			E.setId(EH.getId());
			E.setModel(EH.getModel());
			E.setManufacturer(EH.getManufacturer());
			E.setNote(EH.getNote());
			E.setOrgId(EH.getOrgId());
			E.setStatus(EH.getStatus());
			E.setTempID(EH.getTempID());
			E.setTempname(EH.getTempname());
			E.setTemptype(EH.getTemptype());
			E.setUid(EH.getUid());
			E.setUpdatedDate(EH.getUpdatedDate());
			E.setVin(EH.getVin());
			E.setYear(EH.getYear());
			JEH.add(E);

		}
		ObjectWriter ow = new ObjectMapper().writer()
				.withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(JEH);
		// System.out.println(json);
		return json;
	}

	public String getPendingJson() throws Exception {
		List<EntryHeader> eh = entryHeaderDAO.findAll("AND deleted=false AND status='Pending' AND uid="+SecurityUtil.getUserId());
		ArrayList<EntryHeader> JEH = new ArrayList<EntryHeader>();
		for (EntryHeader EH : eh) {
			EntryHeader E = new EntryHeader();
			E.setAssignee(EH.getAssignee());
			E.setCategory(EH.getCategory());
			E.setDeleted(EH.isDeleted());
			E.setId(EH.getId());
			E.setModel(EH.getModel());
			E.setManufacturer(EH.getManufacturer());
			E.setNote(EH.getNote());
			E.setOrgId(EH.getOrgId());
			E.setStatus(EH.getStatus());
			E.setTempID(EH.getTempID());
			E.setTempname(EH.getTempname());
			E.setTemptype(EH.getTemptype());
			E.setUid(EH.getUid());
			E.setUpdatedDate(EH.getUpdatedDate());
			E.setVin(EH.getVin());
			E.setYear(EH.getYear());
			JEH.add(E);

		}
		ObjectWriter ow = new ObjectMapper().writer()
				.withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(JEH);
		// System.out.println(json);
		return json;
	}

	public String insertEH(JSONObject json) {
		ArrayList<String> dateId = new ArrayList();
		String gmtTime = null;
		try {
			EntryHeader eh = new EntryHeader();
			eh.setVin(json.getString("VIN"));
			eh.setOrgId(SecurityUtil.getUserOrganisation());
			eh.setManufacturer(json.getString("Manufacturer"));
			eh.setModel(json.getString("Model"));
			eh.setYear(json.getString("Year"));
			eh.setTemptype(json.getString("TemplateType"));
			eh.setTempname(json.getString("TemplateName"));
			eh.setImage(json.getString("Image"));
			String tn = json.getString("TemplateID");
			eh.setCategory(json.getString("Series"));
			eh.setNote(json.getString("Note"));
			eh.setTempID(Long.parseLong(tn));
			eh.setAssignee(json.getString("Assignee"));
			eh.setStatus(json.getString("Status"));
			eh.setUpdated(SecurityUtil.getUserId());
			eh.setUid(SecurityUtil.getUserId());

			// set GMT time and return to local

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
			gmtTime = sdf.format(new Date());
			eh.setUpdatedDate(gmtTime);
			dateId.add(gmtTime);
			entryHeaderDAO.create(eh);
			dateId.add(Long.toString(eh.getId()));
			ObjectWriter ow = new ObjectMapper().writer()
					.withDefaultPrettyPrinter();
			String di = ow.writeValueAsString(dateId);
			// System.out.println(di);

			return di;


		} catch (JSONException | JsonProcessingException e) {
		
			e.printStackTrace();
		}

		return "hello";
	}

	public void create(EntryHeader entity) {
		entryHeaderDAO.create(entity);
	}

	public void update(EntryHeader entity) {
		entryHeaderDAO.update(entity);
	}

	public void delete(EntryHeader entity) {
		entryHeaderDAO.delete(entity);
	}

	public void deleteById(Long entityId) {
		entryHeaderDAO.deleteById(entityId);
	}

	public String findImageBlobString(String imageName) throws IOException {
		
			String filePath = servletRequest.getRealPath("/")
					+ "/resources/UploadedImage/"
					+ imageName;
			File img = new File(filePath);
			FileInputStream imageInFile = new FileInputStream(img);
			byte imageData[] = new byte[(int) img.length()];
			imageInFile.read(imageData);

			// Converting Image byte array into Base64 String
			String imageDataString = org.apache.commons.codec.binary.Base64.encodeBase64String(imageData);
			//System.out.println(imageDataString);
		return imageDataString;
	}

}
