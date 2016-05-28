package com.groovycoder

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/**
 * General integration and smoke tests for {@link MonsterShopApplication}.
 */
@ContextConfiguration // not mentioned by docs, but had to include this for Spock to startup the Spring context
@SpringBootTest
class MonsterShopApplicationSpecIT extends Specification {

    @Autowired
    ApplicationContext context

    def "starts without errors"() {
        expect: "spring context exists"
        context != null
    }

}
