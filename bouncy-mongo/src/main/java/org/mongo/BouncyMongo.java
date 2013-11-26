package org.mongo;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.crypto.Crypto;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;

public class BouncyMongo {

	
	public static void main(String[] args) throws Exception {
		MongoClient mc = null;
		Security.insertProviderAt(new BouncyCastleProvider(), 1);
		try {
			mc = new MongoClient("localhost:27017");
			DBCollection col = mc.getDB("crypto").getCollection("bouncy_castle");
			BasicDBObject dbo = new BasicDBObject("name", "Bob");
			String secretInfo = "Bob likes to eat bugs";
			
			dbo.append("secret", Crypto.encrypt(secretInfo, "000102030405060708090A0B0C0D0E0F"));
			col.insert(dbo);
			
			String ciphertext = (String) col.findOne(new BasicDBObject("name","Bob")).get("secret");
			
			System.out.println(ciphertext);
			System.out.println(Crypto.decrypt(ciphertext, "000102030405060708090A0B0C0D0E0F"));
		}
		finally {
			if(null != mc) mc.close();
		}
		

	}

}
