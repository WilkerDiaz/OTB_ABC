package com.abc.otb.logico;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * 
 * @author ccastillo
 *
 */
public class Prld01 {
	

	
	BigDecimal xx_vmr_prld01_id; 
	BigDecimal ad_client_id;
	BigDecimal ad_org_id; 
	char isactive;
	Date created; 
	BigDecimal createdby; 
	Date updated; 
	BigDecimal updatedby; 
	BigDecimal xx_budget_year_month;;
	BigDecimal xx_inv_efec_budgeted_amount; 
	BigDecimal xx_inv_amount_orig_budgeted;
	BigDecimal xx_ini_amount_inve_cost;
	BigDecimal xx_amount_ini_inve_real; 
	BigDecimal xx_num_ini_inve_real;
	BigDecimal xx_purch_amount_budgeted;
	BigDecimal xx_quant_budgeted_shopping ; 
	BigDecimal xx_amount_placed_nac_purchcost;
	BigDecimal xx_amount_placed_nac_campra;
	BigDecimal xx_num_nac_shopping_placed;
	BigDecimal xx_nac_purch_amount_received;
	BigDecimal xx_quant_purch_nac;
	BigDecimal xx_purch_amount_placed_costimp;
	BigDecimal xx_purch_amount_placed_impd;
	BigDecimal xx_purch_quant_impd_placed;
	BigDecimal xx_purch_amount_revimpd;
	BigDecimal xx_quant_purch_amount_srev;
	BigDecimal xx_purch_amount_plad_pastmonths;
	BigDecimal xx_purch_quant_placed_months;
	BigDecimal xx_purch_amount_red_pastmonths;
	BigDecimal xx_num_months_redshop;
	BigDecimal xx_sales_amount_bud;
	BigDecimal xx_sales_amount_bud2;
	BigDecimal xx_amount_sale_cost;
	BigDecimal xx_amount_actual_sale;
	BigDecimal xx_quant_actual_sale;
	BigDecimal xx_prom_sale_amount_bud; 
	BigDecimal xx_prom_sale_numbud;
	BigDecimal xx_pect_sale_prombud; 
	BigDecimal xx_act_amount_sale_prom;
	BigDecimal xx_amount_sale_prom_interests;
	BigDecimal xx_pect_sale_prom_interests;
	BigDecimal xx_amount_sale_frbud;
	BigDecimal xx_bud_amount_frsale;
	BigDecimal xx_port_sale_frbud; 
	BigDecimal xx_act_amount_salefr;
	BigDecimal xx_amount_sale_fr_interests;
	BigDecimal xx_pert_sale_fr_interests;
	BigDecimal xx_final_sale_amountbud; 
	BigDecimal xx_final_bud_amount_sale;
	BigDecimal xx_percent_sqale_finalbud;
	BigDecimal xx_final_act_amoun_tsale;
	BigDecimal xx_final_sale_amount_interests;
	BigDecimal xx_percent_act_final_sale;
	BigDecimal xx_transf_amount_sent; 
	BigDecimal xx_num_transfsent;
	BigDecimal xx_transf_amount_received; 
	BigDecimal xx_numb_transfrev; 
	BigDecimal xx_final_inv_amountbud;
	BigDecimal xx_final_inv_amountbud2;
	BigDecimal xx_inv_amount_final_real;
	BigDecimal xx_num_real_finalinv;
	BigDecimal xx_final_inv_amount_projd;
	BigDecimal xx_num_projd_finalinv;
	BigDecimal xx_amount_purchase_limit; 
	BigDecimal xx_quantity_purchlimit;
	BigDecimal xx_rotation_bud; 
	BigDecimal xx_rotation_real;
	BigDecimal xx_percn_bud_coverage;
	BigDecimal xx_real_perc_coverage;
	BigDecimal xx_margac_cording_budpurch;
	BigDecimal xx_margac_cording_buyreal; 
	BigDecimal xx_lisck_gross_marg_perctbud;
	BigDecimal xx_lisck_gross_marg_pertreal;
	BigDecimal xx_net_marg_pertcattle_bud;
	BigDecimal xx_net_marg_pertroyallivestock;
	BigDecimal xx_by_winmarg_pertbud;
	BigDecimal xx_pert_wing_margreal;
	BigDecimal xx_budd_decline;
	BigDecimal xx_real_decline;
	BigDecimal xx_vmr_line_id; 
	BigDecimal xx_vmr_section_id;
	BigDecimal xx_vmr_category_id;
	BigDecimal m_warehouse_id; 
	BigDecimal xx_vmr_department_id;
	BigDecimal xx_linecode; 
	BigDecimal xx_vmr_brand_id;
	BigDecimal xx_receipt_pvp;
	BigDecimal xx_receipt_qty;
	BigDecimal xx_returns_pvp;
	BigDecimal xx_returns_qty; 
	BigDecimal xx_placed_order_pvp_adjustment;
	
	public BigDecimal getxx_vmr_prld01_id() {
		return xx_vmr_prld01_id;
	}
	public void setxx_vmr_prld01_id(BigDecimal xx_vmr_prld01_id) {
		this.xx_vmr_prld01_id = xx_vmr_prld01_id;
	}
	public BigDecimal getad_client_id() {
		return ad_client_id;
	}
	public void setad_client_id(BigDecimal ad_client_id) {
		this.ad_client_id = ad_client_id;
	}
	public BigDecimal getad_org_id() {
		return ad_org_id;
	}
	public void setad_org_id(BigDecimal ad_org_id) {
		this.ad_org_id = ad_org_id;
	}
	public char getisactive() {
		return isactive;
	}
	public void setisactive(char isactive) {
		this.isactive = isactive;
	}
	public Date getcreated() {
		return created;
	}
	public void setcreated(Date created) {
		this.created = created;
	}
	public BigDecimal getcreatedby() {
		return createdby;
	}
	public void setcreatedby(BigDecimal createdby) {
		this.createdby = createdby;
	}
	public Date getupdated() {
		return updated;
	}
	public void setupdated(Date updated) {
		this.updated = updated;
	}
	public BigDecimal getupdatedby() {
		return updatedby;
	}
	public void setupdatedby(BigDecimal updatedby) {
		this.updatedby = updatedby;
	}
	public BigDecimal getxx_budget_year_month() {
		return xx_budget_year_month;
	}
	public void setxx_budget_year_month(BigDecimal xx_budget_year_month) {
		this.xx_budget_year_month = xx_budget_year_month;
	}
	public BigDecimal getxx_inv_efec_budgeted_amount() {
		return xx_inv_efec_budgeted_amount;
	}
	public void setxx_inv_efec_budgeted_amount(
			BigDecimal xx_inv_efec_budgeted_amount) {
		this.xx_inv_efec_budgeted_amount = xx_inv_efec_budgeted_amount;
	}
	public BigDecimal getxx_inv_amount_orig_budgeted() {
		return xx_inv_amount_orig_budgeted;
	}
	public void setxx_inv_amount_orig_budgeted(
			BigDecimal xx_inv_amount_orig_budgeted) {
		this.xx_inv_amount_orig_budgeted = xx_inv_amount_orig_budgeted;
	}
	public BigDecimal getxx_ini_amount_inve_cost() {
		return xx_ini_amount_inve_cost;
	}
	public void setxx_ini_amount_inve_cost(BigDecimal xx_ini_amount_inve_cost) {
		this.xx_ini_amount_inve_cost = xx_ini_amount_inve_cost;
	}
	public BigDecimal getxx_amount_ini_inve_real() {
		return xx_amount_ini_inve_real;
	}
	public void setxx_amount_ini_inve_real(BigDecimal xx_amount_ini_inve_real) {
		this.xx_amount_ini_inve_real = xx_amount_ini_inve_real;
	}
	public BigDecimal getxx_num_ini_inve_real() {
		return xx_num_ini_inve_real;
	}
	public void setxx_num_ini_inve_real(BigDecimal xx_num_ini_inve_real) {
		this.xx_num_ini_inve_real = xx_num_ini_inve_real;
	}
	public BigDecimal getxx_purch_amount_budgeted() {
		return xx_purch_amount_budgeted;
	}
	public void setxx_purch_amount_budgeted(BigDecimal xx_purch_amount_budgeted) {
		this.xx_purch_amount_budgeted = xx_purch_amount_budgeted;
	}
	public BigDecimal getxx_quant_budgeted_shopping() {
		return xx_quant_budgeted_shopping;
	}
	public void setxx_quant_budgeted_shopping(BigDecimal xx_quant_budgeted_shopping) {
		this.xx_quant_budgeted_shopping = xx_quant_budgeted_shopping;
	}
	public BigDecimal getxx_amount_placed_nac_purchcost() {
		return xx_amount_placed_nac_purchcost;
	}
	public void setxx_amount_placed_nac_purchcost(
			BigDecimal xx_amount_placed_nac_purchcost) {
		this.xx_amount_placed_nac_purchcost = xx_amount_placed_nac_purchcost;
	}
	public BigDecimal getxx_amount_placed_nac_campra() {
		return xx_amount_placed_nac_campra;
	}
	public void setxx_amount_placed_nac_campra(
			BigDecimal xx_amount_placed_nac_campra) {
		this.xx_amount_placed_nac_campra = xx_amount_placed_nac_campra;
	}
	public BigDecimal getxx_num_nac_shopping_placed() {
		return xx_num_nac_shopping_placed;
	}
	public void setxx_num_nac_shopping_placed(BigDecimal xx_num_nac_shopping_placed) {
		this.xx_num_nac_shopping_placed = xx_num_nac_shopping_placed;
	}
	public BigDecimal getxx_nac_purch_amount_received() {
		return xx_nac_purch_amount_received;
	}
	public void setxx_nac_purch_amount_received(
			BigDecimal xx_nac_purch_amount_received) {
		this.xx_nac_purch_amount_received = xx_nac_purch_amount_received;
	}
	public BigDecimal getxx_quant_purch_nac() {
		return xx_quant_purch_nac;
	}
	public void setxx_quant_purch_nac(BigDecimal xx_quant_purch_nac) {
		this.xx_quant_purch_nac = xx_quant_purch_nac;
	}
	public BigDecimal getxx_purch_amount_placed_costimp() {
		return xx_purch_amount_placed_costimp;
	}
	public void setxx_purch_amount_placed_costimp(
			BigDecimal xx_purch_amount_placed_costimp) {
		this.xx_purch_amount_placed_costimp = xx_purch_amount_placed_costimp;
	}
	public BigDecimal getxx_purch_amount_placed_impd() {
		return xx_purch_amount_placed_impd;
	}
	public void setxx_purch_amount_placed_impd(
			BigDecimal xx_purch_amount_placed_impd) {
		this.xx_purch_amount_placed_impd = xx_purch_amount_placed_impd;
	}
	public BigDecimal getxx_purch_quant_impd_placed() {
		return xx_purch_quant_impd_placed;
	}
	public void setxx_purch_quant_impd_placed(BigDecimal xx_purch_quant_impd_placed) {
		this.xx_purch_quant_impd_placed = xx_purch_quant_impd_placed;
	}
	public BigDecimal getxx_purch_amount_revimpd() {
		return xx_purch_amount_revimpd;
	}
	public void setxx_purch_amount_revimpd(BigDecimal xx_purch_amount_revimpd) {
		this.xx_purch_amount_revimpd = xx_purch_amount_revimpd;
	}
	public BigDecimal getxx_quant_purch_amount_srev() {
		return xx_quant_purch_amount_srev;
	}
	public void setxx_quant_purch_amount_srev(BigDecimal xx_quant_purch_amount_srev) {
		this.xx_quant_purch_amount_srev = xx_quant_purch_amount_srev;
	}
	public BigDecimal getxx_purch_amount_plad_pastmonths() {
		return xx_purch_amount_plad_pastmonths;
	}
	public void setxx_purch_amount_plad_pastmonths(
			BigDecimal xx_purch_amount_plad_pastmonths) {
		this.xx_purch_amount_plad_pastmonths = xx_purch_amount_plad_pastmonths;
	}
	public BigDecimal getxx_purch_quant_placed_months() {
		return xx_purch_quant_placed_months;
	}
	public void setxx_purch_quant_placed_months(
			BigDecimal xx_purch_quant_placed_months) {
		this.xx_purch_quant_placed_months = xx_purch_quant_placed_months;
	}
	public BigDecimal getxx_purch_amount_red_pastmonths() {
		return xx_purch_amount_red_pastmonths;
	}
	public void setxx_purch_amount_red_pastmonths(
			BigDecimal xx_purch_amount_red_pastmonths) {
		this.xx_purch_amount_red_pastmonths = xx_purch_amount_red_pastmonths;
	}
	public BigDecimal getxx_num_months_redshop() {
		return xx_num_months_redshop;
	}
	public void setxx_num_months_redshop(BigDecimal xx_num_months_redshop) {
		this.xx_num_months_redshop = xx_num_months_redshop;
	}
	public BigDecimal getxx_sales_amount_bud() {
		return xx_sales_amount_bud;
	}
	public void setxx_sales_amount_bud(BigDecimal xx_sales_amount_bud) {
		this.xx_sales_amount_bud = xx_sales_amount_bud;
	}
	public BigDecimal getxx_sales_amount_bud2() {
		return xx_sales_amount_bud2;
	}
	public void setxx_sales_amount_bud2(BigDecimal xx_sales_amount_bud2) {
		this.xx_sales_amount_bud2 = xx_sales_amount_bud2;
	}
	public BigDecimal getxx_amount_sale_cost() {
		return xx_amount_sale_cost;
	}
	public void setxx_amount_sale_cost(BigDecimal xx_amount_sale_cost) {
		this.xx_amount_sale_cost = xx_amount_sale_cost;
	}
	public BigDecimal getxx_amount_actual_sale() {
		return xx_amount_actual_sale;
	}
	public void setxx_amount_actual_sale(BigDecimal xx_amount_actual_sale) {
		this.xx_amount_actual_sale = xx_amount_actual_sale;
	}
	public BigDecimal getxx_quant_actual_sale() {
		return xx_quant_actual_sale;
	}
	public void setxx_quant_actual_sale(BigDecimal xx_quant_actual_sale) {
		this.xx_quant_actual_sale = xx_quant_actual_sale;
	}
	public BigDecimal getxx_prom_sale_amount_bud() {
		return xx_prom_sale_amount_bud;
	}
	public void setxx_prom_sale_amount_bud(BigDecimal xx_prom_sale_amount_bud) {
		this.xx_prom_sale_amount_bud = xx_prom_sale_amount_bud;
	}
	public BigDecimal getxx_prom_sale_numbud() {
		return xx_prom_sale_numbud;
	}
	public void setxx_prom_sale_numbud(BigDecimal xx_prom_sale_numbud) {
		this.xx_prom_sale_numbud = xx_prom_sale_numbud;
	}
	public BigDecimal getxx_pect_sale_prombud() {
		return xx_pect_sale_prombud;
	}
	public void setxx_pect_sale_prombud(BigDecimal xx_pect_sale_prombud) {
		this.xx_pect_sale_prombud = xx_pect_sale_prombud;
	}
	public BigDecimal getxx_act_amount_sale_prom() {
		return xx_act_amount_sale_prom;
	}
	public void setxx_act_amount_sale_prom(BigDecimal xx_act_amount_sale_prom) {
		this.xx_act_amount_sale_prom = xx_act_amount_sale_prom;
	}
	public BigDecimal getxx_amount_sale_prom_interests() {
		return xx_amount_sale_prom_interests;
	}
	public void setxx_amount_sale_prom_interests(
			BigDecimal xx_amount_sale_prom_interests) {
		this.xx_amount_sale_prom_interests = xx_amount_sale_prom_interests;
	}
	public BigDecimal getxx_pect_sale_prom_interests() {
		return xx_pect_sale_prom_interests;
	}
	public void setxx_pect_sale_prom_interests(
			BigDecimal xx_pect_sale_prom_interests) {
		this.xx_pect_sale_prom_interests = xx_pect_sale_prom_interests;
	}
	public BigDecimal getxx_amount_sale_frbud() {
		return xx_amount_sale_frbud;
	}
	public void setxx_amount_sale_frbud(BigDecimal xx_amount_sale_frbud) {
		this.xx_amount_sale_frbud = xx_amount_sale_frbud;
	}
	public BigDecimal getxx_bud_amount_frsale() {
		return xx_bud_amount_frsale;
	}
	public void setxx_bud_amount_frsale(BigDecimal xx_bud_amount_frsale) {
		this.xx_bud_amount_frsale = xx_bud_amount_frsale;
	}
	public BigDecimal getxx_port_sale_frbud() {
		return xx_port_sale_frbud;
	}
	public void setxx_port_sale_frbud(BigDecimal xx_port_sale_frbud) {
		this.xx_port_sale_frbud = xx_port_sale_frbud;
	}
	public BigDecimal getxx_act_amount_salefr() {
		return xx_act_amount_salefr;
	}
	public void setxx_act_amount_salefr(BigDecimal xx_act_amount_salefr) {
		this.xx_act_amount_salefr = xx_act_amount_salefr;
	}
	public BigDecimal getxx_amount_sale_fr_interests() {
		return xx_amount_sale_fr_interests;
	}
	public void setxx_amount_sale_fr_interests(
			BigDecimal xx_amount_sale_fr_interests) {
		this.xx_amount_sale_fr_interests = xx_amount_sale_fr_interests;
	}
	public BigDecimal getxx_pert_sale_fr_interests() {
		return xx_pert_sale_fr_interests;
	}
	public void setxx_pert_sale_fr_interests(BigDecimal xx_pert_sale_fr_interests) {
		this.xx_pert_sale_fr_interests = xx_pert_sale_fr_interests;
	}
	public BigDecimal getxx_final_sale_amountbud() {
		return xx_final_sale_amountbud;
	}
	public void setxx_final_sale_amountbud(BigDecimal xx_final_sale_amountbud) {
		this.xx_final_sale_amountbud = xx_final_sale_amountbud;
	}
	public BigDecimal getxx_final_bud_amount_sale() {
		return xx_final_bud_amount_sale;
	}
	public void setxx_final_bud_amount_sale(BigDecimal xx_final_bud_amount_sale) {
		this.xx_final_bud_amount_sale = xx_final_bud_amount_sale;
	}
	public BigDecimal getxx_percent_sqale_finalbud() {
		return xx_percent_sqale_finalbud;
	}
	public void setxx_percent_sqale_finalbud(BigDecimal xx_percent_sqale_finalbud) {
		this.xx_percent_sqale_finalbud = xx_percent_sqale_finalbud;
	}
	public BigDecimal getxx_final_act_amoun_tsale() {
		return xx_final_act_amoun_tsale;
	}
	public void setxx_final_act_amoun_tsale(BigDecimal xx_final_act_amoun_tsale) {
		this.xx_final_act_amoun_tsale = xx_final_act_amoun_tsale;
	}
	public BigDecimal getxx_final_sale_amount_interests() {
		return xx_final_sale_amount_interests;
	}
	public void setxx_final_sale_amount_interests(
			BigDecimal xx_final_sale_amount_interests) {
		this.xx_final_sale_amount_interests = xx_final_sale_amount_interests;
	}
	public BigDecimal getxx_percent_act_final_sale() {
		return xx_percent_act_final_sale;
	}
	public void setxx_percent_act_final_sale(BigDecimal xx_percent_act_final_sale) {
		this.xx_percent_act_final_sale = xx_percent_act_final_sale;
	}
	public BigDecimal getxx_transf_amount_sent() {
		return xx_transf_amount_sent;
	}
	public void setxx_transf_amount_sent(BigDecimal xx_transf_amount_sent) {
		this.xx_transf_amount_sent = xx_transf_amount_sent;
	}
	public BigDecimal getxx_num_transfsent() {
		return xx_num_transfsent;
	}
	public void setxx_num_transfsent(BigDecimal xx_num_transfsent) {
		this.xx_num_transfsent = xx_num_transfsent;
	}
	public BigDecimal getxx_transf_amount_received() {
		return xx_transf_amount_received;
	}
	public void setxx_transf_amount_received(BigDecimal xx_transf_amount_received) {
		this.xx_transf_amount_received = xx_transf_amount_received;
	}
	public BigDecimal getxx_numb_transfrev() {
		return xx_numb_transfrev;
	}
	public void setxx_numb_transfrev(BigDecimal xx_numb_transfrev) {
		this.xx_numb_transfrev = xx_numb_transfrev;
	}
	public BigDecimal getxx_final_inv_amountbud() {
		return xx_final_inv_amountbud;
	}
	public void setxx_final_inv_amountbud(BigDecimal xx_final_inv_amountbud) {
		this.xx_final_inv_amountbud = xx_final_inv_amountbud;
	}
	public BigDecimal getxx_final_inv_amountbud2() {
		return xx_final_inv_amountbud2;
	}
	public void setxx_final_inv_amountbud2(BigDecimal xx_final_inv_amountbud2) {
		this.xx_final_inv_amountbud2 = xx_final_inv_amountbud2;
	}
	public BigDecimal getxx_inv_amount_final_real() {
		return xx_inv_amount_final_real;
	}
	public void setxx_inv_amount_final_real(BigDecimal xx_inv_amount_final_real) {
		this.xx_inv_amount_final_real = xx_inv_amount_final_real;
	}
	public BigDecimal getxx_num_real_finalinv() {
		return xx_num_real_finalinv;
	}
	public void setxx_num_real_finalinv(BigDecimal xx_num_real_finalinv) {
		this.xx_num_real_finalinv = xx_num_real_finalinv;
	}
	public BigDecimal getxx_final_inv_amount_projd() {
		return xx_final_inv_amount_projd;
	}
	public void setxx_final_inv_amount_projd(BigDecimal xx_final_inv_amount_projd) {
		this.xx_final_inv_amount_projd = xx_final_inv_amount_projd;
	}
	public BigDecimal getxx_num_projd_finalinv() {
		return xx_num_projd_finalinv;
	}
	public void setxx_num_projd_finalinv(BigDecimal xx_num_projd_finalinv) {
		this.xx_num_projd_finalinv = xx_num_projd_finalinv;
	}
	public BigDecimal getxx_amount_purchase_limit() {
		return xx_amount_purchase_limit;
	}
	public void setxx_amount_purchase_limit(BigDecimal xx_amount_purchase_limit) {
		this.xx_amount_purchase_limit = xx_amount_purchase_limit;
	}
	public BigDecimal getxx_quantity_purchlimit() {
		return xx_quantity_purchlimit;
	}
	public void setxx_quantity_purchlimit(BigDecimal xx_quantity_purchlimit) {
		this.xx_quantity_purchlimit = xx_quantity_purchlimit;
	}
	public BigDecimal getxx_rotation_bud() {
		return xx_rotation_bud;
	}
	public void setxx_rotation_bud(BigDecimal xx_rotation_bud) {
		this.xx_rotation_bud = xx_rotation_bud;
	}
	public BigDecimal getxx_rotation_real() {
		return xx_rotation_real;
	}
	public void setxx_rotation_real(BigDecimal xx_rotation_real) {
		this.xx_rotation_real = xx_rotation_real;
	}
	public BigDecimal getxx_percn_bud_coverage() {
		return xx_percn_bud_coverage;
	}
	public void setxx_percn_bud_coverage(BigDecimal xx_percn_bud_coverage) {
		this.xx_percn_bud_coverage = xx_percn_bud_coverage;
	}
	public BigDecimal getxx_real_perc_coverage() {
		return xx_real_perc_coverage;
	}
	public void setxx_real_perc_coverage(BigDecimal xx_real_perc_coverage) {
		this.xx_real_perc_coverage = xx_real_perc_coverage;
	}
	public BigDecimal getxx_margac_cording_budpurch() {
		return xx_margac_cording_budpurch;
	}
	public void setxx_margac_cording_budpurch(BigDecimal xx_margac_cording_budpurch) {
		this.xx_margac_cording_budpurch = xx_margac_cording_budpurch;
	}
	public BigDecimal getxx_margac_cording_buyreal() {
		return xx_margac_cording_buyreal;
	}
	public void setxx_margac_cording_buyreal(BigDecimal xx_margac_cording_buyreal) {
		this.xx_margac_cording_buyreal = xx_margac_cording_buyreal;
	}
	public BigDecimal getxx_lisck_gross_marg_perctbud() {
		return xx_lisck_gross_marg_perctbud;
	}
	public void setxx_lisck_gross_marg_perctbud(
			BigDecimal xx_lisck_gross_marg_perctbud) {
		this.xx_lisck_gross_marg_perctbud = xx_lisck_gross_marg_perctbud;
	}
	public BigDecimal getxx_lisck_gross_marg_pertreal() {
		return xx_lisck_gross_marg_pertreal;
	}
	public void setxx_lisck_gross_marg_pertreal(
			BigDecimal xx_lisck_gross_marg_pertreal) {
		this.xx_lisck_gross_marg_pertreal = xx_lisck_gross_marg_pertreal;
	}
	public BigDecimal getxx_net_marg_pertcattle_bud() {
		return xx_net_marg_pertcattle_bud;
	}
	public void setxx_net_marg_pertcattle_bud(BigDecimal xx_net_marg_pertcattle_bud) {
		this.xx_net_marg_pertcattle_bud = xx_net_marg_pertcattle_bud;
	}
	public BigDecimal getxx_net_marg_pertroyallivestock() {
		return xx_net_marg_pertroyallivestock;
	}
	public void setxx_net_marg_pertroyallivestock(
			BigDecimal xx_net_marg_pertroyallivestock) {
		this.xx_net_marg_pertroyallivestock = xx_net_marg_pertroyallivestock;
	}
	public BigDecimal getxx_by_winmarg_pertbud() {
		return xx_by_winmarg_pertbud;
	}
	public void setxx_by_winmarg_pertbud(BigDecimal xx_by_winmarg_pertbud) {
		this.xx_by_winmarg_pertbud = xx_by_winmarg_pertbud;
	}
	public BigDecimal getxx_pert_wing_margreal() {
		return xx_pert_wing_margreal;
	}
	public void setxx_pert_wing_margreal(BigDecimal xx_pert_wing_margreal) {
		this.xx_pert_wing_margreal = xx_pert_wing_margreal;
	}
	public BigDecimal getxx_budd_decline() {
		return xx_budd_decline;
	}
	public void setxx_bud_ddecline(BigDecimal xx_budd_decline) {
		this.xx_budd_decline = xx_budd_decline;
	}
	public BigDecimal getxx_real_decline() {
		return xx_real_decline;
	}
	public void setxx_real_decline(BigDecimal xx_real_decline) {
		this.xx_real_decline = xx_real_decline;
	}
	public BigDecimal getxx_vmr_line_id() {
		return xx_vmr_line_id;
	}
	public void setxx_vmr_line_id(BigDecimal xx_vmr_line_id) {
		this.xx_vmr_line_id = xx_vmr_line_id;
	}
	public BigDecimal getxx_vmr_section_id() {
		return xx_vmr_section_id;
	}
	public void setxx_vmr_section_id(BigDecimal xx_vmr_section_id) {
		this.xx_vmr_section_id = xx_vmr_section_id;
	}
	public BigDecimal getxx_vmr_category_id() {
		return xx_vmr_category_id;
	}
	public void setxx_vmr_category_id(BigDecimal xx_vmr_category_id) {
		this.xx_vmr_category_id = xx_vmr_category_id;
	}
	public BigDecimal getm_warehouse_id() {
		return m_warehouse_id;
	}
	public void setM_warehouse_id(BigDecimal m_warehouse_id) {
		this.m_warehouse_id = m_warehouse_id;
	}
	public BigDecimal getxx_vmr_department_id() {
		return xx_vmr_department_id;
	}
	public void setxx_vmr_department_id(BigDecimal xx_vmr_department_id) {
		this.xx_vmr_department_id = xx_vmr_department_id;
	}
	public BigDecimal getxx_linecode() {
		return xx_linecode;
	}
	public void setxx_linecode(BigDecimal xx_linecode) {
		this.xx_linecode = xx_linecode;
	}
	public BigDecimal getxx_vmr_brand_id() {
		return xx_vmr_brand_id;
	}
	public void setxx_vmr_brand_id(BigDecimal xx_vmr_brand_id) {
		this.xx_vmr_brand_id = xx_vmr_brand_id;
	}
	public BigDecimal getxx_receipt_pvp() {
		return xx_receipt_pvp;
	}
	public void setxx_receipt_pvp(BigDecimal xx_receipt_pvp) {
		this.xx_receipt_pvp = xx_receipt_pvp;
	}
	public BigDecimal getxx_receipt_qty() {
		return xx_receipt_qty;
	}
	public void setxx_receipt_qty(BigDecimal xx_receipt_qty) {
		this.xx_receipt_qty = xx_receipt_qty;
	}
	public BigDecimal getxx_returns_pvp() {
		return xx_returns_pvp;
	}
	public void setxx_returns_pvp(BigDecimal xx_returns_pvp) {
		this.xx_returns_pvp = xx_returns_pvp;
	}
	public BigDecimal getxx_returns_qty() {
		return xx_returns_qty;
	}
	public void setxx_returns_qty(BigDecimal xx_returns_qty) {
		this.xx_returns_qty = xx_returns_qty;
	}
	public BigDecimal getxx_placed_order_pvp_adjustment() {
		return xx_placed_order_pvp_adjustment;
	}
	public void setxx_placed_order_pvp_adjustment(
			BigDecimal xx_placed_order_pvp_adjustment) {
		this.xx_placed_order_pvp_adjustment = xx_placed_order_pvp_adjustment;
	}
}


