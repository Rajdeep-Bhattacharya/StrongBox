package org.carlspring.strongbox.config;

import org.carlspring.strongbox.providers.repository.RepositoryProviderRegistry;
import org.carlspring.strongbox.providers.datastore.StorageProviderRegistry;

import javax.inject.Inject;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "org.carlspring.strongbox.artifact",
                 "org.carlspring.strongbox.configuration",
                 "org.carlspring.strongbox.io",
                 "org.carlspring.strongbox.providers",
                 "org.carlspring.strongbox.services",
                 "org.carlspring.strongbox.storage",
                 "org.carlspring.strongbox.xml"
               })
public class StorageCoreConfig
{

    @Inject
    private StorageProviderRegistry storageProviderRegistry;

    @Inject
    private RepositoryProviderRegistry repositoryProviderRegistry;

}
