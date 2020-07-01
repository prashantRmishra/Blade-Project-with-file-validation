package com.nseit.sg.aspa.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PropFileReader {
    String filename;
    String ruleJsonFile;
    BufferedReader bufferedReader=null;
    BufferedReader brJson=null;
    Map<String,List<String>> rule;
    

    public PropFileReader(){

    }
    /**
     * constructor to initialize filename
    */
    public PropFileReader(String filename, String jsonFileName){
        this.ruleJsonFile=jsonFileName;
        this.filename=filename;
        this.propertyBuilder();
    }
    /**
     * This method sets filename
     * @param filename
    */
    public void setFileName(String filename,String jsonFileName){
        this.ruleJsonFile=jsonFileName;
        this.filename=filename;
        this.propertyBuilder();

    }
    
    public void propertyBuilder(){
        File file=null;
        File jsonFile=null;
        FileReader fileReader=null;
        FileReader JsonFileReader=null;
        
        try {
            //Next thre lines are for reading csv file
            file = new File(this.filename);
            fileReader = new FileReader(file);
            this.bufferedReader = new BufferedReader(fileReader);
            //Next three lines are for reading json file
            jsonFile = new File(this.ruleJsonFile);
            JsonFileReader = new FileReader(jsonFile);
            this.brJson = new BufferedReader(JsonFileReader);
            ObjectMapper objMapper = new ObjectMapper();
            this.rule = objMapper.readValue(JsonFileReader, new TypeReference<Map<String,List<String>>>() {});


        } catch (Exception e) {
            e.printStackTrace();
        }
        // finally{
        //     try {
        //         if(fileReader!=null && JsonFileReader!=null){
        //             fileReader.close();
        //             JsonFileReader.close();
        //         }
        //     } catch (Exception e) {
        //     }
        // }
    }
    public BufferedReader getFileName(){
        return this.bufferedReader;
    }
    public BufferedReader getJsonFileName(){
        return this.brJson;
    }
    public List<String> getRules(String key){
        List<String> ruleList=null;
        if(!this.rule.isEmpty() && this.rule != null){
            ruleList=this.rule.get(key);
        }
        return ruleList;
    }


}