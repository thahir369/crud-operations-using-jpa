# crud-operations-using-jpa


### command to create an encrypted values with secrete-key: hbk
1. mvn jasypt:encrypt-value "-Djasypt.encryptor.password=hbk" "-Djasypt.plugin.value=rootuser"
2. this is one time process to create encrypted values and adding in application.yml file
