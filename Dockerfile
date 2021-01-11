FROM openjdk:8
EXPOSE 8080
ADD /target/eticket-0.0.1-SNAPSHOT.jar service-e-ticket.jar
ENTRYPOINT ["java","-jar","service-e-ticket.jar"]