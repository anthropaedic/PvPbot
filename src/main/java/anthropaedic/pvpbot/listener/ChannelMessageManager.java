package anthropaedic.pvpbot.listener;

import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.events.message.MessageReceivedEvent;
import net.dv8tion.jda.core.events.message.react.MessageReactionAddEvent;
import net.dv8tion.jda.core.events.message.react.MessageReactionRemoveEvent;

public abstract class ChannelMessageManager
{	
	final Channel myChannel;

	public ChannelMessageManager() 
	{
		throw new IllegalAccessError("Must be instantiated with a channel.");
	}
	
	public ChannelMessageManager(Channel myChannel)
	{
		// TODO: Verify channel
		this.myChannel = myChannel;
	}
	
	public abstract void receivedMessageEvent(MessageReceivedEvent event);
	public abstract void receivedMessageReactionAddEvent(MessageReactionAddEvent event); 
	public abstract void receivedMessageRemoveEvent(MessageReactionRemoveEvent event);
}
