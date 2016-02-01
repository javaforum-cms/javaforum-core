// license-header java merge-point
//
// Attention: Generated code! Do not modify by hand!
// Generated by: HibernateStringClobType.vsl in andromda-hibernate-cartridge.
//
package org.andromda.persistence.hibernate.usertypes;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import net.sf.hibernate.HibernateException;
import net.sf.hibernate.UserType;

/**
 * <p>
 * A hibernate user type which converts a Clob into a String and back again.
 * </p>
 */
public class HibernateStringClobType
    implements UserType
{
    /**
     * The serial version UID of this class. Needed for serialization.
     */
    private static final long serialVersionUID = 10000L;

    /**
     * @see net.sf.hibernate.UserType#sqlTypes()
     */
    public int[] sqlTypes()
    {
        return new int[] {Types.CLOB};
    }

    /**
     * @see net.sf.hibernate.UserType#returnedClass()
     */
    public Class returnedClass()
    {
        return String.class;
    }

    /**
     * @see net.sf.hibernate.UserType#equals(java.lang.Object, java.lang.Object)
     */
    public boolean equals(
        Object x,
        Object y)
        throws HibernateException
    {
        boolean equal = false;
        if (x == null || y == null)
        {
            equal = false;
        }
        else if (!(x instanceof String) || !(y instanceof String))
        {
            equal = false;
        }
        else
        {
            equal = ((String)x).equals(y);
        }
        return equal;
    }

    /**
     * @see net.sf.hibernate.UserType#nullSafeGet(java.sql.ResultSet, java.lang.String[], java.lang.Object)
     */
    public Object nullSafeGet(
        ResultSet resultSet,
        String[] names,
        Object owner)
        throws HibernateException, SQLException
    {
        final StringBuffer buffer = new StringBuffer();
        try
        {
            //First we get the stream
            Reader inputStream = resultSet.getCharacterStream(names[0]);
            if (inputStream == null)
            {
                return null;
            }
            char[] buf = new char[1024];
            int read = -1;

            while ((read = inputStream.read(buf)) > 0)
            {
                buffer.append(new String(
                        buf,
                        0,
                        read));
            }
            inputStream.close();
        }
        catch (IOException exception)
        {
            throw new HibernateException("Unable to read from resultset", exception);
        }
        return buffer.toString();
    }

    /**
     * @see net.sf.hibernate.UserType#nullSafeSet(java.sql.PreparedStatement, java.lang.Object, int)
     */
    public void nullSafeSet(
        PreparedStatement preparedStatement,
        Object data,
        int index)
        throws HibernateException, SQLException
    {
        if (data != null)
        {
            StringReader r = new StringReader((String)data);
            preparedStatement.setCharacterStream(
                index,
                r,
                ((String)data).length());
        }
        else
        {
            preparedStatement.setNull(
                index,
                sqlTypes()[0]);
        }
    }

    /**
     * @see net.sf.hibernate.UserType#deepCopy(java.lang.Object)
     */
    public Object deepCopy(Object value)
        throws HibernateException
    {
        String ret = null;
        value = value == null ? new String() : value;
        String in = (String)value;
        int len = in.length();
        char[] buf = new char[len];

        for (int i = 0; i < len; i++)
        {
            buf[i] = in.charAt(i);
        }
        ret = new String(buf);
        return ret;
    }

    /**
     * @see net.sf.hibernate.UserType#isMutable()
     */
    public boolean isMutable()
    {
        return false;
    }
}