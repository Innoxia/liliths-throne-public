Lilith's Throne
by Innoxia

Version 0.4.4.5

Compiled using Java version: 17.0.4

PATCH NOTES FOR v0.4.4.5

FriendlyAlienFriend's artwork:
	Added artwork for Leotie (Nyan's mum), in clothed, lingerie, and naked variations, each with a pregnant version.

Engine/Modding:
	Converted all groin clothing from hard-coded files to xml files (located in 'res/clothing/innoxia/groin').

Gameplay:
	Added the next section of the main quest, where you need to travel to Themiscyra to find out if it's being threatened by Lunette's daughters. (There are a few scenes marked as having placeholder dialogue. I'll get them filled in for the next update.)

Weapons:
	Added 'morning star' weapon (one-handed, sold by Vicky).
	Added 'xiphos' weapon (one-handed, only obtainable during the Themiscyra quest for now).
	
Clothing:
	Added 'fur cloak' clothing item (over-torso/neck slot, androgynous, sold by Nyan).
	Added 'nipple chain' clothing item (nipple piercing slot, androgynous, sold by Kate, two stickers to apply/remove extra chains).
	Added 'horn chains' clothing item (horn slot, androgynous, sold by Kate, two stickers to apply/remove extra chains).
	Added 'thin horn rings' clothing item (horn slot, androgynous, sold by Kate).
	Added 'ear cuff chain studs' clothing item (ear piercing slot, feminine, sold by Kate, two stickers to apply/remove extra chains).
	Added 'peplos' clothing item (torso slot, feminine, sold by Monica).
	Added 'himation' clothing item (over-torso slot, feminine, sold by Monica).
	Added 'sandals' clothing item (foot slot, androgynous, sold by Monica).
	Added 'meander ring' clothing item (finger slot, androgynous, only obtainable during the Themiscyra quest for now).
	The 'jockstrap' clothing item no longer incorrectly conceals the wearer's anus.
	
Items:
	Added 'Amazon's Secret' (sold by Ralph, applies a 24-hour effect: when orgasming during a tribbing action, there's a chance to impregnate your partner, using fertility for the impregnation calculation instead of virility).
	Added 'Amazonian Ambrosia' (only obtainable during the Themiscyra quest for now, applies +femininity and +muscle).
	
Sex:
	When having dominant sex with your elemental, your elemental is no longer able to bring an end to the sex scene.
	Characters kneeling behind or humping a partner who's on all fours can now use that partner's tail/tentacles/fingers.
	The 'lubricate fingers' and 'lubricate tail' sex actions should now always be available to you (unless physically impossible or if mouth/fingers/tail are already occupied).

Other:
	Nerfed the attribute bonuses gained from 'Lyssieth's true power' perk.
	Added 'strutter tease' and 'leg lover tease' as fetish-related tease attacks for the 'strutter' and 'leg lover' fetishes, respectively.
	Improved dialogue flow when using your elemental to intimidate the imp guards in the fortresses in Submission.
	Added a 'side braids' hair style.
	During the new 'bitch' content (where the player completely submits to a random NPC), the requirement for NPCs to have the selfish trait in order for them to take the player to be tattooed has been removed.
	Added 'shaggy' as a non-standard covering modifier to cow fur.
	The current time and date is now hidden during a bad end.

Bugs:
	Parsing fixes.
	Fixed issue where 'Lyssieth's true power' perk would not enable you to transform your demonic body parts into human parts.
	Tail and wing self-transformation options should now be populated with all intended races when using the debug menu to test the elder Lilin perks.
	When skipping the prologue at the start of a game, imported characters will now correctly gain 5000 flames on top of any flames they already had, instead of flames being set to 5000.
	Fixed bug in the rental mommy t-shirt encounters where the 'dommy' title would spawn dominant NPCs, and the 'subby' title would spawn submissive NPCs, instead of the other way around.
	Fixed bug with the rental daddy t-shirt where the 'subby' text title was assigned to the 'bottom text' sticker category instead of 'top text'.
	Unique NPCs no longer have a random chance to have the 'ahegao' perk (Takahashi is unaffected by this change).
	Fixed issue where NPCs would not be targeting their intended partner in sex scenes which used the 'preferredTarget' variable.