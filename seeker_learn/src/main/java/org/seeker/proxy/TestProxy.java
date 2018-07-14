package org.vegetto.proxy;

import org.apache.log4j.Logger;
import org.seeker.common.util.print.LoggerUtils;
import org.vegetto.proxy.book.BookFacade;
import org.vegetto.proxy.book.MyDao;
import org.vegetto.proxy.book.impl.BookFacadeImpl;
import org.vegetto.proxy.book.impl.MyDaoImpl;

public class TestProxy {
	protected static Logger logger = Logger.getLogger(TestProxy.class.getName());
	public static void main(String[] args) {
		MyHandler proxy = new MyHandler();
		proxy.bind(LoggerUtils.class);
		LoggerUtils.info("11", logger);
		BookFacade bookProxy = (BookFacade) proxy.bind(new BookFacadeImpl());
		bookProxy.addBook();
		
		MyDao dao=(MyDao) proxy.bind(new MyDaoImpl());
		
//		dao.add();
//		dao.update();
//		dao.load("");
	}

}
