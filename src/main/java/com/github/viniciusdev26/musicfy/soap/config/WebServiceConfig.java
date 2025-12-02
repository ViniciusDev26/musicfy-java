package com.github.viniciusdev26.musicfy.soap.config;

import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig {

    @Bean
    public ServletRegistrationBean<MessageDispatcherServlet> messageDispatcherServlet(ApplicationContext applicationContext) {
        MessageDispatcherServlet servlet = new MessageDispatcherServlet();
        servlet.setApplicationContext(applicationContext);
        servlet.setTransformWsdlLocations(true);
        return new ServletRegistrationBean<>(servlet, "/soap/*");
    }

    @Bean(name = "UserService")
    public DefaultWsdl11Definition userWsdl11Definition(XsdSchema usersSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("UserServicePort");
        wsdl11Definition.setLocationUri("/soap");
        wsdl11Definition.setTargetNamespace("http://musicfy.viniciusdev26.github.com/soap/users");
        wsdl11Definition.setSchema(usersSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema usersSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/users.xsd"));
    }

    @Bean(name = "MusicService")
    public DefaultWsdl11Definition musicWsdl11Definition(XsdSchema musicsSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("MusicServicePort");
        wsdl11Definition.setLocationUri("/soap");
        wsdl11Definition.setTargetNamespace("http://musicfy.viniciusdev26.github.com/soap/musics");
        wsdl11Definition.setSchema(musicsSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema musicsSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/musics.xsd"));
    }

    @Bean(name = "PlaylistService")
    public DefaultWsdl11Definition playlistWsdl11Definition(XsdSchema playlistsSchema) {
        DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
        wsdl11Definition.setPortTypeName("PlaylistServicePort");
        wsdl11Definition.setLocationUri("/soap");
        wsdl11Definition.setTargetNamespace("http://musicfy.viniciusdev26.github.com/soap/playlists");
        wsdl11Definition.setSchema(playlistsSchema);
        return wsdl11Definition;
    }

    @Bean
    public XsdSchema playlistsSchema() {
        return new SimpleXsdSchema(new ClassPathResource("xsd/playlists.xsd"));
    }
}
