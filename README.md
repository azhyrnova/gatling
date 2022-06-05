# Simple Gatling project
Created for a performance testing of custom Test API (https://github.com/azhyrnova/test-api)

How to run gatling test from console.
Example of command:
```
mvn -DuserCount=10 -DtimeDuration=5 gatling:test
```
Parameters that could be changed:
userCount - to specify the users injected atOnce at the scenarios
timeDuration - to specify the duration of rampUp in the scenarios