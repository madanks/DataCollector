package com.ptas.common.service;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.ptas.common.dao.NarrativeLibraryDAO;
import com.ptas.common.dao.OrganizationDAO;
import com.ptas.common.dao.TemplateDAO;
import com.ptas.common.dao.TemplateDetailDAO;
import com.ptas.common.dao.UserDAO;
import com.ptas.common.dao.UserInfoDAO;
import com.ptas.common.dao.UserRoleDAO;
import com.ptas.common.domain.NarrativeLibrary;
import com.ptas.common.domain.Organization;
import com.ptas.common.domain.TemplateDetail;
import com.ptas.common.domain.TemplateMaster;
import com.ptas.common.domain.User;
import com.ptas.common.domain.UserInfo;
import com.ptas.common.domain.UserRole;
import com.ptas.common.util.SecurityUtil;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private OrganizationDAO orgn;

	@Autowired
	private UserInfoDAO userInfoDAO;

	@Autowired
	private UserRoleDAO userRoleDAO;

	@Autowired
	private HttpServletRequest servletRequest;

	@Autowired
	private TemplateDetailDAO templateDetailDAO;

	@Autowired
	private TemplateDAO templateDAO;

	@Autowired
	private NarrativeLibraryDAO narrativeLibraryDAO;

	public User getUser(String login) {
		return userDAO.getUser(login);
	}

	public User findOne(String idUserype, String idValue) throws Exception {
		return userDAO.findOne(idUserype, idValue);
	}

	public List<User> findAll() {
		return userDAO.findAll();
	}

	public List<User> findAll(String andClause) {
		return userDAO.findAll(andClause);
	}

	@Transactional
	public void create(User user) {

		try {

			UserInfo ui = user.getUserInfo();

			userInfoDAO.create(ui);

			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			Date date;
			date = dateFormat.parse(dateFormat.format(cal.getTime()));
			user.setCreatedDate(date);
			Organization o = orgn.findOne(SecurityUtil.getUserOrganisation());
			user.setOrganization(o);
			user.setCreatedBy(SecurityUtil.getUserId());
			user.setUserInfo(ui);
			userDAO.create(user);

			Set<UserRole> rrr = user.getUserRole();
			for (UserRole u : rrr) {
				UserRole ur = new UserRole();
				ur.setRole(u.getRole());
				ur.setUpdated(SecurityUtil.getUserId());
				ur.setUser(user);
				userRoleDAO.create(ur);

			}
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Transactional
	public void update(User user) {

		User u = userDAO.getUser(user.getUsername());
		for (UserRole ur : u.getUserRole()) {
			userRoleDAO.deleteIdR(ur.getUserRoleId());
		}
		// update userinfo
		UserInfo ui = user.getUserInfo();
		ui.setUpdated(SecurityUtil.getUserId());
		userInfoDAO.update(ui);
		// update user
		user.setUpdated(SecurityUtil.getUserId());
		userDAO.update(user);
		// update userRole

		Set<UserRole> rrr = user.getUserRole();
		for (UserRole us : rrr) {
			UserRole urs = new UserRole();
			urs.setRole(us.getRole());
			urs.setUpdated(SecurityUtil.getUserId());
			urs.setUser(user);
			userRoleDAO.create(urs);

		}

	}

	public void deactivate(User user) {
		// UserInfo ui = user.getUserInfo();
		// ui.setUpdated(SecurityUtil.getUserId());
		// userInfoDAO.update(ui);// insert user info
		//
		// System.out.println(ui.getFirstName());
		// user.setPassword("Expired");
		user.setAccountNonExpired(false);
		user.setAccountNonLocked(false);
		user.setCredentialsNonExpired(false);
		user.setEnabled(false);
		user.setUpdated(SecurityUtil.getUserId());
		// user.setUserInfo(ui);
		userDAO.update(user);

	}

	public void delete(User entity) {
		userDAO.delete(entity);
	}

	public void deleteById(Long entityId) {
		userDAO.deleteById(entityId);
	}

	public String ChangePassword(JSONObject userService) {
		try {
			User uu = userDAO.findOne("username", userService
					.getString("uname").trim());
			uu.setPassword(userService.getString("pass"));
			userDAO.update(uu);
		
			return "{\"message\":\"Password Successfully Changed\"}";
		} catch (Exception e) {

			e.printStackTrace();
		}
		return "{\"message\":\"Failed to change Password\"}";

	}

	@Transactional
	public void create1(User user1) {
		try {

			UserInfo ui = user1.getUserInfo();

			userInfoDAO.create(ui);

			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			Date date;
			date = dateFormat.parse(dateFormat.format(cal.getTime()));
			user1.setCreatedDate(date);

			user1.setCreatedBy(SecurityUtil.getUserId());
			user1.setUserInfo(ui);
			userDAO.create(user1);

			Set<UserRole> rrr = user1.getUserRole();
			for (UserRole u : rrr) {
				UserRole ur = new UserRole();
				ur.setRole(u.getRole());
				ur.setUpdated(SecurityUtil.getUserId());
				ur.setUser(user1);
				userRoleDAO.create(ur);

			}
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public void UserSignup(JSONObject signupData) throws JSONException,
			IOException, ParseException, InvalidFormatException {
		Organization org = new Organization();
		org.setName(signupData.getString("OrgName"));
		org.setDeleted(false);
		org.setAddress(signupData.getString("OrgAdd"));
		org.setEmail(signupData.getString("Email"));
		org.setPhone(signupData.getString("Phone"));
		org.setPrimaryContact(signupData.getString("FirstName") + " "
				+ signupData.getString("LastName"));
		orgn.create(org);

		UserInfo ui = new UserInfo();
		ui.setFirstName(signupData.getString("FirstName"));
		ui.setLastName(signupData.getString("LastName"));
		ui.setEmail(signupData.getString("Email"));
		ui.setPhone(signupData.getString("Phone"));
		ui.setAddress(signupData.getString("OrgAdd"));
		userInfoDAO.create(ui);

		User u = new User();
		u.setUsername(signupData.getString("UserName"));
		u.setPassword(signupData.getString("pass"));
		u.setAccountNonExpired(true);
		u.setAccountNonLocked(true);
		u.setCreatedDate(new Date());
		u.setCredentialsNonExpired(true);
		u.setEnabled(true);
		u.setOrganization(org);
		u.setCreatedBy(ui.getUserId());
		u.setUserInfo(ui);
		userDAO.create(u);

		UserRole ur = new UserRole();
		ur.setRole("ADMIN");
		ur.setUser(u);
		userRoleDAO.create(ur);

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		Date date = dateFormat.parse(dateFormat.format(cal.getTime()));
		String fileHeader;
		Workbook workbook ;
		fileHeader = servletRequest.getRealPath("/")
					+ "/resources/Excel/TemplateHeader.xlsx";
		workbook = WorkbookFactory.create(new FileInputStream(fileHeader));
		UploadHeader(workbook, org.getId(), date,ui.getUserId());//header Upload;
		
		fileHeader = servletRequest.getRealPath("/")
				+ "/resources/Excel/TemplateDetails.xlsx";
		workbook = WorkbookFactory.create(new FileInputStream(fileHeader));
		UploadDetails(workbook,date,ui.getUserId(),org.getId());
		
		fileHeader = servletRequest.getRealPath("/")
				+ "/resources/Excel/NarrativeLibrary.xlsx";
		workbook = WorkbookFactory.create(new FileInputStream(fileHeader));
		UploadNarrativeLibrary(workbook, org.getId(), date);
	}

	private void UploadNarrativeLibrary(Workbook workbook, Long org, Date date) {
		XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
		XSSFRow row;
		
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);

				NarrativeLibrary narrlib = new NarrativeLibrary();
				narrlib.setLibrary(row.getCell(0).getStringCellValue()
						.toUpperCase());
				narrlib.setValue(row.getCell(1).getStringCellValue());
				narrlib.setoId(org);
				narrativeLibraryDAO.create(narrlib);
				
		}
		
	}

	private void UploadDetails(Workbook workbook, Date date, Long user, Long org) {
		XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
		XSSFRow row;
		XSSFRow row1 = sheet.getRow(1);
		double templateNumber = row1.getCell(0).getNumericCellValue();
		long templatenumber = (new Double(templateNumber)).longValue();
		TemplateMaster tm = templateDAO.find("AND templateNumber=" + templatenumber+" AND org_ID="+org);
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {			
			row = sheet.getRow(i);
			TemplateDetail td = new TemplateDetail();

				String Checkitem = row.getCell(1).getStringCellValue();
				String Group = row.getCell(2).getStringCellValue();
				String Section = row.getCell(3).getStringCellValue();
				String Type = row.getCell(4).getStringCellValue();
				String ValueType = row.getCell(5).getStringCellValue();
				String ValueOption = row.getCell(6).getStringCellValue();
				String library = row.getCell(7).getStringCellValue().toUpperCase();
				String Comment = row.getCell(8).getStringCellValue();
				String Ap = row.getCell(9).getStringCellValue();
				String Av = row.getCell(10).getStringCellValue();
				String fieldName = row.getCell(11).getStringCellValue();
				String TextInstruction = row.getCell(12)
						.getStringCellValue();
				String PicInstruction = row.getCell(13)
						.getStringCellValue();
				String VidInstruction = row.getCell(14)
						.getStringCellValue();

				td.setTemplateMaster(tm);
				td.setCheckItem(Checkitem.trim());
				td.setGroup(Group.trim());
				td.setSection(Section.trim());
				td.setType(Type.trim());
				td.setValueType(ValueType.trim());
				td.setLibrary(library);
				td.setFieldName(fieldName);
				td.setValueOption(ValueOption.trim());
				if (Comment.trim().equalsIgnoreCase("yes")) {
					td.setAllcomment(true);
				}

				if (Ap.trim().equalsIgnoreCase("yes")) {
					td.setAllowPic(true);
				}
				if (Av.trim().equalsIgnoreCase("yes")) {
					td.setAllowVideo(true);
				}
				td.setTextInstruction(TextInstruction.trim());
				td.setPictureInstruction(PicInstruction.trim());
				td.setVideoInstruction(VidInstruction.trim());
				td.setUpdated(user);
				td.setUpdatedDate(date);
				templateDetailDAO.create(td);
		}
	}

	private void UploadHeader(Workbook workbook, Long org, Date date, Long user) {
		XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
		XSSFRow row;

		// templateDAO.deleteTableDataTM();
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			row = sheet.getRow(i);
			double tempNum = row.getCell(0).getNumericCellValue();
			long templateNumber = (new Double(tempNum)).longValue();// templateNumber
			String version = row.getCell(1).getStringCellValue();// version
			String temp_typ = row.getCell(2).getStringCellValue();// template
																	// type
			String temp_name = row.getCell(3).getStringCellValue();// template
																	// Name
			String mfg = row.getCell(4).getStringCellValue(); // Manufacturer
			String category = row.getCell(5).getStringCellValue();// category
			String model = row.getCell(6).getStringCellValue();// model
			Date year = row.getCell(7).getDateCellValue(); // year
			// System.out.println(year);
			String status = row.getCell(8).getStringCellValue();// status
			String approval = row.getCell(9).getStringCellValue();// status

			TemplateMaster tempMaster = new TemplateMaster();
			tempMaster.setTemplateNumber(templateNumber);
			tempMaster.setVersion(version.trim());
			tempMaster.setTemp_type(temp_typ.trim().toUpperCase());
			tempMaster.setTemp_name(temp_name.trim().toUpperCase());
			tempMaster.setOrg_ID(org);
			tempMaster.setManufacturer(mfg.trim().toUpperCase());
			tempMaster.setCategory(category.trim());
			tempMaster.setModel(model.trim());
			tempMaster.setYear(year);
			tempMaster.setStatus(status.trim().toUpperCase());
			if (approval.equalsIgnoreCase("No")) {
				tempMaster.setApproval(false);
			} else {
				tempMaster.setApproval(true);
			}
			tempMaster.setUpdated(user);
			tempMaster.setUpdatedDate(date);
			templateDAO.create(tempMaster);

		}

	}

	public List<String> findUsers() {

		List<String> username= new ArrayList<String>();
		username.add(SecurityUtil.getUsername());
		List<User> u= userDAO.findAll("AND enabled=true AND organization="+SecurityUtil.getUserOrganisation());
		for(User uu:u)
		{
			if(!uu.getUsername().equals(SecurityUtil.getUsername()))
			{
			username.add(uu.getUsername());
			}
		}
		return username;
	}

	
	public String AssignUserJSON() throws JsonProcessingException {
		List<String> username= new ArrayList<String>();
		username.add(SecurityUtil.getUsername());
		List<User> u= userDAO.findAll("AND enabled=true AND organization="+SecurityUtil.getUserOrganisation());
		for(User uu:u)
		{
			if(!uu.getUsername().equals(SecurityUtil.getUsername()))
			{
			username.add(uu.getUsername());
			}
		}
		ObjectWriter ow = new ObjectMapper().writer()
				.withDefaultPrettyPrinter();
		String json = ow.writeValueAsString(username);

		return json;
	}


	public String checkpass(String pass) {
		User u =userDAO.getUser(SecurityUtil.getUsername());
		if(u.getPassword().equals(pass))
		{
			return "{\"status\":\"success\"}";
		}
		else
		{
			return "{\"status\":\"failed\"}";
		}
	}
}
