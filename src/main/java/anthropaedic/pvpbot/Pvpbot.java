package anthropaedic.pvpbot;

import javax.security.auth.login.LoginException;

import com.jagrosh.jdautilities.commons.waiter.EventWaiter;

import anthropaedic.pvpbot.listener.ChannelMessageManager;
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
		Pvpbot myBot = new Pvpbot();
	}
	
	public Pvpbot() 
	{
		this("NDM3MzQ1ODc5NzMzNjk4NTcy.Db1HbA.oqBigO58PVrYxIlIBEcv_8t3YRs");
	}
	
	public Pvpbot(String token) 
	{
		this.token = token;
		login();
		registerListeners();
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
		Channel listenChannel = api.getTextChannelById("341084468435484672");
		api.addEventListener(new ChannelMessageManager(listenChannel));
		api.addEventListener(new EventWaiter());
	}
	

}
