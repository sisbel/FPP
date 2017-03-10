package fpp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import twitter4j.IDs;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.conf.ConfigurationBuilder;

public class FPPMain {

	public static void main(String[] args) throws IOException {
		long cursor = -1;
		IDs ids;
		Map<Long, User> userData = null;
		Constants constants = new Constants();
		Scanner input = new Scanner(System.in);

		/*
		 * System.out.println("Input Consumer Key");
		 * constants.setConsumerKey(input.nextLine());
		 * System.out.println("Input Consumer Secret");
		 * constants.setConsumerSecret(input.nextLine());
		 * System.out.println("input Access Token");
		 * constants.setAccessToken(input.nextLine());
		 * System.out.println("Input Access Token Secret");
		 * constants.setTokenSecret(input.nextLine());
		 */

		constants.setConsumerKey("Eq23zEUXtHctRt91SO8eSRgkh");
		constants.setConsumerSecret(
				"ZQIqvC2qn3wdui8DPeIBqcH80GTR6Jp63cK71iP8TJt8rHEir2");
		constants.setAccessToken(
				"604088594-Ke6ctwedq6oNhNiTSSrcK4jL25DZmAm8HAERY9SK");
		constants.setTokenSecret(
				"MNL5xMEeJ4VVnOzH1jaHYbI0qRO6c5xbFFFULjFQ47jF4");

		ConfigurationBuilder cfb = new ConfigurationBuilder();
		cfb.setDebugEnabled(true)
				.setOAuthConsumerKey(constants.getConsumerKey())
				.setOAuthConsumerSecret(constants.getConsumerSecret())
				.setOAuthAccessToken(constants.getAccessToken())
				.setOAuthAccessTokenSecret(constants.getTokenSecret());

		TwitterFactory tf = new TwitterFactory(cfb.build());
		Twitter twitter = tf.getInstance();
		// Attempt to verify credentials could throw an exception
		try {
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
			} while ((cursor = ids.getNextCursor()) != 0);
		} catch (TwitterException e) {
			e.printStackTrace();
		} // End try/catch

		CreateGML gml = new CreateGML(userData);
	}

}
