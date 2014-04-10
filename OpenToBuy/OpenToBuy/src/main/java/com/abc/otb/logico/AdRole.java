package com.abc.otb.logico;

import java.math.BigDecimal;
import java.util.List;

public class AdRole {

	 List name;
	 String empresa;
	 BigDecimal rank_low;
	 BigDecimal rank_high;
	 String needs_other_role;
	 String notifying_role;
	 String ad_role;
	 String ad_user;
	 
	 public String getAd_role() {
		return ad_role;
	}
	public void setAd_role(String ad_role) {
		this.ad_role = ad_role;
	}
	public String getAd_user() {
		return ad_user;
	}
	public void setAd_user(String ad_user) {
		this.ad_user = ad_user;
	}
	 public String getnotifying_role() {
		return notifying_role;
	}
	public void setnotifying_role(String notifying_role) {
		this.notifying_role = notifying_role;
	}
	public List getName() {
		return name;
	}
	public void setName(List role) {
		this.name = role;
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	 public BigDecimal getrank_low() {
		return rank_low;
	}
	public void setrank_low(BigDecimal rank_low) {
		this.rank_low = rank_low;
	}
	public BigDecimal getrank_high() {
		return rank_high;
	}
	public void setrank_high(BigDecimal rank_high) {
		this.rank_high = rank_high;
	}
	public String getneeds_other_role() {
		return needs_other_role;
	}
	public void setneeds_other_role(String needs_other_role) {
		this.needs_other_role = needs_other_role;
	}
	 
	 
}
