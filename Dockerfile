# base image -- FROM
FROM openjdk:8

# install required RUN + apt-get
# RUN apt-get update
# RUN apt-get install -y maven

# WORKDIR /code

# COPY ADD
# COPY pom.xml /code/pom.xml
# ADD src /code/src
# RUN ["mvn", "package"]

ADD /target/spring-taskmanager-maven-0.0.1-SNAPSHOT.jar /code1/target/spring-taskmanager-maven-0.0.1-SNAPSHOT.jar

CMD [ "java","-jar","/code1/target/spring-taskmanager-maven-0.0.1-SNAPSHOT.jar" ]