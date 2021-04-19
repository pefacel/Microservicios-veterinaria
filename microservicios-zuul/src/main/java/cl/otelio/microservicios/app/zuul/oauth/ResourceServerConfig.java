package cl.otelio.microservicios.app.zuul.oauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {

		resources.tokenStore(tokenStore());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {

		// Todo el mundo se puede authenticar
		http.authorizeRequests().antMatchers("/api/security/oauth/token").permitAll()
		.antMatchers(HttpMethod.GET, "/api/mascotas/listar","/api/clientes/listar", "/api/usuarios/usuarios").permitAll()
		.antMatchers(HttpMethod.GET, "/api/mascotas/ver/{id}", "/api/clientes/{id}","/api/usuarios/usuarios/{id}")
		.hasAnyRole("ADMIN","USER")
		.antMatchers(HttpMethod.POST,"/api/mascotas/", "/api/clientes/", "/api/usuarios/usuarios").hasAnyRole("ADMIN")
		.antMatchers(HttpMethod.PUT,"/api/mascotas/{id}", "/api/clientes/{id}", "/api/usuarios/usuarios/{id}").hasAnyRole("ADMIN")
		.antMatchers(HttpMethod.DELETE,"/api/mascotas/{id}", "/api/clientes/{id}", "/api/usuarios/usuarios/{id}").hasAnyRole("ADMIN")

		;
	}

	@Bean
	public JwtTokenStore tokenStore() {
		return new JwtTokenStore(accessTokenConverter());
	}

	@Bean
	public JwtAccessTokenConverter accessTokenConverter() {
		JwtAccessTokenConverter tokenConverter = new JwtAccessTokenConverter();
		tokenConverter.setSigningKey("algun_codigo_secreto_patata");

		return tokenConverter;
	}

}
