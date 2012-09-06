package com.vidyo.util;

import java.util.ArrayList;
import java.util.List;

public class Pagging {

	private Integer maxRecord=10;
	private Integer curPage=1;
	private Integer totRec=0;
	private Integer posFrom=0;
	private Integer posTo=0;
	private List<Integer> totPages = new ArrayList<Integer>(0);
	
	public Pagging(){}
	
	public Pagging(Integer maxRecord){
		this.maxRecord = maxRecord;
		this.posTo = maxRecord - 1;
	}
	
	public void updateTotRecs(Integer totRec){
		this.curPage = 1;
		this.totRec = totRec;
		int totpage = (int) Math.ceil(totRec / (float)this.maxRecord);
		this.totPages = new ArrayList(0);
		for(int i=1;i<=totpage;i++){
			this.totPages.add(i);
		}
		doPaggin();
	}
	
	public void  doPaggin(){
		
		this.posFrom = (maxRecord * curPage) - maxRecord;
		this.posTo = this.posFrom + (maxRecord - 1);
		if(this.totRec<this.posTo){
			this.posTo = this.totRec - 1;
		}	
	}



	
	public void movePage(Integer i){
		this.curPage = i.intValue();
		doPaggin();
		 
	}

	public Integer getMaxRecord() {
		return maxRecord;
	}

	public void setMaxRecord(Integer maxRecord) {
		this.maxRecord = maxRecord;
	}

	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public Integer getTotRec() {
		return totRec;
	}

	public void setTotRec(Integer totRec) {
		this.totRec = totRec;
	}

	public Integer getPosFrom() {
		return posFrom;
	}

	public void setPosFrom(Integer posFrom) {
		this.posFrom = posFrom;
	}

	public Integer getPosTo() {
		return posTo;
	}

	public void setPosTo(Integer posTo) {
		this.posTo = posTo;
	}

	public List getTotPages() {
		return totPages;
	}

	public void setTotPages(List totPages) {
		this.totPages = totPages;
	}
	
}
