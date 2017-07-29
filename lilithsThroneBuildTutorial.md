# **Tutorial for Building Liliths Throne**

*You will need the Eclipse IDE and Java SE Development Kit!*
*If you already have Eclipse, verify that you are running Luna or later, as Java 1.8 is required*

https://www.eclipse.org/downloads/

http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html

1. Open Eclipse
2. Click File, Import it will then open a wizard, open Git and choose Projects from Git, click Next
3. Choose Clone URL and then click Next
4. Put https://github.com/Innoxia/liliths-throne-public into the first box in Location
5. Put github.com into the second box in Location
6. Put /Innoxia/liliths-throne-public in the third box in Location. Then click Next
7. The wizard will now ask which branch you want, this will be "master", select this and then click next.
8. Unless you want to change the directory, click Next, It will now download the repository.
9. It will now download the repository.
10. Make sure "Import existing Eclipse projects" is selected, then click Next.
11. Make sure the Lilith's Throne project is selected, then click Finish.
12. Click File, and then select Export.
13. Open Java, and then choose "Runnable JAR File"
14. Choose Main - LilithsThrone under Launch Configuration (IF THERE ARE NO OPTIONS, READ HELP BELOW)
15. Choose an export destination for the .jar file, this should be where you normally run the game from
16. Click Finish, A warning may pop up saying "JAR export finished with warnings" This is fine.
17. You should now be able to run the exported .jar file!

## Help
#### No Launch Configuration
1. First close the Export wizard
2. Select the Project in the Package Explorer
3. In the top bar, click Project, and then Properties
4. In the left column, select Run/Debug Settings
5. In the right column, click New
6. Choose Java Application, and click OK
7. A Wizard will open, click Search next to the "Main Class" Box
8. Another Window will open, there will be one matching item called "Main - com.base.main" Select this and click OK
9. Click OK
10. Click Apply and Close
11. Try Exporting Again
