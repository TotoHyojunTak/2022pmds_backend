FROM amazoncorretto:11 AS build
#ARG JAR_FILE=build/libs/*.jar
#COPY --from=build /libs/*.jar app.jar
COPY --from=build /app/build/libs/*.jar /app/app.jar
WORKDIR /app/
ENTRYPOINT ["java","-jar","/app.jar"]
# ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/app.jar"]
# => 설정파일을 분리해서 사용할 때
# java -jar -Dspring.profiles.active=prod app.jar

