FROM gradle:8.14-jdk17-alpine
COPY . /app
ADD deploy/deploy.properties /app/src/main/resources
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["gradle", "bootRun"]