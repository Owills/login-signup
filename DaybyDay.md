# login-signup
Simple login and signup interface for multiple users and admins

Day 1: I set up the general display for the interface using code from piglatin translator project. The user is prompted with a choice bar between login and signup. For the login choice the frame has username and password textfields. For the signup choice the frame has create username, create password, and confirm password textfields.

Day 2: Worked on how to actually store the information in the text file. Each user's info will be stored in chunks with an empty line in between each user. The first line will be the username, the next the salt, the next the hashed password and so on. The username will act as a key for a dictionary which will return an arraylist with the rest of the user's necessary login info. Currently whenever a new account is added the whole file will be rewritten, this wouldn't be ideal for massive amounts of users, but for now it serves its purpose and works well. Password checks were added, the password must match the confirmed password, be at least 7 characters, contain a capital letter and non letter character. the getSalt method now generates a random Salt based on an algorithm I found on stack overflow. An Admin checkbox was added for the create account page.

Day 3: Lots of readability, usability and organization changes. 
Added PasswordManager class instead of static methods, bc methods are not always called. 
Password checks happen within the mainFileManager which will return a String version of the error method to login. Removed Redundant static hash methods. 
Added a file append method, instead of rewriting the file on the creation of an account the account will just be added. 
Added options for Name during account creation 
Added Abstract account class with getters and setter methods. The Hashtable now stores accounts instead of arraylist. The child classes are UserAccount and Admin Account the only difference for nwo is the Admin Account stores the Hashtable (In anticipation For Future Use). To account for such changes the writeFile and ReadFile methods were updated. This would become more useful as the Admin Account and User Account differ more. 
A successful login will now close the original window and bring up a new empty window as either and AdminAccountPage or UserAccountPage.

Day 4: Worked on actual difference in windows between admins and users. Both accounts can sign out, which returns the original windows, reset password which alters the account and then writes the information to masterFile. The user can play Conway's Game of Life because why not. The user can request to be an admin. Admins can view pendings admin requests from users and confirm admins. The user will have to relogin to gain admin privileges. In order for this to happen new informations must be added to the user account storing wether the user has requested to become an admin, admin classes save that information to file as null but admin accounts don't store that information, user classes default as false. The admin account has access to the Hashtable and looks from every account and fine which ones are requesting. The admin account also contains a method to convert a account from a user to admin. 
In the creation of a new account there is now a check to see if the user has actually inputted something as their name.

Passwords for both user1 and user2 are 'Password1'. Information in the master file is stored as follows 
Username 
Name 
Salt 
Password 
If there is an Admin Request 
If user is an Admin

