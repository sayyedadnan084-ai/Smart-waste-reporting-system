FROM node:22 AS frontend
WORKDIR /app/frontend

COPY frontend/package.json ./
RUN npm install

COPY frontend/ ./
RUN npm run build


FROM maven:3.9-eclipse-temurin-17 AS backend
WORKDIR /app

COPY backend/pom.xml backend/pom.xml
COPY backend/src backend/src

COPY --from=frontend /app/frontend/dist backend/src/main/webapp

RUN mvn -f backend/pom.xml clean package -DskipTests


FROM tomcat:10.1-jdk17-temurin

COPY --from=backend /app/backend/target/cleancity.war /usr/local/tomcat/webapps/ROOT.war

EXPOSE 10000

CMD ["sh", "-c", "sed -i 's/port=\"8080\"/port=\"${PORT:-10000}\"/' /usr/local/tomcat/conf/server.xml && catalina.sh run"]
