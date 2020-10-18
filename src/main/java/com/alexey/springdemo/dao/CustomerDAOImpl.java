package com.alexey.springdemo.dao;

import com.alexey.springdemo.entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {


    //    need to inject session factory
    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public List<Customer> getCustomers() {
//        get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        //        create a query...sort by last name
        Query<Customer> theQuery =
                currentSession.createQuery("from Customer order by lastName");

//        execute a query and get the result list
        List<Customer> customers = theQuery.getResultList();
//        return the results
        return customers;
    }

    @Override
    public void saveCustomer(Customer theCustomer) {
//        get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
//        save the current ... finally LOL
        currentSession.saveOrUpdate(theCustomer);
    }

    @Override
    public Customer getCustomer(int theId) {
        //        get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
//        read/retrieve the customer from db using primary key
        Customer theCustomer = currentSession.get(Customer.class, theId);
        return theCustomer;
    }

    @Override
    public void deleteCustomer(int theId) {
        //        get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();
//        delete customer form db by primary key
      Query<Customer> theQuery = currentSession.createQuery("delete from Customer where id=:theCustomerId");
      theQuery.setParameter("theCustomerId", theId);
      theQuery.executeUpdate();
    }

    @Override
    public List<Customer> searchCustomers(String theSearchName) {
//        get the current hibernate session
        Session currentSession = sessionFactory.getCurrentSession();

        Query theQuery = null;

        //
        // only search by name if theSearchName is not empty
        //
        if (theSearchName != null && theSearchName.trim().length() > 0) {

            // search for firstName or lastName ... case insensitive
            theQuery =currentSession.createQuery("from Customer where lower(firstName) like :theName or lower(lastName) like :theName");
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");

        }
        else {
            // theSearchName is empty ... so just get all customers
            theQuery =currentSession.createQuery("from Customer");
        }

        // execute query and get result list
        List<Customer> customers = theQuery.getResultList();

        // return the results
        return customers;
    }

}
