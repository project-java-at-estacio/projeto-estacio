# Define a imagem base a ser utilizada
FROM openjdk:11-jre-slim

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo .jar do projeto para o diretório de trabalho
COPY target/nome-do-arquivo.jar app.jar

# Expõe a porta em que a aplicação será executada
EXPOSE 8080

# Define o comando a ser executado ao iniciar o contêiner
CMD ["java", "-jar", "app.jar"]
