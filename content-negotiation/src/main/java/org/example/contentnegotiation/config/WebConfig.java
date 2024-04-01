package org.example.contentnegotiation.config;

import org.example.contentnegotiation.serialization.converter.YamlJackson2HttpMesageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

// @Configuration diz ao spring boot, que, quando ele estiver carregando a aplicação
// ele leia a classe pq nela tem configurações da aplicação
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new YamlJackson2HttpMesageConverter());
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {

        final MediaType MEDIA_TYPE_APPLICATION_YML = MediaType.valueOf("application/x-yaml");



        // Via Query Param: http://localhost:8080/person?mediaType=xml
        /*
        configurer.favorParameter(true) // ele aceita parametros
                .parameterName("mediaType").ignoreAcceptHeader(true) // passou o nome e vai ignorar o q tiver no header;
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON) // Vai devolver como padrão um Json
                    .mediaType("json", MediaType.APPLICATION_JSON)
                    .mediaType("xml", MediaType.APPLICATION_XML);
        */

        // Via Header Param: http://localhost:8080/person
        configurer.favorParameter(false) // ele aceita parametros
                .ignoreAcceptHeader(false) // passou o nome e vai ignorar o q tiver no header;
                .useRegisteredExtensionsOnly(false)
                .defaultContentType(MediaType.APPLICATION_JSON) // Vai devolver como padrão um Json
                    .mediaType("json", MediaType.APPLICATION_JSON)
                    .mediaType("xml", MediaType.APPLICATION_XML)
                    .mediaType("x-yaml", MEDIA_TYPE_APPLICATION_YML);

    }
}
