/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uwaterloo.ece658.entity;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.UserTransaction;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;

/**
 *
 * @author mier
 */
@RunWith(Arquillian.class)
public abstract class EntityPersistenceTest {

    @Deployment
    public static Archive<?> createDeployment() {
        return ShrinkWrap.create(WebArchive.class, "test.war")
                .addPackage(User.class.getPackage())
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @PersistenceContext
    protected EntityManager em;

    @Inject
    protected UserTransaction utx;

    @Before
    public void preparePersistenceTest() throws Exception {
        startTransaction();
        setUpDatabase();
    }

    protected abstract void setUpDatabase() throws Exception;

    public void startTransaction() throws Exception {
        utx.begin();
        em.joinTransaction();
    }

    // Discard changes to the database to make it in clean state for next test
    @After
    public void rollbackTransaction() throws Exception {
        utx.rollback();
    }
}
