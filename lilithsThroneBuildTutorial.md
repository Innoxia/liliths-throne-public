# **Tutorial for Building Liliths Throne**

*If you already have Java, uninstalling other versions and installing 8u172 is strongly recommended to avoid conflict.*

You may download Oracle's "Java SE Development Kit 8u172" here: https://www.oracle.com/technetwork/java/javase/downloads/java-archive-javase8-2177648.html


## Using Eclipse
*You will need the Eclipse IDE and Java SE Development Kit!*

*If you already have Eclipse, verify that you are running a version between Luna and 2018 (some newer builds have been found to be incompatible).*

You may download a compatible version of Eclipse here: https://www.eclipse.org/downloads/packages/release/oxygen/3a/eclipse-ide-java-developers

1. Go to https://github.com/Innoxia/liliths-throne-public.
1. Click the button that says "master", and click the line that says "dev". You've now switched the branch to "dev", and the button should have changed to reflect this.
1. Now click the green "Code" button and choose "Download ZIP".
1. You should now have a "liliths-throne-public-dev.zip", save this somewhere, for example C:\JavaCode.
1. Extract the zip, you should have a folder "liliths-throne-public-dev" now.
1. Open Eclipse.
1. Click [File -> New -> Java Project] it will then open a wizard. Uncheck "Use default location", and click browse.
1. Browse to the folder where you unzipped the files, and select it. The project name should update, you can change it if you want.    
1. Click "Next", and then click "Finish".
1. Click [File -> Export].
1. Open "Java", and then choose "Runnable JAR File".
1. Choose "Main - LilithsThrone" under Launch Configuration (IF THERE ARE NO OPTIONS, READ HELP BELOW).
1. Choose an export destination for the .jar file, this should be where you normally run the game from.
1. Click "Finish". A warning may pop up saying "JAR export finished with warnings", this is fine.
1. You should now be able to run the exported .jar file!

If the jar does not run:
1. Check menu: [File -> Properties -> left pane:Java Build Path -> tab:Libraries].
1. If this does not list a JDK 1.8 system library, download the JDK from the above oracle link.
1. Extract it and follow instructions in eclipse [menu:Help -> Search -> type:"Adding a new JRE definition"].
1. Possibly you may have to [menu:Source -> Clean Up], then try again steps 10-15.

####*IMPORTANT* 
*If you're building through github, you'll need to place the 'liliths-throne-public-dev/res' folder in the same directory as the exported .jar!*

## Help
#### No Launch Configuration OR "Could not find main method from given launch configuration." OR "No resources selected" error.
1. First close the Export wizard, and click on the icon in the top of the left pane.
1. Select the Project in the Package Explorer.
1. In the top bar, click [Project -> Properties].
1. In the left column, select "Run/Debug Settings".
1. In the right column, click "New".
1. Choose "Java Application", and click "OK".
1. A Wizard will open, click "Search" next to the "Main Class" Box.
1. Another Window will open, there will be one matching item called "Main - com.lilithsthrone.main" Select this and click "OK".
1. Click "OK".
1. Click "Apply" and "Close".
1. Try Exporting again.

If this does not help.

1. Click [Project -> Clean] and wait for this to finish.
1. Select the Project in the Package Explorer.
1. Open the tree, and locate [src -> com.lilithsthrone.main -> Main.java].
1. Right click on this file and choose [Run As -> 1 Java Application] 
1. The program should now launch, you can play like this, or close it now.
1. Try exporting again, but in the Launch Configuration there should be a new one added. Choose this.


####  "C:\Users\[USERNAME]\git\liliths-throne-public is not an empty directory."
**This is the same error as "Some or all projects cannot be imported as they already exist in the workspace"**

This is most likely because you have already have an outdated build open as a project. Make note of the directory it is reporting as not being empty.
1. Close the import wizard.
1. In the Package Explorer, right click the project, and click "Delete".
1. Make sure you select "Delete Project Contents on Disk". If you don't do this, you will have to do it manually in Windows File Explorer.
1. Try Importing again.

####  Eclipse stuck on importing from Git
The GIT plugin of Eclipse tries to run the "bash" command, and if you hadn't installed Windows Subsystem for Linux  properly, it may cause problems with import.
1. In "Turn Windows features on or off", check if Windows Subsystem for Linux is activated.
1. If it's active, try to execute "bash" in Command Prompt.
1. If result contains the phrase "Windows Subsystem for Linux has no installed distributions", then you have to install Linux distribution. Fortunately, the basic setup can be done with just one command.
1. Run "lxrun /install" in Command Prompt, type "y" to continue, after that - enter some username and password.
1. Run "bash" in Command Prompt once. If the appearance of the console changes, then you've succeeded.
1. Try Importing once again.

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
*You will need the NetBeans IDE, as well as Java SE Development Kit!*

The up-to-date NetBeans download can be found here: https://netbeans.apache.org/download/index.html

NetBeans can download the Lilith's Throne code directly from GitHub:
1. Open NetBeans.
1. Head to the menu [Team -> Git -> Clone]. Note that if you have currently selected a project which uses some Version Control System, the menu will look slightly differently:
   1. If that project uses Git: [Team -> Remote -> Clone].
   1. If that project uses a different VCS: [Team -> Other VCS -> Git -> Clone].
1. Enter "https://github.com/Innoxia/liliths-throne-public.git" as Repository URL (you can leave the User and Password fields empty).
1. Click "Next".
1. Select which branch to download, this is usually "master" for the current Release or "dev" for the latest Development version.
1. Click "Next".
1. You can change the destination (Parent Directory) for the project files, as well as the name (Clone Name) for the project. Click "Finish" to proceed.
1. Wait for the download to complete (this will probably happen in the background, but you can see the progress by the bottom-right corner).
   1. You should get a popup warning once it finishes, asking if you want to open the new project; you do.
1. Select the project on the "Projects" view, by the left.
   1. Were it not visible, you can activate the "Project" view from the [Window] menu, or pressing [Ctrl + 1].
1. To run the game from NetBeans, click the green Play button, by the top bar (or press [F6]).
   1. You might be asked about the Main Class, check "Remember Permanently" and select the only available option.
1. You can now proceed to have fun with the code, if that's what you're here for. If what you want is building a runnable .jar, proceed to step 12.
   1. To access the fancy debugging tools, you'll need to run the game by clicking the corresponding button (or pressing [Ctrl + F5]).
1. In order to create a .jar file to launch the game outside NetBeans, click the "hammer and broom" button by the top bar or use the menu [Run -> Clean and Build Project] (you can instead press [Shift + F11]).
   1. Note that the "hammer" [Run -> Build Project] button (or [F11]) will generally do the same, but forcing a cache clear and compiling everything again is recommended.
   1. Keep in mind that the .jar will not work properly unless a Main Class has been previously defined (AKA: don't skip step 10).

Alternatively, you can use NetBeans' import zip feature to import this project:
1. Use the green "Clone or Download" button on the project's main page, and choose "Download ZIP".
1. In NetBeans, navigate to: [File -> Import Project -> From Zip...].
1. Select the ZIP download from step 1.
1. Proceed from step 9 above.

### Selecting the JDK 8u172 for Lilith's Throne
1. Right click the LT project, in the Projects view (you can activate that view from the [Window] menu, or pressing [Ctrl + 1])
1. Click on "Properties".
1. Select the "Build -> Compile" category from the list.
1. Choose the correct JDK from the dropdown and skip to step 5.
   1. If it's not on the list, click on "Manage Java Platforms".
   1. Click "Add Platform".
   1. Make sure "Java Standard Edition" is selected and click "Next".
   1. Navigate to the location of the JDK 8u172 before clicking "Next".
   1. Give it a fancy "Platform Name" (like "JDK 8u172"), then click "Finish".
   1. Click Close. Repeat step 4.
1. NetBeans will now use the selected JDK. Performing a "Clean and Build Project" is recommended to prevent any potential conflict (shortcut: [Shift + F11]).

## Using IntelliJ IDEA
You can use IDEA to clone the repository directly:
1. Import the project from Git with [File -> New -> Project from Version Control -> Git].
1. The "Git Repository URL" is "https://github.com/Innoxia/liliths-throne-public.git", verify it by clicking the "Test" button.
1. "Parent Directory" and "Directory Name" define where to store the project locally, so pick the location you prefer.
1. Click "Clone" to start importing the project.
1. After a moment, you should see a popup in the bottom right corner informing you that there is an unmanaged pom.xml. Click the popup and select "Add as Maven Project".

Alternatively, you can use a fully local copy:
1. Go to https://github.com/Innoxia/liliths-throne-public in your preferred web browser and click "Clone or Download", then "Download ZIP".
1. Extract the zip file where you want the project to be.
1. In IDEA, go to [File -> New -> Project from Existing Sources].
1. Navigate to the folder you extracted in step 2, select it and click "OK".
1. In the next prompt, select "Import project from external model" and pick "Maven".
1. Click "Next" multiple times and then "Finish". Configure the project as desired, but the default settings should be fine.

Your IDE should now load the project tree with resources, sources, etc. and generate project files as needed. Next, you need to create run configurations:
1. For the default quick start, press [Ctrl + Shift + A] and search for "Edit Configurations". Open the action with that name.
1. In the dialogue, click the "+" in the top left corner and choose "Application".
1. Pick a name for the configuration that you will recognize (e.g. "Build and run").
1. Click the "..." next to the "Main class" text field and search for "Main". Pick the class with that name (com.lilithsthrone.main) and click "OK".
1. Click "Apply" and/or "OK".

You should now be able to build and run the game using the new run configuration (which should be selected by default). The default hotkey is [Shift + F10].
Since this does not create a .jar file, you can add Maven run configurations to package and deploy a file that you can move to another computer:
1. Return to the "Edit Configurations" window (use [Ctrl + Shift + A] if you can't find it).
1. Click the "+" and add a "Maven" configuration.
1. Pick a name for the configuration (e.g. "Package .jar")
1. Set "package" as the command option (without quotation marks).
1. Click "Apply" and/or "OK".

Running the newly created configuration ([Shift + F10], make sure that the correct configuration is selected in the top right corner) should create a runnable .jar file in the "target" directory within your working directory. You can rename this file to whatever you want.

Note that the "data" directory (that stores settings and save games) may be overridden during the building process, so make a backup of it beforehand.

### Common issues

#### The Main class isn't recognized and unresolved symbol errors for Java

The importer sometimes fails to set the correct JDK for your project.
1. Open the "Project Structure" dialogue (the default keybind is [Ctrl + Shift + Alt + S] or search for it with [Ctrl + Shift + A]).
1. In the "Project" tab, select an appropriate project SDK from the drop-down menu.
1. If there is none, click [New... -> JDK] and locate it manually. Note that it must be for Java version 1.8.
