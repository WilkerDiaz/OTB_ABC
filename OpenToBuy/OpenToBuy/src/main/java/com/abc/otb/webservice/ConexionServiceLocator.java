/**
 * ConexionServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.abc.otb.webservice;

public class ConexionServiceLocator extends org.apache.axis.client.Service implements com.abc.otb.webservice.ConexionService {

    public ConexionServiceLocator() {
    }


    public ConexionServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public ConexionServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for Conexion
    private java.lang.String Conexion_address = "http://localhost:8080/WebServices/services/Conexion";

    public java.lang.String getConexionAddress() {
        return Conexion_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String ConexionWSDDServiceName = "Conexion";

    public java.lang.String getConexionWSDDServiceName() {
        return ConexionWSDDServiceName;
    }

    public void setConexionWSDDServiceName(java.lang.String name) {
        ConexionWSDDServiceName = name;
    }

    public com.abc.otb.webservice.Conexion getConexion() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(Conexion_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getConexion(endpoint);
    }

    public com.abc.otb.webservice.Conexion getConexion(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.abc.otb.webservice.ConexionSoapBindingStub _stub = new com.abc.otb.webservice.ConexionSoapBindingStub(portAddress, this);
            _stub.setPortName(getConexionWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setConexionEndpointAddress(java.lang.String address) {
        Conexion_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.abc.otb.webservice.Conexion.class.isAssignableFrom(serviceEndpointInterface)) {
                com.abc.otb.webservice.ConexionSoapBindingStub _stub = new com.abc.otb.webservice.ConexionSoapBindingStub(new java.net.URL(Conexion_address), this);
                _stub.setPortName(getConexionWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("Conexion".equals(inputPortName)) {
            return getConexion();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://webservice.otb.abc.com", "ConexionService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://webservice.otb.abc.com", "Conexion"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("Conexion".equals(portName)) {
            setConexionEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
