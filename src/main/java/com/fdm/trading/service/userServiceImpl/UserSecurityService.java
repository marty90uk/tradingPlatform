package com.fdm.trading.service.userServiceImpl;

import com.fdm.trading.dao.UserDao;
import com.fdm.trading.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService {//implements UserDetailsService {

//    private static final Logger LOG = LoggerFactory.getLogger(UserDetailsService.class);

    @Autowired
    private UserDao userDao;

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        User u = userDao.findByUsername(username);
//        if (null == u){
//            LOG.warn("Username {} not found", username);
//            throw new UsernameNotFoundException("Username " + username + " not found");
//        }
//        return u;
//    }
}
