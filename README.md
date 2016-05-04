# README

# Setting up

For settings up you will need following:
* Maven version 3 and above
* Java SE 8
* MySQL version 5.6
* Redis (in case of using redis pub/sub messaging service)
* Weblogic 12.2.1 (for svwi application)

Install libraries under libs folder to your local maven repository
Adamantium jar should have group id "org.primefaces" artifact id "adamantium-theme" and version 2.0, ex. "mvn install:install-file -Dfile=.\sysmon\libs\adamantium-theme-2.0.jar -DgroupId=org.primefaces -DartifactId=adamantium-theme -Dversion=2.0 -Dpackaging=jar -DlocalRepositoryPath=.\repository"
Also, install loggins slf4j library to your local repository, ex. "mvn install:install-file -Dfile=.\sysmon\libs\logging-slf4j-1.0.1-SNAPSHOT.jar -DgroupId=org.eclipse.persistence -DartifactId=logging-slf4j -Dversion=1.0.1-SNAPSHOT -Dpackaging=jar --settings mvn-settings.xml"

All db patches are in db folder and should be applied in version order and secondary in order of number in file name.

Protobuf compiler should be places in utils folder which should be placed in the same folder (/utils/protoc) as sysmon folder.
Compiler can be found here: https://developers.google.com/protocol-buffers/docs/downloads.

# Starting up

To launch agent application location of configuration file, ex: "-DappConfig=D:\Projects\sysmon\sysmon\agent\src\main\resources\application.properties"

To launch receiver application you will need to specify two configuration files:
* Application configuration file, ex. "-DappConfig=D:\Projects\sysmon\sysmon\receiver\src\main\resources\application.properties"
* Database configuration file, ex. "-DdbConfig=D:\Projects\sysmon\sysmon\core\src\main\resources\database.properties"

To launch svwi application you will need to specify only database configuration file as in the example above.

Database connection can be configured with local database usage via url and specified username and password and with JNDI using settings of application server.

Source code contains examples of configurations files, all configuration files should be copied somewhere to protect source code from modifications in case of not editing logic of configuration files.