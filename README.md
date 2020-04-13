# MyAuthorization
Here I tried to create authorization system.
I used next technologies: Java, Java Servlets, HTML/CSS, Tomcat.

**To easier understanding:**

All important manipulation with authorization system based at AuthorizationHandler class.

When user Press "Log in" or "Registration" button, LoginServlet or RegistrationServlet start their work respectively.
According to RegStatus or LogStatus which are defined by AuthorizationHandler methods, program can solve which page 
should be shown. If RegStatus/LogStatus equal to SUCCESS then login and password should be writen to cookies.

**Important:** for correct program work, you should change String path at AuthorizationHandler.
(sorry for this, I didn't found how to solve this problem)
