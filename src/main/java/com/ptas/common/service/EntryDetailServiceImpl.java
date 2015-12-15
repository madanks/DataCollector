package com.ptas.common.service;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.imgscalr.Scalr;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ptas.common.dao.EntryDetailDao;
import com.ptas.common.dao.EntryHeaderDAO;
import com.ptas.common.dao.TempImageDAO;
import com.ptas.common.dao.TemplateDAO;
import com.ptas.common.domain.EntryDetails;
import com.ptas.common.domain.EntryHeader;
import com.ptas.common.domain.TempImage;
import com.ptas.common.domain.TemplateMaster;
import com.ptas.common.util.SecurityUtil;

@Service
@Transactional
public class EntryDetailServiceImpl implements EntryDetailService {
	@Autowired
	private EntryDetailDao EntryDetailDao;

	@Autowired
	private HttpServletRequest servletRequest;

	@Autowired
	private EntryHeaderDAO entryHeaderDAO;

	@Autowired
	private TempImageDAO tempImageDAO;
	
	@Autowired
	private TemplateDAO tmd;

	@Autowired
	private SessionFactory factory;

	public EntryDetails getOrganization(Long organizationId) {
		return EntryDetailDao.findOne(organizationId);
	}

	public EntryDetails findOne(Long id) {
		return EntryDetailDao.findOne(id);
	}

	public EntryDetails findOne(String idOrganizationype, String idValue)
			throws Exception {
		return EntryDetailDao.findOne(idOrganizationype, idValue);
	}

	public List<EntryDetails> findAll() {
		List<EntryDetails> ad = EntryDetailDao.findAll();
		System.out.println(ad);
		return ad;
	}

	public List<EntryDetails> findAll(String andClause) {
		return EntryDetailDao.findAll(andClause);
	}

	public void create(EntryDetails entity) {
		EntryDetailDao.create(entity);
	}

	public String getRecordJson() throws Exception {
		List<EntryDetails> ed = EntryDetailDao.findAll();
		ArrayList<EntryDetails> JED = new ArrayList<EntryDetails>();
		for (EntryDetails ED : ed) {
			EntryDetails E = new EntryDetails();
			E.seteCheckItem(ED.geteCheckItem());
			E.seteComment(ED.geteComment());
			// E.seteHeaderID(ED.geteHeaderID());

			E.seteRecordID(ED.geteRecordID());
			E.seteType(ED.geteType());
			E.seteValue(ED.geteValue());
			E.setEvaluetype(ED.getEvaluetype());
			JED.add(E);

		}
		ObjectWriter ow = new ObjectMapper().writer()
				.withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(JED);
		System.out.println(json);
		return json;
	}


	public String insert(JSONArray json,JSONObject jsonEntryHeader ) {

		try {
			long HeaderID = 0;
			String Status = null;
			String fileName = null;
			long mid=0;
			InputStream inputStream = null;
			OutputStream outputStream = null;

			for (int i = 0, size = json.length(); i < size; i++) {
				EntryDetails ed = new EntryDetails();
				JSONObject objectInArray = json.getJSONObject(i);
				String HeaderIDs = objectInArray.getString("HeaderID");
				ed.seteCheckItem(objectInArray.getString("CheckItem"));
				ed.seteType(objectInArray.getString("Type"));
				ed.seteValue(objectInArray.getString("Value"));
				ed.seteComment(objectInArray.getString("Comment"));
				// Upload---------pic1----------------------------
				if (!objectInArray.getString("picinfo1").equals("")) {
					byte[] byteConent = org.apache.commons.codec.binary.Base64
							.decodeBase64(objectInArray.getString("Pic1"));
					byteConent = scale(byteConent, 300, 200);
					Blob blob = new SerialBlob(byteConent);
					inputStream = blob.getBinaryStream();
					String filePath = servletRequest.getRealPath("/")
							+ "/resources/UploadedImage/" + objectInArray.getString("picinfo1");
					outputStream = new FileOutputStream(filePath);
					int readBytes = 0;
					byte[] buffer = new byte[10000];
					while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
						outputStream.write(buffer, 0, readBytes);
					}
					outputStream.close();
					inputStream.close();
					
				}
				
				// Upload---------pic2----------------------------
				if (!objectInArray.getString("picinfo2").equals("")) {
					byte[] byteConent = org.apache.commons.codec.binary.Base64
							.decodeBase64(objectInArray.getString("Pic2"));
					byteConent = scale(byteConent, 300, 200);
					Blob blob = new SerialBlob(byteConent);
					inputStream = blob.getBinaryStream();

					String filePath = servletRequest.getRealPath("/")
							+ "/resources/UploadedImage/" + objectInArray.getString("picinfo2");
					outputStream = new FileOutputStream(filePath);
					int readBytes = 0;
					byte[] buffer = new byte[10000];
					while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
						outputStream.write(buffer, 0, readBytes);
					}
					outputStream.close();
					inputStream.close();

				}
				
				// Upload---------pic3----------------------------
				if (!objectInArray.getString("picinfo3").equals("")) {
					byte[] byteConent = org.apache.commons.codec.binary.Base64
							.decodeBase64(objectInArray.getString("Pic3"));
					byteConent = scale(byteConent, 300, 200);
					Blob blob = new SerialBlob(byteConent);
					inputStream = blob.getBinaryStream();

					String filePath = servletRequest.getRealPath("/")
							+ "/resources/UploadedImage/" + objectInArray.getString("picinfo3");
					outputStream = new FileOutputStream(filePath);
					int readBytes = 0;
					byte[] buffer = new byte[10000];
					while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
						outputStream.write(buffer, 0, readBytes);
					}
					outputStream.close();
					inputStream.close();
				}
				
				// Upload---------pic4----------------------------
				if (!objectInArray.getString("picinfo4").equals("")) {
					byte[] byteConent = org.apache.commons.codec.binary.Base64
							.decodeBase64(objectInArray.getString("Pic4"));
					byteConent = scale(byteConent, 300, 200);
					Blob blob = new SerialBlob(byteConent);
					inputStream = blob.getBinaryStream();
					String filePath = servletRequest.getRealPath("/")
							+ "/resources/UploadedImage/" + objectInArray.getString("picinfo4");
					outputStream = new FileOutputStream(filePath);
					int readBytes = 0;
					byte[] buffer = new byte[10000];
					while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
						outputStream.write(buffer, 0, readBytes);
					}
					outputStream.close();
					inputStream.close();
				}
				
				// Upload---------pic5----------------------------
				if (!objectInArray.getString("picinfo5").equals("")) {
					byte[] byteConent = org.apache.commons.codec.binary.Base64
							.decodeBase64(objectInArray.getString("Pic5"));
					byteConent = scale(byteConent, 300, 200);
					Blob blob = new SerialBlob(byteConent);
					inputStream = blob.getBinaryStream();
					String filePath = servletRequest.getRealPath("/")
							+ "/resources/UploadedImage/" + objectInArray.getString("picinfo5");
					outputStream = new FileOutputStream(filePath);
					int readBytes = 0;
					byte[] buffer = new byte[10000];
					while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
						outputStream.write(buffer, 0, readBytes);
					}
					outputStream.close();
					inputStream.close();
					
				}
				ed.setImage5(objectInArray.getString("picinfo5"));
				ed.setImage4(objectInArray.getString("picinfo4"));
				ed.setImage3(objectInArray.getString("picinfo3"));
				ed.setImage2(objectInArray.getString("picinfo2"));
				ed.setImage1(objectInArray.getString("picinfo1"));
				ed.setEvaluetype(objectInArray.getString("valuetype"));
				ed.setTemplateDetailID(objectInArray.getString("TemplateDetailID"));
				Status = objectInArray.getString("Status");
				HeaderID = (new Double(HeaderIDs)).longValue();
				EntryHeader eh = entryHeaderDAO.findOne(HeaderID);
				ed.setEntryHeader(eh);
				EntryDetailDao.create(ed);
			}
			
			if (!jsonEntryHeader.getString("headimagename").equals("")) {
				byte[] byteConent = org.apache.commons.codec.binary.Base64
						.decodeBase64(jsonEntryHeader.getString("headimageblob"));
				byteConent = scale(byteConent, 300, 200);
				Blob blob = new SerialBlob(byteConent);
				inputStream = blob.getBinaryStream();
				String filePath = servletRequest.getRealPath("/")
						+ "/resources/UploadedImage/" + jsonEntryHeader.getString("headimagename");
				outputStream = new FileOutputStream(filePath);
				int readBytes = 0;
				byte[] buffer = new byte[10000];
				while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
					outputStream.write(buffer, 0, readBytes);
				}
				outputStream.close();
				inputStream.close();
				
			}
			EntryHeader ehe = entryHeaderDAO.findOne(HeaderID);
			
			ehe.setAssignee(jsonEntryHeader.getString("Assignee"));
			ehe.setCategory(jsonEntryHeader.getString("Series"));
			ehe.setImage(jsonEntryHeader.getString("headimagename"));
			ehe.setManufacturer(jsonEntryHeader.getString("Manufacturer"));
			ehe.setModel(jsonEntryHeader.getString("Model"));
			ehe.setNote(jsonEntryHeader.getString("Note"));
			ehe.setOrgId(SecurityUtil.getUserOrganisation());
			ehe.setTempID(jsonEntryHeader.getLong("TemplateID"));
			ehe.setTempname(jsonEntryHeader.getString("TemplateName"));
			ehe.setTemptype(jsonEntryHeader.getString("TemplateType"));						
			ehe.setUpdated(SecurityUtil.getUserId());		
						
			ehe.setVersion(jsonEntryHeader.getString("Version"));
			ehe.setVin(jsonEntryHeader.getString("VIN"));
			ehe.setYear(jsonEntryHeader.getString("Year"));
			
			TemplateMaster tempMas=tmd.findOne(ehe.getTempID());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
			String gmtTime = sdf.format(new Date());
			ehe.setUpdatedDate(gmtTime);
			if(Status.equalsIgnoreCase("Pending"))
			{
				if(tempMas.isApproval())
				{
					ehe.setStatus("Pending");
				}
				else
				{
					ehe.setStatus("COMPLETED");
				}
				
			}
			else
			{
				ehe.setStatus("Draft");
			}
			entryHeaderDAO.update(ehe);

			return gmtTime;

		} catch (JSONException | SQLException | IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public String UpdateJson(JSONObject jsonUpdate) {
		Session sess = factory.openSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			JSONObject jsonEntryHeader = jsonUpdate
					.getJSONObject("entryheader");
			JSONArray jsonArrayDetails = jsonUpdate
					.getJSONArray("listentryDetails");
			EntryHeader eh = entryHeaderDAO.findOne(jsonEntryHeader
					.getLong("id"));
			for (EntryDetails ed : eh.getEntryDetails()) {
				if (ed.getImage1() != null || ed.getImage1() != "") {
					String filePath = servletRequest.getRealPath("/")
							+ "/resources/UploadedImage/" + ed.getImage1();
					File f = new File(filePath);
					f.delete();
				}
				EntryDetailDao.deleteById(ed.geteRecordID());
			}
			String date = insert(jsonArrayDetails,jsonEntryHeader);
			tx.commit();
			return date;
		} catch (RuntimeException | JSONException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			sess.close();
		}
		// EntryDetailDao.update(entity);
		return null;
	}

	public void update(EntryDetails entity) {
		EntryDetailDao.update(entity);
	}

	public void delete(EntryDetails entity) {
		EntryDetailDao.delete(entity);
	}

	public void deleteById(Long entityId) {
		EntryDetailDao.deleteById(entityId);
	}

	public byte[] scale(byte[] fileData, int width, int height) {
		ByteArrayInputStream in = new ByteArrayInputStream(fileData);
		try {
			BufferedImage img = ImageIO.read(in);
			if (height == 0) {
				height = (width * img.getHeight()) / img.getWidth();
			}
			if (width == 0) {
				width = (height * img.getWidth()) / img.getHeight();
			}
			Image scaledImage = img.getScaledInstance(width, height,
					Image.SCALE_SMOOTH);
			BufferedImage imageBuff = new BufferedImage(width, height,
					BufferedImage.TYPE_INT_RGB);
			imageBuff.getGraphics().drawImage(scaledImage, 0, 0,
					new Color(0, 0, 0), null);

			ByteArrayOutputStream buffer = new ByteArrayOutputStream();

			ImageIO.write(imageBuff, "jpg", buffer);

			return buffer.toByteArray();
		} catch (IOException e) {
			// throw new ApplicationException("IOException in scale");
		}
		return null;
	}

	public String SaveImage(MultipartHttpServletRequest request)
			throws IllegalStateException, IOException {
		MultipartFile file = request.getFile("file");

		String data = request.getParameter("data");
		String uniquefilename = null;
		if (data == null) {
			uniquefilename = "" + SecurityUtil.getUserOrganisation() + ""
					+ SecurityUtil.getUserId() + ""
					+ System.currentTimeMillis()
					+ file.getOriginalFilename().replace(" ", "-");

		} else {
			uniquefilename = data;
		}

		String filePath = servletRequest.getRealPath("/")
				+ "/resources/UploadedImage/" + uniquefilename;
		String extension = "";

		int i = file.getOriginalFilename().lastIndexOf('.');
		if (i > 0) {
			extension = file.getOriginalFilename().substring(i + 1);
		}
		file.transferTo(new File(filePath));
		BufferedImage img = ImageIO.read(new File(filePath)); // load image
		BufferedImage thumbnail = Scalr.resize(img, 350);
		ImageIO.write(thumbnail, extension, new File(filePath));
		TempImage ti = new TempImage();
		ti.setImageName(uniquefilename);
		ti.setUpdatedBy(SecurityUtil.getUserId());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

		ti.setUpdatedDate(sdf.format(new Date()));
		tempImageDAO.create(ti);
		return uniquefilename;
	}

	public String UpdateNewJson(JSONObject jsonDetail) throws SQLException,
			IOException {
		Session sess = factory.openSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			JSONObject jsonEntryHeader = jsonDetail
					.getJSONObject("entryheader");
			JSONArray jsonArrayDetails = jsonDetail
					.getJSONArray("listentryDetails");
			
			EntryHeader eh = entryHeaderDAO.findOne(jsonEntryHeader.getLong("id"));
			for (EntryDetails ed : eh.getEntryDetails()) {
//				if (ed.getImage() != null || ed.getImage() != "") {
//					String filePath = servletRequest.getRealPath("/")
//							+ "/resources/UploadedImage/" + ed.getImage();
//					File f = new File(filePath);
//					f.delete();
//				}
				EntryDetailDao.deleteById(ed.geteRecordID());
			}
			String date = insertNew(jsonArrayDetails,jsonEntryHeader);
			tx.commit();
			return date;
		} catch (Exception e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			sess.close();
		}
		// EntryDetailDao.update(entity);
		return null;
	}


	private String insertNew(JSONArray jsonArrayDetails,JSONObject jsonEntryHeader) throws SQLException,
			IOException {
		try {
			long HeaderID = 0;
			String Status = null;
			for (int i = 0, size = jsonArrayDetails.length(); i < size; i++) {
				EntryDetails ed = new EntryDetails();
				JSONObject objectInArray = jsonArrayDetails.getJSONObject(i);
				String HeaderIDs = objectInArray.getString("HeaderID");
				ed.seteCheckItem(objectInArray.getString("CheckItem"));
				ed.seteType(objectInArray.getString("Type"));
				ed.seteValue(objectInArray.getString("Value"));
				ed.seteComment(objectInArray.getString("Comment"));
				ed.setImage1(objectInArray.getString("picinfo1"));
				ed.setImage2(objectInArray.getString("picinfo2"));
				ed.setImage3(objectInArray.getString("picinfo3"));
				ed.setImage4(objectInArray.getString("picinfo4"));
				ed.setImage5(objectInArray.getString("picinfo5"));
				ed.setEvaluetype(objectInArray.getString("valuetype"));
				ed.setTemplateDetailID(objectInArray
						.getString("TemplateDetailID"));
				Status = objectInArray.getString("Status");
				HeaderID = (new Double(HeaderIDs)).longValue();
				EntryHeader eh = entryHeaderDAO.findOne(HeaderID);
				ed.setEntryHeader(eh);
				EntryDetailDao.create(ed);
			}
			EntryHeader ehe = entryHeaderDAO.findOne(HeaderID);

			ehe.setAssignee(jsonEntryHeader.getString("Assignee"));
			ehe.setCategory(jsonEntryHeader.getString("Series"));
			ehe.setImage(jsonEntryHeader.getString("headimagename"));
			ehe.setManufacturer(jsonEntryHeader.getString("Manufacturer"));
			ehe.setModel(jsonEntryHeader.getString("Model"));
			ehe.setNote(jsonEntryHeader.getString("Note"));
			ehe.setOrgId(SecurityUtil.getUserOrganisation());
			ehe.setTempID(jsonEntryHeader.getLong("TemplateID"));
			ehe.setTempname(jsonEntryHeader.getString("TemplateName"));
			ehe.setTemptype(jsonEntryHeader.getString("TemplateType"));						
			ehe.setUpdated(SecurityUtil.getUserId());		
					
			ehe.setVersion(jsonEntryHeader.getString("Version"));
			ehe.setVin(jsonEntryHeader.getString("VIN"));
			ehe.setYear(jsonEntryHeader.getString("Year"));
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

			String gmtTime = sdf.format(new Date());
			ehe.setUpdatedDate(gmtTime);	
			ehe.setStatus(Status);
			entryHeaderDAO.update(ehe);

			return gmtTime;

		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public String insert(JSONArray json) {

		try {
			long HeaderID = 0;
			String Status = null;
			String fileName = null;
			long mid=0;
			InputStream inputStream = null;
			OutputStream outputStream = null;

			for (int i = 0, size = json.length(); i < size; i++) {
				EntryDetails ed = new EntryDetails();
				JSONObject objectInArray = json.getJSONObject(i);
				String HeaderIDs = objectInArray.getString("HeaderID");
				ed.seteCheckItem(objectInArray.getString("CheckItem"));
				ed.seteType(objectInArray.getString("Type"));
				ed.seteValue(objectInArray.getString("Value"));
				ed.seteComment(objectInArray.getString("Comment"));
				// Upload---------pic1----------------------------
				if (!objectInArray.getString("picinfo1").equals("")) {
					byte[] byteConent = org.apache.commons.codec.binary.Base64
							.decodeBase64(objectInArray.getString("Pic1"));
					byteConent = scale(byteConent, 300, 200);
					Blob blob = new SerialBlob(byteConent);
					inputStream = blob.getBinaryStream();
					String filePath = servletRequest.getRealPath("/")
							+ "/resources/UploadedImage/" + objectInArray.getString("picinfo1");
					outputStream = new FileOutputStream(filePath);
					int readBytes = 0;
					byte[] buffer = new byte[10000];
					while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
						outputStream.write(buffer, 0, readBytes);
					}
					outputStream.close();
					inputStream.close();
					
					
				}
				
				// Upload---------pic2----------------------------
				if (!objectInArray.getString("picinfo2").equals("")) {
					byte[] byteConent = org.apache.commons.codec.binary.Base64
							.decodeBase64(objectInArray.getString("Pic2"));
					byteConent = scale(byteConent, 300, 200);
					Blob blob = new SerialBlob(byteConent);
					inputStream = blob.getBinaryStream();

					String filePath = servletRequest.getRealPath("/")
							+ "/resources/UploadedImage/" + objectInArray.getString("picinfo2");
					outputStream = new FileOutputStream(filePath);
					int readBytes = 0;
					byte[] buffer = new byte[10000];
					while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
						outputStream.write(buffer, 0, readBytes);
					}
					outputStream.close();
					inputStream.close();

				}
				
				// Upload---------pic3----------------------------
				if (!objectInArray.getString("picinfo3").equals("")) {
					byte[] byteConent = org.apache.commons.codec.binary.Base64
							.decodeBase64(objectInArray.getString("Pic3"));
					byteConent = scale(byteConent, 300, 200);
					Blob blob = new SerialBlob(byteConent);
					inputStream = blob.getBinaryStream();

					String filePath = servletRequest.getRealPath("/")
							+ "/resources/UploadedImage/" + objectInArray.getString("picinfo3");
					outputStream = new FileOutputStream(filePath);
					int readBytes = 0;
					byte[] buffer = new byte[10000];
					while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
						outputStream.write(buffer, 0, readBytes);
					}
					outputStream.close();
					inputStream.close();
				}
				
				// Upload---------pic4----------------------------
				if (!objectInArray.getString("picinfo4").equals("")) {
					byte[] byteConent = org.apache.commons.codec.binary.Base64
							.decodeBase64(objectInArray.getString("Pic4"));
					byteConent = scale(byteConent, 300, 200);
					Blob blob = new SerialBlob(byteConent);
					inputStream = blob.getBinaryStream();
					String filePath = servletRequest.getRealPath("/")
							+ "/resources/UploadedImage/" + objectInArray.getString("picinfo4");
					outputStream = new FileOutputStream(filePath);
					int readBytes = 0;
					byte[] buffer = new byte[10000];
					while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
						outputStream.write(buffer, 0, readBytes);
					}
					outputStream.close();
					inputStream.close();
				}
				
				// Upload---------pic5----------------------------
				if (!objectInArray.getString("picinfo5").equals("")) {
					byte[] byteConent = org.apache.commons.codec.binary.Base64
							.decodeBase64(objectInArray.getString("Pic5"));
					byteConent = scale(byteConent, 300, 200);
					Blob blob = new SerialBlob(byteConent);
					inputStream = blob.getBinaryStream();
					String filePath = servletRequest.getRealPath("/")
							+ "/resources/UploadedImage/" + objectInArray.getString("picinfo5");
					outputStream = new FileOutputStream(filePath);
					int readBytes = 0;
					byte[] buffer = new byte[10000];
					while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
						outputStream.write(buffer, 0, readBytes);
					}
					outputStream.close();
					inputStream.close();
					
				}
				ed.setImage5(objectInArray.getString("picinfo5"));
				ed.setImage4(objectInArray.getString("picinfo4"));
				ed.setImage3(objectInArray.getString("picinfo3"));
				ed.setImage2(objectInArray.getString("picinfo2"));
				ed.setImage1(objectInArray.getString("picinfo1"));
				ed.setEvaluetype(objectInArray.getString("valuetype"));
				ed.setTemplateDetailID(objectInArray
						.getString("TemplateDetailID"));
				Status = objectInArray.getString("Status");
				HeaderID = (new Double(HeaderIDs)).longValue();
				EntryHeader eh = entryHeaderDAO.findOne(HeaderID);
				ed.setEntryHeader(eh);
				EntryDetailDao.create(ed);
			}
			EntryHeader ehe = entryHeaderDAO.findOne(HeaderID);
			
			
			TemplateMaster tempMas=tmd.findOne(ehe.getTempID());
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
			String gmtTime = sdf.format(new Date());
			
			if(Status.equalsIgnoreCase("Pending"))
			{
				if(tempMas.isApproval())
				{
					ehe.setStatus("Pending");
				}
				else
				{
					ehe.setStatus("COMPLETED");
				}
				
			}
			else
			{
				ehe.setStatus("Draft");
			}
			entryHeaderDAO.update(ehe);

			return gmtTime;

		} catch (JSONException | SQLException | IOException e) {
			e.printStackTrace();
		}
		return null;

	}

	public String InsertHeaderDetailJson(JSONObject jsonDetail) throws SerialException, SQLException, IOException {

		Session sess = factory.openSession();
		Transaction tx = null;
		try {
			tx = sess.beginTransaction();
			JSONObject jsonEntryHeader = jsonDetail.getJSONObject("entryheader");
			JSONArray jsonArrayDetails = jsonDetail.getJSONArray("listentryDetails");
			String date = insertOfflineData(jsonArrayDetails,jsonEntryHeader);
			tx.commit();
			return date;
		} catch (RuntimeException | JSONException e) {
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			sess.close();
		}
		// EntryDetailDao.update(entity);
		return null;

	}

	private String insertOfflineData(JSONArray jsonArrayDetails,
			JSONObject jsonEntryHeader) throws JSONException, SerialException, SQLException, IOException {
		String Status = null;
		EntryHeader ehe = new EntryHeader();
		ehe.setAssignee(jsonEntryHeader.getString("Assignee"));
		ehe.setCategory(jsonEntryHeader.getString("Series"));
		ehe.setImage(jsonEntryHeader.getString("headimagename"));
		ehe.setManufacturer(jsonEntryHeader.getString("Manufacturer"));
		ehe.setModel(jsonEntryHeader.getString("Model"));
		ehe.setNote(jsonEntryHeader.getString("Note"));
		ehe.setOrgId(SecurityUtil.getUserOrganisation());
		ehe.setUid(SecurityUtil.getUserId());
		ehe.setTempID(jsonEntryHeader.getLong("TemplateID"));
		ehe.setTempname(jsonEntryHeader.getString("TemplateName"));
		ehe.setTemptype(jsonEntryHeader.getString("TemplateType"));						
		ehe.setUpdated(SecurityUtil.getUserId());		
		Status = jsonEntryHeader.getString("Status");			
		ehe.setVersion(jsonEntryHeader.getString("Version"));
		ehe.setVin(jsonEntryHeader.getString("VIN"));
		ehe.setYear(jsonEntryHeader.getString("Year"));
		TemplateMaster tempMas=tmd.findOne(ehe.getTempID());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		String gmtTime = sdf.format(new Date());
		ehe.setUpdatedDate(gmtTime);
		if(Status.equalsIgnoreCase("Pending"))
		{
			if(tempMas.isApproval())
			{
				ehe.setStatus("Pending");
			}
			else
			{
				ehe.setStatus("COMPLETED");
			}
			
		}
		else
		{
			ehe.setStatus("Draft");
		}
		entryHeaderDAO.create(ehe);
		InsertDetail(ehe.getId(),jsonArrayDetails);
		
		
		return "{\"Date\":\""+gmtTime+"\",\"HeaderID\":\""+ehe.getId()+"\"}";
	}

	private void InsertDetail(Long id, JSONArray jsonArrayDetails) throws JSONException, SerialException, SQLException, IOException {
		InputStream inputStream = null;
		OutputStream outputStream = null;

		for (int i = 0, size = jsonArrayDetails.length(); i < size; i++) {
			EntryDetails ed = new EntryDetails();
			JSONObject objectInArray = jsonArrayDetails.getJSONObject(i);
			ed.seteCheckItem(objectInArray.getString("CheckItem"));
			ed.seteType(objectInArray.getString("Type"));
			ed.seteValue(objectInArray.getString("Value"));
			ed.seteComment(objectInArray.getString("Comment"));
			// Upload---------pic1----------------------------
			if (!objectInArray.getString("picinfo1").equals("")) {
				byte[] byteConent = org.apache.commons.codec.binary.Base64
						.decodeBase64(objectInArray.getString("Pic1"));
				byteConent = scale(byteConent, 300, 200);
				Blob blob = new SerialBlob(byteConent);
				inputStream = blob.getBinaryStream();
				String filePath = servletRequest.getRealPath("/")
						+ "/resources/UploadedImage/" + objectInArray.getString("picinfo1");
				outputStream = new FileOutputStream(filePath);
				int readBytes = 0;
				byte[] buffer = new byte[10000];
				while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
					outputStream.write(buffer, 0, readBytes);
				}
				outputStream.close();
				inputStream.close();
				
				
			}
			
			// Upload---------pic2----------------------------
			if (!objectInArray.getString("picinfo2").equals("")) {
				byte[] byteConent = org.apache.commons.codec.binary.Base64
						.decodeBase64(objectInArray.getString("Pic2"));
				byteConent = scale(byteConent, 300, 200);
				Blob blob = new SerialBlob(byteConent);
				inputStream = blob.getBinaryStream();

				String filePath = servletRequest.getRealPath("/")
						+ "/resources/UploadedImage/" + objectInArray.getString("picinfo2");
				outputStream = new FileOutputStream(filePath);
				int readBytes = 0;
				byte[] buffer = new byte[10000];
				while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
					outputStream.write(buffer, 0, readBytes);
				}
				outputStream.close();
				inputStream.close();

			}
			
			// Upload---------pic3----------------------------
			if (!objectInArray.getString("picinfo3").equals("")) {
				byte[] byteConent = org.apache.commons.codec.binary.Base64
						.decodeBase64(objectInArray.getString("Pic3"));
				byteConent = scale(byteConent, 300, 200);
				Blob blob = new SerialBlob(byteConent);
				inputStream = blob.getBinaryStream();

				String filePath = servletRequest.getRealPath("/")
						+ "/resources/UploadedImage/" + objectInArray.getString("picinfo3");
				outputStream = new FileOutputStream(filePath);
				int readBytes = 0;
				byte[] buffer = new byte[10000];
				while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
					outputStream.write(buffer, 0, readBytes);
				}
				outputStream.close();
				inputStream.close();
			}
			
			// Upload---------pic4----------------------------
			if (!objectInArray.getString("picinfo4").equals("")) {
				byte[] byteConent = org.apache.commons.codec.binary.Base64
						.decodeBase64(objectInArray.getString("Pic4"));
				byteConent = scale(byteConent, 300, 200);
				Blob blob = new SerialBlob(byteConent);
				inputStream = blob.getBinaryStream();
				String filePath = servletRequest.getRealPath("/")
						+ "/resources/UploadedImage/" + objectInArray.getString("picinfo4");
				outputStream = new FileOutputStream(filePath);
				int readBytes = 0;
				byte[] buffer = new byte[10000];
				while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
					outputStream.write(buffer, 0, readBytes);
				}
				outputStream.close();
				inputStream.close();
			}
			
			// Upload---------pic5----------------------------
			if (!objectInArray.getString("picinfo5").equals("")) {
				byte[] byteConent = org.apache.commons.codec.binary.Base64
						.decodeBase64(objectInArray.getString("Pic5"));
				byteConent = scale(byteConent, 300, 200);
				Blob blob = new SerialBlob(byteConent);
				inputStream = blob.getBinaryStream();
				String filePath = servletRequest.getRealPath("/")
						+ "/resources/UploadedImage/" + objectInArray.getString("picinfo5");
				outputStream = new FileOutputStream(filePath);
				int readBytes = 0;
				byte[] buffer = new byte[10000];
				while ((readBytes = inputStream.read(buffer, 0, 10000)) != -1) {
					outputStream.write(buffer, 0, readBytes);
				}
				outputStream.close();
				inputStream.close();
				
			}
			ed.setImage5(objectInArray.getString("picinfo5"));
			ed.setImage4(objectInArray.getString("picinfo4"));
			ed.setImage3(objectInArray.getString("picinfo3"));
			ed.setImage2(objectInArray.getString("picinfo2"));
			ed.setImage1(objectInArray.getString("picinfo1"));
			ed.setEvaluetype(objectInArray.getString("valuetype"));
			ed.setTemplateDetailID(objectInArray
					.getString("TemplateDetailID"));
			EntryHeader eh = entryHeaderDAO.findOne(id);
			ed.setEntryHeader(eh);
			EntryDetailDao.create(ed);
		}
	}

}
