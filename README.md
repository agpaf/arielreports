
${moduleName}
==========================

descrição
-----------
Este é um módulo muito básico que pode ser usado como ponto de partida na criação de um novo módulo.

Compilando a partir da Fonte
--------------------
Você precisará ter o Java 1.6+ e o Maven 2.x + instalados. Use o comando 'mvn package' para
compilar e empacotar o módulo. O arquivo estará na pasta omod / target.

Você pode adicionar o snippet fornecido na página [Criando módulos] (https://wiki.openmrs.org/x/cAEr) à sua página.
omod / pom.xml e use o comando mvn:

    pacote mvn -P deploy-web -D deploy.path = "../../ openmrs-1.8.x / webapp / src / main / webapp"

Isso permitirá que você implante quaisquer alterações em sua web
recursos como js ou arquivos sem reinstalar o módulo. O caminho de implantação diz
onde o OpenMRS está implantado.

Instalação
------------
1. Faça o build do módulo para produzir o ficheiro .omod
2. Use a tela Administração do OpenMRS> Gerenciar módulos para fazer upload e instalar o ficheiro .omod

Se uploads não forem permitidos na web (alteráveis ​​por meio de uma propriedade de tempo de execução), poderá colocar o ficheiro .omod
na pasta ~ / .OpenMRS / modules. (Onde ~ / .OpenMRS é considerado o directório de dados do aplicativo que o openmrs em execução está usando atualmente.)
basta reiniciar o OpenMRS / tomcat e o módulo será carregado e iniciado.
