package anthropaedic.pvpbot.listener;

import java.util.Objects;

import org.apache.commons.text.StringEscapeUtils;

import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageReaction;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.core.events.message.react.MessageReactionRemoveEvent;
import net.dv8tion.jda.core.hooks.SubscribeEvent;

public class ChannelMessageManager  {
	
	private final Channel myChannel;
	
	private ChannelMessageManager() 
	{
		throw new IllegalAccessError("Must be instantiated with a channel.");
	}
	
	public ChannelMessageManager(Channel myChannel)
	{
		// TODO: Verify channel
		this.myChannel = myChannel;
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
		MessageReaction r;
		System.out.println("Message: " + event.getMessageId() + " added reaction "); 
		printUnicode(event.getReactionEmote().getName());
	}

	@SubscribeEvent
	public void receivedMessageRemoveEvent(MessageReactionRemoveEvent event) 
	{
		System.out.println("Message: " + event.getMessageId() + " removed reaction ");
		printUnicode(event.getReactionEmote().getName());
	}

	private void printUnicode(String emoji) 
	{
		System.out.println("Escaped:\t" + StringEscapeUtils.escapeJava(emoji));
		System.out.println("Unescaped:\t" + StringEscapeUtils.unescapeJava(emoji));
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
