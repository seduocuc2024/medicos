#Dockerfile
FROM openjdk:21-ea-24-oracle

WORKDIR /app
#aqui debemos asegurarnos del nombre de nuestro jar coincida
COPY target/atencion-0.0.1-SNAPSHOT.jar app.jar

#ubicacion y nombre del wallet descomprimido
COPY Wallet_PRUEBA2 /app/oracle_wallet/
EXPOSE 8080

CMD ["java",".jar","app.jar"]