//package com.szcti.lcloud.exchange.sap;
//
//import com.sap.conn.jco.JCoDestination;
//import com.sap.conn.jco.JCoDestinationManager;
//import com.sap.conn.jco.JCoException;
//import com.sap.conn.jco.JCoFunction;
//import com.sap.conn.jco.JCoTable;
//import com.sap.conn.jco.ext.DestinationDataProvider;
//import com.sap.conn.jco.ext.Environment;
//
//import java.util.Map;
//import java.util.Properties;
//
///**
// *
// * Created by ggqiang on 2018/6/5.
// */
//public class Java2SapDestination {
//    public Object executeCalls(Map<String, Object> map, String[] items) {
//        JCoDestination dest;
//        Object object = null;
//        try {
//            dest = JCoDestinationManager.getDestination("ABAP_AS");
//            dest.ping();
//            //System.out.println("路之家订单开始传入SAP " + map.size());
//
//            //从对象仓库中获取 RFM 函数，需要有SAP提供
//            JCoFunction function = dest.getRepository().getFunction("ZS_TRANS_ORDER");
//            //设置函数的参数
////            function.getImportParameterList().setValue("NUM", "1000001356");
////            function.getImportParameterList().setValue("ID", "1000001356");
////            function.getImportParameterList().setValue("DDLY", "5");
//            //执行函数
//           // function.execute(dest);
//            JCoTable tab =   function.getTableParameterList().getTable("ORDER");
//            System.out.println(tab.getNumRows());
//
////            tab.appendRow();
////            tab.setValue("ID_", "zxcvbbqwertyuiop");
////            tab.setValue("NUM_", "zxcvbbqwertyuiop");
////			function.execute(dest);
//
////			String state= function.getExportParameterList().getString("RETMSG");//调用接口返回状态
////			String message= function.getExportParameterList().getString("E_MESSAGE");//调用接口返回信息
////			System.out.println("state："+state);
//
//			tab.firstRow();
////			tab.getString("NUM_");
//			System.out.println(tab.getString("NUM_"));
//
////			System.out.println("state："+message);
////            System.out.println(tab.getNumRows());
////           for (int i = 0; i < 10; i++) {
////			tab.setRow(i);
////		}
//            //获取函数的返回参数KCNUM,EXEMSG
//            //Object stock = function.getExportParameterList().getValue("NUM");
////            Object msg = function.getExportParameterList().getValue("RETMSG");
////            System.out.println("SAP返回的库存为："+"");
////            System.out.println("SAP返回的执行信息为："+msg);
//        } catch (JCoException e) {
//            e.printStackTrace();
//            System.out.println("Execution on destination " + map + " failed");
//        }
//        return object;
//
//    }
//
//    public static Properties getDestinationPropertiesFromUI() {
//        //adapt parameters in order to configure a valid destination
//        Properties connectProperties = new Properties();
//        connectProperties.setProperty(DestinationDataProvider.JCO_ASHOST, "192.192.2.218");
//        connectProperties.setProperty(DestinationDataProvider.JCO_SYSNR, "00");
//        connectProperties.setProperty(DestinationDataProvider.JCO_CLIENT, "600");
//        connectProperties.setProperty(DestinationDataProvider.JCO_USER, "GONGGUOQIANG");
//        connectProperties.setProperty(DestinationDataProvider.JCO_PASSWD, "282716");
//        connectProperties.setProperty(DestinationDataProvider.JCO_LANG, "ZH");
//        return connectProperties;
//    }
//
//    public Object data2SAP(Map<String, Object> param, String[] array) {
//        MyDestinationDataProvider myProvider = new MyDestinationDataProvider();
//
//        try {
//            Environment.registerDestinationDataProvider(myProvider);
//        } catch (IllegalStateException providerAlreadyRegisteredException) {
//            throw new Error(providerAlreadyRegisteredException);
//        }
//        String destName = "ABAP_AS";//这个名字可以自己定义
//
//        myProvider.changeProperties(destName, getDestinationPropertiesFromUI());
//        Object reStr = executeCalls(param, array);
//        Environment.unregisterDestinationDataProvider(myProvider);
//        return reStr;
//    }
//
//    public static void main(String[] args) {
//        //测试结果sapcode:1000001357,库存为992；sapcode:1000001358,库存为8760；sapcode:1000001356,库存为81288
//        Java2SapDestination destination = new Java2SapDestination();
//        destination.data2SAP(null,null);
//    }
//}
