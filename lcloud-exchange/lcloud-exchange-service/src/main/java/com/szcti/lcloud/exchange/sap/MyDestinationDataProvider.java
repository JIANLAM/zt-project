//package com.szcti.lcloud.exchange.sap;
//
//import com.sap.conn.jco.ext.DestinationDataEventListener;
//import com.sap.conn.jco.ext.DestinationDataProvider;
//import com.sap.conn.jco.ext.SessionException;
//
//import java.util.HashMap;
//import java.util.Properties;
///**
// * Created by ggqiang on 2018/6/5.
// */
//public class MyDestinationDataProvider implements DestinationDataProvider{
//    private DestinationDataEventListener eL;
//    private HashMap<String, Properties> secureDBStorage = new HashMap<String, Properties>();
//
//    public Properties getDestinationProperties(String destinationName)
//    {
//        try
//        {
//            //read the destination from DB
//            Properties p = secureDBStorage.get(destinationName);
//
//            if(p!=null)
//            {
//                //check if all is correct, for example
//                if(p.isEmpty())
//                    throw new SessionException(SessionException.Type.CREATE_SESSION, "destination configuration is incorrect", null);
//
//                return p;
//            }
//
//            return null;
//        }
//        catch(RuntimeException re)
//        {
//            throw new SessionException(SessionException.Type.CREATE_SESSION,re.getMessage());
//        }
//    }
//
//    //An implementation supporting events has to retain the eventListener instance provided
//    //by the JCo runtime. This listener instance shall be used to notify the JCo runtime
//    //about all changes in destination configurations.
//    public void setDestinationDataEventListener(DestinationDataEventListener eventListener)
//    {
//        this.eL = eventListener;
//    }
//
//    public boolean supportsEvents()
//    {
//        return true;
//    }
//
//    //implementation that saves the properties in a very secure way
//    void changeProperties(String destName, Properties properties)
//    {
//        synchronized(secureDBStorage)
//        {
//            if(properties==null)
//            {
//                if(secureDBStorage.remove(destName)!=null)
//                    eL.deleted(destName);
//            }
//            else
//            {
//                secureDBStorage.put(destName, properties);
//                eL.updated(destName); // create or updated
//            }
//        }
//    }
//}
