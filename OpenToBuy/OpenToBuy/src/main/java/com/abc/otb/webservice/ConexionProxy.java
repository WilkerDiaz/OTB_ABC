package com.abc.otb.webservice;

public class ConexionProxy implements com.abc.otb.webservice.Conexion {
  private String _endpoint = null;
  private com.abc.otb.webservice.Conexion conexion = null;
  
  public ConexionProxy() {
    _initConexionProxy();
  }
  
  public ConexionProxy(String endpoint) {
    _endpoint = endpoint;
    _initConexionProxy();
  }
  
  private void _initConexionProxy() {
    try {
      conexion = (new com.abc.otb.webservice.ConexionServiceLocator()).getConexion();
      if (conexion != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)conexion)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)conexion)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (conexion != null)
      ((javax.xml.rpc.Stub)conexion)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public com.abc.otb.webservice.Conexion getConexion() {
    if (conexion == null)
      _initConexionProxy();
    return conexion;
  }
  
  public int realizarSentencia(java.lang.String sentenciaSQL, java.lang.String empresa) throws java.rmi.RemoteException{
    if (conexion == null)
      _initConexionProxy();
    return conexion.realizarSentencia(sentenciaSQL, empresa);
  }
  
  public java.util.Map[] realizarConsulta(java.lang.String sql, java.lang.String empresa) throws java.rmi.RemoteException{
    if (conexion == null)
      _initConexionProxy();
    return conexion.realizarConsulta(sql, empresa);
  }
  
  public int realizarLoteSentencias(java.lang.String[] elLote, java.lang.String empresa) throws java.rmi.RemoteException{
    if (conexion == null)
      _initConexionProxy();
    return conexion.realizarLoteSentencias(elLote, empresa);
  }
  
  public com.abc.otb.logico.ConfiguracionBaseDatos obtenerDatosXML(java.lang.String empresa) throws java.rmi.RemoteException{
    if (conexion == null)
      _initConexionProxy();
    return conexion.obtenerDatosXML(empresa);
  }
  
  public void escribirDatosXML(java.lang.String[] datosBeco, java.lang.String[] datosAmand, java.lang.String[] datosAbstracta, java.lang.String[] datosBcMuebles, java.lang.String[] datosCapuy) throws java.rmi.RemoteException{
    if (conexion == null)
      _initConexionProxy();
    conexion.escribirDatosXML(datosBeco, datosAmand, datosAbstracta, datosBcMuebles, datosCapuy);
  }
  
  
}