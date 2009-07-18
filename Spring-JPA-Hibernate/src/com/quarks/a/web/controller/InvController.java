package com.quarks.a.web.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.quarks.b.delegate.Delegate;
import com.quarks.e.persistence.Message;

/**
 * @author Kuldeep Singh
 */
public class InvController implements Controller
{

    protected final Log    logger = LogFactory.getLog( getClass() );

    @Resource(name="delegate")
    private Delegate delegate;

    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {

        String now = ( new java.util.Date() ).toString();

        Map<String, Object> myModel = new HashMap<String, Object>();
        myModel.put( "now", now );

        List<Message> list = delegate.findAllMessages();
        myModel.put( "list", list );
        return new ModelAndView( "hello", "model", myModel );

    }
}
