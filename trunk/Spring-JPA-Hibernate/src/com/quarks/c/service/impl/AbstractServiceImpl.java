/**
 *
 */
package com.quarks.c.service.impl;

import javax.persistence.EntityManager;

import com.quarks.c.service.IModelService;
/**
 *
 * @author Kuldeep Singh
 *
 */
public abstract class AbstractServiceImpl implements IModelService
{

    protected EntityManager        entityManager        = null;


}
