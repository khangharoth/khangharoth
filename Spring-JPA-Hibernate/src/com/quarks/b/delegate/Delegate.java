/**
 *
 */
package com.quarks.b.delegate;

import java.util.List;

import javax.annotation.Resource;

import com.quarks.c.service.MessageService;
import com.quarks.e.persistence.Message;

/**
 * This is the Mediator between controller layer and Service layer.
 *
 * @author Kuldeep Singh
 */
public final class Delegate
{
	@Resource(name="messageService")
	private MessageService	messageService;


	public List<Message> findAllMessages()
	{return messageService.findAllMessages();}
}
