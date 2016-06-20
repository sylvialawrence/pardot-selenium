# selenium-pardot

This repository has four java test cases that does the following (in order):
1) Log in to Pardot (https://pi.pardot.com, Username: pardot.applicant@pardot.com, Password: **********)
2) Create a list with a random name (Marketing > Segmentation > Lists)
3) Attempt to create another list with that same name and ensure the system correctly gives a validation failure
4) Rename the original list
5) Ensure the system allows the creation of another list with the original name now that the original list is renamed
6) Create a new prospect (Prospect > Prospect List)
7) Add your new prospect to the newly created list
8) Ensure the new prospect is successfully added to the list upon save
9) Send a text only email to the list (Marketing > Emails)  *Please note, email is disabled in this account so I will not actually be able to send the email. 
10) Log out

# Java Classes
junittest1.java does steps 1 to 5. 
unittest2.java does steps 6 to 10. 
junittestsuite.java is the test suite that runs the above two java classes. 
Constant.java is used to declare the variables and share them with the above java classes. 

#To Run the project
Open Selenium and run junittestsuite.java file. 
The console will have the comments logged in addition to the browser activity. 
