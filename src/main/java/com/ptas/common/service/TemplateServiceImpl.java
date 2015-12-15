package com.ptas.common.service;

import java.io.FileOutputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;
import com.ptas.common.dao.EntryHeaderDAO;
import com.ptas.common.dao.TemplateDAO;
import com.ptas.common.dao.UserDAO;
import com.ptas.common.dao.UserInfoDAO;
import com.ptas.common.domain.EntryDetails;
import com.ptas.common.domain.EntryHeader;
import com.ptas.common.domain.TemplateDetail;
import com.ptas.common.domain.TemplateMaster;
import com.ptas.common.domain.UserInfo;
import com.ptas.common.util.SecurityUtil;

@Service
@Transactional
public class TemplateServiceImpl implements TemplateService {

	@Autowired
	private TemplateDAO templateDAO;

	@Autowired
	private EntryHeaderDAO entryHeaderDAO;

	@Autowired
	private UserInfoDAO userinfoDAO;
	
	@Autowired
	private UserDAO userDAO;

	@Autowired
	private HttpServletRequest servletRequest;

	public TemplateMaster getRecordID(Long RecordID) {
		return templateDAO.findOne(RecordID);
	}

	public MfgJson findDistinct() throws Exception {
		List<String> JTHM = templateDAO.findAllDisRow(
				"AND deleted=false AND status='PUBLISHED'AND org_ID="+SecurityUtil.getUserOrganisation(), "manufacturer");
		List<String> JTHTT = templateDAO.findAllDisRow(
				"AND deleted=false AND status='PUBLISHED'AND org_ID="+SecurityUtil.getUserOrganisation(), "temp_type");
		MfgJson mt = new MfgJson();
		mt.setManufacturer(JTHM);
		mt.setTemplateType(JTHTT);

		return mt;

	}

	public String findTemplateName(String m, String t) throws Exception {

		List<TemplateMaster> JTHM = templateDAO.findAll("AND deleted=false"
				+ " AND status='PUBLISHED' " +"AND manufacturer='" + m
				+ "' AND temp_type='" + t + "'"+"AND org_ID="+SecurityUtil.getUserOrganisation());
		List<TemplateMaster> templateNameID = new ArrayList<TemplateMaster>();
		for (TemplateMaster tm : JTHM) {
			TemplateMaster jobj = new TemplateMaster();
			jobj.setTemp_name(tm.getTemp_name());
			jobj.setMrecordID(tm.getMrecordID());
			templateNameID.add(jobj);

		}
		ObjectWriter ow = new ObjectMapper().writer()
				.withDefaultPrettyPrinter();
		String j = ow.writeValueAsString(templateNameID);

		return j;

	}

	public String findTemplateMainJson(String m, String t, String tn)
			throws Exception {

		TemplateMaster JTHM = templateDAO.find("AND deleted=false"
				+ " AND status='PUBLISHED' " + "AND manufacturer='" + m
				+ "' AND temp_type='" + t + "' AND temp_name='" + tn
				+ "' AND org_ID='" + SecurityUtil.getUserOrganisation() + "'");
		Collection<TemplateDetail> mj = JTHM.getTemplateDetail();
		List<TemplateDetail> section = new ArrayList<TemplateDetail>();
		List<TemplateDetail> group = new ArrayList<TemplateDetail>();
		List<TemplateDetail> controls = new ArrayList<TemplateDetail>();

		for (TemplateDetail td : mj) {
			if (td.getGroup().isEmpty() && td.getSection().isEmpty()) {
				section.add(td);

			} else if (td.getGroup().isEmpty() && !td.getSection().isEmpty()) {
				group.add(td);

			} else {
				controls.add(td);

			}
		}
		ArrayList<Section> s = new ArrayList<Section>();
		for (TemplateDetail ts : section) {
			Section sec = new Section();
			sec.setGroup(ts.getCheckItem());
			sec.setId(ts.getDrecordID());
			ArrayList<JSONTemplete> g = new ArrayList<JSONTemplete>();
			for (TemplateDetail tg : group) {
				if (ts.getCheckItem().equalsIgnoreCase(tg.getSection())) {
					JSONTemplete grp = new JSONTemplete();
					grp.setGroup(tg.getCheckItem());
					grp.setId(tg.getDrecordID());
					for (TemplateDetail FQarryBody : controls) {
						if (ts.getCheckItem().equalsIgnoreCase(
								FQarryBody.getSection())
								&& tg.getCheckItem().equalsIgnoreCase(
										FQarryBody.getGroup())) {
							Body bd = new Body();
							bd.setId(FQarryBody.getDrecordID());
							bd.setCheckItem(FQarryBody.getCheckItem());
							bd.setValueType(FQarryBody.getValueType());
							if (!FQarryBody.getValueOption().isEmpty()) {
								ArrayList<String> ValueOption = new ArrayList<String>();

								String domain = FQarryBody.getValueOption();
								String[] strArray = domain.split(",");
								for (String str : strArray) {
									ValueOption.add(str);
								}
								bd.setValueOption(ValueOption);
							}
							bd.setLibrary(FQarryBody.getLibrary());
							bd.setFieldName(FQarryBody.getFieldName());
							bd.setTextInstruction(FQarryBody
									.getTextInstruction());
							bd.setVideoInstruction(FQarryBody
									.getVideoInstruction());
							bd.setPictureInstruction(FQarryBody
									.getPictureInstruction());
							ArrayList<String> JBody = new ArrayList<String>();

							if (FQarryBody.isAllcomment()) {
								JBody.add("comment");
							}

							if (FQarryBody.isAllowPic()) {
								JBody.add("Pic");
							}
							if (FQarryBody.isAllowVideo()) {
								JBody.add("video");
							}
							bd.setControls(JBody);
							grp.addBody(bd);
						}
					}
					g.add(grp);
				}
				sec.setGroupItems(g);
			}
			s.add(sec);
		}

		ObjectWriter ow = new ObjectMapper().writer()
				.withDefaultPrettyPrinter();
		String j = ow.writeValueAsString(s);
		return j;

	}

	public void findReportContent(Long id, String filePath) throws Exception {

		EntryHeader entryheader = entryHeaderDAO.findOne(id);
		// Set<EntryDetails> led = entryheader.getEntryDetails();

		Document document = new Document(PageSize.LETTER);
		PdfWriter pdfWriter = PdfWriter.getInstance(document,
				new FileOutputStream(filePath));

		// TableHeader event = new TableHeader();
		// pdfWriter.setPageEvent(event);

		document.open();
		Paragraph sp = new Paragraph(Chunk.NEWLINE);
		document.add(sp);
		Paragraph p = new Paragraph("Inspection Report", FontFactory.getFont(
				FontFactory.HELVETICA, 40, Font.BOLD, new CMYKColor(255, 200,
						0, 0)));
		p.setAlignment(Element.ALIGN_CENTER);
		document.add(p);
		Paragraph customerName = new Paragraph(entryheader.getCategory(),
				FontFactory.getFont(FontFactory.HELVETICA, 25, Font.BOLD,
						new CMYKColor(255, 200, 0, 0)));
		customerName.setAlignment(Element.ALIGN_CENTER);
		document.add(customerName);
		//document.add(Chunk.NEWLINE);
		document.add(Chunk.NEWLINE);
		Paragraph propaddh = new Paragraph("Property Address",
				FontFactory.getFont(FontFactory.HELVETICA, 20));
		propaddh.setAlignment(Element.ALIGN_CENTER);
		document.add(propaddh);

		Paragraph propadd = new Paragraph(entryheader.getModel(),
				FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD));
		propadd.setAlignment(Element.ALIGN_CENTER);
		document.add(propadd);
		//document.add(Chunk.NEWLINE);
		if(entryheader.getImage()!=null)
		{
		String headerImage = servletRequest.getContextPath()
				+ "/resources/UploadedImage/" + entryheader.getImage();
		
		URL reconstructedURLImage = new URL(
				servletRequest.getScheme(),
				servletRequest.getServerName(),
				servletRequest.getServerPort(), headerImage);

		String linkadd = reconstructedURLImage.toString();
		Image headerimage = Image.getInstance(linkadd);
		headerimage.setAlignment(Element.ALIGN_CENTER);
		document.add(headerimage);
		//document.add(Chunk.NEWLINE);
		}
		else
		{
			document.add(Chunk.NEWLINE);
			document.add(Chunk.NEWLINE);
			
		}
		
		Paragraph preparedby = new Paragraph("Prepared By",
				FontFactory.getFont(FontFactory.HELVETICA, 20));
		preparedby.setAlignment(Element.ALIGN_CENTER);
		document.add(preparedby);

		UserInfo ui = userinfoDAO.findOne(entryheader.getUid());
		Paragraph ppby = new Paragraph(ui.getFirstName() + "  "
				+ ui.getLastName(), FontFactory.getFont(FontFactory.HELVETICA,
				20, Font.BOLD));
		ppby.setAlignment(Element.ALIGN_CENTER);
		document.add(ppby);
		Paragraph inson = new Paragraph("Inspected On", FontFactory.getFont(
				FontFactory.HELVETICA, 20));
		inson.setAlignment(Element.ALIGN_CENTER);
		document.add(inson);
		Paragraph inspon = new Paragraph(entryheader.getUpdatedDate(),
				FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD));
		inspon.setAlignment(Element.ALIGN_CENTER);
		document.add(inspon);
		document.newPage();// add new page
		// general info
		LineSeparator UNDERLINE = new LineSeparator(1, 100, null,
				Element.ALIGN_CENTER, -5);
		Paragraph gi = new Paragraph("General Information",
				FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD,
						new CMYKColor(255, 155, 0, 0)));
		document.add(gi);
		document.add(UNDERLINE);
		document.add(Chunk.NEWLINE);
		Paragraph cont1 = new Paragraph(
				"This confidential report is furnished for the use of the client only. It is not intended to be relied upon for any purpose by any other party not named on the report and Inspection Agreement.",
				FontFactory.getFont(FontFactory.HELVETICA, 12));
		Paragraph cont2 = new Paragraph(
				"This inspection was performed in accordance with and under the terms of a Home Inspection Agreement. The agreement was signed and agreed upon before the preparation of this report and a signed copy of the agreement is available upon request.",
				FontFactory.getFont(FontFactory.HELVETICA, 12));
		document.add(cont1);
		document.add(Chunk.NEWLINE);
		document.add(cont2);
		document.add(Chunk.NEWLINE);
		//
		// Creates a table with four column. The first rows
		// will have cell 1 to cell 4.
		//
		PdfPTable table = new PdfPTable(2);
		
		PdfPCell cellOne = new PdfPCell();
		Chunk dh = new Chunk("Date:  ", FontFactory.getFont(
				FontFactory.HELVETICA, 14, Font.BOLD));
		Chunk datem = new Chunk(entryheader.getUpdatedDate(), FontFactory.getFont(
				FontFactory.HELVETICA, 13));
		cellOne.addElement(dh);
		cellOne.addElement(datem);
		cellOne.setBorder(Rectangle.NO_BORDER);
		table.addCell(cellOne);//cell one complete
		
		PdfPCell cellTwo = new PdfPCell();
		Chunk inspectorh = new Chunk("Inspector:  ", FontFactory.getFont(
				FontFactory.HELVETICA, 14, Font.BOLD));
		Chunk inspector = new Chunk(ui.getFirstName() + "  " + ui.getLastName(), FontFactory.getFont(
				FontFactory.HELVETICA, 13));
		cellTwo.addElement(inspectorh);
		cellTwo.addElement(inspector);
		cellTwo.setBorder(Rectangle.NO_BORDER);
		table.addCell(cellTwo);//cell Two complete
		PdfPCell cellThree = new PdfPCell();
		Chunk customerNameh = new Chunk("Customer Name:  ", FontFactory.getFont(
				FontFactory.HELVETICA, 14, Font.BOLD));
		Chunk custonernam = new Chunk(entryheader.getCategory(), FontFactory.getFont(
				FontFactory.HELVETICA, 13));
		cellThree.addElement(customerNameh);
		cellThree.addElement(custonernam);
		cellThree.setBorder(Rectangle.NO_BORDER);
		table.addCell(cellThree);// cellThree complete
		
		PdfPCell cellFour = new PdfPCell();
		Chunk propaddressh = new Chunk("Property Address:  ", FontFactory.getFont(
				FontFactory.HELVETICA, 14, Font.BOLD));
		Chunk propaddress = new Chunk(entryheader.getModel(), FontFactory.getFont(
				FontFactory.HELVETICA, 13));
		cellFour.addElement(propaddressh);
		cellFour.addElement(propaddress);
		cellFour.setBorder(Rectangle.NO_BORDER);
		table.addCell(cellFour);// cellFour complete
		
		PdfPCell cellFive = new PdfPCell();
		Chunk tobh = new Chunk("Type Of Building:  ", FontFactory.getFont(
				FontFactory.HELVETICA, 14, Font.BOLD));
		Chunk tob = new Chunk(entryheader.getManufacturer(), FontFactory.getFont(
				FontFactory.HELVETICA, 13));
		cellFive.addElement(tobh);
		cellFive.addElement(tob);
		cellFive.setBorder(Rectangle.NO_BORDER);
		table.addCell(cellFive);// cellFive complete
		
		PdfPCell cellSix = new PdfPCell();
		Chunk ybh = new Chunk("Year Built:  ", FontFactory.getFont(
				FontFactory.HELVETICA, 14, Font.BOLD));
		Chunk yb = new Chunk(entryheader.getYear(), FontFactory.getFont(
				FontFactory.HELVETICA, 13));
		cellSix.addElement(ybh);
		cellSix.addElement(yb);
		cellSix.setBorder(Rectangle.NO_BORDER);
		table.addCell(cellSix);// cellSix complete
		
		table.setWidthPercentage(100);
		document.add(table);
		document.newPage();// add new page
		Paragraph itir = new Paragraph("Interpreting the Inspection Results",
				FontFactory.getFont(FontFactory.HELVETICA, 20, Font.BOLD,
						new CMYKColor(255, 155, 0, 0)));
		itir.setAlignment(Element.ALIGN_CENTER);
		document.add(itir);
		document.add(UNDERLINE);
		document.add(Chunk.NEWLINE);
		Paragraph itird = new Paragraph(
				"Each item or area inspected will be marked with a finding, which represents the inspection result for that item. The following descriptions represent an explanation for each of the inspection findings.",
				FontFactory.getFont(FontFactory.HELVETICA, 9));
		document.add(itird);
		document.add(Chunk.NEWLINE);
		
		
		Chunk iaf = new Chunk("Inspected - Appears Functional:", FontFactory.getFont(
				FontFactory.HELVETICA, 12, Font.BOLD));
		
		Paragraph iafd = new Paragraph(
				"The item, component, or unit was visually observed, and if no other comments were made, then the item appeared to be functioning as intended, allowing for normal wear and tear.",
				FontFactory.getFont(FontFactory.HELVETICA, 9));
		document.add(iaf);
		document.add(iafd);
		document.add(Chunk.NEWLINE);
		
		Chunk rrr = new Chunk("Repair or Replacement Recommended:", FontFactory.getFont(
				FontFactory.HELVETICA, 12, Font.BOLD));
		
		Paragraph rrrd = new Paragraph(
				"The item, component or unit was visually observed, and is not functioning as intended or needs further inspection by a qualified specialist. Items, components or units that can be repaired to satisfactory condition may not need replacement.",
				FontFactory.getFont(FontFactory.HELVETICA, 9));
		document.add(rrr);
		document.add(rrrd);
		document.add(Chunk.NEWLINE);
		
		Chunk gmt = new Chunk("General Maintenance Item:", FontFactory.getFont(
				FontFactory.HELVETICA, 12, Font.BOLD));
		
		Paragraph gmtd = new Paragraph(
				"These are repairs that, in the opinion of the inspector, are regular maintenance items typical for all homes. Repair to these items is not urgent, but should be performed in the near future.",
				FontFactory.getFont(FontFactory.HELVETICA, 9));
		document.add(gmt);
		document.add(gmtd);
		document.add(Chunk.NEWLINE);
		
		Chunk sn = new Chunk("Service Needed:", FontFactory.getFont(
				FontFactory.HELVETICA, 12, Font.BOLD));
		
		Paragraph snd = new Paragraph(
				"The item, component, or unit is functioning, but a service check-up is recommended to optimize performance.",
				FontFactory.getFont(FontFactory.HELVETICA, 9));
		document.add(sn);
		document.add(snd);
		document.add(Chunk.NEWLINE);
		
		Chunk lins = new Chunk("Limited Inspection:", FontFactory.getFont(
				FontFactory.HELVETICA, 12, Font.BOLD));
		
		Paragraph linsd = new Paragraph(
				"The item, component, or unit was not fully inspected, and some form of limitation is preventing a complete inspection of the item/area. The report will state a reason for the limited inspecting of the item.",
				FontFactory.getFont(FontFactory.HELVETICA, 9));
		document.add(lins);
		document.add(linsd);
		document.add(Chunk.NEWLINE);
		
		Chunk nins = new Chunk("Not Inspected :", FontFactory.getFont(
				FontFactory.HELVETICA, 12, Font.BOLD));
		
		Paragraph ninsd = new Paragraph(
				"The item, component, or unit was not inspected, and no representations of whether or not it was functioning as intended are made. The report will state a reason for not inspecting the item.",
				FontFactory.getFont(FontFactory.HELVETICA, 9));
		document.add(nins);
		document.add(ninsd);
		document.add(Chunk.NEWLINE);
		Chunk npre = new Chunk("Not Present:", FontFactory.getFont(
				FontFactory.HELVETICA, 12, Font.BOLD));
		
		Paragraph npred = new Paragraph(
				"The item, component or unit is not in this home or building.",
				FontFactory.getFont(FontFactory.HELVETICA, 9));
		document.add(npre);
		document.add(npred);
		
		document.newPage();// add new page
		Paragraph toc = new Paragraph("Table Of Content", FontFactory.getFont(
				FontFactory.HELVETICA, 30, Font.BOLD, new CMYKColor(255, 155,
						0, 0)));
		toc.setAlignment(Element.ALIGN_CENTER);
		document.add(toc);
		document.add(Chunk.NEWLINE);
		for (EntryDetails ed : entryheader.getEntryDetails()) {

			if (ed.geteType().equalsIgnoreCase("Section")) {

				Paragraph section = new Paragraph(ed.geteCheckItem(),
						FontFactory.getFont(FontFactory.HELVETICA, 15,
								Font.BOLD));
				document.add(section);
			}
		}
		document.newPage();// add new page

		// Detail data starts here
		boolean flag1 = false;
		boolean flag2 = false;
		int imagecount = 0;

		for (EntryDetails ed : entryheader.getEntryDetails()) {
			//System.out.println(pdfWriter.getPageNumber());
			document.add(Chunk.NEWLINE);

			if (ed.geteType().equalsIgnoreCase("Section")) {
				if (flag1) {
					document.newPage();
				}
				flag1 = true;
				flag2 = false;
				Paragraph section = new Paragraph(ed.geteCheckItem(),
						FontFactory.getFont(FontFactory.HELVETICA, 20,
								Font.BOLD, new CMYKColor(255, 155, 0, 0)));
				document.add(section);

				document.add(UNDERLINE);

			} else if (ed.geteType().equalsIgnoreCase("Group")) {

				if (flag2) {
					document.newPage();
				}
				flag2 = true;
				Paragraph group = new Paragraph(" " + ed.geteCheckItem(),
						FontFactory.getFont(FontFactory.HELVETICA, 17,
								Font.BOLD, new CMYKColor(255, 155, 0, 0)));
				document.add(group);
				document.add(UNDERLINE);

			} else {
				Paragraph cont = new Paragraph("      " + ed.geteCheckItem(),
						FontFactory.getFont(FontFactory.HELVETICA, 15,
								Font.BOLD));
				document.add(cont);
				PdfPTable tableImage = new PdfPTable(1);
				PdfPCell cellBlankRow = new PdfPCell(new Phrase(" "));
				cellBlankRow.setBorder(Rectangle.NO_BORDER);
				if (ed.getEvaluetype().equalsIgnoreCase("Edit")) {

					Paragraph v2 = new Paragraph("          "
							+ ed.geteValue().toUpperCase(),
							FontFactory.getFont(FontFactory.HELVETICA, 13));

					Paragraph c2 = new Paragraph("          "
							+ ed.geteComment().toUpperCase(),
							FontFactory.getFont(FontFactory.HELVETICA, 13));

					
					if(!ed.geteValue().equals(""))
					{
						document.add(v2);
						document.add(Chunk.NEWLINE);
					}
					if(!ed.geteComment().equals(""))
					{
						document.add(c2);
						document.add(Chunk.NEWLINE);
					}

					if (!ed.getImage1().equals("")) {
						String file = servletRequest.getContextPath()
								+ "/resources/UploadedImage/" + ed.getImage1();
						URL reconstructedURL = new URL(
								servletRequest.getScheme(),
								servletRequest.getServerName(),
								servletRequest.getServerPort(), file);

						String a = reconstructedURL.toString();
						Image image = Image.getInstance(a);
						//image.scalePercent(50f);
						PdfPCell cellpdf= new PdfPCell(image);
						cellpdf.setBorder(Rectangle.NO_BORDER);
						tableImage.addCell(cellBlankRow);
						tableImage.addCell(cellpdf);
						tableImage.addCell(cellBlankRow);

					}
					
					if (!ed.getImage2().equals("")) {

						String file = servletRequest.getContextPath()
								+ "/resources/UploadedImage/" + ed.getImage2();
						URL reconstructedURL = new URL(
								servletRequest.getScheme(),
								servletRequest.getServerName(),
								servletRequest.getServerPort(), file);

						String a = reconstructedURL.toString();
						Image image = Image.getInstance(a);
						//image.scalePercent(50f);
						PdfPCell cellpdf= new PdfPCell(image);
						cellpdf.setBorder(Rectangle.NO_BORDER);
						tableImage.addCell(cellpdf);
						tableImage.addCell(cellBlankRow);
					}
					
					if (!ed.getImage3().equals("")) {

						String file = servletRequest.getContextPath()
								+ "/resources/UploadedImage/" + ed.getImage3();
						URL reconstructedURL = new URL(
								servletRequest.getScheme(),
								servletRequest.getServerName(),
								servletRequest.getServerPort(), file);

						String a = reconstructedURL.toString();
						Image image = Image.getInstance(a);
						//image.scalePercent(50f);
						PdfPCell cellpdf= new PdfPCell(image);
						cellpdf.setBorder(Rectangle.NO_BORDER);
						tableImage.addCell(cellpdf);
							tableImage.addCell(cellBlankRow);
					}
					
					if (!ed.getImage4().equals("")) {

						String file = servletRequest.getContextPath()
								+ "/resources/UploadedImage/" + ed.getImage4();
						URL reconstructedURL = new URL(
								servletRequest.getScheme(),
								servletRequest.getServerName(),
								servletRequest.getServerPort(), file);

						String a = reconstructedURL.toString();
						
						Image image = Image.getInstance(a);
						//image.scalePercent(50f);
						PdfPCell cellpdf= new PdfPCell(image);
						cellpdf.setBorder(Rectangle.NO_BORDER);
						tableImage.addCell(cellpdf);
							tableImage.addCell(cellBlankRow);
					}
					
					if (!ed.getImage5().equals("")) {

						String file = servletRequest.getContextPath()
								+ "/resources/UploadedImage/" + ed.getImage5();
						URL reconstructedURL = new URL(
								servletRequest.getScheme(),
								servletRequest.getServerName(),
								servletRequest.getServerPort(), file);

						String a = reconstructedURL.toString();
						Image image = Image.getInstance(a);
						//image.scalePercent(50f);
						PdfPCell cellpdf= new PdfPCell(image);
						cellpdf.setBorder(Rectangle.NO_BORDER);
						tableImage.addCell(cellpdf);
							tableImage.addCell(cellBlankRow);
						
					}
					document.add(tableImage);

				} else if (ed.getEvaluetype().equalsIgnoreCase("ListChoice")) {
					Chunk v2;
					if (ed.geteValue().equalsIgnoreCase("Select")) {
						v2 = new Chunk("          No Value Selected",
								FontFactory.getFont(FontFactory.HELVETICA, 13));

					} else {
						v2 = new Chunk("          "+ed.geteValue().toUpperCase(),
								FontFactory.getFont(FontFactory.HELVETICA, 13));

					}

					Chunk c2 = new Chunk("          "+ed.geteComment().toUpperCase(),
							FontFactory.getFont(FontFactory.HELVETICA, 13));
					if(!ed.geteValue().equals(""))
					{
						document.add(v2);
						document.add(Chunk.NEWLINE);
					}
					if(!ed.geteComment().equals(""))
					{
						document.add(c2);
						document.add(Chunk.NEWLINE);
					}
					
					if (!ed.getImage1().equals("")) {
						String file = servletRequest.getContextPath()
								+ "/resources/UploadedImage/" + ed.getImage1();
						URL reconstructedURL = new URL(
								servletRequest.getScheme(),
								servletRequest.getServerName(),
								servletRequest.getServerPort(), file);

						String a = reconstructedURL.toString();
						Image image = Image.getInstance(a);
						//image.scalePercent(50f);
						PdfPCell cellpdf= new PdfPCell(image);
						cellpdf.setBorder(Rectangle.NO_BORDER);
						tableImage.addCell(cellBlankRow);
						tableImage.addCell(cellpdf);
							tableImage.addCell(cellBlankRow);
						

					}
					
					if (!ed.getImage2().equals("")) {

						String file = servletRequest.getContextPath()
								+ "/resources/UploadedImage/" + ed.getImage2();
						URL reconstructedURL = new URL(
								servletRequest.getScheme(),
								servletRequest.getServerName(),
								servletRequest.getServerPort(), file);

						String a = reconstructedURL.toString();
						Image image = Image.getInstance(a);
						//image.scalePercent(50f);
						PdfPCell cellpdf= new PdfPCell(image);
						cellpdf.setBorder(Rectangle.NO_BORDER);
						
						tableImage.addCell(cellpdf);
							tableImage.addCell(cellBlankRow);
					}
					
					if (!ed.getImage3().equals("")) {

						String file = servletRequest.getContextPath()
								+ "/resources/UploadedImage/" + ed.getImage3();
						URL reconstructedURL = new URL(
								servletRequest.getScheme(),
								servletRequest.getServerName(),
								servletRequest.getServerPort(), file);

						String a = reconstructedURL.toString();
						Image image = Image.getInstance(a);
						//image.scalePercent(50f);
						PdfPCell cellpdf= new PdfPCell(image);
						cellpdf.setBorder(Rectangle.NO_BORDER);
						tableImage.addCell(cellpdf);
							tableImage.addCell(cellBlankRow);
					}
					
					if (!ed.getImage4().equals("")) {

						String file = servletRequest.getContextPath()
								+ "/resources/UploadedImage/" + ed.getImage4();
						URL reconstructedURL = new URL(
								servletRequest.getScheme(),
								servletRequest.getServerName(),
								servletRequest.getServerPort(), file);

						String a = reconstructedURL.toString();
						Image image = Image.getInstance(a);
						//image.scalePercent(50f);
						PdfPCell cellpdf= new PdfPCell(image);
						cellpdf.setBorder(Rectangle.NO_BORDER);
						tableImage.addCell(cellpdf);
							tableImage.addCell(cellBlankRow);
					}
					
					if (!ed.getImage5().equals("")) {

						String file = servletRequest.getContextPath()
								+ "/resources/UploadedImage/" + ed.getImage5();
						URL reconstructedURL = new URL(
								servletRequest.getScheme(),
								servletRequest.getServerName(),
								servletRequest.getServerPort(), file);

						String a = reconstructedURL.toString();
						Image image = Image.getInstance(a);
						//image.scalePercent(50f);
						PdfPCell cellpdf= new PdfPCell(image);
						cellpdf.setBorder(Rectangle.NO_BORDER);
						tableImage.addCell(cellpdf);
							tableImage.addCell(cellBlankRow);
					}
					document.add(tableImage);

				} else if (ed.getEvaluetype().equalsIgnoreCase("Numeric")) {
	
					Chunk v2 = new Chunk("          "+ed.geteValue().toUpperCase(),
							FontFactory.getFont(FontFactory.HELVETICA, 13));

					Chunk c2 = new Chunk("          "+ed.geteComment().toUpperCase(),
							FontFactory.getFont(FontFactory.HELVETICA, 13));
					if(!ed.geteValue().equals(""))
					{
						document.add(v2);
						document.add(Chunk.NEWLINE);
					}
					if(!ed.geteComment().equals(""))
					{
						document.add(c2);
						document.add(Chunk.NEWLINE);
					}
					if (!ed.getImage1().equals("")) {
						String file = servletRequest.getContextPath()
								+ "/resources/UploadedImage/" + ed.getImage1();
						URL reconstructedURL = new URL(
								servletRequest.getScheme(),
								servletRequest.getServerName(),
								servletRequest.getServerPort(), file);

						String a = reconstructedURL.toString();
						Image image = Image.getInstance(a);
							//image.scalePercent(50f);
						tableImage.addCell(cellBlankRow);
						tableImage.addCell(new PdfPCell(image));
							tableImage.addCell(cellBlankRow);

					}
					
					if (!ed.getImage2().equals("")) {

						String file = servletRequest.getContextPath()
								+ "/resources/UploadedImage/" + ed.getImage2();
						URL reconstructedURL = new URL(
								servletRequest.getScheme(),
								servletRequest.getServerName(),
								servletRequest.getServerPort(), file);

						String a = reconstructedURL.toString();
						Image image = Image.getInstance(a);
						//image.scalePercent(50f);
						PdfPCell cellpdf= new PdfPCell(image);
						cellpdf.setBorder(Rectangle.NO_BORDER);
						tableImage.addCell(cellpdf);
							tableImage.addCell(cellBlankRow);
					}
					
					if (!ed.getImage3().equals("")) {

						String file = servletRequest.getContextPath()
								+ "/resources/UploadedImage/" + ed.getImage3();
						URL reconstructedURL = new URL(
								servletRequest.getScheme(),
								servletRequest.getServerName(),
								servletRequest.getServerPort(), file);

						String a = reconstructedURL.toString();
						Image image = Image.getInstance(a);
						//image.scalePercent(50f);
						PdfPCell cellpdf= new PdfPCell(image);
						cellpdf.setBorder(Rectangle.NO_BORDER);
						tableImage.addCell(cellpdf);
							tableImage.addCell(cellBlankRow);
					}
					
					if (!ed.getImage4().equals("")) {

						String file = servletRequest.getContextPath()
								+ "/resources/UploadedImage/" + ed.getImage4();
						URL reconstructedURL = new URL(
								servletRequest.getScheme(),
								servletRequest.getServerName(),
								servletRequest.getServerPort(), file);

						String a = reconstructedURL.toString();
						Image image = Image.getInstance(a);
						//image.scalePercent(50f);
						PdfPCell cellpdf= new PdfPCell(image);
						cellpdf.setBorder(Rectangle.NO_BORDER);
						tableImage.addCell(cellpdf);
							tableImage.addCell(cellBlankRow);
					}
					
					if (!ed.getImage5().equals("")) {

						String file = servletRequest.getContextPath()
								+ "/resources/UploadedImage/" + ed.getImage5();
						URL reconstructedURL = new URL(
								servletRequest.getScheme(),
								servletRequest.getServerName(),
								servletRequest.getServerPort(), file);

						String a = reconstructedURL.toString();
						Image image = Image.getInstance(a);
						//image.scalePercent(50f);
						PdfPCell cellpdf= new PdfPCell(image);
						cellpdf.setBorder(Rectangle.NO_BORDER);
						tableImage.addCell(cellpdf);
							tableImage.addCell(cellBlankRow);
					}
					document.add(tableImage);
					

				} else if (ed.getEvaluetype().equalsIgnoreCase("Checkbox")) {

					Chunk v2 = new Chunk("          "+ed.geteValue().toUpperCase(),
							FontFactory.getFont(FontFactory.HELVETICA, 13));

					Chunk c2 = new Chunk("          "+ed.geteComment().toUpperCase(),
							FontFactory.getFont(FontFactory.HELVETICA, 13));
					if(!ed.geteValue().equals(""))
					{
						document.add(v2);
						document.add(Chunk.NEWLINE);
					}
					if(!ed.geteComment().equals(""))
					{
						document.add(c2);
						document.add(Chunk.NEWLINE);
					}
					if (!ed.getImage1().equals("")) {
						String file = servletRequest.getContextPath()
								+ "/resources/UploadedImage/" + ed.getImage1();
						URL reconstructedURL = new URL(
								servletRequest.getScheme(),
								servletRequest.getServerName(),
								servletRequest.getServerPort(), file);

						String a = reconstructedURL.toString();
						Image image = Image.getInstance(a);
							//image.scalePercent(50f);
						tableImage.addCell(cellBlankRow);
						tableImage.addCell(new PdfPCell(image));
							tableImage.addCell(cellBlankRow);

					}
					
					if (!ed.getImage2().equals("")) {

						String file = servletRequest.getContextPath()
								+ "/resources/UploadedImage/" + ed.getImage2();
						URL reconstructedURL = new URL(
								servletRequest.getScheme(),
								servletRequest.getServerName(),
								servletRequest.getServerPort(), file);

						String a = reconstructedURL.toString();
						Image image = Image.getInstance(a);
						//image.scalePercent(50f);
						PdfPCell cellpdf= new PdfPCell(image);
						cellpdf.setBorder(Rectangle.NO_BORDER);
						tableImage.addCell(cellpdf);
							tableImage.addCell(cellBlankRow);
					}
					
					if (!ed.getImage3().equals("")) {

						String file = servletRequest.getContextPath()
								+ "/resources/UploadedImage/" + ed.getImage3();
						URL reconstructedURL = new URL(
								servletRequest.getScheme(),
								servletRequest.getServerName(),
								servletRequest.getServerPort(), file);

						String a = reconstructedURL.toString();
						Image image = Image.getInstance(a);
						//image.scalePercent(50f);
						PdfPCell cellpdf= new PdfPCell(image);
						cellpdf.setBorder(Rectangle.NO_BORDER);
						tableImage.addCell(cellpdf);
							tableImage.addCell(cellBlankRow);
					}
					
					if (!ed.getImage4().equals("")) {

						String file = servletRequest.getContextPath()
								+ "/resources/UploadedImage/" + ed.getImage4();
						URL reconstructedURL = new URL(
								servletRequest.getScheme(),
								servletRequest.getServerName(),
								servletRequest.getServerPort(), file);

						String a = reconstructedURL.toString();
						Image image = Image.getInstance(a);
						//image.scalePercent(50f);
						PdfPCell cellpdf= new PdfPCell(image);
						cellpdf.setBorder(Rectangle.NO_BORDER);
						tableImage.addCell(cellpdf);
							tableImage.addCell(cellBlankRow);
					}
					
					if (!ed.getImage5().equals("")) {

						String file = servletRequest.getContextPath()
								+ "/resources/UploadedImage/" + ed.getImage5();
						URL reconstructedURL = new URL(
								servletRequest.getScheme(),
								servletRequest.getServerName(),
								servletRequest.getServerPort(), file);

						String a = reconstructedURL.toString();
						Image image = Image.getInstance(a);
						//image.scalePercent(50f);
						PdfPCell cellpdf= new PdfPCell(image);
						cellpdf.setBorder(Rectangle.NO_BORDER);
						tableImage.addCell(cellpdf);
							tableImage.addCell(cellBlankRow);
					}
					document.add(tableImage);


				}

			}

		}
		document.close();

	}

	public TemplateMaster findByID(Long id) {

		return templateDAO.findOne(id);
	}

	public TemplateMaster findOne(String idTemplateType, String idValue)
			throws Exception {
		return templateDAO.findOne(idTemplateType, idValue);
	}

	public List<TemplateMaster> findAll() {
		return templateDAO.findAll();
	}

	public List<TemplateMaster> findAll(String andClause) {
		return templateDAO.findAll(andClause);
	}

	public void create(TemplateMaster entity) {
		templateDAO.create(entity);
	}

	public void update(TemplateMaster templatemaster) {

		try {
			templatemaster.setUpdated(SecurityUtil.getUserId());
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Calendar cal = Calendar.getInstance();
			Date date;
			date = dateFormat.parse(dateFormat.format(cal.getTime()));
			templatemaster.setUpdatedDate(date);
			templateDAO.update(templatemaster);
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

	public void delete(TemplateMaster entity) {
		templateDAO.delete(entity);
	}

	public void deleteById(Long entityId) {
		templateDAO.deleteById(entityId);
	}

	public TemplateMaster findOne(Long id) {
		return templateDAO.findOne(id);
	}


	public TemplateMaster findTemplate(String manufacturer,
			String templateType, String templatename) {
		TemplateMaster JTHM = templateDAO.find("AND deleted=false"
				+ " AND status='PUBLISHED' " + "AND manufacturer='" + manufacturer
				+ "' AND temp_type='" +templateType + "' AND temp_name='" + templatename
				+ "' AND org_ID='" + SecurityUtil.getUserOrganisation() + "'");
		return JTHM;
	}

	public String findOfflineJson() throws Exception {
		
		List<TemplateMaster> JTHM = templateDAO.findAll("AND deleted=false AND status='PUBLISHED' AND org_ID='" + SecurityUtil.getUserOrganisation()+"'");
		List<OfflineJson> aoj= new ArrayList<OfflineJson>();
		for(TemplateMaster templateMaster:JTHM)
		{
			OfflineJson oj =new OfflineJson(); 
			oj.setManufacturer(templateMaster.getManufacturer());
			oj.setTemplateName(templateMaster.getTemp_name());
			oj.setTemplateType(templateMaster.getTemp_type());
			oj.setTemplateId(templateMaster.getMrecordID());
			ArrayList<Section> s= findTemplateData(templateMaster);
			oj.setJson(s);
			aoj.add(oj);
		}
		ObjectWriter ow = new ObjectMapper().writer()
				.withDefaultPrettyPrinter();
		String offjson = ow.writeValueAsString(aoj);
		return offjson;

	}

	public ArrayList<Section> findTemplateData(TemplateMaster JTHM)
			throws Exception {

		Collection<TemplateDetail> mj = JTHM.getTemplateDetail();
		List<TemplateDetail> section = new ArrayList<TemplateDetail>();
		List<TemplateDetail> group = new ArrayList<TemplateDetail>();
		List<TemplateDetail> controls = new ArrayList<TemplateDetail>();

		for (TemplateDetail td : mj) {
			if (td.getGroup().isEmpty() && td.getSection().isEmpty()) {
				section.add(td);

			} else if (td.getGroup().isEmpty() && !td.getSection().isEmpty()) {
				group.add(td);

			} else {
				controls.add(td);

			}
		}
		ArrayList<Section> s = new ArrayList<Section>();
		for (TemplateDetail ts : section) {
			Section sec = new Section();
			sec.setGroup(ts.getCheckItem());
			sec.setId(ts.getDrecordID());
			ArrayList<JSONTemplete> g = new ArrayList<JSONTemplete>();
			for (TemplateDetail tg : group) {
				if (ts.getCheckItem().equalsIgnoreCase(tg.getSection())) {
					JSONTemplete grp = new JSONTemplete();
					grp.setGroup(tg.getCheckItem());
					grp.setId(tg.getDrecordID());
					for (TemplateDetail FQarryBody : controls) {
						if (ts.getCheckItem().equalsIgnoreCase(
								FQarryBody.getSection())
								&& tg.getCheckItem().equalsIgnoreCase(
										FQarryBody.getGroup())) {
							Body bd = new Body();
							bd.setId(FQarryBody.getDrecordID());
							bd.setCheckItem(FQarryBody.getCheckItem());
							bd.setValueType(FQarryBody.getValueType());
							if (!FQarryBody.getValueOption().isEmpty()) {
								ArrayList<String> ValueOption = new ArrayList<String>();

								String domain = FQarryBody.getValueOption();
								String[] strArray = domain.split(",");
								for (String str : strArray) {
									ValueOption.add(str);
								}
								bd.setValueOption(ValueOption);
							}
							bd.setLibrary(FQarryBody.getLibrary());
							bd.setFieldName(FQarryBody.getFieldName());
							bd.setTextInstruction(FQarryBody
									.getTextInstruction());
							bd.setVideoInstruction(FQarryBody
									.getVideoInstruction());
							bd.setPictureInstruction(FQarryBody
									.getPictureInstruction());
							ArrayList<String> JBody = new ArrayList<String>();

							if (FQarryBody.isAllcomment()) {
								JBody.add("comment");
							}

							if (FQarryBody.isAllowPic()) {
								JBody.add("Pic");
							}
							if (FQarryBody.isAllowVideo()) {
								JBody.add("video");
							}
							bd.setControls(JBody);
							grp.addBody(bd);
						}
					}
					g.add(grp);
				}
				sec.setGroupItems(g);
			}
			s.add(sec);
		}

	return s;

	}

}
