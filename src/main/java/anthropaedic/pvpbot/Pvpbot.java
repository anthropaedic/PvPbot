package anthropaedic.pvpbot;

import javax.security.auth.login.LoginException;

import anthropaedic.pvpbot.listener.MessageManager;
import net.dv8tion.jda.core.AccountType;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;
import net.dv8tion.jda.core.hooks.AnnotatedEventManager;

public class Pvpbot {
	private String token;
	private JDA api;
	
	public static void main(String[] args) {
		Pvpbot myBot = new Pvpbot();
	}
	
	public Pvpbot() {
		this("NDM3MzQ1ODc5NzMzNjk4NTcy.Db1HbA.oqBigO58PVrYxIlIBEcv_8t3YRs");
	}
	
	public Pvpbot(String token) {
		this.token = token;
		login();
		registerListeners();
		}
	
	private void login() {
		try {
			api = new JDABuilder(AccountType.BOT)
					.setToken(token)
					.setEventManager(new AnnotatedEventManager())
					.buildBlocking();
		} catch (LoginException | IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void registerListeners() {
		api.addEventListener(new MessageManager());
	}
	

}
