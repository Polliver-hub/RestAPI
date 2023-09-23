package simpleRESTfulApi.entites;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {
    Client client;

    @BeforeEach
    void beforeEachMethod ( ) {
        client = new Client();
    }
}