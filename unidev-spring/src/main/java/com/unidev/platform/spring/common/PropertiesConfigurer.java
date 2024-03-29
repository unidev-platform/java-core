package com.unidev.platform.spring.common;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
/**
 * Properties configurer, store inside properties
 * Example of usage: <br/>
 <pre>
 <bean id="propertyConfigurer" class="com.unidev.platform.spring.common.PropertiesConfigurer">
 <property name="ignoreResourceNotFound" value="true"/>
 <property name="ignoreUnresolvablePlaceholders" value="true" />
 <property name="locations">
 <list>
 <value>classpath*:/application.properties</value>
 <value>classpath*:/application.#{systemProperties['ENV']}.properties</value>
 <value>file://${user.home}/${application.name}/application.properties</value>
 <value>file://${user.home}/${application.name}/application.#{systemProperties['ENV']}.properties</value>
 </list>
 </property>
 </bean>
 </pre>

 * @author denis
 */
public class PropertiesConfigurer extends PropertyPlaceholderConfigurer {
    private Map propertiesMap;
    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory,
                                     Properties props) throws BeansException {
        super.processProperties(beanFactory, props);
        propertiesMap = new HashMap<String, String>();
        for (Object key : props.keySet()) {
            String keyStr = key.toString();


            propertiesMap.put(keyStr, resolvePlaceholder(props.getProperty(keyStr),
                    props));
        }
    }
    public Map getPropertiesMap() {
        return propertiesMap;
    }
    public void setPropertiesMap(Map propertiesMap) {
        this.propertiesMap = propertiesMap;
    }
    public String getProperty(String name) {
        return propertiesMap.get(name) + "";
    }
    public String getProperty(String name, String defaultValue) {
        if (!propertiesMap.containsKey(name)) {
            return defaultValue;
        }
        return propertiesMap.get(name) + "";
    }
}