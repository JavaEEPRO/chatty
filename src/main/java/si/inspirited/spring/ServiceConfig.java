package si.inspirited.spring;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "si.inspirited.service" })
public class ServiceConfig {}