package com.amaker.online.common.storage;


import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 七牛SDK的包装类，以便于业务使用
 */
public class QiniuWrapper {
	
	private static final Logger logger = LoggerFactory.getLogger(QiniuWrapper.class);
	
	private static final String CONFIG_BUCKET="onlinestudy";
	
	private static final String CONFIG_AK="XK_8ziYSRZUDFQaATIenQVuwTdhzY6AKc6OjRseh";
	private static final String CONFIG_SK="rbW3_Yt6UBTkcPF0jjkEbz2TjfuAkkTrfXKhi3bR";
	private static final String CONFIG_CDN="pky80l3qs.bkt.clouddn.com";
	
	private static final Auth auth;
	private static final UploadManager uploadManager;
	
	private static final String bucketName;
	private static final List<String> cdns;
	
	/**
	 * 从外部文件中初始化七牛存储相关的配置信息
	 */
	static{
		auth = Auth.create(CONFIG_AK, CONFIG_SK);
		Configuration cfg = new Configuration(Zone.zone0());//设置空间上传域名
		uploadManager = new UploadManager(cfg);
		bucketName=CONFIG_BUCKET;
		String cdn = CONFIG_CDN;
		cdns = Arrays.asList(cdn.split(","));
	}

	/**
	 * 上传文件
	 * @param data 二进制格式的文件内容
	 * @param key 文件在七牛中的key
	 * @param update 是否是更新
	 * @return
	 */
	public static String upload(byte[] data,String key,boolean update){
		try {
			String token = update?auth.uploadToken(bucketName,key):auth.uploadToken(bucketName);
			Response response = uploadManager.put(data, getFullKey(data, key), token);
			DefaultPutRet ret = response.jsonToObject(DefaultPutRet.class);
			return ret.key;
		} catch (QiniuException e) {
			logger.error("upload file to qiniu cloud storage failed",e);
		}
		return null;
	}
	
	private static String getFullKey(byte[] data,String key){
		FileType type= FileTypeHelper.getType(data);
		if(type!=null){
			return key+"."+type.name().toLowerCase();
		}else{
			return key;
		}
	}
	
	public static String getUrl(String key,String model){
		return getUrl(key, model, 3600);
	}
	
	/**
	 * 获取多个key图片；
	 * @param keys 逗号隔开的多个key;
	 * @param model
	 * @return
	 */
	public static List<String> getUrls(String keys,String model){
		List<String> list = null;
		if (org.apache.commons.lang.StringUtils.isNotBlank(keys)) {
			list = new ArrayList<String>();
			for (String key : keys.split(",")) {
				list.add(getUrl(key, model, 3600));
			}
		}
		return list;
	}
	
	public static String getUrl(String key){
		if(!StringUtils.isEmpty(key)){
			return auth.privateDownloadUrl("http://"+getCDN()+"/@"+key);
		}
		return null;
	}
	
	/**
	 * @param key
	 * @param expires 单位：秒
	 * @return
	 */
	public static String getUrl(String key,long expires){
		if(!StringUtils.isEmpty(key)){
			long time = System.currentTimeMillis()/1000+expires;
			return auth.privateDownloadUrl("http://"+getCDN()+"/@"+key,time);
		}
		return null;
	}
	
	public static String getUrl(String key,String model,long expires){
		if(StringUtils.hasText(model)){
			return auth.privateDownloadUrl("http://"+getCDN()+"/@"+key+"?"+model,expires);
		}else{
			return auth.privateDownloadUrl("http://"+getCDN()+"/@"+key,expires);
		}
	}
	
	/**
	 * 从多条CDN路径中随机选择一条
	 * @return
	 */
	private static String getCDN(){
		Random random= new Random();
		int num=random.nextInt(cdns.size());
		return cdns.get(num);
	}
	

}
