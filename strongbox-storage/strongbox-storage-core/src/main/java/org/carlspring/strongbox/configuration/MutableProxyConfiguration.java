package org.carlspring.strongbox.configuration;

import javax.xml.bind.annotation.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

/**
 * @author mtodorov
 */
@XmlRootElement(name = "proxy-configuration")
@XmlAccessorType(XmlAccessType.FIELD)
public class MutableProxyConfiguration
        implements Serializable
{

    @XmlAttribute
    private String host;

    @XmlAttribute
    private Integer port;

    @XmlAttribute
    private String username;

    @XmlAttribute
    private String password;

    /**
     * Proxy type (HTTP, SOCKS5, etc)
     */
    @XmlAttribute
    private String type;

    @XmlElementWrapper(name = "non-proxy-hosts")
    private List<String> nonProxyHosts = new ArrayList<>();


    public MutableProxyConfiguration()
    {
    }

    public MutableProxyConfiguration(String host,
                                     Integer port,
                                     String username,
                                     String password,
                                     String type,
                                     List<String> nonProxyHosts)
    {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.type = type;
        this.nonProxyHosts = nonProxyHosts;
    }

    public String getHost()
    {
        return host;
    }

    public void setHost(String host)
    {
        this.host = host;
    }

    public Integer getPort()
    {
        return port;
    }

    public void setPort(Integer port)
    {
        this.port = port;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public List<String> getNonProxyHosts()
    {
        return nonProxyHosts;
    }

    public void setNonProxyHosts(List<String> nonProxyHosts)
    {
        this.nonProxyHosts = nonProxyHosts;
    }

    public void addNonProxyHost(String host)
    {
        nonProxyHosts.add(host);
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MutableProxyConfiguration that = (MutableProxyConfiguration) o;
        return Objects.equal(port, that.port) &&
               Objects.equal(host, that.host) &&
               Objects.equal(username, that.username) &&
               Objects.equal(password, that.password) &&
               Objects.equal(type, that.type) &&
               Objects.equal(nonProxyHosts, that.nonProxyHosts);
    }

    @Override
    public int hashCode()
    {
        return Objects.hashCode(host, port, username, password, type, nonProxyHosts);
    }

    @Override
    public String toString()
    {
        return MoreObjects.toStringHelper(this)
                          .add("host", host)
                          .add("port", port)
                          .add("username", username)
                          .add("password", password)
                          .add("type", type)
                          .add("nonProxyHosts", nonProxyHosts)
                          .toString();
    }

}
