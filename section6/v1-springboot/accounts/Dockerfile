FROM openjdk:17-jdk-slim

# information about the maintainer
MAINTAINER chandanp11.com

# add application jar to the image
COPY build/libs/accounts-0.0.1-SNAPSHOT.jar accounts-0.0.1-SNAPSHOT.jar

# execute the application
ENTRYPOINT ["java", "-jar", "accounts-0.0.1-SNAPSHOT.jar"]