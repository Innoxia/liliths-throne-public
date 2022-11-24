package com.lilithsthrone.game.sex;

import com.lilithsthrone.game.character.GameCharacter;
import com.lilithsthrone.game.character.body.valueEnums.BodyMaterial;
import com.lilithsthrone.game.character.effects.StatusEffect;
import com.lilithsthrone.game.dialogue.utils.UtilText;

/**
 * @since 0.1.65
 * @version 0.4
 * @author Innoxia
 */
public enum PregnancyDescriptor {
	
	ALREADY_PREGNANT {
		@Override
		public String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum, boolean directSexInsemination) {
			boolean isSlime = characterBeingImpregnated.getBodyMaterial()==BodyMaterial.SLIME;
			boolean selfcest = characterBeingImpregnated.equals(characterProvidingCum);
			boolean cumInPussy = isCumInPussy(characterBeingImpregnated, characterProvidingCum);
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("<p class='centre noPad'>");
			if(characterBeingImpregnated.isPlayer() && cumInPussy){
				if(characterProvidingCum==null) {
					sb.append("You feel cum ");
					if(isSlime) {
						sb.append("dispersing through your slimy body, seeking to impregnate your core");
					} else {
						sb.append("deep in your [pc.pussy+]");
					}
					sb.append(", but because [style.boldSex(you're already pregnant, you don't have to worry about it)]!");
					
				} else {
					sb.append("You feel ");
					if(selfcest) {
						sb.append("your own [npc.cum+]");
					} else {
						sb.append("[npc2.namePos] [npc2.cum+]");
					}
					if(isSlime) {
						sb.append(" dispersing through your slimy body, seeking to impregnate your core");
					} else {
						sb.append(" deep in your [pc.pussy+]");
					}
					sb.append(", but because [style.boldSex(you're already pregnant, you don't have to worry about it)]!");
				}
				
			} else {
				if(characterProvidingCum==null) {
					sb.append("[npc.NameIsFull] already pregnant, [style.boldSex(so there's no chance that ");
					if(selfcest) {
						sb.append("[npc.sheHasFull] knocked [npc.herself] up");
					} else {
						sb.append("[npc.sheHasFull] been impregnated");
					}
					sb.append(")]!");
					
				} else {
					sb.append("[npc.NameIsFull] already pregnant, [style.boldSex(so there's no chance that ");
					if(directSexInsemination) {
						if(selfcest) {
							sb.append("[npc.sheHasFull] knocked [npc.herself] up");
						} else {
							sb.append("[npc2.nameHas] knocked [npc.herHim] up");
						}
					} else {
						if(selfcest) {
							sb.append("[npc.sheHasFull] impregnated [npc.herself]");
						} else {
							sb.append("[npc2.nameHas] impregnated [npc.herHim]");
						}
					}
					sb.append(")]!");
				}
			}
			sb.append("</p>");
			
			if(characterProvidingCum!=null) {
				return UtilText.parse(characterBeingImpregnated, characterProvidingCum, sb.toString());
			} else {
				return UtilText.parse(characterBeingImpregnated, sb.toString());
			}
		}
	},

	ALREADY_PREGNANT_EGGS {
		@Override
		public String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum, boolean directSexInsemination) {
			boolean isSlime = characterBeingImpregnated.getBodyMaterial()==BodyMaterial.SLIME;
			boolean selfcest = characterBeingImpregnated.equals(characterProvidingCum);
			boolean cumInPussy = isCumInPussy(characterBeingImpregnated, characterProvidingCum);
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("<p class='centre noPad'>");
			if(characterBeingImpregnated.isPlayer() && cumInPussy){
				if(characterProvidingCum==null) {
					sb.append("You feel cum ");
					if(isSlime) {
						sb.append("dispersing through your slimy body, seeking to impregnate your core");
					} else {
						sb.append("deep in your [pc.pussy+]");
					}
					sb.append(", but because [style.boldSex(your womb is already filled with eggs, you don't have to worry about it)]!");
					
				} else {
					sb.append("You feel ");
					if(selfcest) {
						sb.append("your own [npc.cum+]");
					} else {
						sb.append("[npc2.namePos] [npc2.cum+]");
					}
					if(isSlime) {
						sb.append(" dispersing through your slimy body, seeking to impregnate your core");
					} else {
						sb.append(" deep in your [pc.pussy+]");
					}
					sb.append(", but because [style.boldSex(your womb is already filled with eggs, you don't have to worry about it)]!");
				}
				
			} else {
				if(characterProvidingCum==null) {
					sb.append("[npc.NamePos] womb is already filled with eggs, [style.boldSex(so there's no chance that ");
					if(selfcest) {
						sb.append("[npc.sheHasFull] knocked [npc.herself] up");
					} else {
						sb.append("[npc.sheHasFull] been impregnated");
					}
					sb.append(")]!");
					
				} else {
					sb.append("[npc.NamePos] womb is already filled with eggs, [style.boldSex(so there's no chance that ");
					if(directSexInsemination) {
						if(selfcest) {
							sb.append("[npc.sheHasFull] knocked [npc.herself] up");
						} else {
							sb.append("[npc2.nameHas] knocked [npc.herHim] up");
						}
					} else {
						if(selfcest) {
							sb.append("[npc.sheHasFull] impregnated [npc.herself]");
						} else {
							sb.append("[npc2.nameHas] impregnated [npc.herHim]");
						}
					}
					sb.append(")]!");
				}
			}
			sb.append("</p>");
			
			if(characterProvidingCum!=null) {
				return UtilText.parse(characterBeingImpregnated, characterProvidingCum, sb.toString());
			} else {
				return UtilText.parse(characterBeingImpregnated, sb.toString());
			}
		}
	},
	
	NO_CHANCE {
		@Override
		public String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum, boolean directSexInsemination) {
			boolean isSlime = characterBeingImpregnated.getBodyMaterial()==BodyMaterial.SLIME;
			boolean selfcest = characterBeingImpregnated.equals(characterProvidingCum);
			boolean cumInPussy = isCumInPussy(characterBeingImpregnated, characterProvidingCum);
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("<p class='centre noPad'>");
			if(!cumInPussy) {
				sb.append("Although [npc.namePos] womb is free, [style.boldSex(there's no chance that ");
				if(characterProvidingCum==null) {
					if(selfcest) {
						sb.append("[npc.sheHasFull] knocked [npc.herself] up");
					} else {
						sb.append("[npc.sheHasFull] been impregnated");
					}
					sb.append(")]!");
					
				} else {
					if(directSexInsemination) {
						if(selfcest) {
							sb.append("[npc.sheHasFull] knocked [npc.herself] up");
						} else {
							sb.append("[npc2.nameHas] knocked [npc.herHim] up");
						}
					} else {
						if(selfcest) {
							sb.append("[npc.sheHasFull] impregnated [npc.herself]");
						} else {
							sb.append("[npc2.nameHas] impregnated [npc.herHim]");
						}
					}
					sb.append(")]!");
				}
				
			} else if(characterBeingImpregnated.isPlayer()){
				if(characterProvidingCum==null) {
					sb.append("Despite feeling cum ");
					if(isSlime) {
						sb.append("dispersing through your slimy body, seeking to impregnate your core");
					} else {
						sb.append("deep in your womb");
					}
					sb.append(", you feel that [style.boldSex(you aren't going to get pregnant from this)].");
					
				} else {
					sb.append("Despite feeling ");
					if(selfcest) {
						sb.append("your own [npc.cum+]");
					} else {
						sb.append("[npc2.namePos] [npc2.cum+]");
					}
					if(isSlime) {
						sb.append(" dispersing through your slimy body, seeking to impregnate your core");
					} else {
						sb.append(" deep in your womb");
					}
					sb.append(", you feel that [style.boldSex(you aren't going to get pregnant from this)].");
				}
				
			} else {
				if(characterProvidingCum==null) {
					sb.append("Despite having [npc.her] ");
					if(isSlime) {
						sb.append("slimy body");
					} else {
						sb.append("womb");
					}
					if(selfcest) {
						sb.append(" filled with [npc.her] own seed, [style.boldSex(there's no chance that [npc.nameHasFull] impregnated [npc.herself])].");
					} else {
						sb.append(" filled with cum, [style.boldSex(there's no chance that [npc.nameHasFull] been impregnated)].");
					}
					
				} else {
					sb.append("Despite having [npc.her] ");
					if(isSlime) {
						sb.append("slimy body");
					} else {
						sb.append("womb");
					}
					if(selfcest) {
						sb.append(" filled with [npc.her] own seed, [style.boldSex(there's no chance that [npc.nameHasFull] impregnated [npc.herself])].");
					} else {
						sb.append(" filled with [npc2.namePos] [npc2.cum+], [style.boldSex(there's no chance that [npc.nameHasFull] been impregnated)].");
					}
				}
			}
			sb.append("</p>");
			
			if(characterProvidingCum!=null) {
				return UtilText.parse(characterBeingImpregnated, characterProvidingCum, sb.toString());
			} else {
				return UtilText.parse(characterBeingImpregnated, sb.toString());
			}
		}
	},
	
	LOW_CHANCE {
		@Override
		public String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum, boolean directSexInsemination) {
			boolean isSlime = characterBeingImpregnated.getBodyMaterial()==BodyMaterial.SLIME;
			boolean selfcest = characterBeingImpregnated.equals(characterProvidingCum);
			boolean cumInPussy = isCumInPussy(characterBeingImpregnated, characterProvidingCum);
			
			StringBuilder sb = new StringBuilder();
			
			sb.append("<p class='centre noPad'>");
			if(!cumInPussy) {
				sb.append("Letting out a gasp,");
				if(characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)) {
					sb.append(" [npc.name] [npc.verb(realise)] that [style.boldSex(if [npc.she] [npc.is]n't already pregnant, there's a small chance [npc.she] [npc.is] now)]!");
				} else {
					sb.append(" [npc.name] [npc.verb(realise)] that [style.boldSex(there's a small chance [npc.she]'ll get pregnant from this)]!");
				}
				
			} else if(characterBeingImpregnated.isPlayer()){
				sb.append("You feel ");
				if(selfcest) {
					sb.append("your own [npc.cum+]");
				} else {
					if(characterProvidingCum==null) {
						sb.append("cum");
					} else {
						sb.append("[npc2.namePos] [npc2.cum+]");
					}
				}
				if(isSlime) {
					sb.append(" dispersing through your slimy body, seeking to impregnate your core");
				} else {
					sb.append(" deep in your womb");
				}
				if(characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)) {
					sb.append(", and you realise that [style.boldSex(if you aren't already pregnant, there's a small chance you are now)]!");
				} else {
					sb.append(", and you realise that [style.boldSex(there's a small chance you'll get pregnant)]!");
				}
				
			} else {
				sb.append("After having [npc.her] ");
				if(isSlime) {
					sb.append("slimy body");
				} else {
					sb.append("womb");
				}
				if(selfcest) {
					sb.append(" filled with [npc.her] own seed,");
				} else {
					if(characterProvidingCum==null) {
						sb.append(" filled with cum,");
					} else {
						sb.append(" filled with [npc2.namePos] [npc2.cum+],");
					}
				}
				if(characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)) {
					sb.append(" [npc.name] [npc.verb(realise)] that [style.boldSex(if [npc.she] isn't already pregnant, there's a small chance [npc.she] is now)]!");
				} else {
					sb.append(" [npc.name] [npc.verb(realise)] that [style.boldSex(there's a small chance [npc.she]'ll get pregnant)]!");
				}
			}
			sb.append("</p>");
			
			if(characterProvidingCum!=null) {
				return UtilText.parse(characterBeingImpregnated, characterProvidingCum, sb.toString());
			} else {
				return UtilText.parse(characterBeingImpregnated, sb.toString());
			}
		}
	},
	
	AVERAGE_CHANCE {
		@Override
		public String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum, boolean directSexInsemination) {
			boolean isSlime = characterBeingImpregnated.getBodyMaterial()==BodyMaterial.SLIME;
			boolean selfcest = characterBeingImpregnated.equals(characterProvidingCum);
			boolean cumInPussy = isCumInPussy(characterBeingImpregnated, characterProvidingCum);

			StringBuilder sb = new StringBuilder();
			
			sb.append("<p class='centre noPad'>");
			if(!cumInPussy) {
				sb.append("Letting out a gasp,");
				if(characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)) {
					sb.append(" [npc.name] [npc.verb(realise)] that [style.boldSex(if [npc.she] [npc.is]n't already pregnant, there's a chance [npc.she] [npc.is] now)]!");
				} else {
					sb.append(" [npc.name] [npc.verb(realise)] that [style.boldSex(there's a chance [npc.she]'ll get pregnant from this)]!");
				}
				
			} else if(characterBeingImpregnated.isPlayer()){
				sb.append("You feel ");
				if(selfcest) {
					sb.append("your own [npc.cum+]");
				} else {
					if(characterProvidingCum==null) {
						sb.append("cum");
					} else {
						sb.append("[npc2.namePos] [npc2.cum+]");
					}
				}
				if(isSlime) {
					sb.append(" dispersing through your slimy body, seeking to impregnate your core");
				} else {
					sb.append(" deep in your womb");
				}
				if(characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)) {
					sb.append(", and you realise that [style.boldSex(if you aren't already pregnant, there's a chance you are now)]!");
				} else {
					sb.append(", and you realise that [style.boldSex(there's a chance you'll get pregnant)]!");
				}
				
			} else {
				sb.append("After having [npc.her] ");
				if(isSlime) {
					sb.append("slimy body");
				} else {
					sb.append("womb");
				}
				if(selfcest) {
					sb.append(" filled with [npc.her] own seed,");
				} else {
					if(characterProvidingCum==null) {
						sb.append(" filled with cum,");
					} else {
						sb.append(" filled with [npc2.namePos] [npc2.cum+],");
					}
				}
				if(characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)) {
					sb.append(" [npc.name] [npc.verb(realise)] that [style.boldSex(if [npc.she] isn't already pregnant, there's a chance [npc.she] is now)]!");
				} else {
					sb.append(" [npc.name] [npc.verb(realise)] that [style.boldSex(there's a chance [npc.she]'ll get pregnant)]!");
				}
			}
			sb.append("</p>");
			
			if(characterProvidingCum!=null) {
				return UtilText.parse(characterBeingImpregnated, characterProvidingCum, sb.toString());
			} else {
				return UtilText.parse(characterBeingImpregnated, sb.toString());
			}
		}
	},
	
	HIGH_CHANCE {
		@Override
		public String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum, boolean directSexInsemination) {
			boolean isSlime = characterBeingImpregnated.getBodyMaterial()==BodyMaterial.SLIME;
			boolean selfcest = characterBeingImpregnated.equals(characterProvidingCum);
			boolean cumInPussy = isCumInPussy(characterBeingImpregnated, characterProvidingCum);

			StringBuilder sb = new StringBuilder();
			
			sb.append("<p class='centre noPad'>");
			if(!cumInPussy) {
				sb.append("Letting out a gasp,");
				if(characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)) {
					sb.append(" [npc.name] [npc.verb(realise)] that [style.boldSex(if [npc.she] [npc.is]n't already pregnant, there's a high chance [npc.she] [npc.is] now)]!");
				} else {
					sb.append(" [npc.name] [npc.verb(realise)] that [style.boldSex(there's a high chance [npc.she]'ll get pregnant from this)]!");
				}
				
			} else if(characterBeingImpregnated.isPlayer()){
				sb.append("You feel ");
				if(selfcest) {
					sb.append("your own [npc.cum+]");
				} else {
					if(characterProvidingCum==null) {
						sb.append("cum");
					} else {
						sb.append("[npc2.namePos] [npc2.cum+]");
					}
				}
				if(isSlime) {
					sb.append(" dispersing through your slimy body, seeking to impregnate your core");
				} else {
					sb.append(" deep in your womb");
				}
				if(characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)) {
					sb.append(", and you realise that [style.boldSex(if you aren't already pregnant, there's a high chance you are now)]!");
				} else {
					sb.append(", and you realise that [style.boldSex(there's a high chance you'll get pregnant)]!");
				}
				
			} else {
				sb.append("After having [npc.her] ");
				if(isSlime) {
					sb.append("slimy body");
				} else {
					sb.append("womb");
				}
				if(selfcest) {
					sb.append(" filled with [npc.her] own seed,");
				} else {
					if(characterProvidingCum==null) {
						sb.append(" filled with cum,");
					} else {
						sb.append(" filled with [npc2.namePos] [npc2.cum+],");
					}
				}
				if(characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)) {
					sb.append(" [npc.name] [npc.verb(realise)] that [style.boldSex(if [npc.she] isn't already pregnant, there's a high chance [npc.she] is now)]!");
				} else {
					sb.append(" [npc.name] [npc.verb(realise)] that [style.boldSex(there's a high chance [npc.she]'ll get pregnant)]!");
				}
			}
			sb.append("</p>");
			
			if(characterProvidingCum!=null) {
				return UtilText.parse(characterBeingImpregnated, characterProvidingCum, sb.toString());
			} else {
				return UtilText.parse(characterBeingImpregnated, sb.toString());
			}
		}
	},
	
	CERTAINTY {
		@Override
		public String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum, boolean directSexInsemination) {
			boolean isSlime = characterBeingImpregnated.getBodyMaterial()==BodyMaterial.SLIME;
			boolean selfcest = characterBeingImpregnated.equals(characterProvidingCum);
			boolean cumInPussy = isCumInPussy(characterBeingImpregnated, characterProvidingCum);

			StringBuilder sb = new StringBuilder();
			
			sb.append("<p class='centre noPad'>");
			if(!cumInPussy) {
				sb.append("Letting out a gasp,");
				if(characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)) {
					sb.append(" [npc.name] [npc.verb(realise)] that [style.boldSex(if [npc.she] [npc.is]n't already pregnant, [npc.she] certainly [npc.is] now)]!");
				} else {
					sb.append(" [npc.name] [npc.verb(realise)] that [style.boldSex(it's a certainty that [npc.sheHas] been impregnated from this)]!");
				}
				
			} else if(characterBeingImpregnated.isPlayer()){
				sb.append("You feel ");
				if(selfcest) {
					sb.append("your own [npc.cum+]");
				} else {
					if(characterProvidingCum==null) {
						sb.append("cum");
					} else {
						sb.append("[npc2.namePos] [npc2.cum+]");
					}
				}
				if(isSlime) {
					sb.append(" dispersing through your slimy body, seeking to impregnate your core");
				} else {
					sb.append(" deep in your womb");
				}
				if(characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)) {
					sb.append(", and you realise that [style.boldSex(if you aren't already pregnant, you certainly are now)]!");
				} else {
					sb.append(", and you realise that [style.boldSex(it's a certainty that you've been impregnated)]!");
				}
				
			} else {
				sb.append("After having [npc.her] ");
				if(isSlime) {
					sb.append("slimy body");
				} else {
					sb.append("womb");
				}
				if(selfcest) {
					sb.append(" filled with [npc.her] own seed,");
				} else {
					if(characterProvidingCum==null) {
						sb.append(" filled with cum,");
					} else {
						sb.append(" filled with [npc2.namePos] [npc2.cum+],");
					}
				}
				if(characterBeingImpregnated.hasStatusEffect(StatusEffect.PREGNANT_0)) {
					sb.append(" [npc.name] [npc.verb(realise)] that [style.boldSex(if [npc.she] isn't already pregnant, [npc.she] certainly is now)]!");
				} else {
					sb.append(" [npc.name] [npc.verb(realise)] that [style.boldSex(it's a certainty that [npc.sheHas] been impregnated)]!");
				}
			}
			sb.append("</p>");
			
			if(characterProvidingCum!=null) {
				return UtilText.parse(characterBeingImpregnated, characterProvidingCum, sb.toString());
			} else {
				return UtilText.parse(characterBeingImpregnated, sb.toString());
			}
		}
	};
	
	private static boolean isCumInPussy(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum) {
		return characterBeingImpregnated.getFluidsStoredInOrifice(SexAreaOrifice.VAGINA).stream().anyMatch(f -> {
			try {
				return f.isCum() && f.getFluidCharacter()==characterProvidingCum;
			} catch (Exception e) {
				return false;
			}
		});
	}
	
	public abstract String getDescriptor(GameCharacter characterBeingImpregnated, GameCharacter characterProvidingCum, boolean directSexInsemination);
	
	public static PregnancyDescriptor getPregnancyDescriptorBasedOnProbability(float probability) {
		if (probability <= 0) {
			return PregnancyDescriptor.NO_CHANCE;
		} else if (probability <= 0.15f) {
			return PregnancyDescriptor.LOW_CHANCE;
		} else if (probability <= 0.3f) {
			return PregnancyDescriptor.AVERAGE_CHANCE;
		} else if (probability < 1) {
			return PregnancyDescriptor.HIGH_CHANCE;
		} else {
			return PregnancyDescriptor.CERTAINTY;
		}
	}
}
