package com.subhasish.spring.ws.api.model;

import java.math.BigInteger;

import com.subhasish.spring.ws.api.rsa.Rsa;

public class ChatMessage {
	private String content;
	private String sender;
	private MessageType type;
	
	Rsa rsa = new Rsa(50);
	public enum MessageType {
		CHAT, LEAVE, JOIN
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content=content;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}
	
	public ChatMessage sendMessageToReceiver(Rsa rsa, ChatMessage chatMessage,String msg) {
		BigInteger message=new BigInteger(chatMessage.getContent());
		BigInteger decrypt = rsa.decrypt(message);
		byte[] b=decrypt.toByteArray();
		String string = new String(b);
		System.out.println("Decrypted "+ chatMessage.getContent());
		chatMessage.setContent(msg);
		return chatMessage;
	}

}
