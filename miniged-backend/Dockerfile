FROM openjdk:11-jdk
COPY . /app
RUN apt-get -y update && apt -y install maven && apt-get clean
ADD . /app
WORKDIR /app
RUN mvn clean package -DskipTests
EXPOSE 8080
ENTRYPOINT [ "java" , "-jar" , "/app/target/miniged-0.0.1-SNAPSHOT.jar" ]