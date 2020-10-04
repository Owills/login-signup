# login-signup
Simple login and signup interface for multiple users and admins

Day 1:
I set up the general display for the interface using code from piglatin translator project. The user is promted with a choice bar betwen login and signup. For the login choice the frame has username and password textfields. For teh signup choice the frame has create username, create password, and confirm password textfields.

Day 2:
Worked on how to actually store the information in the text file. Each user's info will be stored in chunks with an empty line in between each user. The first line will be the username, the next the salt, the next the hashed password and so on. The username will act as a key for a dictionary which will return an arraylist with the rest of the user's necessary login info. Currently whenever a new account is added the whole file will be rewritten, this wouldn't be ideal for massive amounts of users, but for now it serves its purpose and works well.
Password checks were added, the password must match the confirmed password, be at least 7 characters, contain a capital letter and non letter character.
the getSalt method now generates a random Salt based on an algorithim I found on stack overflow.
An Admin checkbox was added for the create account page. 
