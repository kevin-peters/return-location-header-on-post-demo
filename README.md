# return-location-header-on-post-demo
Demo project for Spring Boot to show usage of the location header

## curl commands to post new SomeEntity

curl localhost:8080/someEntities -v -X POST --header "Content-Type: application/json" -d "{\"value\" : \"someValue\"}"

curl localhost:8080/someEntitiesViaController -v -X POST --header "Content-Type: application/json" -d "{\"value\" : \"someValue\"}"
