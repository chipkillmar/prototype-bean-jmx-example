package com.chipkillmar;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jmx.export.MBeanExporter;

import javax.annotation.Nullable;

@Configuration
@RequiredArgsConstructor
public class PersonConfiguration {

    private final ObjectProvider<Person> personProvider;

    @Bean
    public boolean addExcludedBean(@Nullable MBeanExporter exporter) {
        // MBeanExporter will be null if JMX is disabled, which it is by default during tests.
        if (exporter != null) {
            // Exclude the singleton "person" bean from the MBean exporter, to prevent this
            // NoSuchBeanDefinitionException:


            // ***************************
            // APPLICATION FAILED TO START
            // ***************************
            //
            // Description:
            //
            // Parameter 0 of constructor in com.chipkillmar.Person required a bean of type 'com.chipkillmar.Address'
            // that could not be found.

            exporter.addExcludedBean("person");
        }
        return true;
    }

    @Bean
    public Person presidentOfTheUnitedStates() {
        Address address = Address.builder()
                .street("1600 Pennsylvania Avenue")
                .city("Washington")
                .state("DC")
                .zip(20500)
                .build();

        Person person = personProvider.getObject(address);
        person.setFirstName("George");
        person.setLastName("Washington");
        person.setEmailAddress("test@example.com");

        return person;
    }
}
