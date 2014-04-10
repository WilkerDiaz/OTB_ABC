/**
 * ConexionService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.abc.otb.webservice;

public interface ConexionService extends javax.xml.rpc.Service {
    public java.lang.String getConexionAddress();

    public com.abc.otb.webservice.Conexion getConexion() throws javax.xml.rpc.ServiceException;

    public com.abc.otb.webservice.Conexion getConexion(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
