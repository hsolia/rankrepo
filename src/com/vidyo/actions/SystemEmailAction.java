package com.vidyo.actions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.vidyo.beans.User;
import com.vidyo.services.EmailManager;
import com.vidyo.services.TemplateManager;
import com.vidyo.services.UserManager;

@ManagedBean(name="systemEmailAction")
@ViewScoped
public class SystemEmailAction extends BaseAction {


	private String templateBody;
	private String templateTitle;
	private String currentTemp;
	private String userMailBody;
	private String toEmail;
	private String ccEmail;
	private String bccEmail;
	private String emailSubject;
	private String actionType="none";
	
	private String[] emailTemps;
	
	public void initSystemEmail(){
		if (!FacesContext.getCurrentInstance().isPostback()) {        
			this.emailTemps = new String[12];
			this.emailTemps[0] = TemplateManager.ACTIVATION_EMAIL;
			this.emailTemps[1] = TemplateManager.USER_WELCOME_EMAIL;
			this.emailTemps[2] = TemplateManager.ADMIN_WELCOME_EMAIL;
			this.emailTemps[3] = TemplateManager.ROOM_UPDATE_EMAIL;
			this.emailTemps[4] = TemplateManager.LOGIN_DETAIL_EMAIL;
			this.emailTemps[5] = TemplateManager.INVITATAION_EMAIL;
			this.emailTemps[6] = TemplateManager.CALL_INSTRUCTION_EMAIL;
			this.emailTemps[7] = TemplateManager.CALL_INSTRUCTION_WITH_PIN;
			this.emailTemps[8] = TemplateManager.CALL_INSTRUCTION_WITHOUT_PIN;
			this.emailTemps[9] = TemplateManager.VOICE_INSTRUCTION_WITH_PIN ;			
			this.emailTemps[10] = TemplateManager.VOICE_INSTRUCTION_WITHOUT_PIN;
			this.emailTemps[11] = TemplateManager.RECORDED_MEETING_LINK_EMAIL;			
		}
	}
	

	public void loadEmailTemplate(String templateID){
		actionType="init";
		setTemplateTitle(templateID);
		this.currentTemp = templateID;
		
		TemplateManager templateManager = (TemplateManager)getBean("templateManager");
		String templatePath = templateManager.emailTemplateMap.get(templateID);
		String templateRealPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(templatePath);
		try{
			BufferedReader br = new BufferedReader(new FileReader(templateRealPath));
			String strLine="";
			this.templateBody="";
			 while ((strLine = br.readLine()) != null)   {
				 this.templateBody +=  strLine;
			 }
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void updateEmailTemplate(String templateID){
		
		TemplateManager templateManager = (TemplateManager)getBean("templateManager");
		String templatePath = templateManager.emailTemplateMap.get(templateID);
		String templateRealPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(templatePath);
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(templateRealPath));

			this.templateBody = this.templateBody.replace("<p>", "");
			this.templateBody = this.templateBody.replace("</p>", "");
			bw.write(this.templateBody);
			if(bw != null){
				try{bw.close();}
				catch(Exception ex){}
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	public void sendUserMail(){
		
		UserManager userManager = (UserManager)getBean("userManager");
		List<User> customerList = userManager.getUserListByCriteria("");
		List userEmailList = new ArrayList();
		for (User user : customerList) {     
			userEmailList.add(user.getEmailaddress()); 
		}
		userEmailList.add(this.bccEmail);
		EmailManager emailManager = (EmailManager)getBean("emailManager");
		emailManager.sendBlastEmail(userEmailList, this.ccEmail, this.userMailBody,this.emailSubject);

		this.toEmail="";
		this.bccEmail="";
		this.ccEmail = "";
		this.userMailBody="";
		this.emailSubject="";
		actionType="sentSuccess";
	}

	public String getTemplateBody() {
		return templateBody;
	}


	public void setTemplateBody(String templateBody) {
		this.templateBody = templateBody;
	}

	public String getTemplateTitle() {
		return templateTitle;
	}



	public void setTemplateTitle(String templateId) {
		if(templateId.equals(TemplateManager.ACTIVATION_EMAIL)){
			this.templateTitle = "Activation Email Template";	
		}else if(templateId.equals(TemplateManager.USER_WELCOME_EMAIL)){
			this.templateTitle = "Welcome Email Template";	
		}else if(templateId.equals(TemplateManager.ADMIN_WELCOME_EMAIL)){
			this.templateTitle = "Welcome Admin Email Template";	
		}else if(templateId.equals(TemplateManager.LOGIN_DETAIL_EMAIL )){
			this.templateTitle = "Forgot Username/Password Email Template";	
		}else if(templateId.equals(TemplateManager.ROOM_UPDATE_EMAIL )){
			this.templateTitle = "Account/Room Update Email Template";	
		}else if(templateId.equals(TemplateManager.INVITATAION_EMAIL)){
			this.templateTitle = "Participant Invitation Email Template";	
		}else if(templateId.equals(TemplateManager.CALL_INSTRUCTION_EMAIL )){
			this.templateTitle = "Call Instuction Email Template";	
		}else if(templateId.equals(TemplateManager.CALL_INSTRUCTION_WITH_PIN )){
			this.templateTitle = "Call Instuction with PIN Template";	
		}else if(templateId.equals(TemplateManager.CALL_INSTRUCTION_WITHOUT_PIN )){
			this.templateTitle = "Call Instuction without PIN Template";	
		}else if(templateId.equals(TemplateManager.VOICE_INSTRUCTION_WITH_PIN )){
			this.templateTitle = "Voice Instuction with PIN Template";	
		}else if(templateId.equals(TemplateManager.VOICE_INSTRUCTION_WITHOUT_PIN )){
			this.templateTitle = "Voice Instuction without PIN Template";	
		}else if(templateId.equals(TemplateManager.RECORDED_MEETING_LINK_EMAIL )){
			this.templateTitle = "Recorded Meeting Link Email Template";	
		}

	}


	public String[] getEmailTemps() {
		return emailTemps;
	}


	public String getCurrentTemp() {
		return currentTemp;
	}


	public void setCurrentTemp(String currentTemp) {
		this.currentTemp = currentTemp;
	}


	public String getUserMailBody() {
		return userMailBody;
	}


	public void setUserMailBody(String userMailBody) {
		this.userMailBody = userMailBody;
	}


	public String getToEmail() {
		return toEmail;
	}


	public void setToEmail(String toEmail) {
		this.toEmail = toEmail;
	}


	public String getCcEmail() {
		return ccEmail;
	}


	public void setCcEmail(String ccEmail) {
		this.ccEmail = ccEmail;
	}


	public String getBccEmail() {
		return bccEmail;
	}


	public void setBccEmail(String bccEmail) {
		this.bccEmail = bccEmail;
	}


	public String getEmailSubject() {
		return emailSubject;
	}


	public void setEmailSubject(String emailSubject) {
		this.emailSubject = emailSubject;
	}


	public String getActionType() {
		return actionType;
	}


	public void setActionType(String actionType) {
		this.actionType = actionType;
	}


}