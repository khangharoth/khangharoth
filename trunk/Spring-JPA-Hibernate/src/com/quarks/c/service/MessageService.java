/**
 *
 */
package com.quarks.c.service;

import java.util.List;

import com.quarks.e.persistence.Message;


/**
 *
 * @author Kuldeep Singh
 *
 */
public interface MessageService extends IModelService
{
    List<Message> findAllMessages();
}
