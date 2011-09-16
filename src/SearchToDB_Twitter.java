/**
 * Twitter Search Exporter
 * 
 * @author John Tiernan
 * @version 2011_16/Sept
 */

import twitter4j.*;
import java.util.List;

public class SearchToDB_Twitter {

	/**
	 * @param args[0] Search keyword
	 * @param args[1] Twitter User name
	 * @param args[2] Password
	 * @param args[3] Destination
	 */
	public static void main(String[] args) {
		if (args.length < 1) {
            System.out.println("java -jar SearchToDB_Twitter.jar \"[query]\"");
            System.exit(-1);
        }
		
		//Command line args
		String keyword = args[0];
		//String uname = args[1];
		//String pass = args[2];
		//String dest = args[3];
		
		//Create Twitter4j object
		Twitter twit = new TwitterFactory().getInstance();
		
		//Example code from lib
		try {
            QueryResult result = twit.search(new Query(args[0]));
            List<Tweet> tweets = result.getTweets();
            for (Tweet tweet : tweets) {
                System.out.println("@" + tweet.getFromUser() + " - " + tweet.getText());
            }
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
		
	}

}
