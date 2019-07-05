# Spring Boot Monitoring

## Prerequisite
* Java8 or higher
* maven
* docker installed
## Getting Start
* Building the Dockerfile for the spring boot application:
```
FROM openjdk:8-jdk-alpine

MAINTAINER Phea Soy

COPY target/spring-boot-monitoring-0.0.1-SNAPSHOT.jar /usr/app/app.jar

WORKDIR /usr/app

EXPOSE 8081:8081

ENTRYPOINT ["java","-jar","app.jar"]
```

* Writting the docker-compose that combine all the application running together:
```
version: '3.7'
services:
  spring-boot-monitoring:
    container_name: spring-boot-monitoring
    build: .
    ports:
    - "8081:8081"
    networks:
      monitoring:
        aliases:
          - spring-boot-monitoring

  grafana:
    image: grafana/grafana:5.4.3
    ports:
      - 3000:3000
    volumes:
      - ./grafana:/var/lib/grafana
    environment:
      - GF_SECURITY_ADMIN_USER=admin
      - GF_SECURITY_ADMIN_PASSWORD=password
    networks:
      monitoring:
        aliases:
          - grafana

  prometheus:
    image: prom/prometheus:v2.6.1
    ports:
      - 9090:9090
    command:
      - --config.file=/etc/prometheus/prometheus.yml
    volumes:
      - ./prometheus.yml:/etc/prometheus/prometheus.yml
    networks:
      monitoring:
        aliases:
          - prometheus  
networks:
  monitoring:

```
* Go to project folder and running following command:

* Generate jar file:
```
mvn clean package
```

* Running applications with docker-compose:
```
docker-compose up -d
```
* Stop the applications:
```
docker-compose down
```
