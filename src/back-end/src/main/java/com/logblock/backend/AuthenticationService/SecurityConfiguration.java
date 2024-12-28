package com.logblock.backend.AuthenticationService;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.logblock.backend.ProfileService.ProfileService;


@Configuration
@EnableWebSecurity
@Profile("!development")
public class SecurityConfiguration {

    @Autowired
    private Environment env;

	@Autowired
	private ProfileService profileService;
    
	@Bean
	public ClientRegistrationRepository userDetailsService() {
		return new InMemoryClientRegistrationRepository(this.googleClientRegistration());
	}

	@Bean 
	@Order(2)
	public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http
			.csrf((csrf) -> csrf.disable())
			.authorizeHttpRequests((authorize) -> authorize
				.anyRequest().authenticated()
			);

		return http.build();
	}

	/**
	 * Catches authentication-related endpoints and proceed 
	 * @param http
	 * @return
	 * @throws Exception
	 */
	@Bean 
	@Order(1)
	public SecurityFilterChain authenticationChain(HttpSecurity http) throws Exception {
		String[] authentication_endpoint = {
			"/oauth/google",
			"/oauth2/authorization/google",
			"/login/oauth2/code/google"
		};
		http
			.csrf((csrf) -> csrf.disable())
			.securityMatcher(authentication_endpoint)
			.authorizeHttpRequests((authorize) -> authorize 			// pre-authorizing authentication endpoints
				.requestMatchers(new AntPathRequestMatcher("/admin/**")).hasAuthority("LB_ADMIN")
				.anyRequest().authenticated()
			)
			.oauth2Login((configurer) -> {
				configurer.successHandler(this.successHandler()).failureHandler(this.failureHandler())
				.userInfoEndpoint(userInfo -> userInfo
			        .userAuthoritiesMapper(this.userAuthoritiesMapper())
			    );
			});

		return http.build();
	}

    @Bean
    OAuthSucessHandler successHandler() {
        return new OAuthSucessHandler();
    }
    
    @Bean
    SimpleUrlAuthenticationFailureHandler failureHandler() {
        return new SimpleUrlAuthenticationFailureHandler();
    }

	@Bean
	GrantedAuthoritiesMapper userAuthoritiesMapper() {
		return (authorities) -> {
			Set<GrantedAuthority> mappedAuthorities = new HashSet<>();
			mappedAuthorities.add(new SimpleGrantedAuthority("LB_USER"));
			authorities.forEach(authority -> {
				if (OidcUserAuthority.class.isInstance(authority)) {
					OidcUserAuthority oidcUserAuthority = (OidcUserAuthority)authority;

					Map<String, Object> userAttributes = oidcUserAuthority.getAttributes();

					com.logblock.backend.DataSource.Model.Profile p = profileService.getProfileByEmail((String) userAttributes.get("email"));
					if(p == null) return;
					if(p.getPrivLevel() >= 2) {
						mappedAuthorities.add(new SimpleGrantedAuthority("LB_ADMIN"));
					}
				}
			});

			return mappedAuthorities;
		};
	}

    @Bean
	public CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.addAllowedHeader("*");
		config.setAllowedMethods(List.of("GET", "POST", "DELETE", "QUERY"));
		config.addAllowedOrigin(
			env.getProperty("logblock.front-end-integration.server-hostname") + 
			":" + 
			env.getProperty("logblock.front-end-integration.server-port"));
		config.setAllowCredentials(true);
		source.registerCorsConfiguration("/**", config);
		return source;
	}

    private ClientRegistration googleClientRegistration() {
        return CommonOAuth2Provider.GOOGLE.getBuilder("google")
		.clientId(env.getProperty("logblock.auth.oauth.google.client-id"))
		.clientSecret(env.getProperty("logblock.auth.oauth.google.client-secret"))
		.build();
	}
}
