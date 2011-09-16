/**
 * Twitter Search Exporter
 * 
 * Small program using Twitter4j to grab and save a search on twitter.
 * 
 * @author John Tiernan
 * @version 2011_16/Sept-5
 */
import twitter4j.*;

import java.util.List;
import java.io.*;
import java.util.Date;

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
           
            try
            {
            	//Get date for filename
            	Date d = new Date();
            	
            	//FileOutputStream output = new FileOutputStream("results_" + d.toString() + ".csv");
            	//System.out.println(output.getName());
            	
            	File fi = new File("results_" + d.toString() + ".csv");
            	if(fi.exists())
            	{
            		System.out.println("Error: You seem to have discovered time travel so this file already exists.");
            		System.out.println("Please return home before trying again");
            		System.exit(-1);
            	}
            	
            	BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(fi));
            	
            	if(fi.canWrite())
            	{
            		System.out.println("Writing to file " + fi.getName());
            		
            		for (Tweet tweet : tweets) {
            			//CSV format
            			//Username, tweet, datestamp, url
            			//@username, tweet..., 123456789, link
            			String data = "@" + tweet.getFromUser() + STBConsts.CSVBreak + tweet.getText().replaceAll(",","COMMA") + STBConsts.CSVBreak + tweet.getCreatedAt() + STBConsts.CSVBreak + tweet.getSource();
            			System.out.println(data);
            		
            			for(int i = 0; i < data.length(); i++)
            			{
            				output.write(data.charAt(i));
            			}
            			output.write('\n');
            		}
            		output.close();
            	}
            	else
            	{
            		for (Tweet tweet : tweets) {
            			System.out.println("@" + tweet.getFromUser() + " - " + tweet.getText());
            		
            		}
            	}
            }
            //If IO error, exit
            catch (IOException ioe)
            {
            	System.out.println("Error:");
            	//System.out.println("");
            	ioe.printStackTrace();
            }
            
            
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to search tweets: " + te.getMessage());
            System.exit(-1);
        }
		
	}

}
