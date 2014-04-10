/**
 * ConfiguracionBaseDatos.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.abc.otb.logico;

public class ConfiguracionBaseDatos  implements java.io.Serializable {
    private java.lang.String campo;

    private java.lang.String nombre;

    private java.lang.String ojdbc;

    private java.lang.String password;

    private java.lang.String url;

    private java.lang.String usuario;

    public ConfiguracionBaseDatos() {
    }

    public ConfiguracionBaseDatos(
           java.lang.String campo,
           java.lang.String nombre,
           java.lang.String ojdbc,
           java.lang.String password,
           java.lang.String url,
           java.lang.String usuario) {
           this.campo = campo;
           this.nombre = nombre;
           this.ojdbc = ojdbc;
           this.password = password;
           this.url = url;
           this.usuario = usuario;
    }


    /**
     * Gets the campo value for this ConfiguracionBaseDatos.
     * 
     * @return campo
     */
    public java.lang.String getCampo() {
        return campo;
    }


    /**
     * Sets the campo value for this ConfiguracionBaseDatos.
     * 
     * @param campo
     */
    public void setCampo(java.lang.String campo) {
        this.campo = campo;
    }


    /**
     * Gets the nombre value for this ConfiguracionBaseDatos.
     * 
     * @return nombre
     */
    public java.lang.String getNombre() {
        return nombre;
    }


    /**
     * Sets the nombre value for this ConfiguracionBaseDatos.
     * 
     * @param nombre
     */
    public void setNombre(java.lang.String nombre) {
        this.nombre = nombre;
    }


    /**
     * Gets the ojdbc value for this ConfiguracionBaseDatos.
     * 
     * @return ojdbc
     */
    public java.lang.String getOjdbc() {
        return ojdbc;
    }


    /**
     * Sets the ojdbc value for this ConfiguracionBaseDatos.
     * 
     * @param ojdbc
     */
    public void setOjdbc(java.lang.String ojdbc) {
        this.ojdbc = ojdbc;
    }


    /**
     * Gets the password value for this ConfiguracionBaseDatos.
     * 
     * @return password
     */
    public java.lang.String getPassword() {
        return password;
    }


    /**
     * Sets the password value for this ConfiguracionBaseDatos.
     * 
     * @param password
     */
    public void setPassword(java.lang.String password) {
        this.password = password;
    }


    /**
     * Gets the url value for this ConfiguracionBaseDatos.
     * 
     * @return url
     */
    public java.lang.String getUrl() {
        return url;
    }


    /**
     * Sets the url value for this ConfiguracionBaseDatos.
     * 
     * @param url
     */
    public void setUrl(java.lang.String url) {
        this.url = url;
    }


    /**
     * Gets the usuario value for this ConfiguracionBaseDatos.
     * 
     * @return usuario
     */
    public java.lang.String getUsuario() {
        return usuario;
    }


    /**
     * Sets the usuario value for this ConfiguracionBaseDatos.
     * 
     * @param usuario
     */
    public void setUsuario(java.lang.String usuario) {
        this.usuario = usuario;
    }

    private java.lang.Object __equalsCalc = null;
    public synchronized boolean equals(java.lang.Object obj) {
        if (!(obj instanceof ConfiguracionBaseDatos)) return false;
        ConfiguracionBaseDatos other = (ConfiguracionBaseDatos) obj;
        if (obj == null) return false;
        if (this == obj) return true;
        if (__equalsCalc != null) {
            return (__equalsCalc == obj);
        }
        __equalsCalc = obj;
        boolean _equals;
        _equals = true && 
            ((this.campo==null && other.getCampo()==null) || 
             (this.campo!=null &&
              this.campo.equals(other.getCampo()))) &&
            ((this.nombre==null && other.getNombre()==null) || 
             (this.nombre!=null &&
              this.nombre.equals(other.getNombre()))) &&
            ((this.ojdbc==null && other.getOjdbc()==null) || 
             (this.ojdbc!=null &&
              this.ojdbc.equals(other.getOjdbc()))) &&
            ((this.password==null && other.getPassword()==null) || 
             (this.password!=null &&
              this.password.equals(other.getPassword()))) &&
            ((this.url==null && other.getUrl()==null) || 
             (this.url!=null &&
              this.url.equals(other.getUrl()))) &&
            ((this.usuario==null && other.getUsuario()==null) || 
             (this.usuario!=null &&
              this.usuario.equals(other.getUsuario())));
        __equalsCalc = null;
        return _equals;
    }

    private boolean __hashCodeCalc = false;
    public synchronized int hashCode() {
        if (__hashCodeCalc) {
            return 0;
        }
        __hashCodeCalc = true;
        int _hashCode = 1;
        if (getCampo() != null) {
            _hashCode += getCampo().hashCode();
        }
        if (getNombre() != null) {
            _hashCode += getNombre().hashCode();
        }
        if (getOjdbc() != null) {
            _hashCode += getOjdbc().hashCode();
        }
        if (getPassword() != null) {
            _hashCode += getPassword().hashCode();
        }
        if (getUrl() != null) {
            _hashCode += getUrl().hashCode();
        }
        if (getUsuario() != null) {
            _hashCode += getUsuario().hashCode();
        }
        __hashCodeCalc = false;
        return _hashCode;
    }

    // Type metadata
    private static org.apache.axis.description.TypeDesc typeDesc =
        new org.apache.axis.description.TypeDesc(ConfiguracionBaseDatos.class, true);

    static {
        typeDesc.setXmlType(new javax.xml.namespace.QName("http://logico.otb.abc.com", "ConfiguracionBaseDatos"));
        org.apache.axis.description.ElementDesc elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("campo");
        elemField.setXmlName(new javax.xml.namespace.QName("http://logico.otb.abc.com", "campo"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("nombre");
        elemField.setXmlName(new javax.xml.namespace.QName("http://logico.otb.abc.com", "nombre"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("ojdbc");
        elemField.setXmlName(new javax.xml.namespace.QName("http://logico.otb.abc.com", "ojdbc"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("password");
        elemField.setXmlName(new javax.xml.namespace.QName("http://logico.otb.abc.com", "password"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("url");
        elemField.setXmlName(new javax.xml.namespace.QName("http://logico.otb.abc.com", "url"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
        elemField = new org.apache.axis.description.ElementDesc();
        elemField.setFieldName("usuario");
        elemField.setXmlName(new javax.xml.namespace.QName("http://logico.otb.abc.com", "usuario"));
        elemField.setXmlType(new javax.xml.namespace.QName("http://www.w3.org/2001/XMLSchema", "string"));
        elemField.setNillable(true);
        typeDesc.addFieldDesc(elemField);
    }

    /**
     * Return type metadata object
     */
    public static org.apache.axis.description.TypeDesc getTypeDesc() {
        return typeDesc;
    }

    /**
     * Get Custom Serializer
     */
    public static org.apache.axis.encoding.Serializer getSerializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanSerializer(
            _javaType, _xmlType, typeDesc);
    }

    /**
     * Get Custom Deserializer
     */
    public static org.apache.axis.encoding.Deserializer getDeserializer(
           java.lang.String mechType, 
           java.lang.Class _javaType,  
           javax.xml.namespace.QName _xmlType) {
        return 
          new  org.apache.axis.encoding.ser.BeanDeserializer(
            _javaType, _xmlType, typeDesc);
    }

}
