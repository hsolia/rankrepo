package com.vidyo.actions;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.vidyo.beans.User;
import com.vidyo.dtos.RecordingDTO;
import com.vidyo.services.EmailManager;
import com.vidyo.services.TemplateManager;
import com.vidyo.util.Pagging;
import com.vidyo.webservices.common.VidyoContentService;

@ManagedBean(name="recordingManageAction")
@ViewScoped
public class RecordingManageAction extends BaseAction implements Serializable{

	private static Logger LOGGER = Logger.getLogger(RecordingManageAction.class);
	private List<RecordingDTO> recordingList;
	private String flvFilePlyaing;
	private String receiverName;
	private String receiverEmailAddress;
	private RecordingDTO recodingDto;
	private int recCount;
	private Pagging pagging = new Pagging(10);
	
	public void initRecordMeetings(){
		if (!FacesContext.getCurrentInstance().isPostback()) { 
			FacesContext fCtx = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false); 
			User user = (User)session.getAttribute("user");

			
			VidyoContentService vidyoContentService = (VidyoContentService)getBean("vidyoContentService");
			recordingList = vidyoContentService.getRecordedMeetings(user);
			recCount = recordingList.size();
			this.pagging.updateTotRecs(recordingList.size());
		}
	}

	public void sendEmailLink(){
		try{
			FacesContext fCtx = FacesContext.getCurrentInstance();
			HttpSession session = (HttpSession) fCtx.getExternalContext().getSession(false); 
			User user = (User)session.getAttribute("user");
			
			TemplateManager templateManager = (TemplateManager)getBean("templateManager");
			
			String tempBody =  templateManager.getTemplateAsString(TemplateManager.RECORDED_MEETING_LINK_EMAIL , user);
	        tempBody = tempBody.replace("[FIRSTNAME]", user.getFirstname()!=null ? user.getFirstname(): "");
	        tempBody = tempBody.replace("[LASTNAME]", user.getLastname()!=null ? user.getLastname(): "");
	        tempBody = tempBody.replace("[MEETINGTITLE]", this.recodingDto.getTitle() !=null ? this.recodingDto.getTitle(): "");
	        tempBody = tempBody.replace("[MEETINGDESC]", this.recodingDto.getDesc()!=null ? this.recodingDto.getDesc(): "");
	        tempBody = tempBody.replace("[LINK]", this.recodingDto.getPlaybackLink()!=null ? this.recodingDto.getPlaybackLink(): "");
	        
			EmailManager emailManager = (EmailManager)getBean("emailManager");
			emailManager.sendUserEmail(tempBody,"Vidyo :: Recorded meeting link", this.receiverEmailAddress);
		}catch(Exception ex){
			LOGGER.error("Error in sendEmailLink",ex);
		}
	}
	
	public void viewRecording(Integer i){
		recodingDto = recordingList.get(i);
		flvFilePlyaing = recodingDto.getFileLink();
	}
	
	public void updateRecording(){
		//recodingDto = recordingList.get(i);
		VidyoContentService vidyoContentService = (VidyoContentService)getBean("vidyoContentService");
		vidyoContentService.updateRecord(recodingDto);

	}
	
	public void deleteRecording(Integer i){
		VidyoContentService vidyoContentService = (VidyoContentService)getBean("vidyoContentService");
		int id=  recordingList.get(i).getId();
		vidyoContentService.deleteRecord(id);
		recordingList.remove(i.intValue());
		this.pagging.updateTotRecs(recordingList.size());
	}
	
	public List<RecordingDTO> getRecordingList() {
		return recordingList;
	}

	public void setRecordingList(List<RecordingDTO> recordingList) {
		this.recordingList = recordingList;
	}

	public String getFlvFilePlyaing() {
		return flvFilePlyaing;
	}

	public void setFlvFilePlyaing(String flvFilePlyaing) {
		this.flvFilePlyaing = flvFilePlyaing;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getReceiverEmailAddress() {
		return receiverEmailAddress;
	}

	public void setReceiverEmailAddress(String receiverEmailAddress) {
		this.receiverEmailAddress = receiverEmailAddress;
	}

	public int getRecCount() {
		return recCount;
	}

	public void setRecCount(int recCount) {
		this.recCount = recCount;
	}

	public RecordingDTO getRecodingDto() {
		return recodingDto;
	}

	public void setRecodingDto(RecordingDTO recodingDto) {
		this.recodingDto = recodingDto;
	}

	public Pagging getPagging() {
		return pagging;
	}

	public void setPagging(Pagging pagging) {
		this.pagging = pagging;
	}


	

}
