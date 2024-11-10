package org.example.redisexamples.component;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Slf4j
class ULIDTest {
    @Autowired
    private ULID ulid;

    @Test
    void nextULID() {
        //01JCAWP1QC6ETT8BRWPK220XAT
        log.info(ulid.nextULID());
        //01JCAWP1QCG1C8NQ39N39MNH46
        log.info(ulid.nextULID());
        //01JCAWP1QC45GP79SK642R9F6P
        log.info(ulid.nextULID());
    }
}