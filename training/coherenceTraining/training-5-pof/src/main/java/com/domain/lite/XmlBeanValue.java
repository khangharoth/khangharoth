package com.domain.lite;


import com.tangosol.run.xml.XmlBean;

import java.sql.Date;


/**
* Example data object using Tangosol's XmlBean Serialization
*
* @author rmisek 2005.08.18
*/
public class XmlBeanValue extends XmlBean
    {
    public XmlBeanValue()
        {
        }

    public String getId()
        {
        return m_sId;
        }

    public void setId(String sString)
        {
        m_sId = sString;
        }

    public Date getDate()
        {
        return m_Date;
        }

    public void setDate(Date date)
        {
        m_Date = date;
        }

    public Long getLong()
        {
        return m_Long;
        }

    public void setLong(Long aLong)
        {
        m_Long = aLong;
        }

    public Integer getInteger()
        {
        return m_Integer;
        }

    public void setInteger(Integer integer)
        {
        m_Integer = integer;
        }

    public Float getFloat()
        {
        return m_Float;
        }

    public void setFloat(Float aFloat)
        {
        m_Float = aFloat;
        }

    public Double getDouble()
        {
        return m_Double;
        }

    public void setDouble(Double aDouble)
        {
        m_Double = aDouble;
        }

    public ChildXmlBeanValue getChild()
        {
        return m_child;
        }

    public void setChild(ChildXmlBeanValue child)
        {
        m_child = child;
        }

    public String            m_sId     = new String(new char[20]);
    public Date              m_Date    = new Date(System.currentTimeMillis());
    public Long              m_Long    = new Long("0");
    public Integer           m_Integer = new Integer("0");
    public Float             m_Float   = new Float("0");
    public Double            m_Double  = new Double("0");
    public ChildXmlBeanValue m_child   = new ChildXmlBeanValue();
    }
