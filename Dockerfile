FROM maven:3.8.5-ibm-semeru-17-focal as build
COPY . /home/app
WORKDIR /home/app
RUN mvn clean package

FROM openjdk:19-jdk-alpine3.15 as production
EXPOSE 8080:8080
COPY --from=build /home/app/target/learning-management-system.jar /opt/lms/learning-management-system.jar
WORKDIR /opt/lms
ENTRYPOINT ["java","-jar","learning-management-system.jar"]