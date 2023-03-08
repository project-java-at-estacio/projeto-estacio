# #!/bin/bash

# # Variáveis de ambiente
# export DB_URL=jdbc:mysql://localhost:3306/biblioteca
# export DB_USER=root
# export DB_PASS=senha

# # Diretório do projeto
# cd /caminho/para/o/projeto/biblioteca

# # Compilação do projeto
# mvn clean package

# # Execução do projeto
# java -jar target/biblioteca-1.0-SNAPSHOT.jar

#!/bin/bash

# Clone the repository
git clone https://github.com/seu-usuario/nome-do-repositorio.git
cd nome-do-repositorio

# Install the necessary dependencies
sudo apt-get update
sudo apt-get install openjdk-11-jdk
sudo apt-get install mysql-server
sudo apt-get install maven

# Create the database and tables
mysql -u root -p < scripts/create_database.sql
mysql -u root -p biblioteca < scripts/create_tables.sql

# Compile the project
mvn clean compile

# Run the project
mvn exec:java -Dexec.mainClass="br.com.biblioteca.Main"

# Este arquivo inicia clonando o repositório, instalando as dependências necessárias (Java, MySQL e Maven),
# criando o banco de dados e tabelas, compilando e finalmente executando o projeto.

# Certifique-se de personalizar as linhas de comando de acordo com o seu sistema operacional e localização do projeto.
# Além disso, é importante garantir que o arquivo tenha permissão de execução (por exemplo, chmod +x startup.sh) antes de executá-lo.