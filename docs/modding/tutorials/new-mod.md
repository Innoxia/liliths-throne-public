<!-- 
    If you work on this document, please write it to be as simple as
    possible, with very little American/British slang.  Non-native
    speakers have to run this through Google Translate and many
    expressions do not translate well.
-->
# Your First Mod

Every mod in Lilith's Throne requires two things:

 * A *directory* (also known as a folder) where all the mod's contents are stored
 * A `mod.xml` file that describes the mod

So, here's how to create your first mod.

## Creating the `mods` Directory

Each mod needs to exist inside of a directory so that it doesn't interfere with other mods and so that it doesn't corrupt the game files. (This is called *compartmentalization*.)

First, we need to be sure the `mods` directory exists.

1. Open the place where you installed Lilith's Throne.
1. If it does not contain a directory called `mods`, create it.

## Creating Your Mod's Directory

Here, we make the directory where all of your mod's content **must** be stored.

You can name this directory whatever you like, but it's best to keep it short and simple.  Generally, it is a good practice to use the mod's name.

For this tutorial, we will use the name `myMod`.

1. Open the `mods` directory in your file browser or shell.
1. Create a new directory called `myMod`.

Your directory structure should now look like:

```
Liliths_Throne_#_#_#_#.jar
mods/
  myMod/
```

From now on, we will refer to `mods/myMod` as the *mod directory*.

## Making `mod.xml`

Lilith's Throne needs to know a few things about your mod so it can load properly.  To provide this information, we must create a file called `mod.xml` in the directory we just created.

1. In your file browser or shell, change directory to the mod directory.
1. Create a new text file named `mod.xml`.
    * **IMPORTANT:**  The file extension must be `.xml`.
    * It's best to use a program such as [notepad++](https://notepad-plus-plus.org/), [vscode](https://code.visualstudio.com/),, kate, gedit, nano, vim, or similar for these files, as they give you more tools and are designed handling them.  
    * It is **NOT** recommended to use Windows Notepad, Word, or other word processors, as they can corrupt the file.
1. Copy and paste the following XML into the file:

```xml
<!-- This is an XML "comment".  They are placed above each part of this file to tell you how they work.  Comments can be removed or added without breaking anything, so if they are annoying to you, feel free to remove them. -->

<!-- The <modInfo> "tag" tells the game where the mod information starts.

It MUST be above any other element in this file, and a matching </modInfo> 
"closing tag" MUST exist at the end of the file to tell the game where the
mod information ends. 
-->
<modInfo>
    <!-- The unique identification code of your mod.  
    
    This does not have to match the mod directory name. Again, keep it
    as short and simple as possible. 
    
    YOU SHOULD NEVER CHANGE THIS AFTER SETTING IT, AS IT WILL CHANGE FILE
    ID PREFIXES, CONFIGURATION, AND OTHERWISE BREAK YOUR MOD AND ANY MODS
    THAT NEED IT. 
    -->
    <id>myMod</id>
    <!-- The displayed name of your mod.  
    You may change this at any point. -->
    <name>My Cool Mod</name>
    <!-- Version of your mod.  This MUST follow the semantic version format. (see https://semver.org/) If you don't want to read the big, complicated document that is linked, just use the format MAJOR.MINOR.PATCH, like below. -->
    <version>0.0.1</version>
    <!-- This tag starts the list of contributors to your mod. These can be artists, coders, or whomever else you wish to credit. -->
    <contributors>
        <!-- The displayed name, username, email, or whatever of a single 
        contributor. -->
        <contributor>MyUsername</contributor>
        <!-- You can add more contributors here. -->
    </contributors>
    <!-- A website that gives information on this mod. Can be left empty. -->
    <website>https://example.com/</website>
    <!-- The link to the source code of this mod. Can be the same as above. Can be left empty. -->
    <sourceCode>https://github.com/...</sourceCode>
    <!-- Optional: Any mods that this mod needs.
    The version "attribute" is also optional.

    <requires>
        <mod id="modID" version="" />
        ...
    </requires>
    -->
    <!-- Optional: Any mods that this mod has problems with.

    <conflicts>
        <mod id="modID" version="" />
        ...
    </conflicts>
    -->
<!-- Closing tag for the modInfo tag above. -->
</modInfo>
```

Once you are done editing the file, save it and close your editor.

You directory structure should now look like:

```
Liliths_Throne_#_#_#_#.jar
mods/
  myMod/
    mod.xml
```

## Next Steps

You are now ready to start the fun part of modding: Actually adding or modifying content.

* [Adding Weapons](add-weapon.md)
* [Adding Maps](add-map.md)

You can also get your mod ready for git (github/gitlab), if you wish.

* [Adding Git Support](git-init.md)