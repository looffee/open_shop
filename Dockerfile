FROM amazoncorretto:22-alpine-jdk as builder
WORKDIR /application
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

FROM amazoncorretto:22-alpine-jdk
WORKDIR /application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./
ENV DB_HOST=localhost
ENV DB_PORT=3306
ENV DB_DATABASE=open_shop_test
ENV DB_USERNAME=root
ENV DB_PASSWORD=my-secret-pw
ENV DB_TYPE=mySql
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]
EXPOSE 8080