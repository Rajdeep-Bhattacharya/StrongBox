package org.carlspring.strongbox.forms.configuration;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * @author Przemyslaw Fusik
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "layout")
public abstract class CustomRepositoryConfigurationForm
{

    public abstract <T> T accept(CustomRepositoryConfigurationFormVisitor<T> visitor);
}
