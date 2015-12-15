package com.ptas.common.service;

import java.io.ByteArrayInputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.ptas.common.dao.NarrativeLibraryDAO;
import com.ptas.common.dao.TemplateDAO;
import com.ptas.common.dao.TemplateDetailDAO;
import com.ptas.common.domain.FileUpload;
import com.ptas.common.domain.NarrativeLibrary;
import com.ptas.common.domain.TemplateDetail;
import com.ptas.common.domain.TemplateMaster;
import com.ptas.common.util.SecurityUtil;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImportExcel implements ImportService {

	@Autowired
	private TemplateDetailDAO templateDetailDAO;

	@Autowired
	private TemplateDAO templateDAO;

	@Autowired
	private NarrativeLibraryDAO narrativeLibraryDAO;

	private TemplateMaster tm;

	private List<NarrativeLibrary> nl;

	public boolean importFile(FileUpload file) throws Exception {
		boolean flag = false;
		ByteArrayInputStream bis = new ByteArrayInputStream(file.getFile()
				.getBytes());
		Workbook workbook;

		if (file.getFile().getOriginalFilename().endsWith("xls")) {
			workbook = new HSSFWorkbook(bis);
		} else if (file.getFile().getOriginalFilename().endsWith("xlsx")) {
			workbook = new XSSFWorkbook(bis);
		} else {
			throw new IllegalArgumentException(
					"Received file does not have a standard excel extension.");
		}
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		Date date = dateFormat.parse(dateFormat.format(cal.getTime()));

		if (file.getFile().getOriginalFilename()
				.equalsIgnoreCase("TemplateHeader.xlsx")
				|| file.getFile().getOriginalFilename()
						.equalsIgnoreCase("TemplateHeader.xls")) {

			XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
			XSSFRow row;
			// templateDAO.deleteTableDataTM();
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				System.out
						.println("--------------------------------hhh----------------");
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
				tempMaster.setOrg_ID(SecurityUtil.getUserOrganisation());
				tempMaster.setManufacturer(mfg.trim().toUpperCase());
				tempMaster.setCategory(category.trim());
				tempMaster.setModel(model.trim());
				tempMaster.setYear(year);
				tempMaster.setStatus(status.trim().toUpperCase());
				if(approval.equalsIgnoreCase("No"))
				{
					tempMaster.setApproval(false);
				}
				else
				{
					tempMaster.setApproval(true);
				}
				tempMaster.setUpdated(SecurityUtil.getUserId());

				tempMaster.setUpdatedDate(date);

				TemplateMaster tmm = templateDAO.find("AND templateNumber="
						+ templateNumber);
				if (tmm == null) {
					System.out.println("Insert");
					templateDAO.create(tempMaster);

				} else {
					System.out.println("update");
					tempMaster.setMrecordID(tmm.getMrecordID());
					templateDAO.update(tempMaster);
				}

			}

		} else if (file.getFile().getOriginalFilename()
				.equalsIgnoreCase("TemplateDetails.xlsx")
				|| file.getFile().getOriginalFilename()
						.equalsIgnoreCase("TemplateDetails.xls")) {

			XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
			XSSFRow row;
			XSSFRow row1 = sheet.getRow(1);
			double templateNumber = row1.getCell(0).getNumericCellValue();
			long templatenumber = (new Double(templateNumber)).longValue();
			int status = checkHeaderAndMode(templatenumber);
			List<TemplateDetail> det = new ArrayList();

			// reading excel
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				if (status == -1) {
					flag = true;
				}

				row = sheet.getRow(i);
				double tn = row.getCell(0).getNumericCellValue();
				long tempnum = (new Double(tn)).longValue();
				TemplateDetail td = new TemplateDetail();
				if (templatenumber == tempnum && status != -1) {
					// push data to list detail
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
					td.setUpdated(SecurityUtil.getUserId());
					td.setUpdatedDate(date);
					det.add(td);
					// end of if condition
				} else if (det.size() != 0 && templatenumber != tempnum
						&& status != -1) {
					if (status == 0) {
						InsertMode(det, templatenumber);
					} else if (status == 1) {
						UpdateMode(det, templatenumber);
					}
					// check status and peform insert or update
					det = new ArrayList();// After insert/update empty Arraylist
											// detail
					status = checkHeaderAndMode(tempnum);
					templatenumber = tempnum;// set new status and template
												// number
					if (status != -1) {
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
						td.setLibrary(library);
						td.setFieldName(fieldName);
						td.setValueType(ValueType.trim());
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
						td.setUpdated(SecurityUtil.getUserId());
						td.setUpdatedDate(date);
						det.add(td);
					}

					// check status!=-1 and push running loop data to array
				}
			}
			if (status == 0 && det.size() != 0) {
				InsertMode(det, templatenumber);
			} else if (status == 1 && det.size() != 0) {
				UpdateMode(det, templatenumber);
			}

		} else if (file.getFile().getOriginalFilename()
				.equalsIgnoreCase("NarrativeLibrary.xlsx")
				|| file.getFile().getOriginalFilename()
						.equalsIgnoreCase("NarrativeLibrary.xls")) {
			XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
			XSSFRow row;
			XSSFRow row1 = sheet.getRow(1);
			String lib = row1.getCell(0).getStringCellValue();
			checkMode(lib);
			for (int i = 1; i <= sheet.getLastRowNum(); i++) {
				row = sheet.getRow(i);

					NarrativeLibrary narrlib = new NarrativeLibrary();
					narrlib.setLibrary(row.getCell(0).getStringCellValue().toUpperCase());
					narrlib.setValue(row.getCell(1).getStringCellValue());
					narrlib.setoId(SecurityUtil.getUserOrganisation());
					narrativeLibraryDAO.create(narrlib);
					
				if (!lib.equalsIgnoreCase(row.getCell(0).getStringCellValue())) {
					lib = row.getCell(0).getStringCellValue();
					checkMode(lib);
				}

			}

		} else {
			throw new IllegalArgumentException(
					"Received file does not have Proper file Name.");
		}
		return flag;
	}

	public int checkHeaderAndMode(Long id) {
		tm = templateDAO.find("AND templateNumber=" + id);
		if (tm == null) {
			return -1;
		}
		Set<TemplateDetail> td = tm.getTemplateDetail();
		if (td.size() == 0) {
			return 0;
		} else {
			return 1;
		}

	}

	public void checkMode(String lib) {
		nl = narrativeLibraryDAO.findAll("AND library='" + lib + "' AND oId="
				+ SecurityUtil.getUserOrganisation());

		if (nl.size() != 0) {
			for (NarrativeLibrary nll : nl) {
				narrativeLibraryDAO.delete(nll);
			}
		}
	}

	public void InsertMode(List<TemplateDetail> tempdetl, Long tempnumm) {
		for (TemplateDetail tde : tempdetl) {
			templateDetailDAO.create(tde);
		}

	}

	public void UpdateMode(List<TemplateDetail> tempdetl, Long tempnumm) {
		Set<TemplateDetail> dtd = tm.getTemplateDetail();
		for (TemplateDetail ddd : dtd) {
			templateDetailDAO.delete(ddd);
		}
		InsertMode(tempdetl, tempnumm);
	}

}
