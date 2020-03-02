package com.chipkillmar;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import javax.management.*;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(properties = "spring.jmx.enabled=true")
class ApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() throws MalformedObjectNameException {
        Person person = applicationContext.getBean("presidentOfTheUnitedStates", Person.class);
        assertThat(person).isNotNull();

        MBeanServer mbeanServer = MBeanServerFactory.findMBeanServer(null).get(0);
        Set<ObjectInstance> objectInstances = mbeanServer.queryMBeans(new ObjectName("com.chipkillmar:*"), null);
        assertThat(objectInstances).hasSize(1);
    }
}
