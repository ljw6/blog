package com.ljw.blog.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.sql.DataSource;

@EnableWebSecurity
@Configuration
public class DefSecuConf extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private DataSource dataSource;//注入数据源

    //配置对象
    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        //jdbcTokenRepository.setCreateTableOnStartup(true);
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.exceptionHandling().accessDeniedPage("/NotAuthor.html");//配置403页面
        http.formLogin() //自定义登录页面跳转
                .loginPage("/login")//登录页面设置
//            .loginProcessingUrl("/user/login")//登录访问路径
                .defaultSuccessUrl("/all").permitAll()//登录成功，跳转页面
                .and().authorizeRequests()
                .antMatchers("/all", "/css/**", "/js/**", "/images/**", "/test/registration").permitAll()
                //.antMatchers("/test/index").hasAnyAuthority("admin,user")//页面权限配置
                //.antMatchers("/test/index").hasAnyRole("sale")//基于角色的访问控制
                .antMatchers("/**").authenticated()
                .and().rememberMe().tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60).userDetailsService(userDetailsService);
        //.and().csrf().disable();//关闭csrf保护
        //http.logout().logoutUrl("/logout").logoutSuccessUrl("/test/hello").permitAll();//用户注销
        http.cors().and().csrf().disable().antMatcher("/");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.debug(true);
        web
                .ignoring()
                .antMatchers("/", "/resources/**", "/favicon.ico", "/css/**", "/js/**", "/images/**", "/regeist", "/test/checkUserOnly", "/error", "/test/checkPhoneOnly", "/static/**", "/article/*");
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
