This project is a template for typical Spring Boot + Gradle case.

## Modules

1) buildSrc - Gradle dependencies and plugins configuration
2) model - contains only data classes
3) common - most common classes for all other modules.
4) backend - microservice business logic without Spring dependency 
5) backend-app - endpoints and DI configuration for backend module


## How to start

1) Enable Lombock support in your IDE, see https://gitlab.inventale.com/platform/spring-boot-template/wikis/Enable-Lombock-in-Idea for details
1) Run `com.inventale.project.HelloWorldApplication`
2) Try it: `curl localhost:8090/hello -v`

## Profiles

We use [Spring profiles](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-profiles)
to deal with environment-specific configurations.
Most common properties are located in the `/config` directory.
Each application can have its own properties in the `resources` path.

## Secret properties

Secret properties can be placed in `local` profile which is active by default
You should copy `config/application-local.yml.template` to `config/application-local.yml`
and specify needed properties.

This file is in gitignore, so the data will be on your local copy only.

## Use cases
* Build project: `./gradlew clean build`

* You can see all outdated dependencies using the command `./gradlew dependencyUpdates`

* You can see information about build and tests `./gradlew clean test --scan`

* Spring boot has built-in tools to get meta information about your application
called [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/current/reference/html/production-ready-features.html)
You can find it after application starts: `http://localhost:8090/actuator/`

* To change active profiles, you can change the value of the `spring.profiles.active` property in `config/application.yml`
or override this property by environment one. See order and other helpful information in the article https://docs.spring.io/spring-boot/docs/1.2.2.RELEASE/reference/html/boot-features-external-config.html.

## Dependency check

We use `owasp` for check dependencies by the plugin `org.owasp.dependencycheck`.
See `id(Plugins.owasp)` in `build.gradle.kts`. Runs by `./gradlew dependencyCheckAnalyze` in `.gitlab-ci.yml`


## Task tree
Optional plugin shows tasks, which will be executed at calling another task. It can help with debugging tasks dependencies while creating your task or optimizing time spent on checks

Example usage: `./gradlew build taskTree --task-depth 3`  
Output will be like:
```
> Task :sdk:taskTree

------------------------------------------------------------
Project :sdk
------------------------------------------------------------

:sdk:build
+--- :sdk:assemble
|    +--- :sdk:assembleDebug
|    |    +--- :sdk:bundleDebugAar ..>
|    +--- :sdk:assembleRelease
|    |    +--- :sdk:bundleReleaseAar ..>
|    +--- :sdk:compileKotlinLinux
|    +--- :sdk:compileKotlinMacos
|    +--- :sdk:compileKotlinMingw
|    +--- :sdk:metadataJar
|    +--- :sdk:mingwMetadataJar
|    \--- :sdk:sourcesJar
\--- :sdk:check
     +--- :sdk:allTests
     |    +--- :sdk:jvmTest ..>
     |    +--- :sdk:linuxTest ..>
     |    +--- :sdk:macosTest ..>
     |    \--- :sdk:mingwTest ..>
     +--- :sdk:ktlintCommonMainSourceSetCheck
     +--- :sdk:ktlintMingwTestSourceSetCheck
     +--- :sdk:lint
     |    +--- :sdk:prepareLintJar
     |    +--- :sdk:processDebugManifest ..>
     |    \--- :sdk:processReleaseManifest ..>
     \--- :sdk:test
          +--- :sdk:testDebugUnitTest ..>
          \--- :sdk:testReleaseUnitTest ..>


(..>) - subtree omitted (exceeds task-depth)
```

More examples you can find at plugin's [homepage](https://github.com/dorongold/gradle-task-tree)

## Prometheus metrics

All exported by application business metrics are declared in `com.inventale.project.metrics.PrometheusMetrics`
HTTP endpoints metrics are exported by default with name `http_server_requests_seconds` 

After the application starts, you can check them here:
* List of all available metrics: `http://localhost:8090/actuator/metrics`
* Metrics page for Prometheus: `http://localhost:8090/actuator/prometheus`
![](resources/prometheus_metrics.png)

Please note that metrics will appear only after the first call of `/hello` endpoint.

If you want to add `@Timed` annotation please note that it works only for classes but not for interfaces. 

Please pay attention on `com.inventale.project.HelloWorldService.getHelloWorld`.
Here you can find an example of usage of the counter `hello_world_count` and the gauge `hello_world_letters_number`

An example of usage of `@Timed` annotation can be found in `com.inventale.project.DefaultHelloWorldMessageProvider`,
`com.inventale.project.DevHelloWorldMessageProvider` and `com.inventale.project.controller.HelloWorldController`.

This annotation requires the `io.micrometer.core.aop.TimedAspect` bean defined in your Spring configuration.

JVM metrics like memory usage and CPU utilization are exported by default.

## How to run application on AWS

1) Login to inventale-dev (6543-7075-3871) AWS account.
2) Navigate to instance https://eu-west-1.console.aws.amazon.com/ec2/v2/home?region=eu-west-1#InstanceDetails:instanceId=i-09ca0c1024757321d
3) Start Instance (Actions -> Start Instance)

If this instance has been deleted, you can your own with Amazon Machine Image with id `ami-08dd8b0ac2036800e` and name `Spring Boot Template`.

Please see the article how to create eс2 instance from custom AMI https://aws.amazon.com/premiumsupport/knowledge-center/launch-instance-custom-ami/ 

This AMI is available only in Ireland on inventale-dev account - nowhere else it will be available.

Service will be started on instance startup.

You can see these metrics in [Prometheus UI](http://prom-de.inventale.com:9090/graph?g0.range_input=1h&g0.expr=hello_world_letters_number&g0.tab=1&g1.range_input=1h&g1.expr=hello_world_letters_number&g1.tab=1&g2.range_input=1h&g2.expr=hello_message_provider_timed_seconds_sum&g2.tab=1&g3.range_input=1h&g3.expr=http_server_requests_seconds_sum&g3.tab=1).
This link works only with Maxifier VPN.
![](resources/prometheus.png)

Prometheus can be used as a datasource for Grafana. You can see a dashboard here: https://grafana.inventale.com/d/j9ouX_NGz/platform?orgId=1&from=now-15m&to=now
![](resources/grafana.png)
