package anthropaedic.pvpbot;

import javax.security.auth.login.LoginException;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import anthropaedic.pvpbot.listener.EchoChannelMessageManager;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.entities.Channel;
import net.dv8tion.jda.core.hooks.AnnotatedEventManager;

public class Pvpbot {
	private String token;
	private JDA api;
	
	public static void main(String[] args) 
	{
		// For testing
		final String token = "NDM3MzQ1ODc5NzMzNjk4NTcy.Db1HbA.oqBigO58PVrYxIlIBEcv_8t3YRs";
		final String echoChannel = "341084468435484672";
		
//		WebhookHttpServer server = new WebhookHttpServer();
		Pvpbot myBot = new Pvpbot(token);
		myBot.setEchoChannel(echoChannel);
	}
		
	public Pvpbot(String token) 
	{
		this.token = token;
		login();
		registerListeners();
	}
	
	public void setEchoChannel(String echoChannel) {
		Channel listenChannel = api.getTextChannelById(echoChannel);
		api.addEventListener(new EchoChannelMessageManager(listenChannel));
	}

	private void login() {
		try 
		{
			api = new JDABuilder(AccountType.BOT)
					.setToken(token)
					.setEventManager(new AnnotatedEventManager())
					.buildBlocking();
		} 
		catch (LoginException | IllegalArgumentException e) 
		{
			System.err.println("Invalid bot token received.");
		} catch (InterruptedException e) {
			System.err.println("Bot start up interrupted.");
		}
	}
	
	private void registerListeners() 
	{
		api.addEventListener(new EventWaiter());
	}
	

}
