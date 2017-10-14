package com.ankurpathak.config.prod;

import com.ankurpathak.annotation.Prod;
import com.ankurpathak.security.CustomTokenEnhancer;
import com.ankurpathak.security.CustomUserDetailsService;
import com.ankurpathak.security.oauth2.CustomClientDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * Created by ankur on 09-03-2017.
 */
@Configuration
@EnableAuthorizationServer
@Prod
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;



    @Bean
    public TokenStore tokenStore() throws Exception{
        return new JwtTokenStore(accessTokenConverter());
    }


    //@Value("${oauth2.certificate.privateKey}") private String privateKey;
    //@Value("${oauth2.certificate.publicKey}") private String publicKey;

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() throws Exception{
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.afterPropertiesSet();
       // codec.setSigningKey(privateKey);

       // codec.setVerifierKey(publicKey);

        KeyStoreKeyFactory keyStoreKeyFactory =
                new KeyStoreKeyFactory(new ClassPathResource("ankurpathak.jks"), "dtr72153".toCharArray());
        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("ankurpathak"));
        return converter;
    }




    @Bean
    public TokenEnhancer tokenEnhancer() {
        return new CustomTokenEnhancer();
    }


    @Bean
    public TokenEnhancerChain tokenEnhancerChain() throws Exception{
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(
                Arrays.asList(tokenEnhancer(), accessTokenConverter()));

        return tokenEnhancerChain;
    }




    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {






        /*


        clients.inMemory()
                .withClient("client")
                .secret("clientpassword")
                .scopes("ACCESS")
                .redirectUris("http://localhost/response")
                .authorizedGrantTypes("authorization_code", "refresh_token", "password", "implicit", "client_credentials"); */

         clients.withClientDetails(customClientDetailsService);




    }
    @Autowired
    private CustomClientDetailsService customClientDetailsService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;



    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.authenticationManager(authenticationManager)
                .tokenStore(tokenStore())
                .tokenEnhancer(tokenEnhancerChain())
                .userDetailsService(customUserDetailsService);

    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //security.checkTokenAccess("permitAll()");
        //security.tokenKeyAccess("permitAll()");

    }



}

