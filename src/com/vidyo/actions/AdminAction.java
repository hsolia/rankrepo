package com.vidyo.actions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import org.springframework.beans.BeanUtils;
import com.vidyo.beans.User;
import com.vidyo.common.VidyoConstants;
import com.vidyo.dtos.CallInfoDTO;
import com.vidyo.dtos.RoomEntityDTO;
import com.vidyo.services.EmailManager;
import com.vidyo.services.UserManager;
import com.vidyo.util.Pagging;
import com.vidyo.webservices.common.VidyoAdminService;

@ManagedBean(name="adminAction")
@ViewScoped
public class AdminAction extends BaseAction implements Serializable {

	private List<User> customerList;
	private User user=new User();
	private RoomEntityDTO meetingRoom = new RoomEntityDTO();
	private String queryString;
	private Integer customerIndex;
	private String username;
	private String firstname;
	private String lastname;
	private String companyname;
	private String address;
	private String phonenumber;
	private String password;

	private String userMailBody;
	private List<CallInfoDTO> callInfoList;
	private String legalTermBody;
	
	private Date  beginningDate = new Date();
	private Date endingDate = new Date();
	private Pagging pagging = new Pagging(10);
	private String emailaddress;
	
	public void initCustomers(){
		if (!FacesContext.getCurrentInstance().isPostback()) {        
			UserManager userManager = (UserManager)getBean("userManager");
			this.customerList = userManager.getUserListByCriteria("");
		}
	}

	public void initManageAdmins(){
		if (!FacesContext.getCurrentInstance().isPostback()) {        
			UserManager userManager = (UserManager)getBean("userManager");
			this.customerList = userManager.getAdminUserList();
		}
	}


	
	public void editLegalTerm(){

		String templatePath =VidyoConstants.LEGAL_TERMSCONDITION_FILE;
		String templateRealPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(templatePath);
		try{
			BufferedReader br = new BufferedReader(new FileReader(templateRealPath));
			String strLine="";
			this.legalTermBody="";
			 while ((strLine = br.readLine()) != null)   {
				 this.legalTermBody +=  strLine;
			 }
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void updateLegalTerm(){
		String templatePath = VidyoConstants.LEGAL_TERMSCONDITION_FILE;
		String templateRealPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(templatePath);
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(templateRealPath));

			this.legalTermBody = this.legalTermBody.replace("<p>&nbsp;</p>", "");
			this.legalTermBody = this.legalTermBody.replace("<p>", "");
			this.legalTermBody = this.legalTermBody.replace("</p>", "");

			bw.write(this.legalTermBody);
			if(bw != null){
				try{bw.close();}
				catch(Exception ex){}
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void searchCustomer(){
		UserManager userManager = (UserManager)getBean("userManager");
		this.customerList = userManager.getUserListByCriteria(queryString);
	}

	public void addAdmin(){
		this.user = new User();
	}
	
	public void insertAdmin(){
		User user = new User();

		BeanUtils.copyProperties(this.user, user);
		
		user.setEnabled("1");
		user.setStatus("1");
		user.setUserrole(VidyoConstants.USER_ROLE_ADMIN);
		UserManager userManager = (UserManager)getBean("userManager");
		userManager.createUser(user);
		this.customerList.add(user);
	}
	
	public void validateUser(FacesContext fc, UIComponent arg1, Object arg2)
	throws ValidatorException  {
		
		VidyoAdminService vidyoAdminServer = (VidyoAdminService)getBean("vidyoAdminService");
		if(vidyoAdminServer.vidyoUserExist(arg2.toString())){
			addMessage("Username already exists", FacesMessage.SEVERITY_INFO, "addform:username");
			throw new ValidatorException(new FacesMessage("Username already exists"));
		}
    }

	
	public void editCustomer(Integer i){
		this.customerIndex = i;
		User user = this.customerList.get(i);
		this.setUser(new User());
		BeanUtils.copyProperties(user, this.user );
		this.emailaddress = this.user.getEmailaddress();
	}

	
	public void custUsage(Integer i){
		this.customerIndex = i;
		
		User user = this.customerList.get(i);
		this.username = user.getUsername();
		this.firstname = user.getFirstname();
		this.lastname = user.getLastname();
		
		VidyoAdminService vidyoAdminService =(VidyoAdminService)getBean("vidyoAdminService");
		UserManager userManager =(UserManager)getBean("userManager");
		
		List<String> roomNameList = vidyoAdminService.getRoomListByUser(user.getUsername());

		callInfoList = userManager.getUsageRecordList(roomNameList);
		this.pagging.updateTotRecs(callInfoList.size());
		if(callInfoList.size()>0 ){
			this.beginningDate = new Date(callInfoList.get(0).getJoinTime());
			this.endingDate = new Date(callInfoList.get(callInfoList.size()-1).getLeaveTime());
		}
		
	}
	
	public void filterUsage(){
		User user = this.customerList.get(this.customerIndex);
		VidyoAdminService vidyoAdminService =(VidyoAdminService)getBean("vidyoAdminService");
		UserManager userManager =(UserManager)getBean("userManager");
		
		List<String> roomNameList = vidyoAdminService.getRoomListByUser(user.getUsername());
		
		callInfoList = userManager.getUsageRecordList(roomNameList,this.beginningDate, this.endingDate);

		this.pagging.updateTotRecs(callInfoList.size());
	}
	
	public void updateCustomer(Integer i){
		User user = this.customerList.get(i);

		BeanUtils.copyProperties(this.user, user);
		
		//user.setUsername(this.username);
/*		user.setFirstname(this.user.getFirstname());
		user.setLastname(this.user.getLastname());
		user.setCompanyname(this.user.getCompanyname());
		user.setPhonenumber(this.user.getPhonenumber());
		user.setEmailaddress(this.user.getEmailaddress());
		user.setAddress(this.user.getAddress());
		user.setPassword(this.user.getPassword());
		
		user.setMeetingRoom(this.user.getMeetingRoom());*/
		
		UserManager userManager = (UserManager)getBean("userManager");
		userManager.updateUser(user);
	}
	
	public void enableCustomer(Integer i){
		User user = this.customerList.get(i);
		if(user.getEnabled().equals("0")){
			user.setEnabled("1");
		}else{
			user.setEnabled("0");
		}
		
		
		UserManager userManager = (UserManager)getBean("userManager");
		userManager.updateUser(user);
	}
	
	public void sendUserMail(Integer i){
		EmailManager emailManager = (EmailManager)getBean("emailManager");
		emailManager.sendUserEmail(this.userMailBody,"Email sent by Vidyo Admin", this.emailaddress);
	}
	
	public void deleteCustomer(Integer i){
		User user = this.customerList.remove(i.intValue());
		UserManager userManager = (UserManager)getBean("userManager");
		userManager.deleteUser(user);

	}
	
	public List<User> getCustomerList() {
		return customerList;
	}


	public void setCustomerList(List<User> customerList) {
		this.customerList = customerList;
	}


	public String getQueryString() {
		return queryString;
	}


	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}


	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
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


	public Integer getCustomerIndex() {
		return customerIndex;
	}


	public void setCustomerIndex(Integer customerIndex) {
		this.customerIndex = customerIndex;
	}


	public String getUserMailBody() {
		return userMailBody;
	}


	public void setUserMailBody(String userMailBody) {
		this.userMailBody = userMailBody;
	}

	public List<CallInfoDTO> getCallInfoList() {
		return callInfoList;
	}

	public void setCallInfoList(List<CallInfoDTO> callInfoList) {
		this.callInfoList = callInfoList;
	}

	public String getLegalTermBody() {
		return legalTermBody;
	}

	public void setLegalTermBody(String legalTermBody) {
		this.legalTermBody = legalTermBody;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public RoomEntityDTO getMeetingRoom() {
		return meetingRoom;
	}

	public void setMeetingRoom(RoomEntityDTO meetingRoom) {
		this.meetingRoom = meetingRoom;
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
