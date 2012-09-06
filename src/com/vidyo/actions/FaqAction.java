package com.vidyo.actions;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import org.springframework.beans.BeanUtils;


import com.vidyo.beans.Faq;
import com.vidyo.daos.CommonDAO;

@ManagedBean(name="faqAction")
@ViewScoped
public class FaqAction extends BaseAction {

	private List<Faq> faqList;
	private Faq faq=new Faq();
	private Integer faqIndex;
	
	public void initFaqs(){
		if (!FacesContext.getCurrentInstance().isPostback()) {        
			CommonDAO commonDao = (CommonDAO)getBean("commonDao");
			this.faqList = commonDao.getAllFaq();
		}
	}



	public void addFaq(){
		this.faq = new Faq();
	}
	
	public void insertFaq(){
		Faq faq = new Faq();
		BeanUtils.copyProperties(this.faq, faq);
		CommonDAO commonDao = (CommonDAO)getBean("commonDao");
		commonDao.insertFaq(faq);
		this.faqList.add(faq);
	}
	
	
	public void editFaq(Integer i){
		this.faqIndex = i;
		Faq faq = this.faqList.get(i);
		BeanUtils.copyProperties(faq, this.faq );
	}
	public void updateFaq(Integer i){
		Faq faq = this.faqList.get(i);
		BeanUtils.copyProperties(this.faq, faq);
		CommonDAO commonDao = (CommonDAO)getBean("commonDao");
		commonDao.updateUser(faq);
	}
	

	

	
	public void deleteFaq(Integer i){
		Faq faq = this.faqList.remove(i.intValue());
		CommonDAO commonDao = (CommonDAO)getBean("commonDao");
		commonDao.deleteFaq(faq);

	}



	public List<Faq> getFaqList() {
		return faqList;
	}



	public void setFaqList(List<Faq> faqList) {
		this.faqList = faqList;
	}



	public Faq getFaq() {
		return faq;
	}



	public void setFaq(Faq faq) {
		this.faq = faq;
	}



	public Integer getFaqIndex() {
		return faqIndex;
	}



	public void setFaqIndex(Integer faqIndex) {
		this.faqIndex = faqIndex;
	}
	
	
}
