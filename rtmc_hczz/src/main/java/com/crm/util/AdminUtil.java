package com.crm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.crm.bean.TMonPopManagerBean;

/**
 * 
 * AdminUtil:超级管理员公共方法
 *
 * @author yumaochun
 * @date  2016年6月5日
 * @version  jdk1.8
 *
 */
public class AdminUtil {
	 
     public static void main(String[] args) 
     {
    	 AdminUtil util = new AdminUtil();
    	
    	 util.initSuperAdmin("superadmin");
/*    	  TMonPopManagerBean temp = new TMonPopManagerBean();
    	 temp = util.getSuperAdmin("f:\\admin.serializable");*/
    	 //util.initSuperAdmin("cd_hskj_100");
     }
     
    public boolean isSuperAdmin(String admin)
    {
    	return admin.equalsIgnoreCase("superAdmin");
    } 
     
    public boolean saveSuperAdmin(TMonPopManagerBean manager)
    {
    	boolean success = true;
   	 	manager.setSMnId("superAdmin");
        FileOutputStream fos = null; 
        ObjectOutputStream oos = null; 
        try{ 
        	//System.out.println(this.getClass().getClassLoader().getResource("/admin.serializable").getPath());
             fos = new FileOutputStream(this.getClass().getClassLoader().getResource("/admin.serializable").getPath()); 
             oos = new ObjectOutputStream(fos); 
             oos.writeObject(manager);
             
        }catch(Exception e){ 
        		success = false;
             e.printStackTrace(); 
        }finally{ 
             try{ 
                  oos.close(); 
             }catch(Exception es){ 
                  System.out.println("Exception: oos.close()"); 
             }    
        }
        return success;
    }
    
    public void initSuperAdmin(String userPass)
    {
    	TMonPopManagerBean manager = new TMonPopManagerBean(); 
   	 	manager.setSMnId("superAdmin");
   	 	manager.setSEmail("");
   	 	manager.setSMnName("超级管理员");
   	 	manager.setSMnPwd(new Certification().encryptMD5(userPass));
   	 	manager.setSQq("");
   	 	manager.setSContactPhone("");
        FileOutputStream fos = null; 
        ObjectOutputStream oos = null; 

        try{ 
             fos = new FileOutputStream("f:\\admin.serializable"); 
             oos = new ObjectOutputStream(fos); 
             System.out.println("===设置超级用户信息");
             oos.writeObject(manager);    
        }catch(Exception e){ 
             e.printStackTrace(); 
        }finally{ 
             try{ 
                  oos.close(); 
             }catch(Exception es){ 
                  System.out.println("Exception: oos.close()"); 
             }    
        }
    }
    
    public TMonPopManagerBean getSuperAdmin()
    {
    	TMonPopManagerBean manager = new TMonPopManagerBean(); 
        InputStream fis = null; 
        ObjectInputStream ins = null; 
        try{ 
        	 fis = new FileInputStream(new File(this.getClass().getClassLoader().getResource("/admin.serializable").getPath()));
        	 ins = new ObjectInputStream(fis); 
        	 manager= (TMonPopManagerBean)ins.readObject(); 
        }catch(Exception e){ 
             e.printStackTrace(); 
        }finally{ 
             try{ 
            	 ins.close(); 
             }catch(Exception es){ 
                  System.out.println("Exception: oos.close()"); 
             }    
        }
        return manager;
    }
    
    public TMonPopManagerBean getSuperAdmin(String url)
    {
    	TMonPopManagerBean manager = new TMonPopManagerBean(); 
        FileInputStream fis = null; 
        ObjectInputStream ins = null; 
        try{ 
        	 fis = new FileInputStream(url); 
        	 ins = new ObjectInputStream(fis); 
        	 manager= (TMonPopManagerBean)ins.readObject(); 
        }catch(Exception e){ 
             e.printStackTrace(); 
        }finally{ 
             try{ 
            	 ins.close(); 
             }catch(Exception es){ 
                  System.out.println("Exception: oos.close()"); 
             }    
        }
        return manager;
    }
}
