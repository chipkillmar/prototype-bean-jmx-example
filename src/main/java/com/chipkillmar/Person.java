package com.chipkillmar;

import lombok.Data;
import lombok.NonNull;
import org.springframework.context.annotation.Scope;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

import static org.springframework.beans.factory.config.ConfigurableBeanFactory.SCOPE_PROTOTYPE;

@Component
@Data
@ManagedResource
@Scope(scopeName = SCOPE_PROTOTYPE)
public class Person {

    private String firstName;
    private String lastName;
    private String emailAddress;
    private final Address address;

    public Person(@NonNull Address address) {
        this.address = address;
    }

    @ManagedAttribute
    public String getEmailAddress() {
        return emailAddress;
    }

    @ManagedAttribute
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
