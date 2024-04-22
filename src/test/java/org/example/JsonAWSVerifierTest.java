package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class JsonAWSVerifierTest {

    @Test
    void emptyJson() {
        boolean result = JsonAWSVerifier.verifyAsterisk("src/main/resources/test1.json");
        Assertions.assertTrue(result);
    }

    @Test
    void jsonWithoutPolicyName() {
        boolean result = JsonAWSVerifier.verifyAsterisk("src/main/resources/test2.json");
        Assertions.assertTrue(result);
    }

    @Test
    void jsonWithArrayOfPolicyName() {
        boolean result = JsonAWSVerifier.verifyAsterisk("src/main/resources/test3.json");
        Assertions.assertTrue(result);
    }

    @Test
    void jsonWithNotOnlyPolicyNameAndPolicyDocument() {
        boolean result = JsonAWSVerifier.verifyAsterisk("src/main/resources/test4.json");
        Assertions.assertTrue(result);
    }

    @Test
    void jsonWithoutVersion() {
        boolean result = JsonAWSVerifier.verifyAsterisk("src/main/resources/test5.json");
        Assertions.assertTrue(result);
    }

    @Test
    void jsonWithVersionAsNumber() {
        boolean result = JsonAWSVerifier.verifyAsterisk("src/main/resources/test6.json");
        Assertions.assertTrue(result);
    }

    @Test
    void jsonWithNotAllowedVersion() {
        boolean result = JsonAWSVerifier.verifyAsterisk("src/main/resources/test11.json");
        Assertions.assertTrue(result);
    }


    @Test
    void jsonWithMoreThanPolicyDocumentValuesLikeVersionAndStatement() {
        boolean result = JsonAWSVerifier.verifyAsterisk("src/main/resources/test7.json");
        Assertions.assertTrue(result);
    }

    @Test
    void jsonWithVersionAsArray() {
        boolean result = JsonAWSVerifier.verifyAsterisk("src/main/resources/test8.json");
        Assertions.assertTrue(result);
    }

    @Test
    void jsonWithStatementEmpty() {
        boolean result = JsonAWSVerifier.verifyAsterisk("src/main/resources/test9.json");
        Assertions.assertTrue(result);
    }

    @Test
    void jsonWithoutEffect() {
        boolean result = JsonAWSVerifier.verifyAsterisk("src/main/resources/test10.json");
        Assertions.assertTrue(result);
    }

    @Test
    void jsonWithoutResource() {
        boolean result = JsonAWSVerifier.verifyAsterisk("src/main/resources/test12.json");
        Assertions.assertTrue(result);
    }

    @Test
    void jsonWithResourceLikeNumber() {
        boolean result = JsonAWSVerifier.verifyAsterisk("src/main/resources/test13.json");
        Assertions.assertTrue(result);
    }

    @Test
    void jsonWithMoreStatementsWithOneWithEmpyAction() {
        boolean result = JsonAWSVerifier.verifyAsterisk("src/main/resources/test14.json");
        Assertions.assertTrue(result);
    }

    @Test
    void jsonWithMoreStatementsWithUnknownParameterStatement() {
        boolean result = JsonAWSVerifier.verifyAsterisk("src/main/resources/test15.json");
        Assertions.assertTrue(result);
    }

    @Test
    void jsonWithMoreStatementsWithOneWithSidAsObject() {
        boolean result = JsonAWSVerifier.verifyAsterisk("src/main/resources/test16.json");
        Assertions.assertTrue(result);
    }

    @Test
    void jsonWithMoreStatementsWithResourceAsListWithAsterisk() {
        boolean result = JsonAWSVerifier.verifyAsterisk("src/main/resources/test17.json");
        Assertions.assertFalse(result);
    }

    @Test
    void jsonWithMoreStatementsWithResourceAsStringWithAsteriskAndResourceWithEmptyList() {
        boolean result = JsonAWSVerifier.verifyAsterisk("src/main/resources/test18.json");
        Assertions.assertFalse(result);
    }

    @Test
    void jsonWithMoreStatementsWithResourceWithTwoAsterisks() {
        boolean result = JsonAWSVerifier.verifyAsterisk("src/main/resources/test19.json");
        Assertions.assertTrue(result);
    }

    @Test
    void bigTestWithOneAsterisk() {
        boolean result = JsonAWSVerifier.verifyAsterisk("src/main/resources/test20.json");
        Assertions.assertFalse(result);
    }
}
