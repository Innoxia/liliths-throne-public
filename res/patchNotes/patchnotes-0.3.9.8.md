## v0.3.9.8
### Contributors:
- Fixed issue where changing content options at the start of new character creation would throw background errors, causing the UI to become unresponsive. (by AceXP)
- Fixed issue where fluid addiction status effect tooltip descriptions would display 'demonic horse fluid' instead of 'demonic fluid'. (by AceXP)
- Fixed bug where the defined horse-morph names in Name.java were never being used. (by Rydelfox)
- Several parsing and typo fixes. (by AceXP)
- Fixed issue where you would return to an incorrect dialogue scene when leaving inventory management in a friendly occupant's apartment. (PR#1394 by AceXP)
- Typo fixes in the Rat Warrens. (Pr#1400 by aDrunkLittleDerp)
- While carrying an arcane makeup set, characters will now reapply heavy lipstick if it was worn off during sex. (PR#1403 by CognitiveMist)
- Fixed parser errors in vagina reveal descriptions. (PR#1404 by AceXP)
- Enable loading of patterns from the res/mods folder. (PR#1405 by AceXP)
- Fixed bug where five minutes passed, instead of twenty-five, when selling yourself as a submissive partner in Angel's Kiss. (PR#1406 by void-weaver)
- Fixed bug where Sean's fight scene wouldn't initialise correctly. (PR#1407 by void-weaver)
- Fixed bug where if you used an item from an NPC's inventory it would be described as though the NPC was using the item. (PR#1408)

### DSG's Enforcer Uniform Update:
- Added sticker system support, consolidated all extant variants of the Enforcer stabvest, coat, waistcoat, beret, peaked cap, and bowler hat into their respective items.
- Added the following sticker assets that did not already exist in some form in the game: Combat Diver Badge, Commissioner Cap Badge, Commissioner/Deputy Commissioner Visor/Crown Oak Leaves, Commissioner Aiguillette, Elis Cap Badge, Thinis Cap Badge, Itza'aak Cap Badge, Lyonesse Cap Badge.
- Detailed buttons added to the Enforcer coat and waistcoat.
- Fixes and standardization of ribbon racks and name plates.
- Hand optimization of almost all vectorized text.
- Added the 'Contractor's' variant to the stab vest and plate carrier.

### DSG's Enforcer outfit update:
- Added sticker and pattern support.
- Renamed conditionals to be more reader friendly.
- Changed comments to be more clear.
- Removed berets from the Patrol Service Uniform.
- Possibly fixed bugs related to headgear spawning on Enforcers with the wrong colors and no headgear spawning on Enforcers entirely.

### Engine:
- Added ItemTags for defining items, clothing, and weapons as being restricted or illegal, causing them to be unable to be sold to merchants and confiscated by Enforcers.
- Added mod support for defining clothing 'stickers', which apply cosmetic changes to clothing items. (See 'res/mods/innoxia/items/clothing/rentalMommy/rental_mommy.xml' for a fully documented example of how to define them.)

### Gameplay:
- Added two new Enforcer characters and a new quest involving them, all of which has been designed by DSG. The start of this quest will randomly trigger in Dominion's street tiles under the following conditions: no arcane storm; main quest is past Brax's section; over 5 days have passed since completing the 'Angry Harpies' quest; time is 17:00-21:00.
- Enforcers in the 'alleyway Enforcer encounters' will now confiscate illegal items, and arrest you if they find that you're carrying highly illegal items.

### Items:
- Added sticker support to the 'rental mommy' and 'rental daddy' T-shirts.
- 'Biojuice Canisters' and 'Glowing Mushrooms' are now tagged as restricted items.
- 'Demon's Dagger' (no longer sold by Vicky) and all of the Enforcer weapons are now tagged as either illegal (Enforcers will confiscate them) or highly illegal (Enforcers will arrest you).
- All Enforcer clothing is now tagged as illegal (Enforcers will confiscate them).

### Other:
- Slightly altered description of 'cynical' personality trait to differentiate it from 'selfish'.
- Items and weapons will now correctly display ItemTag descriptions in their tooltips.
- Roxy now buys weapons as well as items and clothing. Her buy modifier has been reduced from 0.4 to 0.3 (meaning she will now only give you 30% of an item's value).
- Sean now correctly wears an Enforcer patrol uniform instead of a dress uniform.

### Bugs:
- Parsing fixes.
- Updated example links in xml modding files to point to the correct files.
- Fixed bug where you could get stuck in Brax's office after resolving the part of the main quest which involves him.
- Fixed issue with the cheat guns' 'mag dump' combat move being automatically removed after selecting it.
- Fixed descriptions of putting kitty panties on/off being inverted.
- Fixed bug where weapons could show incorrect image previews in the dye screen. (The 'Demon's Dagger' was suffering from this.)