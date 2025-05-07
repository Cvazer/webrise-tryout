FROM gradle:8.14-jdk17-alpine
COPY . /app
WORKDIR /app
EXPOSE 8080
ENTRYPOINT ["gradle", "bootRun"]