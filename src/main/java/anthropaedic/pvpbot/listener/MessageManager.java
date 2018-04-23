package anthropaedic.pvpbot.listener;

import java.util.Objects;

import org.apache.commons.lang3.ObjectUtils;

import net.dv8tion.jda.core.entities.ChannelType;
import net.dv8tion.jda.core.entities.Guild;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.TextChannel;
import net.dv8tion.jda.core.entities.User;
import net.dv8tion.jda.core.events.message.GenericMessageEvent;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;
import net.dv8tion.jda.core.hooks.SubscribeEvent;

public class MessageManager  {
	
	@SubscribeEvent
	public void receivedMessageEvent(MessageReceivedEvent event) 
	{
		MessageChannel channel = event.getChannel();
		User author = event.getAuthor();
		Guild discordServer = event.getGuild();
		Message message = event.getMessage();
		long messageId = event.getMessageIdLong();
		
		
	}
	
	private void sendMessage(Message message) 
	{
		
	}
	
	private void sendMessage(String message)
	{
		
	}
	
	
	private boolean isAuthorSelf(MessageReceivedEvent event) 
	{
		if(event != null) 
		{
			User author = event.getAuthor();
			User self = event.getJDA().getSelfUser();
			return Objects.equals(author, self);	
		}
		
		return true;
	}
	
	private boolean shouldIgnoreMessage(MessageReceivedEvent event) 
	{
		if(event != null) {
			TextChannel channel = event.getTextChannel();
			if(channel != null) 
			{
				return event.getAuthor().isBot() || !channel.canTalk();
			}
			
		}
		
		return true;
	}
	
	
}
