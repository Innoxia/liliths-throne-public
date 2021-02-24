## v0.3.13
### Contributors:
- Dragon breath attack is now unlocked by the character's race being identified as 'dragon', not by having a dragon torso. (by DSG)
- Fixed issue with the dragon subspecies 'Coatl' not being detected correctly. (by Stadler)
- Fixed bug where feral characters would spawn with crotch-boobs, even if crotch-boob row count was defined as 0. (by Stadler)

### Engine/Modding:
- Added parser hooks to penetration, orifice, and tongue modifiers (using prefixes 'PENETRATION_MODIFIER_', 'ORIFICE_MODIFIER_', and 'TONGUE_MODIFIER_' respectively).
- Added support in racialBody xml files for setting 'breastCrotchType' to NONE for races you want to never spawn with crotch-boobs.

### Other:
- Characters no longer have their cum & girlcum automatically gain the 'musky' modifier when changing into a non-bipedal leg configuration. (Centaurs still have this modifier by default.)
- Sharks and all dragon subspecies will no longer spawn with crotch-boobs.
- Removed restrictive minimum and maximum limitations for insect wing size.
- Ralph no longer sells Impish Brew (you can still buy it from Roxy).

### Bugs:
- Racial food items which have more than one torso type associated with them now have access to those torso types in the enchantment menu.
- Fixed dragon's 'Breath weapon' special attack dealing 0 damage.
- Fixed some minor issues with NPCs spawned as a dragon subspecies having the odd part assigned incorrectly.
- Fixed bug where the penis slot would not accept any clothing if a character lacked a penis, causing strapons to be impossible to equip.