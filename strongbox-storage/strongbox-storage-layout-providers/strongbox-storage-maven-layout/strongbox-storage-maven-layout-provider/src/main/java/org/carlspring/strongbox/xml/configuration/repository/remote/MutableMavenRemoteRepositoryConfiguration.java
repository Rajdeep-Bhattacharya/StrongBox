package org.carlspring.strongbox.xml.configuration.repository.remote;

import org.carlspring.strongbox.xml.repository.remote.CustomRemoteRepositoryConfiguration;
import org.carlspring.strongbox.xml.repository.remote.MutableRemoteRepositoryConfiguration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "maven-remote-repository-configuration")
public class MutableMavenRemoteRepositoryConfiguration extends MutableRemoteRepositoryConfiguration
{

    @Override
    public CustomRemoteRepositoryConfiguration getImmutable()
    {
        return new MavenRemoteRepositoryConfiguration();
    }

}
