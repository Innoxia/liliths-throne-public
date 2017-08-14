package com.base.game.dialogue.places.dominion.slaverAlley;

import com.base.game.character.Quest;
import com.base.game.character.QuestLine;
import com.base.game.character.attributes.AffectionLevel;
import com.base.game.character.attributes.ObedienceLevel;
import com.base.game.dialogue.DialogueNodeOld;
import com.base.game.dialogue.responses.Response;
import com.base.game.inventory.clothing.ClothingType;
import com.base.main.Main;
import com.base.utils.Colour;
import com.base.world.WorldType;
import com.base.world.places.HarpyNests;
import com.base.world.places.SlaverAlley;

/**
 * @since 0.1.83
 * @version 0.1.83
 * @author Innoxia
 */
public class ScarlettsShop {
	
	public static final DialogueNodeOld SCARLETTS_SHOP_EXTERIOR = new DialogueNodeOld("Scarlett's shop", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
					+ "Tucked away in one corner of Slaver Alley, you see a shop that's quite unlike all the others."
					+ " Although the words 'Scarlett's Shop; open for business' are painted in fancy gold lettering above the door, the large glass windows don't show any sign of there being any slaves for sale."
					+ " While the areas in front of all of the other shops in Slaver Alley are filled with advertisement boards and platforms upon which to display their goods,"
						+ " the area in front of 'Scarlett's Shop' is noticeably barren; yet further proof that this particular store must have fallen upon hard times."
				+ "</p>"
				+ (Main.game.getPlayer().getMainQuest() == Quest.MAIN_1_E_REPORT_TO_ALEXA
					?"<p>"
						+ "You haven't gone to report to Scarlett's matriarch, Alexa, yet, and you don't really want to have to talk to Scarlett until you've done as she's asked."
						+ " Scarlett said that you can find Alexa up in the Harpy Nests, so you'll need to go there report to her before stepping foot inside this shop again."
					+ "</p>"
					:"");
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				if(Main.game.getPlayer().getMainQuest() == Quest.MAIN_1_E_REPORT_TO_ALEXA) {
					return new Response("Enter", "You should go and find Alexa before entering Scarlett's Shop again.", null);
					
				} else {
					return new Response("Enter", "Enter the shop.", SCARLETTS_SHOP);
				}

			}else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld SCARLETTS_SHOP = new DialogueNodeOld("Scarlett's shop", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			if (Main.game.getPlayer().getMainQuest() == Quest.MAIN_1_D_SLAVERY) {
				return "<p>"
						+ "Pushing open the door, you find that the shop's interior is just as bare as you feared."
						+ " There isn't a single slave for sale in sight, and the only sign of life is a rather defeated-looking harpy slouched down at the shop's front desk."
					+ "</p>"
					+ "<p>"
						+ "The harpy, who you assume to be Scarlett, looks up at you as you enter the shop, and with an annoyed huff, she shouts out, "
						+ "[scarlett.speech(If you aren't here to donate any slaves, then you'd better turn around and fuck right off! I'm not in the mood to deal with any fucking morons like you today.)]"
					+ "</p>"
					+ "<p>"
						+ "Apparently this extremely rude harpy is the person that you need to deal with, and, letting out an annoyed sigh, you wonder if you should ask about Arthur now, or come back at another time."
					+ "</p>";
				
			} else {
				return "<p>"
							+ "Pushing open the door, you find that the shop's interior is just as bare as you feared."
							+ " There isn't a single slave for sale in sight, and the only sign of life is a rather defeated-looking harpy slouched down at the shop's front desk."
						+ "</p>"
						+ "<p>"
							+ "The harpy, who you assume to be Scarlett, looks up at you as you enter the shop, and with an annoyed huff, she shouts out, "
							+ "[scarlett.speech(If you aren't here to donate any slaves, then you'd better turn around and fuck right off! I'm not in the mood to deal with any fucking morons like you today.)]"
						+ "</p>"
						+ "<p>"
							+ "You're quite taken aback at Scarlett's rude words, and, deciding that it's probably best not to get involved with someone like that, you turn around and head for the exit."
						+ "</p>";
			}
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				if (Main.game.getPlayer().getMainQuest() == Quest.MAIN_1_D_SLAVERY) {
					return new Response("Ask for Arthur", "Ask Scarlett if she's got a slave named Arthur for sale.", SCARLETT_IS_A_BITCH);

				}else {
					return null;
				}
				
			} else if (index == 0) {
				return new Response("Leave", "Exit the shop.", SCARLETTS_SHOP_EXTERIOR);
				
			} else {
				return null;
			}

		}
	};
	
	public static final DialogueNodeOld SCARLETT_IS_A_BITCH = new DialogueNodeOld("Scarlett's shop", ".", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "Letting out a defeated sigh as you wonder why everything has to be so difficult, you walk towards the front desk where Scarlett is sitting."
					+ "</p>"
					+ "<p>"
						+ "[scarlett.speech(Didn't I tell you to fuck off already?)]"
						+ " the rude harpy calls out, sitting up in her chair as you approach."
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Look, I just need to know if you've got a slave for sale going by the name of 'Arthur', ok?)]"
					+ "</p>"
					+ "<p>"
						+ "As you mention the name 'Arthur', Scarlett stands up from behind her desk, and her cheeks flush red as she starts to shout and curse,"
						+ " [scarlett.speech(If I hear that fucking name one more time, I swear I'm going to kill someone!"
						+ " Are you working with them?! Huh?! Those fucking cunts who fucked me over?! You've got five fucking seconds to start explaining!)]"
					+ "</p>"
					+ "<p>"
						+ "Despite her foul language and aggressive posturing, you find Scarlett's little outburst to be more embarrassing rather than anything else."
						+ " She clearly lacks the physical strength required in order to follow through on any of her threats, so it's more out of a desire to calm her down rather than due to feeling intimidated that you start to answer her,"
						+ " [pc.speech(Calm down, I'm not working with 'them', whoever they might be. I'm just a friend of Arthur's who's been trying to track him down."
						+ " I found out from a helpful enforcer that he was meant to have been given to you."
						+ " If you're still in possession of him, I'd like to see if I can buy his freedom, and if not, then could you [style.italics(please)] just tell me who you sold him to?)]"
					+ "</p>"
					+ "<p>"
						+ "[scarlett.speech(Huh,)]"
						+ " Scarlett huffs as she sits back down, "
						+ "[scarlett.speech(so I guess you really are as stupid as you look. Can't you see that I've got no slaves for sale? I'm fucking finished. And it's all thanks to that business with Arthur...)]"
					+ "</p>"
					+ "<p>"
						+ "Rolling your eyes at the annoying harpy's reaction, you don't get any time to respond before she continues,"
						+ " [scarlett.speech(There might be a way for both of us to profit here though."
						+ " Yeah, I know what happened to Arthur, [style.italics(and)] where he's gone, but I'm also not going to tell a fucking idiot like you anything about it."
						+ " Not without helping me out first, at least.)]"
					+ "</p>"
					+ "<p>"
						+ "Wondering just how many different people you're going to have to deal with before finally finding Arthur, you sigh,"
						+ " [pc.speech(And what is it I'd need to do?)]"
					+ "</p>"
					+ "<p>"
						+ "[scarlett.speech(Even a clueless moron like you can see that this business is a complete failure,)]"
						+ " Scarlett grumbles as she leans back in her chair, "
						+ "[scarlett.speech(and my matriarch is [style.italics(not)] going to be happy when she finds out."
						+ " If you want to find out what happened to your stupid little friend, you're going to go up to the Harpy Nests, find a matriarch called 'Alexa',"
							+ " tell her that my business is bust, and take whatever punishment she'll decide upon on my behalf.)]"
					+ "</p>"
					+ "<p>"
						+ "You realise that if you ever want to find out what happened to Arthur, you're going to have to agree to Scarlett's demands, even if you don't actually plan on taking any punishment on her behalf."
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Agree", "Agree to help Scarlett.", SCARLETT_IS_A_SUPER_BITCH) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().incrementQuest(QuestLine.MAIN));
					}
				};

			}else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld SCARLETT_IS_A_SUPER_BITCH = new DialogueNodeOld("Scarlett's shop", ".", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "Deciding to just agree with the insufferable harpy for now, you respond,"
						+ " [pc.speech(Fine, I'll do it, but you'd better keep your end of the bargain here. When I get back, you're going to tell me exactly what's happened to Arthur, ok?)]"
					+ "</p>"
					+ "<p>"
						+ "[scarlett.speech(Just fuck off and do it already!"
						+ " Fuck, you're a real asshole to try and talk to, you know?!)]"
						+ " Scarlett shouts, looking just as infuriated as you feel right now, "
						+ "[scarlett.speech(Don't bother coming back here until you've taken Alexa's punishment, ok?"
						+ " And don't let her go easy on you, I'm gonna enjoy hearing what she did to you when you get back!)]"
					+ "</p>"
					+ "<p>"
						+ "Not wanting to waste any more time talking to her, you turn your back on Scarlett and walk out of the shop."
						+ " As the door swings shut behind you, you find yourself wondering if you've ever met anyone as annoying as that harpy is."
						+ " You seriously hope that this matriarch, 'Alexa', is nothing like Scarlett, and as you set off in the direction of the Harpy Nests, you wonder how many more hurdles you'll be presented with before finally finding Arthur..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 0) {
				return new Response("Leave", "Exit the shop.", SCARLETTS_SHOP_EXTERIOR);
				
			} else {
				return null;
			}
		}
	};
	
	
	public static final DialogueNodeOld ALEXAS_SHOP_EXTERIOR = new DialogueNodeOld("Scarlett's shop", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			if (Main.game.getPlayer().getMainQuest() == Quest.MAIN_1_F_SCARLETTS_FATE) {
				return  "<p>"
							+ "Once again, you find yourself standing before Scarlett's shop, but this time, you notice that the surrounding area is a lot quieter than usual."
							+ " As you walk towards the front entrance, you pass a couple of wolf-girls as they hurry past the shop, and you can't help but overhear a snippet of their whispered conversation,"
							+ " [npcFemale.speech(...I'm telling you, it's <i>the</i> Alexa in there! Hurry up, before she sees us!)]"
						+ "</p>"
						+ "<p>"
							+ "Stepping forwards, you see that the fancy gold lettering reading 'Scarlett's shop; open for business' has been roughly crossed out in red paint, and beneath,"
								+ " in the same scarlet hue, you read the new words 'Alexa's pet shop: For Sale'."
						+ "</p>"
						+ "<p>"
							+ " Curious to find out what's become of Scarlett, you wonder if you should enter the shop now, or come back later."
						+ "</p>";
			} else {
				return  "<p>"
							+ "Once again, you find yourself standing in the quiet area in front of Scarlett's old shop."
							+ " Stepping forwards, you see that the fancy gold lettering reading 'Scarlett's shop; open for business' has been roughly crossed out in red paint, and beneath,"
								+ " in the same scarlet hue, you read the new words 'Alexa's Pet Shop: For Sale'."
						+ "</p>"
						+ "<p>"
							+ "You wonder if you should enter Alexa's Pet Shop now, or come back later."
						+ "</p>";
			}
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				if (Main.game.getPlayer().getMainQuest() == Quest.MAIN_1_F_SCARLETTS_FATE) {
					return new Response("Enter", "Enter the shop.", ALEXAS_SHOP) {
						@Override
						public void effects() {
							Main.game.getAlexa().addSlave(Main.game.getScarlett());
							Main.game.getScarlett().setObedience(ObedienceLevel.POSITIVE_TWO_OBEDIENT.getMedianValue());
							Main.game.getScarlett().resetInventory();
							Main.game.getScarlett().equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.NECK_SLAVE_COLLAR, Colour.CLOTHING_BLACK_STEEL, false), true, Main.game.getAlexa());
							Main.game.getScarlett().equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.BDSM_BALLGAG, Colour.CLOTHING_PINK, false), true, Main.game.getAlexa());
							Main.game.getScarlett().equipClothingFromNowhere(ClothingType.generateClothing(ClothingType.BDSM_WRIST_RESTRAINTS, Colour.CLOTHING_PINK, false), true, Main.game.getAlexa());
						}
					};
					
				} else {
					return new Response("Enter", "Enter the shop.", ALEXAS_SHOP);
				}

			}else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ALEXAS_SHOP = new DialogueNodeOld("Alexa's pet shop", ".", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			if (Main.game.getPlayer().getMainQuest() == Quest.MAIN_1_F_SCARLETTS_FATE) {
				return "<p>"
							+ "Pushing open the front door, you're greeted by the familiar, beautiful figure of Alexa sitting down behind the shop's counter."
							+ " She throws you a delighted smile as you step inside the shop,"
							+ " [alexa.speech(Hello again! Please, do come in!)]"
						+ "</p>"
						+ "<p>"
							+ "Doing as the harpy says, you walk up to the front desk, where you return Alexa's greeting,"
							+ " [pc.speech(Hi Alexa. Is Scarlett here? I need to get some information from her.)]"
						+ "</p>"
						+ "<p>"
							+ "Alexa smiles up at you, before turning her head to one side and calling out,"
							+ " [alexa.speech(Come, pet! This nice [pc.race] is asking after you!)]"
						+ "</p>"
						+ "<p>"
							+ " [scarlett.speech(M-Mrph...)]"
						+ "</p>"
						+ "<p>"
							+ "You hear a quiet, muffled groan come from one side of the shop, and, turning to look in the direction Alexa is now facing,"
								+ " you see the submissive form of Scarlett kneeling beneath one of the many empty platforms that litter the edge of the store."
							+ " Your eyes open wide as you watch her obey her matriarch;"
								+ " obediently crawling out from her refuge, you see that she's been stripped naked and forced to wear a pair of wrist restraints, a ball gag, and, most shockingly of all, a heavy steel slave collar."
						+ "</p>"
						+ "<p>"
							+ "[alexa.speech(Good pet!)]"
							+ " Alexa cries, leaning down to stroke Scarlett's plume of bright-red feathers,"
							+ " [alexa.speech(Now stay!)]"
						+ "</p>"
						+ "<p>"
							+ "Turning back towards you, the matriarch smiles,"
							+ " [alexa.speech(As you might have seen from the sign outside, both this shop <i>and</i> its previous owner are now for sale. So, if you were hoping to get some information from Scarlett, you'll have to buy her first!)]"
						+ "</p>"
						+ "<p>"
							+ "You suppress a groan as you wonder just how many more hurdles you'll have to overcome before finally finding Arthur."
							+ " No matter what your views on slavery might be, it looks as though you only have one option."
							+ " You'll have to buy Scarlett, then demand that she tell you what happened to Arthur."
						+ "</p>";
					
			} else {
				return "<p>"
							+ "Pushing open the front door, you're greeted yet again by the beautiful matriarch, Alexa, sitting down behind the shop's counter."
							+ " She throws you a warm smile as you step inside the shop,"
							+ " [alexa.speech(Hello again! Have you got the two thousand flames yet?)]"
						+ "</p>"
						+ "<p>"
							+ "[scarlett.speech(M-Mrph...)]"
							+ " Scarlett's muffled groan drifts out from the other side of the shop as she hears Alexa talk about selling her."
						+ "</p>";	
			}
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				if (Main.game.getPlayer().getMainQuest() == Quest.MAIN_1_F_SCARLETTS_FATE) {
					return new Response("Offer to buy", "Offer to buy Scarlett from Alexa.", ALEXAS_SHOP_FOR_SALE);
					
				} else {
					if(Main.game.getPlayer().getMoney() >= 2000) {
						return new Response("Buy Scarlett (<span style='color:" + Colour.CURRENCY.toWebHexString() + ";'>" + Main.game.getCurrencySymbol() + "</span> 2000)"
								, "Buy Scarlett for 2000 flames.", ALEXAS_SHOP_BUYING_SCARLETT) {
							@Override
							public void effects() {
								Main.game.getPlayer().incrementMoney(-2000);
							}
						};
						
					} else {
						return new Response("Buy Scarlett (" + Main.game.getCurrencySymbol() + " 2000)", "You don't have enough money to buy Scarlett.", null);
					}
					
				}

			} else if (index == 0 && Main.game.getPlayer().getMainQuest().getSortingOrder() > Quest.MAIN_1_F_SCARLETTS_FATE.getSortingOrder()) {
				return new Response("Leave", "Leave Alexa's Pet Shop.", ALEXAS_SHOP_EXTERIOR);
				
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ALEXAS_SHOP_FOR_SALE = new DialogueNodeOld("Alexa's pet shop", ".", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "[pc.speech(If that's what it takes, I'll buy Scarlett,)] you say."
					+ "</p>"
					+ "<p>"
						+ "[alexa.speech(Excellent!)] Alexa cries, leaning forwards in her chair,"
						+ " [alexa.speech(Just show me your slaver license, and then we can talk about what she's worth!)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Wait, a slaver license? How do I get one of those?)] you ask."
					+ "</p>"
					+ "<p>"
						+ "[alexa.speech(Oh dear,)] Alexa sighs,"
						+ " [alexa.speech(I thought you'd have one already, considering how you had business with Scarlett. Well, it looks like you won't be able to buy her after all."
							+ " Slaver licenses aren't being issued right now, and it might be months before there's an opportunity to obtain one...)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(I wonder if Lilaya might be able to help me with that...)] you think out-loud."
					+ "</p>"
					+ "<p>"
						+ "Upon hearing your words, Alexa suddenly sits bolt-upright, looking up at you with an amazed expression on her face,"
						+ " [alexa.speech(Wait... <i>The</i> Lilaya, as in Lyssieth's daughter?! You know her?!)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Well, it's kind of a long story, but we're sort of related,)] you reply, amazed that Alexa knew Lilaya by name,"
						+ " [pc.speech(I'm guessing you know her as well then?)]"
					+ "</p>"
					+ "<p>"
						+ "[alexa.speech(Why didn't you mention this earlier?! Of course I know Lilaya! She's helped me with a lot of things in the past...)]"
						+ " Alexa's voice trails off as she sinks into her own thoughts for a moment, before suddenly snapping back to reality,"
						+ " [alexa.speech(Anyway! This changes everything! To be honest, I've been wanting to get out of the slave trading business for a while now;"
							+ " I don't have the time to run this store myself, and I can't trust any of the harpies in my flock to do a good enough job."
							+ " The way I see it, we can help each other out here.)]"
					+ "</p>"
					+ "<p>"
						+ "Thankful that you're finally getting a lucky break, you wait patiently as Alexa explains her offer."
					+ "</p>"
					+ "<p>"
						+ "[alexa.speech(Scarlett here has been a thorn in my side ever since she joined my flock. No doubt as you've already discovered, she's incredibly rude, thoughtless, and disrespectful towards everyone she meets."
							+ " I filled out the paperwork to have her enslaved a while ago, but I wanted to give her one more chance to redeem herself first."
							+ " A chance that she wasted.)]"
					+ "</p>"
					+ "<p>"
						+ " Alexa frowns in Scarlett's direction, before continuing,"
						+ " [alexa.speech(I want her out of my flock, and I want to get out of the slave trading business."
							+ " I owe Lilaya countless favours, so I'm willing to transfer both my slaver license and ownership of this shop to you, on three conditions;"
							+ " one, you take ownership of Scarlett; two, you tell Lilaya how I've helped you out; and three, you pay the license transfer fee of two thousand flames."
							+ " Agreed?)]"
					+ "</p>"
					+ "<p>"
						+ "It seems as though this is what it's going to take to finally get the information you need from Scarlett."
						+ " Two thousand flames doesn't seem like much to pay in order to obtain a highly-coveted slaver license, and Alexa's even willing to grant you ownership of this shop!"
						+ " You thank your lucky stars that Alexa is indebted to Lilaya..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Agree", "Agree to the deal.", ALEXAS_SHOP_FOR_SALE_PLAYER_RESERVATION) {
					@Override
					public QuestLine getQuestLine() {
						return QuestLine.MAIN;
					}
				};

			}else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ALEXAS_SHOP_FOR_SALE_PLAYER_RESERVATION = new DialogueNodeOld("Alexa's pet shop", ".", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "[pc.speech(Agreed,)] you say, reaching out to shake Alexa's feathered hand."
					+ "</p>"
					+ "<p>"
						+ "[alexa.speech(Excellent! Let me know when you've got the two thousand flames needed to complete the transfer paperwork, then we can head down to the slavery administration and get things settled!)]"
						+ " Alexa cheerily replies, leaning back in her chair."
					+ "</p>"
					+ "<p>"
						+ "Assuring Alexa that you'll mention her kindness to Lilaya the next time you see her, you step away from the desk to count your coins..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			return ALEXAS_SHOP.getResponse(index);
		}
	};
	
	public static final DialogueNodeOld ALEXAS_SHOP_BUYING_SCARLETT = new DialogueNodeOld("Alexa's pet shop", ".", true, true) { //TODO
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "<i style='color:"+Colour.GENERIC_BAD.toWebHexString()+";'>"
								+ "This is as far as I've got with the writing for the preview version! I ended up doing a couple of rewrites during the week, and didn't have time to get it all finished for Friday. ;_;</i>"
					+ "</p>"
					+ "<p>"
						+ "You give Alexa 2000 flames, and she puts a leash on Scarlett.</br>"
						+ "Scarlet starts being difficult, so ALexa tells you to go ahead to the Slavery Administration building.</br>"
						+ "You walk down the street to the slavery administration building.</br>"
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Slavery Administration", "You arrive at the Slavery Administration building.", ALEXAS_SHOP_BUYING_SCARLETT_FINAL) {
					@Override
					public void effects() {
						Main.game.getAlexa().setLocation(WorldType.SLAVER_ALLEY, SlaverAlley.SLAVERY_ADMINISTRATION);
						Main.game.getScarlett().setLocation(WorldType.SLAVER_ALLEY, SlaverAlley.SLAVERY_ADMINISTRATION);
						Main.game.setActiveWorld(Main.game.getWorlds().get(WorldType.SLAVER_ALLEY), SlaverAlley.SLAVERY_ADMINISTRATION, false);
						
						Main.game.getScarlett().setRelationshipAffection(Main.game.getAlexa(), AffectionLevel.NEGATIVE_FIVE_LOATHE.getMedianValue());
						Main.game.getScarlett().setObedience(ObedienceLevel.NEGATIVE_FOUR_DEFIANT.getMedianValue());
						Main.game.getScarlett().setRelationshipAffection(Main.game.getPlayer(), AffectionLevel.NEGATIVE_FIVE_LOATHE.getMedianValue());
						Main.game.getPlayer().addSlave(Main.game.getScarlett());
					}
				};

			}else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ALEXAS_SHOP_BUYING_SCARLETT_FINAL = new DialogueNodeOld("Slavery Administration", ".", true, true) { //TODO
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "Intro for Nikki (slavery administraion manager).</br>"
						+ "Alexa and Scarlett arrive, Scarlett walking on all fours.</br>"
						+ "Alexa's slave license is transferred to you.</br>"
						+ "Explain that you can buy collars; when used, one of their employees will come and collect the new slave (included in collar's price).</br>"
						+ "Slaves will be kept in holding here until you decide to have them relocated.</br>"
						+ "(Gain side-quest: Lilaya's empty house)"
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Where's Arthur?!", "Order Scarlett to tell you where Arthur is.", ALEXAS_SHOP_BUYING_SCARLETT_DECIDE_HER_FATE) {
					@Override
					public void effects() {
						Main.game.getAlexa().setLocation(WorldType.HARPY_NEST, HarpyNests.ALEXAS_NEST);
					}
					@Override
					public QuestLine getQuestLine() {
						return QuestLine.MAIN;
					}
				};
			}else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ALEXAS_SHOP_BUYING_SCARLETT_DECIDE_HER_FATE = new DialogueNodeOld("Slavery Administration", ".", true, true) { //TODO
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "Alexa leaves the building.</br>"
						+ "Scarlett is defiant, but breaks and tells you when the cat-boy overhears and mentions public use as a good way to break slaves.</br>"
						+ "Tells you where Arthur is now.</br>"
						+ "She begs to be released."
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			if (index == 1) {
				return new Response("Keep her", "Order Scarlett to tell you where Arthur is.", ALEXAS_SHOP_BUYING_SCARLETT_KEEP_HER);

			} else if (index == 2) {
				return new Response("Free her", "Order Scarlett to tell you where Arthur is.", ALEXAS_SHOP_BUYING_SCARLETT_FREE_HER) {
					@Override
					public void effects() {
						Main.game.getAlexa().setLocation(WorldType.HARPY_NEST, HarpyNests.ALEXAS_NEST);
						Main.game.getScarlett().setRelationshipAffection(Main.game.getAlexa(), AffectionLevel.NEGATIVE_FIVE_LOATHE.getMedianValue());
						Main.game.getScarlett().setObedience(ObedienceLevel.ZERO_FREE_WILLED.getMedianValue());
						Main.game.getScarlett().setRelationshipAffection(Main.game.getPlayer(), AffectionLevel.ZERO_NEUTRAL.getMedianValue());
						Main.game.getPlayer().removeSlave(Main.game.getScarlett());
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ALEXAS_SHOP_BUYING_SCARLETT_KEEP_HER = new DialogueNodeOld("Slavery Administration", ".", true, true) { //TODO
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "You keep her.</br>"
						+ " Nikki puts her in holding until you can sort out accommodation for her."
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			return SlaverAlleyDialogue.SLAVERY_ADMINISTRATION.getResponse(index);
		}
	};
	
	public static final DialogueNodeOld ALEXAS_SHOP_BUYING_SCARLETT_FREE_HER = new DialogueNodeOld("Slavery Administration", ".", true, true) { //TODO
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "You free her."
					+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			return SlaverAlleyDialogue.SLAVERY_ADMINISTRATION.getResponse(index);
		}
	};
	
	
	
	
	public static final DialogueNodeOld PLAYERS_SHOP_EXTERIOR = new DialogueNodeOld("Your shop", ".", false) { //TODO
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
					+ "You own this shop!"
				+ "</p>"
				+ "<p>"
					+ "<i>Slavery content will be added for next week!</i>"
				+ "</p>";
		}

		@Override
		public Response getResponse(int index) {
			return null;
		}
	};
}
