package org.carlspring.strongbox.storage.validation.artifactid;

import org.carlspring.strongbox.artifact.coordinates.MavenArtifactCoordinates;
import org.carlspring.strongbox.providers.io.RepositoryFileAttributes;
import org.carlspring.strongbox.storage.validation.artifact.LowercaseValidationException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.mockito.Mock;
import org.mockito.Spy;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * Created by dinesh on 12/10/17.
 */
@Execution(CONCURRENT)
public class MavenArtifactIdLowercaseValidatorTest
{

    @Spy
    MavenArtifactIdLowercaseValidator mavenArtifactIdLowercaseValidator = new MavenArtifactIdLowercaseValidator();

    MavenArtifactCoordinates mavenArtifactCoordinates;

    @Mock
    RepositoryFileAttributes repositoryFileAttributes;

    @BeforeEach
    public void setUp()
    {
        initMocks(this);
        mavenArtifactCoordinates = new MavenArtifactCoordinates("org.dinesh.artifact.is.uppercase",
                                                                "my-maven-Artifact",
                                                                "1.0",
                                                                "classfier",
                                                                "extension");
        // repositoryFileSystem
    }

    @Test
    public void validateGroupIdCase()
            throws Exception
    {
        doReturn(repositoryFileAttributes).when(mavenArtifactIdLowercaseValidator).getAttributes(any());
        when(repositoryFileAttributes.getCoordinates()).thenReturn(mavenArtifactCoordinates);

        assertThrows(LowercaseValidationException.class, () -> {
            mavenArtifactIdLowercaseValidator.validate(null, mavenArtifactCoordinates);
        });
    }

}