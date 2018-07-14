package org.vegetto.jta;

import javax.naming.NamingException;
import javax.sql.XAConnection;
import javax.transaction.xa.XAResource;

import com.atomikos.jdbc.AtomikosDataSourceBean;

public class TestJTA {
	public static void main(String[] args) throws Exception {
		AtomikosDataSourceBean bean = new AtomikosDataSourceBean();
		bean.getReference();
		XAResource xar = bean.getXaDataSource().getXAConnection().getXAResource();
		XAConnection connection = bean.getXaDataSource().getXAConnection();
//		connection.xar.start(arg0, arg1)
	}
}
