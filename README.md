# Console Currency-Converter

## Methods review: 
### &nbsp;&nbsp;&nbsp;&nbsp; **1. "main" method** 

&nbsp;&nbsp;&nbsp;&nbsp;In the "main" method I'm using HashMap to store currency codes and Scanner to get the input from the user. Then printing currencies the user can convert to and checking if the user's input is valid (it has to be from 1 to 5). If the input isn't valid, currency options will be printed until the input is valid.  

&nbsp;&nbsp;&nbsp;&nbsp;After that, we call a "sendHttpGetRequest" method to convert. After conversion we can continue or stop, it's up to the user. We are wrapping our code from the "main" method with the "while" loop so the user can convert until he chooses not to.  
  
    
### &nbsp;&nbsp;&nbsp;&nbsp; **2. "sendHttpGetRequest" method**  

&nbsp;&nbsp;&nbsp;&nbsp;I use this method to make a GET request to the API  (http://data.fixer.io/api/latest) with the URL where I'll get an exchange rate and use it in our conversion. To implement that I'm using the URL (I've previously copied from https://fixer.io/)  which API is asking me to use, but I'm inputting my parameters (access key, base, and toCode).  

&nbsp;&nbsp;&nbsp;&nbsp;For "access key" I've created another class with a private getAccessKey() method which returns my access key so nobody else can see it or use it. As for the "base" parameter, the free fixer.io plan allows us to convert only from EUROS that's why the "base" parameter is always "EUR".  

&nbsp;&nbsp;&nbsp;&nbsp;After creating a proper URL I'm setting an httpConnection using my URL to open a connection. I'm setting a getRequestMethod as "GET" since I'm making a GET request. I have to check if my GET request was successful so I'm comparing my "responseCode" to one of HTTP's response codes which is "OK".  

&nbsp;&nbsp;&nbsp;&nbsp;After checking the response I'm using BufferedReader to read the information from our response which contains what we requested. BufferedReader is reading our response and while there's data in it I'm adding it to the StringBuilder.  

&nbsp;&nbsp;&nbsp;&nbsp;Since our response is in JSON format I have to parse JSON. I used "org.json" and added it to my pom.xml. I'm passing my response as a string to my JSONObject and collecting the exchange rate from the "rates" section.



