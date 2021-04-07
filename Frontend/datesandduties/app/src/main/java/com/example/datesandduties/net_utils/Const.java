package com.example.datesandduties.net_utils;

public class Const {

    public static final String URL_LIST_ALL_ACCOUNTS = "http://coms-309-008.cs.iastate.edu:8080/account/all";
    public static final String URL_CREATE_ACCOUNT = "http://coms-309-008.cs.iastate.edu:8080/account/add";
    public static final String URL_LOGIN = "http://coms-309-008.cs.iastate.edu:8080/account/login";
    public static final String DEL_USER = "http://coms-309-008.cs.iastate.edu:8080/account/delete";


    public static final String ADD_EVENT = "http://coms-309-008.cs.iastate.edu:8080/event/add";
    public static final String ALL_EVENT = "http://coms-309-008.cs.iastate.edu:8080/event/all";
    public static final String DEL_EVENT = "http://coms-309-008.cs.iastate.edu:8080/event/delete";

    public static final String FIND_USER = "http://coms-309-008.cs.iastate.edu:8080/account/findUser"; //username
    public static final String FIND_EVENT = "http://coms-309-008.cs.iastate.edu:8080/event/findEvent"; //title

    public static final String LINK_EVENT = "http://coms-309-008.cs.iastate.edu:8080/account/assignEvent"; //accid //eventid
    public static final String GET_EVENTS = "http://coms-309-008.cs.iastate.edu:8080/account/events"; //id
}
