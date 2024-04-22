# RemitlyTask

## JsonAWSVerifier

JsonAWSVerifier is a Java class designed to verify JSON data representing `AWS::IAM::Role Policy`. It contains methods to check the syntax of the JSON data and to verify if the Resource field in the IAM policy contains a single asterisk `*`.

## Implementation

This method first reads JSON data from a file specified by the file path and returns it as a JSONObject. It then proceeds to verify AWS syntax, including edge cases such as PolicyDocument and PolicyName requirements, checking possible keys and values, and their types for correctness. Then it ensures that the 'Resource' field exists, and if it does, it checks whether it contains a `*`.

## Installation
__Install java__
If you don't have Java 17 installed, you can must install it. You can check it by typing command:
`java -version`

__Clone repository__
Begin by cloning this repository to your local machine. Open your terminal and use the following command:
`git clone https://github.com/Konrad1w/RemitlyTask.git`

__Navigate to the Cloned Repository:__
Once the cloning process is complete, navigate to the directory where the repository has been cloned. You can use the `cd` command to change directories or open terminal in repository folder

__Run using fat jar file__
Type:
`java -jar target/JsonValidatorRemitlyTask-1.0-SNAPSHOT-jar-with-dependencies.jar "path to json file from the project root or absolute path"`

__Run tests__
Type:
`mvn test`