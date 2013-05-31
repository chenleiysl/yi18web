package cn.yi18.pojo;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.yi18.cache.*;
import cn.yi18.jdbc.DBManager;
import cn.yi18.jdbc.QueryHelper;
import cn.yi18.util.Inflector;




/**
 * pojo基础类的父类，所有的基础类都继承于该类
 *    该类同时也实现了一些简单的数据库操着方法
 * @author 陈磊
 *
 */
public class POJO implements Serializable
{
	
	/**
	 * 
	 */
	private final static Logger log = LoggerFactory.getLogger(POJO.class);
	protected final static transient char OBJ_COUNT_CACHE_KEY = '#';
	private static final long serialVersionUID = 1L;
	private static final String DABASE = "yi18";
	private String __this_table_name;//对应的数据库表名
	private long id;
	
	public long getId() { return id; }
	public void setId(long id) { this.id = id; }
	/**
	 * 清空缓存
	 * @param cache
	 * @param key
	 */
	public static void evictCache(String cache, Serializable key) {
		EhCacheEngine.remove(cache, key);
	}
	
	/**
	 * 设置缓存
	 * @param cache
	 * @param key
	 * @param value
	 */
	public static void setCache(String cache, Serializable key, Serializable value) {
		EhCacheEngine.add(cache, key, value);
	}
	
	/**
	 * 取得缓存
	 * @param cache
	 * @param key
	 * @return
	 */
	public static Object getCache(String cache, Serializable key) {
		return EhCacheEngine.get(cache, key);
	}
	
	/**
	 * 是否根据ID缓存对象，此方法对Get(long id)有效
	 * @return
	 */
	protected boolean isObjectCachedByID() { return false; }
	/**
	 * 根据条件决定是否清除对象缓存
	 * @param er
	 * @return
	 */
	public boolean evict(boolean er) {
		if(er && isObjectCachedByID())
			EhCacheEngine.remove(cacheRegion(), getId());
		return er;
	}
	
	/**
	 * 清除指定主键的对象缓存
	 * @param obj_id
	 */
	protected void evict(long obj_id) {
		EhCacheEngine.remove(cacheRegion(), obj_id);
	}
	
	/**
	 * 插入对象到数据库表中
	 * @return
	 */
	public long save() {
		if(getId() > 0)
			_insertObject(this);
		else
			setId(_insertObject(this));
		if(this.isObjectCachedByID())
			EhCacheEngine.remove(cacheRegion());
		return getId();
	}
	
	
	/**
	 * 根据id主键删除对象
	 * @return
	 */
	public boolean delete() {
		boolean dr = evict(QueryHelper.update("DELETE FROM " + tableName() + " WHERE id=?", getId()) == 1);
		if(dr)
			EhCacheEngine.remove(cacheRegion());	
		return dr;
	}
	public boolean delete(long id) {
		boolean dr = evict(QueryHelper.update("DELETE FROM " + tableName() + " WHERE id=?", id) == 1);
		if(dr)
			EhCacheEngine.remove(cacheRegion());	
		return dr;
	}
	
	public boolean delete(String filter ) {
		boolean dr = evict(QueryHelper.update("DELETE FROM " + tableName() + " WHERE "+filter) == 1);
		if(dr)
			EhCacheEngine.remove(cacheRegion());	
		return dr;
	}
	/**
	 * 分页列出所有对象
	 * @param page
	 * @param size
	 * @return
	 */
	public List<? extends POJO> list() {
		String sql = "SELECT * FROM " + tableName() + " ORDER BY id DESC";
		return QueryHelper.query_cache(getClass().getSimpleName(), "all", getClass(), sql);
	}
	public List<? extends POJO> list(int page, int size) {
		String sql = "SELECT * FROM " + tableName() + " ORDER BY id DESC";
		return QueryHelper.query_slice(getClass(), sql, page, size);
	}
	public List<? extends POJO> filter(String filter, int page, int size) {
		String sql = "SELECT * FROM " + tableName() + " WHERE " + filter + " ORDER BY id DESC";
		return QueryHelper.query_slice(getClass(), sql, page, size);
	}
	
	public int update(Map<String, Object> map,Long id) 
	{
		String sql="UPDATE "+ tableName() + " SET ";
		 Set<String> sets = map.keySet();
		 int i = 0;
		  for (String string : sets) 
		  {
			  i++;
			 sql=sql+string+" =?";
			 if (sets.size() > i) 
			 	{
				 sql=sql+" , ";
			 	}
		  }
		  sql=sql+"  WHERE id = ?";
		
		  Object[] params = ArrayUtils.add(map.values().toArray(), id);
		  
		  if(this.isObjectCachedByID())
			  EhCacheEngine.remove(cacheRegion());//清空该缓存 清空全部
			
		return QueryHelper.update(sql, params);
		
	}
	
	
	/**
	 * 统计此对象的总记录数
	 * @return
	 */
	public int totalCount() {
		return (int) QueryHelper.stat("SELECT COUNT(*) FROM " + tableName() );
	}
	public int totalCount(String filter) {
		return (int) QueryHelper.stat("SELECT COUNT(*) FROM " + tableName() + " WHERE " + filter);
	}
	
	
	
	/**
	 * 根据主键读取对象详细资料，根据预设方法自动判别是否需要缓存
	 * @param id
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends POJO> T get(long id) {
		if(id <= 0) return null;
		String sql = "SELECT * FROM " + tableName() + " WHERE id=?";
		boolean cached = isObjectCachedByID();
		return (T)QueryHelper.read_cache(cached?cacheRegion():null, id+"",getClass(), sql, id);
	}
	public List<? extends POJO> batchGet(List<Long> ids) {
		if(ids==null || ids.size()==0)
			return null;
		StringBuilder sql = new StringBuilder("SELECT * FROM " + tableName() + " WHERE id IN (");
		for(int i=1;i<=ids.size();i++) {
			sql.append('?');
			if(i < ids.size())
				sql.append(',');
		}
		sql.append(')');
		List<? extends POJO> beans = QueryHelper.query(getClass(), sql.toString(), ids.toArray(new Object[ids.size()]));
		if(isObjectCachedByID()){
			for(Object bean : beans){
				EhCacheEngine.add(cacheRegion(), ((POJO)bean).getId(), bean);
			}
		}
		return beans;
	}
	
	@SuppressWarnings("unchecked")
	public <T extends POJO> T get(Map<String, Object> map) 
	{
		String sql="SELECT * FROM "+ tableName() + " where ";
	 Set<String> sets = map.keySet();
	 int i = 0;
	  for (String string : sets) 
	  {
		  i++;
		 sql=sql+string+" =?";
		 if (sets.size() > i) 
		 	{
			 sql=sql+" and ";
		 	}
	  }
	  
		return (T)QueryHelper.read(getClass(), sql,  map.values().toArray());
		
	}
	
	public List<? extends POJO>  getlist(Map<String, Object> map) 
	{
		String sql="SELECT * FROM  "+ tableName() + " where ";
	 Set<String> sets = map.keySet();
	 int i = 0;
	  for (String string : sets) 
		  {
			  i++;
			 sql=sql+string+" = ?";
			 if (sets.size() > i) 
			 	{
				 sql=sql+" and ";
			 	}
			 else {
					sql=sql+ " ORDER BY id DESC";
				}
		  }
		return QueryHelper.query(getClass(), sql, map.values().toArray());
		
	}
	
	
	public List<? extends POJO>  getlist(Map<String, Object> map,String order) 
	{
		String sql="SELECT * FROM  "+ tableName() + " where ";
	 Set<String> sets = map.keySet();
	 int i = 0;
	  for (String string : sets) 
		  {
			  i++;
			 sql=sql+string+" = ?";
			 if (sets.size() > i) 
			 	{
				 sql=sql+" and ";
			 	}
			 else {
					sql=sql+ " ORDER BY "+order;
				}
		  }
		return QueryHelper.query(getClass(), sql, map.values().toArray());
		
	}
	
	/**
	 * 返回默认的对象对应的表名
	 * @return
	 */
	protected String tableName() {
		if(__this_table_name == null)
			__this_table_name = DABASE+"_" + Inflector.getInstance().tableize(getClass());
		return __this_table_name;
	}
	
	   /**
     * 返回对象对应的缓存区域名
     * @return
     */
 public String cacheRegion() 
 { 
	 return this.getClass().getSimpleName();
	 }

 
 
 protected boolean isAutoLoadUser() { return false; }
//	protected long getAutoLoadUser() { return 0L; }
 
 /**
	 * 插入对象
	 * @param obj
	 * @return 返回插入对象的主键
	 */
	
	private static long _insertObject(POJO obj) {		
		Map<String, Object> pojo_bean = obj.listInsertableFields();		
		String[] fields = pojo_bean.keySet().toArray(new String[pojo_bean.size()]);
		StringBuilder sql = new StringBuilder("INSERT INTO ") ;
		sql.append(obj.tableName());
		sql.append('(');
		for(int i=0;i<fields.length;i++){
			if(i > 0) sql.append(',');
			sql.append(fields[i]);
		}
		sql.append(") VALUES(");
		for(int i=0;i<fields.length;i++){
			if(i > 0) sql.append(',');
			sql.append('?');
		}
		sql.append(')');
		PreparedStatement ps = null;
		ResultSet rs = null;
		try{
			ps = DBManager.getConnection().prepareStatement(sql.toString(), Statement.RETURN_GENERATED_KEYS);		
			for(int i=0;i<fields.length;i++){
				ps.setObject(i+1, pojo_bean.get(fields[i]));
			}
			ps.executeUpdate();
			rs = ps.getGeneratedKeys();
			return rs.next()?rs.getLong(1):-1;
		}catch(SQLException e){
			log.error("保存数据失败！");
			return -1;
		}finally{
			DbUtils.closeQuietly(rs);
			DbUtils.closeQuietly(ps);
			sql = null;
			fields = null;
			pojo_bean = null;
		}
	}
 
 /**
	 * 列出要插入到数据库的域集合，子类可以覆盖此方法
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected Map<String, Object> listInsertableFields() {
		try {
			Map<String, Object> props = BeanUtils.describe(this);
			if(getId() <= 0)
				props.remove("id");
			props.remove("class");
			return props ;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Exception when Fetching fields of " + this);
		}
	}
 
 @Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj == this) return true;
		//不同的子类尽管ID是相同也是不相等的
		if(!getClass().equals(obj.getClass()))
			return false;
		POJO wb = (POJO) obj;
		return wb.getId() == getId();
	}
}
