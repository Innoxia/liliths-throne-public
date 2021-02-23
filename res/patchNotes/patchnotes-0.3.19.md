## v0.3.19
### Contributions:
- Fixed issue where spider-morph book's front cover icon wasn't visible. (by NeverLucky)
- Added furred spider tail as a TF option for spider TF potions. (PR#1510 by Stadler76)

### Engine/Modding:
- Added parser hook for BodyCoveringCategory using the prefix 'BODY_COVERING_CATEGORY_'.
- Added BodyPartTag 'TAIL_TAPERING_BULBOUS' for defining the shape of spider abdomen-like tails.

### Other:
- Added 'hug' and 'pettings' actions to interactions with characters staying in the mansion as your guest.
- Changed name of spider TF item from 'Chocolate Coated Cocoa Beans' to 'Chocolate Coated Coffee Beans'.
- Added ability to regrow or remove hymen via the self-TF menu.
- Added dirty talk variations for tribbing.

### Bugs:
- Typos, grammar, and parsing fixes.
- Fixed bug where trying to set a slave free would throw background errors and cause buttons to become unresponsive.
- Fixed bug where Helena would sometimes end up trying to sell more slaves than she actually owned.
- Fixed related issue where Helena's slaves would not be correctly initialised if you were standing on her shop tile at the moment new slaves were generated.
- The 'Dispel elemental' action now works correctly for companions.
- Fixed bug where you'd sometimes lose a proportion of your health and aura when loading a saved game.
- Fixed bug with the new 'Aristocrat' background, where your corruption would double every time you loaded your game. Also fixed related issue where the derived resistances from aristocrat's corruption would be incorrectly saved and loaded.
- Fixed rare cases where nested else/if statements would parse incorrectly.
- Fixed issue with some non-bat tails being referred to as 'bat' tails.
- When freeing Scarlett (and therefore triggering her body to be reset to her harpy form), she now correctly returns to having her default fetishes, and also no longer regains her anal virginity if she'd lost it while being your slave.
- Fixed issue where spider-morphs with an arachnid leg configuration wouldn't have a spinneret.
- Spider-morphs will no longer spawn with crotch-boobs by default.
- Fixed bug where a spinneret's orifice covering wouldn't be coloured according to the character's skin colour.
- Fixed error log entries related to item spawns being generated when spawning a spider-morph attacker.
- Fixed the save/load menu having very dark row background colours when in light mode, which was making it impossible to read saved game names.
- Fixed issue where all clothing stickers would be unlocked by turning debug mode on, instead of the actual sticker unlocks mode.