## v0.3.15
### NoStepOnSnek's races:
- Added snake race, with subspecies of 'snake-morph' (bipedal snakes), 'lamia' (long-tailed snakes), and 'melusine' (long-tailed snakes with wings)
- Added octopus race, with subspecies of 'octopus-morph' (cephalopod body).
- Added capybara race, with subspecies of capybara-morph (bipedal or quadrupedal body).
- Added 6 food/drink items: Boiled Eggs/Snake Oil for snakes, Shrimp Cocktail/Ink Vodka for octopuses, Chocolate Brownie/Chamomile Tea for capybaras.
- Added special attack 'Ink Cloud', unlocked by having an octopus face.

### Contributions:
- Added support for multiple vagina or penis types per race in enchanted TF potion effects. (PR#1465 by Stadler76)
- Fixed issue where the combat moves of the player's elemental were reset. (by AceXP)
- Fixed softlock at the end of combat when fighting imps in Submission. (by AceXP)
- Improved performance by caching subspecies variables and only doing a recalculation when needed. (PR#1471 by AceXP)
- Fixed typo in 'Ear pull' blowjob action. (PR#1472 by darkofoc)
- Fixed issue where the appearance for feral legs was hardcoded and didn't take various leg configurations into account. (PR#1467 by Stadler76)
- Tidied up a lot of race code, and fixed issue where changing a character's tongueType (as part of changing face type) wouldn't resets its modifiers. Also added 'WING_SIZE_' as a parser prefix hook for WingSize enum values. (PR#1480 by Stadler76)
- Updated the tentacle-constrict icon. (by DSG)
- Made the tooltip fade-in animation toggleable via the existing 'Fade-In' game option. This fixes the bug where tooltips were appearing blank when using some versions of java to run the game. (PR#1449 by CognitiveMist)
- Improved damage calculations for special attacks. Added ability to set 'damageVariance' in modded combat moves. Added CombatMoveType POWER for use when defining special attacks instead of having to use ATTACK or SPELL. (PR#1473 by Stadler76)
- Fixed references to the player using their wings in several scenes when the player was in fact able to fly without their wings. (PR#1483 by Stadler76)
- Performance improvement and fixed issue with loading of book entries for subspecies without a bookEntries.xml file. (PR#1484 by AceXP)
- Fixed an oversight for mer-tailed and serpent-tailed non-ferals where the full leg description, complete with references to feet, was being incorrectly shown. (PR#1487 by Stadler76)
- Fixed graphical issues which arose when selecting very dark dye colors on items due to some stroke colors being set to CMYK black (#231f20) instead of RGB black (#000000). (PR#1488 by DSG)
- Fixed parsing errors, typos and minor issues in Nyan's updated quest content. (PR#1491 by AceXP)
- Added new icons for eagle-harpies and fennec-foxes. (by NeverLucky)
- Fixed bug where upon summoning your elemental for the first time, background errors would be thrown. (by AceXP)
- Made artwork file extension checking case insensitive. (PR#1448 by CognitiveMist)
- Improved performance and memory usage by implementing use of a single instance of the TransformerFactory, DocumentBuilderFactory and DocumentBuilder. (PR#1493 by AceXP)
- Fixed issue with wing types not being available in the enchantment menu, and also maximum penetration girth not being able to be selected. (by Stadler76)

### DSG's Enforcer Item updates (PR#1490):
- Updated Enforcer item files: deleted outdated comments; fixed typos; updated weapon effects to be applied via effects element.
- Enforcer weapon effects are now applied on critical hits instead of by random chance.
- Pepperball rifle and pistol now have new special attacks of 'Mag Dump' and 'Seven Rounds Rapid', respectively.
- The Liquid Stun Gun's silly-mode effects have been moved into a new silly-mode variant of the weapon, which can be spawned via the debug menu's cheat spawn menu.
- Added a test Enforcer beret with no sticker requirements to the debug menu's cheat spawn menu.
- Updated Enforcer beret with some description text that was lost during sticker work.

### DSG's race fixes:
- Race version 1.3:
- Removed face requirements from wyvern, drake, coatl, and ryu subspecies weighting for improved support for lesser morphs.
- Removed attribute scaling from the Breath Weapon special attack as it was already having damage boosted in damage calcuation, creating a 'double-dip'. Base damage is now fixed at 75.
- Removed ear requirement from coatls.
- Removed face TF from wyverns and drakes.
- Changed coatl tongue requirement to only require the BIFURCATED modifier.
- Improved flightlessness detection in drakes and ryus.
- Fixed incorrect year and formatting in the book entry for dragons.
- Fixed typo in bear vagina descriptions.
- Fixed gryphons spawning in with generic feathered wings instead of the correct wing type.
- Race version 1.31:
- Fixed three typoes in the dragon vagina TF description.
- Race version 1.32:
- Fixed similar typoes as 1.31 in the vagina TF descriptions for bears, ferrets, gryphons, otters, raccoons, sharks.
- Race version 1.33:
- Fixed formatting issue in the otter beverage effects description.
- Changed Breath Weapon calculation, base damage will now scale with spell damage bonuses. Starting damage is now 20. Having either a dragon torso or dragon face will increase starting damage to 50. Having both will increase starting damage to 75.
- Fixed feral coatls with a leg configuration of TAIL_LONG not being recongized as a coatl.
- Race version 1.34:
- Fixed dragon racialBody not having any tails listed
- Fixed gryphon racialBody not having tail feathers listed
- Added the 'feathered' tail type to dragons
- Added a missing ARM_WINGS_LEATHERY tag to dragon arm-wings

### Engine/Modding:
- Added parser prefix hooks for muscle, body size, and body shape enums (using 'MUSCLE_', 'BODY_SIZE_', and 'BODY_SHAPE', respectively).
- Added parser prefix hook for the DayPeriod enum (using 'DAY_PERIOD_').
- Eye type xml files now support the 'tags' element.
- Added argument to foot/feet parsing commands to ignore clothing when parsed during sex.
- Added 'CHASTITY' item tag, which makes clothing with this tag apply the new chastity effect to characters who equip it.
- Added an 'applicationLength' element to status effect mods. This enables you to define how long a custom status effect will be applied for if its application conditions are found to be true. If this 'applicationLength' is missing, the currently-used default value of -1 is used, so you don't need to refactor any of your status effect mod files to account for this change.
- Added RESTING_LUST as an attribute, so any modded items or effects can now influence this.
- Changed method signature for Sex methods: 'setTimesCummedInside()' and 'incrementTimesCummedInside()'.
- Added 'newSexType(SexParticipantType, SexAreaInterface, SexAreaInterface)' method to the sex class (at the very bottom of the class file) so that there's a way to generate SexType objects from the parser (via the 'sex' object).
- You can now define 'placeLocations' in subspecies xml files to allow for subspecies to spawn in specific place types. (See 'res/mods/innoxia/race/hyena/spotted.xml' for an annotated example.)
- You can now actually leave 'regionLocations' and 'worldLocations' elements in subspecies xml files blank without throwing errors, as the comments said you could.
- Added ability to define body hair availability to the bodyPart xml files: anus, arm, face, penis, and vagina.
- Added ability to define subspecies names based on their leg configuration. Refactoring old subspecies xml files is not needed. If you would like to make use of this functionality, see the 'res/mods/innoxia/race/hyena/spotted.xml' file.
- Also added ability to define subspecies silly-mode names. See the same file as above.
- Added 'HAIR_NATURAL_MANE' as a body part tag for defining HairTypes as being naturally styled into a mane.
- Added 'slotClothing(slot, coloured)' parsing command for parsing the name of a character's clothing.

### Gameplay:
- Reworked Nyan's romance quest, which includes a new warehouse map and a new character. Nyan's post-quest dating content is half-finished, but will be completed for the next release.
- Added 'cougar-morph' as a subspecies of cat-morph. Requirements to be identified as a cougar are: non-patterned tan fur, muscles of at least 'muscular', and body size of at least 'average'.
- All chastity devices (cages and belt) now apply a new chastity status effect when equipped, which increases in intensity the longer the character continues wearing it.
- Added internal map to Slaver Alley's bounty hunter lodge, and renamed it to 'The Rusty Collar' tavern.
- Added public toilets to the Shopping Arcade, which you can use to wash yourself and/or use a gloryhole.

### Items:
- Added: Women's trousers (feminine, leg slot, sold by Nyan).
- Added: Gemstone necklace (androgynous, neck/wrist slots, sold by Nyan).
- Added: Women's winter coat (feminine, over-torso slot, sold by Nyan).
- Bandada can now be worn in the head and hair slots, and supports pattern recolouring.
- Long-sleeved shirt is now unisex, and the colours of its buttons and tag can be changed.
- Added more colouring options to men's and women's watches.
- All glasses can now be displaced by being pulled up (onto forehead).
- Made the three brown clothing colours less saturated, so they look less orange and more brown than before. Also slightly desaturated the tan colour.
- Added 'dark desaturated brown' and 'desaturated tan' as new clothing colours.
- In clothing/weapon dye screens, standard colours for the selected item now have a slightly lighter border, and the tooltip for each colour reveals whether it's a standard colour or not.
- The 'Demonstone necklace' item is now sold by Vicky, not Nyan.

### Sex:
- Added 'Stroke cock' as an ongoing self-action, which you can start/stop like other ongoing actions.
- Added 'foot worship' actions (performing oral on a partner's feet). Available in the slots: Performing oral (in positions Standing/Behind desk/All fours/Lying down), Reverse cowgirl (for non-taurs), Missionary (for non-taurs), Scissoring.
- Weighting of NPCs' choice to use foot actions is no longer partially based on the player character's fetishes, and is now only affected by the foot action content setting and whether the NPC at least likes the associated foot fetish.
- Added more information to NPCs' 'Desires' status effect tooltip when in sex, so that you can now see NPCs' foreplay and main sex preferences.
- Added 'Humping' sex slots to the 'Over desk' sex position.
- Characters can now use their serpent-tail lower body to perform all of the usual tail sex actions. Serpent-tail girth is based on hip size, while length starts at 500% of height.
- Stomach bulge descriptions are now appended when the penetration length inserted into a character's vagina or anus is greater than 12.5% of their height, instead of just when penetration lengths were detected as being uncomfortable.

### Other:
- The five main horn types (curled, spiral, curved, swept-back, straight) are no longer limited to demons, and instead are generically available for all races who lack special horn types in the same way that feathered/leathery wings are.
- Hyena, panther, and demon eyes now have night vision.
- Rabbits, rats, and squirrels now correctly have plantigrade feet by default instead of digitgrade.
- Removed the 8-hour loiter action and added a 24-hour action.
- Resting lust increase from being vulnerable to an ongoing arcane storm has been reduced from 75 to 50.
- Hyena-morphs now correctly spawn with vertical pupils instead of round, and panther-morphs spawn with round pupils instead of vertical.
- You can now get your milk, cum, and girlcum recoloured by Kate at Succubi's Secrets, and you will no longer see covering recolouring options for parts you do not have (e.g. Horn covering colours will no be displayed if you have no horns).
- NPC surnames are now shown in their name tooltip.
- Updated home icons for Arthur, Zaranix, Desryth, and Helena.
- Slightly adjusted penetration girth brackets.
- Added indication of a serpent-tail's length in characters' body tooltip, and changed torso's 'Height' indication to 'Length' if the character is feral and their size is measured head-to-tail instead of how high they are.
- Serpent-tailed lower bodies can now have their length transformed (either from the self-TF menu or by enchanting food items which have the 'serpent tail' leg configuration transformation available to them, such as snake eggs).
- By default, only body parts belonging to mammal or bird races now support having pubic, facial, ass, and underarm hair.
- Feral snake subspecies now have more accurate body-to-tail length ratios, and by default are now closer to 2m in total length as a result.
- Added tentacle attributes to your phone's 'Body stats' screen and removed breast stats for if you're a feral which has no breasts.
- Added subspecies' status effect attributes to their encyclopedia pages.
- Nyan's Clothing Emporium is now open from 09:00-20:00 instead of 06:00-22:00.
- Traders will no longer always sell/buy unidentified clothing for 50 flames, and instead will offer a buy/sell price that's based on the item's unenchanted value. Also halved items' value reduction from having a 'jinxed' rarity.
- Added debug option to spawn an attacker of a defined race when on either a Submission tunnel tile or a Dominion alleyway or canal tile.

### Bugs:
- Numerous parsing fixes.
- Fixed issue where characters could have their location set to coordinates outside of the map's edges.
- Fixed incorrect parsing in numerous footjob actions.
- Fixed rare issue where characters would have unintended sex preferences after changing position.
- The 'Frustrated' status effect is now correctly removed when the character under its effect orgasms.
- Fixed issue with Kruger and Kalahari not spawning correctly as lion-morphs (and retroactively reset their bodies to their correct forms when you load into this version).
- Fixed bug where numerous miscellaneous sex actions (including most of the sadistic actions and the 'pull hair/ears'-type actions) would never be available to use.
- The 'transformationName' element in vagina body part xml definitions now works correctly.
- Fixed bug where Helena would repeat conversation topics before you'd seen all of them.
- Fixed issue with several incorrectly-gendered cock names (e.g. A male fox's cock being called a 'vixen-cock').
- Fixed very rare bug where the game could randomly freeze for a turn when removing certain items of clothing.
- Fixed issue where 'Present pussy' positioning action was available in gloryhole scenes even if you didn't have a vagina.
- The size of the preview image when dying clothing and weapons now correctly fits into the tooltip's width.
- Fixed incorrect colouring of some status effect icons (such as the icons for the smoking effects).
- Fixed minor bug where ordering of attributes in modded race tooltips would be random.
- Fixed issue where tentacle count was not correctly linked to leg count.
- Fixed incorrect descriptions of height and serpent-tail length for feral characters.
- Fixed issue where transforming into a feral wouldn't apply the correct leg configuration changes, which was leaving characters with tails where they should have had them removed.
- Fixed bug where feral attributes of modded races were not being loaded correctly.
- Fixed minor issue with feral Coatl not being described as serpents in some places.
- Fixed issue with unintended subspecies spawning in the stocks at slaver alley (such as dragons).
- Changing pupil and sclera colour in Succubi's Secrets should now work correctly.
- Fixed issue where hair type was not being checked as being suitable for pulling or not in hair-pulling sex actions.
- Fixed issue where non-harpy offspring could be encountered in the harpy nests.
- Tail length percentages in the self-TF menu should now always correctly increment by 5%, instead of sometimes jumping by 6%.
- Fixed issue where rat-penises were no longer spawning in with pale pink skin by default.
- Fixed issue with feral parts on a half-demon (such as on taurs) always being referred so as being of a 'demonic-horse', instead of the half-demon's correct subspecies.
- Fixed bug where name of nipples was being shown instead of name of crotch nipples, and added more randomly-generated descriptors to the list which nipples use.