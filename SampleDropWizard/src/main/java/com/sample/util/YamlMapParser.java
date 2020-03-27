package com.sample.util;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.File;
import java.io.IOException;
import java.util.Map;

    public class YamlMapParser {
        public static Map<String, Map<String,String>> readValue = null;
        public static Map<String, String> getDbproperties(){
            try {
                ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
                readValue = mapper.readValue(new File("C:/Users/lenovo/IdeaProjects/SampleDropWizard/employee.yaml"), Map.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return readValue.get("database");
        }

    }

