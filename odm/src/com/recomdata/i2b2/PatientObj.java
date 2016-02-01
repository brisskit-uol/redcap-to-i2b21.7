package com.recomdata.i2b2;

public class PatientObj {

        String patient_id;
	String research_id;
	String redcap_id;
	
	public PatientObj(String patient_id, String research_id, String redcap_id) {
		super();
		this.patient_id = patient_id;
		this.research_id = research_id;
		this.redcap_id = redcap_id;
	}
	
	public String getPatient_id() {
		return patient_id;
	}
	public void setPatient_id(String patient_id) {
		this.patient_id = patient_id;
	}
	public String getResearch_id() {
		return research_id;
	}
	public void setResearch_id(String research_id) {
		this.research_id = research_id;
	}
	public String getRedcap_id() {
		return redcap_id;
	}
	public void setRedcap_id(String redcap_id) {
		this.redcap_id = redcap_id;
	}
}