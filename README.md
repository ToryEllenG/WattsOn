# WattsOn
Home Energy Monitoring Application. Semester Long Project for ISAT 306: Instrumentation and Measurements in Data Communications and Networking.

The purpose of this applicaton is to provide a medium which allows the everyday homeowner to track how much energy is being consumed in their house in real time.

The user will be able to see exactly how much energy is being used in the house, and what room(s) it is coming from, creating an effective way to know where to save costs.

Repository for Android application created and maintained by Troy Gamboa.

![Login Page](http://i.imgur.com/rZrlPdD.png "Screenshot of Login Page") ![Data Page](http://i.imgur.com/P8EFs51.png "Screenshot of Data Page")


**(2/28/17)**
	The current UI has been completely revamped, and is utilizing Fragments and Material Design. Now, the user can slide the screen to the desired activity they wish to view, smoothly. A Settings page has been added to the application. A contact page has been added within the settings page, featuring our social media sites. A logout button has also been added with in the main Fragment Activity.

**(2/24/17)**
  In the application's current state, a splash screen has been added upon user launch, and register and login logic have been initialized through the API hosted on an AWS EC2 Web server.   
  
  The API currently handles all methods for logging in and registering a user, taking advantage of an open source PHP hashing library, which can be found at [here](https://github.com/ircmaxell/password_compat). This library is responsible for hashing the user's password upon registration, and checks if the inputted password matches the hashed password upon log in.   
  
  All user data is stored in an AWS RDS mysql instance, located on the cloud. The same database is also responsible for taking data from the back-end team, pushing relevant energy data from a Raspberry Pi in real time. The API also is responsible for encoding this data in JSON format, so that it is able to be parsed through all applications in the front-end team.   
  
  The application also takes use of an open source graphing library, [Graphview](http://www.android-graphview.org/). As of now, the application only shows arbitrary values, of which are populated in real time from a random function.   
  
  Once the graph can be populated with values recieved from the back end team, the user should then be able to compare the amount of energy usage through a chosen time interval, providing them with the information needed to know when and where to cut back on their energy usage, while at the same time promoting energy efficiency.
