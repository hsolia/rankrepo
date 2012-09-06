package com.vidyo.actions;

import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.primefaces.model.chart.PieChartModel;

import com.vidyo.beans.User;
import com.vidyo.daos.UserDAO;
import com.vidyo.dtos.CallInfoDTO;
import com.vidyo.services.UserManager;
import com.vidyo.util.Pagging;

@ManagedBean(name="reportAction")
@ViewScoped
public class ReportAction extends BaseAction {

	private static Logger LOGGER = Logger.getLogger(ReportAction.class); 
	private String exportedFile;
	private String beginningDate;
	private String endingDate;
	private String queryString="";
	List<CallInfoDTO> callInfoList;
	private Pagging pagging = new Pagging(15);
	private PieChartModel pieModel;  

	//SELECT JoinTime, COUNT(*) AS tot FROM ConferenceCall2 GROUP BY JoinTime
	
	public void initReportAction(){
		UserDAO userDao = (UserDAO)getBean("userDao");
		UserManager userManager =(UserManager)getBean("userManager");
		try{
			if(beginningDate==null)beginningDate="";
			if(endingDate==null)endingDate="";
			List<User> userList = userDao.getUserListByNameOrEmail(queryString);
			callInfoList = userManager.getUsageRecordList(userList, beginningDate, endingDate);
			this.pagging.updateTotRecs(callInfoList.size());
			createPieModel();
			LOGGER.info("Total Records: "+callInfoList.size());
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
	}
	
	private void createPieModel() {  
	      pieModel = new PieChartModel();  
	      Map<String,Number> data = new HashMap<String,Number>();
	      for(CallInfoDTO callDto : callInfoList){
	    	  if(data.containsKey(callDto.getConferenceName())){
	    		  Number tot = data.get(callDto.getConferenceName());
	    		  tot = tot.intValue() + 1;
	    		  data.put(callDto.getConferenceName(), tot);
	    	  }else{
	    		  data.put(callDto.getConferenceName(), 1);
	    	  }
	    	  
	    	  
	      }
	      
	      pieModel.setData(data);
	} 


	
	public void exportCustomer(){
		UserManager userManager = (UserManager)getBean("userManager");
		List<User> customerList = userManager.getUserListByCriteria("");
		
		List<String> lines=new ArrayList<String>();
		
		for(User user : customerList){
			String line="";	
			line +=user.getUsername()+",";
			line +=user.getFirstname()+",";
			line +=user.getLastname()+",";
			line +=user.getEmailaddress()+",";
			line +=user.getCompanyname()+",";
			line +=user.getAddress()+",";
			line +=user.getPhonenumber();
			lines.add(line);
		}
		
		try{
			
			String fileRealPath = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/data.csv");
			this.exportedFile = fileRealPath; 
			exportFile(lines,fileRealPath);
			FacesContext.getCurrentInstance().renderResponse();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
		
	}

	public void exportFile(List<String> lines, String fileRealPath){
		BufferedWriter bw =null;
		try{
			bw = new BufferedWriter(new FileWriter(fileRealPath));
			for(String line : lines){
				bw.write(String.valueOf(line));
				bw.newLine();
			}
			
			if(bw != null){
				try{bw.close();}
				catch(Exception ex){}
			}
		}
		catch(FileNotFoundException ex){
			System.out.println(ex.getMessage());
		}
		catch(IOException ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	public void exportData(String filename,byte[] blobData)throws ServletException,Exception {
	   ServletOutputStream out = null;
	   ByteArrayInputStream byteArrayInputStream = null;
	   BufferedOutputStream bufferedOutputStream = null;
	   
	   HttpServletResponse response = (HttpServletResponse)FacesContext.getCurrentInstance().getExternalContext().getResponse();
	   
	   try {
	       //response.setContentType("text/csv");
	       String disposition = "attachment; fileName="+filename;
	       response.setHeader("Content-Disposition", disposition);
	
	       out = response.getOutputStream();
	       //byte[] blobData = dao.getCSV();
	
	       //setup the input as the blob to write out to the client
	       
	       byteArrayInputStream = new ByteArrayInputStream(blobData);
	       bufferedOutputStream = new BufferedOutputStream(out);
	       int length = blobData.length;
	       response.setContentLength(length);
	       //byte[] buff = new byte[length];
	        byte[] buff = new byte[(1024 * 1024) * 2];
	
	       //now lets shove the data down
	       int bytesRead;
	       // Simple read/write loop.
	       while (-1 != 
	              (bytesRead = byteArrayInputStream.read(buff, 0, buff.length))) {
	           bufferedOutputStream.write(buff, 0, bytesRead);
	       }
	       out.flush();
	       out.close();
	
	   } catch (Exception e) {
	       System.err.println(e); throw e;
	
	   } finally {
	       if (out != null)
	           out.close();
	       if (byteArrayInputStream != null) {
	           byteArrayInputStream.close();
	       }
	       if (bufferedOutputStream != null) {
	           bufferedOutputStream.close();
	       }
	   }
	}

	public String getExportedFile() {
		return exportedFile;
	}

	public void setExportedFile(String exportedFile) {
		this.exportedFile = exportedFile;
	}

	public String getBeginningDate() {
		return beginningDate;
	}

	public String getEndingDate() {
		return endingDate;
	}

	public void setBeginningDate(String beginningDate) {
		this.beginningDate = beginningDate;
	}

	public void setEndingDate(String endingDate) {
		this.endingDate = endingDate;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public List<CallInfoDTO> getCallInfoList() {
		return callInfoList;
	}

	public void setCallInfoList(List<CallInfoDTO> callInfoList) {
		this.callInfoList = callInfoList;
	}
	public Pagging getPagging() {
		return pagging;
	}
	public void setPagging(Pagging pagging) {
		this.pagging = pagging;
	}

	public PieChartModel getPieModel() {
		return pieModel;
	}
	
}
