package com.vidyo.actions;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.el.ValueExpression;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.html.simpleparser.HTMLWorker;
import com.lowagie.text.html.simpleparser.StyleSheet;
import com.lowagie.text.pdf.PdfWriter;
import com.vidyo.beans.User;
import com.vidyo.dtos.CallInfoDTO;
import com.vidyo.dtos.RoomEntityDTO;
import com.vidyo.services.TemplateManager;
import com.vidyo.services.UserManager;
import com.vidyo.services.UtilManager;
import com.vidyo.util.Pagging;
import com.vidyo.webservices.admin.Filter;
import com.vidyo.webservices.admin.GetRoomsRequest;
import com.vidyo.webservices.admin.GetRoomsResponse;
import com.vidyo.webservices.admin.ObjectFactory;
import com.vidyo.webservices.admin.VidyoPortalAdminServicePortType;
import com.vidyo.webservices.common.VidyoAdminService;

@ManagedBean(name="userAction")
@ViewScoped
public class UserAction  extends BaseAction implements Serializable {

	private static Logger LOGGER = Logger.getLogger(UserAction.class);
	private RoomEntityDTO roomEntity;
	private String firstname;
	private String lastname;
	private String username;
	private String companyname;
	private String emailaddress;
	private String address;
	private String phonenumber;
	private String password;
	private String actionType="editAccount";

	private String roomnumber;
	private String roompin;
	private String newroomnumber;
	private String newroompin;
	private String dialInstruction;
	private String voiceCallInstruction;	
	private List<CallInfoDTO> callInfoList;

	private Pagging pagging = new Pagging(15);
	private Date  beginningDate = new Date();
	private Date endingDate = new Date();
	private List<String> roomNameList;
	public void initUserAction(){
		if (!FacesContext.getCurrentInstance().isPostback()) {        
			
			HttpSession session = getCurrentSession();
			User user = (User)session.getAttribute("user");
			
			this.username=user.getUsername();
			this.firstname=user.getFirstname();
			this.lastname=user.getLastname();
			this.companyname=user.getCompanyname();
			this.emailaddress=user.getEmailaddress();
			this.address=user.getAddress();
			this.phonenumber=user.getPhonenumber();
			this.password=user.getPassword();
			
			this.initRoomUpdate();

			try{
				VidyoAdminService vidyoAdminService =(VidyoAdminService)getBean("vidyoAdminService");
				roomNameList = vidyoAdminService.getRoomListByUser(user.getUsername());
				UserManager userManager =(UserManager)getBean("userManager");
				callInfoList = userManager.getUsageRecordList(roomNameList);
				this.pagging.updateTotRecs(callInfoList.size());
	
				if(callInfoList.size()>0 ){
					this.beginningDate = new Date(callInfoList.get(0).getJoinTime());
					this.endingDate = new Date(callInfoList.get(callInfoList.size()-1).getLeaveTime());
				}
			}
			catch(Exception ex){
				LOGGER.error("error while geting usage info",ex);
			}
		}
	}
	
	public void initRoomUpdate(){

		FacesContext fCtx = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false); 
		User user = (User)session.getAttribute("user");
	}
	
	public void initRightView(){
		if (!FacesContext.getCurrentInstance().isPostback()) {        
			
			dialInstruction = UtilManager.getDialInInstruction(roomnumber, roompin);
			voiceCallInstruction = UtilManager.getVoiceCallInfo();

			//VidyoUserService vidyoUserService = (VidyoUserService)getBean("vidyoUserService");
			//roomEntity = vidyoUserService.getRoomEntity();
			
			this.initRoomUpdate();
		}
	}	

	public void filterUsage(){
		User user = (User)getCurrentSession().getAttribute("user");
		UserManager userManager =(UserManager)getBean("userManager");
		callInfoList = userManager.getUsageRecordList(roomNameList,this.beginningDate, this.endingDate);
		this.pagging.updateTotRecs(callInfoList.size());
	}
	
	public void exportUsage(){
		try{
			String contentType = "application/vnd.ms-excel";
			 FacesContext fc = FacesContext.getCurrentInstance();
			 String filename = fc.getExternalContext().getUserPrincipal().getName()+ "-" + System.currentTimeMillis()+ ".xls";
			 HttpServletResponse response = (HttpServletResponse)
			 fc.getExternalContext().getResponse();
			 response.setHeader("Content-disposition", "attachment; filename=" + filename);
			     response.setContentType(contentType);
			 
			     StringBuffer htmlBuffer = new StringBuffer();
			     PrintWriter out = response.getWriter();
			     htmlBuffer.append("<HTML>\n");
			 out.print(htmlBuffer);
			 out.close();
			 fc.responseComplete();
		}catch(Exception ex){
			
		}
		
	}
	

	
	public void changeAction(String action){
		if(action.equals("editAccount")){
			this.actionType="editAccount";
		}else if(action.equals("usageInfo")){
			this.actionType="usageInfo";
		}else if(action.equals("editRoom")){
			this.actionType="editRoom";
		}

	}
	
	

	public void exportFile() {
		LOGGER.debug("entered createPDF");     
		FacesContext context = FacesContext.getCurrentInstance();      
		HttpServletResponse response = (HttpServletResponse)context.getExternalContext().getResponse();       
		response.setHeader("Content-disposition", "inline;filename=vidyoCallInstruction.pdf"); 
    	response.setHeader("Expires", "0");         
    	response.setHeader("Cache-Control","must-revalidate, post-check=0, pre-check=0");         
    	response.setHeader("Pragma", "public");      
    	response.setContentType("application/pdf"); 
    	
        try {
        	
    		FacesContext fCtx = FacesContext.getCurrentInstance();
    		HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false); 
    		User user = (User)session.getAttribute("user");
    		
    		TemplateManager templateManager = (TemplateManager)getBean("templateManager");
    		String text = templateManager.renderHTMLTemplate(TemplateManager.CALL_INSTRUCTION_EMAIL, user);
    		
    		StyleSheet styles = new StyleSheet();
            styles.loadTagStyle("body", "font", "Bitstream Vera Sans");
           
            Document document = new Document();
        	ByteArrayOutputStream baos = new ByteArrayOutputStream();         
        	PdfWriter.getInstance(document, baos);      
        	document.open();
            
            
    		ArrayList arrayElementList = HTMLWorker.parseToList(new StringReader(text), styles);
    		for (int i = 0; i < arrayElementList.size(); ++i) {
                Element e = (Element) arrayElementList.get(i);
                document.add(e);
            }
       	
        	document.close();    
        
        	response.setContentLength(baos.size());         
        	OutputStream os = response.getOutputStream();         
        	baos.writeTo(os);         
        	os.flush();         
        	os.close();
        	context.responseComplete();
        	LOGGER.debug("flushed and closed the outputstream");  
        } catch (Exception e) {
                e.printStackTrace();
        }
	}

	
	public void updateUser(){
		
		FacesContext fCtx = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false); 
		User user = (User)session.getAttribute("user");

		user.setUsername(this.username);
		user.setFirstname(this.firstname);
		user.setLastname(this.lastname);
		user.setCompanyname(this.companyname);
		user.setEmailaddress(this.emailaddress);
		user.setAddress(this.address);
		user.setPhonenumber(this.phonenumber);
		user.setPassword(this.password);
		
		UserManager userManager = (UserManager)getBean("userManager");

		userManager.updateUser(user);
		this.actionType="updateAccount";
		
	}

	public void VidyoRoomNumberValidator(FacesContext fc, UIComponent arg1, Object arg2){
		
		String roomnumber = arg2.toString();

		if(roomnumber.equals(this.roomnumber)){
			return;
		}
		if(roomnumber.trim().equals("")){
			return;
		}
			
				int tot=0;
				try{
				Application app = fc.getApplication();          
				ValueExpression expression = app.getExpressionFactory().createValueExpression(fc.getELContext(),"#{vidyoAdminService}", Object.class );         
		VidyoPortalAdminServicePortType port = (VidyoPortalAdminServicePortType) expression.getValue( fc.getELContext() ); 
		
		
		
		
		ObjectFactory factory = new ObjectFactory();
		
		GetRoomsRequest roomsReqest =factory.createGetRoomsRequest();
		GetRoomsResponse roomsResponse = factory.createGetRoomsResponse();
		
		
		Filter filter = factory.createFilter();
		filter.setQuery(factory.createFilterQuery(roomnumber));
		roomsReqest.setFilter(filter);
		
		
		roomsResponse = port.getRooms(roomsReqest);
		tot= roomsResponse.getTotal();
		
		}
		
		catch(Exception ex){
			ex.printStackTrace();
		}
			if(tot>0){
		    	FacesMessage message = new FacesMessage();
		    	message.setSummary("Room number already exists");
			message.setSeverity(FacesMessage.SEVERITY_INFO);
			fc.addMessage(arg1.getClientId(fc), message);
			
			throw new ValidatorException(message);
		
		}

				

		
	}

	public String getFirstname() {
		return firstname;
	}


	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}


	public String getLastname() {
		return lastname;
	}


	public void setLastname(String lastname) {
		this.lastname = lastname;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getCompanyname() {
		return companyname;
	}


	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}


	public String getEmailaddress() {
		return emailaddress;
	}


	public void setEmailaddress(String emailaddress) {
		this.emailaddress = emailaddress;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhonenumber() {
		return phonenumber;
	}


	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getActionType() {
		return actionType;
	}


	public void setActionType(String actionType) {
		this.actionType = actionType;
	}

	public String getRoomnumber() {
		return roomnumber;
	}

	public void setRoomnumber(String roomnumber) {
		this.roomnumber = roomnumber;
	}

	public String getRoompin() {
		return roompin;
	}

	public void setRoompin(String roompin) {
		this.roompin = roompin;
	}

	public String getNewroomnumber() {
		return newroomnumber;
	}

	public void setNewroomnumber(String newroomnumber) {
		this.newroomnumber = newroomnumber;
	}

	public String getNewroompin() {
		return newroompin;
	}

	public void setNewroompin(String newroompin) {
		this.newroompin = newroompin;
	}




	public String getDialInstruction() {
		return dialInstruction;
	}


	public void setDialInstruction(String dialInstruction) {
		this.dialInstruction = dialInstruction;
	}


	public List<CallInfoDTO> getCallInfoList() {
		return callInfoList;
	}


	public void setCallInfoList(List<CallInfoDTO> callInfoList) {
		this.callInfoList = callInfoList;
	}


	public String getVoiceCallInstruction() {
		return voiceCallInstruction;
	}


	public void setVoiceCallInstruction(String voiceCallInstruction) {
		this.voiceCallInstruction = voiceCallInstruction;
	}


	public RoomEntityDTO getRoomEntity() {
		return roomEntity;
	}


	public void setRoomEntity(RoomEntityDTO roomEntity) {
		this.roomEntity = roomEntity;
	}


	public Date getBeginningDate() {
		return beginningDate;
	}

	public void setBeginningDate(Date beginningDate) {
		this.beginningDate = beginningDate;
	}

	public Date getEndingDate() {
		return endingDate;
	}

	public void setEndingDate(Date endingDate) {
		this.endingDate = endingDate;
	}

	public Pagging getPagging() {
		return pagging;
	}

	public void setPagging(Pagging pagging) {
		this.pagging = pagging;
	}


	

}

