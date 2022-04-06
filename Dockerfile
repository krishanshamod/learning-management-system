FROM openjdk:19-jdk-alpine3.15
EXPOSE 8080:8080
COPY ./target/learning-management-system.jar /opt/lms/learning-management-system.jar
WORKDIR /opt/lms
ENTRYPOINT ["java","-jar","learning-management-system.jar"]
