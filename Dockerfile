FROM java:8-jre

ADD ./target/restaurant_manager_project-1.0-SNAPSHOT.jar /app/
CMD ["java", "-jar", "/app/restaurant_manager_project-1.0-SNAPSHOT.jar"]

EXPOSE 3075