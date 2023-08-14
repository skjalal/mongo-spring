package com.example.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.lang.NonNull;

@Configuration
@RequiredArgsConstructor
public class MongodbConfig extends AbstractMongoClientConfiguration {

  private final MongoProperties mongoProperties;

  @Override
  protected void configureClientSettings(MongoClientSettings.Builder builder) {
    builder.applyConnectionString(new ConnectionString(mongoProperties.getUri()));
  }

  @Bean
  @NonNull
  @Override
  public MappingMongoConverter mappingMongoConverter(
      @NonNull MongoDatabaseFactory databaseFactory,
      @NonNull MongoCustomConversions customConversions,
      @NonNull MongoMappingContext mappingContext) {

    var dbRefResolver = new DefaultDbRefResolver(databaseFactory);
    var converter = new MappingMongoConverter(dbRefResolver, mappingContext);
    converter.setCustomConversions(customConversions);
    converter.setCodecRegistryProvider(databaseFactory);
    converter.setTypeMapper(new DefaultMongoTypeMapper(null));
    return converter;
  }

  @NonNull
  @Override
  protected String getDatabaseName() {
    return mongoProperties.getDatabase();
  }
}
