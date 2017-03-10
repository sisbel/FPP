package fpp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import twitter4j.IDs;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

public class FPPMain {

	public static void main(String[] args) throws IOException{
		long cursor = -1;
		IDs ids;
		Map <Long, User> userData = null;
	
		ConfigurationBuilder cfb = new ConfigurationBuilder();
		cfb.setDebugEnabled(true)
		.setOAuthConsumerKey("8V5Uw1qjW7lEBJzwvHGtyMTXH")
		.setOAuthConsumerSecret("3ILBoEuIwSG47BJEqluf4EcoZClmFP5bvRdzuTUH5BowInq9Kw")
		.setOAuthAccessToken("840284961164918784-lv4IATcC6ImSzE8AE2q2feV0i7Vqbda")
		.setOAuthAccessTokenSecret("VpawAZd9E1IzH5J4NkRsN5YAMWkRchS6erhXHxtT7QTEC");
		
		TwitterFactory tf = new TwitterFactory(cfb.build());
		Twitter twitter = tf.getInstance();
		// Attempt to verify credentials could throw an exception
		try{
			twitter.verifyCredentials();
			
			System.out.println("Listening, please wait...");
			System.out.println();
			do {
				ids = twitter.getFollowersIDs("Isthil255", cursor);
				userData = new HashMap();
				for (long id : ids.getIDs()) {
					System.out.println(id);
					User user = twitter.showUser(id);
					System.out.println(user.getName());
					System.out.println();
					userData.put(id, user);
				}
			}while ((cursor = ids.getNextCursor()) != 0);
		}catch (TwitterException e) {
			e.printStackTrace();
		} // End try/catch
		
		CreateGML gml = new CreateGML(userData);
	}

}
