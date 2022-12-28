package Hibernate.Control.Main;

@Configuration
@EnableJpaRepositories(basePackages = "com.baeldung.persistence.dao")
@PropertySource("database.properties")
@EnableTransactionManagement
public class JpaConfig {
}
