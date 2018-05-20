package com.lilithsthrone.game.character.npc;
import java.util.Random;

import com.lilithsthrone.world.places.PlaceType;
import com.lilithsthrone.game.slavery.SlavePermissionSetting;
import com.lilithsthrone.main.Main;
import com.lilithsthrone.utils.Vector2i;

import java.util.ArrayList;
import java.util.List;;

/* This is a attempt at creating some sort of AI for NPC and try to add:
 * 	-Roaming NPC in the map instead of static location
 * 	-Trigger encounter upon meeting the player in the map  
 * 	-Chase player on sight
 * 	
 * 
 * 	**Time consuming idea**
 * 	-Path finding algorithm for NPC goal(example: Slave has use_you permission could have to get to you before using you instead of teleporting)
 * 
 * 
 * PS: Java is not my main language 
 */

public abstract class NPCAI {
	public static void endTurn(NPC currentNpc)
	{
		//Movement:
		
		//Everybody that is not a slave or a companion can move
		if(shouldMove(currentNpc)) {
			moveToNextRandomLocation(currentNpc);
		}		
		else if(Main.game.getSlaveryUtil().isResting(currentNpc) && currentNpc.hasSlavePermissionSetting(SlavePermissionSetting.GENERAL_HOUSE_FREEDOM)) {		
			moveToNextRandomLocation(currentNpc);			
		}
	}
	
	public static void moveToNextRandomLocation(NPC currentNpc)
	{				
		Random rand = new Random();
		if(!Main.game.isInSex() || !Main.game.isInCombat())
		{			
			//if the NPC is in the same world as the player and if not unique TODO add canmove function to npc so that unique npc can move		
			if (Main.game.getActiveWorld().getWorldType() == currentNpc.getWorldLocation() && !currentNpc.isUnique() && !currentNpc.getLocation().equals(Main.game.getPlayer().getLocation()) )
			{
				List<Vector2i> availableLocation = new ArrayList<Vector2i>();
				availableLocation = getAvailableNextLocation(currentNpc.getLocation());
				if (!availableLocation.isEmpty())
				{
					currentNpc.setLocation(availableLocation.get(rand.nextInt(availableLocation.size())));						
				}
			}
		}		
	}
	/*   N       Y+1
	 * W + E x-1	 x+1
	 *   S		 Y-1
	 */
	
	private static List<Vector2i> getAvailableNextLocation(Vector2i currentPosition)
	{
	    List<Vector2i> availableLocations = new ArrayList<Vector2i>();
	    Vector2i north = new Vector2i(currentPosition.getX(),currentPosition.getY()+1);
	    
	    Vector2i south = new Vector2i(currentPosition.getX(),currentPosition.getY()-1);
	            
	    Vector2i west = new Vector2i(currentPosition.getX()-1,currentPosition.getY());
	            
	    Vector2i east = new Vector2i(currentPosition.getX()+1 ,currentPosition.getY());;
	            
	    
	    if (north.getY() < Main.game.getActiveWorld().WORLD_HEIGHT && cellIsPassable(north))
	    {
	        availableLocations.add(north);
	    }
	    if (south.getY() >=0 && cellIsPassable(south))
	    {
	        availableLocations.add(south);
	    }
	    if (west.getX() >= 0 && cellIsPassable(west))
	    {
	        availableLocations.add(west);
	    }
	    if (east.getX() < Main.game.getActiveWorld().WORLD_WIDTH && cellIsPassable(east))
	    {
	        availableLocations.add(east);
	    }
	    
	    return availableLocations;
	}

	private static boolean cellIsPassable(Vector2i cellLocation) {
	    return Main.game.getActiveWorld().getCell(cellLocation).getPlace().getPlaceType() != PlaceType.GENERIC_IMPASSABLE;
	}
	
	private static boolean shouldMove(NPC currentNpc) {
		return !currentNpc.isSlave() && !Main.game.getPlayer().getCompanions().contains(currentNpc);
	}
}
