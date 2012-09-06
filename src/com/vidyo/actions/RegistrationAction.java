package com.vidyo.actions;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.vidyo.beans.User;
import com.vidyo.common.VidyoConstants;
import com.vidyo.services.UserManager;
import com.vidyo.webservices.common.VidyoAdminService;
import com.vidyo.webservices.common.VidyoUserService;



@ManagedBean(name="registrationAction")
@ViewScoped
public class RegistrationAction extends BaseAction {

    
	private String firstname;
	private String lastname;
	private String username;
	private String companyname;
	private String emailaddress;
	private String address;
	private String phonenumber;
	private String password;
	private boolean actionSuccess=false;
	
	private String legalTermBody;
	
	
	public void loadLegalTerm(){

		String templatePath = VidyoConstants.LEGAL_TERMSCONDITION_FILE;
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
	
	public void validateCheckbox(FacesContext context, UIComponent component, Object value) {
        if (!Boolean.parseBoolean(value.toString())) {
            String clientId = component.getClientId(context);
            //FacesMessage message = MessageFactory.getMessage("You should accept Legal Terms", clientId);
        	FacesMessage message = new FacesMessage();
        	message.setSummary("You must accept Legal Terms");
        	message.setSeverity(FacesMessage.SEVERITY_ERROR);
        	throw new ValidatorException(message);
        }
    }

	public void validateUser(FacesContext fc, UIComponent arg1, Object arg2)
	throws ValidatorException  {
		
		VidyoAdminService vidyoAdminServer = (VidyoAdminService)getBean("vidyoAdminService");
		if(vidyoAdminServer.vidyoUserExist(arg2.toString())){
			addMessage("Username already exists", FacesMessage.SEVERITY_INFO, "regisform:username");
			throw new ValidatorException(new FacesMessage("Username already exists"));
		}
    }
	

	
	public String addUser(){
		
		User user = new User();
		user.setUsername(this.username);
		user.setFirstname(this.firstname);
		user.setLastname(this.lastname);
		user.setCompanyname(this.companyname);
		user.setEmailaddress(this.emailaddress);
		user.setAddress(this.address);
		user.setPhonenumber(this.phonenumber);
		user.setPassword(this.password);
		user.setUserrole("USER");
		user.setStatus(UserManager.USER_UNACTIVE_STATUS);
		user.setEnabled(UserManager.USER_ENABLED_STATUS);
		UserManager userManager = (UserManager)getBean("userManager");

		userManager.createUser(user);
		this.actionSuccess=true;
		return "signup";
		
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


	public boolean isActionSuccess() {
		return actionSuccess;
	}


	public void setActionSuccess(boolean actionSuccess) {
		this.actionSuccess = actionSuccess;
	}

	public String getLegalTermBody() {
		return legalTermBody;
	}

	public void setLegalTermBody(String legalTermBody) {
		this.legalTermBody = legalTermBody;
	}	
}
