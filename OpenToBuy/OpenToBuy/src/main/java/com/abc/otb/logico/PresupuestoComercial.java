package com.abc.otb.logico;

import java.math.BigDecimal;

/**
 * 
 * @author ccastillo
 *
 */
public class PresupuestoComercial {

	//variables 
	private String mesNombre;
	private int anio;
	private int mesNumero;
	
	//para el tipo de registro Bolivares y piezas
	private BigDecimal inv_inicial_presupuestado; 
	private BigDecimal inv_inicial_real;
	private BigDecimal compras_presupuestadas;
	private BigDecimal compras_colocadas_total;
	private BigDecimal compras_colocadas_nacionales;
	private BigDecimal compras_colocadas_importadas;
	private BigDecimal compras_colocadas_mes_anteriores;
	private BigDecimal compras_recibidas_total;
	private BigDecimal compras_chequeadas_total;
	private BigDecimal compras_chequeadas_nacional;
	private BigDecimal compras_chequeadas_importadas;
	private BigDecimal compras_chequedas_mes_anterior;
	private BigDecimal ventas_presupuestadas;
	private BigDecimal ventas_reales;
	private BigDecimal traspasos_enviados;
	private BigDecimal traspasos_recibidos;
	private BigDecimal devoluciones;
	private BigDecimal rebajas_total_presupuestas;
	private BigDecimal rebajas_total_real;
	private BigDecimal rebajas_promocionales_presupuestadas;
	private BigDecimal rebajas_promocionales_real;
	private BigDecimal rebajas_fr_presupuestadas;
	private BigDecimal rebajas_fr_real;
	private BigDecimal rebajas_definitivas_presupuestadas;
	private BigDecimal rebajas_definitivas_real;
	private BigDecimal inv_final_presupuestado;
	private BigDecimal inv_final_real;
	private BigDecimal inv_final_proyectado;
	private BigDecimal limite_de_compras;
	
	//para el tipo de registro rotacion/cobertura
	private BigDecimal rotacion_presupuestada;
	private BigDecimal rotacion_real;
	private BigDecimal cobertura_presupuestada;
	private BigDecimal cobertura_real;
	  
	
	public String getMesNombre() {
		return mesNombre;
	}
	public void setMesNombre(String mesNombre) {
		this.mesNombre = mesNombre;
	}
	public int getMesNumero() {
		return mesNumero;
	}
	public void setMesNumero(int mesNumero) {
		this.mesNumero = mesNumero;
	}
	public int getAnio() {
		return anio;
	}
	public void setAnio(int anio) {
		this.anio = anio;
	}
	public BigDecimal getinv_inicial_presupuestado() {
		return inv_inicial_presupuestado;
	}
	public void setinv_inicial_presupuestado(BigDecimal inv_inicial_presupuestado) {
		this.inv_inicial_presupuestado = inv_inicial_presupuestado;
	}
	public BigDecimal getinv_inicial_real() {
		return inv_inicial_real;
	}
	public void setinv_inicial_real(BigDecimal inv_inicial_real) {
		this.inv_inicial_real = inv_inicial_real;
	}
	public BigDecimal getcompras_presupuestadas() {
		return compras_presupuestadas;
	}
	public void setcompras_presupuestadas(BigDecimal compras_presupuestadas) {
		this.compras_presupuestadas = compras_presupuestadas;
	}
	public BigDecimal getcompras_colocadas_total() {
		return compras_colocadas_total;
	}
	public void setcompras_colocadas_total(BigDecimal compras_colocadas_total) {
		this.compras_colocadas_total = compras_colocadas_total;
	}
	public BigDecimal getcompras_colocadas_nacionales() {
		return compras_colocadas_nacionales;
	}
	public void setcompras_colocadas_nacionales(
			BigDecimal compras_colocadas_nacionales) {
		this.compras_colocadas_nacionales = compras_colocadas_nacionales;
	}
	public BigDecimal getcompras_colocadas_importadas() {
		return compras_colocadas_importadas;
	}
	public void setcompras_colocadas_importadas(
			BigDecimal compras_colocadas_importadas) {
		this.compras_colocadas_importadas = compras_colocadas_importadas;
	}
	public BigDecimal getcompras_colocadas_mes_anteriores() {
		return compras_colocadas_mes_anteriores;
	}
	public void setcompras_colocadas_mes_anteriores(
			BigDecimal compras_colocadas_mes_anteriores) {
		this.compras_colocadas_mes_anteriores = compras_colocadas_mes_anteriores;
	}
	public BigDecimal getcompras_recibidas_total() {
		return compras_recibidas_total;
	}
	public void setcompras_recibidas_total(BigDecimal compras_recibidas_total) {
		this.compras_recibidas_total = compras_recibidas_total;
	}
	public BigDecimal getcompras_chequeadas_total() {
		return compras_chequeadas_total;
	}
	public void setcompras_chequeadas_total(BigDecimal compras_chequeadas_total) {
		this.compras_chequeadas_total = compras_chequeadas_total;
	}
	public BigDecimal getcompras_chequeadas_nacional() {
		return compras_chequeadas_nacional;
	}
	public void setcompras_chequeadas_nacional(
			BigDecimal compras_chequeadas_nacional) {
		this.compras_chequeadas_nacional = compras_chequeadas_nacional;
	}
	public BigDecimal getcompras_chequeadas_importadas() {
		return compras_chequeadas_importadas;
	}
	public void setcompras_chequeadas_importadas(
			BigDecimal compras_chequeadas_importadas) {
		this.compras_chequeadas_importadas = compras_chequeadas_importadas;
	}
	public BigDecimal getcompras_chequedas_mes_anterior() {
		return compras_chequedas_mes_anterior;
	}
	public void setcompras_chequedas_mes_anterior(
			BigDecimal compras_chequedas_mes_anterior) {
		this.compras_chequedas_mes_anterior = compras_chequedas_mes_anterior;
	}
	public BigDecimal getventas_presupuestadas() {
		return ventas_presupuestadas;
	}
	public void setventas_presupuestadas(BigDecimal ventas_presupuestadas) {
		this.ventas_presupuestadas = ventas_presupuestadas;
	}
	public BigDecimal getventas_reales() {
		return ventas_reales;
	}
	public void setventas_reales(BigDecimal ventas_reales) {
		this.ventas_reales = ventas_reales;
	}
	public BigDecimal gettraspasos_enviados() {
		return traspasos_enviados;
	}
	public void settraspasos_enviados(BigDecimal traspasos_enviados) {
		this.traspasos_enviados = traspasos_enviados;
	}
	public BigDecimal gettraspasos_recibidos() {
		return traspasos_recibidos;
	}
	public void settraspasos_recibidos(BigDecimal traspasos_recibidos) {
		this.traspasos_recibidos = traspasos_recibidos;
	}
	public BigDecimal getdevoluciones() {
		return devoluciones;
	}
	public void setdevoluciones(BigDecimal devoluciones) {
		this.devoluciones = devoluciones;
	}
	public BigDecimal getrebajas_total_presupuestas() {
		return rebajas_total_presupuestas;
	}
	public void setrebajas_total_presupuestas(BigDecimal rebajas_total_presupuestas) {
		this.rebajas_total_presupuestas = rebajas_total_presupuestas;
	}
	public BigDecimal getrebajas_total_real() {
		return rebajas_total_real;
	}
	public void setrebajas_total_real(BigDecimal rebajas_total_real) {
		this.rebajas_total_real = rebajas_total_real;
	}
	public BigDecimal getrebajas_promocionales_presupuestadas() {
		return rebajas_promocionales_presupuestadas;
	}
	public void setrebajas_promocionales_presupuestadas(
			BigDecimal rebajas_promocionales_presupuestadas) {
		this.rebajas_promocionales_presupuestadas = rebajas_promocionales_presupuestadas;
	}
	public BigDecimal getrebajas_promocionales_real() {
		return rebajas_promocionales_real;
	}
	public void setrebajas_promocionales_real(BigDecimal rebajas_promocionales_real) {
		this.rebajas_promocionales_real = rebajas_promocionales_real;
	}
	public BigDecimal getrebajas_fr_presupuestadas() {
		return rebajas_fr_presupuestadas;
	}
	public void setrebajas_fr_presupuestadas(BigDecimal rebajas_fr_presupuestadas) {
		this.rebajas_fr_presupuestadas = rebajas_fr_presupuestadas;
	}
	public BigDecimal getrebajas_fr_real() {
		return rebajas_fr_real;
	}
	public void setrebajas_fr_real(BigDecimal rebajas_fr_real) {
		this.rebajas_fr_real = rebajas_fr_real;
	}
	public BigDecimal getrebajas_definitivas_presupuestadas() {
		return rebajas_definitivas_presupuestadas;
	}
	public void setrebajas_definitivas_presupuestadas(
			BigDecimal rebajas_definitivas_presupuestadas) {
		this.rebajas_definitivas_presupuestadas = rebajas_definitivas_presupuestadas;
	}
	public BigDecimal getrebajas_definitivas_real() {
		return rebajas_definitivas_real;
	}
	public void setrebajas_definitivas_real(BigDecimal rebajas_definitivas_real) {
		this.rebajas_definitivas_real = rebajas_definitivas_real;
	}
	public BigDecimal getinv_final_presupuestado() {
		return inv_final_presupuestado;
	}
	public void setinv_final_presupuestado(BigDecimal inv_final_presupuestado) {
		this.inv_final_presupuestado = inv_final_presupuestado;
	}
	public BigDecimal getinv_final_real() {
		return inv_final_real;
	}
	public void setinv_final_real(BigDecimal inv_final_real) {
		this.inv_final_real = inv_final_real;
	}
	public BigDecimal getinv_final_proyectado() {
		return inv_final_proyectado;
	}
	public void setinv_final_proyectado(BigDecimal inv_final_proyectado) {
		this.inv_final_proyectado = inv_final_proyectado;
	}
	public BigDecimal getlimite_de_compras() {
		return limite_de_compras;
	}
	public void setlimite_de_compras(BigDecimal limite_de_compras) {
		this.limite_de_compras = limite_de_compras;
	}
	public BigDecimal getrotacion_presupuestada() {
		return rotacion_presupuestada;
	}
	public void setrotacion_presupuestada(BigDecimal rotacion_presupuestada) {
		this.rotacion_presupuestada = rotacion_presupuestada;
	}
	public BigDecimal getrotacion_real() {
		return rotacion_real;
	}
	public void setrotacion_real(BigDecimal rotacion_real) {
		this.rotacion_real = rotacion_real;
	}
	public BigDecimal getcobertura_presupuestada() {
		return cobertura_presupuestada;
	}
	public void setcobertura_presupuestada(BigDecimal cobertura_presupuestada) {
		this.cobertura_presupuestada = cobertura_presupuestada;
	}
	public BigDecimal getcobertura_real() {
		return cobertura_real;
	}
	public void setcobertura_real(BigDecimal cobertura_real) {
		this.cobertura_real = cobertura_real;
	}
	
}
