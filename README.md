# WineRecom

WineRecom API allow consumers to access a service, which adapta the API from Recombee and use it to expose new method, which make an external database (Snooth) interact with the external API.


## User

### Create Account [POST]

+ Request (application/json)

      https://wine-recomm-rest.herokuapp.com/sde/wine/newaccount
    
    + Body
    
            {
                "username": "jave"
                "password": "ciao"
                "email": "jave@jave.it
            }

+ Response 200 (application/json)

    
    + Body
    
             {
                "username": "jave"
                "password": "ciao"
                "email": "jave@jave.it
            }



## Wine

### Search Wine [GET]

Return a list of wines, with specific proprieties, from Snooth db.

+ Request (application/json)

      https://wine-recomm-rest.herokuapp.com/sde/wine/search/

+ Response 200 (application/json)

    
    + Body
    
            [
                {
                    "name": "Hitching Post Generation",
                    "code": "hitching-post-generation-2005",
                    "region": "USA > California > Santa Barbara",
                    "winery": "Hitching Post",
                    "varietal": "Cabernet Franc; Merlot; Syrah",
                    "price": 15.99,
                    "vintage": 2005,
                    "type": "Red Wine",
                    "link": "http://www.snooth.com/wine/hitching-post-generation-2005/",
                    "image": "https://ei.isnooth.com/multimedia/a/e/1/image_319467_square.jpeg",
                    "rating": 0,
                    "review": null,
                    "alcohol": null,
                    "reviews": null
                },
                {
                    "name": "Villa Farnia di Farnese Montepulciano d'Abruzzo",
                    "code": "villa-farnia-di-farnese-montepulciano-dabruzzo-2008-1",
                    "region": "Italy > Abruzzi > Montepulciano d'Abruzzo",
                    "winery": "Farnese Vini",
                "varietal": "Montepulciano",
                "price": 20.75,
                "vintage": 2008,
                "type": "Red Wine",
                "link": "http://www.snooth.com/wine/villa-farnia-di-farnese-montepulciano-dabruzzo-2008-1/",
                "image": "https://ei.isnooth.com/multimedia/d/5/c/image_749147_square.jpeg",
                "rating": 0,
                "review": null,
                "alcohol": null,
                "reviews": null
                }
                .
                .
                .
                .
                .
            ]   



### Get Wine Details [GET]

Get the details of a specific wine. 
 
+ Request (application/json)

      https://wine-recomm-rest.herokuapp.com/sde/wine/details?code=hitching-post-generation-2005

+ Response 200 (application/json)

   
    + Body
    
            {
                "name": "Hitching Post Generation",
                "code": "hitching-post-generation-2005",
                "region": "USA > California > Santa Barbara",
                "winery": "Hitching Post",
                "varietal": "Cabernet Franc; Merlot; Syrah",
                "price": 15.99,
                "vintage": 2005,
                "type": "Red Wine",
                "link": "http://www.snooth.com/wine/hitching-post-generation-2005/",
                "image": "https://ei.isnooth.com/multimedia/a/e/1/image_319467_square.jpeg",
                "rating": 0,
                "review": null,
                "alcohol": "0.0",
                "reviews": []
            }
    
   

## User-Wine Interaction

### Rating and Wishlist

#### Add to Wishlist and Rate [PUT]

Add a rating from a specific user to a wine, and can either add it to its wishlist or not.

+ Request (application/json) 

      https://wine-recomm-rest.herokuapp.com/sde/wine/rate?rating=3.5&code=hitching-post-generation-2005&review=good&iswish=1
    
    + Body
    
            { 
                "username": "cane", 
                "password": "cane", 
                "email": "cane@cane.it 
            }
        
        
+ Response 200 (application/json)

   + Body
   
           {
            "name": "cane",
            "rating": "4.5",
            "body": "I like the flavuor",
            "date": 1517930390033,
            "lang": null
            }
   


#### Get Wishlist [POST]

Return the wishlist of wines for a user.

+ Request (application/json)

      https://wine-recomm-rest.herokuapp.com/sde/wine/mywine
    
    + Body

+ Response 200 (application/json)

   + Body
   
            [
              {
                  "name": "Hitching Post Generation",
                  "code": "hitching-post-generation-2005",
                  "region": "USA > California > Santa Barbara",
                  "winery": "Hitching Post",
                  "varietal": "Cabernet Franc; Merlot; Syrah",
                  "price": 15.99,
                  "vintage": 2005,
                  "type": "Red Wine",
                  "link": "http://www.snooth.com/wine/hitching-post-generation-2005/",
                  "image": "https://ei.isnooth.com/multimedia/a/e/1/image_319467_square.jpeg",
                  "rating": 0,
                  "review": null,
                  "alcohol": null,
                  "reviews": null
              },
              {
                  "name": "Villa Farnia di Farnese Montepulciano d'Abruzzo",
                  "code": "villa-farnia-di-farnese-montepulciano-dabruzzo-2008-1",
                  "region": "Italy > Abruzzi > Montepulciano d'Abruzzo",
                  "winery": "Farnese Vini",
                  "varietal": "Montepulciano",
                  "price": 20.75,
                  "vintage": 2008,
                  "type": "Red Wine",
                  "link": "http://www.snooth.com/wine/villa-farnia-di-farnese-montepulciano-dabruzzo-2008-1/",
                  "image": "https://ei.isnooth.com/multimedia/d/5/c/image_749147_square.jpeg",
                  "rating": 0,
                  "review": null,
                  "alcohol": null,
                  "reviews": null
                } 
                .
                .
                .
                .
                .
            ]
   


## Recommendation

### Get Wine Recommendation [POST]

Return a specific number of recommendations for a user.

+ Request (application/json)
    
      https://wine-recomm-rest.herokuapp.com/sde/wine/recom
    
    + Body
    
            { 
                "username": "cane", 
                "password": "cane", 
                "email": "cane@cane.it 
            }

+ Response 200 (application/json)

   + Body 
   
            [
              {
                  "name": "Hitching Post Generation",
                  "code": "hitching-post-generation-2005",
                  "region": "USA > California > Santa Barbara",
                  "winery": "Hitching Post",
                  "varietal": "Cabernet Franc; Merlot; Syrah",
                  "price": 15.99,
                  "vintage": 2005,
                  "type": "Red Wine",
                  "link": "http://www.snooth.com/wine/hitching-post-generation-2005/",
                  "image": "https://ei.isnooth.com/multimedia/a/e/1/image_319467_square.jpeg",
                  "rating": 0,
                  "review": null,
                  "alcohol": null,
                  "reviews": null
              },
              {
                  "name": "Villa Farnia di Farnese Montepulciano d'Abruzzo",
                  "code": "villa-farnia-di-farnese-montepulciano-dabruzzo-2008-1",
                  "region": "Italy > Abruzzi > Montepulciano d'Abruzzo",
                  "winery": "Farnese Vini",
                  "varietal": "Montepulciano",
                  "price": 20.75,
                  "vintage": 2008,
                  "type": "Red Wine",
                  "link": "http://www.snooth.com/wine/villa-farnia-di-farnese-montepulciano-dabruzzo-2008-1/",
                  "image": "https://ei.isnooth.com/multimedia/d/5/c/image_749147_square.jpeg",
                  "rating": 0,
                  "review": null,
                  "alcohol": null,
                  "reviews": null
                } 
                .
                .
                .
                .
                .
            ]
   
       
