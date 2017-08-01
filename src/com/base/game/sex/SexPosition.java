package com.base.game.sex;

/**
 * @since 0.1.69
 * @version 0.1.82
 * @author Innoxia
 */
public enum SexPosition {
	
	// Unique:
	
	ROSE_STANDING("Extreme-hand-holding", "You're standing in one of the many empty bedrooms in Lilaya's home. Before you, the cat-girl maid, Rose, is displaying her hands for your benefit."),
	
	PIX_SHOWER_RAPE("Face-to-wall (Pix behind)", "You're standing with your face pressed up against one wall of the shower, and behind you, Pix is growling hungrily into your ear."),

	RALPH_UNDER_DESK("Kneeling under counter", "You're kneeling under Ralph's desk, with your face just inches away from his crotch."),
	
	// Consensual:
	
	CHAIR_BOTTOM("Sitting in chair (bottom)", "You're sitting down, looking up at [npc.name] as [npc.she] smiles down at you."),
	CHAIR_TOP("Sitting in chair (top)", "[npc.Name] is sitting down in front of you, and as [npc.she] looks up into your [pc.eyes+], [npc.she] flashes you a quick smile."),
	
	// Generic:
	
	STANDING("Face-to-face", "You're standing face-to-face with [npc.name]."),
	
	DOGGY_PLAYER_ON_ALL_FOURS("Doggy-style (on all fours)", "You're down on all fours, presenting yourself to [npc.name], who's kneeling down behind you, eager to take advantage of your submissive position."),
	DOGGY_PARTNER_ON_ALL_FOURS("Doggy-style (kneeling behind)", "[npc.Name] is down on all fours, presenting [npc.herself] to you. You're kneeling down behind [npc.herHim], ready to take advantage of [npc.her] submissive position."),
	
	COWGIRL_PARTNER_TOP("Cowgirl (bottom)", "You're lying down on your back as [npc.name] straddles your stomach in the cowgirl position."),
	COWGIRL_PLAYER_TOP("Cowgirl (top)", "You're straddling [npc.name]'s stomach in the cowgirl position."),
	
	KNEELING_PLAYER_PERFORMING_ORAL("Kneeling (performing oral)", "You're kneeling at the feet of [npc.name], with your [pc.face+] hovering just inches away from [npc.her] groin."),
	KNEELING_PARTNER_PERFORMING_ORAL("Kneeling (receiving oral)", "[npc.Name] is kneeling on the floor in front of you, with [npc.her] [npc.face+] hovering just inches away from your groin."),
	
	/**Player has their back to the wall.*/
	BACK_TO_WALL_PLAYER("Back to wall", "You're standing with your back to the wall, and in front of you, [npc.name] is eyeing you up with a hungry glint in [npc.her] [npc.eyes+]."),
	/**Partner has their back to the wall.*/
	BACK_TO_WALL_PARTNER("Partner's back to wall", "You're standing face-to-face with [npc.name] as you push [npc.herHim] back against the wall."),

	/**Player has their face to the wall.*/
	FACING_WALL_PLAYER("Face-to-wall (partner behind)", "You're standing with your face to the wall, and behind you, [npc.name] is pressing into your back as [npc.she] breathes down your neck."),
	/**Partner has their face to the wall.*/
	FACING_WALL_PARTNER("Face-to-wall (behind partner)", "You're standing behind [npc.name] as you push [npc.herHim] into the wall."),
	
	SIXTY_NINE_PARTNER_TOP("Sixty-nine (bottom)", "You're lying beneath [npc.name], who's down on all fours over the top of you. [npc.Her] crotch is positioned over your face, while [npc.her] own head is similarly positioned over your groin."),
	SIXTY_NINE_PLAYER_TOP("Sixty-nine (top)", "You're on all fours over the top of [npc.name]. Your crotch is positioned over [npc.her] [npc.face+], while you're looking down at [npc.her] groin.");
	
	private String name, description;

	private SexPosition(String name, String description) {
		this.name = name;
		this.description = description;
	}

	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
}
