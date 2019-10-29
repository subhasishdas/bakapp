package com.subhasish.spring.ws.api.controller;

import java.math.BigInteger;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import com.subhasish.spring.ws.api.autoresponse.AutoResponse;
import com.subhasish.spring.ws.api.model.ChatMessage;
import com.subhasish.spring.ws.api.rsa.Rsa;

@Controller
public class ChatController {

	Rsa rsa = new Rsa(50);
	AutoResponse ar = new AutoResponse();
	
	String chatMessages;
	
	@MessageMapping("/chat.register")
	@SendTo("/topic/public")
	public ChatMessage register(@Payload ChatMessage chatMessage, SimpMessageHeaderAccessor headerAccessor) {
		headerAccessor.getSessionAttributes().put("username", chatMessage.getSender());
		return chatMessage;
	}

	@MessageMapping("/chat.send")
	@SendTo("/topic/public")
	public ChatMessage sendMessage(@Payload ChatMessage chatMessage) {
		String s = chatMessage.getContent();
		chatMessages=s;
       byte[] bytes = s.getBytes();
       BigInteger message = new BigInteger(bytes);
       BigInteger encrypt = rsa.encrypt(message);
       chatMessage.setContent(encrypt.toString());
		System.out.println("Encrypted" + encrypt.toString());
		//chatMessage.setContent(chatMessages);
       //sendMessageToReceiver(chatMessage);
		return new ChatMessage().sendMessageToReceiver(rsa, chatMessage, chatMessages);
	}

	@MessageMapping("/chat1.send")
	@SendTo("/topic/public")
	public ChatMessage sendAutoMessage(@Payload ChatMessage chatMessage) {
		String s = chatMessage.getContent().toLowerCase();
		s=ar.findWord(s);
	       byte[] bytes = s.getBytes();
	       BigInteger message = new BigInteger(bytes);
	       BigInteger encrypt = rsa.encrypt(message);
	       //chatMessage.setContent(encrypt.toString());
		chatMessage.setContent(new AutoResponse().getResponse(encrypt.toString()));
		return chatMessage;
	}
	
}
