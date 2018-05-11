# **Tutorial for Building Liliths Throne**

*You will need the Eclipse IDE and Java SE Development Kit!*

*If you already have Eclipse, verify that you are running Luna or later, as Java 1.8 is required*

https://www.eclipse.org/downloads/

http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html


1. Open Eclipse
2. Click File, Import it will then open a wizard, open Git and choose Projects from Git, click Next
3. Choose Clone URL and then click Next
4. Put https://github.com/Innoxia/liliths-throne-public into the first box in Location
5. Put github.com into the second box in Location, this may be done automatically.
6. Put /Innoxia/liliths-throne-public in the third box in Location, this may be done automatically. Then click Next
7. The wizard will now ask which branch you want, this will be "master", select this and then click next.
8. Unless you want to change the directory, click Next.
9. It will now download the repository.
10. Make sure "Import existing Eclipse projects" is selected, then click Next.
11. Make sure the Lilith's Throne project is selected, then click Finish.
12. Click File, and then select Export.
13. Open Java, and then choose "Runnable JAR File"
14. Choose Main - LilithsThrone under Launch Configuration (IF THERE ARE NO OPTIONS, READ HELP BELOW)
15. Choose an export destination for the .jar file, this should be where you normally run the game from
16. Click Finish, A warning may pop up saying "JAR export finished with warnings" This is fine.
17. You should now be able to run the exported .jar file!

If the jar does not run:
1. Check menu:File->Properties->left pane:Java Build Path->tab:Libraries
2. If this does not list a jdk 1.8 system library, download the jdk from the above oracle link
3. Extract it and follow instructions in eclipse menu:help->Search->type:'Adding a new JRE definition'
4. Possibly you may have to menu:Source->Clean Up, then try again steps 12-17.

## Help
#### No Launch Configuration OR "Could not find main method from given launch configuration."
1. First close the Export wizard, and click on the icon in the top of the left pane.
2. Select the Project in the Package Explorer
3. In the top bar, click Project, and then Properties
4. In the left column, select Run/Debug Settings
5. In the right column, click New
6. Choose Java Application, and click OK
7. A Wizard will open, click Search next to the "Main Class" Box
8. Another Window will open, there will be one matching item called "Main - com.lilithsthrone.main" Select this and click OK
9. Click OK
10. Click Apply and Close
11. Try Exporting Again


####  "C:\Users\[USERNAME]\git\liliths-throne-public is not an empty directory."
**This is the same error as "Some or all projects cannot be imported as they already exist in the workspace"**

This is most likely because you have already have an outdated build open as a project. Make note of the directory it is reporting as not being empty.
1. Close the import wizard
2. In the Package Explorer, right click the project, and click Delete
3. Make sure you select "Delete Project Contents on Disk". If you don't do this, you will have to do it manually in Windows File Explorer.
4. Try Importing again

####  Eclipse stuck on importing from Git
The GIT plugin of Eclipse tries to run the "bash" command, and if you hadn't installed Windows Subsystem for Linux  properly, it may cause problems with import.
1. In "Turn Windows features on or off", check if Windows Subsystem for Linux is activated.
2. If it's active, try to execute "bash" in Command Prompt.
3. If result contains the phrase "Windows Subsystem for Linux has no installed distributions", then you have to install Linux distribution. Fortunately, the basic setup can be done with just one command.
4. Run "lxrun /install" in Command Prompt, type "y" to continue, after that - enter some username and password.
5. Run "bash" in Command Prompt once. If the appearance of the console changes, then you've succeeded.
6. Try Importing once again.

## Using Maven
[Maven](https://maven.apache.org) is a Java build system that can be used to build applications.  
Support is integrated into Eclipse, but it can be used without an IDE, so you can build the game without Eclipse.  
All build information is provided in [pom.xml](/pom.xml).

### Building
From the root of the repository (the folder where `pom.xml` is) run:

```
mvn package
```

This creates the JAR file in `/target/game-1.jar`.  
Your first build will take longer than the subsequent ones as only changed files are recompiled.

## Using NetBeans
You should be able to use NetBeans' import zip feature to easily import this project.
1. Use the green "Clone or Download" button on the project's main page, and choose "Download ZIP".
2. In NetBeans, navigate to: "File -> Import Project -> From Zip..."
3. Select the ZIP download from step 1.
4. The imported project should now run in NetBeans.
