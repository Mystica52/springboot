# springboot
SPRING BOOT
# It  took as Bank Kigali(Bk) accademic intern project to faciliate me  to understand the java /springboot  which used in micro services used in BK 

Spring boot is a java based framework which is used to develop microservices services which means it is easy to develop and deploy independently. It is used to create a stand alone application which runs on its own  without an external web server. It is used for one who is experienced with java , spring, maven,and gradle. It has man different dependency like 
Starter, auto-configuration,beans, and actuators



Advantage of spring boot

Easy deployment
Less production time
Easy to understand and develop spring application

The  way of bootstrap is  to use the spring initializr by adding the need dependence according to the project and after we download  the zip file of it. 
Annotations
@spring boot Application it is for the main class file
@RestController help to create the restful web service
@Getter & @Setter generate the default boolean type
@RequestMapping used to map the URL request
@allArgConstructor used because jpa use generated default constructor method to create the bean class using reflection
@Services it is used to mark the service provide here and many annotation but i think this is  most important used in restful
@Autowired it is used to connect the bean into another
@postmappung used to post in postman
@getmapping used to get the details needed
@deletemapping used to delete the details
@component scan used to find the beans and corresponding injected with autowired
My project how it works
I did a sample exercise of Login and registration API using some dependency and annotation needed.
Let deal with what it  works
In that project I downloaded from spring initializr I added another package which contains the java class ofl  user  detail, use services where we can create the user detail retrieve,delete and update and database to save the user details. in authentication for security of person account and we can include the dependence of security


 You can create an account  and automatically save to the database in order to secure the application but when you want to use it again you don’t need to create again you must login and continue for other stages.


The challenge 

Because it is for the first time to use it was challenged but I finally know it
To post it  the details in database using postman ,
To know the different function of annotation and dependency
testing of spring boot is somehow challenged 
Lot of class files sometimes you forgot what some of them did



# system of registration of student and with  authentication and send random OTP to the email 
# am still working on saving otp in the database
# otp services is useless 


So spring boot is good to use for the one who is familiar with it and it is easy. I will become more familiar as time goes. To this spring boot needed often to use it in order to master it. I still continue to learn some terms used in it.

