/**
 * Twitter Search Exporter
 * 
 * Small program using Twitter4j to grab and save a search on twitter.
 * 
 * @author John Tiernan
 * @version 2011_16/Sept-2
 */

import twitter4j.*;

import java.util.List;
import java.io.*;

public class SearchToDB_Twitter {

	/**
	 * @param args[0] Search keyword
	 * @param args[1] Twitter User name
	 * @param args[2] Password
	 * @param args[3] Destination
	 */
	public static void main(String[] args) {
		System.out.println("SearchToDB - Twitter\n\t(C)John Tiernan 2001");
		
		
		if (args.length < 1) {
            System.out.println("Error: Too few parameters\n\tUsage: java -jar SearchToDB_Twitter.jar \"[query]\"");
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
			Query q = new Query(keyword);
            QueryResult result = twit.search(q);

            List<Tweet> tweets = result.getTweets();
            //Edits
            int resultCount = tweets.size();
            
            System.out.println("Query: " + keyword);
            System.out.println("Number of results: " + resultCount);
           
            for (Tweet tweet : tweets) {
                System.out.println("@" + tweet.getFromUser() + " - " + tweet.getText());
            }
            
            //CSV format
            //Username, tweet, datestamp, url
            //@username, tweet..., 123456789, link
            
            
            
            
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
		
	}

}
