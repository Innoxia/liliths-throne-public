Modding Enhancements Mod for Lilith’s Throne
By Felyndiira (Sara on the LT Discord)


What does it do?

The mod allows you to use XML editing to customize the following for any non-special NPC (or the player):

Character Descriptions
Whether an NPC will automatically resist you just for having the ‘Unwilling Fucktoy’ fetish.
All dirty talk dialogue
All slave management descriptions

The mod does not add any in-game interfaces (that would be too difficult to complete in so little time), but allows you to XML edit either a save file or character export to add these.

Will characters with these modded tags still work with the normal game?

Yes!  Tags that aren’t recognized by the game are simply ignored, so the extra tags that this mod adds will simply be disregarded by the base game, resulting in a perfectly normal PC or NPC.

How do I add a custom description?

To add a custom description, you must first specify the following flag in the character’s XML tags:

<customDescription value="true"/>

Then, you can edit the description tag to whatever you wish.  Special parser tags like [npc.name] or [npc.cRed(redtext)] are available to use.  You can use &lt;br&gt; or &lt;p&gt; tags to create newlines (note that <> can’t be used in XML fields, so you need to use &lt;&gt; instead):

<description value="[npc.name] is all about the explosions.  Give [npc.her] an explosion a day and she will be happy.&lt;br&gt;&lt;br&gt;This is basically a test character."/>

How do I add custom Dirty Talk dialogue?

Custom dirty talk dialogue uses the <customDialogue> tag, which looks something like this:

 <customDialogue>
        <dialogueSet tag="pened_sub_noresist">
          <htmlContent condition="">What is this?  I am feeling...so feverish.  Perhaps you do have the qualifications to tame even me.</htmlContent>
        </dialogueSet>
        <dialogueSet tag="dirtytalk_sub_resist">
          <htmlContent condition="">No...please stop.  This power is too much for me...</htmlContent>
          <htmlContent condition="">Aah!  What sort of unearthly monstrosity are you to assault a pure woman so?!</htmlContent>
        </dialogueSet>
        <dialogueSet tag="dirtytalk_sub_noresist">
          <htmlContent condition="">You seek to dominate of the [npc.cRed(Five Crimson Flames)]>  Interesting!  I'll take your challenge!</htmlContent>
          <htmlContent condition="">What sort of majestic exploding sexual power will you show, to please a master like myself?</htmlContent>
        </dialogueSet>
        <dialogueSet tag="nopen_dom_all">
          <htmlContent condition="">[npc.iRed(Interesting!)]  So you seek to submit to the Master of the [npc.cRed(Five Crimson Flames)]?  Let's see what you are made of!</htmlContent>
          <htmlContent condition="">Be a good [npc.girl] and orgasm already, lest I be forced to unleash the power hidden in my Hagan eye!</htmlContent>
          <htmlContent condition="">Do you not understand the powers you seek to challenge?  My sexual techniques is passed down through generations of Crimson Demons!</htmlContent>
        </dialogueSet>
        <dialogueSet tag="pened_dom_all">
          <htmlContent condition="">Follow my guidance, thrust your [npc.orifice] into me to my instruction, and you too may master the [npc.cRed(Five Crimson Flames)].</htmlContent>
        </dialogueSet>
        <dialogueSet tag="pening_sub_noresist">
          <htmlContent condition="">I am assaulting your fortress, and yet why is it that your will overpowers my own?!</htmlContent>
        </dialogueSet>
        <dialogueSet tag="pened_all">
          <htmlContent condition="">[npc.iRed(Aahhh!)]  You've unleashed a power you know not how to control with that blow!</htmlContent>
        </dialogueSet>
        <dialogueSet tag="pening_dom_all">
          <htmlContent condition="">I invoke the [npc.cRed(Five Crimson Flames)] and strike fear into those who I thrust into.  [npc.bYellow(EXPLOSION!)]</htmlContent>
        </dialogueSet>
        <dialogueSet tag="pened_sub_resist">
          <htmlContent condition="">I...my powers are fading.  Please, let me have some of my dignity and leave me be.</htmlContent>
        </dialogueSet>
        <dialogueSet tag="nopen_sub_noresist">
          <htmlContent condition="">[npc.iRed(Interesting!)]  So you seek to dominate the Master of the [npc.cRed(Five Crimson Flames)]?  Let's see what you are made of!</htmlContent>
          <htmlContent condition="">It will take more than that to conquer the mind of a master of the supreme magical arts!</htmlContent>
          <htmlContent condition="">Oh, [npc.cRed(Five Crimson Flames)], give me the power to show this fool the folly of [npc.his] petty attempts at dominance!</htmlContent>
        </dialogueSet>
  </customDialogue>

f

