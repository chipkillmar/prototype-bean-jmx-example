# Prototype Beans and JMX in Spring Boot

I ran into the following `NoSuchBeanDefinitionException` trying to export prototype scoped beans
using `MBeanExporter` that had me puzzled.  Why couldn't the constructor parameter be found?

It turns out that exporting the prototypes was working fine, but that I needed to exclude the
singleton bean from being exported via:

```
mbeanExporter.addExcludedBean("person")
```

## Spring Boot logs

Here are the logs that include the mysterious exception:

```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::        (v2.2.4.RELEASE)

2020-03-02 10:44:42.566  INFO 81455 --- [           main] com.chipkillmar.Application              : Starting Application on ... with PID 81455 (/Users/.../java/main started by ckillmar in /Users/...)
2020-03-02 10:44:42.571  INFO 81455 --- [           main] com.chipkillmar.Application              : No active profile set, falling back to default profiles: default
2020-03-02 10:44:43.209  WARN 81455 --- [           main] s.c.a.AnnotationConfigApplicationContext : Exception encountered during context initialization - cancelling refresh attempt: org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'person' defined in file [/Users/.../java/main/com/chipkillmar/Person.class]: Unsatisfied dependency expressed through constructor parameter 0; nested exception is org.springframework.beans.factory.NoSuchBeanDefinitionException: No qualifying bean of type 'com.chipkillmar.Address' available: expected at least 1 bean which qualifies as autowire candidate. Dependency annotations: {}
2020-03-02 10:44:43.218  INFO 81455 --- [           main] ConditionEvaluationReportLoggingListener : 

Error starting ApplicationContext. To display the conditions report re-run your application with 'debug' enabled.
2020-03-02 10:44:43.346 ERROR 81455 --- [           main] o.s.b.d.LoggingFailureAnalysisReporter   : 

***************************
APPLICATION FAILED TO START
***************************

Description:

Parameter 0 of constructor in com.chipkillmar.Person required a bean of type 'com.chipkillmar.Address' that could not be found.


Action:

Consider defining a bean of type 'com.chipkillmar.Address' in your configuration.

```

## Running

To run the application:

```
./gradlew bootRun
```