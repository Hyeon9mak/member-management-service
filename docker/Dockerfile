FROM openjdk:17
COPY /build/libs/member-management-service.jar member-management-service.jar
ENTRYPOINT ["java", "-Dspring.profiles.active=${PROFILE}", "-Dnaver-cloud.api.access-key=${NAVER_CLOUD_API_ACCESS_KEY}", "-Dnaver-cloud.api.secret-key=${NAVER_CLOUD_API_SECRET_KEY}", "-Dnaver-cloud.sens.api.service-id=${NAVER_CLOUD_SENS_API_SERVICE_ID}", "-Dnaver-cloud.sens.api.sms.message.from=${NAVER_CLOUD_SENS_API_MESSAGE_FROM}", "-jar", "member-management-service.jar"]
