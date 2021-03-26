Readme:

Q1:
The jdepend report gives us a structure of our application in a layered format including values representing the programs extensibility, accessability, modularity and instancibility. We see that the student cli class is underneath the logic class studentmanager.
This means that our student cli has access to the logic but our studentmanager does not depend on the cli.

Q2:
We can generate the cli application by including/adding a build plugin to our pom file, in my case i used the spring plugin. The plugin allows us to specify 
our main class, and package our application into a jar file that contains all of our dependancies and logic necassary for performing a query on the data base.
We first add the spring plugin to our pom, we then open a terminal in the folder containing pom, we then use the command mvn compile followed by mvn package. 
This will generate a jar file in /target/site/. We then can open a cli inside the site folder and run the command java -jar jarfile.jar arg1 to execute the jar 
and in our case print out the students details.

Q3:
I chose to not interfare with the garbage collection as much as possible for many reasons.
Firstly implemented 2 versions of the application, one in which closed connnections and 
statements manually, and one which let the standard java garbage collection handle this.
The version that did not interfare with garbage collection exerted better performance and when profiled 
used less memory then the manual application.
I believe that my design is still prone to memory leaks as the connection is opened in the static block,
I think that this could cause issues, and does not provide a clear closing criterea.
If i understood more about the way java handles garbage collection and memory management in general,
I think i could have manually configured the garbage collection to achieve better resource management and performance.
But at this level of understanding using the default java garbage collection is better then attempting to manually handle it.


