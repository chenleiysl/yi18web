package cn.yi18.service;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import cn.yi18.enums.UserEnum;
import cn.yi18.pojo.User;
import cn.yi18.util.DigestMD;
import cn.yi18.util.Pinyin4j;



public class UserService 
{
			/**
			 *   添加用户
			 * @param user
			 * @return 0：用户存在，添加失败，>0 :用户Id
			 */
			public long add(User user) 
			{
				User userben = new User();
				user.setPassword(DigestMD.MD5(user.getPassword()));//对密码做MD5摘要
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("account", user.getAccount());
				if(userben.get(map)!=null) //判断注册的用户是否存在，如果存在返回0
						return 0;
				return user.save();
				
			}
			
			/**
			 * 激活用户
			 * @param account 帐号
			 * @param hkey  key
			 * @return
			 */
			public boolean activate(String account,String hkey) 
			{
				User bean = new User();
				Map<String, Object> map= new HashMap<String, Object>();
				map.put("account", account);
				//map.put("hkey", hkey);
				User user = bean.get(map);
				if(user!=null)
				{
					map.clear();
					if(hkey.equals(user.getHkey()))
					{
						String name =Pinyin4j.ToHanyuPinyin(user.getName());//如果存在汉字，就把汉字转变成拼音
						map.put("home", name);// 设置个人主页
						if(bean.get(map)!=null)// 如果已经有了该主页，就把ID加上
						map.put("home", name+user.getId());
						map.put("hkey", null);// 清除hkey
						map.put("isuse", UserEnum.Use_Status.IsUse.getValue());//设置用户可用
						map.put("image", "avatar.gif");
						bean.update(map, user.getId());
						return true;
					}
				}
				return false;
				
			}
			
			/**
			 * 重置密码
			 * @param email 账号
			 * @param pwd 新密码
			 * @param hkey
			 * @return 
			 */
			public boolean resetpwd(String account, String pwd, String hkey)
			{
				User bean = new User();
				Map<String, Object> map= new HashMap<String, Object>();
				map.put("account", account);
				map.put("hkey", hkey);
				User user = bean.get(map);
				if (user==null) {
					return false;
				}
				else
				{
					map.clear();
					map.put("hkey", null);
					map.put("password", DigestMD.MD5(pwd));
					user.update(map, user.getId());
					return true;
				}
				
			}
			
			public boolean resetpwd(String account, String hkey)
			{
				User bean = new User();
				Map<String, Object> map= new HashMap<String, Object>();
				map.put("account", account);
				
				User user = bean.get(map);
				if (user==null) {
					return false;
				}
				else
				{
					map.clear();
					map.put("hkey", hkey);
					user.update(map, user.getId());
					return true;
				}
				
			}

			/**
			 * 登录验证
			 * @param account 账号	
			 * @param pwd  密码
			 * @return
			 */
			public static User validateLogin(String account, String pwd) {
				User bean = new User();
				Map<String, Object> map= new HashMap<String, Object>();
				map.put("account", account);
				map.put("password", DigestMD.MD5(pwd));
				User user = bean.get(map);
				if(user==null) //如果为空就返回
					return null;
				if(user.getIsuse()==UserEnum.Use_Status.IsUse.getValue())
				{
					//map.clear();	
					//bean.update(map, user.getId());
					return user;
				}
				return null;
			}

			
			/**
			 * 保存图片
			 * @param id
			 * @param filename
			 */
			public void aveportrait(long id, String filename) {
				User bewn = new User();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("image", filename);
				bewn.update(map , id);
				
			}

			public void aveportrait(long id, File tempfile, File file, String left,
					String top, String width, String height) 
			{
			
				 BufferedImage image = null;   
			     try {   
			              image = ImageIO.read(tempfile);   
			          } catch (IOException e) {   
			          e.printStackTrace();   
			          }   
			      int w = image.getWidth();// 图片宽度   
			      int h = image.getHeight();// 图片高度   
			      
			      Rectangle rect = new Rectangle(Integer.parseInt(left), Integer.parseInt(top),Integer.parseInt(width), Integer.parseInt(height));
//				try {
//					//ImageUtils.cut(tempfile, file, w, h, rect);
//					aveportrait(id, file.getName());
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}

				
			}

			public boolean chpwd(String newpwd, long id) {
				User bean = new User();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("password", DigestMD.MD5(newpwd));
				
				return bean.update(map , id)>0?true:false;
				
			}

		

//			public void logout(long id) {
//				User bean = new User();
//				Map<String, Object> map= new HashMap<>();
//				
//				bean.update(map, id);
//				
//			}
		

}
