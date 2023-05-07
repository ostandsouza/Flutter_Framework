package com.codecraft.flutter.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;

;


public class ReadJson
{
    private static final Logger logger = LogManager.getLogger(ReadJson.class);
    private static ReadJson Obj;

    private ReadJson(){};

    public static ReadJson getInstance()
    {
        if(Obj==null)
            Obj = new ReadJson();
        return Obj;
    }

    /**
     * Function accept file name(with extension) present in the resource folder"/main/java/resource". Resource folder that is marked as source.
     */
    public List<String> parsingJSONFile(String fileName, String parentNode, String searchKey)
    {

        logger.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "is executed");
        logger.info("JSON File named passed : " +fileName);
        JSONParser jp = new JSONParser();
        List<String> listOfValues = new LinkedList<String>();

        try
        {
            JSONObject jsonObject = (JSONObject)jp.parse(new FileReader(ReadJson.getInstance().getClass().getClassLoader().getResource(fileName).getPath()));
            //Default return type is JSONArray
            return  parsingJSONArray(jsonObject,parentNode,searchKey);



        }catch(Exception e)
        {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }
    }

    /**e
     * Incase of need to accept JSONObject and JSONArray, then change the param as JsonElement.
     * Prefer to use this funnction with return value more than one.
     * @param jsonObject
     * @param parentNode
     * @param searchKey
     * @return
     */
    public List<String> parsingJSONArray(JSONObject jsonObject, String parentNode, String searchKey)
    {
        logger.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "is executed");
        JSONParser jp = new JSONParser();
        List<String> listOfValues = new LinkedList<String>();

        try
        {
            //Default return type is JSONArray
            JSONArray arrayObj= (JSONArray) jsonObject.get(parentNode);
            if(!arrayObj.isEmpty() && arrayObj.size()>0)
            {
                arrayObj.forEach(obj-> {
                    JSONObject childObj = (JSONObject) obj;
                    logger.info(childObj.get(searchKey));
                    listOfValues.add(childObj.get(searchKey).toString());
                });

            }else
            {}
            logger.debug(listOfValues);
            return listOfValues;

        }catch(Exception e)
        {
            e.printStackTrace();
            return Collections.EMPTY_LIST;
        }
    }
    /**
     * Function accept file name(with extension) present in the resource folder"/main/java/resource". Resource folder that is marked as source.
     *
     *
     *
     *
     *
     */
    public Map<String, String> parsingJSONForAParentNode(String fileName, String parentNode, String searchKey)
    {
        logger.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "is executed");
        logger.info("JSON File named passed : " +fileName);
        JSONParser jp = new JSONParser();
        List<String> listOfValues = new LinkedList<String>();

        try
        {
            JSONObject jsonObject = (JSONObject)jp.parse(new FileReader(ReadJson.getInstance().getClass().getClassLoader().getResource(fileName).getPath()));
            JSONObject obj=(JSONObject)jsonObject.get(parentNode);

            if(searchKey==null) {
                return (Map<String, String>) obj.keySet().stream()
                        .collect(Collectors.toMap(x -> x, x -> {
                            if (obj.get(x).getClass().toString().contains("java.lang.String"))
                                return obj.get(x);
                            return x;
                        }));
            }
            logger.debug("Parsing JSON with search key as " +searchKey);
            JSONObject childObj=(JSONObject)obj.get(searchKey);
            logger.debug("Number of elements fetched " +childObj.size());

            return (Map<String, String>) childObj.keySet().stream() .collect(Collectors.toMap(x -> x, x ->childObj.get(x)));

        }catch(Exception e)
        {
            e.printStackTrace();
            Collections.emptyMap();
        }
        return null;
    }



    public String getAValueFromJSON(String fileName, String parentNode, String searchKey)
    {
        logger.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "is executed");

        try
        {
            JSONParser jp = new JSONParser();

            JSONObject jsonObject = (JSONObject)jp.parse(new FileReader(ReadJson.getInstance().getClass().getClassLoader().getResource(fileName).getPath()));
            JSONObject parentObj= (JSONObject) jsonObject.get(parentNode);
            return Optional.ofNullable(parentObj.get(searchKey).toString()).get();

        }catch(Exception e)
        {
            e.printStackTrace();
            return "Null";
        }
    }


    //Recommended function with JSON Object fetched using API and want to return a value only from child node.
    public String getAValueFromJSON(JSONObject jsonObject, String parentNode, String searchKey)
    {
        logger.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "is executed");
        try
        {
            if(searchKey==null)
                return jsonObject.get(parentNode).toString();
            else {
                JSONObject parentObj = (JSONObject) jsonObject.get(parentNode);
                return Optional.ofNullable(parentObj.get(searchKey).toString()).get();
            }

        }catch(Exception e)
        {
            e.printStackTrace();
            return "Null";
        }
    }

    public String getValueFromJsonKey(String fileName, String searchKey)
    {
        try
        {
            JSONParser jp = new JSONParser();

            JSONObject jsonObject = (JSONObject)jp.parse(new FileReader(ReadJson.getInstance().getClass().getClassLoader().getResource(fileName).getPath()));
            return Optional.ofNullable(jsonObject.get(searchKey).toString()).get();

        }catch(Exception e)
        {
            e.printStackTrace();
            return "Null";
        }
    }


    public HashMap<String, Object> getJSONForParentNode(String fileName, String parentNode)
    {
        JSONParser jp = new JSONParser();
        List<String> listOfValues = new LinkedList<String>();

        try
        {
            JSONObject jsonObject = (JSONObject)jp.parse(new FileReader(ReadJson.getInstance().getClass().getClassLoader().getResource(fileName).getPath()));
            JSONObject obj=(JSONObject)jsonObject.get(parentNode);

            return obj;

        }catch(Exception e)
        {
            e.printStackTrace();
            Collections.emptyMap();
        }
        return null;
    }

    //created function to parse  json response.
    //Saved the response to parse

    public List<Object> parsingMultipleLevelsOfJsonArray(JSONArray arrayObj, String parentNode, String searchKey)
    {
        logger.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "is executed");
        //Returned value can be boolean or string
        List<Object> listOfValues = new LinkedList<Object>();
        try
        {
//             JSONParser jp = new JSONParser();
//             //line to be removed if parameter that to be accepted is JSON array.
//            //if object then try parsing JSON Object
//             JSONArray arrayObj=  (JSONArray)jp.parse(new FileReader(ReadJson.getInstance().getClass().getClassLoader().getResource(fileName).getPath()));

            //Default return type is JSONArray


            if(!arrayObj.isEmpty() && arrayObj.size()>0) {
                arrayObj.forEach(obj -> {

                    if (((JSONObject) obj).containsKey(parentNode)) {
                        JSONArray secondOj = (JSONArray) ((JSONObject) obj).get(parentNode);

                        secondOj.forEach(secondObj -> {
                            listOfValues.add(((JSONObject) secondObj).get(searchKey));

                        });
                    }
                });

                logger.debug("List of values -" +listOfValues);
                return listOfValues;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;

    }


}
