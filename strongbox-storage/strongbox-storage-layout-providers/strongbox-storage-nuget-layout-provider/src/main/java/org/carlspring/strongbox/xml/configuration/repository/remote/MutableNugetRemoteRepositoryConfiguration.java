package org.carlspring.strongbox.xml.configuration.repository.remote;

import org.carlspring.strongbox.xml.repository.remote.CustomRemoteRepositoryConfiguration;
import org.carlspring.strongbox.xml.repository.remote.MutableRemoteRepositoryConfiguration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "nuget-remote-repository-configuration")
public class MutableNugetRemoteRepositoryConfiguration extends MutableRemoteRepositoryConfiguration
{

    @Override
    public CustomRemoteRepositoryConfiguration getImmutable()
    {
        return new NugetRemoteRepositoryConfiguration();
    }

}
