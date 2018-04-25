package anthropaedic.pvpbot.listener;

import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.text.StringEscapeUtils;

import anthropaedic.pvpbot.integration.FacebookCompatibleReactions;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.core.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.core.hooks.SubscribeEvent;

public class EchoChannelMessageManager extends ChannelMessageManager
{
	public EchoChannelMessageManager(Channel listenChannel) 
	{
		super(listenChannel);
	}
	
	@SubscribeEvent
	public void receivedMessageEvent(MessageReceivedEvent event) 
	{
		if(canSendMessage(event))
		{
			sendMessage(event.getMessage());
		}
		System.out.println("Can't send nuffin!");
	}

	@SubscribeEvent
	public void receivedMessageReactionAddEvent(MessageReactionAddEvent event) 
	{
		String unicodeCharacter = event.getReactionEmote().getName();
		System.out.println("Message: " + event.getMessageId() + " added reaction " + getEscaped(unicodeCharacter)); 
		String facebookEmote = getFacebookEmote(unicodeCharacter);
		((TextChannel) myChannel).sendMessage(facebookEmote).queue();
	}

	@SubscribeEvent
	public void receivedMessageRemoveEvent(MessageReactionRemoveEvent event) 
	{
		System.out.println("Message: " + event.getMessageId() + " removed reaction " + getEscaped(event.getReactionEmote().getName()));
	}

	private String getEscaped(String emoji) 
	{
		return "Escaped:\t" + StringEscapeUtils.escapeJava(emoji);
	}
	
	private String getFacebookEmote(String unicodeCharacter)
	{
		if(unicodeCharacter != null)
		{
			return FacebookCompatibleReactions.getFacebookReaction(unicodeCharacter).toString();
		}
		
		return StringUtils.EMPTY;
	}
	
	private void sendMessage(Message message) 
	{
		((TextChannel) myChannel).sendMessage(message).queue();
		System.out.println("Message sent: " + message.getContentStripped());
	}
		
	private boolean canSendMessage(MessageReceivedEvent event)
	{
		return 	isDesignatedChannel(event) &&
				!isAuthorSelf(event) &&
				!shouldIgnoreMessage(event);
	}

	private boolean isDesignatedChannel(MessageReceivedEvent event) 
	{
		System.out.println("CHANNELS - IN: " + myChannel + " OUT: " + event.getChannel());

		return 	event != null && 
				Objects.equals(event.getChannel(), myChannel);
	}
	
	private boolean isAuthorSelf(MessageReceivedEvent event) 
	{
		if(event != null) 
		{
			User author = event.getAuthor();
			User self = event.getJDA().getSelfUser();
			System.out.println("is self? " + Objects.equals(author, self));
			return Objects.equals(author, self);	
		}

		return true;
	}
	
	private boolean shouldIgnoreMessage(MessageReceivedEvent event) 
	{
		if(event != null) 
		{
			TextChannel channel = event.getTextChannel();
			System.out.println("isBot? " + event.getAuthor().isBot());
			System.out.println("can talk? " + channel.canTalk());
			return	channel != null && 
					(event.getAuthor().isBot() || !channel.canTalk());
		}
		
		return true;
	}
}
