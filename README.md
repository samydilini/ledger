1.Dependency injection can be seen at Services
2.classes are doing there own responsibilities.
3.Services are using singleton to instantiate.
4.87% test coverage is achieved
5.Error handling and validating is done for the files.

Assumptions
A loan will be identified by user name and the bank name
a second loan will not happen with the same user name and id

Technologies used
1. java 11
2. junit tests
3. Mockito
4. maven
5. Git
6. maven-assembly-plugin

How to run (steps 2 and 3 are already done)

1.	cd com.ledger.loan.calulator
2.  `mvn clean install -DskipTests -q assembly:single` to compile
5.	`java -jar ./target/geektrust.jar <PATH>/files/test1.txt` to run the app
6.  Find the out put file "result_output.csv" in target/classes

Important: last two entries in the second test set are producing different emi remaining times. The calculations done to produce this is attached as images.

Sample correct outputs are as follows,

$ java -jar ./target/geektrust.jar ~/mine/practice/ledger/ledger/files/test1.txt
##################
IDIDI Dale 1000 55
IDIDI Dale 8000 20
MBI Harry 1044 12
MBI Harry 0 24
##################

$ java -jar ./target/geektrust.jar ~/mine/practice/ledger/ledger/files/test2.txt
##################
IDIDI Dale 1326 9
IDIDI Dale 3652 4
UON Shelly 15856 2
MBI Harry 9032 9
##################

Error handling example
##################
Jun. 14, 2021 12:54:32 PM calculator.com.ledger.loan.calulator.services.LoanManager makePayment
WARNING: loan was not found for the user : Dale with Bank NameIDIDI
##################
