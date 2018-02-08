package unitn.sde.project.resources;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.PersistenceUnit;
import javax.persistence.Tuple;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.glassfish.jersey.client.ClientConfig;
import org.json.JSONArray;
import org.json.JSONObject;

import unitn.sde.project.adapters.SnoothAdapter;
import unitn.sde.project.model.User;
import unitn.sde.project.model.Wine;
import unitn.sde.project.model.WineReview;

@Stateless
@LocalBean//Will map the resource to the URL 
@Path("/wine")
public class WineResource {
	// Allows to insert contextual objects into the class,
    // e.g. ServletContext, Request, Response, UriInfo
    @Context
    UriInfo uriInfo;
    @Context
    Request request;
    @Context
    Response response;
    @Context
	WebTarget service;
    
    // will work only inside a Java EE application
    @PersistenceUnit(unitName="introsde-jpa")
    EntityManager entityManager;

    // will work only inside a Java EE application
    @PersistenceContext(unitName = "introsde-jpa",type=PersistenceContextType.TRANSACTION)
    private EntityManagerFactory entityManagerFactory;
    
    
    /*
     * The base URI of snooth API.
     */
    private static URI getBaseURI() {
		return UriBuilder.fromUri(
				"https://api.snooth.com/").build();
	}
    
    
    /*
     * Get Snooth service, if null create one.
     */
    private WebTarget getService() {
    	if(service == null) {
        	ClientConfig clientConfig = new ClientConfig();
    		Client client = ClientBuilder.newClient(clientConfig);
    		service = client.target(getBaseURI());
    	}
    	return service;
    }
    
    
    /*
     * The Snooth API 's key.
     */
    private static String getSnoothKey() {
    	return "erxagkvbgsju22cflsis5rmtzrwirxo7329fj9no9kq10ppz";
    }

    
    /* REST METHODS*/
    
    
    @GET
    @Produces({MediaType.TEXT_XML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
    public String getAvailableMethos() {
        System.out.println("Getting list of methods...");
        return "{\"available_methods\": {[{\"search\": \"wines\"}]}";
    }
    
    
    /*
     *  Perform a research 
     */   
    @GET
    @Path("/search")
    @Produces({MediaType.TEXT_XML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
    public List<Wine> getWineList(
    		@DefaultValue("wine") @QueryParam("search") String search, 
    		@DefaultValue("") @QueryParam("type") String type, 
    		@DefaultValue("") @QueryParam("color") String color, 
    		@DefaultValue("") @QueryParam("country") String country, 
    		@DefaultValue("10000.0") @QueryParam("maxprice") String maxprice, 
    		@DefaultValue("0.0") @QueryParam("minprice") String minprice, 
    		@DefaultValue("0.0") @QueryParam("minrank") String minrank, 
    		@DefaultValue("5.0") @QueryParam("maxrank") String maxrank)  {
    	
    	
    	List<Wine> list = new ArrayList<Wine>();    	
    	
    	/*the API doesn't support a blank color*/
    	if(color.equals("")) {
    		response = getService()
        			.path("wines")
        			.queryParam("akey", getSnoothKey())
        			.queryParam("q", search)
        			.queryParam("n", 20)
        			.queryParam("t", type)
        			.queryParam("c", country)
        			.queryParam("mp", minprice)
        			.queryParam("xp", maxprice)
        			.queryParam("mr", minrank)
        			.queryParam("xr", maxrank)
        			.request()
        			.accept(MediaType.APPLICATION_JSON)
        			.get();
    	}else {
    		response = getService()
        			.path("wines")
        			.queryParam("akey", getSnoothKey())
        			.queryParam("q", search)
        			.queryParam("n", 20)
        			.queryParam("t", type)
        			.queryParam("c", country)
        			.queryParam("color", color)
        			.queryParam("mp", minprice)
        			.queryParam("xp", maxprice)
        			.queryParam("mr", minrank)
        			.queryParam("xr", maxrank)
        			.request()
        			.accept(MediaType.APPLICATION_JSON)
        			.get();
    	}
    	
		String responseBody = response.readEntity(String.class);
		System.out.println(responseBody);
		JSONObject jResponse = new JSONObject(responseBody);
		JSONObject metaData = (JSONObject) jResponse.get("meta");
		JSONArray wines = (JSONArray) jResponse.get("wines");
		
		
		for(int i = 0; i < 20; i++) {
			list.add(SnoothAdapter.simplifyResponse((JSONObject) wines.get(i)));
		}
		
		return list;
    }
    
    
    /*
     *  Create a new snooth account 
     */   
    @POST
    @Path("/newaccount")
    @Produces({MediaType.TEXT_XML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
    @Consumes({MediaType.TEXT_XML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
    public User createUser(User newUser){
    	
    	if(newUser.getUsername() != null &&
    	   newUser.getPassword() != null &&
    	   newUser.getEmail() != null ) {
    		/* FAKEKEKEKE JUST FOR TEESTINGGG
    		response = getService()
        			.path("create-account")
        			.queryParam("akey", getSnoothKey())
        			.queryParam("e", newUser.getEmail())
        			.queryParam("p", newUser.getPassword())
        			.queryParam("s", newUser.getUsername())
        			.request()
        			.accept(MediaType.APPLICATION_JSON)
        			.get();
    		
    		String responseBody = response.readEntity(String.class);
    		JSONObject jResponse = new JSONObject(responseBody);
    		*/
    		JSONObject jResponse = new JSONObject("{\"meta\":{\"results\":1,\"returned\":1,\"errmsg\":\"\",\"status\":1}}");   		
    		JSONObject metaData = (JSONObject) jResponse.get("meta");
    		
    		return metaData.getInt("returned") == 1 ? newUser : null;
    	}
    	User ret = new User();
    	ret.setEmail("merda");
    	return ret;
    }

    
    /*
     * Rate a particoular wine
     */
    @PUT
    @Path("/rate")
    @Produces({MediaType.TEXT_XML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
    @Consumes({MediaType.TEXT_XML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
    public WineReview rateWine(User user,
    		@QueryParam("rating") double rating,
    		@QueryParam("code") String code,
    		@QueryParam("review") String review,
    		@QueryParam("iswish") int isWish
    		) {
    	
    	if((user.getUsername() != null || user.getEmail() != null) &&
    	    user.getPassword() != null) {

    		String id = user.getUsername() == null ? user.getEmail() : user.getUsername();
    		
    		response = getService()
        			.path("rate")
        			.queryParam("akey", getSnoothKey())
        			.queryParam("u", id)
        			.queryParam("p", user.getPassword())
        			.queryParam("b", review)
        			.queryParam("r", rating)
        			.queryParam("id", code)
        			.queryParam("w", isWish)
        			.request()
        			.accept(MediaType.APPLICATION_JSON)
        			.get();
    		
    		String responseBody = response.readEntity(String.class);
			System.out.println(responseBody);
    		JSONObject jResponse = new JSONObject(responseBody);
    		JSONObject metaData = (JSONObject) jResponse.get("meta");

			WineReview wr = new WineReview();
			
    		if(metaData.getInt("status") == 1) {
    			try{wr.setName(user.getUsername());} catch(Exception e) {};
    			try{wr.setBody(review);} catch(Exception e) {};
    			try{wr.setDate(System.currentTimeMillis());} catch(Exception e) {};
    			try{wr.setRating(rating+"");} catch(Exception e) {};
    		}
    		
    		/*START RECOMBEE PART*/
    		System.out.println("bb".toString());
        	response = getService()
        			.path("wine")
        			.queryParam("akey", getSnoothKey())
        			.queryParam("id", code)
        			.queryParam("food", 0)
        			.request()
        			.accept(MediaType.APPLICATION_JSON)
        			.get();
        	
        	String responseBodyRec = response.readEntity(String.class);
    		System.out.println(responseBodyRec);
    		JSONObject jResponseRec = new JSONObject(responseBodyRec);
    		JSONArray jWinesArrayRec = jResponseRec.getJSONArray("wines");
    		JSONObject jWinesRec = jWinesArrayRec.getJSONObject(0);
    		
    		System.out.println(jResponseRec.toString());
    		Wine wine = SnoothAdapter.simplifyResponseWithMoreDetails(jWinesRec, id);
    		/*QUI HAI IL TYPE E IL RATING*/
    		System.out.println("type: " + wine.getType() + ", raintg: " + wine.getRating());
    		
    		/*FINISH RECOMBEE PART*/
    		
    		return wr;
    	}

    	return null;
    }
    
    
    /*
     * Get wine's details
     */
    @GET
    @Path("/details")
    @Produces({MediaType.TEXT_XML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
    public Wine getWineDetails(
    		@QueryParam("username") String name,
    		@QueryParam("code") String code, 
    		@QueryParam("food") int food) {

		System.out.println("bb".toString());
    	response = getService()
    			.path("wine")
    			.queryParam("akey", getSnoothKey())
    			.queryParam("id", code)
    			.queryParam("food", food)
    			.request()
    			.accept(MediaType.APPLICATION_JSON)
    			.get();
    	
    	String responseBody = response.readEntity(String.class);
		System.out.println(responseBody);
		JSONObject jResponse = new JSONObject(responseBody);
		JSONArray jWinesArray = jResponse.getJSONArray("wines");
		JSONObject jWines = jWinesArray.getJSONObject(0);
		
		System.out.println(jResponse.toString());
		Wine wine = SnoothAdapter.simplifyResponseWithMoreDetails(jWines, name);
		
		return wine;
    }
    
    
    /*
     * Get my Wines
     */
    @POST
    @Path("/mywine")
    @Produces({MediaType.TEXT_XML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
    public List<Wine> getMyWineList(User user, 
    		@DefaultValue("0") @QueryParam("showrating") int showrating)  {
    	
    	List<Wine> wines = new ArrayList<>();
    	
    	if((user.getUsername() != null || user.getEmail() != null) &&
        	    user.getPassword() != null) {
    		
    		String id = user.getUsername() == null ? user.getEmail() : user.getUsername();
    		
	    	response = getService()
	    			.path("my-wines")
	    			.queryParam("akey", getSnoothKey())
	    			.queryParam("u", id)
	    			.queryParam("p",user.getPassword())
	    			.queryParam("n", 200)
	    			.queryParam("c", 0)
	    			.queryParam("r", showrating)
	    			.request()
	    			.accept(MediaType.APPLICATION_JSON)
	    			.get();
	    	
	    	String responseBody = response.readEntity(String.class);
			System.out.println(responseBody);
			JSONObject jResponse = new JSONObject(responseBody);
			
			JSONArray jWinesArray = jResponse.getJSONArray("wines");
			for(int i = 0; i < jWinesArray.length(); i++) {
				wines.add(SnoothAdapter.simplifyResponseWithinWishlist(jWinesArray.getJSONObject(i)));
			}
			
			return wines;
    	}
    	
    	
    	return null;
    }
    
    
    @POST
    @Path("/recom")
    @Produces({MediaType.TEXT_XML,  MediaType.APPLICATION_JSON ,  MediaType.APPLICATION_XML })
    public List<Wine> getWineList(User user)  {
    	
    	
    	List<Wine> list = new ArrayList<Wine>();    	
    	List<Wine> recomWine = RecomAdapt.recommendedWines(user.getUsername(), 5);
    	
    	for(Wine w : recomWIne){
            response = getService()
                    .path("wines")
                    .queryParam("akey", getSnoothKey())
                    .queryParam("q", w.getVarietal())
                    .queryParam("n", 20)
                    .request()
                    .accept(MediaType.APPLICATION_JSON)
                    .get();
        
            
            String responseBody = response.readEntity(String.class);
            System.out.println(responseBody);
            JSONObject jResponse = new JSONObject(responseBody);
            JSONObject metaData = (JSONObject) jResponse.get("meta");
            JSONArray wines = (JSONArray) jResponse.get("wines");
            list.add(SnoothAdapter.simplifyResponse((JSONObject) wines.get(0)));
		}
		
		
		return list;
    }
    
    
    /*OTHER METHODS*/
    
    
    
}
