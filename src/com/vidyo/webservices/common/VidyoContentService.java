package com.vidyo.webservices.common;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBElement;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;

import org.apache.log4j.Logger;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.vidyo.beans.User;
import com.vidyo.common.VidyoConfig;
import com.vidyo.common.VidyoConstants;
import com.vidyo.dtos.RecordingDTO;
import com.vidyo.webservices.content.DeleteRecordRequest;
import com.vidyo.webservices.content.DeleteRecordResponse;
import com.vidyo.webservices.content.ObjectFactory;
import com.vidyo.webservices.content.Record;
import com.vidyo.webservices.content.RecordsSearchRequest;
import com.vidyo.webservices.content.RecordsSearchResponse;
import com.vidyo.webservices.content.UpdateRecordRequest;
import com.vidyo.webservices.content.UpdateRecordResponse;
import com.vidyo.webservices.content.VidyoReplayContentManagementServicePortType;



public class VidyoContentService implements ApplicationContextAware  {
	public static Logger LOGGER = Logger.getLogger(VidyoAdminService.class);
	VidyoReplayContentManagementServicePortType port;
	private static ApplicationContext appCtx = null;
	
    public VidyoContentService(VidyoReplayContentManagementServicePortType port,String username, String password) {
    	
    	System.out.println("VidyoContentService started");
		this.port = port;
    	BindingProvider bindingProvider = (BindingProvider)this.port;
		Map requestContext = bindingProvider.getRequestContext();
		requestContext.put(BindingProvider.USERNAME_PROPERTY, username);
		requestContext.put(BindingProvider.PASSWORD_PROPERTY, password);
    }
    
    public void deleteRecord(int id){
    	LOGGER.info("Deleting records from Vidyo API");
		try{
			ObjectFactory factory = new ObjectFactory();
			
			DeleteRecordRequest request =  factory.createDeleteRecordRequest();
			DeleteRecordResponse response =  factory.createDeleteRecordResponse();
			
			request.setId(id);
			
			response = this.port.deleteRecord(request);
			
			
			LOGGER.info("Vidyo API Record deleted: "+response.getOK());
		}
		catch(Throwable ex){
			LOGGER.error("Error in deleteRecord",ex);
		}
    }

    public void updateRecord(RecordingDTO recordingDto){
    	LOGGER.info("Deleting records from Vidyo API");
		try{
			ObjectFactory factory = new ObjectFactory();
			
			UpdateRecordRequest request =  factory.createUpdateRecordRequest();
			UpdateRecordResponse response =  factory.createUpdateRecordResponse();
			
			JAXBElement<String> desc = new JAXBElement<String>(new QName("http://replay.vidyo.com/apiservice", "comments") , String.class, null, recordingDto.getDesc());
			
			request.setId(recordingDto.getId());
			request.setTitle(recordingDto.getTitle());
			request.setComments(desc);
			
			response = this.port.updateRecord(request);
			
			
			LOGGER.info("Vidyo API Record updated : "+response.getOK());
		}
		catch(Throwable ex){
			LOGGER.error("Error in updateRecord",ex);
		}
    }

    
	public List getRecordedMeetings(User user){
		LOGGER.info("Searching Recodring list for "+ user.getUsername());
		List<RecordingDTO> recordingList = new ArrayList<RecordingDTO>();
		SimpleDateFormat dateformat = new SimpleDateFormat("MM/dd/yyyy hh:mm");//'YYYY-MM-DD HH:MM:SS'
		VidyoConfig vidyoConfig = (VidyoConfig)appCtx.getBean("vidyoConfig");
		
		
		try{
			ObjectFactory factory = new ObjectFactory();
			
			RecordsSearchRequest request =  factory.createRecordsSearchRequest();
			RecordsSearchResponse response =  factory.createRecordsSearchResponse();
			
			request.setUsernameFilter(user.getUsername());
			request.setWebcast(false);
			
			response = this.port.recordsSearch(request);
			
			List<Record> records = response.getRecords();
			for(Record record : records){
				RecordingDTO recordingDto = new RecordingDTO();
				recordingDto.setId(record.getId());
				recordingDto.setTitle(record.getTitle());
				recordingDto.setDesc(record.getComments());
				recordingDto.setDateCreated(dateformat.format(record.getDateCreated().toGregorianCalendar().getTime()));
				recordingDto.setDuration(record.getDuration());
				recordingDto.setPlaybackLink(record.getExternalPlaybackLink());
				recordingList.add(recordingDto);
			}
			LOGGER.info("Total Recording Serach: "+response.getSearchCount());
		}
		catch(Throwable ex){
			LOGGER.error("Error in getRecordedMeetings",ex);
		}

		return recordingList;
	}

	  public static ApplicationContext getApplicationContext() {
	        return appCtx;
	   }
	    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
	         this.appCtx = applicationContext;
	   }
	
}
