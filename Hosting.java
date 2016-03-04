package net.gandi.xmlrpc;


import java.net.URL;
import java.util.Map;
import java.util.HashMap;

import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class Hosting {


    @SuppressWarnings({ "unchecked", "rawtypes" })
        public static void ApiPaasVhostList(String apikey, Integer paasId) {

            try {   

                XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();

                config.setServerURL(new URL(
                            "https://rpc.gandi.net/xmlrpc/"));

                XmlRpcClient client = new XmlRpcClient();
                client.setTypeFactory(new XmlRpcTypeNil(client));
                client.setConfig(config);

                Map<String,Object> apiParams = new HashMap<String, Object>(); 
                apiParams.put("paas_id", paasId); 

                Object[] params = new Object[] { apikey, apiParams};

                Object[] response = (Object[]) client.execute("paas.vhost.list", params);
                for(Object o: response) {
                    HashMap map = (HashMap) o;
                    System.out.println(map.get("id") + " " + map.get("name") + " " + map.get("date_creation"));
                }


            } catch (Exception e) {
                e.printStackTrace();
            }   
        }   

    @SuppressWarnings("unchecked")
        public static void ApiPaasInfo(String apikey, Integer paasId) {

            try {   

                XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();

                config.setServerURL(new URL(
                            "https://rpc.gandi.net/xmlrpc/"));

                XmlRpcClient client = new XmlRpcClient();
                client.setTypeFactory(new XmlRpcTypeNil(client));
                client.setConfig(config);


                Object[] params = new Object[] { apikey, paasId};

                Object response = client.execute("paas.info", params);
                System.out.println("Object : " + response.toString());

                Map<String, Object> m = (Map<String, Object>) response; 
                for(Map.Entry<String, Object> e : m.entrySet()){


                    String key = String.valueOf(e.getKey());
                    String value = String.valueOf(e.getValue());

                    if(e.getKey().contentEquals("vhosts")) {

                        System.out.println(key + " :: " + value);


                    } else {
                        System.out.println(key + " :: " + value);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }   
        }

    public static void ApiVersionInfo(String apikey) {
        try {    

            XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();

            config.setServerURL(new URL(
                        "https://rpc.gandi.net/xmlrpc/"));

            XmlRpcClient client = new XmlRpcClient();
            client.setTypeFactory(new XmlRpcTypeNil(client));
            client.setConfig(config);

            // Get API version
            Object[] params = new Object[] { apikey };

            Object response = client.execute("version.info", params);
            System.out.println("Message : " + response.toString());


        } catch (Exception e) {
            e.printStackTrace();
        }   
    }

    @SuppressWarnings("unchecked")
        public static void ApiPaasList(String apikey) {
            try {

                XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();

                config.setServerURL(new URL(
                            "https://rpc.gandi.net/xmlrpc/"));

                XmlRpcClient client = new XmlRpcClient();
                client.setTypeFactory(new XmlRpcTypeNil(client));
                client.setConfig(config);

                // Get PaaS list
                Object[] params = new Object[] { apikey };
                Object[] response = (Object[]) client.execute("paas.list", params);

                for(Object r : response) {

                    System.out.println("Object : " + r.toString());
                    Map<String, String> m = (Map<String, String>) r; 
                    for(Map.Entry<String, String> e : m.entrySet()){
                        if(e.getKey().contentEquals("date_end")) {
                            String date_end = String.valueOf(e.getValue());
                            System.out.println("date_end :: " + date_end);
                        } else if (e.getKey().contentEquals("date_start")){
                            String date_start = String.valueOf(e.getValue());
                            System.out.println("date_start :: " + date_start);  
                        }else if (e.getKey().contentEquals("servers")){
                            // TODO get real value

                            String servers = String.valueOf(e.getValue());
                            System.out.println("servers :: " + servers);

                        }else if (e.getKey().contentEquals("data_disk_additional_size")){
                            String data_disk_additional_size = String.valueOf(e.getValue());
                            System.out.println("data_disk_additional_size :: " + data_disk_additional_size);    
                        }else if (e.getKey().contentEquals("date_end_commitment")){
                            String date_end_commitment = String.valueOf(e.getValue());
                            System.out.println("date_end_commitment :: " + date_end_commitment);    
                        } else if (e.getKey().contentEquals("id")){
                            String id = String.valueOf(e.getValue());
                            System.out.println("id :: " + id);  
                        }else if (e.getKey().contentEquals("datacenter_id")){
                            String datacenter_id = String.valueOf(e.getValue());
                            System.out.println("datacenter_id :: " + datacenter_id);
                        }else if (e.getKey().contentEquals("need_upgrade")){
                            String need_upgrade = String.valueOf(e.getValue());
                            System.out.println("need_upgrade :: "+ need_upgrade);
                        }else {
                            System.out.println(e.getKey() +" :: "+ e.getValue());
                        }
                    }

                }


            } catch (Exception e) {
                e.printStackTrace();
            }   
        }

    public static void main (String [] args) {
        // Production API key
        String apikey = "API_KEY"; 

        ApiVersionInfo(apikey);

        ApiPaasList(apikey);

        Integer idInstance = 131978;
        ApiPaasInfo(apikey, idInstance);
        ApiPaasVhostList(apikey, idInstance);
    }
}
