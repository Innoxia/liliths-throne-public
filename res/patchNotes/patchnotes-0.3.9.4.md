## v0.3.9.4
### Contributors:
- Added an encounter in Dominion's alleyways and Lilaya's home's corridors where you stumble upon two of your slaves having sex with one another. To trigger, you need your slaves to have the appropriate outside/house freedom and sex permissions, and the one initiating sex needs to have not had sex for at least 4 hours and to be attracted to the other. (by PoyntFury)
- Improved String-matching utility methods and dynamically generated ColorListPreset classes. (PR#1368 by CognitiveMist)
- Fixed bug where sometimes multiple-partner sex would break upon orgasm. (by CognitiveMist)
- Added 'broodmother pill', which doubles offspring conceived during its effect. (Usage dialogue written by PoyntFury)
- Fixed a bug where having multiple arm pairs allowed an all-out strike with a 2-handed weapons. (PR#1384 by AceXP)
- Added average penis girth in the Encyclopedia information on races. (PR#1385 by AceXP)
- Prevented any succubus/incubus alleyway attackers from spawning with the 'Prude' trait, as it doesn't fit them. (PR#1386 by AceXP)
- Fixed issue where 'Arcane impotence' was being applied when arcane was lower than 15, instead of lower than 10 as it should have been. (PR#1388 by AceXP)
- Standardized all encounters to override getDialogues() instead of accepting Util.newHashMapOfValues() as a parameter. (PR#1389 by DSG)
- Added a new 'limited' option for the bypass sex action content setting, which allows you to perform sex actions of one corruption level higher than your current corruption. (PR#1391 by AceXP)
- Added 'wet wipes' item, which cleans the target's dirty inventory slots when used. (by DSG)
- Added 'peach' as a silly-mode item, sold by Ralph. (Adapted from PR#339 by Rfpnj)
- Added variation of what your starter spell is based on your character's birth month (fireball, slam, ice shard, or poison cloud). (PR#966 by Rfpnj)

### Engine:
- Added modding support for items. You can find a fully-documented example in 'res/items/innoxia/pills/fertility.xml'. Item modding will be expanded at a later date to support enchantments, but for now, the modding framework should be enough for most uses.
- Converted Combat from an Enum to a class, which can now be accessed via the parser using the command 'combat'.

### Rat Warrens:
- Fixed bug where Murk would always use the 'normal' dom pace instead of being rough.
- Fixed several instances of Murk's sex scenes ending with an action named 'Milking room' with no tooltip description.
- Fixed bug where Murk would fuck you without first taking out your pussy pump.
- Fixed issue with missing dialogue node when having sex with Murk in the Gambling Den.
- THe 'Free captives' action in the milking room is now correctly disabled once you've discovered that the milkers cannot be freed.
- Filled in all placeholder dialogue for both Vengar's and Murk's post-quest-completion scenes in the Gambling Den.
- Murk's name now remains as 'Murk' after feminising him in the post-quest gambling den content.

### Items:
- Reduced rarity of crafted 'Fetish Endowment' potions from legendary to epic.

### Sex:
- NPCs will no longer feel the urgent need to expose masculine characters' nipples during sex.
- You can no longer deny your partner's orgasm in the 'glory hole' sex position.
- Fixed bug where your sealed clothing wouldn't be displaced at the start of the Enforcer's 'strip search' sex scene.
- Added positioning requests to the 'glory hole' sex position for when you're dominantly using it, so you can ask the person on the other side to push their pussy or ass up against the hole to be fucked.
- Blocked 'Offer X' and 'Request X' actions in the 'glory hole' sex position (as they didn't really work due to the position's limitations).
- NPCs will now only self-use an item type once in sex (so you no longer have to repeatedly use pills on an NPC who wants to use the opposite type of pill).
- Added an 'Automatic stripping' content option (in the 'sex' property category), which makes it so that all characters start naked in each sex scene. This is disabled by default.
- Sex scenes in which characters automatically start naked no longer remove their piercings.
- Fixed issue with NPCs self-using breeder pills in illogical situations.
- Lowered corruption requirements for most positioning actions.

### Slavery:
- Added 'Save Virginity' as a generic permission for slaves, which is enabled by default.
- Slightly improved wording of slave sex interactions in the log.
- Reduced the chance for slaves to have the 'bonding' event with one another.
- Added filtering options for the 'Occupancy ledger' in the Office room. You can filter events by their type and by slaves involved.

### Other:
- Enforcer encounters now have a cooldown of four hours before they can be randomly encountered again.
- Characters in the 'lying down' sex slot can now kiss/suckle a character's breasts if they are in the 'cowgirl' sex slot.
- The 'American tourist' occupation now only parses the main dialogue screen when converting spelling to American English (as it was causing some lag when trying to parse everything).
- Elementals are now always treated as having all their body parts pierced (due to their transformative abilities).
- Split up Elemental dialogue into 'Interactions' and 'Management' tabs, and added a management option 'Self-clean' to set whether or not your elemental automatically cleans dirty fluids from their body/clothes (enabled by default).
- Elementals now spawn at maximum affection towards their summoner (this will be retroactively applied to your Elemental when loading into this version).
- NPCs who do not have the martial artist perk and who have a main weapon equipped and no offhand weapon equipped will now be far less likely to use the 'offhand strike' attack.
- The SWORD Enforcers in the Enforcer Warehouse (in Claire's teleportation quest) now prefer to attack in combat rather than teasing.
- If you have anal content disabled, the 'buttslut' fetish is no longer a requirement for unlocking the 'lusty maiden' fetish.
- You can no longer encounter the same person in the nightclub more than once per night.
- Moved 'Post-sex clothing replacement' property category from 'gameplay' to 'sex'.
- You can no longer give Bunny or Loppy items during sex.
- Added pregnancy reactions for if you manage to impregnate Bunny and Loppy, and slightly improved the flow of dialogue when entering their rooms.
- Added two new upgrades to the 'Soothing Waters' spell, which cause it to clean the target's body & clothing and wash out fluids from their orifices.
- Added 'Head pat' action to Nyan's romance actions.
- Added 'naive' and 'cynical' personality traits. (They are like the other core traits in that they are mostly for roleplaying purposes and will have some limited dialogue variations throughout the game.)
- Updated 'Enforcer HQ' map.

### Bugs:
- Numerous parsing and typo fixes.
- Fixed issue with slaves not being correctly identified as being able to ambush you in Dominion even if you gave them the right permissions.
- Fixed issue where slaves would never jump you for sex in alleyways that already had a persistent character present in them.
- Slaves being held in Slavery Administration will no longer generate slave-interaction events with other slaves.
- Silly-mode items are no longer tracked by the Encyclopedia (so that players doing normal playthroughs will not be confused as to why some items are unobtainable).
- Fixed minor issue with the display of clothing and weapon Encyclopedia counts passing their totals.
- Fixed issue where characters with an unknown race would have their body overview tooltip show that they had crotch-boobs, even if they didn't have any.
- Fixed bug where characters' images wouldn't display in their character overview screen if you had the 'American tourist' occupation.
- Fixed issue where you couldn't change your Elemental's surname.
- Fixed bug where attempting to enslave gang members in the Rat Warrens would cause the game to break.
- Fixed issue with pregnancy roulette where sex would end after the character being bred had orgasmed, even if you hadn't orgasmed yet.
- You no longer lose affection with characters in the pregnancy roulette game when sex is automatically ended after orgasming.
- Newly-created characters are no longer considered to have lost their penile virginity if you give them 'handjobs received' experience.
- A character's oral virginity loss description will now correctly be displayed in their body overview screen.
- Fixed issue with Natalya's clothing not being displaced when starting sex with her.
- Fixed issue where sex scenes with pure virgins could sometimes cause the game to lock up and become unresponsive.
- Fixed bug where you could talk to your elemental via the phone action during combat, sex, or other non-neutral scenes.
- Fixed issue where Elementals could use arcane scrolls even though they could not use the upgrade points gained from them.
- Fixed bug where when Lyssieth self-transformed into her human form, her earrings would be unequipped and placed in her inventory.
- Fixed issue with background error being thrown when first entering the Enforcer Warehouse (in Claire's teleportation quest).
- Fixed bug where looking at your item Encyclopedia while trading with an NPC would not show any tooltips and throw numerous background errors.
- Removed ability to contact previous partners in the nightclub if they are no longer attracted to you (if, for example, you've changed your femininity), as it was causing issues with sex immediately ending.
- Fixed issue where maximum limits of testicle size and penis girth were swapped with one another when enchanting clothing with secondary and tertiary size effects.
- Clothing which should be discarded on unequip is no longer placed into the unequipping character's inventory as a result of having body parts transformed (such as unequipping a condom when a character's penis is removed).
- Fixed issue where milking slaves would sometimes not unequip their milking pumps after finishing their work hours in the milking room.
- Fixed issue where opening the 'characters present' screen while managing a slave would result in background errors being thrown and the UI becoming unresponsive.
- Fixed bug where if you ran out of items while enchanting during trading, you'd be able to take all of the shopkeeper's items for free.
- Fixed issue where enslaving your offspring without first beating them in combat would display the interaction interface after enslaving them.
- Fixed issue where slaves generating background sex interaction events would throw background errors if you were in a sex scene at the time they were generated.
- Fixed bug where exiting Dominion locations (such as the City Hall or Enforcer HQ) during an arcane storm would spawn persistent characters on your tile without offering you any interactions with them.
- Upgrading a room into the spa from the Office's 'Occupancy ledger' will no longer place the 'under construction' tile next to the office instead of next to the spa tile.
- Fixed bug where your elemental was being affected by the 'Blinded by Freedom' status effect (from the silly mode's 'American Tourist' background perk).
- Fixed bug where the 'Localised Storm' upgrade for the 'Arcane Cloud' spell would not apply lust damage to the character affected by it.
- Fixed strawberry and blueberry fluid flavour using plural descriptions where all other flavours were singular.
- In her sex scene in Lilaya's room, Rose should no longer have her orifices penetrated during 'quick sex' action generation.