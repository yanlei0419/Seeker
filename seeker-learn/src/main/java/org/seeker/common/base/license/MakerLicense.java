//package org.seeker.common.base.license;
//
//import java.io.FileNotFoundException;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.io.ObjectOutputStream;
//import java.lang.reflect.Method;
//import java.security.GeneralSecurityException;
//
//import org.apache.commons.codec.binary.Base64;
//import org.seeker.mapper.base.license.config.LicenseConfig;
//
//import com.smardec.license4j.License;
//import com.smardec.license4j.LicenseManager;
//import com.smardec.license4j.LicenseUtil;
//
//public class MakerLicense {
//	public static void makerKey(String[] keys) {
//		FileOutputStream fos = null;
//		try {
//			fos = new FileOutputStream(LicenseConfig.outFilePath + LicenseConfig.keyName);
//			ObjectOutputStream oo = new ObjectOutputStream(fos);
//			oo.writeObject(keys);
//			oo.flush();
//			oo.close();
//			System.out.println("创建key成功");
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//
//	public static void makerLic(String[] keys) throws IllegalArgumentException, GeneralSecurityException, IOException {
//		if (keys.length != 2) {
//			System.out.println("key不对无法生成lincense");
//			return;
//		}
//		keys[0] = new String(Base64.decodeBase64(keys[0].getBytes()));
//		keys[1] = new String(Base64.decodeBase64(keys[1].getBytes()));
//
//		LicenseManager.setSerializeStrings(true);
//		LicenseManager.setPublicKey(keys[0]);
//		LicenseManager.setPrivateKey(keys[1]);
//		License license = new License();
//
//		license.addFeature("RegistrationState", LicenseConfig.RegistrationState);
//		license.addFeature("MacAddress", LicenseConfig.MacAddress);
//		license.addFeature("ResidueDegree", LicenseConfig.ResidueDegree);
//		license.addFeature("Company", LicenseConfig.Company);
//		license.addFeature("Customer", LicenseConfig.Customer);
//		license.addFeature("ExpiredDate", LicenseConfig.ExpiredDate);
//		license.addFeature("CreateTime", LicenseConfig.CreateTime);
//		license.addFeature("LastAccessTime", LicenseConfig.LastAccessTime);
//		license.addFeature("SystemName", LicenseConfig.SystemName);
//		license.addFeature("AuthorName", LicenseConfig.AuthorName);
//		license.addFeature("email", LicenseConfig.email);
//
//		// license.addFeature("MinorVersion", LicenseConfig.MinorVersion);
//		// license.addFeature("MajorVersion", LicenseConfig.MajorVersion);
//
//		LicenseManager.saveLicense(license, LicenseConfig.outFilePath + LicenseConfig.licName);
//		System.out.println("创建lic成功");
//	}
//
//
//	public static void checkKeys() throws Exception{
//		String className = "org.vegetto.mapper.base.license.";
//		className = className + "LicenseValidate";
//
//		Class cls = Class.forName(className);
//		Object instance = cls.newInstance();
//		String methodName = "LicenseValidate";
//		Method localMethod = cls.getMethod(methodName, new Class[0]);
//		localMethod.invoke(instance);
//		System.out.println("验证成功");
//	}
//
//
//	public static void main(String[] args) throws Exception {
//		String[] keys =LicenseUtil.createKeyPair();
//		keys[0]=Base64.encodeBase64String(keys[0].getBytes());
//		keys[1]=Base64.encodeBase64String(keys[1].getBytes());
////		System.out.println(keys[0]);
////		System.out.println(keys[1]);
//		makerKey(keys);
//		makerLic(keys);
//		checkKeys();
//	}
//}
