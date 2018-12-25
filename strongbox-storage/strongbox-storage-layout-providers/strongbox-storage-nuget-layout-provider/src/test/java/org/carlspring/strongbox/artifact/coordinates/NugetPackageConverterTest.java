package org.carlspring.strongbox.artifact.coordinates;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class NugetPackageConverterTest
{

    @Test
    public void testArtifactPathToCoordinatesConversion()
        throws Exception
    {
        String path = "Org.Carlspring.Strongbox.Examples.Nuget.Mono/1.0/Org.Carlspring.Strongbox.Examples.Nuget.Mono.1.0.nupkg";
        NugetArtifactCoordinates nac = NugetArtifactCoordinates.parse(path);
        assertEquals("Org.Carlspring.Strongbox.Examples.Nuget.Mono", nac.getId(),
                     "Failed to convert path to artifact coordinates!");
        assertEquals("1.0", nac.getVersion(), "Failed to convert path to artifact coordinates!");

        path = "Org.Carlspring.Strongbox.Examples.Nuget.Mono/1.0/Org.Carlspring.Strongbox.Examples.Nuget.Mono.nuspec";
        nac = NugetArtifactCoordinates.parse(path);
        assertEquals("Org.Carlspring.Strongbox.Examples.Nuget.Mono", nac.getId(),
                     "Failed to convert path to artifact coordinates!");
        assertEquals("1.0", nac.getVersion(), "Failed to convert path to artifact coordinates!");

        path = "Org.Carlspring.Strongbox.Examples.Nuget.Mono/1.0/Org.Carlspring.Strongbox.Examples.Nuget.Mono.1.0.nupkg.sha512";
        nac = NugetArtifactCoordinates.parse(path);
        assertEquals("Org.Carlspring.Strongbox.Examples.Nuget.Mono", nac.getId(),
                     "Failed to convert path to artifact coordinates!");
        assertEquals("1.0", nac.getVersion(), "Failed to convert path to artifact coordinates!");
    }

}
