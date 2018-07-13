package org.vegetto.common.base.hibernate;
//package org.seeker.base;
//
//import java.io.Serializable;
//import java.lang.reflect.ParameterizedType;
//import java.util.List;
//
//import org.hibernate.Criteria;
//import org.hibernate.Query;
//import org.hibernate.Session;
//import org.hibernate.Transaction;
//
//import com.chinasoft.project.datasource.HibernateSessionFactory;
//import com.chinasoft.project.vo.PageBean;
//
//public class HibernateGenericDaoImpl<T extends Serializable, ID extends Serializable>
//		implements IGenericDao<T, ID> {
//
//	// 声明一个类型对象用于接收传入的具体类型
//	private Class<T> persistentClass;
//
//	// 通过反射技术获取传入的具体的类
//	@SuppressWarnings("unchecked")
//	public HibernateGenericDaoImpl() {
//		super();
//		this.persistentClass = ((Class<T>) ((ParameterizedType) this.getClass()
//				.getGenericSuperclass()).getActualTypeArguments()[0]);
//	}
//
//	@Override
//	public void insert(T entity) {
//		// TODO Auto-generated method stub
//		Session session = HibernateSessionFactory.getSession();
//		Transaction transaction = null;
//		try{
//			transaction = session.beginTransaction();
//			session.save(entity);
//			transaction.commit();
//		} catch(RuntimeException e){
//			e.printStackTrace();
//			transaction.rollback();
//		} finally {
//			HibernateSessionFactory.closeSession();
//		}
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public T selectById(ID id) {
//		// TODO Auto-generated method stub
//		Session session = HibernateSessionFactory.getSession();		
//		try{                                    
//			T	entity = (T) session.get(this.persistentClass, id);	
//			return entity;
//		} catch(RuntimeException e){
//			e.printStackTrace();
//			return null;
//		} finally {
//			HibernateSessionFactory.closeSession();
//		}
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public List<T> selectAll() {
//		// TODO Auto-generated method stub
//		Session session = HibernateSessionFactory.getSession();		
//		try{
//			Criteria criteria = session.createCriteria(this.persistentClass);
//			List<T> list = criteria.list();
//			return list;
//		} catch(RuntimeException e){
//			e.printStackTrace();
//			return null;
//		} finally {
//			HibernateSessionFactory.closeSession();
//		}
//	}
//
//	@Override
//	public void update(T entity) {
//		// TODO Auto-generated method stub
//		Session session = HibernateSessionFactory.getSession();
//		Transaction transaction = null;
//		try{
//			transaction = session.beginTransaction();
//			session.update(entity);
//			transaction.commit();
//		} catch(RuntimeException e){
//			e.printStackTrace();
//			transaction.rollback();
//		} finally {
//			HibernateSessionFactory.closeSession();
//		}
//	}
//
//	@Override
//	public void delete(T entity) {
//		// TODO Auto-generated method stub
//		Session session = HibernateSessionFactory.getSession();
//		Transaction transaction = null;
//		try{
//			transaction = session.beginTransaction();
//			session.delete(entity);
//			transaction.commit();
//		} catch(RuntimeException e){
//			e.printStackTrace();
//			transaction.rollback();
//		} finally {
//			HibernateSessionFactory.closeSession();
//		}
//	}
//
//	@Override
//	public PageBean selectByPage(String strHQL, int currentPage, int pageSize,
//			Object... params) {
//		 // 步骤1：获取一个数据库的连接
//		Session session = HibernateSessionFactory.getSession();
//		// 步骤2：创建一个PageBean对象，在算法中为pageBean对象的所有属性赋值
//		PageBean pageBean = new PageBean();
//		try{
//			// 步骤3：使用Hibernate中的Query接口完成自定义HQL(Hibernate Query Language, 面向对象的SQL语句)语句的查询
//			/* 例子：完成ProductCategory数据表的查询
//			 *           SQL :   select * from productcategory order by categoryid;
//			 *           HQL:   select   pc   from Productcategory as pc order by pc.categoryid
//			 * 分页的HQL语句：  select xxx from ClassName as xxx where codition1=? and condition2=? 
//			 */
//			Query query = session.createQuery(strHQL);
//			for (int i = 0; i < params.length; i++) {
//				query.setParameter( i , params[i]);
//			}
//			// 步骤4：控制查询显示的记录个数
//			query.setFirstResult((currentPage - 1)*pageSize);  // 设置query查询从数据表中第几行开始
//			query.setMaxResults(pageSize);  // 设置每页显示的个数
//			// 步骤5：将quert查询获取的结果集合赋值给pageBean的
//			pageBean.setData(query.list());
//			
//			// 步骤6：为pageBean对象中的totalRows属性赋值
//			// HQL：select count(xxx / *) from ClassName as xxx where codition1=? and condition2=? 			
//			strHQL = "select count(*) " + strHQL.substring(strHQL.toLowerCase().indexOf("from"));
//			query = session.createQuery(strHQL);
//			for (int i = 0; i < params.length; i++) {
//				query.setParameter( i , params[i]);
//			}
//			// 步骤7：获取查询的结果
//			int totalRows = Integer.parseInt(query.uniqueResult().toString()); // 获取当HQL查询只返回一个结果的情况
//			pageBean.setTotalRows(totalRows);
//			
//			// 步骤8：为其余属性赋值
//			pageBean.setCurrentPage(currentPage);
//			pageBean.setPageSize(pageSize);
//			
//			// 步骤9：返回pageBean返回
//			return pageBean;
//			
//		} catch (RuntimeException e){
//			e.printStackTrace();
//			return null;
//		} finally {
//			HibernateSessionFactory.closeSession();
//		}
//	}
//
//	/** 
//	 * 通过 传入的属性值查询 实体类
//	 */
//	@Override
//	public List<T> findByCondition(String conditionName,Object conditionValue) {
//		StringBuffer hql =new StringBuffer(" from "+this.persistentClass.getName()+" t1 where t1."+conditionValue+" = ? ");
//		
//		Query query=HibernateSessionFactory.getSession().createQuery(hql.toString()); query.setParameter(0, conditionValue); 
//		return (List<T>)query.list();
//	}
//
//}
