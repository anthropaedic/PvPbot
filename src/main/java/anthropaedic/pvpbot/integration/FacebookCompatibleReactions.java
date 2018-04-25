package anthropaedic.pvpbot.integration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.EnumUtils;

public final class FacebookCompatibleReactions 
{
	private final static EnumMap<FacebookReaction, List<String>> reactionMap;
	private final static HashMap<String, FacebookReaction> characterMap;
	
	private final static List<String> LOVE_LIST;
	private final static List<String> HAHA_LIST;
	private final static List<String> WOW_LIST;
	private final static List<String> SAD_LIST;
	private final static List<String> ANGRY_LIST;
	private final static List<String> LIKE_LIST;
	private final static List<String> DISLIKE_LIST;
	
	static {
		LOVE_LIST = getList("\u2763,\u2764,\uD83D\uDC9B,\uD83D\uDC9A,\uD83D\uDC99,\uD83D\uDC9C,"
						  + "\uD83D\uDE0D,\uD83D\uDE18,\uD83D\uDC95,\uD83D\uDC9E,\uD83D\uDC93,"
						  + "\uD83D\uDC97,\uD83D\uDC96,\uD83D\uDC98,\uD83D\uDC9D,\uD83D\uDC9F");
		HAHA_LIST = getList("\uD83D\uDE02");
		LIKE_LIST = getList("\uD83D\uDC4D");
		DISLIKE_LIST = getList("\uD83D\uDC4E");
		WOW_LIST = getList("\uD83D\uDE2F,\uD83D\uDE32");
		SAD_LIST = getList("\uD83D\uDE22,\uD83D\uDE25,\uD83D\uDE2A");
		ANGRY_LIST = getList("\uD83D\uDE20,\uD83D\uDE21");

		reactionMap = new EnumMap<>(FacebookReaction.class);
		characterMap = new HashMap<>();
		
		populateMaps(FacebookReaction.LOVE, LOVE_LIST);
		populateMaps(FacebookReaction.HAHA, HAHA_LIST);
		populateMaps(FacebookReaction.LIKE, LIKE_LIST);
		populateMaps(FacebookReaction.DISLIKE, DISLIKE_LIST);
		populateMaps(FacebookReaction.WOW, WOW_LIST);
		populateMaps(FacebookReaction.SAD, SAD_LIST);
		populateMaps(FacebookReaction.ANGRY, ANGRY_LIST);
	}
	
	public static FacebookReaction getFacebookReaction(String unicodeCharacter) 
	{
		FacebookReaction reaction = characterMap.get(unicodeCharacter);
		if(isValidEnum(reaction))
		{
			return reaction;
		}
		else 
		{
			return FacebookReaction.INVALID;
		}
	}
	
	public static List<String> getUnicodeCharacter(FacebookReaction reaction) 
	{
		if(reaction != null)
		{
			return reactionMap.get(reaction);
		}
		
		return new ArrayList<>();
	}

	private FacebookCompatibleReactions() 
	{
		throw new IllegalAccessError("Cannot instantiate FacebookCompatibleReactions.class");
	}

	private static List<String> getList(String commaSeparated) 
	{
		return Arrays.asList(commaSeparated.split("\\s*,\\s*"));
	}
	
	private static boolean isValidEnum(FacebookReaction reaction)
	{
		return reaction != null && EnumUtils.isValidEnum(FacebookReaction.class, reaction.toString());
	}
	
	private static  void addListToReaction(FacebookReaction reaction, List<String> reactionList)
	{
		reactionMap.put(reaction, reactionList);
	}
	
	private static void populateCharacterMapFromReactionList(FacebookReaction reaction, List<String> reactionList) 
	{
		for(String reactionCharacter : reactionList) 
		{
			characterMap.put(reactionCharacter, reaction);
		}
	}
	
	private static void populateMaps(FacebookReaction reaction, List<String> reactionList) 
	{
		addListToReaction(reaction, reactionList);
		populateCharacterMapFromReactionList(reaction, reactionList);
	}
}
