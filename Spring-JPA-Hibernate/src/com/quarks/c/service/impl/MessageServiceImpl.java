package com.quarks.c.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.transaction.annotation.Transactional;

import com.quarks.c.service.MessageService;
import com.quarks.d.dao.MessageDAO;
import com.quarks.e.persistence.Message;
/**
 *
 * @author Kuldeep Singh
 *
 */
public class MessageServiceImpl extends AbstractServiceImpl implements MessageService
{

    @Resource(name="messageDAO")
    private  MessageDAO messageDAO;

    @Transactional
    public List<Message> findAllMessages()
    {
        return messageDAO.findAll();
    }

}
