FROM maven:3.9.9-eclipse-temurin-23 AS compiler
ARG APP_DIR=/app 
WORKDIR ${APP_DIR}
COPY pom.xml .
COPY mvnw .
COPY mvnw.cmd .
COPY src src
RUN chmod +x mvnw
RUN mvn clean package -Dmaven.test.skip=true 
FROM maven:3.9.9-eclipse-temurin-23
ARG DEPLOY_DIR=/code_folder
WORKDIR ${DEPLOY_DIR}
COPY --from=compiler /app/target/movies-0.0.1-SNAPSHOT.jar PAFExam.jar
ENV SERVER_PORT=4000
EXPOSE ${SERVER_PORT}
ENTRYPOINT SERVER_PORT=${SERVER_PORT} java -jar PAFExam.jar