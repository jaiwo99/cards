package com.jaiwo99.cards.util;

import com.jaiwo99.cards.domain.Jiang;
import com.jaiwo99.cards.domain.Role;
import com.jaiwo99.cards.domain.RoleType;
import com.jaiwo99.cards.repository.JiangRepository;
import com.jaiwo99.cards.repository.RoleRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author liang - jaiwo99@gmail.com
 */
@Component
public class EntityGenerator {

    private static final Logger logger = LoggerFactory.getLogger(EntityGenerator.class);

    @Autowired
    private JiangRepository jiangRepository;
   
    @Autowired
    private RoleRepository roleRepository;

    public void generateJiang(int count) {
        while(count-- > 0) {
            generateJiang();
        }
    }

    public Jiang generateJiang() {
        final String name = randomName();
        logger.debug("Generating Jiang[name:{}]", name);
        return jiangRepository.save(new Jiang(name));
    }

    private String randomName() {
        return "generated-jiang:" + UUID.randomUUID().toString();
    }
    

    public void generateRoles(int count) {
       switch (count) {
       case 6:
    	   roleRepository.save(new Role(RoleType.KING));
    	   roleRepository.save(new Role(RoleType.DEFECTOR));
    	   roleRepository.save(new Role(RoleType.LOYALIST));
    	   roleRepository.save(new Role(RoleType.LOYALIST));
    	   roleRepository.save(new Role(RoleType.REBEL));
    	   roleRepository.save(new Role(RoleType.REBEL));
    	   break;
       default:
           logger.error("not supported [count:{}]", count);
    	   break;
       }
    }
    
    
}
