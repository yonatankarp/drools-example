# Drools & SpringBoot Example

This repository provides a straightforward illustration of leveraging
Spring Boot and Drools to construct a rule system. This rule system can be
employed to process requests by considering specific parameters, making it more
user-friendly for non-engineering team members.

The different rules are defined under the [rules](src/main/kotlin/com/yonatankarp/drools/rules)
directory and can be extended by adding a new implementation of the `Rule`
interface.

The rules are then aggregated into a map between the rule name and its
implementation in [RulesConfig.kt](src/main/kotlin/com/yonatankarp/drools/config/RulesConfig.kt)
and later on used in the service.

All the rules are defined in the [risk-rules.drl](src/main/resources/rules/risk-rules.drl)

## Running the project locally

1. Build the project by running the command:

```shell
./gradlew build
```

2. Execute the Spring boot application:

```shell
./gradlew bootRun
```

3. Once the application is up and running you can use the following command to
   call the service:

```shell
curl --location 'localhost:8080/risk' \
--header 'Content-Type: application/json' \
--data-raw '{
    "countryCode": "DE",
    "customerNumber": "12345",
    "email": "john.doe@gmail.com"
}'
```

You should see the rules that have been applied to the request in the logs, and
the value `true` in the response from the service. If no rule was applied, the
response from the service would be `false`.
