<!-- 
    If you work on this document, please write it to be as simple as
    possible, with very little American/British slang.  Non-native
    speakers have to run this through Google Translate and many
    expressions do not translate well.
-->
# Git Tutorial

> **IMPORTANT:** Your mod must have been created according to [these instructions](new-mod.md).  If you have not read that document, please do so before continuing!

> **NOTE:** This is an optional step.

This tutorial will help you get started using [git](https://git-scm.org) as a way to keep track of what you've changed in your mod.

Git is a "Source Code Management" system designed to allow developers to track changes to the source code, including who made the change, when the change occurred, and why the change occurred (if the person who made the change wrote that down).

Git also allows you to upload your project to sites like [GitHub](https://github.com), [GitLab](https://gitlab.com) and many others.  Doing so makes it much easier for other people to download and contribute to your mod.

This tutorial assumes that you understand how to use your computer operating system's shell (cmd.exe, bash, zsh, and others).

## Installing Git

Before you can continue, you will need to install the git command-line interface (CLI).

* Windows: https://git-scm.com/download/win
* Linux: Install using your package manager (`rpm`, `apt`, etc.)
* Mac OSX: https://git-scm.com/download/mac

Follow the instructions of the installer, then run the following command in your shell to make sure everything works:

```shell
git version
```

You should see output similar to the following:

```
git version 2.25.1
```

We also **highly** recommend using a tool like [git-identity](https://github.com/madx/git-identity) to manage alternate accounts if you do not wish to associate your main account with adult projects.

## Initializing your Working Directory

Git works by creating a collection of information about your files, called a *repository*.  You can then change files when you wish, and when you complete a set of changes, you can lock in your changes and record them to the repository with a *commit*.

First, we need to create that repository.

1. `cd` to your mod directory.
1. Run `git init` to create the repository.

You should see output similar to

```
Initialized empty Git repository in /.../liliths-throne/mods/exampleMod/.git/
```

You should now see a hidden directory called `.git` inside of your mod directory.  **Do not add, remove, or modify anything in this directory**, as it's where git stores your repository data.

## Creating Your First Commit

You have a repository.  Now, you must add the initial state of your mod to it, called the *initial commit*.

1. Run the command `git add .` to *stage* all the files in the current directory (including any subdirectories) for commit.  This means that changes to those files will be included in the commit.
1. Remove files from the stage by running `git rm --cached path/to/file.ext`. More file paths can be added to the end of the command.
    * If you forget the `--cached`, they will be removed *completely*!  This cannot be undone.
1. Once you are ready, run `git commit`.  An editor (usually $EDITOR on Linux) will open so you can write a message describing the changes you've made.
1. Since this is the first changeset, it is traditional to write "Initial commit".
1. Once done, save and close your editor.  `git` will make the commit for you.

You have now created the beginning of your code's history (called a *reflog*).  You can see your history by using `git log`.

## Pushing Your Code to GitHub

*Pushing* is the process of synchronizing changes from your code to another repository, in this case hosted on GitHub.com.

1. Create an account on GitHub.com
1. Follow GitHub's instructions for [creating and using an SSH key](https://docs.github.com/en/authentication/connecting-to-github-with-ssh/about-ssh)
    * There is a button in the upper-right corner of their webpage for switching languages, if you need to.
1. Create a new project on GitHub for your mod.

We now need to tell `git` about the new *remote* repository.

1. `cd` to your mod directory.
1. Using the information on the front page of your new GitHub project, add a new remote (the command should look like `git remote add origin git@github.com:USERNAME/PROJECT.git`)

Now we actually push the code changes to your project:
1. Run `git push -u origin master`.  This will take some time, depending on your Internet connection.
1. Because we used `-u` in the last command to tell git what our *upstream* repository is for the `master` branch (more on that later), any pushes later on are much easier (`git push`).

**NOTE:** GitLab is a very similar process.

## Pulling Changed Code from GitHub

In the future, your code will likely change on your GitHub repository (such as when you add a LICENSE file by using GitHub's tools).  To continue working, you must *pull* those changes into your *local* changeset on your computer.  This process will *merge* the histories of your local workspace and the remote GitHub repositories.

1. `cd` to your mod directory again.
1. Run `git pull`.  This will take time, depending on the size of the changeset and your internet connection.

### Merge Conflicts

Sometimes, your local changes will conflict with changes made remotely, and you will see an error from `git` telling you that automatic merging has failed.

**Do not panic!** Open the files it tells you have problems with a tool like [WinMerge](https://winmerge.org) or [Meld](https://meldmerge.org/).  You will be presented with a *three-way merge*:

<table>
<tr>
<td>The remote state of the file<br/>(<em>"Theirs"</em>)</td>
<td>The file with the changes you've selected<br/>(<em>"Base"</em>)</td>
<td>The stored local state of the file<br/>(<em>"Yours"</em>)</td>
</tr>
<tr><td colspan="3">Changes inside of the selected line</td></tr>
</table>

Fixing a broken merge is like playing a difficult mini-game, where you have to select lines from the left or right to get the most "correct" version of the file.  Sometimes, however, neither line is right or corrections need to be made.  In this event, you must type in the correct text yourself.

Once done with the file, save it and mark it as merged using either the built-in button on your merge tool, or by closing the tool and using `git add filename`.

Once done with all the files in the merge, commit your changes and quickly push them to the repository before someone breaks it again.

## More Information

Git's built-in documentation has much more technical information, and is often a confusing mess.  We recommend using a search engine to search for whatever you are trying to accomplish and finding a third-party tutorial.  The solution is often simpler than it may initially seem.