FROM adoptopenjdk/openjdk11
COPY ./target/conductores-0.0.1-SNAPSHOT.jar /usr/src/bootdocker/conductores-0.0.1-SNAPSHOT.jar
WORKDIR /usr/src/bootdocker
EXPOSE 8089
ENTRYPOINT ["java", "-jar", "conductores-0.0.1-SNAPSHOT.jar"]
