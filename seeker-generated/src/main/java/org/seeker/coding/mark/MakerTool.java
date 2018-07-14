package org.seeker.coding.mark;

import java.io.IOException;

import org.seeker.coding.mark.config.ConfigMaker;
import org.seeker.coding.mark.java.BizImplMaker;
import org.seeker.coding.mark.java.DaoImplMaker;
import org.seeker.coding.mark.java.DaoMaker;
import org.seeker.coding.mark.java.PoMaker;
import org.seeker.coding.mark.java.strtus2.ActionMaker;
import org.seeker.coding.mark.page.excel.JspAddEl;
import org.seeker.coding.mark.page.excel.JspDetailEl;
import org.seeker.coding.mark.page.excel.JspListEl;
import org.seeker.coding.pdm.xml.MyTable;
import org.seeker.coding.mark.java.BizMaker;
import org.seeker.coding.mark.page.excel.JspUpdateEl;

/**
 * 创建java类 包括bo vo dao 等 2012.5.13
 */
public class MakerTool {
	
	

	/**
	 * 创建java类的入口方法
	 * 
	 * @param table
	 * @param filePath -
	 *            文件导出的路径（在硬盘上的绝对路径）
	 */
	public static void startCreate(MyTable table, String filePath, boolean is_package_exist) {
		
		try {
			//生成Java类
			PoMaker.createPo(table, filePath);//生成Bo
			DaoMaker.createDAO(table, filePath);//生成Dao
			DaoImplMaker.createDaoImpl(table, filePath);//生成DaoImpl
			BizMaker.createBiz(table, filePath);//生成Biz
			BizImplMaker.createBizImpl(table, filePath);//生成BizImpl
			ActionMaker.createAction(table, filePath);//生成Action
			ConfigMaker.createConfig(table, filePath, is_package_exist);//生成Config
			
			//生成jsp页面
			JspAddEl.createAdd(table, filePath);//生成add.jsp
			JspListEl.createList(table, filePath);//生成list.jsp
			JspUpdateEl.createUpdate(table, filePath);//生成update.jsp
			JspDetailEl.createDetail(table, filePath);//生成detail.jsp
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
}
