/**
 * Conexion.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.abc.otb.webservice;

public interface Conexion extends java.rmi.Remote {
    public int realizarSentencia(java.lang.String sentenciaSQL, java.lang.String empresa) throws java.rmi.RemoteException;
    public java.util.Map[] realizarConsulta(java.lang.String sql, java.lang.String empresa) throws java.rmi.RemoteException;
    public int realizarLoteSentencias(java.lang.String[] elLote, java.lang.String empresa) throws java.rmi.RemoteException;
    public com.abc.otb.logico.ConfiguracionBaseDatos obtenerDatosXML(java.lang.String empresa) throws java.rmi.RemoteException;
    public void escribirDatosXML(java.lang.String[] datosBeco, java.lang.String[] datosAmand, java.lang.String[] datosAbstracta, java.lang.String[] datosBcMuebles, java.lang.String[] datosCapuy) throws java.rmi.RemoteException;
}
