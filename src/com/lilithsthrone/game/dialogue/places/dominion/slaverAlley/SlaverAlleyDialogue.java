package com.lilithsthrone.game.dialogue.places.dominion.slaverAlley;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.lilithsthrone.game.character.body.valueEnums.Femininity;
import com.lilithsthrone.game.character.npc.NPC;
import com.lilithsthrone.game.character.quests.Quest;
import com.lilithsthrone.game.character.quests.QuestLine;
import com.lilithsthrone.game.dialogue.DialogueFlagValue;
import com.lilithsthrone.game.dialogue.DialogueNodeOld;
import com.lilithsthrone.game.dialogue.SlaveryManagementDialogue;
import com.lilithsthrone.game.dialogue.responses.Response;
import com.lilithsthrone.game.dialogue.responses.ResponseEffectsOnly;
import com.lilithsthrone.game.dialogue.responses.ResponseSex;
import com.lilithsthrone.game.dialogue.responses.ResponseTrade;
import com.lilithsthrone.game.dialogue.utils.UtilText;
import com.lilithsthrone.game.sex.SexPositionSlot;
import com.lilithsthrone.game.sex.managers.dominion.SMStocks;
import com.lilithsthrone.game.slavery.SlaveJobSetting;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.rendering.SVGImages;
import com.lilithsthrone.utils.Colour;
import com.lilithsthrone.utils.Util;
import com.lilithsthrone.utils.Util.Value;
import com.lilithsthrone.world.WorldType;
import com.lilithsthrone.world.places.PlaceType;

/**
 * @since 0.1.0
 * @version 0.2.2
 * @author Innoxia
 */
public class SlaverAlleyDialogue {

	public static final DialogueNodeOld OUTSIDE = new DialogueNodeOld("Slaver Alley", "-", false) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public int getMinutesPassed(){
			return 5;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "As you navigate through the labyrinthine passageways of Dominion's back-alleys, you start to hear the faint murmur of voices somewhere before you."
						+ " Proceeding with caution, you slowly step around the next corner, and find yourself looking into a small, well-kept courtyard."
					+ "</p>"
					+ "<p>"
						+ "Clean, grey cobblestones line the floor, and in the middle of the little square, surrounded by wooden benches, a bubbling stream of water cascades out of a finely-crafted stone fountain."
						+ " On three of its sides, multiple narrow entrances link this area to the surrounding alleyways, but it's what's on the fourth that draws your attention."
						+ " A huge, open gateway, flanked by a pair of muscular horse-boys, has been built into the wall, and it's through this opening that the sound of a busy marketplace can be heard."
					+ "</p>"
					+ "<p>"
						+ "As you step closer, neither of the guards react to your presence, and you notice that their attention is focused solely on who's trying to leave the area beyond."
						+ " Crossing the courtyard, you see that the words 'Slaver Alley' have been cast into the dull iron framing of the gate, and on the walls to either side,"
							+ " promotional posters for different vendors have been plastered over the red bricks."
					+ "</p>"
					+ "<p>"
						+ "It looks as though any member of the public is free to come and go as they please."
					+ "</p>";
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Slaver Alley", "Step through the gate and enter Slaver Alley."){
					@Override
					public void effects() {
						Main.mainController.moveGameWorld(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_ENTRANCE, true);
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld GATEWAY = new DialogueNodeOld("Gateway", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "You find yourself standing in the gateway that links Slaver Alley to the rest of Dominion."
						+ " On either side of you, horse-boy guards keep a watchful eye on anyone that passes through here; obviously on the lookout for runaway slaves."
					+ "</p>"
					+ "<p>"
						+ "A touristy-looking information board, looking quite out of place with its surroundings, has been fixed to a nearby wall, and upon reading it,"
							+ " you discover that a powerful arcane shield has been erected over the entire area, which prevents the effects of any on-going arcane storms from being felt by any of the marketplace's occupants."
						+ " True enough, as you glance up at the sky above, you see the faint pink crackle of the shield's arcane energy, reassuring you that you needn't worry about attacks from any horny customers."
					+ "</p>"
					+ "<p>"
						+ "There are only a couple of other pieces of information that are of any real relevance to you."
						+ " In large red writing at the top of the information board, you read that you must be in possession of a slaver license before being able to buy or sell slaves here."
						+ " Underneath, the sentence telling you that you can obtain a license from the Slaver Administration building is crossed out, and in scrawling writing, the words"
						+ " 'Slaver Licenses are not being issued' has been written beneath."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new ResponseEffectsOnly("Leave", "Step back out into Dominion's alleyways."){
					@Override
					public void effects() {
						Main.mainController.moveGameWorld(WorldType.DOMINION, PlaceType.DOMINION_SLAVER_ALLEY, true);
					}
				};

			}else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld ALLEYWAY = new DialogueNodeOld("Alleyway", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 2;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "You find yourself walking down one of Slaver Alley's numerous interconnected passageways."
						+ " Lined with clean, grey cobblestone, and being almost as wide as a normal street, they're quite unlike the regular sort of passages that make up the rest of Dominion's back-alleys.");
			
			if (Main.game.isDayTime()) {
				UtilText.nodeContentSB.append(
							" Colourful canvas awnings have been unfurled from the sides of the surrounding buildings, and, looking up through the gap in the middle, you see a faint pink crackle;"
									+ " evidence of the shield that protects Slaver Alley from arcane storms."
						+ "</p>");
			} else {
				UtilText.nodeContentSB.append(
							" A series of arcane-powered lights cast a flickering glow over the area, and as you look up at the night sky, you see a faint pink crackle;"
									+ " evidence of the shield that protects Slaver Alley from arcane storms."
						+ "</p>");
			}
			
			UtilText.nodeContentSB.append(
					"<p>"
						+ "Although not as busy as Dominion's main streets, there are still a significant amount of people wandering about,"
							+ " and you often have to push your way through small crowds that inexplicably decide to loiter right in the middle of the path."
						+ " The close proximity of the surrounding buildings echoes and amplifies the crowd's inane chatter, making Slaver Alley feel more alive than any other part of the city you've wandered through."
					+ "</p>"
					+ "<p>"
						+ "The most striking feature of this area is the manner of goods that are sold here."
						+ " Down each passageway, multiple wooden stands have been erected, and on top each one, slaves of all different races and sexes are being publicly displayed."
						+ " You see that information boards have been attached to each one of these stands, giving you instructions as to which shop you need to visit in order to negotiate the purchase of the wares on display."
					+ "</p>"
					+ "<p>"
						+ "Glancing up at the slaves as you pass, you notice that the one thing they all have in common is that they're all wearing metal collars of some sort or another."
						+ " Apart from that peculiar accessory, the vast majority of them are completely naked, although you do see a few that have been dressed in skimpy pieces of highly-revealing clothing."
					+ "</p>");

			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld MARKET_STALL = new DialogueNodeOld("Slaver's shop", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "Deciding that you'd like to take a look inside one of the shops, you approach the entrance to the nearest building."
						+ " In order to display yet more slaves to passing customers, you see that a series of large glass windows have been installed into the walls before you."
						+ " As you walk up to the entrance, some of the slaves standing behind these windows glance your way; no doubt wondering if you'll be their next owner."
					+ "</p>"
					+ "<p>"
						+ "Pushing open the door, you step inside the shop, and find that the interior is surprisingly clean, well-lit, and airy."
						+ " Yet more slaves line the walls around you, and in the middle of the room, the shop's owner cries out a greeting from behind a circular wooden desk."
					+ "</p>"
					+ "<p>"
						+ "After browsing the wares for a moment, you decide that you've had enough."
						+ " After all, without a permit, you're unable to buy anything."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return null;
		}
	};
	
	public static final DialogueNodeOld AUCTION_BLOCK = new DialogueNodeOld("Auctioning block", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return "<p>" // captured or slaves for public sale (seized assets)
						+ "As you walk towards Slaver Alley's central courtyard, the excited buzz of hundreds of voices grows louder and louder."
						+ " The source of this incessant din is hard to miss, and takes the form a huge, bustling crowd, which has surrounded a raised wooden platform."
						+ " Several noticeboards, elevated above the heads of the energetic mob by means of several-metre-high poles, declare this area to be for 'Public Auctions'."
					+ "</p>"
					+ "<p>"
						+ (Main.game.getPlayer().isHasSlaverLicense()
								?"Being in possession of a slaver's license, you wonder if you should approach the stage and participate in the next auction."
								:"The noticeboards also declare that you'd need a slaver license in order to participate in any auctions, so, not being in possession of one yourself, there'd be no point in approaching the stage.")
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				if(Main.game.getPlayer().isHasSlaverLicense()) {
					return new Response("Approach", "Approach the auction block.", AUCTION_BLOCK_LIST);
				} else {
					return new Response("Approach", "You don't have a slaver license, so you're unable to participate in any slave auctions.", null);
				}
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AUCTION_BLOCK_LIST = new DialogueNodeOld("Auctioning block", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append(
					"<p>" // captured or slaves for public sale (seized assets)
						+ "You make your way through the pressing crowds and take your place on the outskirts of the main bidding area."
						+ " The information boards in this section seem to carry quite a lot more detail than those on the outskirts of the courtyard, and, upon reading one, you discover a little more about the slaves that are on offer here."
					+ "</p>"
					+ "<p>"
						+ " According to the notice board, the vast majority of slaves are bought and sold by through the stores that scattered throughout slaver alley, but in the cases of a slave owner's assets being seized,"
							+ " or if a slave owner somehow ends up being enslaved themselves, all slaves that they owned are put up for public auction."
						+ " It also states that some slaves willingly sell themselves in order to pay off their debts, and offering themselves up at a public auction is apparently a popular choice for those individuals."
					+ "</p>"
					+ "<p>"
						+ "Attached to the bottom of each of the noticeboards is a list of the slaves that are up next for the public auction."
						+ " After reading through the list of names, you wonder if you should stick around to place a bid on any of them..."
					+ "</p>"
					+ "<p style='text-align:center;'>"
						+ "<b>Upcoming Public Auctions</b>"
						+ "<div class='container-full-width' style='margin-bottom:0; text-align:center;'>"
							+ "<div style='width:40%; float:left; font-weight:bold; margin:0; padding:0;'>"
								+ "Slave"
							+ "</div>"
							+ "<div style='float:left; width:17%; font-weight:bold; margin:0; padding:0; text-align:center;'>"
								+ "<b style='color:"+Colour.OBEDIENCE.toWebHexString()+";'>Obedience</b>"
							+"</div>"
							+ "<div style='float:left; width:17%; font-weight:bold; margin:0; padding:0; text-align:center;'>"
								+ "<b style='color:"+Colour.CURRENCY_GOLD.toWebHexString()+";'>Value</b>"
							+"</div>"
							+ "<div style='float:left; width:17%; font-weight:bold; margin:0; padding:0; text-align:center;'>"
								+ "<b style='color:"+Colour.CURRENCY_GOLD.toWebHexString()+";'>Starting Bid</b>"
							+"</div>"
							+ "<div style='float:left; width:9%; font-weight:bold; margin:0; padding:0; text-align:center;'>"
								+ "Bid"
							+ "</div>"
						+ "</div>");
			
			List<NPC> charactersPresent = new ArrayList<>(Main.game.getCharactersPresent());
			charactersPresent.removeIf((npc) -> Main.game.getPlayer().getCompanions().contains(npc));
			
			charactersPresent.sort(Comparator.comparing(NPC::getName));
			
			if(charactersPresent.isEmpty()) {
				UtilText.nodeContentSB.append(
						"<div class='container-full-width' style='margin-bottom:0; text-align:center;'>"
								+ "<b>There are no upcoming auctions...</b>"
						+ "</div>");
				
			} else {
				int i=0;
				for(NPC slave : charactersPresent){
					boolean alternateBackground = i%2==0;
					
					UtilText.nodeContentSB.append(UtilText.parse(slave,
							"<div class='container-full-width inner' style='margin-bottom:0;"+(alternateBackground?"background:#292929;'":"'")+"'>"
								+ "<div style='width:40%; float:left; margin:0; padding:0; text-align:center;'>"
									+ "<b style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>"+slave.getName()+"</b> - "
									+ "<span style='color:"+slave.getFemininity().getColour().toWebHexString()+";'>"+Util.capitaliseSentence(slave.getGender().getName())+"</span> "
									+ "<span style='color:"+slave.getRace().getColour().toWebHexString()+";'>"+Util.capitaliseSentence((slave.isFeminine()?slave.getSubspecies().getSingularFemaleName():slave.getSubspecies().getSingularMaleName()))+"</span>"
								+ "</div>"
								+ "<div style='float:left; width:17%; margin:0; padding:0; text-align:center;'>"
									+ "<b style='color:"+slave.getObedience().getColour().toWebHexString()+";'>"+slave.getObedienceValue()+ "</b>"
								+"</div>"
								+ "<div style='float:left; width:17%; margin:0; padding:0; text-align:center;'>"
									+ UtilText.formatAsMoney(slave.getValueAsSlave())
								+"</div>"
								+ "<div style='float:left; width:17%; margin:0; padding:0; text-align:center;'>"
									+ UtilText.formatAsMoney((int)(slave.getValueAsSlave()*0.5f))
								+"</div>"
								+ "<div style='float:left; width:9%; font-weight:bold; margin:0; padding:0;'>"
									+ "<div id='"+slave.getId()+"_BID' class='square-button solo'><div class='square-button-content'>"+SVGImages.SVG_IMAGE_PROVIDER.getTransactionBid()+"</div></div>"
								+ "</div>"
							+ "</div>"
							));
					i++;
				}
			}
			
			UtilText.nodeContentSB.append("</p>");
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Import", "View the character import screen.", AUCTION_IMPORT);
				
			} else if(index==0) {
				return new Response("Back", "Walk away from the auction block.", AUCTION_BLOCK);
				
			} else {
				return null;
			}
		}
	};
	
	public static void setupBidding(NPC slaveToBidOn) {
		biddingNPC = slaveToBidOn;
		biddingPrice = (int) (biddingNPC.getValueAsSlave()*0.5f);
		biddingRoundsTotal = Util.random.nextInt(3)+1;
		biddingRounds = 0;
		playerBidLeader = false;
		currentRivalBidder = SlaveAuctionBidder.generateNewSlaveAuctionBidder(biddingNPC);
	}
	
	public static final DialogueNodeOld AUCTION_IMPORT = new DialogueNodeOld("Auctioning block", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "";
		}
		
		@Override
		public String getHeaderContent(){
			StringBuilder saveLoadSB = new StringBuilder();

			saveLoadSB.append(
					"<p>"
						+ "You can import any of your previously exported characters in order to add them to the auctioning list."
						+ " (Export any character in the game by viewing their character sheet and pressing the little export button in the top-right of the screen.)"
					+ "</p>"
					+ "<p>"
						+ "<table align='center'>");
			
			Main.getSlavesForImport().sort(Comparator.comparingLong(File::lastModified).reversed());
			
			for(File f : Main.getSlavesForImport()){
				saveLoadSB.append(getImportRow(f.getName()));
			}
			
			saveLoadSB.append("</table>"
					+ "</p>"
					+ "<p id='hiddenPField' style='display:none;'></p>");
			
			return saveLoadSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			
			if(index==0) {
				return new Response("Back", "Return to the previous screen.", AUCTION_BLOCK_LIST);
				
			} else {
				return null;
			}
		}
	};
	
	private static NPC biddingNPC = null;
	private static int biddingPrice = 0;
	private static int biddingRounds = 0;
	private static int biddingRoundsTotal = 1;
	private static boolean playerBidLeader = false;
	private static SlaveAuctionBidder currentRivalBidder = null;
	
	public static final DialogueNodeOld AUCTION_BIDDING = new DialogueNodeOld("Auctioning block", ".", true) {
		private static final long serialVersionUID = 1L;
		
		@Override
		public boolean isContinuesDialogue() {
			return biddingRounds!=0;
		}
		
		@Override
		public String getContent() {
			if(biddingRounds==0) {
				return UtilText.parse(biddingNPC,
						"<p>"
							+ "Deciding that you'd like to try and bid on [npc.name], you hang around the area until [npc.she]'s up for auction."
							+ " Thankfully, you don't have to wait long, and soon enough you see the [npc.race], stark naked except for the slave collar around [npc.her] neck, being led up onto the platform."
						+ "</p>"
						+ "<p>"
							+ "Within moments, the bidding starts, and "+currentRivalBidder.getName(true)+" quickly matches the starting price, muttering, "
								+ UtilText.parseNPCSpeech(currentRivalBidder.getRandomBiddingComment(), (currentRivalBidder.getGender().isFeminine()?Femininity.FEMININE:Femininity.MASCULINE_STRONG))
						+ "</p>"
						+ "<p>"
							+ "<i>The current bid is "+UtilText.formatAsMoney(biddingPrice)+", which means that you'll need to bid "+UtilText.formatAsMoney(biddingPrice+100)+" to get in the lead for buying [npc.name].</i>"
						+ "</p>");
				
			} if(biddingRounds==biddingRoundsTotal) {
				if(playerBidLeader) {
					return UtilText.parse(biddingNPC,
							"<p>"
								+ "The "+currentRivalBidder.getName(false)+" backs out of the bidding, sighing, "
								+ UtilText.parseNPCSpeech(currentRivalBidder.getRandomFailedBiddingComment(), (currentRivalBidder.getGender().isFeminine()?Femininity.FEMININE:Femininity.MASCULINE_STRONG))
							+ "</p>"
							+ "<p>"
								+ "Nobody else in the crowd offers another bid, leaving the auctioneer to call out,"
								+ " [maleNPC.speech(Going once... Going twice... Sold! To the [pc.race] at the back!)]"
							+ "</p>"
							+ "<p>"
								+ "Walking towards the stage, you pay the auctioneer's assistant the amount that you bid, totalling "+UtilText.formatAsMoney(biddingPrice)+"."
								+ " She informs you that your new slave will be ready for collection from the Slavery Administration building, before handing over the paperwork which proves your ownership of [npc.name]."
							+ "</p>");
				} else {
					return UtilText.parse(biddingNPC,
							"<p>"
								+ "You back out of the bidding, which allows the "+currentRivalBidder.getName(false)+" you were competing with to win the auction."
							+ "</p>"
							+ "<p>"
								+ "Nobody else in the crowd offers another bid, leaving the auctioneer to call out,"
								+ " [maleNPC.speech(Going once... Going twice... Sold! To the "+currentRivalBidder.getName(false)+" near the back!)]"
							+ "</p>"
							+"<p>"
								+ "As the "+currentRivalBidder.getName(false)+" walks towards the stage to finalise their purchase of [npc.name], you hear them mutter, "
								+ UtilText.parseNPCSpeech(currentRivalBidder.getRandomSuccessfulBiddingComment(), (currentRivalBidder.getGender().isFeminine()?Femininity.FEMININE:Femininity.MASCULINE_STRONG))
							+ "</p>");
				}
				
			} else {
				return UtilText.parse(biddingNPC,
						"<p>"
							+ "The "+currentRivalBidder.getName(false)+" continues to bid against someone else, taking [npc.name]'s asking price up to "+UtilText.formatAsMoney(biddingPrice)+"."
						+ "</p>"
						+ "<p>"
							+ "<i>The current bid is "+UtilText.formatAsMoney(biddingPrice)+", which means that you'll need to bid "+UtilText.formatAsMoney(biddingPrice+100)+" to get in the lead for buying [npc.name].</i>"
						+ "</p>");
			}
		}
		
		@Override
		public Response getResponse(int responseTab, int index) {
			if(biddingRounds==biddingRoundsTotal) {
				if(index==1) {
					if(playerBidLeader) {
						return new Response("Continue", UtilText.parse(biddingNPC, "You won the bidding! [npc.Name] is now ready for collection from Slavery Administration."), AUCTION_BLOCK) {
							@Override
							public void effects() {
							}
						};
					} else {
						return new Response(UtilText.parse(biddingNPC, "[npc.Name] sold"), "You didn't win the auction, but there's always next time, right?", AUCTION_BLOCK) {
							@Override
							public void effects() {
								Main.game.getFinch().removeSlave(biddingNPC);
								Main.game.banishNPC(biddingNPC);
							}
						};
					}
					
				} else {
					return null;
				}
			
			} else {
				if(index==1) {
					if(Main.game.getPlayer().getMoney()>=biddingPrice+100) {
						return new Response("Bid "+UtilText.formatAsMoney(biddingPrice+100), UtilText.parse(biddingNPC, "Place a bid of "+(biddingPrice+100)+" flames for [npc.name]."), AUCTION_BIDDING) {
							@Override
							public void effects() {
								biddingPrice += 100;
								playerBidLeader = true;
								increaseBid();
								if(biddingRounds==biddingRoundsTotal) {
									Main.game.getPlayer().addSlave(biddingNPC);
									biddingNPC.setLocation(WorldType.SLAVER_ALLEY, PlaceType.SLAVER_ALLEY_SLAVERY_ADMINISTRATION, true);
									Main.game.getPlayer().incrementMoney(-biddingPrice);
								}
							}
						};
					} else {
						return new Response("Bid "+UtilText.formatAsMoneyUncoloured(biddingPrice+100, "span"), "You can't afford a bid of "+(biddingPrice+100)+" flames, so you'll have to let this slave go to someone else.", null);
					}
					
				} else if(index==2) {
					return new Response("Stop bidding", UtilText.parse(biddingNPC, "Stop bidding, which will allow someone else to buy [npc.name]."), AUCTION_BIDDING) {
						@Override
						public void effects() {
							playerBidLeader = false;
							biddingRounds=biddingRoundsTotal;
						}
					};
					
				} else {
					return null;
				}
			}
		}
	};
	
	private static void increaseBid() {
		biddingRounds++;
		if(biddingRounds!=biddingRoundsTotal) {
			biddingPrice = (int) (biddingPrice * (1+(0.8f*Math.random())));
			playerBidLeader = false;
		}
	}
	
	

	private static String getImportRow(String name) {
		String baseName = name.substring(0, name.lastIndexOf('.'));
		return "<tr>"
				+ "<td style='min-width:200px;'>"
					+ baseName
				+ "</td>"
				+ "<td>"
					+ "<div class='saveLoadButton' id='import_slave_" + baseName + "' style='color:"+Colour.GENERIC_GOOD.toWebHexString()+";'>Import</div>"
				+ "</td>"
				+ "</tr>";
	}
	
	public static final DialogueNodeOld PUBLIC_STOCKS = new DialogueNodeOld("Public Stocks", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			UtilText.nodeContentSB.setLength(0);
			
			UtilText.nodeContentSB.append("<p>"
						+ "The collection of twenty-or-so public stocks, positioned right in the middle of a wide courtyard, is the first thing anyone sees when entering Slaver Alley."
						+ " Used as a means of punishment for disobedient slaves, each one of the devices consists of a wooden frame, with holes for securing the occupant's arms and head."
						+ " A small sign positioned next to each one informs members of the public as to what kinds of use have been permitted by the slave's owner."
					+ "</p>"
					+ "<p>"
						+ "About half of the stocks in front of you are currently occupied, with most of the slaves on offer already being used by members of the public."
						+ " As you walk past, you see that a few of the occupants are currently available..."
					+ "</p>");

			List<String> sexAvailability = new ArrayList<>();
			

			List<NPC> charactersPresent = new ArrayList<>(Main.game.getCharactersPresent());
			charactersPresent.removeIf((npc) -> Main.game.getPlayer().getCompanions().contains(npc));
			
			for(NPC npc : charactersPresent) {
				UtilText.nodeContentSB.append(UtilText.parse(npc, 
						"<p>"
							+ "[npc.Name]," + (npc.getOwner().isPlayer()?" <b style=color:"+Colour.GENERIC_ARCANE.toWebHexString()+";>who is your slave</b>, and is":"")
								+ " <span style='color:"+npc.getGender().getColour().toWebHexString()+";'>[npc.a_gender]</span> <span style='color:"+npc.getRace().getColour().toWebHexString()+";'>[npc.race]</span>, has been marked as available for"));
				
				sexAvailability.clear();
				if(npc.getSlaveJobSettings().contains(SlaveJobSetting.SEX_ORAL)) {
					sexAvailability.add(" <b style='color:"+Colour.BASE_PINK_LIGHT.toWebHexString()+";'>oral</b>");
				}
				if(npc.getSlaveJobSettings().contains(SlaveJobSetting.SEX_VAGINAL)) {
					sexAvailability.add(" <b style='color:"+Colour.BASE_PINK.toWebHexString()+";'>vaginal</b>");
				}
				if(npc.getSlaveJobSettings().contains(SlaveJobSetting.SEX_ANAL)) {
					sexAvailability.add(" <b style='color:"+Colour.BASE_PINK_DEEP.toWebHexString()+";'>anal</b>");
				}
				
				if(!sexAvailability.isEmpty()) {
					UtilText.nodeContentSB.append(
							Util.stringsToStringList(sexAvailability, false)
							+" use.</p>");
				} else {
					UtilText.nodeContentSB.append(
							" receiving oral only.</p>");
				}
			}
			
			return UtilText.nodeContentSB.toString();
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			List<NPC> charactersPresent = Main.game.getCharactersPresent();
			
			if(index==0) {
				return new Response("Complain", "You don't like the idea of slaves being publicly used. There appears to be an enforcer watching over the area, so perhaps you should go and complain to him... (Not yet implemented!)", null);
				
			} else if(index <= charactersPresent.size()) {
				return new ResponseSex(
						"Use "+charactersPresent.get(index-1).getName(),
						UtilText.parse(charactersPresent.get(index-1), "Walk up to [npc.name] and have some fun..."),
						false, false,
						new SMStocks(
								charactersPresent.get(index-1).getSlaveJobSettings().contains(SlaveJobSetting.SEX_VAGINAL),
								charactersPresent.get(index-1).getSlaveJobSettings().contains(SlaveJobSetting.SEX_ANAL),
								charactersPresent.get(index-1).getSlaveJobSettings().contains(SlaveJobSetting.SEX_ORAL),
								Util.newHashMapOfValues(new Value<>(Main.game.getPlayer(), SexPositionSlot.STOCKS_FUCKING)),
								Util.newHashMapOfValues(new Value<>(charactersPresent.get(index-1), SexPositionSlot.STOCKS_LOCKED_IN_STOCKS))),
						AFTER_STOCKS_SEX,
						"<p>"
							+ "Deciding that you'd like to have some fun with the [npc.race] in the stocks nearest to you, you walk up behind [npc.herHim]."
							+ " [npc.She] lets out a little [npc.moan] as [npc.she] hears you, and shifts [npc.her] [npc.hips+] in anticipation of what's about to happen..."
						+ "</p>") {
					@Override
					public void effects() {
						Main.game.setActiveNPC(charactersPresent.get(index-1));
					}
				};
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld AFTER_STOCKS_SEX = new DialogueNodeOld("Public Stocks", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "Having had your fun with [npc.name], you step back, grinning as you hear [npc.herHim] let out [npc.a_moan+]."
						+ " A few people had stopped to watch you using the helpless [npc.race], and some of them compliment you on your performance before moving forwards to have a turn themselves..."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(index==1) {
				return new Response("Continue", "Continue on your way.", PUBLIC_STOCKS);
			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld SLAVERY_ADMINISTRATION_EXTERIOR = new DialogueNodeOld("Slavery Administration", ".", false) {
		private static final long serialVersionUID = 1L;

		@Override
		public int getMinutesPassed() {
			return 1;
		}

		@Override
		public String getContent() {
			return "<p>"
						+ "A large metal sign, bearing the words 'Slavery Administration', is prominently displayed above the entrance to the building before you."
						+ " Its clean, white-washed walls and heavy oak doors set this particular establishment apart from the rest of the stores in Slaver Alley."
					+ "</p>"
					+ "<p>"
						+ "Noticing a little information board attached to one side of the entrance, you walk up and read what it says."
						+ " From what you can gather, it appears as though this is an official government building, in which all matters relating to the ownership of slaves, licenses, and businesses dealing in slave-trading are handled."
						+ " A little piece of paper has been stuck to the bottom of the board, which reads; 'Slaver Licenses are not being issued!'"
					+ "</p>"
					+ (Main.game.getPlayer().isHasSlaverLicense()
						?"<p>"
							+ "Being in possession of a slaver license yourself, you could enter the Slavery Administration building and make use of its services."
						+ "</p>"
						:"<p>"
							+ "Although it's doubtful that you'd be able to make use of any of its services, it looks like any member of the public is free to enter the building."
						+ "</p>");
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Enter", "Step inside the 'Slavery Administration' building.", SLAVERY_ADMINISTRATION);

			} else {
				return null;
			}
		}
	};
	
	private static int slaverLicenseCost = 5000;
	
	public static final DialogueNodeOld SLAVERY_ADMINISTRATION = new DialogueNodeOld("Slavery Administration", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			if(Main.game.getPlayer().isHasSlaverLicense()) {
				return "<p>"
						+ "The heavy oak doors of the 'Slavery Administration' building are wide open, and, stepping through the inviting entranceway, you find yourself standing in a cavernous entrance hall."
						+ " Rows of marble pillars line the edges of the room, with little wooden benches interspersed between them."
						+ " Two doors are set into the wall opposite you, with signs labelling them as 'Holding pens' and 'Offices'."
						+ " The only other piece of furniture to be seen is a long, black-marble desk, behind which the black-haired cat-boy, [finch.name], is grinning at you with mischievous eyes."
					+ "</p>"
					+ "<p>"
						+ "[finch.speech(Ah, if it isn't my <i>favourite</i> customer, [pc.name]!)]"
						+ " he shouts, beckoning you over to the desk,"
						+ " [finch.speech(What can I help you with today?)]"
					+ "</p>"
					+ "<p>"
						+ "Walking forwards, you return [finch.name]'s greeting,"
						+ " [pc.speech(Hi [finch.name].)]"
					+ "</p>"
					+ "<p>"
						+ "[finch.speech(As an owner of a slaver license, I'm pleased to offer you my services,)]"
						+ " he says, standing up to reveal his tiny feline cock,"
						+ " [finch.speech(I've got slave collars, with the appropriate paperwork already completed, as well as a large selection of clothing suitable for your slaves!)]"
					+ "</p>"
					+ "<p>"
						+ "As you come to a halt in front of the black marble desk, [finch.name] grins expectantly at you,"
						+ " [finch.speech(Or, if you'd like, we can discuss the management and handling of your property."
							+ " Remember, any new slaves you capture will be delivered here first."
							+ " All the costs of collection and transport are covered in the cost of each slave collar, so just let me know if you want to have your slaves moved elsewhere.)]"
					+ "</p>"
					+ "<p>"
						+ "Thanking [finch.name], you wonder what you do next..."
					+ "</p>";
				
			} else {
				if(!Main.game.getDialogueFlags().values.contains(DialogueFlagValue.finchIntroduced)) {
					return "<p>"
								+ "The heavy oak doors of the 'Slavery Administration' building are wide open, and, stepping through the inviting entranceway, you find yourself standing in a large, cavernous entrance hall."
								+ " Rows of marble pillars line the edges of the room, with little wooden benches interspersed between them."
								+ " Two doors are set into the wall opposite you, with signs labelling them as 'Holding pens' and 'Offices'."
								+ " The only other piece of furniture to be seen is a long, black-marble desk, behind which a black-haired cat-boy is grinning at you with mischievous eyes."
							+ "</p>"
							+ "<p>"
								+ "[finch.speech(Welcome!)]"
								+ " he shouts, beckoning you over to the desk,"
								+ " [finch.speech(Can I help you with anything?)]"
							+ "</p>"
							+ "<p>"
								+ "Walking forwards, you return the cat-boy's greeting,"
								+ " [pc.speech(Hello, I was just looking around.)]"
							+ "</p>"
							+ "<p>"
								+ "[finch.speech(I'm afraid that there's not really much to see here."
									+ " All the <i>fun</i> happens in the holding cells, and they're off-limits."
									+ " Unless you've got a slaver license, there's really not much I can offer you, except for a good day!)]"
								+ " he says, standing up and bowing a little,"
								+ " [finch.speech(Oh, where are my manners?! I'm [finch.name], the manager of the Slavery Administration."
									+ " I keep petitioning my superiors to have the name changed to something a little more <i>exciting</i>, but they're quite set in their ways.)]"
							+ "</p>"
							+ "<p>"
								+ "[pc.speech(I'm [pc.name],)]"
								+ " you answer, returning the cat-boy's disarming smile."
								+ " As [finch.name] continues grinning at you, your eyes are drawn down to his groin, where you see that his choice of crotchless chaps and briefs have left his tiny cat-like cock completely exposed."
							+ "</p>"
							+ "<p>"
								+ "Noticing your downwards glance, [finch.name] lets out a little laugh, and you can't help but notice that there's a slightly unsettling edge to his voice,"
								+ " [finch.speech(I like having easy access! You never know when a disobedient slave needs to be put in their place...)]"
							+ "</p>"
							+ "<p>"
								+ "Due to your lack of a slaver license, [finch.name] is unable to offer you any services."
								+ " As he sits back down, concealing his exposed groin behind the desk once more, you wonder if you should ask him about how to obtain one."
							+ "</p>";
				} else {
					return "<p>"
							+ "The heavy oak doors of the 'Slavery Administration' building are wide open, and, stepping through the inviting entranceway, you find yourself standing in a large, cavernous entrance hall."
							+ " Rows of marble pillars line the edges of the room, with little wooden benches interspersed between them."
							+ " Two doors are set into the wall opposite you, with signs labelling them as 'Holding pens' and 'Offices'."
							+ " The only other piece of furniture to be seen is a long, black-marble desk, behind which the black-haired cat-boy, [finch.name], is grinning at you with mischievous eyes."
						+ "</p>"
						+ "<p>"
							+ "[finch.speech(Hello again!)]"
							+ " he shouts, beckoning you over to the desk,"
							+ " [finch.speech(Can I help you with anything?)]"
						+ "</p>"
						+ "<p>"
							+ "Walking forwards, you return [finch.name]'s greeting,"
							+ " [pc.speech(Hello, I was just looking around.)]"
						+ "</p>"
						+ "<p>"
						+ "[finch.speech(I'm afraid that there's not really much to see here."
							+ " All the <i>fun</i> happens in the holding cells, and they're off-limits. Unless you've got a slaver license, there's really not much I can offer you, except for a good day!)]"
							+ " he says, standing up to reveal his tiny feline cock."
						+ "</p>"
						+ "<p>"
							+ "Due to your lack of a slaver license, [finch.name] is unable to offer you any services."
							+ " Throwing a grin your way, he sits back down, concealing his exposed groin behind the desk once more."
						+ "</p>";
				}
			}
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if(Main.game.getPlayer().isHasSlaverLicense()) {
				if (index == 1) {
					return new ResponseTrade("Trade", "Buy slavery-related items.", Main.game.getFinch());

				} else if (index == 5) {
					return new Response("Slave Manager", "Open the slave management screen.", SlaveryManagementDialogue.getSlaveryOverviewDialogue());

				} else if (index == 0) {
					return new Response("Leave", "Step back outside.", SLAVERY_ADMINISTRATION_EXTERIOR);

				} else {
					return null;
				}
			} else {
				if (index == 1) {
					if(!Main.game.getPlayer().hasQuest(QuestLine.SIDE_SLAVERY)) {
						return new Response("Slaver license", "Ask Finch about obtaining a slaver license.", SLAVERY_ADMINISTRATION_ASK_ABOUT_SLAVER_LICENSE) {
							@Override
							public void effects() {
								Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().startQuest(QuestLine.SIDE_SLAVERY));
								Main.game.getDialogueFlags().values.add(DialogueFlagValue.finchIntroduced);
							}
						};
						
					} else if(Main.game.getPlayer().getQuest(QuestLine.SIDE_SLAVERY) == Quest.SIDE_SLAVER_RECOMMENDATION_OBTAINED) {
						if(Main.game.getPlayer().getMoney() >= slaverLicenseCost) {
							return new Response("Present letter (<span style='color:" + Colour.CURRENCY_GOLD.toWebHexString() + ";'>" + UtilText.getCurrencySymbol() + "</span> "+slaverLicenseCost+")",
									"Show Finch the letter of recommendation you obtained from Lilaya, and then pay "+slaverLicenseCost+" flames to obtain a slaver license.", SLAVERY_ADMINISTRATION_SLAVER_LICENSE_OBTAINED) {
								@Override
								public void effects() {
									Main.game.getPlayer().incrementMoney(-slaverLicenseCost);
								}
							};
						} else {
							return new Response("Present letter (" + UtilText.getCurrencySymbol() + " "+slaverLicenseCost+")", "You don't have enough money to buy a slaver license! You need at least "+slaverLicenseCost+" flames.", null);
						}
						
					} else {
						return new Response("Present letter (" + UtilText.getCurrencySymbol() + " "+slaverLicenseCost+")", "You need to obtain a letter of recommendation from Lilaya first!", null);
						
					}
					
				} else if (index == 0) {
					return new Response("Leave", "Step back outside.", SLAVERY_ADMINISTRATION_EXTERIOR) {
						@Override
						public void effects() {
							Main.game.getDialogueFlags().values.add(DialogueFlagValue.finchIntroduced);
						}
					};

				} else {
					return null;
				}
			}
		}
	};
	
	public static final DialogueNodeOld SLAVERY_ADMINISTRATION_ASK_ABOUT_SLAVER_LICENSE = new DialogueNodeOld("Slavery Administration", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "Walking up to [finch.name]'s desk, you ask,"
						+ " [pc.speech(How do I get a slaver license? Is there some kind of form I need to fill out?)]"
					+ "</p>"
					+ "<p>"
						+ "[finch.Name] leans back in his chair, grinning up at you,"
						+ " [finch.speech(Yeah, there's a form to fill out, <i>and</i> a fee of five-thousand flames to pay, but slaver licenses aren't handed out to just anyone."
							+ " If you're looking to apply for one, you're going to have to join the waiting list."
							+ " Last time I looked, I think the estimated wait time for new applicants is just over four years...)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Four years?!)]"
						+ " you exclaim in disbelief,"
						+ " [pc.speech(Is there no other way to get one?)]"
					+ "</p>"
					+ "<p>"
						+ "[finch.speech(Well, unless you can get a letter of recommendation from someone who's already got a slaver license, then no, there's no other way."
							+ " And if you had that, you wouldn't be asking, would you?)]"
					+ "</p>"
					+ "<p>"
						+ "[finch.Name] grins up at you, and you can't help but notice the slightly threatening look in his [finch.eyeColour] cat-like eyes."
						+ " You thank him for the information and step away from the desk, determined to go and ask for a letter of recommendation from Lilaya, the one person you know that can help you out."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return SLAVERY_ADMINISTRATION.getResponse(0, index);
		}
	};
	
	public static final DialogueNodeOld SLAVERY_ADMINISTRATION_SLAVER_LICENSE_OBTAINED = new DialogueNodeOld("Slavery Administration", ".", true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "Walking up to [finch.name]'s desk, you place the letter of recommendation down in front of him,"
						+ " [pc.speech(I got a letter of recommendation from my aunt, so can I get that license now?)]"
					+ "</p>"
					+ "<p>"
						+ "[finch.speech(Hah! It's not quite as easy as that, this just means that you're on the priority list, not that-)]"
						+ " [finch.name] starts explaining, but as he reads the signature at the bottom of your letter, he stops talking."
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Is something wrong?)]"
						+ " you ask, noticing that all the colour has drained from his face."
					+ "</p>"
					+ "<p>"
						+ "[finch.speech(Your aunt is <i>Lilaya</i>?)]"
						+ " he asks, putting the letter to one side,"
						+ " [finch.speech(why didn't you say so earlier?! If you've got the five-thousand flame fee, I'll process your license right now!)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Oh, great!)]"
						+ " you cheerily reply, handing over the money,"
						+ " [pc.speech(I guess you know Lilaya then?)]"
					+ "</p>"
					+ "<p>"
						+ "[finch.Name] pulls out a large stack of forms from under his desk, and as he starts filling them out, he replies,"
						+ " [finch.speech(Of course! Everyone's heard of Lilaya. After all, she's the only half-demon to ever have been recognised by her Lilin mother, right?"
							+ " Anyway, it will take me about fifteen minutes to fill out these forms, so you can wait on one of the benches if you'd like.)]"
					+ "</p>"
					+ "<p>"
						+ "Thankful for Lilaya's apparent semi-celebrity status, you walk over to one of the wooden benches at the edge of the room and take a seat."
						+ " True to his word, [finch.name] takes just under fifteen minutes to work his way through the pile of forms, and once he's finished, he calls you back over to his desk."
					+ "</p>"
					+ "<p>"
						+ "[finch.speech(Right, I just need your name and signature here, here, and here,)]"
						+ " he says, pointing to three different places on the forms,"
						+ " [finch.speech(then I'll talk you through the rules and regulations.)]"
					+ "</p>"
					+ "<p>"
						+ "Quickly skimming over the forms to make sure that you're not signing your life away by mistake, you then sign in the places that [finch.name] points to."
						+ " Once you're done, [finch.name] files the papers away beneath his desk, before producing a little checklist, titled 'Slavery rules & regulations'."
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			if (index == 1) {
				return new Response("Rules", "Allow [finch.name] to explain the rules to you.", SLAVERY_ADMINISTRATION_SLAVER_LICENSE_OBTAINED_RULES) {
					@Override
					public void effects() {
						Main.game.getTextEndStringBuilder().append(Main.game.getPlayer().setQuestProgress(QuestLine.SIDE_SLAVERY, Quest.SIDE_UTIL_COMPLETE));
					}
				};

			} else {
				return null;
			}
		}
	};
	
	public static final DialogueNodeOld SLAVERY_ADMINISTRATION_SLAVER_LICENSE_OBTAINED_RULES = new DialogueNodeOld("Slavery Administration", ".", true, true) {
		private static final long serialVersionUID = 1L;

		@Override
		public String getContent() {
			return "<p>"
						+ "[finch.speech(So, before you run off and try to enslave the first person you meet, you need to be aware of the basics,)]"
							+ " [finch.name] states, pointing down at the checklist in front of him,"
						+ " [finch.speech(First off, all slaves are meant to wear an enchanted slave collar, which can only be purchased from here at the Slavery Administration building."
							+ " Although some slave owners allow their slaves to work without wearing one, we strongly advise against this."
							+ " We're unable to track down any runaway slaves who don't have their collars on, so if you decide to take theirs off, don't come crying to me when they run away!)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(Alright,)] you reply, nodding for Finch to continue."
					+ "</p>"
					+ "<p>"
						+ "[finch.speech(Now, you won't need to worry about this if you're only planning on buying and selling slaves, but if you're hoping to enslave people, then there are some *strict* limitations."
							+ " You can only enslave two sorts of people; those that are willing, and those that have broken the law."
							+ " Slave collars have a special enchantment in them that detects if the wearer falls into either one of those categories, so don't try and run around making just anyone into your slave!)]" 
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(That makes sense,)] you reply."
					+ "</p>"
					+ "<p>"
						+ "[finch.speech(Good. Now, assuming you're interested in enslaving people, the last thing you need to be aware of is what happens once you clasp a collar around a new slave's neck."
							+ " As soon as it clips into place, and provided that the new wearer is willing to be enslaved or is wanted by the Enforcers, the collar's special enchantment will activate,)]"
						+ " [finch.name] says, grinning,"
						+ " [finch.speech(and they'll be teleported right into the holding cells in this very building!"
							+ " Now, normally this kind of spell is limited to only the most powerful of arcane users, so you might be shocked the first time you see it!)]"
					+ "</p>"
					+ "<p>"
						+ "[pc.speech(So I have to come here and pick up everyone I enslave?)] you ask."
					+ "</p>"
					+ "<p>"
						+ "[finch.speech(Exactly! We had to add this enchantment to the collars a few years ago to counteract some nasty illegal enslavement practices."
							+ " With this new system, we're making sure that all slaves go through the proper channels.)]"
					+ "</p>"
					+ "<p>"
						+ "[finch.Name] picks up the last piece of paper you signed and holds it out for you to take,"
						+ " [finch.speech(That's all there really is to it. With this license, you're able to buy and sell slaves in any manner you like."
							+ " The only restriction is on enslavement, which, as I said, can only be done to those who are willing, or those who are wanted by the Enforcers.)]"
					+ "</p>"
					+ "<p>"
						+ "Thanking him for explaining things to you, you take your new slaver license from [finch.name], who has some final words,"
						+ " [finch.speech(Now that you've got a license, I can sell you some slave collars if you'd like. I've also got a few more fun things in stock...)]"
					+ "</p>";
		}

		@Override
		public Response getResponse(int responseTab, int index) {
			return SLAVERY_ADMINISTRATION.getResponse(0, index);
		}
	};
	
}
