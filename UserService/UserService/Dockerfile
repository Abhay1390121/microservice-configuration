# Use lightweight Java runtime
FROM openjdk:27-ea-15-jdk-oracle

# Set working directory
WORKDIR /app

# Copy jar file
COPY target/student_service.jar student_service.jar

# Run the jar
ENTRYPOINT ["java", "-jar", "student_service.jar"]