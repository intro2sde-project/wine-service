package unitn.sde.project.recom;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.recombee.api_client.RecombeeClient;
import com.recombee.api_client.api_requests.AddBookmark;
import com.recombee.api_client.api_requests.AddDetailView;
import com.recombee.api_client.api_requests.AddItemProperty;
import com.recombee.api_client.api_requests.AddRating;
import com.recombee.api_client.api_requests.AddUser;
import com.recombee.api_client.api_requests.DeleteBookmark;
import com.recombee.api_client.api_requests.GetItemValues;
import com.recombee.api_client.api_requests.ListItemRatings;
import com.recombee.api_client.api_requests.Request;
import com.recombee.api_client.api_requests.ResetDatabase;
import com.recombee.api_client.api_requests.SetItemValues;
import com.recombee.api_client.api_requests.UserBasedRecommendation;
import com.recombee.api_client.bindings.Rating;
import com.recombee.api_client.bindings.Recommendation;
import com.recombee.api_client.exceptions.ApiException;

import unitn.sde.project.model.Wine;
import unitn.sde.project.model.User;

public class WineRecom {
	
	private static Integer count = 0;
	
	public WineRecom() {
		
	}
	
	//add user to catalog on recombee
	 public static boolean addUser(String username) {

		 RecombeeClient client = new RecombeeClient("wine", "7FA7cxphIIMwDajDfLdyC8GCFMs2JLCKFNWtkPrZ3YDZQU7KQd06ELn5SHLpUcFp");
		 try {
			 
			 client.send(new AddUser(username));
			 return true;
		 } catch (ApiException e) {
			 e.printStackTrace();
	        //Use fallback
			 return false;
		 }
		 
	    	
	    }
	 
	 //add rating to beer by user
	 public static boolean addRating(String username, long idWine, double rating) {
		 
		 
		 RecombeeClient client = new RecombeeClient("wine", "7FA7cxphIIMwDajDfLdyC8GCFMs2JLCKFNWtkPrZ3YDZQU7KQd06ELn5SHLpUcFp");
		 try {
			 
			 client.send(new AddRating(username, Long.toString(idWine),  rating)
					  .setCascadeCreate(true)
					);
			 
			 Rating [] result = client.send(new ListItemRatings(Long.toString(idWine)));
			 for(Rating rate : result) {
				System.out.println(" - " + rate.getUserId() + " rate: " + rate.getRating());
			 }
			 return true;
		 } catch (ApiException e) {
			 e.printStackTrace();
	        //Use fallback
			 return false;
		 }
		 
		
	 }
	 
	 //add beer to user wishlist (wishlist intended as purchased list on recombee)
	 public static boolean addWishlist(String username, long idWine) {
		 
		 RecombeeClient client = new RecombeeClient("wine", "7FA7cxphIIMwDajDfLdyC8GCFMs2JLCKFNWtkPrZ3YDZQU7KQd06ELn5SHLpUcFp");
		 try {
			 
			 client.send(new AddBookmark(username,Long.toString(idWine))
                     .setCascadeCreate(true));
			 return true;
		 } catch (ApiException e) {
			 e.printStackTrace();
	        //Use fallback
			 return false;
		 }
		
	 }
	 
	 //delete beer from user wishlist
	 public static boolean deleteWishlist(String username, long idWine) {
		 
		 RecombeeClient client = new RecombeeClient("wine", "7FA7cxphIIMwDajDfLdyC8GCFMs2JLCKFNWtkPrZ3YDZQU7KQd06ELn5SHLpUcFp");
		 try {
			 
			 client.send(new DeleteBookmark(username,Long.toString(idWine))
                     );
			 return true;
		 } catch (ApiException e) {
			 e.printStackTrace();
	        //Use fallback
			 return false;
		 }
		
	 }
	 
	 //add beer to catalog on recombee
	 public static boolean addWine(final Wine wine) {
		 
		 RecombeeClient client = new RecombeeClient("wine", "7FA7cxphIIMwDajDfLdyC8GCFMs2JLCKFNWtkPrZ3YDZQU7KQd06ELn5SHLpUcFp");
		 
		 try {
			 
			client.send(new SetItemValues(String.format("wine-%s", ++count), //itemId
		                //values:
		                new HashMap<String, Object>() {{
		                    put("type", wine.getType());
		                    
		                }}
					 ).setCascadeCreate(true)); 
			 
			 return true;
		 } catch (ApiException e) {
			 e.printStackTrace();
	        //Use fallback
			 return false;
		 }
		 
	 }
	 
	 //get list of recommended beers userbased
	 public static List<String> recommendedWineTypes(String username, long count){
		 
		 List<String> typeList = new ArrayList<String>();
		 String type = null;
		 RecombeeClient client = new RecombeeClient("wine", "7FA7cxphIIMwDajDfLdyC8GCFMs2JLCKFNWtkPrZ3YDZQU7KQd06ELn5SHLpUcFp");
		 try {	 
		 
		 Recommendation [] result = client.send(new UserBasedRecommendation(username,  count)
				 );
		 
		 for(Recommendation rec: result) {
			 Map<String, Object>  result_1 = client.send(new GetItemValues(rec.getId()));
			 JSONObject obj = new JSONObject(result_1);
			 type = obj.getString("type");
			 typeList.add(type);
		 }

		 } catch (ApiException e) {
			 e.printStackTrace();
	        //Use fallback
		 }
		 return typeList;
				
	 }
	
	 //add beer viewed by user	
	 public static boolean addView(String username, long idWine) {
		 RecombeeClient client = new RecombeeClient("wine", "7FA7cxphIIMwDajDfLdyC8GCFMs2JLCKFNWtkPrZ3YDZQU7KQd06ELn5SHLpUcFp");
		 try {
			 
			 client.send(new AddDetailView(username,Long.toString(idWine))
	                 .setCascadeCreate(true));
			 return true;
		 } catch (ApiException e) {
			 e.printStackTrace();
	        //Use fallback
			 return false;
		 }
	 }
}
